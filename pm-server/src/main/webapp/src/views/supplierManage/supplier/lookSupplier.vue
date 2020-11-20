<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div class="subtit" style="margin-bottom:16px">{{$t('route.lookSupplier')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('supMana.supplier.name')" prop="name" size="small">
            <el-input v-model="ruleForm.name" readonly></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.address')" prop="address" size="small">
            <el-input v-model="ruleForm.address" readonly></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.phone')" prop="phone" size="small">
            <el-input v-model="ruleForm.phone" readonly></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.supplier.description')" size="small">
            <el-input v-model="ruleForm.description" readonly></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { findSupplier, updateSupplier } from "@/api/supplier";
import lef from "@/components/common/l_icon";
import con from "@/components/common/content";

export default {
  components: {
    lef,
    con,
  },
  mixins: [btn],
  data() {
    return {
      ruleForm: {
        name: "",
        address: "",
        phone: "",
        description: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入供应商名称", trigger: "blur" },
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
      this.$refs[formName].resetFields();
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
</style>
