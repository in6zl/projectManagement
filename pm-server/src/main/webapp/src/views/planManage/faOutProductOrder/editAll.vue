<template>
  <div>
    <div class="box">
      <el-scrollbar style="height:100%">
        <div class="main-top">
          <div style="margin:0 0 16px 0">编辑出货单</div>
          <!--父表单-->
          <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="110px">
            <top>
              <el-form-item label="销售单" prop="saleId" size="small" slot="first">
                <el-select v-model="ruleForm.saleId" filterable placeholder="请选择" value>
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
                  style="width:200px"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="出货时间" prop="outDate" slot="three">
                <el-date-picker
                  v-model="ruleForm.outDate"
                  type="datetime"
                  placeholder="选择日期时间"
                  size="small"
                  style="width:200px"
                  format="yyyy-MM-dd HH:mm:ss"
                />
              </el-form-item>
            </top>
            <top>
              <el-form-item label="车辆" prop="carId" size="small" slot="first">
                <el-select v-model="ruleForm.carId" filterable placeholder="请选择" value>
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
                  style="width:200px"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="出货地址" prop="shipment" slot="three">
                <el-input
                  v-model="ruleForm.address"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:200px"
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
                  style="width:200px"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="收货人联系方式" prop="receiverPhone" slot="second">
                <el-input
                  v-model="ruleForm.receiverPhone"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:200px"
                  maxlength="30"
                />
              </el-form-item>
              <el-form-item label="描述" prop="shipment" slot="three">
                <el-input
                  v-model="ruleForm.description"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  style="width:200px"
                  maxlength="30"
                />
              </el-form-item>
            </top>
          </el-form>
        </div>

        <!--子列表-->
        <div class="roleManageTable">
          <!--添加按钮-->
          <div class="addChild">
            <el-button icon="el-icon-plus" size="small" type="primary" @click="openDialog">新增详情</el-button>
          </div>
          <el-table
            :data="tableData"
            style="width: 100%"
            :height="tableHeight"
            :header-cell-style="tableHeaderColor"
          >
            <el-table-column type="index" width="100" :label="$t('table.serial')" />
            <el-table-column
              prop="product.name"
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
              prop="unit.name"
              :label="$t('inoutBound.unitName')"
              width="150"
              :show-overflow-tooltip="true"
            />
            <!--<el-table-column-->
            <!--prop="bin.name"-->
            <!--:label="$t('inoutBound.binName')"-->
            <!--width="150"-->
            <!--:show-overflow-tooltip="true"-->
            <!--/>-->
            <!--<el-table-column-->
            <!--prop="unitNum"-->
            <!--:label="$t('inoutBound.unitNum')"-->
            <!--width="150"-->
            <!--:show-overflow-tooltip="true"-->
            <!--/>-->
            <!------------------------------->
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
      </el-scrollbar>
    </div>
    <!--弹框-->
    <el-dialog title="新增详情" :visible.sync="dialogFormVisible" width="38%">
      <el-form
        ref="ruleFormDetail"
        :model="ruleFormDetail"
        :rules="rulesDetail"
        label-width="150px"
        class="demo-ruleForm"
      >
        <el-form-item :label="$t('inoutBound.productName')" prop="product" size="small">
          <el-select
            v-model="ruleFormDetail.product"
            filterable
            placeholder="请选择"
            style="width: 200px"
            value-key="id"
          >
            <el-option v-for="item in productList" :key="item.id" :label="item.name" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
          <el-input v-model="ruleFormDetail.num" maxlength="11" style="width: 200px" />
        </el-form-item>
        <el-form-item :label="$t('inoutBound.unitName')" prop="unit" size="small">
          <el-select
            v-model="ruleFormDetail.unit"
            filterable
            placeholder="请选择"
            style="width: 200px"
            value-key="id"
          >
            <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item" />
          </el-select>
        </el-form-item>
        <!--<el-form-item :label="$t('inoutBound.binName')" prop="bin" size="small">-->
        <!--<el-select-->
        <!--v-model="ruleFormDetail.bin"-->
        <!--filterable-->
        <!--placeholder="请选择"-->
        <!--style="width: 200px"-->
        <!--value-key-->
        <!--&gt;-->
        <!--<el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item" />-->
        <!--</el-select>-->
        <!--</el-form-item>-->
        <!--<el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">-->
        <!--<el-input v-model="ruleFormDetail.unitNum" maxlength="11" style="width: 200px" />-->
        <!--</el-form-item>-->

        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button size="small" type="primary" @click="submitFormDetail('ruleFormDetail')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--提交按钮-->
    <submit class="subBtn">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')">确定</el-button>
    </submit>
  </div>
</template>

