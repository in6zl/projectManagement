<template>
  <div class="box">
    <div class="main">
      <!--父表单-->
      <div style="overflow:hidden;background:white;width:100%;padding:16px">
        <div class="subtit">{{ $t('route.addOutboundOrderProduct') }}</div>
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px">
          <el-col :span="12">
            <el-form-item
              style="font-size:10px"
              :label="$t('inoutBound.code')"
              prop="code"
              size="small"
            >
              <el-input v-model="ruleForm.code" maxlength="30" style="width: 98%" />
            </el-form-item>
          </el-col>
          <!-- <el-col :span="12">
            <el-form-item style="font-size:10px" label="车牌号" size="small" prop="carId">
              <el-select
                v-model="ruleForm.carId"
                filterable
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
                @change="initOutProductOrder"
              >
                <el-option
                  v-for="item in carList"
                  :key="item.id"
                  :label="item.carNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col> -->
          <el-col :span="12">
            <el-form-item style="font-size:10px">
              <span slot="label">
                <span>出货单号码</span>
                <!-- <span><el-tooltip content="快速创建" placement="top">
                  <svg-icon icon-class="add" @click.native="addFaOutProductOrder()" />
                </el-tooltip></span>-->
              </span>
              <!-- <el-input
                v-model="ruleForm.outProductOrderNo"
                maxlength="30"
                style="width: 100%"
              />-->

              <el-select
                v-model="ruleForm.outProductOrder"
                filterable
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
                clearable
                size="small"
              >
                <el-option
                  v-for="item in selectedOutProductOrderList"
                  :key="item.id"
                  :label="item.orderNo"
                  :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.warehouseName')" prop="warehouse" size="small">
              <el-select
                v-model="ruleForm.warehouse"
                filterable
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
                @change="initProductList(ruleForm.warehouse.id)"
              >
                <el-option
                  v-for="item in warehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
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
                style="width: 100%"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd HH:mm:ss"
              />
            </el-form-item>
          </el-col>
        </el-form>
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
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="addDetail"
            >新增详情</el-button>
          </div>
          <div class="addChild">
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="submitDialogFrom"
            >库位推荐</el-button>
          </div>
        </div>
        <el-table
          :data="ruleForm.detailList"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column label="成品(库存数)" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <el-select
                v-model="scope.row.product"
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
                @change="initUnit(scope.$index,ruleForm.detailList)"
              >
                <el-option
                  v-for="item in productList"
                  :key="item.id"
                  :label="`${(item.name)} (${(item.storedNumInWarHouse?item.storedNumInWarHouse:0)})`"
                  :value="item"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            width="150"
            :show-overflow-tooltip="true"
          >
            <template slot-scope="scope">
              <el-input type="text" size="mini" v-model="scope.row.num" />
            </template>
          </el-table-column>
          <el-table-column prop="unitName" :label="$t('inoutBound.unitName')" width="150" :show-overflow-tooltip="true" >
            <template slot-scope="scope">
              {{scope.row.product.unitName}}
            </template>
          </el-table-column>
          <el-table-column width="450" label="库位(现有数量) / 出库数量 " :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <tr v-for="faStorehouse  in scope.row.storageBins" :key="faStorehouse.id">
                <td>
                  <el-select v-model="faStorehouse.id" style="width: 200px">
                    <el-option
                      v-for="item in scope.row.storageBinsSelect"
                      :key="item.id"
                      :label="`${(item.code)} (${(item.num?item.num:0)})`"
                      :value="item.id"
                    />
                  </el-select>
                </td>
                <td>
                  <el-input type="text" v-model="faStorehouse.inboundNum" />
                </td>
                <td>
                  <el-tooltip content="删除" placement="top">
                    <svg-icon
                      icon-class="delete"
                      @click.native="deleteStoreHouse(faStorehouse, scope.row.storageBins)"
                    />
                  </el-tooltip>
                </td>
              </tr>
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
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index,  scope.row)" />
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
        v-if="haveBtn('outboundOrderProduct:add') && ruleForm.outProductOrder"
      >确定</el-button>
      <el-button
        size="small"
        type="primary"
        @click="submitFormAndAdd('ruleForm')"
        v-if="haveBtn('outboundOrderProduct:add') && !ruleForm.outProductOrder"
      >新建并创建新出货单</el-button>
    </div>
    <!--弹框-->
    <el-dialog title="新增详情" :visible.sync="dialogFormVisible" width="38%">
      <el-form
        ref="ruleFormDetail"
        :model="ruleFormDetail"
        :rules="rulesDetail"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item :label="$t('inoutBound.productName')" prop="product" size="small">
          <el-select
            v-model="ruleFormDetail.product"
            filterable
            placeholder="请选择"
            style="width: 100%"
            value-key="id"
            @change="getProductStorehouse"
          >
            <el-option v-for="item in productList" :key="item.id" :label="item.name" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
          <el-input v-model="ruleFormDetail.num" maxlength="11" style="width: 100%" />
        </el-form-item>
        <el-form-item :label="$t('inoutBound.unitName')" prop="unit" size="small">
          <el-select
            v-model="ruleFormDetail.unit"
            filterable
            placeholder="请选择"
            style="width: 100%"
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
            style="width: 100%"
            value-key="id"
          >
            <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item" />
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button size="small" type="primary" @click="submitFormDetail('ruleFormDetail')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="选择车辆" :visible.sync="selectCar" width="38%">
      <el-form
        ref="carData"
        :model="carData"
        :rules="carRules"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="车辆" prop="carId" size="small">
          <el-select
            v-model="carData.carId"
            filterable
            placeholder="请选择"
            style="width: 100%"
            value-key="id"
          >
            <el-option v-for="item in carList" :key="item.id" :label="item.carNo" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button size="small" @click="closeCar">取消</el-button>
          <el-button size="small" type="primary" @click="submitFormCar('carData')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {
  addOutboundOrderProduct,
  outboundOrderProductSotreHouseSuggest,
} from "@/api/outboundOrderProduct";
import {
  getFaOutProductOrderList,
  getOutProductOrderNo,
  getWarehouseProduct,
  getProductStorehouse,
  getStorehouseNum,
} from "@/api/faOutProductOrder";
import { getFaWarehouseList, findByRole } from "@/api/faWareHouse";
import { list as unitList } from "@/api/unit";
import { listCar } from "@/api/car";
import { getApplicationDetails , getApplication } from "@/api/outProductApplication";
import lef from "@/components/common/l_icon";
import { findListByWaHouse } from "@/api/product";
import { getSaleNo } from "@/api/sellOrder";

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
      selectCar: false,
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 420,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        code: "",
        carId: undefined,
        outProductOrder: undefined,
        warehouse: undefined,
        outOperator: undefined,
        receiver: undefined,
        outTime: undefined,
        detailList: [],
        outProductApplicationId: undefined,
      },
      carList: [],
      selectedOutProductOrderList: [],
      outProductOrderList: [],
      warehouseList: [],
      applicationDetails: [],
      rules: {
        code: [{ required: true, message: "请输入", trigger: "blur" }],
        carId: [{ required: true, message: "请选择", trigger: "blur" }],
        outProductOrder: [
          { required: true, message: "请选择", trigger: "blur" },
        ],
        warehouse: [{ required: true, message: "请输入", trigger: "blur" }],
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
      carData: {
        carId: undefined,
        applicationId: undefined,
      },
      applicationId: undefined,
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
      carRules: {
        carId: [{ required: true, message: "请选择车辆", trigger: "blur" }],
      },
    };
  },
  mounted() {
    getSaleNo().then((res) => {
      this.ruleForm.code = res.data;
    });
    this.applicationId = this.$route.query.applicationId;
    this.carData.applicationId = this.applicationId;
    this.productParam.applicationId = this.applicationId;
    this.storehouseParam.applicationId = this.applicationId;
    getFaOutProductOrderList({ isCompile: 0 }).then((res) => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
      }
    });
    findByRole().then((res) => {
      if (res.code === "0") {
        this.warehouseList = res.data;
        this.ruleForm.warehouseId = this.warehouseList[0].id;
        this.initProductList(this.ruleForm.warehouseId);
      }
    });
    unitList({ code: "", name: "" }).then((res) => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
    listCar({}).then((res) => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });

    getApplication(this.applicationId).then((res) => {
      if (res.code === "0") {
        this.ruleForm.carId=res.data.carSchedule.car.id;
        this.initOutProductOrder();
        this.applicationDetails = res.data.detailsList;
        this.applicationDetails.forEach((item) => {
          let detail = {};
          detail.product = item.product;
          detail.num = item.num;
          detail.unit = item.unit;
          this.ruleForm.detailList.push(detail);
        });
      }
    });
  },
  methods: {
    deleteStoreHouse(storeHouse, row) {
      for (let i = 0; i < row.length; i++) {
        if (row[i].id == storeHouse.id) {
          row.splice(i, 1);
        }
      }
    },
    initUnit(index, detailList) {
      this.ruleForm.detailList[index].unitName = this.ruleForm.detailList[
        index
      ].product.unitName;
    },
    initOutProductOrder() {
      this.selectedOutProductOrderList = [];
      if (this.outProductOrderList && this.outProductOrderList.length > 0) {
        this.outProductOrderList.forEach((outProductOrder) => {
          if (outProductOrder.carId == this.ruleForm.carId) {
            this.selectedOutProductOrderList.push(outProductOrder);
          }
        });
      }
    },
    addDetail() {
      this.ruleForm.detailList.push({});
    },
    initProductList(warehouseId) {
      findListByWaHouse(warehouseId).then((res) => {
        this.productList = res.data;
      });
    },
    submitDialogFrom() {
      if (!this.ruleForm.warehouse || !this.ruleForm.warehouse.id) {
        this.$message({
          message: "请先选择内仓",
          type: "warning",
        });
        return;
      }
      if (!this.ruleForm.detailList || this.ruleForm.detailList.length == 0) {
        this.$message({
          type: "info",
          message: "请先添加详情",
        });
        return;
      }
      for (let i = 0; i < this.ruleForm.detailList.length; i++) {
        let detail = this.ruleForm.detailList[i];
        if (!detail.product || !detail.product.id) {
          this.$message({
            type: "info",
            message: "第" + (i + 1) + "行详情请先选择成品",
          });
          return;
        }
        if (!detail.num) {
          this.$message({
            type: "info",
            message: "第" + (i + 1) + "行详情请先填入数量",
          });
          return;
        } else {
          let patt1 = /^\d+\.{0,1}\d*$/;
          let result = patt1.test(detail.num);
          if (!result) {
            this.$message({
              type: "info",
              message: "第" + (i + 1) + "行详情数量必须是数字",
            });
            return;
          }
        }
      }
      outboundOrderProductSotreHouseSuggest(this.ruleForm).then((res) => {
        this.ruleForm = res.data;
        let hasSuggest = false;
        this.ruleForm.detailList.forEach((detail) => {
          if (detail.storageBins && detail.storageBins.length > 0) {
            hasSuggest = true;
          }
        });
        if (!hasSuggest) {
          this.$message({
            type: "info",
            message: "抱歉，系统未找到可以推荐的库位",
          });
        }
        // this.dialogVisibleSuggest=true;
      });
    },
    /**
     * 获取内仓成品
     **/
    // getWarehouseProduct(value) {
    //   this.tableData = [];
    //   this.productParam.warehouseId = value;
    //   this.storehouseParam.warehouseId = value;
    //   getWarehouseProduct(this.productParam).then(res => {
    //     if (res.code === "0") {
    //       this.productList = res.data;
    //     }
    //   });
    // },

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
      this.dialogFormVisible = true;
      this.isUpdate = true;
    },

    /**
     * 子表单内容添加到父页面
     */
    async submitFormDetail(formName) {
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

    submitFormCar(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getOutProductOrderNo(this.carData).then((res) => {
            if (res.code === "0") {
              this.ruleForm.outProductOrderId = res.data.id;
              this.ruleForm.outProductOrderNo = res.data.orderNo;
              this.closeCar();
            }
          });
        }
      });
    },
    submitFormAndAdd(formName) {
      this.ruleForm.outProductApplicationId = this.applicationId;
      this.submitForm(formName);
    },

    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (
            !this.ruleForm.detailList ||
            this.ruleForm.detailList.length == 0
          ) {
            this.$message({
              message: "请添加出库单详情",
              type: "warning",
            });
            return false;
          }
          if (!this.ruleForm.outProductOrder) {
            this.ruleForm.outProductOrder = null;
          }
          // let binflag=true;
          // this.ruleForm.detailList.forEach((item) => {
          //   if (!item.bin) {
          //     this.$message({
          //       message: "请为成品选择库位",
          //       type: "warning",
          //     });
          //     binflag= false;
          //   }
          // });
          // if(!binflag){
          //   return;
          // }
          //对于未被推荐的库位，删除
          let form = JSON.parse(JSON.stringify(this.ruleForm));
          for (let i = form.detailList.length - 1; i >= 0; i--) {
            if (
              !form.detailList[i].storageBins ||
              form.detailList[i].storageBins.length == 0
            ) {
              form.detailList.splice(i, 1);
            }
          }
          if (!form.detailList || form.detailList.length == 0) {
            this.$message({
              message: "没有需要保存的库位",
              type: "warning",
            });
            return false;
          }
          addOutboundOrderProduct(form).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
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
    deleteRow(index, detail) {
      this.ruleForm.detailList.splice(index, 1);
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
      this.$router.push("outboundOrderProduct");
    },
    openCar() {
      this.selectCar = true;
    },
    closeCar() {
      this.selectCar = false;
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
.main {
  background: none !important;
}
.table {
  background: white;
  padding: 16px 0;
}
.tableBtn {
  display: flex;
  justify-content: flex-start;
}
</style>
