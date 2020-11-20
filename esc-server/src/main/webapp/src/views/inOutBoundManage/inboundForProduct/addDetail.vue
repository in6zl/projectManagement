<template>
  <div class="main">
    <div>{{ $t('route.addInboundOrderProductDetail') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('inoutBound.productName')" prop="productId" size="small">
        <el-select v-model="ruleForm.productId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in productList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
        <el-input v-model="ruleForm.num" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.unitName')" prop="unitId" size="small">
        <el-select v-model="ruleForm.unitId" filterable placeholder="请选择" value>
          <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.binName')" prop="binId" size="small">
        <el-select v-model="ruleForm.binId" filterable placeholder="请选择" value>
          <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">
        <el-input v-model="ruleForm.unitNum" />
      </el-form-item>

      <el-form-item>
        <el-button size="small" @click="toIndex">取消</el-button>
        <el-button
          size="small"
          type="primary"
          @click="submitForm('ruleForm')"
          :loading="isLoading"
        >确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addInboundOrderProductDetail } from "@/api/inboundOrderProductDetail";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { prolist } from "@/api/product";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        productId: undefined,
        num: undefined,
        binId: undefined,
        unitId: undefined,
        unitNum: undefined
      },
      productList: [],
      unitList: [],
      binList: [],
      rules: {
        productId: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unitId: [{ required: true, message: "请输入", trigger: "blur" }],
        binId: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    unitList({ code: "", name: "" }).then(res => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
    getFaStorehouseList({}).then(res => {
      if (res.code === "0") {
        this.binList = res.data;
      }
    });
    prolist({}).then(res => {
      if (res.code === "0") {
        this.productList = res.data;
      }
    });
  },
  methods: {
    submitForm(formName) {
      this.isLoading = true;
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
      const data = {
        product: {
          id: this.ruleForm.productId
        },
        unit: {
          id: this.ruleForm.unitId
        },
        bin: {
          id: this.ruleForm.binId
        },
        unitNum: this.ruleForm.unitNum,
        num: this.ruleForm.num,
        inboundOrderProduct: {
          id: this.$route.query.inboundProductId
        }
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          addInboundOrderProductDetail(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push({
                path: "inboundOrderProductDetail",
                query: {
                  id: this.$route.query.id,
                  code: this.$route.query.code
                }
              });
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toIndex() {
      this.$router.push({
        path: "inboundOrderProductDetail",
        query: { id: this.$route.query.id, code: this.$route.query.code }
      });
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