<script>
import {
  updateFaOutProductOrder,
  findFaOutProductOrder
} from "@/api/faOutProductOrder";
import { parseTime } from "@/utils/index";
import { getSellList } from "@/api/sellOrder";
import { listCar } from "@/api/car";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { prolist } from "@/api/product";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
export default {
  components: {
    top,
    submit
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
        receiverPhone: undefined
      },
      saleList: [],
      carList: [],
      rules: {
        saleId: [
          { required: true, message: "请输入销售单号", trigger: "blur" }
        ],
        carId: [{ required: true, message: "请输入车辆号", trigger: "blur" }],
        orderNo: { required: true, message: "请输入出货单号", trigger: "blur" },
        outDate: { required: true, message: "请输入出货时间", trigger: "blur" },
        address: { required: true, message: "请输入出货地址", trigger: "blur" }
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        product: undefined,
        num: undefined,
        // bin: undefined,
        unit: undefined
        // unitNum: undefined
      },
      productList: [],
      unitList: [],
      binList: [],
      rulesDetail: {
        product: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" },{ validator: numValidator, trigger: ["blur", "change"] }],
        unit: [{ required: true, message: "请输入", trigger: "blur" }]
        // bin: [{ required: true, message: '请输入', trigger: 'blur' }]
      }
    };
  },
  mounted() {
    const id = this.$route.query.id;
    /**
     * 子模块回显和表单数据的封装
     */
    findFaOutProductOrder(id).then(res => {
      this.ruleForm = res.data;
      res.data.outProductOrderDetailsList.forEach(item => {
        this.tableData.push({
          productId: item.product.id,
          // binId: item.binId,
          unitId: item.unit.id,
          product: {
            id: item.product.id,
            name: item.product.name
          },
          num: item.num,
          // bin: {
          //   id: item.binId,
          //   name: item.binName
          // },
          unit: {
            id: item.unit.id,
            name: item.unit.name
          }
          // unitNum: item.unitNum
        });
      });
    });
    getSellList("").then(res => {
      if (res.code === "0") {
        this.saleList = res.data.content;
      }
    });
    listCar({}).then(res => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });
    unitList({ code: "", name: "" }).then(res => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
    // getFaStorehouseList({}).then(res => {
    //   if (res.code === '0') {
    //     this.binList = res.data
    //   }
    // })
    prolist({}).then(res => {
      if (res.code === "0") {
        this.productList = res.data;
      }
    });
  },
  methods: {
    /**
     * 打开修改弹框
     */
    update(index) {
      this.tableDataIndex = index;
      this.ruleFormDetail = this.tableData[index];
      this.dialogFormVisible = true;
      this.isUpdate = true;
    },
    /**
     * 子表单内容添加到父页面
     */
    submitFormDetail(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const ruleFormDetail = this.ruleFormDetail;
          const { product, num, bin, unit, unitNum } = {
            product: ruleFormDetail.product,
            num: ruleFormDetail.num,
            // bin: ruleFormDetail.bin,
            unit: ruleFormDetail.unit
            // unitNum: ruleFormDetail.unitNum
          };
          if (!this.isUpdate) {
            this.tableData.push({
              product: product,
              num: num,
              // bin: bin,
              unit: unit
              // unitNum: unitNum
            });
          } else {
            if (this.tableData[this.tableDataIndex] === ruleFormDetail) {
              this.dialogFormVisible = false;
              return;
            } else {
              this.tableData[this.tableDataIndex] = {
                product: product,
                num: num,
                // bin: bin,
                unit: unit
                // unitNum: unitNum
              };
            }
          }
          this.$refs[formName].resetFields();
          this.dialogFormVisible = false;
        } else {
          return false;
        }
      });
    },
    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const data = {
            id: this.$route.query.id,
            saleId: this.ruleForm.saleId,
            carId: this.ruleForm.carId,
            outOperator: this.ruleForm.outOperator,
            receiver: this.ruleForm.receiver,
            receiverPhone: this.ruleForm.receiverPhone,
            orderNo: this.ruleForm.orderNo,
            outDate: parseTime(this.ruleForm.outDate),
            address: this.ruleForm.address,
            description: this.ruleForm.description,
            outProductOrderDetailsList: this.tableData
          };
          updateFaOutProductOrder(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
                type: "success"
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
    /**
     * 添加弹框
     */
    openDialog() {
      this.ruleFormDetail = {};
      this.isUpdate = false;
      this.dialogFormVisible = true;
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    toIndex() {
      this.$router.push("faOutProductOrder");
    }
  }
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.main-top {
  background: white;
  padding: 16px;
}
.roleManageTable {
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
  z-index: 1000000000000000;
}
.box /deep/.el-scrollbar{
  position:inherit!important;
}
// .box /deep/.el-scrollbar__thumb {
//   display: none;
// }
</style>
