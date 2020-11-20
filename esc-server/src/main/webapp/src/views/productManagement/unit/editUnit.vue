<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">{{$t('unit.editUnit')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('unit.name')" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item :label="$t('unit.description')" prop="description" size="small">
            <el-input v-model="ruleForm.description" style="width:569px"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm')" size="small">{{$t('button.cancel')}}</el-button>
      <el-button
        type="primary"
        @click="submitForm('ruleForm')"
        size="small"
      >{{$t('button.confirm')}}</el-button>
    </submit>
  </div>
</template>
<script>
import { btn } from "@/components/minxs/button";
import { updateUnit, list, getUnitInfo,getByName } from "@/api/unit";
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
    const validateName = (rule, value, callback) => {
      getByName({
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
      id: this.$route.query.id,
      ruleForm: {
        description: "",
        name: "",
      },
      rules: {
        description: [
          {
            min: 1,
            max: 255,
            message: "长度在1到255个字符串",
            trigger: ["blur", "change"],
          }
        ],
        name: [
          { required: true, message: "请输入单位名称", trigger: "blur" },
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
    let id = this.id;
    getUnitInfo(id).then((res) => {
      this.ruleForm = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            id: this.id,
            description: this.ruleForm.description,
            name: this.ruleForm.name,
          };
          updateUnit(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.$router.push("/unit");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/unit");
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