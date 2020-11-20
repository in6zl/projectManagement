<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div class="subtit">新增销售单</div>
        <el-steps :active="active" finish-status="success" align-center>
          <el-step title="填写销售单信息"></el-step>
          <el-step title="新增产品"></el-step>
          <el-step title="完成"></el-step>
        </el-steps>
        <el-form
          class="saleform"
          label-width="120px"
          ref="saleForm"
          :rules="saleRules"
          :model="saleForm"
          v-if="active==0"
          style="overflow:hidden"
        >
          <el-form-item :label="$t('main.orderNo')" prop="orderNo">
            <el-input
              :placeholder="$t('main.placeInsert')"
              size="small"
              v-model.number="saleForm.orderNo"
              maxlength="16"
            ></el-input>
          </el-form-item>
          <el-form-item :label="$t('main.seller')" prop="seller">
            <el-input
              :placeholder="$t('main.placeInsert')"
              maxlength="16"
              size="small"
              v-model="saleForm.seller"
            ></el-input>
          </el-form-item>
          <el-form-item label="金额" prop="amount">
            <el-input
              :placeholder="$t('main.placeInsert')"
              maxlength="32"
              size="small"
              v-model="saleForm.amount"
            ></el-input>
          </el-form-item>
          <el-form-item :label="$t('main.sellerPhone')" prop="sellerPhone">
            <el-input
              :placeholder="$t('main.placeInsert')"
              size="small"
              minlength="11"
              maxlength="11"
              v-model="saleForm.sellerPhone"
            ></el-input>
          </el-form-item>
          <el-form-item label="出货地址" prop="shipment">
            <el-input
              :placeholder="$t('main.placeInsert')"
              maxlength="128"
              size="small"
              v-model="saleForm.shipment"
            ></el-input>
          </el-form-item>
          <el-form-item label="期望到货时间" prop="expectTime">
            <el-date-picker
              v-model="saleForm.expectTime"
              type="date"
              placeholder="选择日期"
              size="small"
              value-format="yyyy-MM-dd"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="签单时间" prop="orderTime">
            <el-date-picker
              v-model="saleForm.orderTime"
              type="datetime"
              placeholder="选择日期时间"
              size="small"
              format="yyyy-MM-dd HH:mm:ss"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
        </el-form>

        <div class="roleManageTable" v-if="active==1">
          <el-button
            size="small"
            type="primary"
            @click="addProduct()"
            style="margin:40px 16px 16px 16px"
          >新增产品</el-button>
          <el-button size="small" style="margin:40px 16px 16px 0" @click="batchDelete">批量删除</el-button>
          <el-table
            :data="tableData"
            style="width: 100%"
            :height="320"
            :header-cell-style="tableHeaderColor"
            @selection-change="handleSelectionChange"
          >
            >
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column type="index" width="80" align="center" :label="$t('table.serial')"></el-table-column>
            <el-table-column
              label="成品"
              prop="product.name"
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
                    v-for="(item,i) in productOptions"
                    :key="i"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column prop="num" label="数量" :show-overflow-tooltip="true" align="center">
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
            <el-table-column prop="unit" label="单位" :show-overflow-tooltip="true" align="center"></el-table-column>
            <el-table-column label="操作" fixed="right" align="right">
              <template slot-scope="scope">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <div class="already" v-if="active==3" style="text-align:center">
          <div style="margin-top: 50px!important;">
            <img src="@/assets/images/success.png" style="width:16px;" />
            <span>新增销售单成功</span>
          </div>
          <div class="alreadyBtn" style="margin-top: 50px!important;">
            <el-button plain size="small" @click="checkDetial">查看详情</el-button>
            <el-button type="primary" size="small" @click="goHome">返回首页</el-button>
          </div>
        </div>
      </div>
    </con>
    <submit class="subBtn" v-if="active==0">
      <el-button @click="resetForm('sale')" size="small">取消</el-button>
      <el-button @click="next()" size="small" type="primary">下一步</el-button>
    </submit>
    <submit class="subBtn" v-if="active==1">
      <el-button @click="prev()" size="small" type="primary">上一步</el-button>
      <el-button @click="success()" size="small" type="success">完成</el-button>
    </submit>
  </div>
</template>

