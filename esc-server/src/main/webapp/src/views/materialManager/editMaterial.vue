<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑物料</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="编码" prop="code" size="small">
            <el-input size="small" v-model="ruleForm.code" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="物料名称" prop="name" size="small">
            <el-input v-model="ruleForm.name" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="规格" prop="spec" size="small">
            <el-input v-model="ruleForm.spec" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="物料单位" prop="unitName" size="small">
            <el-select v-model="ruleForm.unitName" placeholder="请选择" style="width:569px">
              <el-option v-for="(item,i) in units" :key="i" :label="item.name" :value="item.name"></el-option>
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
import {
  findAllMaterialData,
  edit,
  findMaterialListData,
  findByName
} from "@/api/material";
import { list } from "@/api/unit";
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
        id:this.$route.query.id,
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
        id: "",
        code: "",
        name: "",
        spec: "",
        unitName: "",
      },
      materials: [],
      code: "",
      name: "",
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
          { required: true, message: "请选择物料名称", trigger: "change" },
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
        unitName: [{ required: true, message: "请选择单位", trigger: "blur" }],
      },
    };
  },
  mounted() {
    let id = this.$route.query.id;
    this.getAllUnits();
    findAllMaterialData({
      id: id,
      pageIndex: 0,
      pageSize: 100,
    }).then((res1) => {
      if (res1.code === "0") {
        this.ruleForm.id = res1.data.content[0].id;
        this.ruleForm.name = res1.data.content[0].name;
        this.ruleForm.spec = res1.data.content[0].spec;
        this.ruleForm.code = res1.data.content[0].code;
        this.ruleForm.unitName = res1.data.content[0].unitName;
      }
    });
  },
  methods: {
    getAllUnits() {
      let data = {
        id: "",
        code: "",
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
            id: this.ruleForm.id,
            code: this.ruleForm.code,
            name: this.ruleForm.name,
            spec: this.ruleForm.spec,
            unitName: this.ruleForm.unitName,
            type: "1",
          };
          edit(data).then((res) => {
            if (res.code === "0") {
              this.$router.push("/materialMgr");
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/materialMgr");
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