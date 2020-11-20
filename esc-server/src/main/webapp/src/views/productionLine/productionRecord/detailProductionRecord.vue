<template>
  <div class="main">
    <div class="subtit">{{$t('route.detailProductionRecord')}}</div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('productionRecord.batch')" size="small">
        <el-input v-model="ruleForm.batch" disabled></el-input>
      </el-form-item>
      <el-form-item :label="$t('productionRecord.num')" size="small">
        <el-input v-model="ruleForm.num" disabled></el-input>
      </el-form-item>
      <el-form-item :label="$t('productionRecord.productName')" size="small">
        <el-input v-model="ruleForm.productName" disabled></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getPr } from "@/api/productionRecord";
export default {
  mixins: [btn],
  data() {
    return {
      ruleForm: {},
    };
  },
  mounted() {
      let id = this.$route.query.id;
      this.getPr(id);
  },

  methods: {
    getPr(id) {
        getPr(id).then(res => {
          this.ruleForm = res.data;
        })
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
