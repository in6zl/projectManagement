<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-select
                v-model="warehouseId"
                size="small"
                clearable
                placeholder="请选择内仓"
                style="width:95%"
              >
                <el-option
                  v-for="item in fawarehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="second">
              <el-input placeholder="请输入编码" size="small" style="width:95%" v-model="code"></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>
      <div class="wareHouseManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px">货架列表</div>
          <el-button
            size="small"
            @click="toAddFaStorageBin(),changBg(1)"
            type="primary"
            style="margin-bottom:10px"
            v-if="haveBtn('faStorageBin:add')"
          >新增</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="code" label="货架编号 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="layers" label="总层数" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="warehouseName" label="所属内仓" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <el-tooltip content="编辑货架" placement="top" v-if="haveBtn('faStorageBin:edit')">
                <svg-icon icon-class="edit" @click="updataFaStorageBin(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除货架" placement="top" v-if="haveBtn('faStorageBin:delete')">
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
import searchForm from "@/components/commonTop/index";
import {
  getFaStorageBinPage,
  transformFaStorageBinList,
  deleteFaStorageBin,
} from "@/api/faStorageBin";
import { findFaWarehouse, getFaWarehouseList } from "@/api/faWareHouse";
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
      pageNo:1,
      pageSize:10,
      ischange: 1,
      tableData: [],
      pageData: {
        startIndex: 0,
        endIndex: 0,
        totalElements: 0,
        currentPage: 0,
        totalPages: 0,
      },
      code: "",
      address: "",
      fawarehouseList: [],
      warehouseId: null,
      num: 1,
      searchForm: {
        warehouseId: null,
        address: "",
        pageable: { pageNo: 0, pageSize: 10 },
      },
    };
  },
  mounted() {
    this.faStorageBinPage();
    getFaWarehouseList(new Object()).then((res) => {
      // console.log('res.data',res.data);
      this.fawarehouseList = res.data;
    });
  },
  methods: {
    transformPage(data) {
      this.pageData.startIndex =
        data.pageable.pageNumber * data.pageable.pageSize + 1;
      if (data.pageable.pageNumber + 1 == data.totalPages) {
        this.pageData.endIndex = data.totalElements;
      } else {
        this.pageData.endIndex =
          (data.pageable.pageNumber + 1) * data.pageable.pageSize;
      }
      this.pageData.totalElements = data.totalElements;
      this.pageData.currentPage = data.pageable.pageNumber + 1;
      this.pageData.totalPages = data.totalPages;
      //  console.log('this.pageData',this.pageData);
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaStorageBin() {
      this.$router.push("/addFaStorageBin");
    },
    updataFaStorageBin(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editFaStorageBin", query: { id: id } });
    },
    faStorageBinPage() {
      getFaStorageBinPage(this.searchForm).then((res) => {
        if (res.code === "0") {
          this.tableData = transformFaStorageBinList(res.data.content);
          this.transformPage(res.data);
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
            deleteFaStorageBin(rows[index].id).then((res) => {
              if (res.code === "0") {
                this.$message({
                  message: "删除成功",
                  type: "success",
                });
                rows.splice(index, 1);
                let number = rows.length === 0 ? this.num - 1 : this.num;
                this.handleCurrentChange(number);
              } else {
                this.$message({
                  message: res.message,
                  type: "error",
                });
              }
            });
          }
        },
      });
    },
    changBg(index) {
      this.ischange = index;
    },
    search() {
      this.searchForm.code = this.code;
      this.searchForm.warehouseId = this.warehouseId;
      this.searchForm.pageable.pageNo = 0;
      this.faStorageBinPage();
    },
    handleCurrentChange(currentPage) {
      this.num = currentPage;
      this.pageNo = currentPage
      this.searchForm.pageable.pageNo = currentPage - 1;
      this.faStorageBinPage();
    },
    handleSizeChange() {},
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
<style>
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.wareHouseManageTable {
  background-color: white;
  margin-top: 16px;
}
</style>