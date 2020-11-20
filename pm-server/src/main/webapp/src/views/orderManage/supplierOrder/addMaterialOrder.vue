<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增原材料订单详情</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-row>
            <el-col :span="11">
              <el-form-item label="订单号" prop="orderNo" size="small">
                <el-input v-model="ruleForm.orderNo" style="width:100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="总数量" prop="num" size="small">
                <el-input v-model="ruleForm.num" style="width:100%"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="物料名称" prop="materialId">
                <el-select
                  v-model="ruleForm.materialId"
                  :placeholder="$t('main.placeInsert')"
                  size="small"
                  filterable
                  clearable
                  @change="getUnit"
                  style="width:100%"
                >
                  <el-option
                    v-for="item in materialList"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="物料单位" prop="unit" size="small">
                <el-input v-model="ruleForm.unit" style="width:100%" disabled></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="交货日期" prop="deliveryDate" size="small">
                <el-date-picker
                  v-model="ruleForm.deliveryDate"
                  type="date"
                  placeholder="选择日期"
                  style="width:100%"
                ></el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="供应商" prop="supplierId" size="small">
                <el-select
                  size="small"
                  style="width: 100%"
                  :placeholder="$t('main.placeInsert')"
                  filterable
                  clearable
                  v-model="ruleForm.supplierId"
                >
                  <el-option
                    v-for="item in supplierList"
                    :key="item.id"
                    :value="item.id"
                    :label="item.name"
                  />
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="交付数量" prop="deliveryNum" size="small">
                <el-input v-model="ruleForm.deliveryNum" style="width:100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="金额" prop="amount" size="small">
                <el-input v-model="ruleForm.amount" style="width:100%"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="11">
              <el-form-item label="文件日期" prop="documentDate" size="small">
                <el-date-picker
                  v-model="ruleForm.documentDate"
                  type="date"
                  placeholder="选择日期"
                  style="width:100%"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm')" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm')" size="small" type="primary">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getSupplier } from "@/api/supplier";
import { addMaterialOrder } from "@/api/materialOrder";
import { getSaleNo } from "@/api/sellOrder";
import { findMaterialListData } from "@/api/material";
import { list } from "@/api/unit";
import { getOrganization } from "@/api/organization";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    return {
      materialList: [],
      unitList: [],
      supplierList: [],
      ruleForm: {
        orderNo: "",
        materialId: "",
        unitId: "",
        supplierId: "",
        deliveryDate: "",        
        documentDate: "",
        amount: "",
        num: "",
        deliveryNum: "",
      },
      rules: {
        orderNo: [{ required: true, message: "请输入订单号", trigger: "blur" }],
        materialId: [
          { required: true, message: "请输入物料", trigger: "blur" },
        ],
        unitId: [{ required: true, message: "请选择单位", trigger: "blur" }],
        num: [
          { required: true, message: "请输入数量", trigger: "blur" },
          {
            pattern: /^[1-9][0-9]*$/,
            message: "请输入数字",
            trigger: "change",
          },
        ],
        supplierId:[{ required: true, message: "请选择供应商", trigger: "blur" }],
        deliveryNum: [
          { required: true, message: "请输入待发货数量", trigger: "blur" },
          {
            pattern: /^[1-9][0-9]*$/,
            message: "请输入数字",
            trigger: "change",
          },
        ],
        amount: [
          {
            pattern: /^\d+(\.\d+)?$/,
            message: "请输入数字",
            trigger: "change",
            required:true
          },
        ],
      },
    };
  },
  mounted() {
    this.createSaleNo();
    this.getSelectList();
    getSupplier().then((res) => {
      this.supplierList = res.data;
    });
  },
  methods: {
    getUnit(id) {
      this.ruleForm.unit = this.materialList.filter(
        (item) => id === item.id
      )[0].unitName;
    },
    getSelectList() {
      findMaterialListData().then((res) => {
        this.materialList = res.data;
      });
    },
    createSaleNo() {
      getSaleNo().then((res) => {
        if (res.code == 0) {
          this.ruleForm.orderNo = Number(res.data);
        }
      });
    },
    submitForm(formName) {
      this.ruleForm.material = { id: this.ruleForm.materialId }
      this.ruleForm.supplier = { id: this.ruleForm.supplierId }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addMaterialOrder(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/supplierOrderDetails");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/supplierOrderDetails");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
.main form {
  width: 100% !important;
}
.main-top {
  background: white;
  padding: 16px;
  min-height: calc(100vh - 132px);
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
}
.box {
  padding: 0;
}
</style>
