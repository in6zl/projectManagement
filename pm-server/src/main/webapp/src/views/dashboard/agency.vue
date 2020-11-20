<template>
  <div class="main">
    <div class="main-top">
      <div class="subtit">代办事项</div>
    </div>
    <el-tabs v-model="editableTabsValue"  style="margin-top:10px">
      <el-tab-pane :key="name" v-for="name in state" :label="name" :name="name">
        <el-table :data="tableData" :header-cell-style="tableHeaderColor" style="width: 100%">
          <el-table-column label="编号" prop="date" fixed="left"></el-table-column>
          <el-table-column label="成品" prop="name" fixed="left"></el-table-column>
          <el-table-column prop="address" label="数量" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column fixed="right" label="操作" width="50">
            <template slot-scope="scope">
              <el-button @click="handleClick(scope.row)" type="text" size="small">生产</el-button>
            </template>
          </el-table-column>
        </el-table>
        <p class="getMore">加载更多</p>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import { Line } from "@antv/g2plot";

export default {
  data() {
    return {
      value1: "",
      state: ["待生产", "待出库", "待入库"],
      editableTabsValue: "待生产",
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  mounted() {},
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.main {
  background: white;
  border-radius: 5px;
  padding: 16px;
  height: 450px;
}
.main-top {
  height: 44px;
  line-height: 34px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ebebeb;
}
.getMore {
  color: gray;
  width: 100%;
  text-align: center;
  cursor: pointer;
}
</style>