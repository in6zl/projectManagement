<template>
  <div>
    <div class="box">
      <el-scrollbar style="height:100%">
        <div class="main-top">
          <div style="margin:0 0 16px 0" class="subtit">编辑出货申请单</div>
          <!--父表单-->
          <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px">
            <top>
              <el-form-item label="销售单" prop="saleNo" size="small" slot="first">
                <el-input v-model="ruleForm.saleNo" size="small" style="width:100%" maxlength="30" />
              </el-form-item>
              <el-form-item label="内仓" prop="faWarehouseIdList" size="small" slot="second">
                <el-select
                  v-model="ruleForm.faWarehouseIdList"
                  filterable
                  placeholder="请选择"
                  multiple
                  collapse-tags
                  value
                  style="width:100%"
                >
                  <el-option
                    v-for="item in warehouseList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
              <el-form-item label="出货时间" prop="outDate" slot="three">
                <el-date-picker
                  v-model="ruleForm.outDate"
                  type="datetime"
                  placeholder="选择日期时间"
                  size="small"
                  style="width:100%"
                  format="yyyy-MM-dd HH:mm:ss"
                />
              </el-form-item>
            </top>
            <top>
              <el-form-item label="出货地址" prop="address" slot="first">
                <el-input
                  v-model="ruleForm.address"
                  size="small"
                  style="width:100%"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="出货人" prop="outOperator" slot="second">
                <el-input
                  v-model="ruleForm.outOperator"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:100%"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="收货人" prop="receiver" slot="three">
                <el-input
                  v-model="ruleForm.receiver"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:100%"
                  maxlength="30"
                />
              </el-form-item>
            </top>
            <top>
              <el-form-item label="收货人手机" prop="receiverPhone" slot="first">
                <el-input
                  v-model="ruleForm.receiverPhone"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:100%"
                  maxlength="30"
                />
              </el-form-item>
            </top>
          </el-form>
        </div>

        <!--子列表-->
        <div class="table">
          <!--添加按钮-->
          <div class="addChild">
            <el-button
              icon="el-icon-plus"
              size="small"
              type="primary"
              style="float:right;margin-bottom:16px"
              @click="addDetail()"
            >新增详情</el-button>
          </div>
          <el-table
            :data="tableData"
            style="width: 100%"
            :height="tableHeight"
            :header-cell-style="tableHeaderColor"
          >
            <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
            <el-table-column
              prop="product.name"
              :label="$t('inoutBound.productName')"
              :show-overflow-tooltip="true"
              align="center"
            >
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.product.id"
                  :placeholder="$t('product.select')"
                  size="small"
                  style="width:50%"
                  @change="getUnit(scope.row.product.id,scope.$index)"
                >
                  <el-option
                    v-for="(item,i) in productList"
                    :key="i"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column
              prop="num"
              :label="$t('inoutBound.num')"
              :show-overflow-tooltip="true"
              align="center"
            >
              <template slot-scope="scope">
                <el-input
                  type="text"
                  size="small"
                  :placeholder="$t('main.placeInsert')"
                  v-model.number="scope.row.num"
                  @change="format(scope.row.num,scope.$index)"
                  style="width:50%"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column
              prop="operation"
              :label="$t('table.operation')"
              fixed="right"
              align="right"
            >
              <template slot-scope="scope">
                <el-tooltip content="删除" placement="top">
                  <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-scrollbar>
    </div>
    <!--提交按钮-->
    <submit class="subBtn">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button
        size="small"
        type="primary"
        @click="submitForm('ruleForm')"
        v-if="haveBtn('application:edit')"
      >确定</el-button>
    </submit>
  </div>
</template>

