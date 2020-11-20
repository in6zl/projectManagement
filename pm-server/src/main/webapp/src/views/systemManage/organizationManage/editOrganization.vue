<template>
  <div>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑组织</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="组织名称" prop="name" size="small">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <!--      <el-form-item label="类型" prop="type" size="small">-->
          <!--        <el-select style="display: block" v-model="ruleForm.type" filterable placeholder="请选择" disabled="true">-->
          <!--          <el-option-->
          <!--            v-for="item in typeList"-->
          <!--            :key="item.id"-->
          <!--            :label="item.name"-->
          <!--            :value="item.id"-->
          <!--          />-->
          <!--        </el-select>-->
          <!--      </el-form-item>-->
          <el-form-item label="地址" prop="address" size="small">
            <el-input v-model="ruleForm.address"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" prop="phone" size="small">
            <el-input v-model="ruleForm.phone"></el-input>
          </el-form-item>
          <el-form-item label="组织角色" prop="roles" size="small">
            <el-select
              style="display: block"
              v-model="ruleForm.roles"
              filterable
              placeholder="请选择"
              multiple
            >
              <el-option
                v-for="item in allRoles"
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
      <el-button @click="toIndex" size="small" :class="{changBg:ischange == 2}">取消</el-button>
      <el-button
        @click="submitForm('ruleForm'),changBg(1)"
        size="small"
        :class="{changBg:ischange == 1}"
        type="primary"
      >确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import {
  updateOrganization,
  findOrganization,
  getOrganization,
  findByName,
} from "@/api/organization";
import { getRolesExclusionAdmin } from "@/api/role";
import con from "@/components/common/content";
import submit from "@/components/common/submitBtn";
export default {
  components: {
    submit,
    con,
  },
  mixins: [btn],
  data() {
    const validateName = (rule, value, callback) => {
      findByName({
        id: this.$route.query.id,
        name: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error("组织名称重复"));
        } else {
          return callback();
        }
      });
    };
    return {
      typeList: [
        { id: 0, name: "组织" },
        { id: 1, name: "供应商" },
      ],
      ruleForm: {
        name: "",
        type: "",
        address: "",
        phone: "",
        roles: [],
      },
      allRoles: [],
      name: "",
      rules: {
        name: [
          { required: true, message: "请输入组织名称", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
          {
            validator: validateName, // 自定义验证
            trigger: "blur",
          },
        ],
        // type: [
        //   { required: true, message: "请选择类型", trigger: "blur" }
        // ],
        address: [
          { required: true, message: "请输入地址", trigger: "blur" },
          {
            min: 3,
            max: 40,
            message: "长度在 3 到 40 个字符",
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
        roles: [{ required: true, message: "请选择组织角色", trigger: "blur" }],
      },
    };
  },
  methods: {
    getRolesExclusionAdmin() {
      getRolesExclusionAdmin().then((res) => {
        this.allRoles = res.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let organization = {};
          let saveOrganization = {};
          organization.id = this.ruleForm.id;
          organization.name = this.ruleForm.name;
          // organization.type = this.ruleForm.type;
          organization.address = this.ruleForm.address;
          organization.phone = this.ruleForm.phone;
          saveOrganization.organization = organization;
          saveOrganization.roles = this.ruleForm.roles;
          updateOrganization(saveOrganization).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.toIndex();
            } else {
              this.$message.error(res.message);
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
    toIndex() {
      this.$router.push("/organizationManage");
    },
  },
  mounted() {
    this.getRolesExclusionAdmin();
    let id = this.$route.query.id;
    findOrganization(id).then((res) => {
      this.ruleForm = res.data;
    });
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
