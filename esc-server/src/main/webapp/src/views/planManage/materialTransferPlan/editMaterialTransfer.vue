<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑物料调货计划</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="内仓" prop="faWarehouse.id" size="small">
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
          <el-form-item label="物料" prop="material.id" size="small">
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
          <el-form-item label="供应商名称" prop="supplier.id" size="small">
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
              :picker-options="pickerDialgoOptions"
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
              @change="chooseTime(ruleForm.requireTime,ruleForm.materialTime)"
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
import { update, findById } from "@/api/materialTransferPlan";
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
      isfreeze: false,
      defaultNode: [],
      ruleForm: {
        faWarehouse: {
          id: "",
          name: "",
        },
        material: {
          id: "",
          name: "",
        },
        supplier: {
          id: "",
          name: "",
        },
        num: "",
        requireTime: "",
        state: "",
        materialTime: "",
      },
      faWarehouseList: [],
      materialList: [],
      supplierList: [],
      rules: {
        faWarehouse: {
          id: { required: true, message: "请输入内仓", trigger: "blur" },
        },
        material: {
          id: { required: true, message: "请输入原材料", trigger: "blur" },
        },
        supplier: {
          id: { required: true, message: "请输入供应商", trigger: "blur" },
        },
        num: [{ required: true, message: "请输入数量", trigger: "blur" },
        { validator: numValidator, trigger: ["blur", "change"] }],
        requireTime: [
          { required: true, message: "请输入需求日期", trigger: "blur" },
        ],
        materialTime: [
          { required: true, message: "请输入需求时间", trigger: "blur" },
        ],
      },
      pickerDialgoOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return time.getTime() <= Date.now() - 24 * 60 * 60 * 1000;
        },
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
    let id = this.$route.query.id;
    this.ruleForm.id = id;
    findById(id).then((res) => {
      this.ruleForm.faWarehouse = res.data.faWarehouse || {
        id: "",
        name: "",
      };
      this.ruleForm.supplier = res.data.supplier || {
        id: "",
        name: "",
      };
      this.ruleForm.material = res.data.material || {
        id: "",
        name: "",
      };
      this.ruleForm.num = res.data.num || "";
      this.ruleForm.requireTime = res.data.requireTime || "";
      this.ruleForm.materialTime = this.ruleForm.requireTime.slice(11, 16);
      this.ruleForm.state = res.data.state;
    });
  },
  methods: {
    getMyDate(str) {
      var oDate = new Date(str),
        oYear = oDate.getFullYear(),
        oMonth = oDate.getMonth() + 1,
        oDay = oDate.getDate(),
        oHour = oDate.getHours(),
        oMin = oDate.getMinutes(),
        oSen = oDate.getSeconds(),
        oTime =
          oYear +
          "-" +
          this.addZero(oMonth) +
          "-" +
          this.addZero(oDay) +
          " " +
          this.addZero(oHour) +
          ":" +
          this.addZero(oMin) +
          ":" +
          this.addZero(oSen);
      return oTime;
    },
    addZero(num) {
      if (parseInt(num) < 10) {
        num = "0" + num;
      }
      return num;
    },
    chooseTime(_Date, _Time, index) {
      if (!_Date && _Time) {
        this.$message({
          message: "请先选择日期",
          type: "error",
        });
        _Time = "";
        return;
      } else if (_Date && _Time) {
        this.verdictTime(_Date, _Time, index);
      }
    },
    verdictTime(_Date, _Time, index) {
      var now = new Date();
      let freezetime =
        this.freezetime < 600 || !this.freezetime
          ? 600
          : Number(this.freezetime);
      let sysFreezetime = freezetime * 1000 + new Date(Date.now()).getTime();
      let chooseTime =
        new Date(this.getMyDate(_Date).slice(0, 11) + "00:00:00").getTime() +
        _Time.slice(0, 2) * 60 * 60 * 1000 +
        _Time.slice(3, 5) * 60 * 1000;
      if (chooseTime < sysFreezetime) {
        this.$message({
          message: "请选择冻结范围外时间",
          type: "error",
        });
        this.isfreeze = true;
        return false;
      } else {
        this.isfreeze = false;
        return true;
      }
    },
    submitForm(formName) {
      if (this.isfreeze) {
        this.$message({
          message: "请选择冻结时间段外的时间",
          type: "error",
        });
        return;
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.ruleForm.requireTime =
            parseTime(this.ruleForm.requireTime).substr(0, 11) +
            this.ruleForm.materialTime +
            ":00";
          update(this.ruleForm).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
                type: "success",
              });
              this.$router.push("/materialTransfer");
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
@import "~@/styles/sroll.scss";
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
