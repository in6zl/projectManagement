<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('product.placeholder')+$t('registration.carNo')"
                size="small"
                v-model="carNo"
                style="width:95%"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-select
                v-model="carType"
                :placeholder="$t('product.select')+$t('registration.carType')"
                size="small"
                clearable
                style="width:95%"
              >
                <el-option
                  v-for="(item,i) in carTypes"
                  :key="i"
                  :label="item.dictKey"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="three">
              <el-select
                v-model="carState"
                :placeholder="$t('product.select')+$t('registration.carState')"
                size="small"
                clearable="true"
                style="width:95%"
              >
                <el-option
                  v-for="(item,i) in carStates"
                  :key="i"
                  :label="item.message"
                  :value="item.code"
                ></el-option>
              </el-select>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="small"
            @click="search"
            v-if="haveBtn('registration:list')"
          >{{$t('button.search')}}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t('registration.registrationList')}}</div>
          <el-button
            size="small"
            @click="toRegistration(),changBg(1)"
            type="primary"
            v-if="haveBtn('registration:add')"
          >扫描</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="carNo"
            :label="$t('registration.carNo')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="driverPhone"
            :label="$t('registration.driverPhone')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="carTypeName"
            :label="$t('registration.carType') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="stateName"
            :label="$t('registration.carState') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="enterTime"
            :label="$t('registration.enterTime') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="exitTime"
            :label="$t('registration.exitTime') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            align="center"
            width="150"
          >
            <template slot-scope="scope">
              <el-dropdown trigger="click" v-if="haveBtn('registration:edit')">
                <span class="el-dropdown-link">
                  <el-tooltip content="操作" placement="top">
                    <svg-icon icon-class="line"></svg-icon>
                  </el-tooltip>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    @click.native="updataRegistration(scope.$index,tableData)"
                    v-if="scope.row.state!=8&&scope.row.state!=9"
                  >修改</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="enterPark(scope.$index,tableData)"
                    v-if="scope.row.state==1||scope.row.state==2"
                  >进园</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="exitPark(scope.$index,tableData)"
                    v-if="scope.row.state==6"
                  >出园</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="size"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          small
        ></el-pagination>
      </div>
    </div>
    <el-dialog title="扫码窗口" :visible.sync="qrcodeOpen" width="30%">
      <el-form :model="qrcodeForm" ref="qrcodeForm" class="qrcodeScan" :rules="qrcodeRule">
        <el-form-item>
          <div class="qrcodeTitle">送货单二维码扫描</div>
        </el-form-item>
        <el-form-item>
          <el-image :src="require('@/assets/images/scan.gif')" style="width:150px;height:150px"></el-image>
        </el-form-item>
        <div style="display:flex;align-item:center">
          <el-form-item prop="sendOrderId">
            <el-input
              placeholder="请输入二维码"
              size="small"
              style="width:200px"
              v-model="qrcodeForm.sendOrderId"
            />
          </el-form-item>
          <el-form-item style="margin-left:20px">
            <el-button @click="addRegistration('qrcodeForm')" size="small" type="primary">登记</el-button>
          </el-form-item>
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

import {
  pageRegistartion,
  deleteRegistration,
  getRegistrationInfo,
  updateRegistration,
  enterPark,
  exitPark,
  getRegistrationState
} from "@/api/registration";
import { getDict } from "@/api/dict";
import { formatTime, parseTime } from "../../../utils";
import { preRegistrationList } from "@/api/preRegistration";
import { getValue } from "@/api/dict";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    const validateNum = (rule, value, callback) => {
      if (/[\u4E00-\u9FA5]/.test(value)) {
        return callback(new Error("二维码不能存在中文"));
      } else {
        let flag = false;
        this.preRegistration.forEach((pre) => {
          if (value === pre.id) {
            flag = true;
            return;
          }
        });
        if (flag) {
          return callback();
        } else {
          return callback(new Error("二维码无效"));
        }
      }
    };
    return {
      tableHeight: window.innerHeight - 385,
      isExit: false,
      index: 1,
      ischange: 1,
      tableData: [],
      carNo: "",
      car: "",
      totalPages: "100",
      size: "",
      carTypes: [],
      carType: "",
      carState: "",
      currentPage: 1,
      carStates: [],
      driverPhone: "",
      num: 1,
      qrcodeOpen: false,
      qrcodeForm: {
        sendOrderId: "",
      },
      qrcodeRule: {
        sendOrderId: [
          {
            validator: validateNum, // 自定义验证
            trigger: ["blur", "change"],
          },
        ],
      },
      preRegistration: [],
    };
  },
  mounted() {
    getValue("登记车辆类型").then((res) => {
      this.carTypes = res.data;
      getRegistrationState().then((res) => {
        this.carStates = res.data;
        preRegistrationList().then((res) => {
          this.preRegistration = res.data;
        });
        this.registrationList();
      });
    });
  },
  methods: {
    handleCurrentChange(val) {
      this.num = val;
      let curr = val - 1;
      let data = {},
        pageNum = curr,
        pageSize = 10;
      pageRegistartion(data, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          var content = res.data.content;
          this.setCarType(content);
          this.tableData = content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalPages * 10;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toRegistration() {
      this.qrcodeOpen = true;
    },
    setCarType(data) {
      data.forEach((d) => {
        this.carTypes.forEach((car) => {
          if (car.dictValue == d.carType) {
            d["carTypeName"] = car.dictKey;
          }
        });
      });
    },
    addRegistration(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$router.push({
            path: "/addRegistration",
            query: { item: this.qrcodeForm.sendOrderId },
          });
        }
      });
    },
    updataRegistration(index, rows) {
      var item = rows[index];
      this.$router.push({
        path: "/editRegistration",
        query: { item: item.id },
      });
    },
    changBg(index) {
      this.ischange = index;
    },
    registrationList() {
      let data = {},
        pageNum = 0,
        pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageRegistartion(data, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          var content = res.data.content;
          this.setCarType(content);
          this.tableData = content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalPages * 10;
          loading.close();
        }
      });
    },
    search() {
      this.currentPage = 1;
      let data = {
          carNo: this.carNo,
          state: this.carState,
          carType: this.carType,
        },
        pageNum = 0,
        pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageRegistartion(data, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          loading.close();
          var content = res.data.content;
          this.setCarType(content);
          this.tableData = content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalPages * 10;
        } else {
          loading.close();
        }
      });
    },
    enterPark(index, rows) {
      this.$confirm("是否进园", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        enterPark(rows[index].id).then((res) => {
          this.$message({
            message: "进园成功",
            type: "success",
          });
          this.registrationList();
        });
      });
    },
    exitPark(index, rows) {
      this.$confirm("是否出园", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        exitPark(rows[index].id).then((res) => {
          this.$message({
            message: "出园成功",
            type: "success",
          });
          this.registrationList();
        });
      });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.operation >>> .el-button {
  width: 65px;
  margin-left: 12px;
}

.qrcodeScan {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.qrcodeScan >>> .el-form-item {
  margin-bottom: 0px;
}

.qrcodeTitle {
  font-size: 24px;
}
</style>