<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增货架</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          
          <el-form-item label="货架编码" prop="code" size="small">
            <el-input v-model="ruleForm.code" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="总层数" prop="layers" size="small">
            <el-input v-model="ruleForm.layers" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="内仓" prop="warehouseId" size="small">
            <el-select v-model="ruleForm.warehouseId" style="width:100%">
              <el-option
                v-for="item in ruleForm.warehouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="toFaStorageBinManage" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm')" size="small" type="primary">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { addFaStorageBin,getByName } from "@/api/faStorageBin";
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
        code: value,
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
        warehouseId: "",
        code: "",
        layers: "",
        warehouseList: [],
      },
      rules: {
        warehouseId: [
          { required: true, message: "请选择内仓", trigger: "blur" },
        ],
        code: [
          { required: true, message: "请输入货架编码", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        layers: [
          { required: true, message: "请输入货架总层数", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          {
            pattern: /^[1-9][0-9]*$/,
            message: "请输入数字",
            trigger: "change",
          },
        ],
      },
    };
  },
  mounted() {
    this.getGetFaWarehouseList();
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addFaStorageBin(this[formName]).then((res) => {
            this.$message({
              message: "创建成功",
              type: "success",
            });
            this.$router.push("/faStorageBinManage");
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
    toFaStorageBinManage() {
      this.$router.push("/faStorageBinManage");
    },
    getGetFaWarehouseList() {
      getFaWarehouseList(new Object()).then((res) => {
        console.log(res);
        this.ruleForm.warehouseList = res.data;
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