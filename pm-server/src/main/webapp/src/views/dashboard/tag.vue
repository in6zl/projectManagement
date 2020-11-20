<template>
  <div class="main">
    <div class="tag">
      <div>供货数量</div>
      <div>{{formatNum(234)}}</div>
      <div>
        <span>周同比:</span>
        <span>
          213
          <i class="el-icon-top"></i>
        </span>
      </div>
      <div class="chart" style="width:100%;height:100px"></div>
    </div>
    <div class="tag">
      <div>销售单数量</div>
      <div>{{formatNum(771)}}</div>
      <div>
        <span>周同比:</span>
        <span>
          213
          <i class="el-icon-top"></i>
        </span>
      </div>
      <div class="chart" style="width:100%;height:100px"></div>
    </div>
    <div class="tag">
      <div>出货单数量</div>
      <div>{{formatNum(88823)}}</div>
      <div>
        <span>周同比:</span>
        <span>
          213
          <i class="el-icon-top"></i>
        </span>
      </div>
      <div class="chart" style="width:100%;height:100px"></div>
    </div>
    <div class="tag">
      <div>入库单数量</div>
      <div>{{formatNum(22325)}}</div>
      <div>
        <span>周同比:</span>
        <span>
          213
          <i class="el-icon-top"></i>
        </span>
      </div>
      <div class="chart" style="width:100%;height:100px"></div>
    </div>
  </div>
</template>

