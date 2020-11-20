<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-select
                v-model="warehouseId"
                placeholder="请选择内仓"
                clearable
                @change="findFaStorageBinList()"
                size="small"
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
              <el-select
                v-model="storageBinId"
                placeholder="请选择货架"
                clearable
                size="small"
                style="width:95%"
              >
                <el-option
                  v-for="item in faStorageBinList"
                  :key="item.id"
                  :label="item.code"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="three" id="nameStyle">
              <el-input placeholder="请输入库位编码" size="small" v-model="code" style="width:95%"></el-input>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="small"
            @click="search"
            v-if="haveBtn('faStorehouseManage')"
          >搜索</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="wareHouseManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">库位列表</div>
          <div>
            <el-button
              size="small"
              @click="toAddFaStorehouse(),changBg(1)"
              type="primary"
              style="margin-bottom:10px"
              v-if="haveBtn('faStorehouse:add')"
            >新增</el-button>
            <el-button
              size="small"
              @click="printFastonehouse(),changBg(2)"
              :class="{changBg:ischange == 2}"
              v-if="haveBtn('faStorehouse:print')"
            >打印</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
          @selection-change="handleSelectionChange"
        >
          >
          <el-table-column type="selection" align="center" width="60"></el-table-column>
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="code" label="库位编号 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="warehouseName" label="内仓" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="shelvesCode" label="货架编码 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="rowNum" label="库位所在层数" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="columnNum" label="库位所在列数" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="tagNames" label="标签" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" widht="150" align="center">
            <template slot-scope="scope">
              <el-tooltip
                content="编辑标签"
                placement="top"
                v-if="haveBtn('faStorehouseTagRelationship')"
              >
                <svg-icon
                  icon-class="example"
                  @click="faStorehouseTagRelationship(scope.$index,tableData)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="编辑库位" placement="top" v-if="haveBtn('faStorehouse:edit')">
                <svg-icon icon-class="edit" @click="updataFaStorehouse(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除库位" placement="top" v-if="haveBtn('faStorehouse:delete')">
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
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
import {
  getFaStorehousePage,
  transformFaStorehouseList,
  deleteFaStorehouse,
} from "@/api/faStorehouse";
import { getFaStorageBinList } from "@/api/faStorageBin";
import { findFaWarehouse, getFaWarehouseList } from "@/api/faWareHouse";
export default {
  components: {
    searchForm,
    lef,
    paging,
  },
  data() {
    return {
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      pageNo: 1,
      pageSize: 10,
      tableData: [],
      pageData: {
        startIndex: 0,
        endIndex: 0,
        totalElements: 0,
        currentPage: 0,
        totalPages: 0,
      },
      code: "",
      num: 1,
      address: "",
      warehouseId: null,
      fawarehouseList: [],
      storageBinId: null,
      faStorageBinList: [],
      searchForm: {
        warehouseId: null,
        storageBinId: null,
        name: "",
        pageable: { pageNo: 0, pageSize: 10 },
      },
      faStorehouseList: [],
    };
  },
  mounted() {
    this.faStorehousePage();
    getFaWarehouseList(new Object()).then((res) => {
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
    },
    findFaStorageBinList() {
      getFaStorageBinList({ warehouseId: this.warehouseId }).then((res) => {
        this.faStorageBinList = res.data;
        this.storageBinId = "";
        this.fawarehouseList.forEach((v) => {
          if (this.warehouseId == v.id) {
            this.businessId = v.type;
          }
        });
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaStorehouse() {
      this.$router.push("/addFaStorehouse");
    },
    updataFaStorehouse(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editFaStorehouse", query: { id: id } });
    },
    faStorehouseTagRelationship(index, rows) {
      let id = rows[index].id;
      this.$router.push({
        path: "/faStorehouseTagRelationship",
        query: { id: id },
      });
    },
    faStorehousePage() {
      getFaStorehousePage(this.searchForm).then((res) => {
        if (res.code === "0") {
          this.tableData = transformFaStorehouseList(res.data.content);
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
          if (action == "confirm") {
            deleteFaStorehouse(rows[index].id).then((res) => {
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
    changBg(index) {
      this.ischange = index;
    },
    search() {
      this.searchForm.warehouseId = this.warehouseId;
      this.searchForm.storageBinId = this.storageBinId;
      this.searchForm.code = this.code;
      this.searchForm.pageable.pageNo = 0;
      this.faStorehousePage();
    },
    handleCurrentChange(currentPage) {
      this.num = currentPage;
      this.pageNo = currentPage;
      this.searchForm.pageable.pageNo = currentPage - 1;
      this.faStorehousePage();
    },
    handleSizeChange() {},
    print(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/printFaStorehouse", query: { id: id } });
    },
    handleSelectionChange(val) {
      this.faStorehouseList = val;
    },
    printFastonehouse() {
      if (this.faStorehouseList.length == 0) {
        this.$message({
          message: "请选择需要打印的库位",
        });
      } else {
        localStorage.setItem(
          "faStorehouseList",
          JSON.stringify(this.faStorehouseList)
        );
        this.$router.push("/printFaStorehouse");
      }
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
/* .main .el-form-item:nth-of-type(4){
  float: right;
} */
#nameStyle {
  float: none;
}
.wareHouseManageTable {
  background-color: white;
  margin-top: 16px;
}
</style>