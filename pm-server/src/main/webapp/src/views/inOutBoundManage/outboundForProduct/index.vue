<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form ref="form" :model="searchForm">
          <search-form>
            <el-form-item prop="outProductOrderId" slot="first">
              <el-select
                v-model="searchForm.outProductOrderId"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('inoutBound.outProductOrderNo')"
                value
                clearable
              >
                <el-option
                  v-for="item in outProductOrderList"
                  :key="item.id"
                  :label="item.orderNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item prop="warehouseId" slot="second">
              <el-select
                v-model="searchForm.warehouseId"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('inoutBound.warehouseName')"
                value
                clearable
              >
                <el-option
                  v-for="item in warehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item prop="isFinished" slot="three">
              <el-select
                v-model="searchForm.isFinished"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('inoutBound.isFinished')"
                value
                clearable
              >
                <el-option
                  v-for="item in isFinishedList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item prop="searchStartTime" slot="first">
              <el-date-picker
                v-model="searchForm.searchStartTime"
                size="small"
                type="date"
                :placeholder="$t('inoutBound.startTime')"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width:95%"
              />
            </el-form-item>
            <el-form-item prop="searchEndTime" slot="second">
              <el-date-picker
                v-model="searchForm.searchEndTime"
                size="small"
                type="date"
                :placeholder="$t('inoutBound.endTime')"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width:95%"
              />
            </el-form-item>
            <el-form-item slot="three">
              <el-button
                style="float:right;margin:8px 16px 0 0;"
                type="primary"
                size="mini"
                @click="search"
              >{{ $t('button.search') }}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{ $t('route.outboundOrderProduct') }}</div>
          <!--          <el-button-->
          <!--            icon="el-icon-plus"-->
          <!--            size="small"-->
          <!--            :class="{changBg:isChange === 1}"-->
          <!--            @click="toAdd(),changBg(1)"-->
          <!--          >{{ $t('button.add') }}</el-button>-->
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            prop="code"
            :label="$t('inoutBound.code')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="outProductOrderNo"
            :label="$t('inoutBound.outProductOrderNo')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="warehouseName"
            :label="$t('inoutBound.warehouseName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="outOperator"
            :label="$t('inoutBound.outOperator')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="receiver"
            :label="$t('inoutBound.receiver')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="outTime"
            :label="$t('inoutBound.outTime')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="isFinished"
            :label="$t('inoutBound.isFinished')"
            :formatter="isFinishedFormatter"
            :show-overflow-tooltip="true"
          />
          <!------------------------------->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip
                content="出库"
                placement="top"
                v-if="tableData[scope.$index].isFinished === 0"
              >
                <svg-icon icon-class="create" @click.native="outbound(scope.$index, tableData)" />
              </el-tooltip>
              <el-tooltip
                content="打印预览"
                placement="top"
                v-if="tableData[scope.$index].isFinished === 1"
              >
                <svg-icon
                  icon-class="look"
                  @click.native="toPrintOutboundOrderProduct(scope.$index, tableData)"
                />
              </el-tooltip>
              <el-tooltip
                content="编辑"
                placement="top"
                v-if="tableData[scope.$index].isFinished === 0"
              >
                <svg-icon
                  icon-class="edit"
                  @click.native="update(scope.$index,tableData)"
                  v-if="haveBtn('outboundOrderProduct:edit')"
                />
              </el-tooltip>
              <el-tooltip
                content="删除"
                placement="top"
                v-if="tableData[scope.$index].isFinished === 0"
              >
                <svg-icon
                  icon-class="delete"
                  @click.native="deleteRow(scope.$index, tableData)"
                  v-if="haveBtn('outboundOrderProduct:delete')"
                />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog title="出库" :visible.sync="beforeOutbound" width="45%">
      <div class="table">
        <el-table
          :data="detailData"
          style="width: 100%"
          height="250px"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column
            prop="code"
            :label="$t('inoutBound.code')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="productName"
            :label="$t('inoutBound.productName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unit"
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="binName"
            :label="$t('inoutBound.binName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="binNum"
            :label="$t('inoutBound.binNum')"
            width="150"
            :show-overflow-tooltip="true"
          />
        </el-table>
      </div>
      <div class="outBoundBtn">
        <el-button size="small" @click="beforeOutbound = false">取消</el-button>
        <el-button size="small" type="primary" @click="outboundConfirm">确定</el-button>
      </div>
    </el-dialog>
    <div class="paging">
      <div>显示第 {{pageStart}} 至 {{pageEnd}} 项结果，共{{ searchParam.pageParam.totalPages }}页</div>
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
import {
  pageOutboundOrderProduct,
  deleteOutboundOrderProduct,
  outbound,
  getDetailAndStorehouseNum,
} from "@/api/outboundOrderProduct";
import { getFaOutProductOrderList } from "@/api/faOutProductOrder";
import { getFaWarehouseList } from "@/api/faWareHouse";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
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
  data() {
    return {
      outBoundId: undefined,
      detailData: [],
      beforeOutbound: false,
      // 查询参数
      searchForm: {
        outProductOrderId: undefined,
        isFinished: undefined,
        pageNo: 0,
        pageSize: 10,
      },
      searchParam: {
        outProductOrder: undefined,
        isFinished: undefined,
        searchStartTime: undefined,
        searchEndTime: undefined,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
      outProductOrderList: [],
      warehouseList: [],
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" },
      ],
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 400,
      tableData: [],
      totalElements: 0,
      totalPages: 0,
    };
  },
  mounted() {
    this.page();
    getFaOutProductOrderList({}).then((res) => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
      }
    });
    getFaWarehouseList({}).then((res) => {
      if (res.code === "0") {
        this.warehouseList = res.data;
      }
    });
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAdd() {
      this.$router.push("addOutboundOrderProduct");
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      pageOutboundOrderProduct(this.searchParam).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.searchParam.pageParam.totalPages = res.data.totalPages;
          this.searchParam.pageParam.pageNo = res.data.pageable.pageNumber;
          this.searchParam.pageParam.pageIndex =
            res.data.pageable.pageNumber + 1;
          this.searchParam.pageParam.pageSize = res.data.pageable.pageSize;
        }
      });
    },
    handleCurrentChange(cpage) {
      this.searchParam.pageParam.pageNo = cpage - 1;
      this.page();
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteOutboundOrderProduct(rows[index].id).then((res) => {
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
          } else {
            this.$message.error(res.message);
          }
        });
      });
    },
    outbound(index, rows) {
      // 获取详情及库位数量信息
      getDetailAndStorehouseNum(rows[index].id).then((res) => {
        if (res.code === "0") {
          this.detailData = res.data;
        }
      });
      this.beforeOutbound = true;
      this.outBoundId = rows[index].id;
    },

    outboundConfirm() {
      console.log(this.outBoundId);
      outbound(this.outBoundId).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "出库成功",
            type: "success",
          });
          this.page();
          this.beforeOutbound = false;
        } else {
          this.$message.error(res.message);
        }
      });
    },

    toPrintOutboundOrderProduct(index, rows) {
      const id = rows[index].id;
      this.$router.push({
        path: "printOutboundOrderProduct",
        query: { id: id },
      });
    },
    update(index, rows) {
      const id = rows[index].id;
      this.$router.push({
        path: "editOutboundOrderProduct",
        query: { id: id },
      });
    },
    detail(index, rows) {
      const id = rows[index].id;
      const outProductOrderNo = rows[index].outProductOrderNo;
      this.$router.push({
        path: "outboundOrderProductDetail",
        query: { id: id, outProductOrderNo: outProductOrderNo },
      });
    },
    search() {
      this.searchParam = {
        outProductOrder: {
          id: this.searchForm.outProductOrderId,
        },
        isFinished: this.searchForm.isFinished,
        searchStartTime: this.searchForm.searchStartTime,
        searchEndTime: this.searchForm.searchEndTime,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.page();
      // const loading = this.$loading({
      //   lock: true,
      //   text: "Loading",
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)",
      // });
      // pageOutboundOrderProduct(data)
      //   .then((res) => {
      //     if (res.code === "0") {
      //       loading.close();
      //       this.tableData = res.data.content;
      //       this.totalElements = res.data.totalElements;
      //       this.totalPages = res.data.totalPages;
      //     } else {
      //       loading.close();
      //     }
      //   })
      //   .catch(() => {
      //     loading.close();
      //   });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    isFinishedFormatter(row, column) {
      if (row.isFinished === 0) {
        return "出库中";
      } else {
        return "出库完成";
      }
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.outBoundBtn {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: 16px;
}
</style>
