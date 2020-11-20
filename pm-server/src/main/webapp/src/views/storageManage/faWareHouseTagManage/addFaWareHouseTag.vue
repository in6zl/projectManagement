<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增标签</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="名称" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="标签值" prop="value" size="small">
            <el-input v-model="ruleForm.value" style="width:100%"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="toWareHouseTagManage" size="small">取消</el-button>
      <el-button type="primary" @click="submitForm('ruleForm')" size="small">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import lef from "@/components/common/l_icon";

import {
  findFaWarehouseTagByName,
  addFaWarehouseTag,
  getByName,
} from "@/api/faWareHouseTag";
import { getFaWarehouseList } from "@/api/faWareHouse";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  components: {
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    var nameChecker = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入标签名称"));
      }
      findFaWarehouseTagByName({ name: this.ruleForm.name }).then((res) => {
        if (res.data.length > 0) {
          callback(new Error("名称已存在"));
        } else {
          callback();
        }
      });
    };
    const numValidator = (rule, value, callback) => {
      if (!value) {
        return callback();
      } else {
        if (!/^\d+$/.test(value)) {
          return callback(new Error("数量必须是数字"));
        }
        if (value > 128) {
          return callback(new Error("最大值不能超过128"));
        }
        return callback();
      }
    };
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
      ruleForm: {
        name: "",
        value: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入标签名称", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        value: [
          { required: true, message: "请输入标签值", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
        ],
      },
      formName: "",
    };
  },
  mounted() {},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          try {
            addFaWarehouseTag(this[formName]).then((res) => {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push({
                name: "faWareHouseTagManage",
              });
            });
          } catch (e) {
            console.log(e);
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toWareHouseTagManage() {
      this.$router.push({
        name: "faWareHouseTagManage",
        params: this.$route.query,
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.tree {
  width: 100%;
  height: 345px;
  border-radius: 4px;
  border: 1px solid rgba(230, 230, 230, 1);
  overflow: hidden;
  & > .el-tree {
    margin: 16px;
  }
}
.main >>> .el-textarea__inner {
  height: 88px;
}
.main >>> .el-button {
  margin-bottom: 10px;
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