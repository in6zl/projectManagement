<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input placeholder="请输入名称" size="small" style="width:95%" v-model="name"></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input placeholder="请输入标签值" size="small" style="width:95%" v-model="value"></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTab" style="background:white">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="width:200px;line-height:34px" class="subtit">标签列表</div>
          <div>
            <el-button
              size="small"
              @click="toAddFaWareHouseTag()"
              type="primary"
              style="margin-bottom:10px"
              v-if="haveBtn('faWareHouseTag:add')"
            >新增</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="name" label="名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="value" label="标签值" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <el-tooltip content="编辑标签" placement="top" v-if="haveBtn('faWareHouseTag:edit')">
                <svg-icon icon-class="edit" @click="updataFaWareHouseTag(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除标签" placement="top" hide-after="100">
                <svg-icon icon-class="delete" @click="deleteRow(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- <div class="paging">
      <div>显示第 {{pageData.startIndex}} 至 {{pageData.endIndex}} 项结果，共 {{pageData.totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="pageData.totalElements"
          background
          small
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageData.currentPage"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="10"
        ></el-pagination>
      </div>
    </div>-->
    <paging
      :pageSize="pageSize"
      :pageNo="pageNo"
      :totalElements="pageData.totalElements"
      @currentChange="handleCurrentChange"
    ></paging>
  </div>
</template>

<script>
import { findFaWarehouse } from "@/api/faWareHouse";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
import {
  getFaWarehouseTagPage,
  transformFaWarehouseTagList,
  deleteWareHouseTag,
} from "@/api/faWareHouseTag";
export default {
  components: {
    searchForm,
    lef,
    paging,
  },
  data() {
    return {
      pageNo: 1,
      pageSize: 10,
      tabIndex: 1,
      activeName: "1",
      tableHeight: window.innerHeight - 350,
      index: 1,
      num: 1,
      ischange: 1,
      tagIschange: 1,
      tableData: [],
      searchForm: {
        name: null,
        value: null,
        pageable: { pageNo: 0, pageSize: 10 },
        pageNo: 1,
        pageSize: 10,
      },
      name: null,
      value: null,
      pageData: {
        startIndex: 0,
        endIndex: 0,
        totalElements: 0,
        currentPage: 0,
        totalPages: 0,
      },
    };
  },
  mounted() {
    this.faWarehouseTagPage();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaWareHouseTag() {
      this.$router.push("/addFaWareHouseTag");
    },
    updataFaWareHouseTag(index, rows) {
      this.searchForm.id = rows[index].id;
      this.$router.push({
        path: "/editFaWareHouseTag",
        query: this.searchForm,
      });
    },
    faWarehouseTagPage() {
      this.tableData = null;
      this.editableTabsValue = 1;
      this.searchForm.pageable.pageNo = 0;
      let data = this.searchForm;
      getFaWarehouseTagPage(data).then((res) => {
        // console.log(res);
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.pageData.totalElements = res.data.totalElements;
          this.transformPage(res.data);
          this.searchForm.tableName = "tag";
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        callback: (action) => {
          console.log("action: ", action);
          if (action == "confirm") {
            deleteWareHouseTag(rows[index].id).then((res) => {
              if (res.code === "0") {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                rows.splice(index, 1);
                let number = rows.length === 0 ? this.num - 1 : this.num;
                this.handleCurrentChange(number);
              }
            });
          }
        },
      });
    },
    search() {
      this.searchForm.name = this.name;
      this.searchForm.value = this.value;
      this.searchForm.pageable.pageNo = 0;
      let data = this.searchForm;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      getFaWarehouseTagPage(data).then((res) => {
        // console.log(res);
        if (res.code === "0") {
          loading.close();
          this.tableData = res.data.content;
          this.pageData.totalElements = res.data.totalElements;
          this.transformPage(res.data);
        } else {
          loading.close();
        }
      });
    },
    handleCurrentChange(currentPage) {
      this.num = currentPage;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      // console.log(currentPage);
      this.searchForm.pageable.pageNo = currentPage - 1;
      this.searchForm.pageNo = currentPage - 1;
      this.pageNo = currentPage;
      let data = this.searchForm;
      getFaWarehouseTagPage(data).then((res) => {
        if (res.code === "0") {
          loading.close();
          this.tableData = res.data.content;
          this.pageData.totalElements = res.data.totalElements;
          this.transformPage(res.data);
        } else {
          loading.close();
        }
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.wareHouseManageTable {
  background-color: white;
  margin-top: 16px;
}
.main >>> .el-tabs__header {
  margin: 0px !important;
}
.main >>> .el-tabs__nav-scroll {
  margin-bottom: 1px;
  background: white;
  padding-left: 16px;
}
.main .userEdit {
  margin-top: 16px;
}
</style>
