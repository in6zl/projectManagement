<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">编辑生产计划</div>
        <el-form
          ref="planItemForm"
          :rules="planItemRules"
          :model="planItemForm"
          label-width="200px"
          class="demo-ruleForm"
        >
          <el-form-item label="编码" prop="code">
            <el-col :span="21">
              <el-input
                type="text"
                size="small"
                :placeholder="$t('main.placeInsert')"
                v-model="planItemForm.code"
                style="width:100%"
              ></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="成品" prop="product">
            <el-col :span="21">
              <el-select
                v-model="planItemForm.product"
                :placeholder="$t('main.placeInsert')"
                size="small"
                filterable
                clearable
                style="width:100%"
                @change="initUnit"
                value-key="id"
              >
                <el-option
                  v-for="item in productList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
                ></el-option>
              </el-select>
            </el-col>
          </el-form-item>

          <el-form-item label="生产数量" prop="num">
            <el-col :span="21">
              <el-input
                type="text"
                size="small"
                :placeholder="$t('main.placeInsert')"
                v-model.number="planItemForm.num"
                style="width:100%"
              ></el-input>
            </el-col>
          </el-form-item>

          <el-form-item label="产线" prop="productionLine">
            <el-col :span="21">
              <el-select
                v-model="planItemForm.productionLine"
                :placeholder="$t('main.placeInsert')"
                size="small"
                filterable
                clearable
                style="width:100%"
                value-key="id"
              >
                <el-option
                  v-for="item in productionLineList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
                ></el-option>
              </el-select>
            </el-col>
          </el-form-item>

          <el-form-item label="计划开始生产日期" prop="startDate">
            <el-col :span="10">
              <el-date-picker
                v-model="planItemForm.startDate"
                type="date"
                placeholder="选择日期"
                size="small"
                :picker-options="pickerOptions"
                @change="chooseStartDate"
                style="width:100%"
              ></el-date-picker>
            </el-col>
            <el-col :span="1" style="text-align:center">--</el-col>
            <el-col :span="10">
              <el-time-select
                v-model="startMinute"
                :picker-options="{
                step: '00:10',
                start: '00:00',
                end: '23:50',
              }"
                placeholder="选择时间"
                size="small"
                style="width:100%"
                @change="chooseTime(planItemForm.startDate,startMinute)"
              ></el-time-select>
            </el-col>
          </el-form-item>

          <el-form-item label="计划结束日期" prop="endDate">
            <el-col :span="10">
              <el-date-picker
                v-model="planItemForm.endDate"
                type="date"
                placeholder="选择日期"
                size="small"
                :picker-options="pickerEndOptions"
                @change="chooseEndDate"
                style="width:100%"
              ></el-date-picker>
            </el-col>
            <el-col :span="1" style="text-align:center">--</el-col>
            <el-col :span="10">
              <el-time-select
                v-model="endMinute"
                :picker-options="{
                step: '00:10',
                start: '00:00',
                end: '23:50',
              }"
                placeholder="选择时间"
                size="small"
                style="width:100%"
              ></el-time-select>
            </el-col>
          </el-form-item>
        </el-form>
      </div>

      <div class="roleManageTable">
        <!-- 弹出框 -->
        <el-dialog title="确认物料需求计划" :visible.sync="dialogVisible" width="70%">
          <el-button
            type="primary"
            size="small"
            style="float:right;margin-right:16px"
            @click="addRow"
          >添加数据</el-button>
          <el-table :data="dialogData" style="width: 100%">
            <el-table-column type="index" label="序号"></el-table-column>
            <el-table-column prop="warehouse.name" label="内仓">
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.warehouse"
                  placeholder="请选择"
                  v-if="scope.row.isEdit"
                  size="mini"
                  clearable
                  value-key="id"
                >
                  <el-option
                    v-for="item in warehouseOption"
                    :key="item.id"
                    :label="item.name"
                    :value="item"
                  ></el-option>
                </el-select>
                <span v-else>{{scope.row.warehouse.name}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="material.name" label="物料名称">
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.material"
                  placeholder="请选择"
                  v-if="scope.row.isEdit"
                  size="mini"
                  clearable
                  value-key="id"
                >
                  <el-option
                    v-for="item in materialOption"
                    :key="item.id"
                    :label="item.name"
                    :value="item"
                  ></el-option>
                </el-select>
                <span v-else>{{scope.row.material.name}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="num" label="需求数量">
              <template slot-scope="scope">
                <el-input
                  type="text"
                  size="mini"
                  style="width:100px"
                  v-model="scope.row.num"
                  v-if="scope.row.isEdit"
                ></el-input>
                <span v-else>{{scope.row.num}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="requireTime" label="需求日期">
              <template slot-scope="scope">
                <span v-if="scope.row.isEdit">
                  <el-date-picker
                    v-model="scope.row.requireTime"
                    type="date"
                    placeholder="选择日期"
                    size="small"
                    style="width:140px"
                    :picker-options="pickerDialgoOptions"
                    @change="chooseDialogDate(scope.$index)"
                  ></el-date-picker>
                </span>
                <span v-else class="dateSpan">{{scope.row.requireTime}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="materailtime" label="需求时间">
              <template slot-scope="scope">
                <span v-if="scope.row.isEdit">
                  <el-time-select
                    v-model="scope.row.materialTime"
                    @change="chooseTime(scope.row.requireTime,scope.row.materialTime)"
                    :picker-options="{
                  step: '00:10',
                  start: '00:00',
                  end: '23:50',
                }"
                    placeholder="选择时间"
                    size="small"
                    style="width:120px"
                  ></el-time-select>
                </span>
                <span v-else>{{scope.row.materialTime}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作" fixed="right" align="right">
              <template slot-scope="scope">
                <svg-icon
                  icon-class="delete"
                  @click.native="deleteDialogRow(scope.$index,tableData)"
                ></svg-icon>
                <svg-icon icon-class="edit" @click.native="handle(scope.$index,tableData)"></svg-icon>
              </template>
            </el-table-column>
          </el-table>
          <span slot="footer" class="dialog-footer">
            <el-button @click="cancle" size="small">取 消</el-button>
            <el-button type="primary" @click="confirm" size="small">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </con>
    <submit>
      <el-button size="small" @click="goback">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('planItemForm')">确定</el-button>
    </submit>
  </div>
</template>

<script>
import { list } from "@/api/unit";
import { parseTime } from "@/utils/index";
import { prolist } from "@/api/product";
import {
  findProductionLine,
  createMaterialReq,
  createMaterialReqByProductionPlanItem,
  saveMaterialPlan,
  getDic,
} from "@/api/productLine";
import { deleteItemm, getByName } from "@/api/productionPlan";
import { get } from "@/api/productionPlanItem";
import { add } from "@/api/productionPlan";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
import { getSaleNo } from "@/api/sellOrder";
export default {
  components: {
    submit,
    con,
    lef,
  },
  data() {
    const validateUnit = (rule, value, callback) => {
      if (this.unitOption.length == 0) {
        return callback(new Error("请先创建单位"));
      } else {
        for (let item of this.unitOption) {
          if (item.code == value) {
            return callback();
          }
        }
        callback(new Error("请选择正确的单位"));
      }
    };
    const validateName = (rule, value, callback) => {
      getByName({
        id:this.$route.query.id,
        code: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    var that = this;
    return {
      proDialogVisible: false,
      rowData: {
        warehouse: {},
        material: {},
        num: "",
        isEdit: false,
        materialTime: null,
        requireTime: "",
      },
      submitObj: [],
      freezetime: "",
      startMinute: "",
      endMinute: "",
      warehouseOption: [],
      materialOption: [],
      dialogData: [],
      dialogVisible: false,
      productList: [],
      productId: "",
      pickerOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return time.getTime() <= Date.now() - 24 * 60 * 60 * 1000;
        },
      },
      pickerPlanStartOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return time.getTime() <= Date.now() - 24 * 60 * 60 * 1000;
        },
      },
      pickerEndOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return (
            time.getTime() <=
            new Date(that.addDate(that.planItemForm.startDate, 1)).getTime() -
              24 * 60 * 60 * 1000
          );
        },
      },
      pickerPlanEndOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return (
            time.getTime() <=
            new Date(that.addDate(that.planItemForm.startDate, 1)).getTime() -
              24 * 60 * 60 * 1000
          );
        },
      },
      pickerDialgoOptions: {
        disabledDate(time) {
          //此条为设置禁止用户选择今天之前的日期，包含今天。
          // return time.getTime() <= (Date.now()-(24 * 60 * 60 * 1000));
          //此条为设置禁止用户选择今天之前的日期，不包含今天。
          return time.getTime() <= Date.now() - 24 * 60 * 60 * 1000;
        },
      },
      options: [
        { name: "已完成", value: 1 },
        { name: "未完成", value: 0 },
      ],
      productionLineList: [],
      unitOption: [],
      planItemForm: {
        code: "",
        product: {},
        num: "",
        unit: "",
        productionLine: {},
        leader: null,
        startDate: "",
        endDate: "",
      },
      productionPlanForm: {
        planName: "",
        startDate: "",
        endDate: "",
        description: "",
        leader: "",
        leaderPhone: "",
        timeValue: "",
      },
      tableHeight: window.innerHeight - 500,
      tableData: [],
      productionPlanRules: {
        code: [
          { required: true, message: "必选", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        leader: { required: true, message: "必选", trigger: "blur" },
        timeValue: { required: true, message: "必选", trigger: "blur" },
      },
      planItemRules: {
        code: [
          { required: true, message: "请输入编码", trigger: "change" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        product: [{ required: true, message: "请选择成品", trigger: "change" }],
        num: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          {
            type: "number",
            message: "数量必须是数字",
            trigger: "blur",
          },
        ],
        productionLine: {
          required: true,
          message: "请选择产线",
          trigger: "change",
        },
        product: {
          required: true,
          message: "请选择成品",
          trigger: "change",
        },
        startDate: {
          required: true,
          message: "请选择开始日期",
          trigger: "blur",
        },
        endDate: {
          required: true,
          message: "请选择计划结束日期",
          trigger: "blur",
        },
      },
    };
  },
  methods: {
    initUnit() {
      this.planItemForm.unit = this.planItemForm.product.unitName;
    },
    alertProDialog() {
      this.proDialogVisible = true;
      (this.startMinute = ""), (this.endMinute = "");
    },
    goback() {
      this.$router.push("/productionPlanMgr");
    },
    chooseStartDate(Time) {
      this.startMinute = "";
    },
    chooseEndDate(Time) {
      this.endMinute = "";
    },
    chooseDialogDate(index) {
      this.dialogData[index].materialTime = "";
    },
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
    chooseTime(_Date, _Time) {
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
          return false;
        }
      }
      return true;
    },
    addDate(date, days) {
      var d = new Date(date);
      d.setDate(d.getDate() + days);
      var m = d.getMonth() + 1;
      return d.getFullYear() + "-" + m + "-" + d.getDate();
    },
    confirm() {
      let istrue = true;
      this.dialogData.map((item) => {
        if (
          item.warehouse.name == "" ||
          item.warehouse.id == "" ||
          item.material.name == "" ||
          item.material.id == "" ||
          item.num <= 0 ||
          item.requireTime == "" ||
          item.materialTime == ""
        ) {
          this.$message({
            message: "请填写完参数",
            type: "error",
          });
          return (istrue = false);
        } else {
          return (istrue = true);
        }
      });
      if (!istrue) {
        return;
      } else {
        this.dialogData.map((item) => {
          item.requireTime =
            parseTime(item.requireTime).substr(0, 10) + " " + item.materialTime;
        });
      }

      let confirmObj = {
        productionPlan: this.submitObj,
        list: this.dialogData,
      };
      saveMaterialPlan(confirmObj).then((res) => {
        if (res.code == 0) {
          this.$message({
            message: "创建成功",
            type: "success",
          });
          this.$router.push("/productionPlanMgr");
        }
      });
    },
    proConfirmCancle(formName) {
      this.$refs[formName].resetFields();
      this.proDialogVisible = false;
    },
    cancle() {
      this.dialogVisible = false;
    },
    handle(index) {
      let _dialog = this.dialogData;
      _dialog[index].isEdit = !_dialog[index].isEdit;
      this.$set(_dialog, index, _dialog[index]);
      if (!_dialog[index].warehouse.name) {
        _dialog[index].warehouse = "";
        _dialog[index].material = "";
      }
      if (
        (_dialog[index].warehouse.id == "" ||
          _dialog[index].material.id == "" ||
          _dialog[index].num <= 0) &&
        _dialog[index].isEdit == false
      ) {
        this.$message({
          message: "请填写完参数",
          type: "error",
        });
        _dialog[index].isEdit = true;
        return;
      }
      if (
        !this.chooseTime(
          _dialog[index].requireTime,
          _dialog[index].materialTime
        )
      ) {
        _dialog[index].isEdit = true;
        return;
      }
      let changeTime = this.getMyDate(
        parseInt(new Date(_dialog[index].requireTime).getTime())
      );
      _dialog[index].requireTime =
        changeTime != "NaN-NaN-NaN NaN:NaN:NaN" ? changeTime.substr(0, 10) : "";
    },
    addRow(index, tableData) {
      this.dialogData.push(JSON.parse(JSON.stringify(this.rowData)));
    },
    //获取所有成品，查询使用
    getAllProductList() {
      prolist({}).then((res) => {
        this.productList = res.data;
      });
    },
    getAllProductionLineList() {
      findProductionLine({}).then((res) => {
        this.productionLineList = res.data;
      });
    },
    resetForm(formName) {
      // this.$refs[formName].resetFields();
      this.$router.push("/productionPlanMgr");
    },
    submitForm(formName) {
      let that = this;
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let data = {};
          data.item = this.planItemForm;

          this.dialogVisible = true;
          that.materialOption = [];
          that.warehouseOption = [];

          let addObj = JSON.parse(JSON.stringify(this.planItemForm));
          addObj.startDate = this.getMyDate(
            parseInt(new Date(that.planItemForm.startDate).getTime()) +
              (parseInt(that.startMinute.substr(0, 2) * 3600) +
                parseInt(that.startMinute.substr(3, 5) * 60)) *
                1000
          );
          addObj.endDate = this.getMyDate(
            parseInt(new Date(that.planItemForm.endDate).getTime()) +
              (parseInt(that.endMinute.substr(0, 2) * 3600) +
                parseInt(that.endMinute.substr(3, 5) * 60)) *
                1000
          );
          this.submitObj = addObj;
          console.log("this.submitObj", this.submitObj);
          createMaterialReqByProductionPlanItem(addObj)
            .then((MaterialRes) => {
              if (MaterialRes.code == 0) {
                this.dialogData = MaterialRes.data;
                this.dialogData.map((item, index) => {
                  item.isEdit = false;
                  item.warehouse = item.warehouse ? item.warehouse : "";
                  that.warehouseOption.push({
                    name: item.warehouse ? item.warehouse.name : "",
                    id: item.warehouse ? item.warehouse.id : "",
                  });
                  that.materialOption.push({
                    name: item.material ? item.material.name : "",
                    id: item.material ? item.material.id : "",
                  });
                  item.deliveryNum = 0;
                  item.materialTime = item.requireTime.slice(11, 16);
                  item.requireTime = item.requireTime.slice(0, 11);
                });
              }
            })
            .then(() => {
              that.warehouseOption = that.duplicate(that.warehouseOption, "id");
              that.materialOption = that.duplicate(that.materialOption, "id");
            });

          // this.$refs[formName].resetFields();
        }
      });
    },
    //去重
    duplicate(arr, type) {
      if (arr.length == 0) {
        return arr;
      } else {
        return arr.reduce(function (prev, element) {
          if (!prev.find((el) => el[type] == element[type])) {
            prev.push(element);
          }
          return prev;
        }, []);
      }
    },
    addItem(formName) {
      let that = this;
      this.$refs[formName].validate((validate) => {
        if (validate) {
          let addObj = {
            product: {},
            num: "",
            unit: "",
            productionLine: {},
            startDate: "",
            endDate: "",
          };
          //成品
          addObj.product = this.productList.filter(
            (item) => item.id === this.planItemForm.product
          )[0];
          addObj.unit = this.planItemForm.unit;
          addObj.num = this.planItemForm.num;
          addObj.startDate = this.getMyDate(
            parseInt(new Date(that.planItemForm.startDate).getTime()) +
              (parseInt(that.startMinute.substr(0, 2) * 3600) +
                parseInt(that.startMinute.substr(3, 5) * 60)) *
                1000
          );
          addObj.endDate = this.getMyDate(
            parseInt(new Date(that.planItemForm.endDate).getTime()) +
              (parseInt(that.endMinute.substr(0, 2) * 3600) +
                parseInt(that.endMinute.substr(3, 5) * 60)) *
                1000
          );
          //产线
          addObj.productionLine = this.productionLineList.filter(
            (item) => item.id === this.planItemForm.productionLine
          )[0];
          if (addObj.startDate > addObj.endDate) {
            that.planItemForm.endDate = "";
            that.planItemForm.startDate = "";
            that.startMinute = "";
            that.endMinute = "";
            that.$message({
              message: "结束日期应晚于开始日期",
              type: "error",
            });
            return;
          }
          let minuts = that.startMinute == "" ? "00:00" : that.startMinute;
          if (!that.chooseTime(addObj.startDate, minuts)) {
            this.$message({
              message: "请选择冻结时间之外的时间",
              type: "error",
            });
            return;
          }
          this.tableData.push(addObj);
          this.$refs[formName].resetFields();
          this.proDialogVisible = false;

          // this.planItemForm.product = "";
          // this.planItemForm.productionLine = "";
        }
      });
    },
    deleteRow(index, tableData) {
      tableData.splice(index, 1);
    },
    deleteDialogRow(index) {
      this.dialogData.splice(index, 1);
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
    querySearchAsyncUnit(queryString, cb) {
      var unitOption = this.unitOption;
      for (let item of unitOption) {
        item.value = item.code;
      }
      var results = queryString
        ? unitOption.filter(this.createStateFilter(queryString))
        : unitOption;
      cb(results);
    },
    unitSelect(item) {
      this.planItemForm.unit = item;
    },
  },
  mounted() {
    let unitData = {
      id: "",
      name: "",
    };
    list(unitData).then((res) => {
      this.unitOption = res.data;
    });
    //成品
    this.getAllProductList();
    //产线
    this.getAllProductionLineList();
    getDic("freeze_time").then((res) => {
      this.freezetime = res.data;
    });
    get(this.$route.query.id).then((res) => {
      let startMinuteArray = res.data.startDate.split(" ")[1].split(":");
      this.startMinute = startMinuteArray[0] + ":" + startMinuteArray[1];
      let endMinuteArray = res.data.endDate.split(" ")[1].split(":");
      this.endMinute = endMinuteArray[0] + ":" + endMinuteArray[1];
      this.planItemForm = res.data;
      console.log(this.planItemForm);
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
.addPlan {
  padding-left: 20px;
}
.dateSpan {
  width: 75px !important;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: none;
  text-overflow: clip;
  display: inline-block;
}
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
