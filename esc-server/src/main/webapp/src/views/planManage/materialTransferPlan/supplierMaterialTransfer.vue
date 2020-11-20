<template>
  <div class="container">
    <!-- 已确认 -->
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-select
                v-model="faWareHouse"
                :placeholder="$t('main.placeInsert')+'内仓'"
                style="width:95%"
                size="small"
                clearable
                filterable
              >
                <el-option
                  v-for="item in faWarehouseList"
                  :key="item.id"
                  :value="item.id"
                  :label="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="second">
              <el-select
                v-model="state"
                :placeholder="$t('main.placeInsert')+'状态'"
                style="width:95%"
                size="small"
                clearable
                filterable
                multiple
              >
                <el-option
                  v-for="item in stateList"
                  :key="item.id"
                  :value="item.id"
                  :label="item.name"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item size="small" slot="three">
              <el-date-picker
                v-model="searchreqTime"
                type="date"
                placeholder="选择日期"
                size="small"
                style="width:95%"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
          </search-form>
          <el-button
            type="primary"
            size="small"
            style="float:right;margin:0 16px 16px 0;"
            @click="search"
          >{{$t('button.search')}}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t("route.materialTransfer")}}</div>
          <div>
            <el-button @click="createSendOrder" type="primary" size="small">生成送货单</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="60" align="center" :selectable="selectable"></el-table-column>
          <el-table-column type="index" :label="$t('table.serial')"></el-table-column>
          <el-table-column label="内仓" prop="faWarehouse.name" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="物料" prop="material.name" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="供应商名称" prop="supplier.name" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="状态" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <span v-if="scope.row.state == 0">未确认</span>
              <span v-if="scope.row.state == 1">已确认</span>
              <span v-if="scope.row.state == 2">已取消</span>
              <span v-if="scope.row.state == 3">处理中</span>
              <span v-if="scope.row.state == 4">已完成</span>
            </template>
          </el-table-column>
          <el-table-column label="需求数量" prop="num" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="待处理数量" prop="unProcessNum" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="处理中数量" prop="processingNum" :show-overflow-tooltip="true"></el-table-column>
          <!-- <el-table-column label="处理失败数量" width="120" prop="processFailedNum" :show-overflow-tooltip="true"></el-table-column> -->
          <el-table-column label="已完成数量" prop="finishedNum" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="需求时间" prop="requireTime" align="center" width="150"></el-table-column>
        </el-table>
      </div>
      <div class="paging">
        <div>显示第 1 至 10 项结果，共{{totalPages}}页</div>
        <div>
          <el-pagination
            layout="prev, pager, next"
            :total="totalElements"
            background
            small
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
          ></el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import searchForm from "@/components/commonTop/index";
