<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('input.pleaseEnter')+$t('role.roleName')"
                size="small"
                v-model="roleName"
                style="width:95%"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>

          <div style="clear:both"></div>
        </el-form>
      </div>
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px;font-weight:700">{{$t('role.roleList')}}</div>
          <el-button
            size="small"
            v-if="haveBtn('role:add')"
            @click="toAddRole(),changBg(1)"
            type="primary"
            style="margin-bottom:10px"
          >{{$t('button.add')}}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :header-cell-style="tableHeaderColor"
          :height="tableHeight"
        >
          <el-table-column type="index" :label="$t('table.serial')" align="center" width="60px"></el-table-column>
          <el-table-column prop="name" :label="$t('role.roleName')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="description"
            :label="$t('role.desc') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="createTime"
            :label="$t('role.createTime')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            align="center"
            width="150px"
          >
            <template slot-scope="scope">
              <svg-icon
                icon-class="edit"
                v-if="haveBtn('role:edit')"
                @click="updataRole(scope.$index,tableData)"
              ></svg-icon>
              <svg-icon
                icon-class="delete"
                v-if="haveBtn('role:delete')"
                @click="deleteRow(scope.$index, tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging :pageSize="pageSize" :pageNo="pageNum" :totalElements="totalElements" @currentChange="handleCurrentChange"></paging>
  </div>
</template>

<script>
import { getRoleList, deleteRole, page } from "@/api/role";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
export default {
  components: {
    searchForm,
    lef,
    paging
  },
  data() {
    return {
      index: 1,
      ischange: 1,
      tableData: [],
      roleName: "",
      createTime: "",
      totalElements: "",
      pageNum: 1,
      pageSize: 10,
      totalPages: "",
      tableHeight: window.innerHeight - 320,
    };
  },
  mounted() {
    this.roleList();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddRole() {
      this.$router.push("/addRole");
    },
    changBg(index) {
      this.ischange = index;
    },
    roleList() {
      let data = {
        pageParam: { pageNo: this.pageNum, pageSize: this.pageSize },
      };
      page(data).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalElements = res.data.totalElements;
          this.totalPages = res.data.totalPages;
        }
      });
    },
    handleCurrentChange(cpage) {
      this.pageNum = cpage - 1;
      this.roleList();
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteRole(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              if (this.pageNum != 0 && rows.length == 0) {
                this.pageNum = this.pageNum - 1;
              }
              this.roleList();
            } else {
              this.$message.error(res.message);
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    updataRole(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editRole", query: { id: id } });
    },
    search() {
      let data = {};
      data.name = this.roleName;
      data.createTime = this.createTime;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      page(data)
        .then((res) => {
          if (res.code === "0") {
            loading.close();
            this.tableData = res.data.content;
            this.totalElements = res.data.totalElements;
            this.totalPages = res.data.totalPages;
          } else {
            loading.close();
          }
        })
        .catch((err) => {
          loading.close();
        });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
<style lang='scss' scoped>
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.roleManageTable {
  background: white;
}
.el-row {
  margin-right: 0px !important;
}
/* .el-scrollbar {
  position: unset;
} */
</style>
