<template>
  <div class="main">
    <div class="main-top">
      <div style="float:left" class="subtit">供应商发货统计</div>
      <div style="float:right">
        <el-date-picker
          v-model="value1"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          size="small"
          end-placeholder="结束日期"
        ></el-date-picker>
      </div>
    </div>
    <div id="container"></div>
  </div>
</template>
<script>
import { Line } from "@antv/g2plot";

export default {
  data() {
    return {
      value1: "",
    };
  },
  mounted() {
    fetch("https://g2plot.antv.vision/zh/examples/data/emissions.json")
      .then((res) => res.json())
      .then((data) => {
        const linePlot = new Line(document.getElementById("container"), {
          title: {
            visible: false,
            text: "The causes of CO2 emissions",
          },
          padding: "auto",
          forceFit: true,
          data,
          xField: "year",
          yField: "value",
          seriesField: "category",
          xAxis: {
            type: "time",
          },
          yAxis: {
            label: {
              // 数值格式化为千分位
              formatter: (v) =>
                `${v}`.replace(/\d{1,3}(?=(\d{3})+$)/g, (s) => `${s},`),
            },
          },
          responsive: true,
        });

        linePlot.render();
      });
  },
};
</script>

<style lang="scss" scoped>
.main {
  background: white;
  border-radius: 5px;
  padding: 16px;
  margin-right: 16px;
  height: 450px;
}
.el-tabs__nav-wrap::after {
  display: none !important;
}
.main-top {
  height: 34px;
  line-height: 34px;
  margin-bottom: 10px;
}
</style>