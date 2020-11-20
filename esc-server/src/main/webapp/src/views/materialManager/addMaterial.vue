<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增物料</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="编码" prop="code" size="small">
            <el-input class="selfBtn" size="small" v-model="ruleForm.code" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="物料名称" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="规格" prop="spec" size="small">
            <el-input v-model="ruleForm.spec" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="物料单位" prop="unit" size="small">
            <el-select v-model="ruleForm.unit" placeholder="请选择" style="width:569px">
              <el-option
                v-for="(item,i) in units"
                :key="i"
                :label="item.name"
                :value="item.name"
                style="width:569px"
              ></el-option>
            </el-select>
          </el-form-item>
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
import request from "@/utils/request";
import { add, findMaterialListData,findByName } from "@/api/material";
import { list } from "@/api/unit";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  mixins: [btn],
  components: {
    submit,
    con,
    lef,
  },
  data() {
    const validateCode = (rule, value, callback) => {
      findByName({
        code: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    const validateName = (rule, value, callback) => {
      findByName({
        name: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    return {
      ruleForm: {
        code: "",
        name: "",
        spec: "",
        unit: "",
        type: "",
      },
      units: [],
      rules: {
        code: [
          { required: true, message: "请输入物料编号", trigger: "blur" },
          {
            min: 1,
            max: 20,
            message: "长度在1到20个字符串",
            trigger: ["blur", "change"],
          },
          {
            validator: validateCode, // 自定义验证
            trigger: "blur",
          },
        ],
        spec: [
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },
        ],
        name: [
          { required: true, message: "请输入物料名称", trigger: "change" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },
          {
            validator: validateName, // 自定义验证
            trigger: "blur",
          },
        ],
        unit: [{ required: true, message: "请选择单位", trigger: "blur" }],
      },
    };
  },
  mounted() {
    this.getAllUnits();
    this.generateCode();
  },
  methods: {
    getAllUnits() {
      let data = {
        id: "",
        name: "",
      };
      list(data).then((res) => {
        this.units = res.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            code: this.ruleForm.code,
            name: this.ruleForm.name,
            spec: this.ruleForm.spec,
            unitName: this.ruleForm.unit,
            type: this.ruleForm.type,
          };
          add(data).then((res) => {
            this.$router.push("/materialMgr");
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/materialMgr");
    },
    generateCode() {
      this.ruleForm.code = "CN" + new Date().getTime();
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
._selfBt {
  width: 20%;
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
.el-form {
  width: 80%;
}
</style>