<script>
import { formatNum } from "@/utils/index";
export default {
  data() {
    return {};
  },
  methods: {
    formatNum,
    supplyNum() {
      let myChart = this.$echarts.init(
        document.getElementsByClassName("chart")[0]
      );
      let option = {
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "0%",
          right: "0%",
          bottom: "20%",
          top: "0%"
        },
        xAxis: [
          {
            type: "category",
            data: ["2011", "2012", "2013", "2018", "2019", "2020"],
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            splitLine: { show: false },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            splitNumber: 4,
            splitLine: {
              lineStyle: {
                type: "dashed",
                color: "#DDD"
              },
              show: false
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            nameTextStyle: {
              color: "#999"
            },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "Ⅳ～Ⅴ类",
            type: "line",
            data: [40.11, 39.56, 36.96, 37.05, 35.71, 25.33],
            symbolSize: 0, //不显示拐点
            lineStyle: {
              normal: {
                width: 4,
                color: {
                  type: "linear",
                  colorStops: [
                    {
                      offset: 0,
                      color: "#A9F387" // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#48D8BF" // 100% 处的颜色
                    }
                  ],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: "rgba(72,216,191, 0.3)",
                shadowBlur: 10,
                shadowOffsetY: 20
              }
            },
            areaStyle: {
              normal: {
                color: "#D6F2E4"
              }
            },
            smooth: true
          }
        ]
      };
      myChart.setOption(option);
      window.addEventListener("resize", function() {
        myChart.resize();
      });
    },
    saleNum() {
      let myChart = this.$echarts.init(
        document.getElementsByClassName("chart")[1]
      );
      let option = {
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "0%",
          right: "0%",
          bottom: "20%",
          top: "0%"
        },
        xAxis: [
          {
            type: "category",
            data: ["2011", "2012", "2013", "2018", "2019", "2020"],
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            splitLine: { show: false },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            splitNumber: 4,
            splitLine: {
              lineStyle: {
                type: "dashed",
                color: "#DDD"
              },
              show: false
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            nameTextStyle: {
              color: "#999"
            },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "Ⅳ～Ⅴ类",
            type: "line",
            data: [40.11, 39.56, 36.96, 37.05, 35.71, 25.33],
            symbolSize: 0, //不显示拐点
            lineStyle: {
              normal: {
                width: 4,
                color: {
                  type: "linear",
                  colorStops: [
                    {
                      offset: 0,
                      color: "#FAAD14" // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#FAAD14" // 100% 处的颜色
                    }
                  ],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: "rgba(72,216,191, 0.3)",
                shadowBlur: 10,
                shadowOffsetY: 20
              }
            },
            areaStyle: {
              normal: {
                color: "#FEEFD0"
              }
            },
            smooth: true
          }
        ]
      };
      myChart.setOption(option);
      window.addEventListener("resize", function() {
        myChart.resize();
      });
    },
    outWareHouseNum() {
      let myChart = this.$echarts.init(
        document.getElementsByClassName("chart")[2]
      );
      let option = {
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "0%",
          right: "0%",
          bottom: "20%",
          top: "0%"
        },
        xAxis: [
          {
            type: "category",
            data: ["2011", "2012", "2013", "2018", "2019", "2020"],
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            splitLine: { show: false },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            splitNumber: 4,
            splitLine: {
              lineStyle: {
                type: "dashed",
                color: "#DDD"
              },
              show: false
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            nameTextStyle: {
              color: "#999"
            },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "Ⅳ～Ⅴ类",
            type: "line",
            data: [40.11, 39.56, 36.96, 37.05, 35.71, 25.33],
            symbolSize: 0, //不显示拐点
            lineStyle: {
              normal: {
                width: 4,
                color: {
                  type: "linear",
                  colorStops: [
                    {
                      offset: 0,
                      color: "#F4664A" // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#F4664A" // 100% 处的颜色
                    }
                  ],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: "rgba(72,216,191, 0.3)",
                shadowBlur: 10,
                shadowOffsetY: 20
              }
            },
            areaStyle: {
              normal: {
                color: "#FDE0DB"
              }
            },
            smooth: true
          }
        ]
      };
      myChart.setOption(option);
      window.addEventListener("resize", function() {
        myChart.resize();
      });
    },
    inWareHouseNum() {
      let myChart = this.$echarts.init(
        document.getElementsByClassName("chart")[3]
      );
      let option = {
        tooltip: {
          trigger: "axis"
        },
        grid: {
          left: "0%",
          right: "0%",
          bottom: "20%",
          top: "0%"
        },
        xAxis: [
          {
            type: "category",
            data: ["2011", "2012", "2013", "2018", "2019", "2020"],
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            splitLine: { show: false },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            splitNumber: 4,
            splitLine: {
              lineStyle: {
                type: "dashed",
                color: "#DDD"
              },
              show: false
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: "white"
              }
            },
            nameTextStyle: {
              color: "#999"
            },
            splitArea: {
              show: false
            },
            axisTick: {
              show: false
            }
          }
        ],
        series: [
          {
            name: "Ⅳ～Ⅴ类",
            type: "line",
            data: [40.11, 39.56, 36.96, 37.05, 35.71, 25.33],
            symbolSize: 0, //不显示拐点
            lineStyle: {
              normal: {
                width: 4,
                color: {
                  type: "linear",
                  colorStops: [
                    {
                      offset: 0,
                      color: "#3286FB" // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: "#3286FB" // 100% 处的颜色
                    }
                  ],
                  globalCoord: false // 缺省为 false
                },
                shadowColor: "rgba(72,216,191, 0.3)",
                shadowBlur: 10,
                shadowOffsetY: 20
              }
            },
            areaStyle: {
              normal: {
                color: "#D6E7FE"
              }
            },
            smooth: true
          }
        ]
      };
      myChart.setOption(option);
      window.addEventListener("resize", function() {
        myChart.resize();
      });
    }
  },
  mounted() {
    this.supplyNum();
    this.saleNum();
    this.outWareHouseNum();
    this.inWareHouseNum();
  }
};
</script>

<style lang="scss" scoped >
.tag {
  width: 24%;
  height: 172px;
  background: white;
  float: left;
  overflow: hidden;
  margin-bottom: 16px;
  & > div:first-child {
    width: 100%;
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
    margin: 16px 0 0 16px;
  }
  & > div:nth-child(2) {
    font-size: 28px;
    font-family: HelveticaNeue-Bold, HelveticaNeue;
    font-weight: bold;
    color: rgba(51, 51, 51, 1);
    margin: 8px 0 0 16px;
  }
  & > div:nth-child(3) {
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(153, 153, 153, 1);
    margin: 12px 0 0 16px;
    & > span:nth-child(2) {
      color: #30bf78;
    }
  }
}
.main > div:not(:first-child) {
  margin-left: 1.3%;
}
</style>