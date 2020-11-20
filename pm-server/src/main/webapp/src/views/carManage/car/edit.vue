<template>
  <div>
    <lef top='15'></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">{{ $t('route.editCar') }}</div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="150px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('car.carNo')" prop="carNo" size="small">
            <el-input v-model="ruleForm.carNo" />
          </el-form-item>
          <el-form-item :label="$t('car.driverName')" prop="driverName" size="small">
            <el-input v-model="ruleForm.driverName" />
          </el-form-item>
          <el-form-item :label="$t('car.driverPhone')" prop="driverPhone" size="small">
            <el-input v-model="ruleForm.driverPhone" />
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
import { updateCar, getCar } from "@/api/car";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
import { getValue } from "@/api/dict";

export default {
  components: {
    submit,
    con,
    lef
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
      typeList: [
        { id: 1, name: "类型1" },
        { id: 2, name: "类型2" },
      ],
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
    const id = this.$route.query.id;
    getCar(id).then((res) => {
      this.ruleForm = res.data;
      this.ruleForm.type=this.ruleForm.type+""
    });
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
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateCar(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
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
.main > .el-button {
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
