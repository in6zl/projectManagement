<template>
  <div class="main">
    <div>{{ $t('route.editInboundOrderMaterial') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('inoutBound.sendOrderNo')" prop="sendOrderId" size="small">
        <el-select v-model="ruleForm.sendOrderId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in sendOrderList"
            :key="item.id"
            :label="item.sendOrderNo"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.inOperator')" prop="inOperator" size="small">
        <el-input v-model="ruleForm.inOperator" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.deliveryMan')" prop="deliveryMan" size="small">
        <el-input v-model="ruleForm.deliveryMan" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.inTime')" prop="inTime" size="small">
        <el-date-picker
          v-model="ruleForm.inTime"
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
import {
  updateInboundOrderMaterial,
  getInboundOrderMaterial
} from "@/api/inboundOrderMaterial";
import { getSupplier } from "@/api/sendOrder";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        sendOrderId: undefined,
        inOperator: undefined,
        deliveryMan: undefined,
        inTime: undefined,
        isFinished: undefined
      },
      sendOrderList: [],
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" }
      ],
      rules: {
        sendOrderId: [{ required: true, message: "请输入", trigger: "blur" }],
        inOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        deliveryMan: [{ required: true, message: "请输入", trigger: "blur" }],
        isFinished: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    const id = this.$route.query.id;
    getInboundOrderMaterial(id).then(res => {
      this.ruleForm = res.data;
    });
    getSupplier({}).then(res => {
      if (res.code === "0") {
        this.sendOrderList = res.data;
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
        id: this.$route.query.id,
        invoice: {
          id: this.ruleForm.sendOrderId
        },
        inOperator: this.ruleForm.inOperator,
        deliveryMan: this.ruleForm.deliveryMan,
        inTime: this.ruleForm.inTime,
        isFinished: this.ruleForm.isFinished
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          updateInboundOrderMaterial(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success"
              });
              this.$router.push("inboundOrderMaterial");
            } else {
              this.$message.error(res.message);
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
      this.$router.push("inboundOrderMaterial");
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
