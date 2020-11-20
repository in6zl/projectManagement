<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <!-- 用户搜索栏 -->
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input v-model="searchName" placeholder="请输入产线名称" size="small" style="width:95%" />
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <!-- 数据展示栏 -->
      <div class="roleManageTable">
        <!-- 用户编辑栏 -->
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">产线列表</div>
          <el-button
            v-if="haveBtn('productionLine:add')"
            size="small"
            type="primary"
            style="margin-bottom:10px"
            @click="toAddProductionLine(),changBg(1)"
          >新增</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号" />
          <el-table-column prop="name" label="产线名称" :show-overflow-tooltip="true" />
          <el-table-column prop="address" label="产线位置 " :show-overflow-tooltip="true" />
          <el-table-column prop="chejian.name" label="所属车间" :show-overflow-tooltip="true" />
          <el-table-column prop="warehouse.name" label="内仓" :show-overflow-tooltip="true" />
          <el-table-column prop="operation" label="操作" align="center" width="150">
            <template slot-scope="scope">
              <el-tooltip effect="dark" content="编辑" placement="top">
                <svg-icon
                  v-if="haveBtn('productionLine:edit')"
                  icon-class="edit"
                  @click="editItem(scope.row)"
                />
              </el-tooltip>
              <el-tooltip effect="dark" content="删除" placement="top">
                <svg-icon
                  v-if="haveBtn('productionLine:delete')"
                  icon-class="delete"
                  @click="deleteItem(scope.$index, tableData)"
                />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging
      :pageSize="pageSize"
      :pageNo="pageIndex"
      :totalElements="totalElements"
      @currentChange="pageIndexChange"
    ></paging>
  </div>
</template>

<script>
import { findAllProductionLineData, deleteItem } from "@/api/productLine";
import { parseTime } from "@/utils/index";
import { haveBtn } from "@/permission";
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
      searchName: "",
      searchTime: "",
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      pageTotal: 0,
      pageSize: 10,
      pageIndex: 1,
      totalElements: 0,
      num: 1,
      searchItem: {
        searchName: "",
      },
      currentPage: 1,
    };
  },
  computed: {
    pageStart() {
      return (this.pageIndex - 1) * this.pageSize + 1;
    },
    pageEnd() {
      return this.pageIndex * this.pageSize;
    },
  },
  mounted() {
    this.initFindAllProductionLineData();
  },
  methods: {
    search() {
      this.currentPage = 1;
      this.searchItem.searchName = this.searchName;
      findAllProductionLineData({
        name: this.searchItem.searchName,
        pageIndex: this.pageIndex - 1,
        pageSize: this.pageSize,
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    deleteItem(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteItem(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            let number =
              rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
            this.pageIndexChange(number);
          } else {
            this.$message({
              message: res.message,
              type: "error",
            });
          }
        });
      });
    },
    // 编辑数据
    editItem(item) {
      console.info(item);
      this.$router.push({
        path: "/editProductionLine",
        query: { id: item.id },
      });
    },
    // 点击页码
    pageIndexChange(index) {
      this.num = index;
      this.pageIndex = index
      findAllProductionLineData({
        name: this.searchItem.searchName,
        pageIndex: index - 1,
        pageSize: this.pageSize,
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    // 初始化DataGrid数据
    initFindAllProductionLineData() {
      findAllProductionLineData({
        pageIndex: this.pageIndex - 1,
        pageSize: this.pageSize,
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddProductionLine() {
      this.$router.push("/addProductionLine");
    },
    changBg(index) {
      this.ischange = index;
    },
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
</style>
