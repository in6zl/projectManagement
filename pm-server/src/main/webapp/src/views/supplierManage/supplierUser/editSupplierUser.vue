<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑用户</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="用户名" prop="username" size="small">
            <el-input v-model="ruleForm.username" />
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
          <el-form-item label="供应商" prop="supplierId" size="small" v-if="ruleForm.type == 2">
            <el-select v-model="ruleForm.supplierId" placeholder="请选择" style="width:60%">
              <el-option
                v-for="item in suppliers"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button @click="toIndex" size="small" :class="{changBg:ischange == 2}">取消</el-button>
            <el-button
              @click="submitForm('ruleForm'),changBg(1)"
              size="small"
              type="primary"
              :class="{changBg:ischange == 1}"
            >确定</el-button>
          </el-form-item>
        </el-form>
      </div>
    </con>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { updateUser, findUser, getUserList } from "@/api/user";
import { getOrganization } from "@/api/organization";
import { getRolesByOrg } from "@/api/role";
import { getSupplier } from "@/api/supplier";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
export default {
  mixins: [btn],
  components: {
    con,
    lef,
  },
  data() {
    const validateName = (rule, value, callback) => {
      let flag = false;
      this.users.forEach((user) => {
        if (user.username === value&&this.name!=value) {
          flag = true;
        }
      });
      if (flag) {
        return callback(new Error("用户名称重复"));
      } else {
        return callback();
      }
    };
    return {
      types: [
        { name: "普通用户", value: 0 },
        { name: "供应商", value: 2 },
      ],
      suppliers: [],
      users: [],
      name: "",
      isAdmin: false,
      organizationOps: [],
      orgRoles: [],
      ruleForm: {
        id: "",
        username: "",
        password: "",
        realName: "",
        email: "",
        phone: "",
        address: "",
        type: 2,
        roleIds: [],
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
          {
            validator: validateName,
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
        address: [
          {
            min: 3,
            max: 40,
            message: "长度在 3 到 40 个字符",
            trigger: "blur",
          },
        ],
        roleIds: [
          {
            required: true,
            message: "请选择角色类型",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    let id = this.$route.query.id;
    if (id === "1") {
      this.isAdmin = true;
    }
    this.getUser(id);

    getSupplier({}).then((res) => {
      this.suppliers = res.data;
    });
    getUserList({ type: 2 }).then((res) => {
      this.users = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      let that = this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.username = this.ruleForm.username.trim();
          updateUser(this.ruleForm).then((res) => {
            if (res.code === "0") {
              that.$router.push("/supplierUser");
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
      this.$router.push("/supplierUser");
    },
    toIndex() {
      this.$router.push("/supplierUser");
    },
    getUser(id) {
      findUser(id)
        .then((res) => {
          if (res.code === "0") {
            this.orgRoles = res.data.roles;
            this.ruleForm = res.data;
            this.name = res.data.username;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
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
.el-input {
  width: 60% !important;
}
</style>