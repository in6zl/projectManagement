<template>
  <div>
    <lef top='15'></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0">新增用户</div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="username" size="small">
            <el-input v-model="ruleForm.username" />
          </el-form-item>
          <el-form-item label="密码" prop="password" size="small">
            <el-input v-model="ruleForm.password" />
          </el-form-item>
          <el-form-item label="真实姓名" prop="realName" size="small">
            <el-input v-model="ruleForm.realName" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email" size="small">
            <el-input v-model="ruleForm.email" />
          </el-form-item>
          <el-form-item label="手机号码" prop="phone" size="small">
            <el-input v-model="ruleForm.phone" />
          </el-form-item>
          <el-form-item label="地址" prop="address" size="small">
            <el-input v-model="ruleForm.address" placeholder="输入详细地址" size="small" />
          </el-form-item>
          <!--      <el-form-item label="用户类型" prop="type" size="small">-->
          <!--        <el-select v-model="ruleForm.type" placeholder="请选择" @change="getSupplier" style="width:100%">-->
          <!--          <el-option-->
          <!--                  v-for="item in types"-->
          <!--                  :key="item.value"-->
          <!--                  :label="item.name"-->
          <!--                  :value="item.value"/>-->
          <!--        </el-select>-->
          <!--      </el-form-item>-->
          <el-form-item label="供应商" prop="supplierId" size="small" v-if="isSupplier">
            <el-select v-model="ruleForm.supplierId" placeholder="请选择" style="width:100%">
              <el-option
                v-for="item in suppliers"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="组织" prop="organizationId" size="small">
            <el-select
              v-model="ruleForm.organizationId"
              placeholder="请选择"
              @change="getRolesByOrg"
              style="width:100%"
            >
              <el-option
                v-for="item in organizationOps"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="角色" prop="roleIds" size="small">
            <el-select v-model="ruleForm.roleIds" placeholder="请选择" multiple style="width:100%">
              <el-option
                v-for="item in orgRoles"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button size="small" :class="{changBg:ischange == 2}" @click="toIndex">取消</el-button>
      <el-button
        size="small"
        :class="{changBg:ischange == 1}"
        @click="submitForm('ruleForm'),changBg(1)"
        type="primary"
      >确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { createUser } from "@/api/user";
import { getOrganization } from "@/api/organization";
import { getRolesByOrg } from "@/api/role";
import { getSupplier } from "@/api/supplier";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    lef,
    con,
  },
  mixins: [btn],
  data() {
    return {
      isSupplier: false,
      suppliers: [],
      types: [
        { name: "普通用户", value: 0 },
        { name: "供应商", value: 2 },
      ],
      orgRoles: [],
      organizationOps: [],
      ruleForm: {
        username: "",
        password: "",
        realName: "",
        email: "",
        phone: "",
        address: "",
        roleIds: [],
        enable: 1,
        type: 1,
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 8,
            max: 16,
            message: "长度在 8 到 16 个字符",
            trigger: "blur",
          },
        ],
        email: [
          {
            min: 4,
            max: 30,
            message: "长度在 4 到 30 个字符",
            trigger: "blur",
          },
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"],
          },
        ],
        realName: [
          { required: true, message: "请输入真实姓名", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
        ],
        phone: [
          { required: true, message: "请输入手机号码", trigger: "blur" },
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change",
          },
        ],
        // type: [
        //   { required: true, message: "请选择用户类型", trigger: "blur" }
        // ],
        supplierId: [
          { required: true, message: "请选择供应商", trigger: "blur" },
        ],
        address: [
          {
            min: 3,
            max: 40,
            message: "长度在 3 到 40 个字符",
            trigger: "blur",
          },
        ],
        roleIds: {
          required: true,
          message: "请选择角色类型",
          trigger: "blur",
        },
        organizationId: [
          { required: true, message: "请选择组织", trigger: "blur" },
        ],
      },
    };
  },
  mounted() {
    getOrganization({}).then((res) => {
      this.organizationOps = res.data;
      console.log("组织:" + this.organizationOps);
    });
    // getSupplier({}).then(res => {
    //   this.suppliers = res.data;
    //   console.log('供应商:' + this.organizationOps)
    // })
  },
  methods: {
    // getSupplier(value) {
    //   if (value == 2) {
    //     this.isSupplier = true;
    //   } else if (value == 0) {
    //     this.isSupplier = false;
    //   }
    // },
    getRolesByOrg(organizationId) {
      getRolesByOrg(organizationId).then((res) => {
        this.ruleForm.roleIds = [];
        this.orgRoles = res.data;
      });
    },
    submitForm(formName) {
      const that = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.username = this.ruleForm.username.trim();
          createUser(this.ruleForm).then((res) => {
            if (res.code === "0") {
              that.$router.push("/userManage");
            } else {
              this.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    toIndex() {
      this.$router.push("/userManage");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
.box {
  padding: 0;
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
