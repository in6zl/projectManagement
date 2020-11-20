<template>
  <div class="main">
    <div>{{ $t('route.editOutboundOrderMaterial') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item
        :label="$t('inoutBound.materialRequirePlanId')"
        prop="materialRequirePlanName"
        size="small"
      >
        <el-select v-model="ruleForm.materialRequirePlanId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in materialRequirePlanList"
            :key="item.id"
            :label="item.businessId"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
        <el-input v-model="ruleForm.outOperator" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.receiver')" prop="receiver" size="small">
        <el-input v-model="ruleForm.receiver" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.outTime')" prop="outTime" size="small">
        <el-date-picker
          v-model="ruleForm.outTime"
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.isFinished')" prop="isFinished" size="small">
        <el-select v-model="ruleForm.isFinished" filterable placeholder="请选择" value>
          <el-option
            v-for="item in isFinishedList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button size="small" @click="resetForm('ruleForm')">取消</el-button>
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
import {
  updateOutboundOrderMaterial,
  getOutboundOrderMaterial
} from "@/api/outboundOrderMaterial";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        materialRequirePlanId: undefined,
        outOperator: undefined,
        receiver: undefined,
        outTime: undefined,
        isFinished: undefined
      },
      materialRequirePlanList: [{ id: "1", businessId: "1" }],
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" }
      ],
      rules: {
        materialRequirePlanId: [
          { required: true, message: "请输入", trigger: "blur" }
        ],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        receiver: [{ required: true, message: "请输入", trigger: "blur" }],
        isFinished: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    const id = this.$route.query.id;
    getOutboundOrderMaterial(id).then(res => {
      this.ruleForm = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      this.isLoading = true;
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
      const data = {
        id: this.$route.query.id,
        materialRequirePlan: {
          id: this.ruleForm.materialRequirePlanId
        },
        outOperator: this.ruleForm.outOperator,
        receiver: this.ruleForm.receiver,
        outTime: this.ruleForm.outTime,
        isFinished: this.ruleForm.isFinished
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          updateOutboundOrderMaterial(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success"
              });
              this.$router.push("outboundOrderMaterial");
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
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main > .el-button {
  margin-bottom: 30px;
}
</style>
