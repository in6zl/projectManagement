<template>
  <div class="box">
    <div class="main">
      <div style="overflow:hidden;background:white;width:100%;padding:16px">
        <div class="subtit">{{ $t('route.editOutboundOrderProduct') }}</div>
        <!--父表单-->
        <div style="overflow:hidden">
          <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px">
            <el-col :span="12">
              <el-form-item
                style="font-size:10px"
                :label="$t('inoutBound.code')"
                prop="code"
                size="small"
              >
                <el-input v-model="ruleForm.code" maxlength="30" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                :label="$t('inoutBound.outProductOrderNo')"
                prop="outProductOrderId"
                size="small"
              >
                <el-input
                  v-model="ruleForm.outProductOrderNo"
                  style="width: 100%"
                  maxlength="30"
                  disabled="disabled"
                />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('inoutBound.warehouseName')" prop="warehouseId" size="small">
                <el-select
                  v-model="ruleForm.warehouseId"
                  filterable
                  placeholder="请选择"
                  style="width: 100%"
                  value
                  @change="getWarehouseProduct"
                  disabled
                >
                  <el-option
                    v-for="item in warehouseList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  />
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
                <el-input v-model="ruleForm.outOperator" maxlength="30" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('inoutBound.receiver')" prop="receiver" size="small">
                <el-input v-model="ruleForm.receiver" maxlength="30" style="width: 100%" />
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('inoutBound.outTime')" prop="outTime" size="small">
                <el-date-picker
                  v-model="ruleForm.outTime"
                  type="date"
                  style="width: 100%"
                  format="yyyy 年 MM 月 dd 日"
                  value-format="yyyy-MM-dd HH:mm:ss"
                />
              </el-form-item>
            </el-col>
          </el-form>
        </div>
        <!--添加按钮-->
        <!-- <div class="addChild">
          <el-button
            icon="el-icon-plus"
            size="small"
            :class="{changBg:isChange === 1}"
            @click="openDialog"
          >新增详情</el-button>
        </div> -->
      </div>
      <!--子列表-->
      <div class="table">
        <lef :top="7"></lef>
        <div style="margin-left:16px;float:left;line-height:34px;float:left" class="subtit">新增详情</div>
        <div
          class="tableBtn"
          style="float:right;margin-top:-22px;margin-right:14px;padding-bottom:16px"
        >
          <!--添加按钮-->
          <!-- <div class="addChild">
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="addFaOutProductOrder"
            >快速创建出库单</el-button>
          </div>-->
          <div class="addChild">
            <el-button icon="el-icon-plus" size="small" type="primary" @click="openDialog">新增详情</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            :label="$t('inoutBound.productName')"
            width="150"
            :show-overflow-tooltip="true"  >
            <template slot-scope="scope">
              <el-select v-model="scope.row.product"  value-key="id">
                  <el-option v-for="item in productList" :key="item.id" :label="`${(item.name)} (${(item.storedNumInWarHouse?item.storedNumInWarHouse:0)})`" :value="item" />
                </el-select>
              </template>
          </el-table-column>
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            width="150"
            :show-overflow-tooltip="true"
          ><template slot-scope="scope">
              <el-input type="text" size="mini" v-model="scope.row.num" />
            </template>
          </el-table-column>
          <el-table-column
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true" >
          <template slot-scope="scope">{{scope.row.product.unitName}}</template>
          </el-table-column>
          <el-table-column
            :label="$t('inoutBound.binName')"
            width="150"
            :show-overflow-tooltip="true"
          > <template slot-scope="scope">
              <el-select v-model="scope.row.bin" :value-key="scope.row.bin.id" >
                 <el-option v-for="item in binList" :key="item.id" :label="item.code" :value="item" />
              </el-select>
            </template>
          </el-table-column>
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
    </div>
    <!--提交按钮-->
    <div class="submit-button">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button
        size="small"
        type="primary"
        @click="submitForm('ruleForm')"
        v-if="haveBtn('outboundOrderProduct:edit')"
      >确定</el-button>
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
            @change="getProductStorehouse"
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
        <el-form-item :label="$t('inoutBound.binName')" prop="bin" size="small">
          <el-select
            v-model="ruleFormDetail.bin"
            filterable
            placeholder="请选择"
            style="width: 200px"
            value-key="id"
          >
            <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">
          <el-input v-model="ruleFormDetail.unitNum" maxlength="11" style="width: 200px" />
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
import {
  updateOutboundOrderProduct,
  getOutboundOrderProduct,
} from "@/api/outboundOrderProduct";
import {
  getFaOutProductOrderList,
  getWarehouseProduct,
  getProductStorehouse,
  getStorehouseNum,
} from "@/api/faOutProductOrder";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { list as unitList } from "@/api/unit";
import { findListByWaHouse } from "@/api/product";
import { getFaStorehouseList } from "@/api/faStorehouse";
import lef from "@/components/common/l_icon";

