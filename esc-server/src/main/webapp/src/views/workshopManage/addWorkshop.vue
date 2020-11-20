<template>
  <div>
    <lef top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">添加车间</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="车间名称" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="车间地址" prop="address" size="small">
            <el-input v-model="ruleForm.address" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="联系人" prop="manager" size="small">
            <el-input v-model="ruleForm.manager" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" prop="phone" size="small">
            <el-input v-model="ruleForm.phone" style="width:569px"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm')" size="small">{{$t('button.cancel')}}</el-button>
      <el-button
        @click="submitForm('ruleForm')"
        size="small"
        type="primary"
      >{{$t('button.confirm')}}</el-button>
    </submit>
  </div>
</template>
<script>
import { btn } from "@/components/minxs/button";
import { addWorkshop,findByName } from "@/api/workshop";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    const validateCode = (rule, value, callback) => {
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
      list: [],
      ruleForm: {
        name: "",
        address:"",
        phone:"",
        manager:"",
        storeNum:0
      },
      rules: {
        name: [
          { required: true, message: "请输入车间名称", trigger: "blur" },
          {
            min: 1,
            max: 32,
            message: "长度在1到32个字符串",
            trigger: ["blur", "change"],
          },
          {
            validator: validateCode, // 自定义验证
            trigger: "blur",
          },
        ],
        address: [
          {
            min: 1,
            max: 128,
            message: "长度在1到128个字符串",
            trigger: ["blur", "change"],
          },
        ],
        manager: [
          { required: true, message: "请输入负责人", trigger: "blur" },
          {
            min: 1,
            max: 32,
            message: "长度在1到32个字符串",
            trigger: ["blur", "change"],
          },
        ],
        phone: [
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change"
          }
        ],
      },
    };
  },
  mounted() {
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addWorkshop(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/workshop");
            } else {
              this.$message({
                message: res.message,
                type: "error",
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
      this.$router.push("/workshop");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
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