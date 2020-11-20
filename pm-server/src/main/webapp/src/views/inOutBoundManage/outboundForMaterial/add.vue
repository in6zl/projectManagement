<template>
  <div class="saleOrder">
    <lef :top="15"></lef>
    <div class="main">
      <div>新增物料出库单</div>
      <el-form ref="ruleForm" :model="ruleForm" :inline="true">
        <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
          <el-input v-model="ruleForm.outOperator" />
        </el-form-item>
        <el-form-item label="内仓" prop="faWarehouse" size="small">
          <el-select placeholder="请选择" fliterable clearable v-model="ruleForm.faWarehouse.id">
            <el-option
              v-for="item in faWarehouseList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.outTime')" prop="outTime" size="small">
          <el-date-picker
            v-model="ruleForm.outTime"
            type="date"
            format="yyyy 年 MM 月 dd 日"
            value-format="yyyy-MM-dd HH:mm:ss"
          />
        </el-form-item>
        <el-form-item :label="$t('inoutBound.isFinished')" prop="isFinished" size="small">
          <el-select v-model="ruleForm.isFinished" filterable placeholder="请选择" value>
            <el-option
              v-for="item in isFinishedList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
      </el-form>
      <div style="float:right;padding: 0 16px 16px 0">
        <el-button
          @click="resetForm('materialForm'),changBg(2)"
          size="small"
          :class="{changBg:ischange == 2}"
        >取消</el-button>
        <el-button
          @click="submitForm('materialForm'),changBg(1)"
          size="small"
          :class="{changBg:ischange == 1}"
        >确定</el-button>
      </div>
    </div>

    <div class="addResult">
      <div>新增出库单详情</div>
      <el-form
        label-width="50"
        ref="itemForm"
        :inline="true"
        :rules="itemRules"
        :model="itemForm"
        style="padding-left:16px"
      >
        <el-form-item :label="$t('inoutBound.materialName')" prop="materialId" size="small">
          <el-select v-model="ruleForm.materialId" filterable placeholder="请选择" value>
            <el-option
              v-for="item in materialList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
          <el-input v-model="ruleForm.num" />
        </el-form-item>
        <el-form-item :label="$t('inoutBound.unitName')" prop="unitId" size="small">
          <el-select v-model="ruleForm.unitId" filterable placeholder="请选择" value>
            <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.binName')" prop="binId" size="small">
          <el-select v-model="ruleForm.binId" filterable placeholder="请选择" value>
            <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">
          <el-input v-model="ruleForm.unitNum" />
        </el-form-item>

        <el-form-item style="float:right;" size="small">
          <el-button type="primary" @click="addItem('itemForm')">添加产品</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="roleManageTable">
      <el-table
        :data="tableData"
        style="width: 100%"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
        <el-table-column width="240" label="物料名" prop="material.name" :show-overflow-tooltip="true"></el-table-column>

        <el-table-column prop="num" label="数量" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="unit.code" label="单位名称" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="batch" label="库位名" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="getTime" label="换算数量" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column label="操作" fixed="right" align="right">
          <template slot-scope="scope">
            <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import lef from "@/components/common/l_icon";

import { getFaWarehouseList } from "@/api/faWareHouse.js";
export default {
  mixins: [btn],
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      faWarehouseList: [],
      tableData: [],
      materialList: [],
      unitList: [],
      binList: [],
      tableHeight: window.innerHeight - 430,
      ruleForm: {
        materialRequirePlanId: undefined,
        outOperator: undefined,
        faWarehouse: {
          id: "",
        },
        outTime: undefined,
        isFinished: undefined,
      },
      itemForm: {
        materialId: undefined,
        num: undefined,
        binId: undefined,
        unitId: undefined,
        unitNum: undefined,
      },
      materialRequirePlanList: [{ id: "1", businessId: "1" }],
      isFinishedList: [
        { id: 0, name: "未完成" },
        { id: 1, name: "已完成" },
      ],
      rules: {
        materialRequirePlanId: [
          { required: true, message: "请输入", trigger: "blur" },
        ],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        receiver: [{ required: true, message: "请输入", trigger: "blur" }],
        isFinished: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      itemRules: {
        materialId: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unitId: [{ required: true, message: "请输入", trigger: "blur" }],
        binId: [{ required: true, message: "请输入", trigger: "blur" }],
      },
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/materialRequisitionMgr");
    },
    submitForm(formName) {
      this.$refs[formName].validate((validate) => {
        let data = {};
        data.code = this.materialForm.code;
        data.description = this.materialForm.description;
        data.isCompile = this.materialForm.isCompile;
        data.item = this.tableData;
        if (data.item.length === 0) {
          this.$message({
            message: "请添加领料单项",
            type: "fail",
          });
          return;
        }
        //insert material requisition
        save(data).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "创建成功",
              type: "success",
            });
            this.$router.push("/materialRequisitionMgr");
            this.$refs[formName].resetFields();
          }
        });
      });
    },
    addItem(formName) {
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let addObj = {
            material: {},
            unit: {
              id: "",
              code: "",
            },
            num: "",
            batch: "",
            getTime: "",
            faWarehouse: "",
          };
          addObj.material = this.materialList.filter(
            (item) => item.id === this.itemForm.material
          )[0];
          addObj.faWarehouse = this.warehouseList.filter(
            (item) => item.id === this.itemForm.faWarehouse
          )[0];
          addObj.unit.id = this.itemForm.unit.id;
          addObj.unit.code = this.itemForm.unit.code;
          addObj.num = this.itemForm.num;
          addObj.batch = this.itemForm.batch;
          addObj.minutes = this.itemForm.minutes
            ? this.itemForm.minutes + ":00"
            : "";
          addObj.getTime =
            parseTime(this.itemForm.getTime).slice(0, 11) +
            this.itemForm.minutes;
          if (!addObj.minutes) {
            this.$message({
              message: "领取时间不能为空",
              type: "error",
            });
            this.itemForm.getTime = "";
            return;
          }
          this.tableData.push(addObj);
          this.$refs[formName].resetFields();
          this.itemForm.minutes = "";
        }
      });
    },
    deleteRow(index, tableData) {
      tableData.splice(index, 1);
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
  },
  mounted() {
    let outOrderData = JSON.parse(localStorage.getItem("outOrderData"));
    getFaWarehouseList({}).then((res) => {
      this.faWarehouseList = res.data;
    });
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/sale.scss";
.addResult >>> .el-form-item:nth-child(1) {
  margin-left: 0 !important;
}
</style>
