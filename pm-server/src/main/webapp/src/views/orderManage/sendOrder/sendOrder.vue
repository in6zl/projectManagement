<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="queryParams.code"
                :placeholder="$t('main.placeInsert')+$t('supMana.sendOrder.sendOrderNo')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                v-model="queryParams.sendAddress"
                :placeholder="$t('main.placeInsert')+$t('supMana.sendOrder.sendAddress')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="three">
              <el-select
                v-model="queryParams.state"
                :placeholder="$t('main.choseDate')+$t('supMana.sendOrder.state')"
                size="small"
                style="width:95%"
                maxlength="30"
                clearable
              >
                <el-option
                  v-for="item in states"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="queryParams.carNo"
                :placeholder="$t('main.placeInsert')+$t('supMana.sendOrder.carNo')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                v-model="queryParams.driverName"
                :placeholder="$t('main.placeInsert')+$t('supMana.sendOrder.driverName')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="three">
              <el-input
                v-model="queryParams.driverPhone"
                :placeholder="$t('main.placeInsert')+$t('supMana.sendOrder.driverPhone')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="mini"
            @click="search"
          >{{ $t('button.search') }}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{ $t('supMana.sendOrder.sendOrderList') }}</div>
          <el-button
            size="small"
            type="primary"
            v-if="haveBtn('sendOrder:add')"
            @click="toAdd()"
          >{{ $t('button.add') }}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            prop="code"
            :label="$t('supMana.sendOrder.sendOrderNo')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="sendAddress"
            :label="$t('supMana.sendOrder.sendAddress')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="carNo"
            :label="$t('supMana.sendOrder.carNo')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="driverName"
            :label="$t('supMana.sendOrder.driverName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="driverPhone"
            :label="$t('supMana.sendOrder.driverPhone')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="stateName"
            :label="$t('supMana.sendOrder.state')"
            :show-overflow-tooltip="true"
          >
            <!-- <template slot-scope="scope">
              <span style="margin-left: 10px">{{ getStateName(scope.row.state) }}</span>
            </template>-->
          </el-table-column>
          <el-table-column
            prop="sendDate"
            :label="$t('supMana.sendOrder.sendDate')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="createTime"
            :label="$t('table.createTime')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip content="打印预览" placement="top">
                <svg-icon icon-class="look" @click.native="lookRow(scope.$index, tableData)" />
              </el-tooltip>
              <el-tooltip content="发车" placement="top" v-if="scope.row.state < 2">
                <svg-icon icon-class="car" @click.native="fache(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="编辑" placement="top">
                <svg-icon
                  icon-class="edit"
                  v-if="haveBtn('sendOrder:edit')&&scope.row.state < 2"
                  @click.native="editSendOrder(scope.$index,tableData)"
                />
              </el-tooltip>
              <el-tooltip content="删除" placement="top" v-if="scope.row.state < 2">
                <svg-icon
                  icon-class="delete"
                  v-if="haveBtn('sendOrder:delete')"
                  @click.native="deleteRow(scope.$index, tableData)"
                />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{ queryParams.totalElements }} 项</div>
      <div>
        <el-pagination
          :current-page="queryParams.pageNum + 1"
          :page-size="queryParams.pageSize"
          :total="queryParams.totalElements"
          layout="prev, pager, next"
          background
          small
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
        />
      </div>
    </div>
  </div>
</template>

<script>
import {
  deleteSendOrder,
  searchSendOrder,
  sendSendOrder,
} from "@/api/sendOrder";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      states: [
        { name: "未送货", value: 1 },
        { name: "在途中", value: 2 },
        { name: "已登记", value: 3 },
        { name: "等待进园", value: 4 },
        { name: "正在进园", value: 5 },
        { name: "园区中", value: 6 },
        { name: "已进厂", value: 7 },
        { name: "已出厂", value: 8 },
        { name: "正在出园", value: 9 },
        { name: "已出园", value: 10 },
        { name: "已完成", value: 11 },
        { name: "已撤销", value: 12 },
      ],
      queryParams: {
        pageNum: 0,
        pageSize: 10,
        totalElements: 0,
        sendOrderNo: "",
        sendAddress: "",
        carNo: "",
        driverName: "",
        driverPhone: "",
        state: "",
      },
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 380,
      tableData: [],
      num: 1,
      sendSendOrder: {
        pageNum: 0,
        pageSize: 10,
        totalElements: 0,
        sendOrderNo: "",
        sendAddress: "",
        carNo: "",
        driverName: "",
        driverPhone: "",
        state: "",
      },
    };
  },
  mounted() {
    this.searchSendOrder();
  },
  methods: {
    getStateName(state) {
      let stateName;
      this.states.find((item) => {
        if (item.value === state) {
          stateName = item.name;
        }
      });
      return stateName;
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },

    handleCurrentChange(val) {
      this.num = val;
      this.sendSendOrder.pageNum = val - 1;
      searchSendOrder(this.sendSendOrder).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.queryParams.totalElements = res.data.totalElements;
        }
      });
    },

    handleSizeChange() {},

    toAdd() {
      this.$router.push("/addSendOrder");
    },

    changBg(index) {
      this.ischange = index;
      this.$router.push("/detailSendOrder");
    },

    searchSendOrder() {
      this.sendSendOrder.sendOrderNo = this.queryParams.sendOrderNo;
      this.sendSendOrder.sendAddress = this.queryParams.sendAddress;
      this.sendSendOrder.carNo = this.queryParams.carNo;
      this.sendSendOrder.driverName = this.queryParams.driverName;
      this.sendSendOrder.driverPhone = this.queryParams.driverPhone;
      this.sendSendOrder.state = this.queryParams.state;
      searchSendOrder(this.queryParams).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.queryParams.totalElements = res.data.totalElements;
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteSendOrder(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            let number =
              rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
            this.handleCurrentChange(number);
          }
        });
      });
    },
    fache(index, rows) {
      this.$confirm("确认发车将无法删除,是否确认发车?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        sendSendOrder(rows[index].id).then((res) => {
          this.$message({
            message: "发车成功",
            type: "success",
          });
          this.searchSendOrder();
        });
      });
    },
    editSendOrder(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editSendOrder", query: { id: id } });
    },
    search() {
      const data = this.queryParams;
      data.pageNum = 0;
      data.pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.searchSendOrder();
      loading.close();
    },
    lookRow(index, tableData) {
      const id = tableData[index].id;
      this.$router.push({ path: "/detailSendOrder", query: { id: id } });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
