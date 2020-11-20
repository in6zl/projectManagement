<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top"  style="width:70%">
        <div style="padding:16px;font-weight:700">编辑角色</div>
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
              <el-tree :data="data"
                      :default-checked-keys="defaultNode" includeHalfChecked show-checkbox node-key="id" ref="tree"></el-tree>
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
    <submit class="subBtn">
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
import { updateRole, findRole, getResourceTree } from "@/api/role";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
    lef
  },
  mixins: [btn],
  data() {
    return {
      ruleForm: {
        name: "",
        permission: "",
        description: "",
      },
      data: [],
      resourceIds: [],
      defaultNode: [],
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
  mounted() {
    let id = this.$route.query.id
    this.find(id)
    getResourceTree().then((res) => {
      this.data = this.filterChild(res.data);
      this.data = res.data
    });
  },

  methods: {
    /**
     * 获取选中和半选中的节点
     * @returns {[]}
     */
    getCheckedNodes() {
      let checkedNodes = this.$refs.tree.getCheckedNodes(false, true);
      let checkedNodeId = [];
      checkedNodes.map((item) => checkedNodeId.push({ id: item.id }));

      return checkedNodeId;
    },
    filterChild(data) {
      if (data.length == 0) return;
      data.map((item) => {
        item.label = this.$t("route." + item.name)
        if (item.children  && item.children.length > 0) {
          this.filterChild(item.children)
        } else if(this.resourceIds.indexOf(item.id) !== -1) {
          this.defaultNode.push(item.id)
        }
      });
      return data;
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.resources = this.getCheckedNodes();
          updateRole(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
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
      this.$router.push("/roleManage");
    },
    find(id) {
      findRole(id).then((res) => {
        if (res.code === "0") {
          let { id, name, description, resources } = res.data
          this.ruleForm = { id, name, description, resources }
          this.resourceIds = resources.map(item => item.id)
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
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
.box {
  height: calc(100vh - 50px);
  padding-bottom: 100px;
  background: white;
}
.main >>> .el-textarea__inner {
  height: 88px;
}
.subBtn {
  margin-top: -10px;
}
</style>
