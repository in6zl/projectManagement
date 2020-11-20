<template>
  <div class="content">
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增出货单</div>
        <!--父表单-->
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="110px">
          <top>
            <el-form-item label="销售单" prop="saleId" slot="first">
              <el-select
                v-model="ruleForm.saleId"
                filterable
                placeholder="请选择"
                value
                style="width:100%"
                size="small"
              >
                <el-option
                  v-for="item in saleList"
                  :key="item.id"
                  :label="item.orderNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-form-item label="出货单号" prop="orderNo" slot="second">
              <el-input
                v-model="ruleForm.orderNo"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:100%"
                maxlength="30"
              />
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
            <el-form-item label="车辆" prop="carId" slot="first">
              <el-select
                v-model="ruleForm.carId"
                filterable
                placeholder="请选择"
                style="width:100%"
                value
                size="small"
              >
                <el-option
                  v-for="item in carList"
                  :key="item.id"
                  :label="item.carNo"
                  :value="item.id"
                />
              </el-select>
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

            <el-form-item label="出货地址" prop="shipment" slot="three">
              <el-input
                v-model="ruleForm.address"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:100%"
                maxlength="30"
              />
            </el-form-item>
          </top>
          <top>
            <el-form-item label="收货人" prop="receiver" slot="first">
              <el-input
                v-model="ruleForm.receiver"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:100%"
                maxlength="30"
              />
            </el-form-item>

            <el-form-item label="收货人电话" prop="receiverPhone" slot="second">
              <el-input
                v-model="ruleForm.receiverPhone"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:100%"
                maxlength="30"
              />
            </el-form-item>

            <el-form-item label="描述" prop="shipment" slot="three">
              <el-input
                v-model="ruleForm.description"
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
      <lef :top="37"></lef>

      <div class="roleManageTable">
        <!--添加按钮-->
        <div class="userEdit">
          <span class="subtit" style="line-height:34px">新增详情</span>
          <el-button icon="el-icon-plus" size="small" @click="addDetail" type="primary">新增详情</el-button>
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
          <el-table-column prop="num" :label="$t('inoutBound.num')" :show-overflow-tooltip="true">
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
              <el-tooltip content="编辑" placement="top">
                <svg-icon icon-class="edit" @click.native="update(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </con>
    <!--提交按钮-->
    <submit class="subBtn">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { addFaOutProductOrder, getNumber } from "@/api/faOutProductOrder";
import { parseTime } from "@/utils/index";
import { getSellList, searchSell, saleList } from "@/api/sellOrder";
import { listCar } from "@/api/car";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { prolist } from "@/api/product";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    top,
    submit,
    con,
    lef,
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
        saleId: undefined,
        carId: undefined,
        orderNo: undefined,
        outDate: undefined,
        address: undefined,
        description: undefined,
        outOperator: undefined,
        receiver: undefined,
        receiverPhone: undefined,
      },
      saleList: [],
      carList: [],
      rules: {
        saleId: [
          { required: true, message: "请输入销售单号", trigger: "blur" },
        ],
        carId: [{ required: true, message: "请输入车辆号", trigger: "blur" }],
        orderNo: { required: true, message: "请输入出货单号", trigger: "blur" },
        outDate: { required: true, message: "请输入出货时间", trigger: "blur" },
        address: { required: true, message: "请输入出货地址", trigger: "blur" },
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        product: undefined,
        num: undefined,
      },
      productList: [],
      unitList: [],
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
    saleList({}).then((res) => {
      if (res.code === "0") {
        this.saleList = res.data;
      }
    });
    listCar({}).then((res) => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });
    getNumber({}).then((res) => {
      if (res.code === "0") {
        this.ruleForm.orderNo = res.data;
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
          const data = {
            saleId: this.ruleForm.saleId,
            carId: this.ruleForm.carId,
            outOperator: this.ruleForm.outOperator,
            receiver: this.ruleForm.receiver,
            receiverPhone: this.ruleForm.receiverPhone,
            orderNo: this.ruleForm.orderNo,
            outDate: parseTime(this.ruleForm.outDate),
            address: this.ruleForm.address,
            description: this.ruleForm.description,
            outProductOrderDetailsList: this.tableData,
          };
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
          addFaOutProductOrder(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("faOutProductOrder");
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
      this.$router.push("faOutProductOrder");
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.subBtn {
  margin-top: -18px;
}
.main-top {
  background: white;
  padding: 16px;
  & >>> .el-form-item {
    margin-bottom: 5px;
  }
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
}

.userEdit {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
}
.dialogBtn {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  margin-right: 9px;
}
.content >>> .el-dialog__body {
  padding: 5px 20px !important;
}
</style>
