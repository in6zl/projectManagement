<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top" style="width:70%;over-flow:hidden">
        <div style="margin:0 0 16px 0;font-weight:700">新增角色</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="角色名称" prop="name" size="small">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="角色权限" prop="permission" size="small">
            <div class="tree">
              <el-tree
                :data="data"
                show-checkbox
                node-key="id"
                :default-checked-keys="defaultNode"
                ref="tree"
              ></el-tree>
            </div>
          </el-form-item>

          <el-form-item label="角色描述" prop="description">
            <el-input
              type="textarea"
              v-model="ruleForm.description"
              :rows="5"
              resize="none"
              placeholder="请输入角色描述"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="toIndex" size="small" :class="{changBg:ischange == 2}">取消</el-button>
      <el-button
        @click="submitForm('ruleForm'),changBg(1),getCheckedNodes()"
        size="small"
        type="primary"
      >确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { createRole, getResourceTree } from "@/api/role";
import { generateTitle } from "@/utils/i18n";
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
      defaultNode: [],
      ruleForm: {
        name: "",
        permission: "",
        description: "",
      },
      data: [],
      rules: {
        name: [
          { required: true, message: "请输入角色名称", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在 1 到 10 个字符",
            trigger: "blur",
          },
        ],
        description: [
          {
            min: 3,
            max: 40,
            message: "长度在 3 到 40 个字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    getResourceTree().then((res) => {
      this.data = this.filterChild(res.data);
      this.data.map((item) => {
        if (item.selected) {
          this.defaultNode.push(item.id);
        }
      });
    });
  },
  methods: {
    generateTitle,
    getCheckedNodes() {
      let checkedNodes = this.$refs.tree.getCheckedNodes(false, true);
      let checkedNodeId = [];
      checkedNodes.map((item) => checkedNodeId.push({ id: item.id }));
      return checkedNodeId;
    },
    filterChild(data) {
      if (data.length == 0) return;
      data.map((item) => {
        item.label = this.$t("route." + item.name);
        if (item.children && item.children.length > 0) {
          this.filterChild(item.children);
        }
      });
      return data;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        this.ruleForm.resources = this.getCheckedNodes();
        if (valid) {
          createRole(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/roleManage");
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
      this.$router.push("/roleManage");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
@import "~@/styles/sroll.scss";
.tree {
  width: 100%;
  height: 250px;
  border-radius: 4px;
  border: 1px solid rgba(230, 230, 230, 1);
  overflow-y: auto;
  & > .el-tree {
    margin: 16px;
  }
}
.app-main {
  overflow: hidden !important;
}
.box {
  height: calc(100vh - 50px);
  padding-bottom: 100px;
  background: white;
}
.main >>> .el-textarea__inner {
  height: 88px;
}
.el-scrollbar {
  position: inherit;
}
.subBtn {
  margin-top: -10px;
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
