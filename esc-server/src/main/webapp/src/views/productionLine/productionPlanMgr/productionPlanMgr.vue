<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <!-- 用户搜索栏 -->
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input placeholder="请输入编码" v-model="code" size="small" style="width:95%"></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-select
                v-model="status"
                size="small"
                clearable
                placeholder="请选择生产状态"
                style="width:95%"
              >
                <el-option
                  v-for="item in states"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
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
          <div style="line-height:34px" class="subtit">产线计划</div>
          <el-button
            size="small"
            v-if="haveBtn('productionPlan:add')"
            @click="toAddProductionLine(),changBg(1)"
            type="primary"
          >新增</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="code" label="编号" width="130" :show-overflow-tooltip="true">
            <template slot-scope="scoped">
              <a
                @click="viewMaterialPlanList(scoped.$index,tableData)"
                style="color:#00f;"
              >{{scoped.row.code}}</a>
            </template>
          </el-table-column>
          <el-table-column prop="product.name" label="成品" width="130" :show-overflow-tooltip="true" ></el-table-column>
          <el-table-column prop="num" label="生产数量" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="productionLine.name" label="产线" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="startDate" label="开始日期" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="endDate" label="计划结束日期" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="status"
            label="生产状态"
            :formatter="statusFormatter"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="operation" width="150" label="操作" align="center">
            <template slot-scope="scope">
              <el-tooltip v-if="scope.row.status!==1" content="生产" placement="top">
                <svg-icon
                  v-if="haveBtn('productionPlan:edit')"
                  icon-class="material"
                  @click="openDialog(scope.$index,tableData)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip v-if="scope.row.status!==1" content="结束生产" placement="top">
                <svg-icon
                  v-if="haveBtn('productionPlan:edit')"
                  icon-class="check"
                  @click="finishProduct(scope.row)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="详情" placement="top">
                <svg-icon
                  v-if="haveBtn('productionPlan:edit')"
                  icon-class="look"
                  @click="detailItem(scope.row)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip v-if="scope.row.status!==1" content="编辑" placement="top">
                <svg-icon
                  v-if="haveBtn('productionPlan:edit')"
                  icon-class="edit"
                  @click="editItem(scope.row)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip v-if="scope.row.status!==1" content="删除" placement="top">
                <svg-icon
                  v-if="haveBtn('productionPlan:delete')"
                  icon-class="delete"
                  @click="deleteItem(scope.$index,tableData)"
                ></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{pageStart}} 至 {{pageEnd}} 项结果，共 {{searchParam.pageable.totalPages}} 页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          @current-change="pageIndexChange"
          :page-count="searchParam.pageable.totalPages"
          :current-page.sync="searchParam.pageable.pageIndex"
          background
          small
        ></el-pagination>
      </div>
    </div>
    <!--弹框-->
    <el-dialog title="新增生产记录" :visible.sync="dialogFormVisible" width="38%">
      <el-form
        ref="ruleFormDetail"
        :model="ruleFormDetail"
        :rules="rulesDetail"
        label-width="150px"
        class="demo-ruleForm"
      >
        <el-form-item :label="$t('productionRecord.batch')" prop="batch" size="small">
          <el-input v-model="ruleFormDetail.batch" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item :label="$t('productionRecord.num')" prop="num" size="small">
          <el-input v-model="ruleFormDetail.num" style="width:80%"></el-input>
        </el-form-item>
        <el-form-item :label="$t('productionRecord.productName')" prop="productId" size="small">
          <el-select v-model="ruleFormDetail.productId" placeholder="请选择" style="width:80%" disabled>
            <el-option v-for="item in products" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="生产日期" prop="productionDate" size="small">
          <el-date-picker
            v-model="ruleFormDetail.productionDate"
            type="date"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd HH:mm:ss"
            style="width:80%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button size="small" type="primary" @click="submitFormDetail('ruleFormDetail')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import {
  findAllProductionPlanData,
  finishProduct,deleteProductionPlanItemById
} from "@/api/productionPlan";
import {
  page,deleteItem
} from "@/api/productionPlanItem";
import { createPr } from "@/api/productionRecord";
import { parseTime } from "@/utils/index";
import { list } from "@/api/unit";
import { prolist } from "@/api/product";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      dialogFormVisible: false,
      status: "",
      code: "",
      productPlan: {
        planName: "",
        status: "",
      },
      states: [
        {
          id: 0,
          name: "待生产",
        },
        {
          id: 1,
          name: "已生产",
        },
      ],
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      pageTotal: 0,
      pageSize: 5,
      pageIndex: 1,
      totalElements: 0,
      ruleFormDetail: {},
      products: [],
      units: [],
      rulesDetail: {
        code:[{ required: true, message: "请输入编号", trigger: "blur" }],
        batch: [{ required: true, message: "请输入批次号", trigger: "blur" }],
        num: [{ required: true, message: "请输入生产数量", trigger: "blur" }],
        productId: [{ required: true, message: "请选择成品", trigger: "blur" }],
        unitId: [{ required: true, message: "请选择单位", trigger: "blur" }],
        productionDate: [
          { required: true, message: "请选择生产日期", trigger: "blur" },
        ],
      },
      searchParam: {
        planName: undefined,
        status: undefined,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
    };
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageable.pageNo *
          this.searchParam.pageable.pageSize +
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
  mounted() {
    this.page();
    this.getProduct();
  },
  methods: {
    getProduct() {
      prolist({}).then((res) => {
        this.products = res.data;
      });
    },
    search() {
      this.searchParam = {
        status: this.status,
        code: this.code,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.page();
    },
    page() {
      page(this.searchParam).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.searchParam.pageable.totalPages = res.data.totalPages;
          this.searchParam.pageable.pageNo = res.data.pageable.pageNumber;
          this.searchParam.pageable.pageIndex =
            res.data.pageable.pageNumber + 1;
          this.searchParam.pageable.pageSize = res.data.pageable.pageSize;
        }
      });
    },
    deleteItem(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteProductionPlanItemById(rows[index].id).then((res) => {
          this.pageIndex = 1;
          rows.splice(index, 1);
          this.searchParam.pageable.pageNo =
            rows.length === 0 && this.searchParam.pageable.pageNo != 0
              ? this.searchParam.pageable.pageNo - 1
              : this.searchParam.pageable.pageNo;
          this.page();
        });
      });
    },
    finishProduct(item) {
      this.$confirm("是否确认完成生成?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        finishProduct(item.id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "操作成功",
              type: "success",
            });
            this.page();
          } else {
            this.$message.error(res.message);
          }
        });
      });
    },
    //编辑数据
    editItem(item) {
      this.$router.push({
        path: "/editProductionPlan",
        query: { id: item.id },
      });
    },
    detailItem(item) {
      this.$router.push({
        path: "/detailProductionPlan",
        query: { id: item.id },
      });
    },
    //点击页码
    pageIndexChange(index) {
      this.searchParam.pageable.pageNo = index - 1;
      this.page();
    },
    //初始化DataGrid数据
    // initFindAllProductionPlanData() {
    //   page({
    //     pageIndex: this.pageIndex - 1,
    //     pageSize: this.pageSize,
    //   }).then((response) => {
    //     this.tableData = response.data.content;
    //     this.pageTotal = response.data.totalPages;
    //     this.totalElements = response.data.totalElements;
    //   });
    // },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddProductionLine() {
      this.$router.push("/addProductionPlan");
    },
    changBg(index) {
      this.ischange = index;
    },
    statusFormatter(row) {
      if (row.status === 1) {
        return "已生产";
      } else {
        return "待生产";
      }
    },
    /**
     * 添加弹框
     */
    openDialog(index,rows) {
      this.ruleFormDetail = {};
      this.dialogFormVisible = true;
      this.ruleFormDetail.productId=rows[index].product.id;
      this.ruleFormDetail.productionPlanItem={
        id:rows[index].id
      }
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    submitFormDetail(formName) {
      let pr = {};
      pr = {
        product: {
          id: this.ruleFormDetail.productId,
        },
        num: this.ruleFormDetail.num,
        batch: this.ruleFormDetail.batch,
        productionDate: this.ruleFormDetail.productionDate,
        productionPlan:this.ruleFormDetail.productionPlanItem
      };
      this.$refs[formName].validate((valid) => {
        if (valid) {
          createPr(pr).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "生产成功",
                type: "success",
              });
              this.page();
            }else{
              this.$message({
                message: res.message,
                type: "error",
              });
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
        this.dialogFormVisible = false;
      });
    },
    viewMaterialPlanList(index, rows) {
      this.$router.push({
        path: "/materialPlanDetail",
        query: { id: rows[index].id, type: 1 },
      });
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
