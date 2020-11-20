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
                v-model="orderNo"
              ></el-input>
            </el-form-item>
            <!--          <el-form-item label="交付状态" style="float:left;">-->
            <!--            <el-select-->
            <!--              v-model="isCompilePlan"-->
            <!--              :placeholder="$t('main.placeInsert')"-->
            <!--              style="width:95%"-->
            <!--              size="small"-->
            <!--            >-->
            <!--              <el-option-->
            <!--                v-for="item in options"-->
            <!--                :key="item.value"-->
            <!--                :value="item.value"-->
            <!--                :label="item.text"-->
            <!--              ></el-option>-->
            <!--            </el-select>-->
            <!--          </el-form-item>-->
            <!--          <el-form-item style="float:right">-->
            <!--            <el-button type="primary" size="mini" @click="search">{{$t('button.search')}}</el-button>-->
            <!--          </el-form-item>-->
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
          <div style="line-height:34px" class="subtit">交付计划</div>
          <!-- <el-button
          size="small"
          @click="toAddDeliveryPlan(),changBg(1)"
          :class="{changBg:ischange == 1}"
          >{{$t('button.add')}}</el-button>-->

          <!--        <el-button-->
          <!--          size="small"-->
          <!--          @click="changBg(2)"-->
          <!--          :class="{changBg:ischange == 2}"-->
          <!--        >{{$t('button.allOperation')}}</el-button>-->
          <!--        <el-button size="small" @click="changBg(3)" :class="{changBg:ischange == 3}">-->
          <!--          {{$t('button.moreOperation')}}-->
          <!--          <i class="el-icon-arrow-down" style="margin-left:5px;" />-->
          <!--        </el-button>-->
        </div>
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column prop="name" label="计划名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="销售单号" prop="saleNo" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="deliveryDate" label="交付日期" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="isCompilePlan" label="状态" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="opration"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip
                effect="dark"
                content="创建出货申请单"
                placement="top"
                v-if="tableData[scope.$index].isCompilePlan=='未处理' && haveBtn('application:add')"
              >
                <svg-icon icon-class="create" @click.native="createApp(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip effect="dark" content="查看详情" placement="top">
                <svg-icon icon-class="look" @click.native="lookRow(scope.$index, tableData)"></svg-icon>
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
import { deliveryList, searchSell, deleteDeliver } from "@/api/deliveryPlan";
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
      tableHeight: window.innerHeight - 250,
      tableData: [],
      options: [
        { text: "已交付", value: 1 },
        { text: "未交付", value: 0 },
      ],
      isCompilePlan: "",
      seller: "",
      orderNo: "",
      pageable: {},
      totalElements: "",
      pageNum: "0",
      pageSize: "10",
      totalPages: "",
      searchParam:{
        orderNo: undefined,
        isCompilePlan:undefined,
        pageable: { pageNo: 0, pageSize:10 ,totalPages:undefined,pageIndex:undefined}
      },
    };
  },
  computed: {
    pageStart() {
      return (this.searchParam.pageable.pageNo ) * this.searchParam.pageable.pageSize + 1;
    },
    pageEnd() {
       return (this.searchParam.pageable.pageNo + 1 ) * this.searchParam.pageable.pageSize;
    },
  },
  mounted() {
    this.page();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddDeliveryPlan() {
      this.$router.push("/addDeliveryPlan");
    },
    changBg(index) {
      this.ischange = index;
    },
    page() {
      deliveryList(this.searchParam).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.searchParam.pageable.totalPages=res.data.totalPages;
          this.searchParam.pageable.pageNo=res.data.pageable.pageNumber;
          this.searchParam.pageable.pageIndex=res.data.pageable.pageNumber+1;
          this.searchParam.pageable.pageSize=res.data.pageable.pageSize;
          for (let item of this.tableData) {
            item.isCompilePlan == 0
              ? (item.isCompilePlan = "未处理")
              : (item.isCompilePlan = "已处理");
          }
        }
      });
    },
    lookRow(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/detailPlan", query: { id: id } });
    },
    createApp(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/addApplication", query: { id: id } });
    },
    deleteRow(index, rows) {
      console.log(rows[index].id);
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteDeliver(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              this.searchParam.pageable.pageNo =rows.length === 0 && this.searchParam.pageable.pageNo!=0?  this.searchParam.pageable.pageNo -1: this.searchParam.pageable.pageNo;
              this.page();
            }
          });
        })
    },
    updataSale(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editDeliveryPlan", query: { id: id } });
    },
    search() {
      this.searchParam={
        orderNo: this.orderNo,
        isCompilePlan:this.isCompilePlan == "" ? "" : this.stateValue == "未完成" ? 0 : 1,
        pageable: { pageNo: 0, pageSize:10 ,totalPages:undefined,pageIndex:undefined}
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
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
