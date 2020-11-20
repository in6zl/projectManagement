<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.orderNo')"
                size="small"
                style="width:95%"
                clearable
                v-model="orderNo"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.seller')"
                size="small"
                style="width:95%"
                clearable
                v-model="seller"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.sellerPhone')"
                size="small"
                style="width:95%"
                clearable
                v-model="sellerPhone"
              ></el-input>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item slot="first">
              <el-select
                v-model="stateValue"
                :placeholder="$t('main.placeInsert')+$t('main.orderState')"
                style="width:95%"
                clearable
                size="small"
              >
                <el-option v-for="item in options" :key="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="three">
              <el-button
                type="primary"
                size="mini"
                @click="search"
                style="float:right;margin:0 16px 0 0;"
              >{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>

          <div style="clear:both"></div>
        </el-form>
      </div>
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t('main.sellOrder')}}</div>
          <el-button
            size="small"
            @click="toAddSale(),changBg(1)"
            type="primary"
          >{{$t('button.add')}}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
          ref="table"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            :label="$t('table.orderNo')"
            prop="orderNo"
            :show-overflow-tooltip="true"
          >
            <template slot-scope="scoped">
              <!--              <el-tooltip class="item" effect="dark" content="详情" placement="top-end">-->
              <a
                @click="openDetial(scoped.$index,tableData)"
                style="color:#00f;"
              >{{scoped.row.orderNo}}</a>
              <!--              </el-tooltip>-->
            </template>
          </el-table-column>
          <el-table-column
            prop="orderTime"
            :label="$t('table.orderTime')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="seller" :label="$t('table.seller')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="sellerPhone"
            :label="$t('table.sellerPhone')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="amount" :label="$t('table.amount')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="state" :label="$t('table.state')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="expectDate"
            :label="$t('table.expectDate')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="address"
            :label="$t('table.address')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            width="150"
            prop="opration"
            :label="$t('table.operation')"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip effect="dark" content="创建交付计划" placement="top">
                <svg-icon
                  icon-class="create"
                  @click.native="createDeliveryPlan(scope.$index,tableData)"
                  v-if="tableData[scope.$index].state==='未完成' && haveBtn('deliveryPlan:add')"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip effect="dark" content="编辑" placement="top">
                <svg-icon icon-class="edit" @click.native="updataSale(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip effect="dark" content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{ pageStart }} 至 {{ pageEnd }} 项结果，共 {{ searchParam.pageable.totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          background
          small
          @current-change="handleCurrentChange"
          :page-count="searchParam.pageable.totalPages"
          :current-page.sync="searchParam.pageable.pageIndex"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getSellList,
  searchSell,
  deleteSellOrder,
  isUpdate,
} from "@/api/sellOrder";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

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
      tableHeight: window.innerHeight - 380,
      tableData: [],
      options: ["已完成", "未完成"],
      stateValue: "",
      sellerPhone: "",
      seller: "",
      orderNo: "",
      pageable: {},
      totalElements: "",
      pageNum: "0",
      pageSize: "10",
      totalPages: "",
      searchParam: {
        orderNo: undefined,
        seller: undefined,
        sellerPhone: undefined,
        stateValue: undefined,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: 0,
          pageIndex: 0,
        },
      },
    };
  },
  mounted() {
    this.page();
  },
  watch: {
    "$store.getters.sidebar.opened": function (newVal, oldVal) {
      if (!newVal) {
        this.tableHeight = window.innerHeight - 340;
      } else {
        this.tableHeight = window.innerHeight - 390;
      }
    },
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageable.pageNo * this.searchParam.pageable.pageSize +
        1
      );
    },
    pageEnd() {
      return (
        (this.searchParam.pageable.pageNo + 1) *
        this.searchParam.pageable.pageSize
      );
    },
  },
  methods: {
    openDetial(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/detail", query: { id: id } });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddSale() {
      this.$router.push("/addSale");
    },
    changBg(index) {
      this.ischange = index;
    },
    sellList() {
      this.$refs.table.bodyWrapper.scrollTop = 0;
      getSellList({ pageable: { pageNo: 0, pageSize: 10 } }).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.pageable = res.data.pageable;
          this.totalElements = res.data.totalElements;
          this.totalPages = res.data.totalPages;
          for (let item of this.tableData) {
            item.expectDate = item.expectDate.slice(0, 11);
            item.state == 0 ? (item.state = "未完成") : (item.state = "已完成");
          }
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteSellOrder(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            this.searchParam.pageable.pageNo =
              rows.length === 0 && this.searchParam.pageable.pageNo != 0
                ? this.searchParam.pageable.pageNo - 1
                : this.searchParam.pageable.pageNo;
            this.page();
          } else {
            this.$message({
              message: res.message,
              type: "error",
            });
          }
        });
      });
    },
    updataSale(index, rows) {
      let id = rows[index].id;
      isUpdate(id).then((res) => {
        if (res.code === "0") {
          this.$router.push({ path: "/editSale", query: { id: id } });
        } else {
          this.$message({
            message: res.message,
            type: "error",
          });
        }
      });
    },
    page() {
      searchSell(this.searchParam).then((response) => {
        this.tableData = response.data.content;
        for (let item of this.tableData) {
          !item.state || item.state == 0
            ? (item.state = "未完成")
            : (item.state = "已完成");
        }
        this.searchParam.pageable.totalPages = response.data.totalPages;
        this.searchParam.pageable.pageNo = response.data.pageable.pageNumber;
        this.searchParam.pageable.pageIndex =
          response.data.pageable.pageNumber + 1;
        this.searchParam.pageable.pageSize = response.data.pageable.pageSize;
      });
    },
    search() {
      this.searchParam = {
        orderNo: this.orderNo,
        seller: this.seller,
        sellerPhone: this.sellerPhone,
        stateValue:
          this.stateValue == "" ? "" : this.stateValue == "未完成" ? 0 : 1,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.page();
      loading.close();
    },
    handleCurrentChange(cpage) {
      this.searchParam.pageable.pageNo = index - 1;
      this.page();
    },
    createDeliveryPlan(index, row) {
      this.$router.push({
        path: "/addDeliveryPlan",
        query: {
          value: row[index].id,
          text: row[index].orderNo,
        },
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.main-top {
  background: white;
}
</style>
