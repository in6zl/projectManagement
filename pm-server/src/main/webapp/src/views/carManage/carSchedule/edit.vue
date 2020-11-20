<template>
  <div class="main">
    <div>{{ $t('route.editCarSchedule') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('car.carNo')" prop="carId" size="small">
        <el-select v-model="ruleForm.carId" filterable placeholder="请选择" value>
          <el-option v-for="item in carList" :key="item.id" :label="item.carNo" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('car.outProductOrderNo')" prop="outProductOrderId" size="small">
        <el-select v-model="ruleForm.outProductOrderId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in outProductOrderList"
            :key="item.id"
            :label="item.orderNo"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('car.startTime')" prop="startTime" size="small">
        <el-date-picker
          v-model="ruleForm.startTime"
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item :label="$t('car.endTime')" prop="endTime" size="small">
        <el-date-picker
          v-model="ruleForm.endTime"
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
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
import { updateCarSchedule, getCarSchedule } from "@/api/carSchedule";
import { listCar } from "@/api/car";
import { getFaOutProductOrderList } from "@/api/faOutProductOrder";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        carId: "",
        outProductOrderId: "",
        startTime: undefined,
        endTime: undefined
      },
      carList: [],
      outProductOrderList: [],
      rules: {
        carId: [{ required: true, message: "请输入", trigger: "blur" }],
        outProductOrderId: [
          { required: true, message: "请输入", trigger: "blur" }
        ],
        startTime: [{ required: true, message: "请输入", trigger: "blur" }],
        endTime: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    const id = this.$route.query.id;
    getCarSchedule(id).then(res => {
      this.ruleForm = res.data;
    });
    listCar({}).then(res => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });
    getFaOutProductOrderList({}).then(res => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
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
        car: {
          id: this.ruleForm.carId
        },
        outProductOrder: {
          id: this.ruleForm.outProductOrderId
        },
        startTime: this.ruleForm.startTime,
        endTime: this.ruleForm.endTime
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          updateCarSchedule(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success"
              });
              this.$router.push("carSchedule");
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
      this.$router.push("carSchedule");
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
