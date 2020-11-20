<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.organizationName')"
                size="small"
                style="width:95%"
                v-model="organizationName"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
        </el-form>
      </div>
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div class="subtit" style="line-height:34px">{{$t('main.organizationList')}}</div>
          <el-button
            size="small"
            type="primary"
            @click="toAddOrg(),changBg(1)"
            style="margin-bottom:10px"
            v-if="haveBtn('organization:add')"
          >{{$t('button.add')}}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="width: 100%;min-height:350px;"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" align="center" width="60px" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="name"
            :label="$t('table.organizationName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <!--          <el-table-column-->
          <!--            prop="type"-->
          <!--            :label="$t('table.organizationType')"-->
          <!--            width="135"-->
          <!--            :show-overflow-tooltip="true"-->
          <!--            :formatter="typeFormatter"-->
          <!--          ></el-table-column>-->
          <el-table-column prop="phone" :label="$t('table.phone')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="address"
            :label="$t('table.address')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="createTime"
            :label="$t('table.createTime')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            fixed="right"
            align="center"
            width="150px"
          >
            <template slot-scope="scope">
              <svg-icon
                icon-class="edit"
                v-if="haveBtn('organization')"
                @click.native="updataOrganization(scope.$index,tableData)"
              ></svg-icon>
              <svg-icon
                v-if="haveBtn('organization:delete')"
                icon-class="delete"
                @click.native="deleteRow(scope.$index, tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging
      :pageSize="pageSize"
      :pageNo="pageNum"
      :totalElements="totalElements"
      @currentChange="currentChange"
    ></paging>
  </div>
</template>

<script>
import { deleteOrganization, page } from "@/api/organization";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
export default {
  components: {
    searchForm,
    lef,
    paging,
  },
  data() {
    return {
      organizationName: "",
      organizationType: "",
      createTime: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      totalElements: "",
      pageNum: 1,
      pageSize: 10,
      totalPages: "",
      typeList: [
        { id: 0, name: "组织" },
        { id: 1, name: "供应商" },
      ],
    };
  },
  mounted() {
    this.organizationList();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    handleCurrentChange(page) {
      this.pageNum = page - 1;
      this.organizationList();
    },
    toAddOrg() {
      this.$router.push("/addOrganization");
    },
    changBg(index) {
      this.ischange = index;
    },
    organizationList() {
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
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteOrganization(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              if (this.pageNum != 0 && rows.length == 0) {
                this.pageNum = this.pageNum - 1;
              }
              this.organizationList();
            } else {
              this.$message.error(res.message);
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    updataOrganization(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editOrganization", query: { id: id } });
    },
    typeFormatter(row, column) {
      if (row.type === 1) {
        return "供应商";
      }
      if (row.type === 0) {
        return "组织";
      }
    },
    search() {
      let data = {};
      data.name = this.organizationName;
      // data.type = this.organizationType;
      data.LocalDateTime = this.createTime;
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
