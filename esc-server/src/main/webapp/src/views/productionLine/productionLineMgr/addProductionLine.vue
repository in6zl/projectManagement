<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增产线</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="产线名称" prop="name" size="small">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="产线位置" prop="address" size="small">
            <el-input v-model="ruleForm.address"></el-input>
          </el-form-item>
          <el-form-item label="所属车间" prop="chejian" size="small">
            <el-select
              v-model="ruleForm.chejian"
              :placeholder="$t('main.placeInsert')"
              style="width:100%"
              size="small"
            >
              <el-option
                v-for="item in chejians"
                :key="item.name"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="负责人" prop="leader" size="small">
            <el-input v-model="ruleForm.leader"></el-input>
          </el-form-item>
          <el-form-item label="负责人手机" prop="leaderPhone" size="small">
            <el-input v-model="ruleForm.leaderPhone"></el-input>
          </el-form-item>
          <el-form-item label="内仓" prop="warehouse" size="small">
            <el-select
              v-model="ruleForm.warehouse"
              :placeholder="$t('main.placeInsert')"
              style="width:100%"
              size="small"
            >
              <el-option
                v-for="item in options"
                :key="item.name"
                :label="item.name"
                :value="item.id"
              ></el-option>
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
import request from "@/utils/request";
import { add, findProductionLine, getByName } from "@/api/productLine";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { listWorkshop } from "@/api/workshop";
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
      options: [],
      chejians: [],
      ruleForm: {
        name: "",
        address: "",
        chejian: "",
        leader: "",
        leaderPhone: "",
        warehouse: "",
      },
      lines: [],
      rules: {
        name: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 10 个字符",
            trigger: "blur",
          },
          {
            validator: validateName, // 自定义验证
            trigger: "blur",
          },
        ],
        chejian: [
          {
            required: true,
            message: "请输入所属车间",
            trigger: "blur",
          },
        ],
        leader: [{ required: true, message: "请输入负责人", trigger: "blur" }],
        leaderPhone: [
          { required: true, message: "请输入负责人手机", trigger: "blur" },
        ],
        warehouse: [
          { required: true, message: "请选择内仓数据", trigger: "blur" },
        ],
        leaderPhone: [
          { required: true, message: "请输入手机号码", trigger: "blur" },
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change",
          },
        ],
      },
    };
  },
  mounted() {
    this.initWareHouseData();
    listWorkshop({}).then((res) => {
      this.chejians = res.data;
    });
  },
  methods: {
    initWareHouseData() {
      getFaWarehouseList({ type: 1 }).then((res) => {
        console.info(res);
        this.options = res.data;
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          //数据填充
          /**
           *
           */
          add({
            name: this.ruleForm.name,
            address: this.ruleForm.address,
            chejian: {
              id: this.ruleForm.chejian,
            },
            leader: this.ruleForm.leader,
            leaderPhone: this.ruleForm.leaderPhone,
            warehouse: {
              id: this.ruleForm.warehouse,
            },
          }).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.$router.push("/productionLineMgr");
            } else {
              this.$message({
                message: "添加失败",
                type: "error",
              });
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
      this.$router.push("/productionLineMgr");
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
