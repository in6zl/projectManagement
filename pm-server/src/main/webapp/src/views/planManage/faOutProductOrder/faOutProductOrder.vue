<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="orderNo"
                :placeholder="$t('main.placeInsert')+'出货单号'"
                size="small"
                style="width:95%"
                maxlength="30"
              />
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
          <div style="line-height:34px" class="subtit">出货单</div>
          <el-button
            size="small"
            type="primary"
            @click="toAddFaOutProductOrder(),changBg(1)"
          >{{ $t('button.add') }}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column prop="orderNo" label="出货单号" :show-overflow-tooltip="true">
            <template slot-scope="scoped">
              <a
                @click="openDetial(scoped.$index,tableData)"
                style="color:#00f;"
              >{{scoped.row.orderNo}}</a>
            </template>
          </el-table-column>
          <el-table-column label="销售单" prop="saleOrderNo" :show-overflow-tooltip="true" />
          <el-table-column label="车辆" prop="carNo" :show-overflow-tooltip="true" />
          <el-table-column prop="outDate" label="出货时间" :show-overflow-tooltip="true" />
          <el-table-column prop="address" label="出货地址" :show-overflow-tooltip="true" />
          <el-table-column prop="outOperator" label="出货人" :show-overflow-tooltip="true" />
          <el-table-column prop="receiver" label="收货人" :show-overflow-tooltip="true" />
          <el-table-column prop="receiverPhone" label="收货人号码" :show-overflow-tooltip="true" />
          <el-table-column prop="isCompile" label="状态" :show-overflow-tooltip="true" />
          <el-table-column prop="opration" label="操作" align="center" width="120">
            <template slot-scope="scope">
              <el-tooltip
                content="出货"
                placement="top"
                v-if="tableData[scope.$index].isCompile == '出货中'"
              >
                <svg-icon icon-class="create" @click.native="outProduct(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip
                content="送货完成"
                placement="top"
                v-if="tableData[scope.$index].isCompile == '在途中'"
              >
                <svg-icon
                  icon-class="complete"
                  @click.native="outProductFinished(scope.$index,tableData)"
                />
              </el-tooltip>
              <!--              <el-tooltip content="删除" placement="top">-->
              <!--                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />-->
              <!--              </el-tooltip>-->
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{ pageStart }} 至 {{ pageEnd }} 项结果，共 {{ searchParam.pageParam.totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          background
          small
          @current-change="handleCurrentChange"
          :page-count="searchParam.pageParam.totalPages"
          :current-page.sync="searchParam.pageParam.pageIndex"
        />
      </div>
    </div>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

import {
  getFaOutProductOrderPage,
  deleteFaOutProductOrder,
  outProduct,
  outProductFinished,
} from "@/api/faOutProductOrder";
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      organizationName: "",
      organizationType: "",
      createTime: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 290,
      tableData: [],
      options: ["已完成", "未完成"],
      stateValue: "",
      sellerPhone: "",
      seller: "",
      orderNo: "",
      address: "",
      pageable: {},
      totalElements: "",
      pageNo: "0",
      pageSize: "10",
      totalPages: "",
      searchParam: {
        orderNo: undefined,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
    };
  },
  mounted() {
    this.page();
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageParam.pageNo *
          this.searchParam.pageParam.pageSize +
        1
      );
    },
    pageEnd() {
      return (
        (this.searchParam.pageParam.pageNo + 1) *
        this.searchParam.pageParam.pageSize
      );
    },
  },
  methods: {
    openDetial(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/printOutProductOrder", query: { id: id } });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaOutProductOrder() {
      this.$router.push("/addFaOutProductOrder");
    },
    changBg(index) {
      this.ischange = index;
    },
    page() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      getFaOutProductOrderPage(this.searchParam)
        .then((res) => {
          if (res.code === "0") {
            this.tableData = res.data.content;
            this.searchParam.pageParam.totalPages = res.data.totalPages;
            this.searchParam.pageParam.pageNo = res.data.pageable.pageNumber;
            this.searchParam.pageParam.pageIndex =
              res.data.pageable.pageNumber + 1;
            this.searchParam.pageParam.pageSize = res.data.pageable.pageSize;
            for (const item of this.tableData) {
              item.isCompile === 0
                ? (item.isCompile = "出货中")
                : item.isCompile === 1
                ? (item.isCompile = "在途中")
                : (item.isCompile = "已完成");
            }
          }
          loading.close();
        })
        .catch(() => {
          loading.close();
        });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteFaOutProductOrder(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              this.searchParam.pageParam.pageNo =
                rows.length === 0 && this.searchParam.pageParam.pageNo != 0
                  ? this.searchParam.pageParam.pageNo - 1
                  : this.searchParam.pageParam.pageNo;
              this.page();
            }
          });
        })
    },
    updateFaOutProductOrder(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editFaOutProductOrder", query: { id: id } });
    },
    search() {
      this.searchParam = {
        orderNo: this.orderNo,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.page();
    },
    handleCurrentChange(cpage) {
      this.searchParam.pageParam.pageNo = index - 1;
      this.page();
    },
    createDeliveryPlan() {
      this.$router.push("/addDeliveryPlan");
    },
    outProduct(index, rows) {
      outProduct(rows[index].id).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "出货成功",
            type: "success",
          });
          this.page();
        }
      });
    },
    outProductFinished(index, rows) {
      outProductFinished(rows[index].id).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "送货完成",
            type: "success",
          });
          this.page();
        }
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
