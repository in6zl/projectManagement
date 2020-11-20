<template>
  <div>
    <con>
      <div class="main-top">
        <!--父表单-->

        <div style="padding:16px" class="subtit">{{ $t('route.editOutboundOrderMaterial') }}</div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="100px"
          style="overflow:hidden"
        >
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.code')" prop="code" size="small">
              <el-input v-model="ruleForm.code" maxlength="30" style="width: 95%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
              <el-input v-model="ruleForm.outOperator" maxlength="30" style="width: 95%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="内仓" prop="warehouseId" size="small">
              <el-select
                v-model="ruleForm.warehouseId"
                placeholder="请选择"
                style="width: 95%"
                fliterable
                clearable
                disabled
              >
                <el-option
                  v-for="item in faWarehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.outTime')" prop="outTime" size="small">
              <el-date-picker
                v-model="ruleForm.outTime"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width: 95%"
              />
            </el-form-item>
          </el-col>
        </el-form>
      </div>

      <!--子列表-->
      <div class="table">
        <div class="addTitle">
          <div style="margin-left:16px" class="subtit">新增详情</div>
          <!--添加按钮-->
          <div class="addChild" style="margin-right:16px">
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="openDialog"
              style="margin-bottom:16px"
              type="primary"
            >新增详情</el-button>
          </div>
        </div>
        <el-table :data="tableData" style="width: 100%" :header-cell-style="tableHeaderColor">
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column
            :label="$t('inoutBound.materialName')"
            width="150"
            :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <el-select v-model="scope.row.material" >
                 <el-option v-for="item in materialList" :key="item.id" :label="`${(item.name)} (${(item.storedNumInWarHouse?item.storedNumInWarHouse:0)})`" :value="item" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column :label="$t('inoutBound.num')"  width="150" :show-overflow-tooltip="true" > 
            <template slot-scope="scope">
              <el-input type="text" v-model="scope.row.num" />
            </template>
          </el-table-column>
          <el-table-column :label="$t('inoutBound.unitName')"  width="150" :show-overflow-tooltip="true" >
            <template slot-scope="scope">{{scope.row.material.unitName}}</template>
          </el-table-column>
          <el-table-column
            :label="$t('inoutBound.binName')"
            width="150" :show-overflow-tooltip="true">
          <template slot-scope="scope">
              <el-select v-model="scope.row.bin" :value-key="scope.row.bin.id" >
                 <el-option v-for="item in binList" :key="item.id" :label="item.code" :value="item" />
              </el-select>
            </template>
          </el-table-column>
          <!-- <el-table-column
            prop="unitNum"
            :label="$t('inoutBound.unitNum')"
            width="150"
            :show-overflow-tooltip="true"
          /> -->
          <!------------------------------->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            fixed="right"
            align="right"
          >
            <template slot-scope="scope">
              <el-tooltip content="编辑" placement="top">
                <svg-icon icon-class="edit" @click.native="update(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <!--弹框-->
      <el-dialog title="新增详情" :visible.sync="dialogFormVisible" width="38%">
        <el-form
          ref="ruleFormDetail"
          :model="ruleFormDetail"
          :rules="rulesDetail"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('inoutBound.materialName')" prop="material" size="small">
            <el-select
              v-model="ruleFormDetail.material"
              filterable
              placeholder="请选择"
              style="width: 100%"
              value-key="id"
            >
              <el-option
                v-for="item in materialList"
                :key="item.id"
                :label="item.name"
                :value="item"
              />
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
            <el-input v-model="ruleFormDetail.num" maxlength="11" style="width: 100%" />
          </el-form-item>
          <el-form-item :label="$t('inoutBound.unitName')" prop="unit" size="small">
            <el-select
              v-model="ruleFormDetail.unit"
              filterable
              placeholder="请选择"
              style="width: 100%"
              value-key="id"
            >
              <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item" />
            </el-select>
          </el-form-item>
          <el-form-item :label="$t('inoutBound.binName')" prop="bin" size="small">
            <el-select
              v-model="ruleFormDetail.bin"
              filterable
              placeholder="请选择"
              style="width: 100%"
              value-key="id"
            >
              <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item" />
            </el-select>
          </el-form-item>
          <!-- <el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">
            <el-input v-model="ruleFormDetail.unitNum" maxlength="11" style="width: 100%" />
          </el-form-item> -->

          <el-form-item>
            <el-button size="small" @click="closeDialog">取消</el-button>
            <el-button size="small" type="primary" @click="submitFormDetail('ruleFormDetail')">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </con>
    <submit>
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')">确定</el-button>
    </submit>
  </div>
</template>

