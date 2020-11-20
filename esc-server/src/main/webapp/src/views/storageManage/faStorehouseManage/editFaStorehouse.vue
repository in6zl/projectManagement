<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑库位</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="150px"
          class="demo-ruleForm"
        >
          <el-form-item label="库位编码" prop="code" size="small">
            <el-input v-model="ruleForm.code" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="内仓" prop="warehouseId" size="small">
            <el-select
              v-model="ruleForm.warehouseId"
              @change="findFaStorageBinList()"
              style="width:100%"
            >
              <el-option
                v-for="item in ruleForm.warehouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="货架编码" prop="shelvesId" size="small">
            <el-select v-model="ruleForm.shelvesId" style="width:100%" @change="getLayers">
              <el-option
                v-for="item in ruleForm.faStorageBinList"
                :key="item.id"
                :label="item.code"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="货架总层数" size="small">
            <el-input v-model="layers" style="width:100%" disabled />
          </el-form-item>
          <el-form-item label="库位所在层数" prop="rowNum" size="small">
            <el-input v-model="ruleForm.rowNum" style="width:100%" />
          </el-form-item>
          <el-form-item label="库位所在列数" prop="columnNum" size="small">
            <el-input v-model="ruleForm.columnNum" style="width:100%" />
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button @click="toFaStorehouseManage()" size="small">取消</el-button>
      <el-button @click="submitForm('ruleForm')" type="primary" size="small">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import { getFaWarehouseList } from "@/api/faWareHouse";
import lef from "@/components/common/l_icon";

import {
  addFaStorageBin,
  findFaStorageBin,
  updateFaStorageBin,
  getFaStorageBinList,
} from "@/api/faStorageBin";
import {
  findFaStorehouse,
  updateFaStorehouse,
  getByName,
} from "@/api/faStorehouse";
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
        id: this.$route.query.id,
        code: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    const layerNumValidator = (rule, value, callback) => {
      if (!/^[1-9][0-9]*$/.test(value)) {
        return callback(new Error("数量必须是数字"));
      } else if (parseInt(value) > parseInt(this.layers)) {
        return callback(new Error("不能超过货架的总层数"));
      } else {
        return callback();
      }
    };
    return {
      ruleForm: {
        warehouseId: "",
        code: "",
        warehouseList: [],
        faStorageBinList: [],
        rowNum: "",
        columnNum: "",
        shelvesId: "",
      },
      layers: "",
      rules: {
        warehouseId: [
          { required: true, message: "请选择内仓", trigger: "blur" },
        ],
        shelvesId: [
          { required: true, message: "请选择货架", trigger: "blur" },
          
        ],
        code: [
          { required: true, message: "请输入编码", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        rowNum: [
          { required: true, message: "请输入库位所在层数", trigger: "blur" },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          { validator: layerNumValidator, trigger: ["blur", "change"] },
        ],
        columnNum: [
          {
            required: true,
            message: "请输入库位所在列数",
            trigger: "blur",
          },
          { max: 16, message: "最大长度为16个字符", trigger: "blur" },
          
          {
            pattern: /^[1-9][0-9]*$/,
            message: "请输入数字",
            trigger: "change",
          },
        ],
      },
    };
  },
  mounted() {
    getFaWarehouseList(new Object()).then((res) => {
      let id = this.$route.query.id;
      findFaStorehouse(id).then((res1) => {
        res1.data.warehouseList = res.data;
        getFaStorageBinList({ warehouseId: res1.data.warehouseId }).then(
          (res2) => {
            res1.data.faStorageBinList = res2.data;
            this.ruleForm = res1.data;
            this.getLayers();
          }
        );
      });
    });
  },
  methods: {
    findFaStorageBinList() {
      getFaStorageBinList({ warehouseId: this.ruleForm.warehouseId }).then(
        (res) => {
          this.ruleForm.faStorageBinList = res.data;
          this.ruleForm.shelvesId = "";
          this.ruleForm.warehouseList.forEach((v) => {
            if (this.ruleForm.warehouseId == v.id) {
              this.ruleForm.businessId = v.type;
            }
          });
        }
      );
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          updateFaStorehouse(this[formName]).then((res) => {
            this.$message({
              message: "修改成功",
              type: "success",
            });
            this.$router.push("/faStorehouseManage");
          });
        } else {
          return false;
        }
      });
    },
    getLayers() {
      findFaStorageBin(this.ruleForm.shelvesId).then((res) => {
        this.layers = res.data.layers;
      });
    },
    toFaStorehouseManage() {
      this.$router.push("/faStorehouseManage");
    },
    getGetFaWarehouseList() {
      getFaWarehouseList(new Object()).then((res) => {
        this.ruleForm.warehouseList = res.data;
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.tree {
  width: 100%;
  height: 345px;
  border-radius: 4px;
  border: 1px solid rgba(230, 230, 230, 1);
  overflow: hidden;
  & > .el-tree {
    margin: 16px;
  }
}
.main >>> .el-textarea__inner {
  height: 88px;
}
.main >>> .el-button {
  margin-bottom: 10px;
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