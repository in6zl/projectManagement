
<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="padding:16px" class="subtit">编辑领料单</div>
        <el-form
          class="demo-ruleForm"
          label-width="120px"
          ref="materialForm"
          :rules="materialRules"
          :model="materialForm"
          style="overflow:hidden"
        >
          <el-col :span="11">
            <el-form-item label="领料单号" prop="code">
              <el-input
                :placeholder="$t('main.placeInsert')"
                size="small"
                v-model="materialForm.code"
                style="width:100%"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="是否完成" prop="isCompile">
              <el-select
                v-model="materialForm.isCompile"
                :placeholder="$t('main.placeInsert')"
                style="width:100%"
                size="small"
                clearable
              >
                <el-option
                  v-for="item in options"
                  :key="item.name"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="所属车间" prop="workShop">
              <el-select
                v-model="materialForm.workShop"
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
          </el-col>
          <el-col :span="11">
            <el-form-item label="备注" prop="description">
              <el-input
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:100%;"
                v-model="materialForm.description"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>

        <div class="addTitle" style="padding:16px 16px 0 16px">
          <div style="line-height:34px" class="subtit">新增领料单项</div>
          <el-button
            type="primary"
            size="small"
            style="margin:0 0 16px 16px"
            @click="alertProDialog"
          >添加产品</el-button>
        </div>
        <!-- 添加产品 -->
        <el-dialog title="添加产品" :visible.sync="proDialogVisible">
          <el-form ref="itemForm" :rules="itemRules" :model="itemForm" label-width="150px">
            <el-row>
              <el-form-item label="物料名称" prop="material">
                <el-col :span="21">
                  <el-select
                    v-model="itemForm.material"
                    :placeholder="$t('main.placeInsert')"
                    size="small"
                    filterable
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
                </el-col>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="数量" prop="num">
                <el-col :span="21">
                  <el-input
                    type="text"
                    size="small"
                    :placeholder="$t('main.placeInsert')"
                    v-model.number="itemForm.num"
                  ></el-input>
                </el-col>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="批次号" prop="batch">
                <el-col :span="21">
                  <el-input
                    :placeholder="$t('main.placeInsert')"
                    size="small"
                    style="width:100%"
                    v-model="itemForm.batch"
                  ></el-input>
                </el-col>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="领取时间" prop="getTime">
                <el-col :span="10">
                  <el-date-picker
                    v-model="itemForm.getTime"
                    type="date"
                    placeholder="选择日期"
                    size="small"
                    style="width:100%"
                    :picker-options="pickerOptions"
                    @change="chooseGetDate"
                  ></el-date-picker>
                </el-col>
                <el-col :span="1" style="text-align:center">--</el-col>
                <el-col :span="10">
                  <el-time-select
                    v-model="itemForm.minutes"
                    :picker-options="{
                  step: '00:10',
                  start: '00:00',
                  end: '23:50',
                }"
                    placeholder="选择时间"
                    size="small"
                    style="width:100%"
                    @change="chooseTime(itemForm.getTime,itemForm.minutes)"
                  ></el-time-select>
                </el-col>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="内仓" prop="warehouse" size="small">
                <el-col :span="21">
                  <el-select
                    v-model="itemForm.warehouse"
                    :placeholder="$t('main.placeInsert')"
                    size="small"
                    filterable
                    clearable
                    style="width:100%"
                  >
                    <el-option
                      v-for="item in warehouseList"
                      :key="item.name"
                      :label="item.name"
                      :value="item.id"
                    ></el-option>
                  </el-select>
                </el-col>
              </el-form-item>
            </el-row>
            <el-row>
              <el-col :span="24" class="proConfirmBtn">
                <el-button size="small" @click="proConfirmCancle('itemForm')">取消</el-button>
                <el-button type="primary" size="small" @click="addItem('itemForm')">确定</el-button>
              </el-col>
            </el-row>
          </el-form>
        </el-dialog>
        <div class="roleManageTable">
          <el-table
            :data="tableData"
            style="width: 100%"
            :height="tableHeight"
            :header-cell-style="tableHeaderColor"
          >
            <el-table-column type="index" width="80" :label="$t('table.serial')"></el-table-column>
            <el-table-column
              width="240"
              label="物料"
              prop="material.name"
              :show-overflow-tooltip="true"
            ></el-table-column>

            <el-table-column label="数量" width="135">
              <template slot-scope="scope">
                <span v-if="scope.row.isEdit">
                  <el-input type="text" v-model="scope.row.num" size="small"></el-input>
                </span>
                <span v-else>{{scope.row.num}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="batch" label="批次号" width="135" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column prop="getTime" label="领取时间" width="300" :show-overflow-tooltip="true"></el-table-column>
            <el-table-column
              prop="warehouse.name"
              label="内仓"
              width="135"
              :show-overflow-tooltip="true"
            ></el-table-column>
            <el-table-column label="操作" fixed="right" align="right">
              <template slot-scope="scope">
                <svg-icon icon-class="edit" @click.native="handle(scope.$index, tableData)"></svg-icon>
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </con>
    <submit>
      <el-button size="small" @click="goback">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('materialForm')">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { parseTime } from "@/utils/index";
import {
  findMaterialListData,
  updateMaterialRequisition,
  getMaterialRequisitionById,
  save,
  getByName,
} from "@/api/material";
import { getDic } from "@/api/productLine";
import { getFaWarehouseList } from "@/api/faWareHouse";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
import { listWorkshop } from "@/api/workshop";

export default {
  mixins: [btn],
  components: {
    submit,
    con,
    lef,
  },
  data() {
    const numValidator = (rule, value, callback) => {
      if (!/^[1-9]+[0-9]*$/.test(value)) {
        return callback(new Error("数量必须是正整数"));
      } else {
        return callback();
      }
    };
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
    return {
      proDialogVisible: false,
      freezeTime: "",
      warehouseList: [],
      materialList: [],
      chejians: [],
      options: [
        { name: "已完成", value: 1 },
        { name: "未完成", value: 0 },
      ],
      itemOptions: [],
      itemForm: {
        material: "",
        num: "",
        getTime: "",
        batch: "",
        warehouse: "",
        minutes: "",
      },
      materialForm: {
        description: "",
        isCompile: "",
        id: "",
        code: "",
        workShop: "",
      },
      pickerOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return time.getTime() <= Date.now() - 24 * 60 * 60 * 1000;
        },
      },
      tableHeight: window.innerHeight - 400,
      tableData: [],
      materialRules: {
        code: [
          { required: true, message: "必填", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        workShop:{ required: true, message: "必选", trigger: "blur" },
        isCompile: { required: true, message: "必选", trigger: "blur" },
      },
      itemRules: {
        material: [
          { required: true, message: "请选择物料", trigger: "change" },
        ],
        num: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          { validator: numValidator, trigger: ["blur", "change"] },
        ],
        getTime: {
          required: true,
          message: "请选择时间",
          trigger: "blur",
        },
        warehouse: {
          required: true,
          message: "请选择内仓",
          trigger: "change",
        },
      },
    };
  },
  created() {
    let id = this.$route.query.id;
    this.initMaterialRequisitionById(id);
  },
  methods: {
    alertProDialog() {
      this.proDialogVisible = true;
      this.minutes = "";
    },
    proConfirmCancle(formName) {
      this.$refs[formName].resetFields();
      this.proDialogVisible = false;
    },
    goback() {
      this.$router.push("/materialRequisitionMgr");
    },
    handle(index) {
      let oprateData = this.tableData[index];
      oprateData.isEdit = !oprateData.isEdit;
      if (!oprateData.isEdit) {
        oprateData.getTime =
          oprateData.getTime.slice(0, 11) + oprateData.minutes;
      }
      this.updateNum(this.tableData[index], this.tableData[index].isFreeze);
    },
    updateNum(data, freezeTime) {
      if (freezeTime) {
        let reqNum = data.reqNum;
        if (!(data.num <= reqNum) && data.isEdit == false) {
          this.$message({
            message: "该条数据已被冻结需求量不能增加",
            type: "error",
          });
          return (data.isEdit = true);
        } else if (data.num <= 0 || !data.num) {
          this.$message({
            message: "请填写正确的数量",
            type: "error",
          });
          return (data.isEdit = true);
        }
      }
    },
    chooseDate(index) {
      this.tableData[index].minutes = "";
    },
    chooseGetDate(index) {
      this.itemForm.minutes = "";
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
        var now = new Date();
        let freezetime =
          this.freezetime < 600 || !this.freezetime
            ? 600
            : Number(this.freezetime);
        let sysFreezetime = freezetime * 1000 + new Date(Date.now()).getTime();
        let chooseTime =
          new Date(parseTime(_Date).slice(0, 11) + "00:00:00").getTime() +
          _Time.slice(0, 2) * 60 * 60 * 1000 +
          _Time.slice(3, 5) * 60 * 1000;
        if (chooseTime < sysFreezetime) {
          this.$message({
            message: "请选择冻结范围外时间",
            type: "error",
          });
          _Date = "";
          _Time = "";
          return false;
        }
        if (index) {
          this.dialogData[index].isFreeze = false;
          this.$set(this.dialogData, index, this.dialogData[index]);
        }
        return true;
      }
    },
    initMaterialRequisitionById(id) {
      getMaterialRequisitionById(id)
        .then((res) => {
          this.materialForm.description = res.data.description;
          this.materialForm.isCompile = res.data.compile ? 1 : 0;
          this.materialForm.id = res.data.id;
          this.materialForm.code = res.data.code;
          this.materialForm.workShop = res.data.workShopDTO;
          res.data.materialRequisitionDetail.map((dataItem) => {
            let addObj = {
              id: "",
              material: {},
              num: "",
              batch: "",
              getTime: "",
              isEdit: false,
              minutes: "",
              reqNum: "",
            };
            addObj.id = dataItem.id;
            addObj.code = dataItem.code;
            addObj.material = dataItem.material;
            addObj.num = dataItem.num;
            addObj.reqNum = addObj.num;
            addObj.batch = dataItem.batch;
            addObj.getTime = dataItem.getTime;
            addObj.warehouse = dataItem.warehouse;
            addObj.minutes = addObj.getTime.slice(11, 16);
            this.tableData.push(addObj);
          });
        })
        .then(() => {
          //判断dialogData里面的数据是否是冻结时间
          this.tableData.map((item) => {
            item.isFreeze = false;
            let _date = JSON.parse(JSON.stringify(item.getTime));
            let minutes = JSON.parse(JSON.stringify(item.minutes));
            let milliscond =
              new Date(_date).getTime() -
              8 * 60 * 60 * 1000 +
              minutes.slice(0, 2) * 60 * 60 * 1000 +
              minutes.slice(3, 5) * 60 * 1000; //获取当前时间毫秒
            let freeze_time = Date.parse(new Date()) + this.freezeTime * 1000;
            if (milliscond < freeze_time) {
              item.isFreeze = true;
            }
          });
        });
    },
    //获取所有物料，查询使用
    getAllMaterialList() {
      findMaterialListData().then((res) => {
        this.materialList = res.data;
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.$router.push("/materialRequisitionMgr");
    },
    submitForm(formName) {
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let data = {
            id: this.materialForm.id,
            code: this.materialForm.code,
            description: this.materialForm.description,
            isCompile: this.materialForm.isCompile,
            item: this.tableData,
            workShopDTO: this.materialForm.workShop,
          };

          if (data.item.length === 0) {
            this.$message({
              message: "请添加领料单项",
              type: "fail",
            });
            return;
          }
          //update material requisition
          save(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "更新成功",
                type: "success",
              });
              this.$router.push("/materialRequisitionMgr");
              this.$refs[formName].resetFields();
            }
          });
        }
      });
    },
    addItem(formName) {
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let addObj = {
            material: {},
            num: "",
            batch: "",
            getTime: "",
            isEdit: false,
            minutes: "",
          };
          addObj.material = this.materialList.filter(
            (item) => item.id === this.itemForm.material
          )[0];
          addObj.warehouse = this.warehouseList.filter(
            (item) => item.id === this.itemForm.warehouse
          )[0];
          addObj.num = this.itemForm.num;
          addObj.batch = this.itemForm.batch;
          addObj.minutes = this.itemForm.minutes
            ? this.itemForm.minutes + ":00"
            : "";
          addObj.getTime =
            parseTime(this.itemForm.getTime).slice(0, 11) + addObj.minutes;
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
          this.proDialogVisible = false;
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
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    initWareHouseData() {
      getFaWarehouseList({}).then((res) => {
        this.warehouseList = res.data;
      });
    },
  },
  mounted() {
    this.getAllMaterialList();
    this.initWareHouseData();
    getDic("freeze_time").then((res) => {
      this.freezetime = res.data;
    });

    listWorkshop({}).then((res) => {
      this.chejians = res.data;
    });
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/sale.scss";
.main >>> .el-textarea__inner {
  height: 88px;
}
.submitBtn {
  width: 100%;
  padding: 15px 0 15px 0;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  z-index: 10;
}
.proConfirmBtn {
  padding: 0 0 10px 0;
  display: flex;
  justify-content: center;
}
.subBtn {
  margin-top: -18px;
}
.addTitle {
  display: flex;
  justify-content: space-between !important;
}
</style>