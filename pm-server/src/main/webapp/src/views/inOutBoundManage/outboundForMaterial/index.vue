<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form ref="form" :model="searchForm">
          <search-form>
            <el-form-item prop="isFinished" slot="first">
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
            <el-form-item prop="searchStartTime" slot="second">
              <el-date-picker
                v-model="searchForm.searchStartTime"
                size="small"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                placeholder="开始时间"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width:95%"
              />
            </el-form-item>

            <el-form-item prop="searchEndTime" slot="three">
              <el-date-picker
                v-model="searchForm.searchEndTime"
                size="small"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                placeholder="结束时间"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width:95%"
              />
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="mini"
            @click="search"
          >{{ $t('button.search') }}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{ $t('route.outboundOrderMaterial') }}</div>
          <el-button
            size="small"
            type="primary"
            @click="toAdd(),changBg(1)"
          >{{ $t('button.add') }}</el-button>
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
            prop="outOperator"
            :label="$t('inoutBound.outOperator')"
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
              <el-tooltip content="出库详情" placement="top">
                <svg-icon icon-class="look" @click.native="detail(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="编辑" placement="top">
                <svg-icon icon-class="edit" @click.native="update(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
              <!--<svg-icon icon-class="registration" @click.native="toPrintOutboundOrderMaterial(scope.$index, tableData)" />-->
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
import {
  pageOutboundOrderMaterial,
  deleteOutboundOrderMaterial,
} from "@/api/outboundOrderMaterial";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  computed: {
    pageStart() {
      return (this.searchParam.pageParam.pageNo ) * this.searchParam.pageParam.pageSize + 1;
    },
    pageEnd() {
      return (this.searchParam.pageParam.pageNo + 1 ) * this.searchParam.pageParam.pageSize;
    },
  },
  data() {
    return {
      // 查询参数
      searchForm: {
        materialRequirePlanId: undefined,
        isFinished: undefined,
        pageNo: 0,
        pageSize: 10,
      },
      searchParam:{
        materialRequirePlan: undefined,
        isFinished: undefined,
        searchStartTime: undefined,
        searchEndTime: undefined,
        pageParam: { pageNo: 0, pageSize:10 ,totalPages:undefined,pageIndex:undefined}
      },
      materialRequirePlanList: [{ id: "1", businessId: "1" }],
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
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAdd() {
      this.$router.push("addOutboundOrderMaterial");
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      pageOutboundOrderMaterial(this.searchParam).then(res => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.searchParam.pageParam.totalPages=res.data.totalPages;
          this.searchParam.pageParam.pageNo=res.data.pageable.pageNumber;
          this.searchParam.pageParam.pageIndex=res.data.pageable.pageNumber+1;
          this.searchParam.pageParam.pageSize=res.data.pageable.pageSize;
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
        deleteOutboundOrderMaterial(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            this.searchParam.pageParam.pageNo =rows.length === 0 && this.searchParam.pageParam.pageNo!=0?  this.searchParam.pageParam.pageNo -1: this.searchParam.pageParam.pageNo;
            this.page();
          } else {
            this.$message.error(res.message);
          }
        });
      });
    },
    toPrintOutboundOrderMaterial(index, rows) {
      const id = rows[index].id;
      this.$router.push({
        path: "printOutboundOrderMaterial",
        query: { id: id },
      });
    },
    update(index, rows) {
      const id = rows[index].id;
      this.$router.push({
        path: "editOutboundOrderMaterial",
        query: { id: id },
      });
    },
    detail(index, rows) {
      const id = rows[index].id;
      const materialRequirePlanName = rows[index].materialRequirePlanName;
      this.$router.push({
        path: "outboundOrderMaterialDetail",
        query: { id: id, materialRequirePlanName: materialRequirePlanName },
      });
    },
    search() {
      this.searchParam = {
        materialRequirePlan: {
          id: this.searchForm.materialRequirePlanId,
        },
        isFinished: this.searchForm.isFinished,
        searchStartTime: this.searchForm.searchStartTime,
        searchEndTime: this.searchForm.searchEndTime,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages:undefined,
          pageIndex:undefined
        }
      };
      // const loading = this.$loading({
      //   lock: true,
      //   text: "Loading",
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)"
      // });
     this.page();
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
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