<script>
import {
  updateOutboundOrderMaterial,
  getOutboundOrderMaterial,
} from "@/api/outboundOrderMaterial";
import { getFaWarehouseList } from "@/api/faWareHouse.js";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { findMaterialListData ,findListByWaHouse} from "@/api/material";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  components: {
    submit,
    con,
  },
  data() {
    return {
      // --------布局------------
      isUpdate: false,
      tableDataIndex: "",
      dialogFormVisible: false,
      isChange: 1,
      index: 1,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        code:"",
        outOperator: undefined,
        warehouseId: undefined,
        outTime: undefined,
      },
      faWarehouseList: [],
      rules: {
        code:[{ required: true, message: "请输入", trigger: "blur" }],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        warehouseId: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        material: undefined,
        num: undefined,
        bin: undefined,
        unit: undefined,
        unitNum: undefined,
      },
      materialList: [],
      unitList: [],
      binList: [],
      rulesDetail: {
        material: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unit: [{ required: true, message: "请输入", trigger: "blur" }],
        bin: [{ required: true, message: "请输入", trigger: "blur" }],
      },
    };
  },
  async mounted() {
    const id = this.$route.query.id;
    /**
     * 子模块回显和表单数据的封装
     */
    await getOutboundOrderMaterial(id).then((res) => {
      this.ruleForm = res.data;
      res.data.detailDtoList.forEach((item) => {
        this.tableData.push({
          materialId: item.materialId,
          bin: {"id":item.binId,"code":item.binCode},
          unitName: item.unitName,
          material: {
            id: item.materialId,
            name: item.materialName,
            unitName:item.unitName,
          },
          num: item.num,
        });
      });
    });
    getFaWarehouseList({ type: 1 }).then((res) => {
      this.faWarehouseList = res.data;
    });
    unitList({ code: "", name: "" }).then((res) => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
    await getFaStorehouseList({"warehouse":{"id":this.ruleForm.warehouseId}}).then((res) => {
      console.log("warehouse",this.ruleForm.warehouseId);
      if (res.code === "0") {
        this.binList = res.data;
      }
    });
    await findListByWaHouse(this.ruleForm.warehouseId).then((res) => {
      if (res.code === "0") {
        this.materialList = res.data;
      }
    });
  },
  methods: {
    
    initUnit(index){
      this.tableData[index].unitName=this.tableData[index].material.unitName;
    },
    /**
     * 打开修改弹框
     */
    update(index) {
      this.tableDataIndex = index;
      this.ruleFormDetail = this.tableData[index];
      this.dialogFormVisible = true;
      this.isUpdate = true;
    },
    /**
     * 子表单内容添加到父页面
     */
    submitFormDetail(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const ruleFormDetail = this.ruleFormDetail;
          const { material, num, bin, unit, unitNum } = {
            material: ruleFormDetail.material,
            num: ruleFormDetail.num,
            bin: ruleFormDetail.bin,
            unit: ruleFormDetail.unit,
            unitNum: ruleFormDetail.unitNum,
          };
          if (!this.isUpdate) {
            this.tableData.push({
              material: material,
              num: num,
              bin: bin,
              unit: unit,
              unitNum: unitNum,
            });
          } else {
            if (this.tableData[this.tableDataIndex] === ruleFormDetail) {
              this.dialogFormVisible = false;
              return;
            } else {
              this.tableData[this.tableDataIndex] = {
                material: material,
                num: num,
                bin: bin,
                unit: unit,
                unitNum: unitNum,
              };
            }
          }
          this.$refs[formName].resetFields();
          this.dialogFormVisible = false;
        } else {
          return false;
        }
      });
    },
    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const data = {
            id: this.$route.query.id,
            code:this.ruleForm.code,
            warehouse: {
              id: this.ruleForm.warehouseId,
            },
            outOperator: this.ruleForm.outOperator,
            outTime: this.ruleForm.outTime,
            detailList: this.tableData,
          };
          updateOutboundOrderMaterial(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
                type: "success",
              });
              this.$router.push("outboundOrderMaterial");
            } else {
              this.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    changBg(index) {
      this.isChange = index;
    },
    deleteRow(index) {
      this.tableData.splice(index, 1);
    },
    /**
     * 添加弹框
     */
    openDialog() {
      // this.ruleFormDetail = {};
      // this.isUpdate = false;
      // this.dialogFormVisible = true;
      
      this.tableData.push({
         material: this.materialList[0],
            num: 0,
            bin: this.binList[0],
      })
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    toIndex() {
      this.$router.push("outboundOrderMaterial");
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.submit-button {
  width: 100%;
  display: flex;
  position: absolute;
  bottom: 0px;
  padding: 15px 0 15px 0;
  justify-content: center;
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  background: white;
}
.main {
  background: none !important;
}

.table {
  background: white;
  padding: 16px 0;
  margin-top: 16px;
  min-height: calc(100vh - 200px);
}
.subBtn {
  margin-top: -16px;
}
.addTitle {
  display: flex;
  justify-content: space-between !important;
}
</style>