import {
  deletePlan,
  confirmList,
  getList,
  statisticsNum,
  confirm,
} from "@/api/materialTransferPlan";
import { getFaWarehouseList } from "@/api/faWareHouse.js";
import { findMaterialListData } from "@/api/material.js";
import { getOrganization } from "@/api/organization.js";
import { getDic } from "@/api/productLine";
import { utils } from "@/components/minxs/utils";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  mixins: [utils],
  data() {
    return {
      multipleSelection: [],
      tableState: 0,
      confirmedList: [], //未确认表格数据
      activeName: "first",
      deleteList: [],
      dateFreeze: false, //判断时间是否是冻结 提交时如果true 不允许用户提交
      saveOrUpdateList: [],
      reqTime: "",
      freezetime: "",
      noEdit: [],
      dialogData: [],
      dialogVisible: false,
      faWareHouse: "",
      organizationName: "",
      organizationType: "",
      state: "",
      transfer: {
        state: this.getStateByTab(),
        wareHouseId: "",
        requireTime: "",
      },
      stateList: [
        {
          id: "1",
          name: "已确认",
        },
        {
          id: "2",
          name: "已取消",
        },
        {
          id: "3",
          name: "处理中",
        },
        {
          id: "4",
          name: "已完成",
        },
      ],
      searchreqTime: "",
      faWarehouseList: [],
      faWarehouseOptions: [],
      materialList: [],
      supplierList: [],
      createTime: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 300,
      tableData: [],
      seller: "",
      orderNo: "",
      pageable: {},
      totalElements: "",
      pageNum: "0",
      pageSize: "10",
      totalPages: "",
      selectedMaterialTransfer: [],
      pickerOptions: {
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
  mounted() {
    let name = this.$route.query.name;
    this.activeName = name ? name : "first";
    this.tableState = this.activeName == "second" ? "1" : "0";
    this.list();
    let that = this;
    getDic("freeze_time").then((res) => {
      this.freezetime = res.data;
    });

    getFaWarehouseList({}).then((res) => {
      this.faWarehouseList = res.data;
      this.faWarehouseOptions = res.data;
    });
    findMaterialListData().then((res) => {
      this.materialList = res.data;
    });
    getOrganization({ type: 1 }).then((res) => {
      this.supplierList = res.data;
    });
    this.getConfirmList();
    this.list();
  },
  methods: {
    handleSelectionChangeRoleManageTable(val) {
      this.multipleSelection = val;
    },
    createSendOrder() {
      console.log("selectedMaterialTransfer", this.selectedMaterialTransfer);
      this.$router.push({
        name: "addSendOrder",
        params: { selectedMaterialTransfer: this.selectedMaterialTransfer },
      });
    },
    selectable(row, index) {
      if (row.unProcessNum == 0) {
        return false;
      } else {
        return true;
      }
    },
    handleSelectionChange(rows) {
      this.selectedMaterialTransfer = rows;
      // console.log(rows);
    },
    handleTable(val) {
      this.tableState = val.index;
      this.list();
    },
    getConfirmList(data) {
      confirmList(data)
        .then((res) => {
          this.dialogData = res.data;
          this.dialogData.map((item) => {
            item.isEdit = false;
            item.reqNum = item.num;
            item.material = item.material
              ? {
                  id: item.material.id,
                  name: item.material.name,
                }
              : "";
            item.supplier = item.supplier
              ? {
                  id: item.supplier.id,
                  name: item.supplier.name,
                }
              : "";
            item.faWareHouse = item.faWareHouse
              ? {
                  id: item.faWareHouse.id,
                  name: item.faWareHouse.name,
                }
              : "";
            item.reqTime = item.requireTime.slice(0, 11);
            item.materialTime = item.requireTime.slice(11, 16);
          });
        })
        .then(() => {
          //判断dialogData里面的数据是否是冻结时间
          this.dialogData.map((item) => {
            item.isFreeze = false;
            let _date = JSON.parse(JSON.stringify(item.requireTime));
            let minutes = item.materialTime;
            let milliscond = new Date(_date).getTime();
            //获取当前时间毫秒
            let freeze_time = new Date().getTime() + this.freezetime * 1000;
            if (milliscond < freeze_time) {
              item.isFreeze = true;
            }
          });
        });
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
    chooseDialogDate(index) {
      this.dialogData[index].materialTime = "";
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
      if (
        _Date + " " + _Time ==
        this.dialogData[index].requireTime.slice(0, 16)
      ) {
        this.dialogData[index].eidtTime = false;
      } else {
        this.dialogData[index].eidtTime = true;
      }
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
        if (!this.dialogData[index].id) {
          this.$message({
            message: "请选择冻结范围外时间",
            type: "error",
          });
        }
        this.dialogData[index].isFreeze = true;
        this.$set(this.dialogData, index, this.dialogData[index]);
        return false;
      } else {
        this.dialogData[index].isFreeze = false;
        this.$set(this.dialogData, index, this.dialogData[index]);
        return true;
      }
    },
    addRow() {
      var data = {
        faWarehouse: {},
        material: {},
        supplier: {},
        num: "",
        reqTime: "",
        materialTime: "",
        isEdit: false,
        isFreeze: false,
      };
      this.dialogData.push(JSON.parse(JSON.stringify(data)));
    },
    handle(index) {
      let _dialog = this.dialogData;
      _dialog[index].isEdit = !_dialog[index].isEdit;
      if (!_dialog[index].faWarehouse.name) {
        _dialog[index].faWarehouse = "";
        _dialog[index].material = "";
      }
      this.$set(_dialog, index, _dialog[index]);
      if (
        (_dialog[index].faWarehouse.id == "" ||
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
      this.updateNum(_dialog[index], _dialog[index].isFreeze, index);
      let changeTime = this.getMyDate(
        parseInt(new Date(_dialog[index].reqTime).getTime())
      );
      _dialog[index].reqTime =
        changeTime != "NaN-NaN-NaN NaN:NaN:NaN" ? changeTime.substr(0, 10) : "";
      if (
        this.dialogData[index].isFreeze &&
        !_dialog[index].isEdit &&
        _dialog[index].eidtTime
      ) {
        _dialog[index].isEdit = true;
        this.$message({
          message: "请选择冻结时间外的时间",
          type: "error",
        });
        return;
      }
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
        }
      }
    },
    save() {
      let istrue = true;
      let isFreeze = true;
      let isfull = true;
      this.dialogData.map((item) => {
        if (!item.supplier.id) {
          this.$message({
            message: "请选择供应商后提交数据",
            type: "error",
          });
          return (istrue = false);
        }
        if (item.eidtTime && item.isFreeze) {
          this.$message({
            message: "请选择冻结时间外的时间段",
            type: "error",
          });
          return (isFreeze = false);
        }
        if (
          !item.faWarehouse.id ||
          !item.material.id ||
          item.num <= 0 ||
          !item.reqTime ||
          !item.materialTime
        ) {
          this.$message({
            message: "请填写完参数",
            type: "error",
          });
          return (isfull = false);
        } else {
          return (isfull = true);
        }
      });
      if (!istrue || !isFreeze || !isfull) {
        return;
      } else {
        this.dialogData.map((item) => {
          this.saveOrUpdateList.push({
            id: item.id || "",
            material: { id: item.material.id || "" },
            supplier: { id: item.supplier.id || "" },
            faWarehouse: { id: item.faWarehouse.id || "" },
            num: item.num,
            state: 0,
            requireTime:
              (item.reqTime + item.materialTime).indexOf(" ") == -1
                ? item.reqTime + " " + item.materialTime + ":00"
                : item.reqTime + item.materialTime + ":00",
          });
        });
      }
      confirm({
        deleteList: this.deleteList,
        saveOrUpdateList: this.saveOrUpdateList,
      })
        .then((res) => {
          if (res.code == 0) {
            this.dialogData = [];
            this.$message({
              message: "更新成功",
              type: "success",
            });
            this.$router.push("/materialTransfer");
            this.dialogVisible = false;
            this.dialogData.map((item) => {
              item.isEdit = false;
              item.eidtTime = false;
            });
            this.list();
          }
        })
        .catch(() => {
          this.saveOrUpdateList = [];
        });
    },
    close(done) {
      this.saveOrUpdateList = [];
      this.deleteList = [];
      this.dialogData = [];
      this.getConfirmList();
      this.list();
      done();
    },
    deleteDialogRow(index, tableData) {
      // 确认调货计划dialog删除改取消确认
      // this.deleteList.push(tableData[index]);
      tableData.splice(index, 1);
      this.list();
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    sheetDialog() {
      this.getConfirmList(this.multipleSelection);
      if (this.dialogData.length == 0) {
        this.$message({
          message: "没有需要确认的计划",
          type: "warning",
        });
      } else {
        this.dialogVisible = true;
      }
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    changBg(index) {
      this.ischange = index;
    },
    list() {
      let data = {
          state: this.getStateByTab(),
        },
        pageSize = this.pageSize,
        pageNum = this.pageNum;
      getList(data, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          this.tableData = statisticsNum(res.data.content);
          this.pageable = res.data.pageable;
          this.totalElements = res.data.totalElements;
          this.totalPages = res.data.totalPages;
        }
      });
    },
    getStateByTab() {
      let stateArray1 = [1, 2, 3, 4];
      let res = new Array();
      for (let i = 0; i < stateArray1.length; i++) {
        if (this.state && this.state.length > 0) {
          for (let j = 0; j < this.state.length; j++) {
            if (parseInt(stateArray1[i]) == parseInt(this.state[j])) {
              res = this.state;
            }
          }
        } else {
          return stateArray1;
        }
      }
      return res;
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deletePlan(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              this.list();
            }
          });
        })
    },
    update(index, rows) {
      let id = this.tableData[index].id;
      this.$router.push({ path: "/editMaterialTransfer", query: { id: id } });
    },
    search() {
      this.transfer.state = this.getStateByTab();
      this.transfer.wareHouseId = this.faWareHouse;
      this.transfer.requireTime = this.searchreqTime;
      let data = {
        state: this.getStateByTab(),
        wareHouseId: this.faWareHouse,
        requireTime: this.searchreqTime,
      };
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      getList(data, 0, 10)
        .then((res) => {
          if (res.code === "0") {
            loading.close();
            this.tableData = statisticsNum(res.data.content);
            this.pageable = res.data.pageable;
            this.size = res.data.size;
            this.totalElements = res.data.totalElements;
            this.totalPages = res.data.totalPages;
          }
        })
        .catch(() => {
          loading.close();
        });
    },
    handleCurrentChange(cpage) {
      this.num = cpage;
      this.pageNum = cpage - 1;
      let data = {
        state: this.transfer.state,
        wareHouseId: this.transfer.wareHouseId,
        requireTime: this.transfer.requireTime,
      };
      getList(data, cpage - 1, 10).then((res) => {
        if (res.code === "0") {
          this.tableData = statisticsNum(res.data.content);
          this.pageable = res.data.pageable;
          this.totalElements = res.data.totalElements;
          this.totalPages = res.data.totalPages;
        }
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.main .el-form-item:nth-of-type(3) {
  float: none !important;
}
.main >>> .el-dialog {
  height: 585px !important;
}
.container >>> .el-tabs__header {
  margin: 0 !important;
  background: white;
}
.container >>> .el-tabs__nav-wrap {
  padding-left: 16px;
}
.container {
  padding-bottom: 16px;
}
</style>
