  <template>
  <div>
    <con>
      <div class="main-top">
        <div style="padding:16px">{{ $t('route.addOutboundOrderMaterial') }}</div>
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="100px"
          style="overflow:hidden"
        >
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.code')" prop="code" size="small">
              <el-input v-model="ruleForm.code" maxlength="30" style="width: 98%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
              <el-input v-model="ruleForm.outOperator" maxlength="30" style="width: 98%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="内仓" size="small">
              <el-select
                v-model="ruleForm.warehouse"
                value-key="id"
                placeholder="请选择"
                style="width: 98%"
                fliterable
                clearable
                @change="selectTableData(ruleForm.faWarehouseId),initMaterialList()"
                :disabled="ruleForm.detailList&&ruleForm.detailList.length>0?true:false"
              >
                <el-option
                  v-for="item in faWarehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
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
                style="width: 98%"
              />
            </el-form-item>
          </el-col>
        </el-form>
      </div>
      <!--子列表-->
      <div class="table">
        <div class="addTitle">
          <div style="margin-left:16px">新增详情</div>
          <!--添加按钮-->

          <div>
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="addDetail"
              type="primary"
            >新增详情</el-button>
            <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="submitDialogFrom"
              type="primary"
            >库位推荐</el-button>
          </div>
        </div>

        <el-table
          :data="ruleForm.detailList"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column label="物料(库存数)" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <el-select
                v-model="scope.row.material"
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
              >
                <el-option
                  v-for="item in materialList"
                  :key="item.id"
                  :label="`${(item.name)} (${(item.storedNumInWarHouse?item.storedNumInWarHouse:0)})`"
                  :value="item"
                  :disabled="countMaterial(scope.row.material,item)"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="num" label="需求数量" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <el-input type="text" v-model="scope.row.num" @blur="validateDetailNum(scope.row)" />
            </template>
          </el-table-column>
          <el-table-column
            prop="material.unitName"
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column prop="num" label="未分配数量" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope">{{countLeftNum(scope.row)}}</template>
          </el-table-column>
          <el-table-column label="库位(现有数量) / 出库数量 " width="450" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <tr v-for="faStorehouse  in scope.row.storageBins" :key="faStorehouse.id">
                <td>
                  <el-select v-model="faStorehouse.id" style="width: 200px">
                    <el-option
                      v-for="item in scope.row.faStorehousesSelect"
                      :key="item.id"
                      :label="`${(item.code)} (${(item.num?item.num:0)})`"
                      :value="item.id"
                    />
                  </el-select>
                </td>
                <td>
                  <el-input type="text" v-model="faStorehouse.inboundNum" />
                </td>
                <td>
                  <el-tooltip content="删除" placement="top">
                    <svg-icon
                      icon-class="delete"
                      @click.native="deleteStoreHouse(faStorehouse, scope.row.faStorehouses)"
                    />
                  </el-tooltip>
                </td>
              </tr>
            </template>
          </el-table-column>
          <!------------------------------->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            fixed="right"
            align="right"
          >
            <template slot-scope="scope">
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  icon-class="delete"
                  @click.native="deleteMaterial(scope.$index, scope.row)"
                />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <!--弹框-->
      <el-dialog title="新增详情" :visible.sync="dialogFormVisible" :modal="modal" width="38%">
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
              clearable
              placeholder="请选择"
              style="width: 98%"
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
            <el-input v-model="ruleFormDetail.num" maxlength="11" style="width: 98%" />
          </el-form-item>
          <el-form-item :label="$t('inoutBound.binName')" prop="bin" size="small">
            <el-select
              v-model="ruleFormDetail.bin"
              filterable
              placeholder="请选择"
              style="width: 98%"
              value-key="id"
            >
              <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item" />
            </el-select>
          </el-form-item>
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
  addOutboundOrderMaterial,
  outboundOrderMaterialSotreHouseSuggest,
} from "@/api/outboundOrderMaterial";
import { getFaWarehouseList } from "@/api/faWareHouse.js";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { findMaterialListData, findListByWaHouse } from "@/api/material";
import { getSaleNo } from "@/api/sellOrder";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  components: {
    submit,
    con,
  },
  data() {
    return {
      outOperator: [],
      // --------布局------------
      isUpdate: false,
      tableDataIndex: "",
      dialogFormVisible: true,
      modal: false,
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 400,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        code:"",
        outOperator: undefined,
        warehouse: undefined,
        outTime: undefined,
        detailList: [],
      },
      faWarehouseList: [],
      rules: {
        code:[{ required: true, message: "请输入", trigger: "blur" }],
        outOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        faWarehouseId: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      // -----------子业务-------------------
      ruleFormDetail: {
        material: undefined,
        num: undefined,
        bin: undefined,
        unitName: undefined,
        unitNum: undefined,
      },
      materialList: [],
      unitList: [],
      binList: [],
      rulesDetail: {
        material: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unitName: [{ required: true, message: "请输入", trigger: "blur" }],
        bin: [{ required: true, message: "请输入", trigger: "blur" }],
      },
    };
  },
  mounted() {
    getSaleNo().then(res=>{
      this.ruleForm.code=res.data
    })
    this.modal = true;
    this.dialogFormVisible = false;
    if(this.$route.params.selectList && this.$route.params.selectList.length>0 ){
      // debugger
       this.ruleForm.warehouse=this.$route.params.selectList[0].warehouse;
       let detailList=new Array();
       let outTime=undefined;
       console.log(this.$route.params.selectList)
       this.$route.params.selectList.forEach(materialRequire=>{
          //出货时间选择需求时间中最早的
          if(!outTime){
            outTime=materialRequire.requireTime
          }else{
            if((new Date(outTime.replace(/-/g,'/')).getTime()) > (new Date(materialRequire.requireTime.replace(/-/g,'/')).getTime())){
               outTime=materialRequire.requireTime
            }
          }
          let detail=new Object();
          detail.material=materialRequire.material;
          detail.num=materialRequire.num-materialRequire.outNum;
          detail.material.unitName=materialRequire.material.unit
          if(detailList.length==0){
            detailList.push(detail);
          }else{
            //合并相同物料
            let hasSameMaterial=false;
            detailList.forEach(detailMaterial=>{
              if(detail.material.id == detailMaterial.material.id){
                 detailMaterial.num+=detail.num;
                 hasSameMaterial=true;
              }
            })
            if(!hasSameMaterial){
              detailList.push(detail);
            }
          }
       })
       this.ruleForm.detailList=detailList;
       this.ruleForm.outTime=outTime;
       this.initMaterialList();
    }

    //通过选择内仓获取table数据
    getFaWarehouseList({ type: 1 }).then((res) => {
      this.faWarehouseList = res.data;
    });
    unitList({name: "" }).then((res) => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
  },
  methods: {
    countMaterial(material, optionMaterial) {
      let exist = false;
      if (this.ruleForm.detailList && this.ruleForm.detailList.length > 0) {
        this.ruleForm.detailList.forEach((detail) => {
          if (
            detail.material.id == optionMaterial.id &&
            material.id != optionMaterial.id
          ) {
            exist = true;
          }
        });
      }
      return exist;
    },
    deleteMaterial(index, detail) {
      this.ruleForm.detailList.splice(index, 1);
    },
    countLeftNum(detail) {
      let num = detail.num;
      if (detail.faStorehouses) {
        detail.faStorehouses.forEach((faStorehouse) => {
          num = num - (faStorehouse.inboundNum ? faStorehouse.inboundNum : 0);
        });
      }
      return num;
    },
    //删除库位
    deleteStoreHouse(storeHouse, row) {
      for (let i = 0; i < row.length; i++) {
        if (row[i].id == storeHouse.id) {
          row.splice(i, 1);
        }
      }
    },
    submitDialogFrom() {
      if (!this.ruleForm.detailList || this.ruleForm.detailList.length ==0) {
        this.$message({
          type: "info",
          message: "请先添加详情",
        });
        return;
      }
      for (let i = 0; i < this.ruleForm.detailList.length; i++) {
        let detail = this.ruleForm.detailList[i];
        if (!detail.material || !detail.material.id) {
          this.$message({
            type: "info",
            message: "第" + (i + 1) + "行详情请先选择物料",
          });
          return;
        }
        if (!detail.num) {
          this.$message({
            type: "info",
            message: "第" + (i + 1) + "行详情请先填入数量",
          });
          return;
        }else{
          let patt1 = /^\d+\.{0,1}\d*$/;
          let result = patt1.test(detail.num);
          if(!result){
            this.$message({
              type: "info",
              message: "第" + (i + 1) + "行详情数量必须是数字",
            });
          return;
          }
        }
      }
      outboundOrderMaterialSotreHouseSuggest(this.ruleForm).then(
        (res) => {this.ruleForm = res.data;
           let hasSuggest=false;
           this.ruleForm.detailList.forEach(detail =>{
              if(detail.storageBins && detail.storageBins.length >0){
                hasSuggest=true;
              }
           })
           if(!hasSuggest){
            this.$message({
              type: "info",
              message: "抱歉，系统未找到可以推荐的库位",
            });
          }
        }
      );
    },
    validateDetailNum(detail) {
      if(this.materialList && this.materialList.length>0){
        let material={};
        this.materialList.forEach(materialSingle=>{
          if(materialSingle.id == detail.material.id){
             material=materialSingle;
          }
        })
        if (material.storedNumInWarHouse < detail.num) {
          this.$message({
            type: "info",
            message: "该物料库存不足",
          });
          return;
        }
      }
      
    },
    initMaterialList() {
      findListByWaHouse(this.ruleForm.warehouse.id).then((res) => {
        this.materialList = res.data;
      });
    },
    addDetail() {
      if (!this.ruleForm.warehouse || !this.ruleForm.warehouse.id) {
        this.$message({
          type: "info",
          message: "请选择内仓",
        });
        return;
      }

      this.ruleForm.detailList.push({ material: {} });
    },
    countWords(arr) {
      return arr.reduce(function (countMap, word, index) {
        countMap[word] = ++countMap[word] || 1; // increment or initialize to 1
        return countMap;
      }, {}); // second argument to reduce initialises countMap to {}
    },
    selectTableData(val) {
      this.binList = {};
      //根据内仓id加载库位列表
      getFaStorehouseList({ warehouseId: val }).then((res) => {
        if (res.code === "0") {
          this.binList = res.data;
        }
      });
      if (this.outOperator.length > 0) {
        this.tableData = this.outOperator.filter(
          (item) => item.warehouse.id == val
        );
      } else {
        return;
      }
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
          const { material, num, bin, unitName, unitNum } = {
            material: ruleFormDetail.material,
            num: ruleFormDetail.num,
            bin: ruleFormDetail.bin,
            unitName: ruleFormDetail.material.unitName,
            unitNum: ruleFormDetail.unitNum,
          };
          if (!this.isUpdate) {
            this.tableData.push({
              material: material,
              num: num,
              bin: bin,
              unitName: unitName,
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
                unitName: unitName,
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
          if (this.ruleForm.detailList && this.ruleForm.detailList.length > 0) {
            let hasSuggest=false;
            for (let i = 0; i < this.ruleForm.detailList.length; i++) {
              var detail = this.ruleForm.detailList[i];
              if (!detail.material || !detail.material.id) {
                this.$message({
                  message: "第" + (i + 1) + "行请选择物料",
                  type: "warning",
                });
                return;
              }
              if (!detail.num) {
                this.$message({
                  message: "第" + (i + 1) + "行请输入数量",
                  type: "warning",
                });
                return;
              }else{
                if(!/^\d+\.*\d*$/.test(detail.num)){
                  this.$message({
                    message: "第" + (i + 1) + "行数量必须是数字",
                    type: "warning",
                  });
                  return;
                }
              }
              if (detail.storageBins && detail.storageBins.length > 0) {
                hasSuggest=true;
                let inboundNum = 0;
                detail.storageBins.forEach((faStorehouse) => {
                  inboundNum += parseFloat(faStorehouse.inboundNum);
                });
                if (detail.num != inboundNum) {
                  this.$message({
                    message:
                      "第" + (i + 1) + "行物料需求数量与库位物料分配数量不符",
                    type: "warning",
                  });
                  return;
                }
              }
            }
            if(!hasSuggest){
                  this.$message({
                    message: "详情没有库位可供保存",
                    type: "warning",
                  });
                  return;
            }
          } else {
            this.$message({
              message: "请创建详情",
              type: "warning",
            });
            return;
          }
          let id=this.$route.params.selectList[0].workShop.id
          addOutboundOrderMaterial(this.ruleForm,id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
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
      this.ruleFormDetail = {};
      this.isUpdate = false;
      this.dialogFormVisible = true;
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    toIndex() {
      let url="outboundOrderMaterial";
      if(this.$route.params.url){
        url=this.$route.params.url;
      }
      this.$router.push(url);
    },
  },
};
</script>

<style lang='scss' scoped>
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
}
.subBtn {
  margin-top: -16px;
}
.addTitle {
  display: flex;
  justify-content: space-between !important;
}
</style>