<script>
import { updateApplication, getApplication } from "@/api/outProductApplication";
import { parseTime } from "@/utils/index";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { prolist } from "@/api/product";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
export default {
  components: {
    top,
    submit,
  },
  data() {
    const numValidator = (rule, value, callback) => {
      if (!/^[1-9]+\.*[0-9]*$/.test(value)) {
        return callback(new Error("数量必须是正数"));
      } else {
        return callback();
      }
    };
    return {
      // --------布局------------
      isUpdate: false,
      tableDataIndex: "",
      dialogFormVisible: false,
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 420,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        saleId: "",
        saleNo: "",
        deliveryPlanId: undefined,
        isCompilePlan: undefined,
        faWarehouseIdList: undefined,
        outDate: undefined,
        address: undefined,
        outOperator: undefined,
        receiver: undefined,
        receiverPhone: undefined,
        deliveryPlanDetailsList: [],
      },
      warehouseList: [],
      saleList: [],
      rules: {
        outDate: { required: true, message: "请输入出货时间", trigger: "blur" },
        faWarehouseIdList: {
          required: true,
          message: "请选择内仓",
          trigger: "blur",
        },
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        product: undefined,
        num: undefined,
      },
      productList: [],
      binList: [],
      rulesDetail: {
        product: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [
          { required: true, message: "请输入", trigger: "blur" },
          { validator: numValidator, trigger: ["blur", "change"] },
        ],
      },
    };
  },
  mounted() {
    let id = this.$route.query.id;
    getApplication(id).then((res) => {
      this.ruleForm = res.data;
      this.tableData = res.data.detailsList;
    });
    getFaWarehouseList({ type: 1 }).then((res) => {
      if (res.code === "0") {
        this.warehouseList = res.data;
      }
    });
    prolist({}).then((res) => {
      if (res.code === "0") {
        this.productList = res.data;
      }
    });
  },
  methods: {
    addDetail() {
      if (this.tableData.length == 0) {
        this.tableData.push({
          product: {
            id: "",
            name: "",
          },
          num: "",
        });
      } else {
        let size = this.tableData.length - 1;
        if (this.tableData[size].product.id === "") {
          this.$message({
            message: "第" + parseInt(size + 1) + "行成品为空",
            type: "info",
          });
          return;
        }
        if (this.tableData[size].num === "") {
          this.$message({
            message: "第" + parseInt(size + 1) + "行数量为空",
            type: "info",
          });
          return;
        }
        this.tableData.push({
          product: {
            id: "",
            name: "",
          },
          num: "",
        });
      }
    },
    format(num, index) {
      if (!/^[1-9][0-9]*$/.test(num)) {
        this.$message({
          message: "请输入数字",
          type: "info",
        });
        this.tableData[index].num = "";
      }
    },
    getUnit(productId, index) {
      let flag = false;
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].product.id === productId && i != index) {
          flag = true;
        }
      }
      if (flag) {
        this.$message({
          message: "已添加此成品",
          type: "info",
        });
        this.tableData[index].product.id = "";
        this.tableData[index].product.name = "";
        this.tableData[index].num = "";
      }
    },
    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.details = this.tableData;
          this.ruleForm.isCompile = 0;
          this.ruleForm.outDate = parseTime(this.ruleForm.outDate);
          if (this.tableData.length == 0) {
            this.$message({
              message: "请先新增详情",
              type: "error",
            });
            return;
          }
          let size = this.tableData.length - 1;
          if (this.tableData[size].product.id === "") {
            this.$message({
              message: "第" + parseInt(size + 1) + "行成品为空",
              type: "info",
            });
            return;
          }
          if (this.tableData[size].num === "") {
            this.$message({
              message: "第" + parseInt(size + 1) + "行数量为空",
              type: "info",
            });
            return;
          }
          updateApplication(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.$router.push("outProductApplication");
            } else {
              this.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    changBg(index) {
      this.isChange = index;
    },
    deleteRow(index) {
      this.tableData.splice(index, 1);
    },
    toIndex() {
      this.$router.push("outProductApplication");
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.main-top {
  background: white;
  padding: 16px;
}
.table {
  background: white;
  margin-top: 16px;
  padding: 16px;
}
.box {
  height: calc(100vh - 140px);
  padding-bottom: 20px;
}
.subBtn {
  margin-top: -18px;
}
.box /deep/.el-scrollbar {
  position: inherit !important;
}
// .box /deep/.el-scrollbar__thumb {
//   display: none;
// }
</style>
