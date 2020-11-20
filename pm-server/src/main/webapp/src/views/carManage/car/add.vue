<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">{{ $t('route.addCar') }}</div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="150px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('car.carNo')" prop="carNo" size="small">
            <el-input v-model="ruleForm.carNo" style="width:100%" />
          </el-form-item>
          <el-form-item :label="$t('car.driverName')" prop="driverName" size="small">
            <el-input v-model="ruleForm.driverName" style="width:100%" />
          </el-form-item>
          <el-form-item :label="$t('car.driverPhone')" prop="driverPhone" size="small">
            <el-input v-model="ruleForm.driverPhone" style="width:100%" />
          </el-form-item>
          <el-form-item :label="$t('car.type')" prop="type" size="small">
            <el-select
              v-model="ruleForm.type"
              filterable
              placeholder="请选择"
              value
              style="width:100%"
            >
              <el-option
                v-for="item in typeList"
                :key="item.id"
                :label="item.dictKey"
                :value="item.dictValue"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('car.isOpen')" prop="isOpen" size="small">
            <el-select
              v-model="ruleForm.isOpen"
              filterable
              placeholder="请选择"
              value
              style="width:100%"
            >
              <el-option
                v-for="item in isOpenList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('car.inUse')" prop="inUse" size="small">
            <el-select
              v-model="ruleForm.inUse"
              filterable
              placeholder="请选择"
              value
              style="width:100%"
            >
              <el-option
                v-for="item in inUseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')" :loading="isLoading">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addCar } from "@/api/car";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
import { getValue } from "@/api/dict";
export default {
  components: {
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        carNo: "",
        driverName: "",
        driverPhone: "",
        type: undefined,
        isOpen: undefined,
        inUse: undefined,
      },
      rules: {
        carNo: [{ required: true, message: "请输入", trigger: "blur" }],
        driverName: [{ required: true, message: "请输入", trigger: "blur" }],
        driverPhone: [
          { required: true, message: "请输入", trigger: "blur" },
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的格式",
            trigger: "change",
          },
        ],
        type: [{ required: true, message: "请输入", trigger: "blur" }],
        isOpen: [{ required: true, message: "请输入", trigger: "blur" }],
        inUse: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      typeList: [],
      inUseList: [
        { id: 0, name: "暂未使用" },
        { id: 1, name: "已使用" },
      ],
      isOpenList: [
        { id: 0, name: "停用" },
        { id: 1, name: "非停用" },
      ],
    };
  },
  mounted() {
    getValue("车辆类型").then((res) => {
      this.typeList=res.data
    });
  },
  methods: {
    submitForm(formName) {
      this.isLoading = true;
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
      const data = {};
      data.carNo = this.ruleForm.carNo;
      data.driverName = this.ruleForm.driverName;
      data.driverPhone = this.ruleForm.driverPhone;
      data.type = this.ruleForm.type;
      data.isOpen = this.ruleForm.isOpen;
      data.inUse = this.ruleForm.inUse;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addCar(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("car");
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
      this.$router.push("car");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
.el-form {
  width: 80%;
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
