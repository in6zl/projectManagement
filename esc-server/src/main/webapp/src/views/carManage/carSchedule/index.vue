<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form ref="form" :model="searchForm">
          <search-form>
            <el-form-item prop="carId" slot="first">
              <el-select
                v-model="searchForm.carId"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('car.carNo')"
                value
                clearable
              >
                <el-option
                  v-for="item in carList"
                  :key="item.id"
                  :label="item.carNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item prop="outProductOrderId" slot="second">
              <el-select
                v-model="searchForm.outProductOrderId"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('car.outProductOrderNo')"
                value
                clearable
              >
                <el-option
                  v-for="item in outProductOrderList"
                  :key="item.id"
                  :label="item.orderNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>

            <el-button
              style="float:right;margin:16px 16px 16px 0;"
              type="primary"
              size="mini"
              @click="search"
              slot="three"
            >{{ $t('button.search') }}</el-button>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <!-- <div class="userEdit">
          <div style="line-height:34px">{{ $t('route.carSchedule') }}</div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column prop="carNo" :label="$t('car.carNo')" :show-overflow-tooltip="true" />
          <el-table-column
            prop="outProductOrderNo"
            :label="$t('car.outProductOrderNo')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="startTime"
            :label="$t('car.startTime')"
            :show-overflow-tooltip="true"
          />
          <el-table-column prop="endTime" :label="$t('car.endTime')" :show-overflow-tooltip="true" />
        </el-table>-->
        <full-calendar
          :config="config"
          :events="events"
          ref="calendar"
          @event-selected="eventClick"
          @day-click="dayClick"
          v-if="isShow"
        ></full-calendar>
      </div>
    </div>
    <!-- <div class="paging">
      <div>显示第 1 至 10 项结果，共{{ totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="totalElements"
          background
          small
          @current-change="handleCurrentChange"
        />
      </div>
    </div>-->
  </div>
</template>

<script>
import { listCarSchedule, deleteCarSchedule } from "@/api/carSchedule";
import { listCar } from "@/api/car";
import { getFaOutProductOrderList } from "@/api/faOutProductOrder";
import searchForm from "@/components/commonTop/index";
import { FullCalendar } from "vue-full-calendar";
import "fullcalendar/dist/fullcalendar.css";
export default {
  components: {
    searchForm,
    FullCalendar,
  },
  data() {
    return {
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
      newItem: {},
      editItem: {},
      // 查询参数
      searchForm: {
        carId: "",
        outProductOrderId: "",
        searchStartTime: undefined,
        searchEndTime: undefined,
        pageNo: 0,
        pageSize: 10,
      },
      carList: [],
      outProductOrderList: [],
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 400,
      tableData: [],
      totalElements: 0,
      totalPages: 0,
      isShow: true, //是否显示日历
    };
  },
  mounted() {
    this.page();
    listCar({}).then((res) => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });
    getFaOutProductOrderList({}).then((res) => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
      }
    });
  },
  watch: {
    "$store.state.app.language": function (curVal, oldVal) {
      if (curVal == "en") {
        this.config.locale = "en";
      } else {
        this.config.locale = "zh-cn";
      }
      this.isShow = false;
      this.$nextTick(() => {
        this.isShow = true;
      });
    },
  },
  methods: {
    changeDate() {
      // this.$refs.calendar.fireMethod('gotoDate', this.selectDate)
      this.$refs.calendar.fireMethod("prev");
    },
    eventClick(event) {
      //events的点击事件
      this.editItem = event;
      this.isAdd = true;
    },
    dayClick(date, jsEvent, view) {
      //日期的点击事件
      this.editItem = {};
      this.isAdd = true;
    },
    addItem(detail) {
      this.newItem = JSON.parse(detail);
      if (this.editItem.id) {
        //如果是编辑，就删掉该条
        this.events.forEach((el, ind) => {
          if (el.id == this.editItem.id) {
            this.events.splice(ind, 1);
          }
        });
      }
      this.events.push({
        id: this.editItem.id ? this.editItem.id : this.setUuid(),
        title: this.newItem.title,
        start: this.newItem.period[0],
        end: this.newItem.period[1],
      });
    },
    setUuid() {
      var s = [];
      var hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4";
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);
      s[8] = s[13] = s[18] = s[23];
      var uuid = s.join("");
      return uuid;
    },

    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAdd() {
      this.$router.push("addCarSchedule");
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      const data = {
        car: {
          id: this.searchForm.carId,
        },
        outProductOrder: {
          id: this.searchForm.outProductOrderId,
        },
        searchStartTime: this.searchForm.searchStartTime,
        searchEndTime: this.searchForm.searchEndTime,
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
        },
      };
      listCarSchedule(data).then((res) => {
        if (res.code === "0") {
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
    handleCurrentChange(cpage) {
      this.searchForm.pageNo = cpage - 1;
      const data = {
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
        },
      };
      this.page(data);
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteCarSchedule(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            this.page();
          } else {
            this.$message.error(res.message);
          }
        });
      });
    },
    update(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "editCarSchedule", query: { id: id } });
    },
    search() {
      const data = {
        car: {
          id: this.searchForm.carId,
        },
        outProductOrder: {
          id: this.searchForm.outProductOrderId,
        },
        searchStartTime: this.searchForm.searchStartTime,
        searchEndTime: this.searchForm.searchEndTime,
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
        },
      };
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageCarSchedule(data)
        .then((res) => {
          if (res.code === "0") {
            loading.close();
            this.tableData = res.data.content;
            this.totalElements = res.data.totalElements;
            this.totalPages = res.data.totalPages;
          } else {
            loading.close();
          }
        })
        .catch(() => {
          loading.close();
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.roleManageTable {
  background: white;
  padding: 16px;
  & >>> .fc-prev-button {
    background: #409eff;
    color: white;
    border: none;
  }
  & >>> .fc-next-button {
    background: #409eff;
    color: white;
    border: none;
  }
  & >>> .fc-today-button {
    background: #409eff;
    color: white;
    border: none;
  }
  & >>> .fc-button {
    background: #409eff;
    color: white;
    border: none;
  }
  & >>> .fc-state-active {
    background: lightskyblue;
    color: white;
    box-shadow: none;
    outline: 0;
  }
}
</style>
