<template>
  <div class="saleOrder" style="padding-bottom:16px">
    <div class="main">
      <div class="subtit">交付计划</div>
      <el-form
        class="demo-ruleForm"
        label-width="100px"
        ref="saleForm"
        :inline="true"
        :rules="saleRules"
        :model="saleForm"
      >
        <el-form-item label="名称" prop="name">
          <el-input placeholder="请输入名称" :value="name" size="small" disabled></el-input>
        </el-form-item>
        <el-form-item label="销售单" style="float:left;" prop="saleOrder">
          <el-input :value="saleForm.saleOrder" disabled></el-input>
        </el-form-item>
        <el-form-item label="选择交付日期" prop="deliveryDate">
          <el-date-picker
            :value="deliveryDate"
            type="date"
            placeholder="选择交付日期"
            size="small"
            style="width:200px"
            value-format="yyyy-MM-dd"
            disabled
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="描述">
          <el-input
            type="textarea"
            :rows="1"
            placeholder="请输入内容"
            v-model="description"
            resize="none"
            disabled
          ></el-input>
        </el-form-item>
      </el-form>
      <div style="float:right;padding: 0 16px 16px 0">
        <el-button @click="changBg(1),back()" size="small" :class="{changBg:ischange == 1}">返回</el-button>
      </div>
    </div>
    <div class="roleManageTable">
      <el-table
        :data="tableData"
        style="width:100vw"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
        <el-table-column label="成品" prop="product.name" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="num" label="数量" :show-overflow-tooltip="true"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getDeliver } from "@/api/deliveryPlan";
import { selectSellParams } from "@/api/sellOrder";
export default {
  mixins: [btn],
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
      console.log(this.saleOrderOptions);
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
    return {
      productOptions: [],
      saleId: "",
      name: "",
      deliveryDate: "",
      description: "",
      saleOrderOptions: [],
      productForm: {
        product: {
          id: "",
          name: "",
        },
        num: "",
        expectTime: "",
      },
      saleForm: {
        saleOrder: {
          text: "",
          value: "",
        },
      },
      tableHeight: window.innerHeight - 290,
      tableData: [],
      saleRules: {
        saleOrder: [
          // { required: true, message: "请选择销售单", trigger: "blur" }
        ],
      },
      productRules: {
        product: {
          name: [
            {
              validator: validatePro, // 自定义验证
              trigger: ["blur", "change"],
            },
            { required: true, message: "请选择成品", trigger: "blur" },
          ],
        },
        num: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          {
            type: "number",
            message: "数量必须是数字",
            trigger: "blur",
          },
        ],
        expectTime: {
          required: true,
          message: "请选交付日期",
          trigger: "blur",
        },
      },
    };
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    back() {
      this.$router.push("/deliveryPlan");
    },
  },
  mounted() {
    let id = this.$route.query.id;
    getDeliver(id).then((res) => {
      if (res.code === "0") {
        let option = {
          text: res.data.saleNo,
          value: res.data.id,
        };
        this.saleOrderOptions.push(option);
        this.saleForm.saleOrder = option.text;
        this.description = res.data.description;
        this.saleId = res.data.saleId;
        this.name = res.data.name;
        this.deliveryDate = res.data.deliveryDate;
        this.tableData = res.data.deliveryPlanDetailsList;
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
  margin-left: 20px;
}
.addResult .el-form-item {
  margin-left: 20px;
  float: left;
}
.addResult {
  overflow: hidden;
}
</style>
