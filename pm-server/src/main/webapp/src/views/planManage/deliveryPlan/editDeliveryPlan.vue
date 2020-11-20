<template>
  <div class="saleOrder">
    <div class="main">
      <div class="subtit">编辑交付计划</div>
      <el-form
        class="demo-ruleForm"
        label-width="150px"
        ref="saleForm"
        :rules="saleRules"
        :model="saleForm"
      >
        <el-row>
          <el-col span="11">
            <el-form-item label="请选择销售单" prop="saleOrder">
              <el-select
                v-model="saleForm.saleOrder"
                style="width:100%;"
                @change="getProductList"
                filterable
                placeholder="请选择"
                size="small"
              >
                <el-option
                  v-for="item in saleOrderOptions"
                  :label="item.text"
                  :key="item.value"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col span="11">
            <el-form-item label="名称" prop="name">
              <el-input
                placeholder="请输入名称"
                maxlength="64"
                v-model="name"
                :value="name"
                clearable
                size="small"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col span="11">
            <el-form-item label="选择交付日期" prop="deliveryDate">
              <el-date-picker
                v-model="saleForm.deliveryDate"
                :value="saleForm.deliveryDate"
                type="date"
                placeholder="选择交付日期"
                size="small"
                style="width:100%"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col span="11">
            <el-form-item label="描述">
              <el-input
                type="textarea"
                :rows="1"
                maxlength="128"
                placeholder="请输入内容"
                v-model="description"
                resize="none"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="addResult">
      <lef :top="23" style="padding:0!important;"></lef>
      <div style="padding:16px 16px 0 16px">
        <div style="line-height:34px;float:left" class="subtit">添加交付计划项</div>
        <el-button
          type="primary"
          size="small"
          style="margin:0 0 16px 16px;float:right"
          @click="alertProDialog"
        >添加交付计划项</el-button>
      </div>

      <!-- 添加生产计划象弹窗 -->
      <!-- <el-dialog title="添加交付计划项" :visible.sync="proDialogVisible">
        <el-form ref="productForm" :rules="productRules" :model="productForm" label-width="150px">
          <el-row>
            <el-form-item label="成品" prop="product.id">
              <el-col :span="21">
                <el-select
                  v-model="productForm.product.id"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  filterable
                  clearable
                  style="width:100%"
                >
                  <el-option
                    v-for="item in productOptions"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-col>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="数量" prop="num">
              <el-col :span="21">
                <el-input
                  type="text"
                  size="small"
                  :placeholder="$t('main.placeInsert')"
                  v-model.number="productForm.num"
                  style="width:100%"
                ></el-input>
              </el-col>
            </el-form-item>
          </el-row>
          <el-row>
            <el-col :span="24" class="proConfirmBtn">
              <el-button size="small" @click="proConfirmCancel('productForm')">取消</el-button>
              <el-button type="primary" size="small" @click="addPro('productForm')">确定</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>-->
    </div>
    <div class="roleManageTable">
      <el-table
        :data="tableData"
        style="width: 100%"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
        <el-table-column width="240" label="成品" prop="product.name" :show-overflow-tooltip="true">
          <template slot-scope="scope">
            <el-select
              :placeholder="$t('main.placeInsert')"
              size="small"
              filterable
              clearable
              style="width:100%"
              v-model="scope.row.product"
              value-key="id"
              @change="autoComplateUnit(scope.row.product.id,tableData,scope.$index)"
            >
              <el-option
                v-for="item in productOptions"
                :key="item.id"
                :label="item.name"
                :value="item"
              ></el-option>
            </el-select>
          </template>
        </el-table-column>

        <el-table-column prop="num" label="数量" width="135" :show-overflow-tooltip="true">
          <template slot-scope="scope">
            <el-input
              type="text"
              size="small"
              :placeholder="$t('main.placeInsert')"
              v-model.number="scope.row.num"
              @change="validateNum(scope.$index, tableData)"
              style="width:100%"
            ></el-input>
          </template>
        </el-table-column>
        <el-table-column label="操作" fixed="right" align="right">
          <template slot-scope="scope">
            <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="submitBtn">
      <el-button size="small" @click="resetForm('saleForm'),changBg(2)">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('saleForm'),changBg(1)">确定</el-button>
    </div>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { prolist, getProductInfo } from "@/api/product";
import { list } from "@/api/unit";
import { getDeliver, updatDeliver, findBySale } from "@/api/deliveryPlan";
import { find, selectSellParams } from "@/api/sellOrder";
import { parseTime } from "@/utils/index";
import lef from "@/components/common/l_icon";

