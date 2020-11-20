<template>
  <div>
    <lef top='15'></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑字典</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="类型名称" prop="typeId" size="small">
            <el-select v-model="ruleForm.typeId" placeholder="请选择" style="width:569px">
              <el-option v-for="(item,i) in DictType" :key="i" :label="item.name" :value="item.id" />
            </el-select>
          </el-form-item>
          <el-form-item label="字典键" prop="dictKey" size="small">
            <el-input v-model="ruleForm.dictKey" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="字典值" prop="dictValue" size="small">
            <el-input v-model="ruleForm.dictValue" style="width:569px"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm'),changBg(1)" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm'),changBg(2)" size="small" type="primary">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { updateDict, findDict } from "@/api/dict";
import { getDictType } from "@/api/dict";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  mixins: [btn],
    components: {
    submit,
    lef,
    con,
  },
  data() {
    return {
      ruleForm: {
        typeId: "",
        dictKey: "",
        dictValue: "",
        displayName: "",
      },
      DictType: [],
      rules: {
        dictKey: [
          { required: true, message: "请输入字典键", trigger: "blur" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },
        ],
        dictValue: [
          { required: true, message: "请输入字典值", trigger: "blur" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },
        ],
        displayName: [
          { required: true, message: "请输入字典值", trigger: "blur" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"],
          },
        ],
      },
    };
  },
  mounted() {
    let id = this.$route.query.id;
    getDictType({}).then((res) => {
      if (res.code === "0") {
        this.DictType = res.data;
      }
    });
    findDict(id).then((res) => {
      let content = res.data;
      this.ruleForm.dictKey = content.dictKey;
      this.ruleForm.dictValue = content.dictValue;
      this.ruleForm.typeId = content.dictType.id;
      this.ruleForm.displayName = content.displayName;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            id: this.$route.query.id,
            dictKey: this.ruleForm.dictKey,
            dictValue: this.ruleForm.dictValue,
            dictType: {
              id: this.ruleForm.typeId,
            },
            displayName: this.ruleForm.displayName,
          };
          updateDict(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.$router.push("/dict");
            }else{
              this.$message({
                message:res.message,
                type:"error"
              })
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/dict");
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
