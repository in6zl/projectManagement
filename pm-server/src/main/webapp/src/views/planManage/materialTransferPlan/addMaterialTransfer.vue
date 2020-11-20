<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">新增物料调货计划</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="内仓" prop="faWarehouse" size="small">
            <el-select
              v-model="ruleForm.faWarehouse.id"
              placeholder="请选择"
              fliterable
              clearable
              style="width:100%"
            >
              <el-option
                v-for="item in faWarehouseList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="物料" prop="material" size="small">
            <el-select
              v-model="ruleForm.material.id"
              placeholder="请选择"
              fliterable
              clearable
              style="width:100%"
            >
              <el-option
                v-for="item in materialList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="供应商名称" prop="supplier" size="small">
            <el-select
              v-model="ruleForm.supplier.id"
              placeholder="请选择"
              fliterable
              clearable
              style="width:100%"
            >
              <el-option
                v-for="item in supplierList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="需求数量" prop="num" size="small">
            <el-input v-model="ruleForm.num" style="width:100%"></el-input>
          </el-form-item>
          <el-form-item label="需求日期" prop="requireTime" size="small">
            <el-date-picker
              v-model="ruleForm.requireTime"
              type="date"
              placeholder="选择日期"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="需求时间" prop="materialTime" size="small">
            <el-time-select
              v-model="ruleForm.materialTime"
              placeholder="选择日期"
              style="width:100%"
              :picker-options="{
          start: '00:00',
          step: '00:10',
          end: '23:59'
        }"
            ></el-time-select>
          </el-form-item>
        </el-form>
      </div>
    </con>
    <submit>
      <el-button
        @click="resetForm('ruleForm'),changBg(2)"
        size="small"
        :class="{changBg:ischange == 2}"
      >取消</el-button>
      <el-button
        @click="submitForm('ruleForm'),changBg(1),getCheckedNodes()"
        size="small"
        :class="{changBg:ischange == 1}"
        type="primary"
      >确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getFaWarehouseList } from "@/api/faWareHouse.js";
import { findMaterialListData } from "@/api/material.js";
import { getOrganization } from "@/api/organization.js";
import { save } from "@/api/materialTransferPlan";
import { parseTime } from "@/utils/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import { getSupplier } from "@/api/supplier";
import lef from "@/components/common/l_icon";

export default {
  components: {
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    const numValidator = (rule, value, callback) => {
      if (!/^[1-9]+[0-9]*$/.test(value)) {
        return callback(new Error("数量必须是数字"));
      } else {
        return callback();
      }
    };
    return {
      defaultNode: [],
      ruleForm: {
        faWarehouse: {
          id: "",
        },
        material: {
          id: "",
        },
        supplier: {
          id: "",
        },
        num: "",
        requireTime: "",
        state: 0,
        materialTime: "",
      },
      faWarehouseList: [],
      materialList: [],
      supplierList: [],
      rules: {
        faWarehouse: [
          { required: true, message: "请输入内仓", trigger: "blur" },
        ],
        material: [
          { required: true, message: "请输入原材料", trigger: "blur" },
        ],
        supplier: [
          { required: true, message: "请输入供应商", trigger: "blur" },
        ],
        num: [{ required: true, message: "请输入数量", trigger: "blur" },
        { validator: numValidator, trigger: ["blur", "change"] }],
        requireTime: [
          { required: true, message: "请输入需求时间", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    getFaWarehouseList({ type: 1 }).then((res) => {
      this.faWarehouseList = res.data;
    });
    findMaterialListData().then((res) => {
      this.materialList = res.data;
    });
    getSupplier().then((res) => {
      this.supplierList = res.data;
    });
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.requireTime =
            parseTime(this.ruleForm.requireTime).substr(0, 11) +
            this.ruleForm.materialTime +
            ":00";
          this.ruleForm.state = 1;
          save(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push({
                path: "/materialTransfer",
                query: {
                  name: "second",
                },
              });
            }
          });
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/materialTransfer");
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
  overflow-y: auto;
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
