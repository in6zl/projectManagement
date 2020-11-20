<template>
  <div>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0">{{$t('resource.editResource')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="120px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('resource.resourceName')" prop="name" size="small">
            <el-input v-model="ruleForm.name" disabled></el-input>
          </el-form-item>
          <el-form-item :label="$t('resource.title')" prop="title" size="small">
            <el-input v-model="ruleForm.title" disabled></el-input>
          </el-form-item>
          <el-form-item :label="$t('resource.url')" prop="url" size="small">
            <el-input v-model="ruleForm.url" disabled></el-input>
          </el-form-item>
          <el-form-item :label="$t('resource.permission')" prop="permission" size="small">
            <el-input v-model="ruleForm.permission" disabled></el-input>
          </el-form-item>
          <el-form-item :label="$t('resource.icon')" size="small">
            <el-input v-model="ruleForm.icon"></el-input>
          </el-form-item>
          <el-form-item :label="$t('resource.type')" prop="type" size="small">
            <el-select
              v-model="ruleForm.type"
              :placeholder="$t('product.select')"
              size="small"
              style="width:100%"
              disabled
            >
              <el-option v-for="item in types" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            :label="$t('resource.parent')"
            size="small"
            v-if="ruleForm.type == 0 && ruleForm.parentId != 0"
          >
            <el-select v-model="ruleForm.parentId" :placeholder="$t('product.select')" size="small">
              <el-option v-for="item in parentResources" :label="item.title" :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="toIndex" size="small">{{$t('permission.cancel')}}</el-button>
      <el-button
        @click="submitForm('ruleForm'),changBg(1),getCheckedNodes()"
        size="small"
        type="primary"
      >{{$t('permission.confirm')}}</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import {
  updateResource,
  getResource,
  getResourceByParentId,
} from "@/api/resource";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  mixins: [btn],
  components: {
    submit,
    con,
  },
  data() {
    return {
      id: this.$route.query.id,
      parentResources: [],
      ruleForm: {
        name: "",
        title: "",
        permission: "",
        type: "",
        url: "",
        icon: "",
        parentId: "",
      },
      types: [
        {
          label: "菜单",
          value: 0,
        },
        {
          label: "按钮",
          value: 1,
        },
      ],
      data: [],
      roles: [],
      flag: false,
      add: false,
      resource: {
        name: "",
        type: "",
        url: "",
        icon: "",
        permission: "",
        level: "",
        sort: "",
        parentId: "",
      },
      addId: "",
      rules: {
        name: [{ required: true, message: "请输入角色名称", trigger: "blur" }],
        type: [
          { required: true, message: "请选择是否隐藏资源", trigger: "blur" },
        ],
        url: [{ required: true, message: "请输入资源地址", trigger: "blur" }],
      },
    };
  },
  mounted() {
    getResourceByParentId("0").then((res) => {
      this.parentResources = res.data;
    });
    getResource(this.id).then((res) => {
      this.ruleForm = res.data;
      console.log("data:" + this.ruleForm);
      debugger;
    });
  },
  created() {},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateResource(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "修改成功",
                type: "success",
              });
              this.$router.push("/resourceManage");
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
      this.$router.push("/resourceManage");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addResource.scss";
@import "~@/styles/sroll.scss";
.tree {
  width: 100%;
  height: 345px;
  border-radius: 4px;
  border: 1px solid rgba(230, 230, 230, 1);
  overflow-y: auto;
}
.main >>> .el-textarea__inner {
  height: 88px;
}
.operation {
  width: 100%;
  display: flex;
  flex-direction: row-reverse;
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
.el-select {
  width: 100%;
}
</style>
