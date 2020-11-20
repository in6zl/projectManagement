<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">{{$t('route.editSupplier')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('supMana.supplier.code')" prop="code" size="small">
            <el-input v-model="ruleForm.code"></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.name')" prop="name" size="small">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.address')" prop="address" size="small">
            <el-input v-model="ruleForm.address"></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.phone')" prop="phone" size="small">
            <el-input v-model="ruleForm.phone"></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.description')" size="small">
            <el-input v-model="ruleForm.description"></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button
        @click="resetForm('ruleForm'),changBg(2)"
        size="small"
        :class="{changBg:ischange == 2}"
      >{{$t('permission.cancel')}}</el-button>
      <el-button
        @click="submitForm('ruleForm'),changBg(1)"
        size="small"
        :class="{changBg:ischange == 1}"
        type="primary"
        v-if="haveBtn('supplier:edit')"
      >{{$t('permission.confirm')}}</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { findSupplier, updateSupplier, getByName } from "@/api/supplier";
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
    const validateCode = (rule, value, callback) => {
      getByName({
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
    return {
      ruleForm: {
        code: "",
        name: "",
        address: "",
        phone: "",
        description: "",
      },
      rules: {
        code: [
          { required: true, message: "请输入供应商编码", trigger: "blur" },
          {
            validator: validateCode,
            trigger: "blur",
          },
        ],
        name: [
          { required: true, message: "请输入供应商名称", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        address: [
          { required: true, message: "请输入供应商地址", trigger: "blur" },
        ],
        phone: [
          { required: true, message: "请输入供应商电话", trigger: "blur" },
          {
            min: 11,
            max: 11,
            message: "手机号码长度在11位",
            trigger: ["blur", "change"],
          },
        ],
      },
    };
  },
  mounted() {
    let id = this.$route.query.id;
    this.findSupplier(id);
  },

  methods: {
    findSupplier(id) {
      findSupplier(id).then((res) => {
        this.ruleForm = res.data;
      });
    },

    submitForm(formName) {
      let supplier = {};
      supplier = this.ruleForm;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateSupplier(supplier).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.$router.push("/supplier");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/supplier");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
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