<script>
import { prolist } from "@/api/product";
import { list } from "@/api/unit";
import { createSellOrder, getSaleNo, getByName } from "@/api/sellOrder";
import { parseTime } from "@/utils/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
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
    const validateName = (rule, value, callback) => {
      getByName({
        orderNo: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    return {
      active: 0,
      saleId: "",
      multipleSelection: null,
      dialogVisible: false,
      productOptions: [],
      unitOption: [],
      saleForm: {
        orderNo: "",
        seller: "",
        sellerPhone: "",
        expectTime: "",
        shipment: "",
        orderTime: "",
        amount: "",
      },
      tableHeight: window.innerHeight - 110,
      tableData: [],
      saleRules: {
        orderNo: [
          { required: true, message: "请输入订单编号", trigger: "blue" },
          { type: "number", message: "订单编号必须数字", trigger: "change" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        seller: { required: true, message: "请输入签单人", trigger: "blur" },
        sellerPhone: [
          {
            required: true,
            message: "请输入签单人手机",
            trigger: "blur",
          },
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change",
          },
        ],
        amount: {
          required: false,
          pattern: /^\d+(\.\d+)?$/,
          message: "金额必须是数字",
          trigger: "change",
          required: true,
        },
        shipment: {
          required: true,
          message: "请输入出货地址",
          trigger: "blur",
        },
        expectTime: {
          required: true,
          message: "请选择期望到货日期",
          trigger: "blur",
        },
        orderTime: {
          required: true,
          message: "请选择签单时间",
          trigger: "blur",
        },
      },
    };
  },
  methods: {
    //批量删除
    batchDelete() {
      let idList = [];
      this.multipleSelection.map((item) => {
        idList.push(item.id);
      });
      this.tableData = this.tableData.filter(
        (item) => idList.indexOf(item.id) == -1
      );
    },
    //选中表格数据
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    goHome() {
      this.$router.push("/sale");
    },
    checkDetial() {
      let id = this.saleId;
      this.$router.push({ path: "/detail", query: { id: id } });
    },
    next() {
      this.$refs.saleForm.validate((valid) => {
        if (valid) {
          this.active = 1;
        }
      });
    },
    prev() {
      this.active = 0;
    },
    success() {
      let data = {};
      data.orderNo = this.saleForm.orderNo;
      data.amount = this.saleForm.amount;
      data.expectDate = parseTime(this.saleForm.expectTime);
      data.address = this.saleForm.shipment;
      data.orderTime = parseTime(this.saleForm.orderTime);
      data.seller = this.saleForm.seller;
      data.sellerPhone = this.saleForm.sellerPhone;
      data.saleDetailsList = this.tableData;
      if (this.tableData.length == 0) {
        this.$message({
          message: "请先新增产品",
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
      createSellOrder(data).then((res) => {
        if (res.code === "0") {
          this.active = 3;
          this.saleId = res.data.id;
        } else {
          this.$message({
            message: "创建失败",
            type: "error",
          });
          // this.$router.push("/sale");
          // this.$refs[formName].resetFields();
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/sale");
    },
    createSaleNo() {
      getSaleNo().then((res) => {
        if (res.code == 0) {
          this.saleForm.orderNo = Number(res.data);
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((validate) => {});
    },
    addProduct() {
      if (this.tableData.length == 0) {
        this.tableData.push({
          product: {
            id: "",
            name: "",
          },
          unit: "",
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
          unit: "",
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
        this.tableData[index].product.id="";
        this.tableData[index].product.name="";
        this.tableData[index].unit="";
        this.tableData[index].num="";
      } else {
        this.productOptions.forEach((p) => {
          if (p.id === productId) this.tableData[index].unit = p.unitName;
        });
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
      console.log(this.productOptions);
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
    querySearchAsyncUnit(queryString, cb) {
      var unitOption = this.unitOption;
      for (let item of unitOption) {
        item.value = item.name;
      }
      var results = queryString
        ? unitOption.filter(this.createStateFilter(queryString))
        : unitOption;
      cb(results);
    },
    proSelect(item) {
      this.productForm.product.id = item.id;
      this.productForm.product.name = item.name;
      this.productForm.unit.id = item.unitId;
      this.productForm.unit.name = item.unitName;
    },
    unitSelect(item) {
      this.productForm.unit.id = item.id;
      this.productForm.unit.name = item.name;
    },
  },
  mounted() {
    // window.addEventListener("scroll", this.handleScroll);
    this.createSaleNo();
    let unitData = {
      id: "",
      name: "",
    };
    let productData = {
      productName: "",
      specification: "",
    };
    prolist(productData).then((res) => {
      this.productOptions = res.data;
    });
    list(unitData).then((res) => {
      this.unitOption = res.data;
    });
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
// @import "~@/styles/systemStyle/sale.scss";

.saleform {
  width: 60%;
  margin-left: 17% !important;
  margin-bottom: 8% !important;
}
.subBtn {
  margin-top: -18px;
}
.main-top {
  background: white;
  padding: 16px;
  min-height: calc(100vh - 150px);
}
</style>