export default {
  components: {
    lef,
  },
  data() {
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
        code: "",
        outProductOrderId: undefined,
        warehouseId: undefined,
        outOperator: undefined,
        receiver: undefined,
        outTime: undefined,
      },
      outProductOrderList: [],
      warehouseList: [],
      rules: {
        code: [{ required: true, message: "请输入", trigger: "blur" }],
        outProductOrderId: [
          { required: true, message: "请输入", trigger: "blur" },
        ],
        warehouseId: [{ required: true, message: "请输入", trigger: "blur" }],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        receiver: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        product: undefined,
        num: undefined,
        bin: undefined,
        unit: undefined,
        unitNum: undefined,
      },
      productParam: {},
      storehouseParam: {},
      productList: [],
      unitList: [],
      binList: [],
      rulesDetail: {
        product: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unit: [{ required: true, message: "请输入", trigger: "blur" }],
        bin: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      productMap:{},
      binMap:{}
    };
  },
  async mounted() {
    const id = this.$route.query.id;
    /**
     * 子模块回显和表单数据的封装
     */
    await getOutboundOrderProduct(id).then((res) => {
      this.ruleForm = res.data;
      res.data.detailDtoList.forEach((item) => {
        this.productMap[item.productId]={"id":item.productId}
        this.binMap[item.binId]={"id":item.binId}
        this.tableData.push({
          productId: item.productId,
          binId: item.binId,
          product: {
            id: item.productId,
            name: item.productName,
            unitName:undefined
          },
          num: item.num,
          bin: {
            id: item.binId,
            name: item.binName,
          },
          unit: item.unit,
          unitNum: item.unitNum,
        });
        console.log("this.tableData",this.tableData);
      });
    });
    await findListByWaHouse(this.ruleForm.warehouseId).then((res) => {
        this.productList = res.data;
        let product=undefined;
        this.tableData.forEach(item=>{
           this.productList.forEach(productSingle=>{
             if(productSingle.id == item.product.id){
                 item.product=productSingle;
             }
           })
        })
    });
    await getFaStorehouseList({"warehouse":{"id":this.ruleForm.warehouseId}}).then((res) => {
      console.log("warehouse",this.ruleForm.warehouseId);
      if (res.code === "0") {
        this.binList = res.data;
      }
    });
    getFaOutProductOrderList({}).then((res) => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
      }
    });
    getFaWarehouseList({ type: 0 }).then((res) => {
      if (res.code === "0") {
        this.warehouseList = res.data;
      }
    });
    unitList({ code: "", name: "" }).then((res) => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
  },
  methods: {
    /**
     * 获取内仓成品
     **/
    getWarehouseProduct(value) {
      this.tableData = [];
      this.productParam.warehouseId = value;
      this.storehouseParam.warehouseId = value;
      getWarehouseProduct(this.productParam).then((res) => {
        if (res.code === "0") {
          this.productList = res.data;
        }
      });
    },

    getProductStorehouse(value) {
      this.storehouseParam.productId = value.id;
      console.log("成品id:" + this.storehouseParam.productId);
      console.log("内仓id:" + this.storehouseParam.warehouseId);
      getProductStorehouse(this.storehouseParam).then((res) => {
        if (res.code === "0") {
          this.binList = res.data;
        }
      });
    },

    /**
     * 打开修改弹框
     */
    update(index) {
      this.tableDataIndex = index;
      this.ruleFormDetail = this.tableData[index];
      getProductStorehouse({
        warehouseId: this.ruleForm.warehouseId,
        productId: this.ruleFormDetail.productId,
      }).then((res) => {
        if (res.code === "0") {
          this.binList = res.data;
        }
      });
      this.dialogFormVisible = true;
      this.isUpdate = true;
    },
    /**
     * 子表单内容添加到父页面
     */
    submitFormDetail(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const ruleFormDetail = this.ruleFormDetail;
          const { product, num, bin, unit, unitNum } = {
            product: ruleFormDetail.product,
            num: ruleFormDetail.num,
            bin: ruleFormDetail.bin,
            unit: ruleFormDetail.unit,
            unitNum: ruleFormDetail.unitNum,
          };
          getStorehouseNum({
            warehouseId: this.productParam.warehouseId,
            storehouseId: bin.id,
            productId: this.storehouseParam.productId,
          }).then((res) => {
            if (res.code === "0") {
              if (num > res.data) {
                this.$message({
                  message: "数量不能大于库存",
                  type: "warning",
                });
              } else {
                if (!this.isUpdate) {
                  this.tableData.push({
                    product: product,
                    num: num,
                    bin: bin,
                    unit: unit,
                    unitNum: unitNum,
                  });
                } else {
                  if (this.tableData[this.tableDataIndex] === ruleFormDetail) {
                    this.dialogFormVisible = false;
                    return;
                  } else {
                    this.tableData[this.tableDataIndex] = {
                      product: product,
                      num: num,
                      bin: bin,
                      unit: unit,
                      unitNum: unitNum,
                    };
                  }
                }
                this.$refs[formName].resetFields();
                this.dialogFormVisible = false;
              }
            }
          });
        } else {
          return false;
        }
      });
    },
    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const data = {
            id: this.$route.query.id,
            outProductOrder: {
              id: this.ruleForm.outProductOrderId,
            },
            faWarehouse: {
              id: this.ruleForm.warehouseId,
            },
            outOperator: this.ruleForm.outOperator,
            receiver: this.ruleForm.receiver,
            outTime: this.ruleForm.outTime,
            isFinished: this.ruleForm.isFinished,
            detailList: this.tableData,
          };
          updateOutboundOrderProduct(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
                type: "success",
              });
              this.$router.push("outboundOrderProduct");
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
      // this.ruleFormDetail = {};
      // this.isUpdate = false;
      // this.dialogFormVisible = true;
       this.tableData.push({
        product: this.productList[0],
        num: 0,
        bin: this.binList[0],
      })
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    toIndex() {
      this.$router.push("outboundOrderProduct");
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.box {
  position: relative;
  height: calc(100vh - 120px);
}
.submit-button {
  width: 100%;
  display: flex;
  position: absolute;
  bottom: 0px;
  padding: 15px 0 15px 0;
  justify-content: center;
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  background: white;
}
</style>