export default {
  mixins: [btn],
  components: {
    lef,
  },
  data() {
    const validatePro = (rule, value, callback) => {
      if (this.productOptions.length == 0) {
        return callback(new Error("请先创建成品"));
      } else {
        for (let item of this.productOptions) {
          if (item.name == value) {
            return callback();
          }
        }
        callback(new Error("请选择正确的成品"));
      }
    };
    const validateSaleOrder = (rule, value, callback) => {
      if (this.saleOrderOptions.length == 0) {
        return callback(new Error("请先创建销售单"));
      } else {
        for (let item of this.saleOrderOptions) {
          if (item.text == value) {
            return callback();
          }
        }
        callback(new Error("请选择正确的销售单"));
      }
    };
    const validateNum = (rule, value, callback) => {
      let flag = false;
      let num = 0;
      console.log(this.deliveryDetails);
      this.deliveryDetails.forEach((detail) => {
        if (detail.product.id === this.productForm.product.id) {
          num += detail.num;
        }
      });
      let ss = 0;
      this.saleDetail.forEach((detail) => {
        if (detail.product.id === this.productForm.product.id) {
          if (detail.num - num < value) {
            flag = true;
            ss = detail.num - num;
          }
        }
      });
      if (flag) {
        return callback(
          new Error("交付数量应不可大于剩余物料数量(" + ss + ")")
        );
      } else {
        return callback();
      }
    };
    return {
      productOptions: [],
      unitOption: [],
      saleId: "",
      name: "",
      deliveryDate: "",
      description: "",
      proDialogVisible: false,
      saleOrderOptions: [],
      saleDetail: [],
      deliveryDetails: [],
      // productForm: {
      //   product: {
      //     id: "",
      //     name: "",
      //   },
      //   unit: {
      //     id: "",
      //     code: "",
      //   },
      //   num: "",
      //   expectTime: "",
      // },
      saleForm: {
        saleOrder: {
          text: "",
          value: "",
        },
        deliveryDate: "",
      },
      amount: 0,
      tableHeight: window.innerHeight - 470,
      tableData: [],
      saleRules: {
        saleOrder: [
          { required: true, message: "请选择销售单", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    proConfirmCancel(formName) {
      this.$refs[formName].resetFields();
      this.proDialogVisible = false;
    },
    async autoComplateUnit(id, tableData, index) {
      tableData.forEach((data) => {
        if (
          tableData[index].id != data.id &&
          data.product.id === tableData[index].productid
        ) {
          this.$message({
            message: "请不要选择重复相同的成品",
          });
          tableData[index].product = {};
        }
      });
    },
    alertProDialog() {
      if (this.productOptions.length === 0) {
        this.$message({
          message: "请先选择销售单",
        });
      } else {
        // this.proDialogVisible = true;
        this.tableData.push({
          product: {
            id: "",
            name: "",
          },
          num: "",
        });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/deliveryPlan");
    },
    submitForm(formName) {
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let flag = false;
          let name=""
          this.tableData.forEach((res) => {
            if (!res.num) {
              name=res.product.name;
              flag = true;
            }
          });
          if (flag) {
            this.$message({
              message: "请输入成品"+name+"的交付数量",
              type: "info",
            });
            return;
          }
          let data = {};
          data.id = this.saleForm.saleOrder;
          data.description = this.description;
          data.deliveryPlanDetailsList = this.tableData;
          data.saleId = this.saleId;
          data.name = this.name;
          data.deliveryDate = this.saleForm.deliveryDate;
          if (this.tableData.length == 0) {
            this.$message({
              message: "请先新增产品",
            });
            return;
          }
          updatDeliver(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/deliveryPlan");
              this.$refs[formName].resetFields();
            }
          });
        }
      });
    },
    getSaleOpetions() {
      selectSellParams().then((res) => {
        if (res.code === "0") {
          this.saleOrderOptions = res.data;
        }
      });
    },
    validateNum(index, rows) {
      let flag = false;
      let num = 0;
      this.deliveryDetails.forEach((detail) => {
        if (detail.product.id === rows[index].product.id) {
          num += detail.num;
        }
      });
      let ss = 0;
      this.saleDetail.forEach((detail) => {
        if (detail.product.id === rows[index].product.id) {
          if (detail.num - num < rows[index].num) {
            flag = true;
            ss = detail.num - num;
          }
        }
      });
      if (flag) {
        this.$message({
          message: "交付数量应不可大于剩余物料数量(" + ss + ")",
          type: "error",
        });
        rows[index].num = 0;
      }
    },
    deleteRow(index, tableData) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        tableData.splice(index, 1);
        this.$message({
          type: "success",
          message: "删除成功!",
        });
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    querySearchAsyncPro(queryString, cb) {
      var productOptions = this.productOptions;
      for (let item of productOptions) {
        item.value = item.name;
      }
      var results = queryString
        ? productOptions.filter(this.createStateFilter(queryString))
        : productOptions;
      cb(results);
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    proSelect(item) {
      this.productForm.product.id = item.id;
      this.productForm.product.name = item.name;
    },
    getProductList(item) {
      this.productOptions = [];
      find(item).then((res) => {
        let productList = [];
        this.saleDetail = res.data.saleDetailsList;
        res.data.saleDetailsList.forEach((d) => {
          productList.push(d.product);
        });
        this.productOptions = productList;
      });
    },
  },
  mounted() {
    let productData = {
      productName: "",
      specification: "",
    };
    this.getSaleOpetions();
    let id = this.$route.query.id;
    getDeliver(id).then((res) => {
      if (res.code === "0") {
        let option = {
          text: res.data.saleNo,
          value: res.data.id,
        };
        this.saleOrderOptions.push(option);
        this.saleForm.saleOrder = option.value;
        this.description = res.data.description;
        this.saleId = res.data.saleId;
        this.name = res.data.name;
        this.saleForm.deliveryDate = res.data.deliveryDate;
        this.tableData = res.data.deliveryPlanDetailsList;
        this.getProductList(res.data.saleId);
        findBySale(res.data.saleId).then((res) => {
          let delivery = res.data;
          delivery.forEach((de) => {
            if (de.id != id) {
              de.deliveryPlanDetailsList.forEach((detail) => {
                this.deliveryDetails.push(detail);
              });
            }
          });
        });
      }
    });
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/sale.scss";

.addResult .el-form-item:nth-child(1) {
  margin-left: 0 !important;
}

.addResult .el-form-item {
  margin-left: 20px;
}

.addResult {
  overflow: hidden;
}
.proConfirmBtn {
  padding: 0 0 10px 0;
  display: flex;
  justify-content: center;
}
.submitBtn {
  width: 100%;
  padding: 15px 0 15px 0;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  z-index: 10;
}
.roleManageTable {
  position: relative;
  height: calc(100vh - 500px);
}
</style>
