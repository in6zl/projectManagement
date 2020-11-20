<template>
  <div>
    <lef top='15'></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑组织</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="生产计划项" prop="productionPlanId" size="small">
            <el-input v-model="ruleForm.productionPlanItem.id"></el-input>
          </el-form-item>
          <el-form-item label="是否通过" prop="pass" size="small">
            <el-input v-model="ruleForm.pass"></el-input>
          </el-form-item>
          <el-form-item label="检查人" prop="examiner" size="small">
            <el-input v-model="ruleForm.examiner"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="url" size="small">
            <el-input v-model="ruleForm.url"></el-input>
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
import {
  updateInspectionRecord,
  findInspectionRecord,
} from "@/api/inspectionRecord";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
    lef
  },
  mixins: [btn],
  data() {
    return {
      ruleForm: {
        productionPlanId: "",
        pass: "",
        examiner: "",
        url: "",
      },
      rules: {
        pass: [{ required: true, message: "请输入是否通过", trigger: "blur" }],
        examiner: [
          { required: true, message: "请输入检查人", trigger: "blur" },
        ],
        url: [{ required: true, message: "请输入图片", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateInspectionRecord(this.ruleForm).then((res) => {
            console.log(res);
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.$router.push("/inspectionRecord/inspectionRecord");
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
  },
  mounted() {
    let id = this.$route.query.id;
    findInspectionRecord(id).then((res) => {
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
