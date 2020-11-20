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
              <el-input placeholder="请输入仓库地址" size="small" style="width:95%" v-model="address"></el-input>
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
          <div style="line-height:34px" class="subtit">内仓列表</div>
          <el-button
            size="small"
            @click="toAddFaWareHouse(),changBg(1)"
            type="primary"
            style="margin-bottom:10px"
            v-if="haveBtn('faWareHouse:add')"
          >新增</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="name" label="内仓名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="area" label="仓库面积 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="address" label="仓库地址" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="longitude" label="经度" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="latitude" label="纬度" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="type" label="仓库类型" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="isFull" label="是否满仓" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="isOpen" label="是否起用" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <span title="3d内仓">
                <svg-icon icon-class="3D" @click="goWareHouse(scope.$index,tableData)"></svg-icon>
              </span>
              <el-tooltip content="编辑内仓" placement="top" v-if="haveBtn('faWareHouse:edit')">
                <svg-icon icon-class="edit" @click="updateFaWareHouse(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除内仓" placement="top" v-if="haveBtn('faWareHouse:delete')">
                <svg-icon icon-class="delete" @click="deleteRow(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
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
    </div>
  </div>
</template>

<script>
import {
  getFaWarehousePage,
  transformFaWarehouseList,
  deleteWareHouse,
} from "@/api/faWareHouse";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      pageData: {
        startIndex: 0,
        endIndex: 0,
        totalElements: 0,
        currentPage: 0,
        totalPages: 0,
      },
      name: "",
      address: "",
      num: 1,
      searchForm: {
        name: "",
        address: "",
        pageable: { pageNo: 0, pageSize: 10 },
      },
      imgUrl: require("@/assets/images/delete.png"),
    };
  },
  mounted() {
    this.faWarehousePage();
  },
  methods: {
    goWareHouse() {
      this.$router.push("/3d");
    },
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
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaWareHouse() {
      this.$router.push("/addFaWareHouse");
    },
    updateFaWareHouse(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editFaWareHouse", query: { id: id } });
    },
    faWarehousePage() {
      getFaWarehousePage(this.searchForm).then((res) => {
        if (res.code === "0") {
          this.tableData = transformFaWarehouseList(res.data.content);
          this.transformPage(res.data);
        }
        // loading.close();
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        callback: (action) => {
          if (action == "confirm") {
            deleteWareHouse(rows[index].id).then((res) => {
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
      this.searchForm.name = this.name;
      this.searchForm.address = this.address;
      this.searchForm.pageable = { pageNo: 0, pageSize: 10 };
      this.faWarehousePage();
    },
    handleCurrentChange(currentPage) {
      this.num = currentPage;
      this.searchForm.pageable.pageNo = currentPage - 1;
      this.faWarehousePage();
    },
    handleSizeChange() {},
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
.backimg {
  width: 39px;
  height: 42px;
  text-align: center;
  margin: 0 auto;
  background-image: url("/delete.png");
}
.wareHouseManageTable {
  background-color: white;
  margin-top: 16px;
}
</style>
