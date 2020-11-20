<template>
  <div class="main">
    <div>计划进展</div>
    <div class="planitem">
      <div class="planitemLeft">
        <div class="circlePercent" @click="activeItem(1)" :class="activeIndex == 1?'active':''">
          <div>
            <el-progress type="circle" :percentage="25" :width="70" :stroke-width="12"></el-progress>
          </div>
          <div>
            <div>交付计划</div>
            <div>80%</div>
          </div>
        </div>
        <div class="circlePercent" @click="activeItem(2)" :class="activeIndex == 2?'active':''">
          <div>
            <el-progress type="circle" :percentage="25" :width="70" :stroke-width="12"></el-progress>
          </div>
          <div>
            <div>生产计划</div>
            <div>80%</div>
          </div>
        </div>
        <div class="circlePercent" @click="activeItem(3)" :class="activeIndex == 3?'active':''">
          <div>
            <el-progress type="circle" :percentage="25" :width="70" :stroke-width="12"></el-progress>
          </div>
          <div>
            <div>物料需求计划</div>
            <div>80%</div>
          </div>
        </div>
        <div class="circlePercent" @click="activeItem(4)" :class="activeIndex == 4?'active':''">
          <div>
            <el-progress type="circle" :percentage="25" :width="70" :stroke-width="12"></el-progress>
          </div>
          <div>
            <div>供货计划</div>
            <div>80%</div>
          </div>
        </div>
      </div>
      <div class="planitemRight">
        <el-table
          :data="tableData"
          style="width: 98%"
          :header-cell-style="tableHeaderColor"
          height="525px"
        >
          <el-table-column type="index" label="序号"></el-table-column>
          <el-table-column prop="name" label="代办事项"></el-table-column>
          <el-table-column prop="date" label="截止日期" width="180"></el-table-column>
        </el-table>
        <p class="getMore">加载更多</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: "1",
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄"
        }
      ]
    };
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    activeItem(index) {
      this.activeIndex = index;
    }
  }
};
</script>

<style scoped>
.main {
  width: 100%;
  background: white;
  margin-top: 16px;
}
.main > div:first-child {
  padding: 16px;
}
.planitem {
  width: 100%;
  display: flex;
}
.planitemLeft {
  padding: 16px;
  padding-top: 0px;
  padding-right: 0px;
}
.planitemLeft > div {
  width: 200px;
  height: 140px;
  background: white;
  border-right: 1px solid #e6e6e6;
  opacity: 0.5;
}
.planitemLeft > div:hover {
  cursor: pointer;
}
.planitemRight {
  flex: 1;
  padding-left: 32px;
  padding-right: 10px;
}
.circlePercent {
  display: flex;
  justify-content: center;
  align-items: center;
}
.circlePercent > div:nth-of-type(2) {
  margin-left: 16px;
}
.circlePercent > div:nth-of-type(2) > div:nth-child(1) {
  font-size: 14px;
  text-align: center;
  margin-bottom: 10px;
}
.circlePercent > div:nth-of-type(2) > div:nth-child(2) {
  font-size: 18px;
  font-weight: bold;
  text-align: center;
  margin-bottom: 10px;
}
.main /deep/.el-table--scrollable-y .el-table__body-wrapper {
  overflow: hidden;
}
.getMore {
  color: gray;
  width: 100%;
  text-align: center;
  cursor: pointer;
}
.active {
  width: 200px;
  height: 140px;
  background: white;
  border: 1px solid #e6e6e6;
  border-right: none!important;
  opacity: 1!important;
}
</style>