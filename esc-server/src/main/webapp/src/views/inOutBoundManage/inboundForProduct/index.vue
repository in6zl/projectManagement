<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form ref="form" :model="searchForm">
          <search-form>
            <el-form-item prop="code" slot="first">
              <el-input
                v-model="searchForm.code"
                :placeholder="'请输入'+$t('inoutBound.code')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item prop="isFinished" slot="second">
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
            <el-form-item prop="type" slot="three">
              <el-select
                v-model="searchForm.type"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('inoutBound.type')"
                value
                clearable
              >
                <el-option
                  v-for="item in typeList"
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
          <!--<el-button
          icon="el-icon-plus"
          size="small"
          :class="{changBg:isChange === 1}"
          @click="toAdd(),changBg(1)"
        >{{ $t('button.add') }}
          </el-button>-->
          <div style="line-height:34px" class="subtit">{{ $t('route.inboundOrderProduct') }}</div>
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
            prop="type"
            :label="$t('inoutBound.type')"
            :formatter="typeFormatter"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="inOperator"
            :label="$t('inoutBound.inOperator')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="deliveryMan"
            :label="$t('inoutBound.deliveryMan')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="inTime"
            :label="$t('inoutBound.inTime')"
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
              <el-tooltip content="入库详情" placement="top">
                <svg-icon icon-class="look" @click.native="detail(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="编辑" placement="top" v-if="scope.row.isFinished == 0">
                <svg-icon icon-class="edit" @click.native="update(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="完成入库" placement="top" v-if="scope.row.isFinished == 0">
                <svg-icon icon-class="fawarehouse" @click.native="finish(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
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
  pageInboundOrderProduct,
  deleteInboundOrderProduct,
  finishInboundOrderProduct 
} from "@/api/inboundOrderProduct";
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
      // 查询参数
      searchForm: {
        isFinished: undefined,
        type: undefined,
        code: undefined,
        pageNo: 0,
        pageSize: 10,
      },
      searchParam: {
        isFinished: undefined,
        type: undefined,
        code: undefined,
        searchStartTime: undefined,
        searchEndTime: undefined,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" },
      ],
      typeList: [
        { id: 0, name: "成品入库" },
        { id: 1, name: "退货入库" },
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
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAdd() {
      this.$router.push("addInboundOrderProduct");
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      pageInboundOrderProduct(this.searchParam).then((res) => {
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
        deleteInboundOrderProduct(rows[index].id).then((res) => {
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
    update(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "editInboundOrderProduct", query: { id: id ,warehouseId:rows[index].warehouseId} });
    },
    detail(index, rows) {
      const id = rows[index].id;
      const code = rows[index].code;
      this.$router.push({
        path: "inboundOrderProductDetail",
        query: { id: id, code: code },
      });
    },
    search() {
      this.searchParam = {
        isFinished: this.searchForm.isFinished,
        type: this.searchForm.type,
        code: this.searchForm.code,
        searchStartTime: this.searchForm.searchStartTime,
        searchEndTime: this.searchForm.searchEndTime,
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      // const loading = this.$loading({
      //   lock: true,
      //   text: "Loading",
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)",
      // });
      this.page();
      // pageInboundOrderProduct(data)
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
        return "未完成";
      } else {
        return "已完成";
      }
    },
    typeFormatter(row, column) {
      if (row.type === 0) {
        return "成品入库";
      }
      if (row.type === 1) {
        return "退货入库";
      }
    },
     finish(index, rows) {
      this.$confirm(
        '<div style="text-align:center"><div class="backimg"></div><div style="text-align:center;margin:0 auto;font-weight:700;padding:20px 0px;font-size:18px">确定入库将无法删除、更新，确定操作吗?</div></div>',
        "",
        {
          center: true,
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          dangerouslyUseHTMLString: true,
          callback: (action) => {
            if (action == "confirm") {
              const id = rows[index].id;
              finishInboundOrderProduct(id).then((res) => {
                if (res.code === "0") {
                  this.$message({
                    message: "入库成功",
                    type: "success",
                  });
                  this.page();
                } else {
                  this.$message.error(res.message);
                }
              });
            }
          },
        }
      );
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
