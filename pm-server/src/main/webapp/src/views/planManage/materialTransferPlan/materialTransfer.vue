<template>
  <div class="container">
    <!-- 未确认 -->
    <div class="main" v-show="activeName =='first'">
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
            <el-form-item size="small" slot="second">
              <el-date-picker
                v-model="searchreqTime"
                type="date"
                placeholder="选择日期"
                size="small"
                style="width:95%"
                value-format="yyyy-MM-dd"
              ></el-date-picker>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t("route.materialTransfer")}}</div>
          <div>
            <el-button
              size="small"
              @click="toAddDeliveryPlan(),changBg(1)"
              type="primary"
              v-if="haveBtn('materialTransfer:add')"
            >{{$t('button.add')}}</el-button>

            <el-button
              size="small"
              @click="changBg(3),sheetDialog()"
              :class="{changBg:ischange == 3}"
            >确认调货计划</el-button>
          </div>
        </div>
        <el-tabs v-model="activeName" @tab-click="handleTable">
          <el-tab-pane label="未确认" name="first"></el-tab-pane>
          <el-tab-pane label="已确认" name="second"></el-tab-pane>
        </el-tabs>
        <el-dialog
          :lock-scroll="false"
          title="确认调货计划"
          :visible.sync="dialogVisible"
          width="90%"
          :before-close="close"
        >
          <!-- 不可编辑表格只可以查看 -->
          <!-- <div
                style="width: 50%;float:left;background:white;padding:20px;border-right:1px solid lightgray;height:500px;overflow:auto"
              >
                <div style="margin-top:16px;margin-bottom: 16px;">调货计划副本</div>
                <el-table :data="noEdit">
                  <el-table-column type="index" label="序号"></el-table-column>
                  <el-table-column prop="faWarehouse.name" label="内仓"></el-table-column>
                  <el-table-column prop="material.name" label="物料名称"></el-table-column>
                  <el-table-column prop="supplier.name" label="供应商"></el-table-column>
                  <el-table-column prop="num" label="需求数量"></el-table-column>
                  <el-table-column prop="state" label="状态">
                    <template slot-scope="scope">
                      <span v-if="scope.row.state == 0">未确认</span>
                      <span v-else>已确认</span>
                    </template>
                  </el-table-column>
                  <el-table-column prop="requireTime" label="需求日期" width="150"></el-table-column>
                </el-table>
          </div>-->
          <div style="width:100%;background:white;height: 48px;">
            <el-button
              type="primary"
              size="small"
              style="float:right;margin-right:16px;margin-bottom:16px"
              @click="addRow"
            >添加数据</el-button>
            <el-button
              type="primary"
              @click="save"
              size="small"
              style="float:right;margin-right:16px;margin-bottom:16px"
            >保存</el-button>
          </div>
          <div>
            <el-table :data="dialogData" height="400">
              <el-table-column type="index" align="center" label="序号"></el-table-column>
              <el-table-column label="内仓">
                <template slot-scope="scope">
                  <!-- <el-select
                    v-model="scope.row.faWarehouse"
                    placeholder="请选择"
                    v-if="scope.row.isEdit && !scope.row.isFreeze"
                    size="mini"
                    clearable
                    value-key="id"
                  >-->
                  <el-select
                    v-model="scope.row.faWarehouse"
                    placeholder="请选择"
                    size="mini"
                    clearable
                    value-key="id"
                  >
                    <el-option
                      v-for="item in faWarehouseOptions"
                      :key="item.id"
                      :label="item.name"
                      :value="item"
                    ></el-option>
                  </el-select>
                  <!-- <span v-else>{{scope.row.faWarehouse.name || ""}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="物料名称">
                <template slot-scope="scope">
                  <el-select
                    v-model="scope.row.material"
                    placeholder="请选择"
                    size="mini"
                    clearable
                    value-key="id"
                  >
                    <el-option
                      v-for="item in materialList"
                      :key="item.id"
                      :label="item.name"
                      :value="item"
                    ></el-option>
                  </el-select>
                  <!-- <span v-else>{{scope.row.material.name || ""}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="供应商">
                <template slot-scope="scope">
                  <el-select
                    v-model="scope.row.supplier"
                    placeholder="请选择"
                    size="mini"
                    clearable
                    value-key="id"
                  >
                    <el-option
                      v-for="item in supplierList"
                      :key="item.id"
                      :label="item.name"
                      :value="item"
                    ></el-option>
                  </el-select>
                  <!-- <span v-else>{{scope.row.supplier.name || ""}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="需求数量">
                <template slot-scope="scope">
                  <el-input type="text" size="mini" style="width:60px" v-model="scope.row.num"></el-input>
                  <!-- <span v-else>{{scope.row.num}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="需求数量(旧)" prop="oldNum"></el-table-column>
              <el-table-column prop="oldRequireTime" label="需求日期(旧)" width="150"></el-table-column>
              <el-table-column label="需求日期" width="150">
                <template slot-scope="scope">
                  <!-- <span v-if="scope.row.isEdit"> -->
                  <el-date-picker
                    v-model="scope.row.reqTime"
                    type="date"
                    placeholder="选择日期"
                    size="small"
                    style="width:140px"
                    :picker-options="pickerDialgoOptions"
                    @change="chooseDialogDate(scope.$index)"
                    value-format="yyyy-MM-dd"
                  ></el-date-picker>
                  <!-- </span> -->
                  <!-- <span v-else>{{scope.row.reqTime}}</span> -->
                </template>
              </el-table-column>
              <el-table-column label="需求时间" width="150">
                <template slot-scope="scope">
                  <!-- <span v-if="scope.row.isEdit"> -->
                  <el-time-select
                    v-model="scope.row.materialTime"
                    @change="chooseTime(scope.row.reqTime,scope.row.materialTime,scope.$index)"
                    :picker-options="{
                      step: '00:10',
                      start: '00:00',
                      end: '23:50',
                    }"
                    placeholder="选择时间"
                    size="small"
                    style="width:120px"
                  ></el-time-select>
                  <!-- </span> -->
                  <!-- <span v-else>{{scope.row.materialTime}}</span> -->
                </template>
              </el-table-column>
              <el-table-column lable="操作" label="操作"  align="right">
                <template slot-scope="scope">
                  <svg-icon
                    icon-class="delete"
                    v-if="haveBtn('materialTransfer:delete')"
                    @click.native="deleteDialogRow(scope.$index,dialogData)"
                  ></svg-icon>
                  <!-- <svg-icon icon-class="edit" @click.native="handle(scope.$index)"></svg-icon> -->
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-dialog>
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
          @selection-change="handleSelectionChangeRoleManageTable"
        >
          <el-table-column type="selection" width="60" align="center" :selectable="selectable"></el-table-column>
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            width="240"
            label="内仓"
            prop="faWarehouse.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            width="240"
            label="物料"
            prop="material.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            width="240"
            label="供应商名称"
            prop="supplier.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column label="状态" width="240" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <span v-if="scope.row.state == 0">未确认</span>
              <span v-else>已确认</span>
            </template>
          </el-table-column>
          <el-table-column width="240" label="需求数量" prop="num" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            width="240"
            label="需求时间"
            prop="requireTime"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="opration"
            :label="$t('table.operation')"
            fixed="right"
            width="100"
            align="center"
          >
            <template slot-scope="scope">
              <svg-icon
                icon-class="edit"
                v-if="scope.row.state<3 && haveBtn('materialTransfer:edit')"
                @click.native="update(scope.$index,tableData)"
              ></svg-icon>
              <svg-icon
                icon-class="delete"
                v-if="scope.row.state<3 && haveBtn('materialTransfer:delete')"
                @click.native="deleteRow(scope.$index,tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="paging">
        <div>显示第 {{searchParam[0].pageable.pageStart}} 至 {{searchParam[0].pageable.pageEnd}} 项结果，共 {{searchParam[0].pageable.totalPages}} 页</div>
        <div>
          <el-pagination
            layout="prev, pager, next"
            background
            small
            :current-page.sync="searchParam[0].pageable.pageIndex"
            @current-change="handleCurrentChange"
            :page-count="searchParam[0].pageable.totalPages"
          ></el-pagination>
        </div>
      </div>
    </div>

    <!-- 已确认 -->

    <div class="main" v-show="activeName =='second'">
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
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div class="subtit" style="line-height:34px">{{$t("route.materialTransfer")}}</div>
          <div>
            <el-button
              size="small"
              @click="toAddDeliveryPlan(),changBg(1)"
              :class="{changBg:ischange == 1}"
              v-if="haveBtn('materialTransfer:add')"
            >{{$t('button.add')}}</el-button>
            <el-button @click="createSendOrder" size="small" v-if="haveBtn('sendOrder:add')" >生成送货单</el-button>
          </div>
        </div>
        <el-tabs v-model="activeName" @tab-click="handleTable">
          <el-tab-pane label="未确认" name="first"></el-tab-pane>
          <el-tab-pane label="已确认" name="second"></el-tab-pane>
        </el-tabs>
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="60" align="center" :selectable="selectable"></el-table-column>
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            label="内仓"
            width="120"
            prop="faWarehouse.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            label="物料"
            width="120"
            prop="material.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            label="供应商名称"
            width="120"
            prop="supplier.name"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column label="状态" width="120" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <span v-if="scope.row.state == 0">未确认</span>
              <span v-if="scope.row.state == 1">已确认</span>
              <span v-if="scope.row.state == 2">已取消</span>
              <span v-if="scope.row.state == 3">处理中</span>
              <span v-if="scope.row.state == 4">已完成</span>
            </template>
          </el-table-column>
          <el-table-column label="需求数量" width="120" prop="num" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            label="待处理数量"
            width="120"
            prop="unProcessNum"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <!-- <el-table-column
            label="处理中数量"
            width="120"
            prop="processingNum"
            :show-overflow-tooltip="true"
          ></el-table-column> -->
          <!-- <el-table-column label="处理失败数量" width="120" prop="processFailedNum" :show-overflow-tooltip="true"></el-table-column> -->
          <el-table-column
            label="已完成数量"
            width="100"
            prop="finishedNum"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            label="需求时间"
            width="160"
            prop="requireTime"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="opration"
            :label="$t('table.operation')"
            fixed="right"
            align="right"
          >
            <template slot-scope="scope">
              <svg-icon
                v-if="scope.row.state<3 && haveBtn('materialTransfer:edit')"
                icon-class="edit"
                @click.native="update(scope.$index,0)"
              ></svg-icon>
              <svg-icon
                v-if="scope.row.state<3 && haveBtn('materialTransfer:delete')"
                icon-class="delete"
                @click.native="deleteRow(scope.$index,tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="paging">
        <div>显示第 {{searchParam[1].pageable.pageStart}} 至 {{searchParam[1].pageable.pageEnd}} 项结果，共 {{searchParam[1].pageable.totalPages}} 页</div>
        <div>
          <el-pagination
            layout="prev, pager, next"
            background
            small
            @current-change="handleCurrentChange"
            :current-page.sync="searchParam[1].pageable.pageIndex"
            :page-count="searchParam[1].pageable.totalPages"
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
import { getSupplier } from "@/api/supplier";
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
      unconfirmedList: [], //未确认表格数据
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
      searchParam: [
        {
          wareHouseId: undefined,
          requireTime: undefined,
          state: [0],
          pageable: {
            pageNo: 0,
            pageSize: 10,
            totalPages: undefined,
            pageIndex: undefined,
            pageStart: undefined,
            pageEnd: undefined,
          },
        },
        {
          wareHouseId: undefined,
          requireTime: undefined,
          state: [],
          pageable: {
            pageNo: 0,
            pageSize: 10,
            totalPages: undefined,
            pageIndex: undefined,
            pageStart: undefined,
            pageEnd: undefined,
          },
        },
      ],
    };
  },
  computed: {
    pageStart0() {
      console.log(11);
      return (
        this.searchParam[0].pageable.pageNo *
          this.searchParam[0].pageable.pageSize +
        1
      );
    },
    pageEnd0() {
      return (
        (this.searchParam[0].pageable.pageNo + 1) *
        this.searchParam[0].pageable.pageSize
      );
    },
    pageStart1() {
      return (
        this.searchParam[1].pageable.pageNo *
          this.searchParam[1].pageable.pageSize +
        1
      );
    },
    pageEnd1() {
      return (
        (this.searchParam[1].pageable.pageNo + 1) *
        this.searchParam[1].pageable.pageSize
      );
    },
  },
  mounted() {
    let name = this.$route.query.name;
    this.activeName = name ? name : "first";
    this.tableState = this.activeName == "second" ? "1" : "0";
    this.list(this.searchParam[parseInt(this.tableState)]);
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
    getSupplier().then((res) => {
      this.supplierList = res.data;
    });
    this.getConfirmList();
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
      this.searchParam[parseInt(this.tableState)] = {
        wareHouseId: this.faWareHouse,
        requireTime: this.searchreqTime,
        state: this.getStateByTab(),
        pageable: {
          pageNo: 0,
          pageSize: this.pageSize,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.list(this.searchParam[parseInt(this.tableState)]);
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
        if (!item.num) {
          this.$message({
            message: "请填写数量",
            type: "error",
          });
          return;
        } else {
          if (!/^\d+\.*\d*/.test(item.num)) {
            this.$message({
              message: "数量必须是数字",
              type: "error",
            });
            return (istrue = false);
          }
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
            this.list(this.searchParam[parseInt(this.tableState)]);
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
      this.list(this.searchParam[parseInt(this.tableState)]);
      this.dialogVisible = false;
      done();
    },
    deleteDialogRow(index, tableData) {
      // 确认调货计划dialog删除改取消确认
      // this.deleteList.push(tableData[index]);
      tableData.splice(index, 1);
      this.list(this.searchParam[parseInt(this.tableState)]);
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
    toAddDeliveryPlan() {
      this.$router.push("/addMaterialTransfer");
    },
    changBg(index) {
      this.ischange = index;
    },
    list(data) {
      getList(data, data.pageable.pageNo, data.pageable.pageSize).then(
        (res) => {
          // debugger
          if (res.code === "0") {
            this.tableData = statisticsNum(res.data.content);
            this.searchParam[parseInt(this.tableState)].pageable.totalPages =
              res.data.totalPages;
            this.searchParam[parseInt(this.tableState)].pageable.pageNo =
              res.data.pageable.pageNumber;
            this.searchParam[parseInt(this.tableState)].pageable.pageIndex =
              res.data.pageable.pageNumber + 1;
            this.searchParam[parseInt(this.tableState)].pageable.pageSize =
              res.data.pageable.pageSize;
            this.searchParam[parseInt(this.tableState)].pageable.pageStart =
              this.searchParam[parseInt(this.tableState)].pageable.pageNo *
                this.searchParam[parseInt(this.tableState)].pageable.pageSize +
              1;
            this.searchParam[parseInt(this.tableState)].pageable.pageEnd =
              (this.searchParam[parseInt(this.tableState)].pageable.pageNo +
                1) *
              this.searchParam[parseInt(this.tableState)].pageable.pageSize;
            console.log(
              "this.searchParam[parseInt(this.tableState)]",
              this.searchParam[parseInt(this.tableState)]
            );
          }
        }
      );
    },
    getStateByTab() {
      let stateArray0 = [0];
      let stateArray1 = [1, 2, 3, 4];
      if (this.tableState == "0") {
        return stateArray0;
      } else if (this.tableState == "1") {
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
      }
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
              rows.splice(index, 1);
              this.searchParam.pageable.pageNo =
                rows.length === 0 && this.searchParam.pageable.pageNo != 0
                  ? this.searchParam.pageable.pageNo - 1
                  : this.searchParam.pageable.pageNo;
              this.list(this.searchParam[parseInt(this.tableState)]);
            }
          });
        })
    },
    update(index, rows) {
      let id = this.tableData[index].id;
      this.$router.push({ path: "/editMaterialTransfer", query: { id: id } });
    },
    search() {
      this.searchParam[parseInt(this.tableState)] = {
        wareHouseId: this.faWareHouse,
        requireTime: this.searchreqTime,
        state: this.getStateByTab(),
        pageable: {
          pageNo: 0,
          pageSize: this.pageSize,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.list(this.searchParam[parseInt(this.tableState)]);
    },
    handleCurrentChange(cpage) {
      this.searchParam[parseInt(this.tableState)].pageable.pageNo = cpage - 1;
      this.list(this.searchParam[parseInt(this.tableState)]);
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
// .container >>> .el-tabs__item {
//   background: white;
//   border: 1px solid lightgray !important;
//   box-sizing: border-box;
// }
// .container >>> .el-tabs__item {
//   background: white;
//   border: 1px solid lightgray !important;
//   border-left: none !important;
//   box-sizing: border-box;
// }
// .container >>> .is-active {
//   border-bottom: none !important;
// }
.el-table--scrollable-x {
  /deep/.el-table__body-wrapper::-webkit-scrollbar {
    width: 4px;
  }
  /deep/.gutter {
    width: 4px !important;
  }
}

.el-table--scrollable-x {
  /deep/.el-table__body-wrapper::-webkit-scrollbar-thumb {
    background-color: #d8d8d8ff;
    border-radius: 7px;
    // box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  }
}
/*定义滚动条的轨道颜色、内阴影及圆角*/
.el-table--scrollable-x {
  /deep/.el-table__body-wrapper::-webkit-scrollbar-track {
    // box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
    background-color: #fff;
    border-radius: 3px;
  }
}
</style>
