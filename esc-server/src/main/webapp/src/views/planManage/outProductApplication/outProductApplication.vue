<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="searchParam.saleNo"
                :placeholder="$t('main.placeInsert')+'销售单号'"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                v-model="searchParam.address"
                :placeholder="$t('main.placeInsert')+'出货地址'"
                size="small"
                style="width:95%"
                maxlength="30"
              />
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
          <div style="line-height:34px" class="subtit">出货申请单</div>
          <!--          <el-button-->
          <!--            size="small"-->
          <!--            :class="{changBg:ischange == 1}"-->
          <!--            @click="toAddFaOutProductOrder(),changBg(1)"-->
          <!--          >{{ $t('button.add') }}</el-button>-->
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight-40"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column prop="saleNo" label="销售单号" :show-overflow-tooltip="true" />
          <el-table-column prop="outDate" label="出货时间" :show-overflow-tooltip="true" />
          <el-table-column prop="address" label="出货地址" :show-overflow-tooltip="true" />
          <el-table-column prop="receiver" label="收货人" :show-overflow-tooltip="true" />
          <el-table-column prop="receiverPhone" label="收货人手机" :show-overflow-tooltip="true" />
          <el-table-column prop="isCompile" label="状态" :show-overflow-tooltip="true" />
          <el-table-column prop="opration" label="操作" align="center" width="120">
            <template slot-scope="scope">
              <el-tooltip
                content="创建出库单"
                placement="top"
                v-if="haveBtn('outboundOrderProduct:add') && scope.row.carSchedule"
              >
                <svg-icon
                  icon-class="create"
                  @click.native="addOutboundOrderProduct(scope.$index,tableData)"
                />
              </el-tooltip>
              <el-tooltip content="创建车辆排班" placement="top" v-if="!scope.row.carSchedule">
                <svg-icon icon-class="car" @click.native="openCarSchedule(scope.row.id)" />
              </el-tooltip>
              <el-tooltip content="取消车辆排班" placement="top" v-if="scope.row.carSchedule">
                <svg-icon icon-class="car" @click.native="cancleCarSchedule(scope.row.carSchedule.id)" />
              </el-tooltip>
              <el-tooltip content="修改" placement="top" v-if="haveBtn('application:edit')">
                <svg-icon
                  icon-class="edit"
                  @click.native="editApplication(scope.$index,tableData)"
                />
              </el-tooltip>
              <el-tooltip content="删除" placement="top" v-if="haveBtn('application:delete')">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{ pageStart }} 至 {{ pageEnd }} 项结果，共 {{ searchParam.pageable.totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          background
          small
          @current-change="handleCurrentChange"
          :page-count="searchParam.pageable.totalPages"
          :current-page.sync="searchParam.pageable.pageIndex"
        />
      </div>
    </div>

 <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible1"
      width="58%"
    >
    <el-form
        ref="ruleFormCarSchedule"
        :model="ruleFormCarSchedule"
        :rules="rulesruleFormCarSchedule"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="车辆" prop="car" size="small">
          <el-select
            v-model="ruleFormCarSchedule.car"
            filterable
            placeholder="请选择"
            style="width: 100%"
            value-key="id"
          >
            <el-option v-for="item in carList" :key="item.id" :label="item.carNo" :value="item" />
          </el-select>
        </el-form-item>
        <el-form-item size="small" label="开始时间" prop="startTime">
          <el-date-picker
            v-model="ruleFormCarSchedule.startTime"
            type="datetime"
            placeholder="选择开始时间"
            size="small"
            style="width:95%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item size="small" label="结束时间" prop="endTime">
          <el-date-picker
            v-model="ruleFormCarSchedule.endTime"
            type="datetime"
            placeholder="选择结束时间"
            size="small"
            style="width:95%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button
            size="small"
            type="primary"
            @click="submitCarSchedule('ruleFormCarSchedule')"
          >确定</el-button>
          <!-- <el-button
            size="small"
            type="primary"
            @click="cancleCarSchedule"
            v-if="ruleFormCarSchedule.id"
          >撤销车辆排班</el-button> -->
          <!-- <el-button size="small" @click="searchCarSchedule()">查询</el-button> -->
        </el-form-item>
      </el-form>
 </el-dialog>

    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      width="58%"
    >
      
      <!-- <full-calendar
        :config="config"
        :events="events"
        selectable="true"
        ref="calendar"
        @event-selected="eventClick"
        @day-click="dayClick"
        @select="handleSelect"
      ></full-calendar> -->
       
 

      <div id='calendar' ref="a"></div>
    
    </el-dialog>
    
  </div>
</template>

<script>
import { listCar } from "@/api/car";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import {
  listCarSchedule,
  addCarSchedule,
  updateCarSchedule,
  deleteCarSchedule,
} from "@/api/carSchedule";
// import { FullCalendar } from "vue-full-calendar";
// import "fullcalendar/dist/fullcalendar.css";
import {
  pageApplication,
  deleteApplication,
} from "@/api/outProductApplication";
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    const endTimeValidator = (rule, value, callback) => {
      if (
        this.ruleFormCarSchedule.startTime.getTime() >
        this.ruleFormCarSchedule.endTime.getTime()
      ) {
        return callback(new Error("结束时间必须大于开始时间"));
      } else {
        return callback();
      }
    };
    return {
      title: "",
      organizationName: "",
      organizationType: "",
      createTime: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 290,
      tableData: [],
      options: ["已完成", "未完成"],
      pageable: {},
      totalElements: "",
      totalPages: "",
      searchForm: {
        pageNo: "0",
        pageSize: "10",
        saleNo: "",
        address: "",
      },
      searchParam: {
        saleNo: undefined,
        address: undefined,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
      ruleFormCarSchedule: {
        id: undefined,
        car: undefined,
        startTime: undefined,
        endTime: undefined,
        outProductApplicationId: undefined,
      },
      rulesruleFormCarSchedule: {
        car: [{ required: true, message: "请选择车辆", trigger: "blur" }],
        startTime: [
          { required: true, message: "请选择开始时间", trigger: "blur" },
        ],
        endTime: [
          { required: true, message: "请选择结束时间", trigger: "blur" },
          { validator: endTimeValidator, trigger: ["blur", "change"] },
        ],
      },
      carList: [],
      dialogFormVisible: false,
      dialogFormVisible1:false,
      config: {
        firstDay: "0", //以周日为每周的第一天
        // weekends: true,//是否在日历中显示周末
        locale: "zh-cn", //语言
        defaultView: "month", //默认按月显示
        height: "auto", //高度
        fixedWeekCount: false, //是否固定显示六周
        // weekMode:"liquid",//周数不定，每周的高度可变，整个日历高度不变
        allDaySlot: false,
        // allDay:true,
        header: {
          //表头信息
          left: "prev, next, today",
          center: "title",
          right: "dayGridMonth,timeGridWeek,timeGridDay",
        },
      },
      events: [
        {
          id: 1,
          title: "出差",
          start: "2019-04-03",
          end: "2019-04-05",
        },
        {
          id: 2,
          title: "春游",
          start: "2019-04-12",
        },
      ],
    };
  },
  mounted() {
    listCar({}).then((res) => {
      this.carList = res.data;
    });
    this.page();
    this.listCarSchedule();
    // document.addEventListener("DOMContentLoaded", function () {
     
    // });
   
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageable.pageNo * this.searchParam.pageable.pageSize +
        1
      );
    },
    pageEnd() {
      return (
        (this.searchParam.pageable.pageNo + 1) *
        this.searchParam.pageable.pageSize
      );
    },
  },
  methods: {
    handleSelect() {
      console.log(1);
    },
    submitCarSchedule(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.ruleFormCarSchedule.id) {
            updateCarSchedule(this.getCarScheduleParam()).then((res) => {
              if (res.code === "0") {
                this.$message({
                  message: "创建成功",
                  type: "success",
                });
                this.dialogFormVisible = false;
                this.page();
                this.cleaRuleFormCarSchedule();
              } else {
                this.$message({
                  message: res.message,
                  type: "warning",
                });
              }
            });
          } else {
            addCarSchedule(this.getCarScheduleParam()).then((res) => {
              if (res.code === "0") {
                this.$message({
                  message: "创建成功",
                  type: "success",
                });
                this.dialogFormVisible = false;
                this.dialogFormVisible1 = false;
                this.page();
                this.cleaRuleFormCarSchedule();
              } else {
                this.$message({
                  message: res.message,
                  type: "warning",
                });
              }
            });
          }
        }
      });
    },
    cancleCarSchedule(carScheduleId) {
      deleteCarSchedule(carScheduleId).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "撤销成功",
            type: "success",
          });
          // this.dialogFormVisible = false;
          this.page();
          this.cleaRuleFormCarSchedule();
        } else {
          this.$message({
            message: res.message,
            type: "warning",
          });
        }
      });
    },
    cleaRuleFormCarSchedule() {
      for (let key in this.ruleFormCarSchedule) {
        this.ruleFormCarSchedule[key] = undefined;
      }
    },
    listCarSchedule() {
      listCarSchedule(this.getCarScheduleParam()).then((res) => {
        if (res.code === "0") {
          this.events = [];
          res.data.map((item) => {
            this.events.push({
              id: item.carNo,
              title: item.carNo,
              start: item.startTime,
              end: item.endTime,
            });
          });
        }
      });
    },
    getCarScheduleParam() {
      let searchParam = new Object();
      searchParam.id = this.ruleFormCarSchedule.id;
      searchParam.car = this.ruleFormCarSchedule.car;
      searchParam.outProductApplicationId = this.ruleFormCarSchedule.outProductApplicationId;
      searchParam.startTime = this.formateTime(
        this.ruleFormCarSchedule.startTime
      );
      searchParam.endTime = this.formateTime(this.ruleFormCarSchedule.endTime);
      return searchParam;
    },
    formateTime(time) {
      if (time) {
        // console.log('time',time.getTime() );
        // time=(new Date()).setTime(time.getTime() );
        let year = time.getFullYear();
        let month =
          time.getMonth() < 9
            ? "0" + (time.getMonth() + 1)
            : time.getMonth() + 1;
        let date = time.getDate();
        let hour =
          time.getHours() <= 9 ? "0" + time.getHours() : time.getHours();
        let minute =
          time.getMinutes() <= 9 ? "0" + time.getMinutes() : time.getMinutes();
        let second =
          time.getSeconds() <= 9 ? "0" + time.getSeconds() : time.getSeconds();
        return (
          year +
          "-" +
          month +
          "-" +
          date +
          " " +
          hour +
          ":" +
          minute +
          ":" +
          second
        );
      } else {
        return time;
      }
    },
    searchCarSchedule() {
      this.listCarSchedule();
    },
    eventClick(event) {
      console.log("eventClick");
      //events的点击事件
      this.editItem = event;
      this.isAdd = true;
    },
    dayClick(date, jsEvent, view) {
      console.log(date);
      //日期的点击事件
      this.editItem = {};
      this.isAdd = true;
    },
    showCarSchedule() {
      console.log(2);
    },
    closeDialog() {
      console.log("close");
      this.dialogFormVisible = false;
      this.clearRuleFormCarSchedule();
    },
    openCarSchedule(carScheduleId) {
      this.title = "创建车辆排班";
      this.ruleFormCarSchedule.outProductApplicationId = carScheduleId;
      this.dialogFormVisible = true;
      this.createCalendar();
    },
    createCalendar(){
      this.$nextTick(() =>{
        var calendarEl = document.getElementById("calendar");
        console.log("calendarEl",calendarEl);
        var calendar = new FullCalendar.Calendar(calendarEl, {
          selectable: true,
          select: (info) =>{
            // console.log('info ',info);
            this.dialogFormVisible1= true;
            this.ruleFormCarSchedule.startTime=info.start
            this.ruleFormCarSchedule.endTime=new Date(info.end.getTime()- 24*60*60*1000)
          },
          events:this.events
        });
        calendar.render();
      })
    },
    showDialogFormVisible1(){
      console.log(11);
      this.dialogFormVisible1= true
    },
    editCarSchedule(outProductAppliaction) {
      this.title = "编辑车辆排班";
      this.ruleFormCarSchedule.outProductApplicationId =
        outProductAppliaction.id;
      this.ruleFormCarSchedule.id = outProductAppliaction.carSchedule.id;
      this.dialogFormVisible = true;
      this.ruleFormCarSchedule.car = outProductAppliaction.carSchedule.car;
      this.ruleFormCarSchedule.startTime = new Date(
        outProductAppliaction.carSchedule.startTime
      );
      this.ruleFormCarSchedule.endTime = new Date(
        outProductAppliaction.carSchedule.endTime
      );
      this.createCalendar();
    },
    clearRuleFormCarSchedule() {
      for (let key in this.ruleFormCarSchedule) {
        this.ruleFormCarSchedule[key] = undefined;
      }
    },
    openDetial(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/printOutProductOrder", query: { id: id } });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddFaOutProductOrder() {
      this.$router.push("/addApplication");
    },
    addOutboundOrderProduct(index, rows) {
      let id = rows[index].id;
      this.$router.push({
        path: "/addOutboundOrderProduct",
        query: { applicationId: id },
      });
    },
    changBg(index) {
      this.ischange = index;
    },
    page() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageApplication(this.searchParam)
        .then((res) => {
          if (res.code === "0") {
            this.tableData = res.data.content;
            this.searchParam.pageable.totalPages = res.data.totalPages;
            this.searchParam.pageable.pageNo = res.data.pageable.pageNumber;
            this.searchParam.pageable.pageIndex =
              res.data.pageable.pageNumber + 1;
            this.searchParam.pageable.pageSize = res.data.pageable.pageSize;
            for (const item of this.tableData) {
              item.isCompile == 0
                ? (item.isCompile = "未完成")
                : (item.isCompile = "已完成");
            }
          }
          loading.close();
        })
        .catch(() => {
          loading.close();
        });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteApplication(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            this.searchParam.pageable.pageNo =
              rows.length === 0 && this.searchParam.pageable.pageNo != 0
                ? this.searchParam.pageable.pageNo - 1
                : this.searchParam.pageParam.pageNo;
            this.page();
          }
        });
      });
    },
    editApplication(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editApplication", query: { id: id } });
    },
    search() {
      (this.searchParam = {
        saleNo: this.searchForm.saleNo,
        address: this.searchForm.address,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      }),
        this.page();
    },
    handleCurrentChange(cpage) {
      this.searchParam.pageable.pageNo = index - 1;
      this.faOutProductOrderPage();
    },
    createDeliveryPlan() {
      this.$router.push("/addDeliveryPlan");
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
