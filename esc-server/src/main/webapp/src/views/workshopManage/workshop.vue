<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input placeholder="请输入车间名称" size="small" style="width:95%" v-model="name"></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input placeholder="请输入车间地址" size="small" style="width:95%" v-model="address"></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-input placeholder="请输入联系人" size="small" style="width:95%" v-model="manager"></el-input>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item slot="first">
              <el-input placeholder="请输入联系方式" size="small" style="width:95%" v-model="phone"></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">车间列表</div>
          <el-button
            size="small"
            @click="addWorkShop(),changBg(1)"
            type="primary"
            style="margin-bottom:10px"
            v-if="haveBtn('workshop:add')"
          >{{$t('button.add')}}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column prop="name" label="车间名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="address" label="车间地址" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="manager" label="联系人" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="phone" label="联系方式" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip content="修改" placement="top" v-if="haveBtn('workshop:edit')">
                <svg-icon
                  icon-class="edit"
                  @click="updateWorkshop(scope.$index,tableData)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top" v-if="haveBtn('workshop:delete')">
                <svg-icon
                  icon-class="delete"
                  @click="deleteRow(scope.$index, tableData)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="size"
          :page-size="pageSize"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          small
        ></el-pagination>
      </div>
    </div>-->
    <paging
      :pageSize="pageSize"
      :pageNo="pageNum"
      :totalElements="size"
      @currentChange="handleCurrentChange"
    ></paging>
  </div>
</template>
<script>
import { deleteWorkshop, pageWorkshop } from "@/api/workshop";
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
      tableHeight: window.innerHeight - 400,
      tableData: [],
      totalPages: "100",
      pageNum: 1,
      pageSize: 10,
      size: 0,
      num: 1,
      currentPage: 1,
      name: "",
      address: "",
      manager: "",
      phone: "",
      workshop: {
        name: "",
        address: "",
        manager: "",
        phone: "",
      },
    };
  },
  mounted() {
    pageWorkshop({}, this.pageNum-1, this.pageSize).then((res) => {
      this.tableData = res.data.content;
      this.totalPages = res.data.totalPages;
      this.size = res.data.totalElements;
    });
  },
  methods: {
    handleCurrentChange(val) {
      this.num = val;
      let curr = val - 1;
      this.pageNum = val
      pageWorkshop(this.workshop, curr, 10).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalElements;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    addWorkShop() {
      this.$router.push("/addWorkshop");
    },
    deleteRow(index, rows) {
      this.$confirm("是否删除", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteWorkshop(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              let number =
                rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
              this.handleCurrentChange(number);
            } else {
              this.$message({
                message: res.message,
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    updateWorkshop(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editWorkshop", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      this.workshop.name = this.name;
      this.workshop.address = this.address;
      this.workshop.manager = this.manager;
      this.workshop.phone = this.phone;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageWorkshop(this.workshop, this.pageNum, this.pageSize).then((res) => {
        this.tableData = res.data.content;
        this.totalPages = res.data.totalPages;
        this.size = res.data.totalElements;
        loading.close();
      });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
// .el-button:focus {
//   background-color: #409eff !important;
//   color: #ffffff;
// }
</style>