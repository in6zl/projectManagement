<template>
  <div class="main">
    <div>{{ $t('route.editOutboundOrderProduct') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item
        :label="$t('inoutBound.outProductOrderNo')"
        prop="outProductOrderId"
        size="small"
      >
        <el-select v-model="ruleForm.outProductOrderId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in outProductOrderList"
            :key="item.id"
            :label="item.orderNo"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.warehouseName')" prop="warehouseId" size="small">
        <el-select v-model="ruleForm.warehouseId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in warehouseList"
            :key="item.id"
            :label="item.name"
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
  updateOutboundOrderProduct,
  getOutboundOrderProduct
} from "@/api/outboundOrderProduct";
import { getFaOutProductOrderList } from "@/api/faOutProductOrder";
import { getFaWarehouseList } from "@/api/faWareHouse";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        outOperator: undefined,
        receiver: undefined,
        outTime: undefined,
        isFinished: undefined
      },
      outProductOrderList: [],
      warehouseList: [],
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" }
      ],
      rules: {
        outProductOrderId: [
          { required: true, message: "请输入", trigger: "blur" }
        ],
        warehouseId: [{ required: true, message: "请输入", trigger: "blur" }],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        receiver: [{ required: true, message: "请输入", trigger: "blur" }],
        isFinished: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    const id = this.$route.query.id;
    getOutboundOrderProduct(id).then(res => {
      this.ruleForm = res.data;
    });
    getFaOutProductOrderList({}).then(res => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
      }
    });
    getFaWarehouseList({}).then(res => {
      if (res.code === "0") {
        this.warehouseList = res.data;
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
        outProductOrder: {
          id: this.ruleForm.outProductOrderId
        },
        faWarehouse: {
          id: this.ruleForm.warehouseId
        },
        outOperator: this.ruleForm.outOperator,
        receiver: this.ruleForm.receiver,
        outTime: this.ruleForm.outTime,
        isFinished: this.ruleForm.isFinished
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          updateOutboundOrderProduct(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success"
              });
              this.$router.push("outboundOrderProduct");
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
      this.$router.push("outboundOrderProduct");
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
