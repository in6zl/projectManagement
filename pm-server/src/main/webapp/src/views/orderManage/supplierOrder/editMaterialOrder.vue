<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑原材料订单详情</div>
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
                  v-model="ruleForm.material.id"
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
              <el-form-item label="物料单位" prop="unitId" size="small">
                <el-select
                  size="small"
                  style="width: 100%"
                  :placeholder="$t('main.placeInsert')"
                  filterable
                  clearable
                  v-model="ruleForm.unit.id"
                >
                  <el-option
                    v-for="item in unitList"
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
              <el-form-item label="供应商" prop="organization" size="small">
                <el-select
                  size="small"
                  style="width: 100%"
                  :placeholder="$t('main.placeInsert')"
                  filterable
                  clearable
                  v-model="ruleForm.organization.id"
                >
                  <el-option
                    v-for="item in organizationList"
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
              <el-form-item label="交付数量" prop="unit" size="small">
                <el-input v-model="ruleForm.item" style="width:100%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="11">
              <el-form-item label="金额" prop="amount" size="small">
                <el-input v-model="ruleForm.amount" type="number" style="width:100%"></el-input>
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
import {
  updateMaterialOrder,
  getMaterialOrder,
} from "@/api/materialOrder";
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
      const numValidator = (rule, value, callback) => {
      if (!/^[1-9]+[0-9]*$/.test(value)) {
        return callback(new Error("数量必须是整数"));
      } else {
        return callback();
      }
    };
     const itemNumValidator = (rule, value, callback) => {
      if (!/^[1-9]+[0-9]*$/.test(value)) {
        return callback(new Error("交付数量必须是正整数"));
      } else {
        return callback();
      }
    };
    const amountNumValidator = (rule, value, callback) => {
      if (!/^[1-9]+\.*[0-9]*$/.test(value)) {
        return callback(new Error("金额必须是正数"));
      } else {
        return callback();
      }
    };
    return {
      materialList: [],
      unitList: [],
      organizationList: [],
      ruleForm: {
        orderNo: "",
        material: {},
        unit: {},
        organization: {},
        deliveryDate: "",
        item: "",
        documentDate: "",
        amount: "",
        num: "",
        deliveredNum: "",
      },
      rules: {
        orderNo: [{ required: true, message: "请输入订单号", trigger: "blur" }],
        materialId: [
          { required: true, message: "请输入物料", trigger: "blur" },
        ],
        unitId: [{ required: true, message: "请选择单位", trigger: "blur" }],
        num: [{ required: true, message: "请输入数量", trigger: "blur" },
              { validator: numValidator, trigger: ["blur", "change"] }],
        deliveredNum: [
          { required: true, message: "请输入待发货数量", trigger: "blur" },
        ],
         unit:[{ validator: itemNumValidator, trigger: ["blur", "change"] }],
        amount:[{ validator: amountNumValidator, trigger: ["blur", "change"] }]
      },
    };
  },
  methods: {
    getUnit(id) {
      this.ruleForm.unit.id = this.materialList.filter(
        (item) => id === item.id
      )[0].unit.id;
    },
    getSelectList() {
      findMaterialListData().then((res) => {
        this.materialList = res.data;
      });
      list().then((res) => {
        this.unitList = res.data;
      });
      getOrganization({ type: 1 }).then((res) => {
        this.organizationList = res.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateMaterialOrder(this.ruleForm).then((res) => {
            console.log(res);
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
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
  mounted() {
    let id = this.$route.query.id;
    this.getSelectList();
    getMaterialOrder(id).then((res) => {
      this.ruleForm = res.data;
      this.ruleForm.materialId = res.data.material.id;
      this.ruleForm.unitId = res.data.unit.id;
      this.ruleForm.organizationId = res.data.organization.id;
      console.log(res.data);
    });
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
