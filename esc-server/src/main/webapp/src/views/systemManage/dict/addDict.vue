<template>
  <div>
    <lef top='15'></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增字典类型</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="类型名称" prop="typeId" size="small">
            <el-select
              v-model="ruleForm.typeId"
              filterable
              placeholder="请选择"
              value
              style="width:569px"
            >
              <el-option
                v-for="item in DictType"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
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
      <el-button @click="resetForm('ruleForm'),changBg(2)" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm'),changBg(1)" size="small" type="primary">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addDict } from "@/api/dict";
import { getDictType } from "@/api/dict";
import con from "@/components/common/content";
import submit from "@/components/common/submitBtn";
import lef from "@/components/common/l_icon";

export default {
  mixins: [btn],
  components: {
    submit,
    con,
    lef
  },
  data() {
    return {
      ruleForm: {
        typeId: "",
        dictKey: "",
        dictValue: "",
        displayName: "",
      },
      rules: {
        typeId: [
          { required: true, message: "请输入类型名称", trigger: "blur" },
        ],
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
      DictType: [],
    };
  },
  mounted() {
    getDictType({}).then((res) => {
      if (res.code === "0") {
        this.DictType = res.data;
      }
    });
  },
  methods: {
    submitForm(formName) {
      let dict = {};
      let dictType = {};
      dict.dictKey = this.ruleForm.dictKey;
      dict.dictValue = this.ruleForm.dictValue;
      dictType.id = this.ruleForm.typeId;
      dict.dictType = dictType;
      dict.displayName = this.ruleForm.displayName;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addDict(dict).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
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
