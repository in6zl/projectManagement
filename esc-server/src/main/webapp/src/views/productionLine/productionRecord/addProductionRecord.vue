<template>
  <div class="main">
    <div>{{$t('route.addProductionRecord')}}</div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('productionRecord.batch')" prop="batch" size="small">
        <el-input v-model="ruleForm.batch" style="width:100%"></el-input>
      </el-form-item>
      <el-form-item :label="$t('productionRecord.num')" prop="num" size="small">
        <el-input v-model="ruleForm.num" style="width:100%"></el-input>
      </el-form-item>
      <el-form-item :label="$t('productionRecord.productName')" prop="productId" size="small">
        <el-select v-model="ruleForm.productId" placeholder="请选择" style="width:100%">
          <el-option
                  v-for="item in products"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          @click="resetForm('ruleForm'),changBg(2)"
          size="small"
          :class="{changBg:ischange == 2}"
        >{{$t('permission.cancel')}}
          </el-button>
        <el-button
          @click="submitForm('ruleForm'),changBg(1)"
          size="small"
          :class="{changBg:ischange == 1}"
        >{{$t('permission.confirm')}}
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { createPr } from "@/api/productionRecord";
import { list } from "@/api/unit";
import { prolist } from "@/api/product";
export default {
  mixins: [btn],
  data() {
    return {
      unit: {
        id: '',
        code: '',
        name: '',
        parentId: '',
        parentNum: ''
      },
      ruleForm: {},
      products: [],
      units: [],
      rules: {
          batch: [{ required: true, message: "请输入批次号", trigger: "blur" }],
          num: [{ required: true, message: "请输入生产数量", trigger: "blur" }],
        productId: [{ required: true, message: "请选择成品", trigger: "blur" }],
        unitId: [{ required: true, message: "请选择单位", trigger: "blur" }]
      }
    };
  },
  mounted() {
    this.getProduct();
    this.getUnit();
  },
  methods: {
    getUnit() {
      list(this.unit).then(res => {
        this.units = res.data;
      })
    },
    getProduct() {
      prolist({}).then(res => {
        this.products = res.data;
      })
    },
    submitForm(formName) {
      let pr = {};
      pr = {
        product: {
          id: this.ruleForm.productId
        },
        num: this.ruleForm.num,
        batch: this.ruleForm.batch,
        unit: {
          id: this.ruleForm.unitId,
        }
      }
      this.$refs[formName].validate(valid => {
        if (valid) {
          createPr(pr).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push("/productionRecord");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm() {
      this.$router.push("/productionRecord");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
</style>
