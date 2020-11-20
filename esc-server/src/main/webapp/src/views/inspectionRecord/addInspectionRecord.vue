<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增检查记录</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="生产计划项" prop="productionPlanId" size="small">
            <el-select
              v-model="ruleForm.productionPlanItemId"
              filterable
              placeholder="请选择"
              value
              style="width:100%"
              clearable
              size="small"
            >
              <el-option
                v-for="item in productionPlanItemList"
                :key="item.id"
                :label="item.orderNo"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="是否通过" prop="pass" size="small">
            <el-select v-model="ruleForm.pass" style="width:100%">
              <el-option label="是" value="1"></el-option>
              <el-option label="否" value="0"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检查人" prop="examiner" size="small">
            <el-input v-model="ruleForm.examiner" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="url" size="small">
            <el-upload
              style="width:100%"
              class="upload-demo"
              drag
              action="api/common/upload"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或
                <em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="resetForm('ruleForm')" size="small" :class="{changBg:ischange == 2}">取消</el-button>
      <el-button @click="submitForm('ruleForm')" size="small" type="primary">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addInspectionRecord } from "@/api/inspectionRecord";
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
    return {
      productionPlanItemList: [
        {
          value: "zhinan",
          label: "此处二级联动",
          children: [
            {
              value: "shejiyuanze",
              label: "此处二级联动",
            },
            {
              value: "shejiyuanze",
              label: "此处二级联动",
            },
            {
              value: "shejiyuanze",
              label: "此处二级联动",
            },
          ],
        },
        {
          value: "ziyuan",
          label: "此处二级联动",
          children: [
            {
              value: "axure",
              label: "此处二级联动",
            },
          ],
        },
      ],
      ruleForm: {
        productionPlanItemId: "",
        pass: "",
        examiner: "",
        url: "",
      },
      rules: {
        productionPlanItemId: [
          { required: true, message: "请输入生产计划项", trigger: "blur" },
        ],
        pass: [{ required: true, message: "请输入是否通过", trigger: "blur" }],
        examiner: [
          { required: true, message: "请输入检查人", trigger: "blur" },
        ],
        url: [{ required: true, message: "请上传图片", trigger: "blur" }],
      },
    };
  },
  methods: {
    submitForm(formName) {
      let data = {};
      let productionPlanItem = {};
      data.examiner = this.ruleForm.examiner;
      data.pass = this.ruleForm.pass;
      data.url = this.ruleForm.url;
      productionPlanItem.id = this.ruleForm.productionPlanItemId;
      data.productionPlanItem = productionPlanItem;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addInspectionRecord(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/inspectionRecord/inspectionRecord");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/inspectionRecord");
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
.upload-demo /deep/ .el-upload {
  width: 100%;
}
.upload-demo /deep/ .el-upload-dragger {
  width: 100%;
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
