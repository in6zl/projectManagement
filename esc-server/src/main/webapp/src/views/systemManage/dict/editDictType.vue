<template>
  <div>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0">编辑组织</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="组织名称" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:569px"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm'),changBg(1)" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm'),changBg(2)" type="primary" size="small">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { updateDictType, findDictType,getByName } from "@/api/dict";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  components: {
    submit,
    con,
  },
  mixins: [btn],
  data() {
    const validateName = (rule, value, callback) => {
      getByName({
        id:this.$route.query.id,
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
        name: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入类型名称", trigger: "blur" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },{
            validator: validateName, // 自定义验证
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateDictType(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.$router.push("/dictType");
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
      this.$router.push("/dictType");
    },
  },
  mounted() {
    let id = this.$route.query.id;
    findDictType(id).then((res) => {
      this.ruleForm = res.data;
    });
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
