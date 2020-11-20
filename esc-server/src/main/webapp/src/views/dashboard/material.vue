<template>
  <div class="main">
    <div class="material">
      <el-tabs v-model="editableTabsValue" @tab-click="handleClick">
        <el-tab-pane
          v-for="(item, index) in editableTabs"
          :key="index"
          :label="item.title"
          :name="item.name"
        >
          <span slot="label">
            <div class="circlePercent">
              <div style="float:left">
                <div>{{item.title}}</div>
                <div>{{item.num}}</div>
              </div>
              <div style="float:right;margin-left:30px">
                <el-progress type="circle" :percentage="25" :width="70" :stroke-width="12"></el-progress>
              </div>
            </div>
          </span>
          <template v-if="item.name === editableTabsValue">
            <el-row>
              <el-col :span="15" :loading="isLoading">
                <div id="materialStatistics"></div>
              </el-col>
              <el-col :span="9">
                <div id="materialSistribution"></div>
              </el-col>
            </el-row>
          </template>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import { Line } from "@antv/g2plot";
import { Area } from "@antv/g2plot";
import { Donut } from "@antv/g2plot";
export default {
  data() {
    return {
      editableTabsValue: "1",
      editableTabs: [
        {
          title: "物料1",
          name: "1",
          num: "500",
        },
        {
          title: "物料2",
          name: "2",
          num: "600",
        },
        {
          title: "物料3",
          name: "3",
          num: "500",
        },
        {
          title: "物料4",
          name: "4",
          num: "500",
        },
        {
          title: "物料5",
          name: "5",
          num: "500",
        },
        {
          title: "物料6",
          name: "6",
          num: "500",
        },
        {
          title: "物料7",
          name: "7",
          num: "500",
        },
        {
          title: "物料8",
          name: "8",
          num: "500",
        },
      ],
      isLoading: false,
      tabIndex: 2,
      checkindex: "",
      data: [
        {
          type: "分类一",
          value: 27,
        },
        {
          type: "分类二",
          value: 25,
        },
        {
          type: "分类三",
          value: 18,
        },
        {
          type: "分类四",
          value: 15,
        },
        {
          type: "分类五",
          value: 10,
        },
        {
          type: "其它",
          value: 5,
        },
      ],
    };
    F;
  },
  mounted() {
    this.isLoading = true;
    this.getmaterialSistribution();
    this.getmaterialList();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    handleClick(tab, event) {
      console.log(this.checkindex, tab.index);
      if (this.checkindex == "") {
        this.checkindex = tab.index;
      } else if (this.checkindex != tab.index) {
        this.checkindex = tab.index;
      } else if (this.checkindex === tab.index) {
        return;
      }
      this.$nextTick(() => {
        this.getmaterialSistribution();
        this.getmaterialList();
      });
    },
    getmaterialList() {
      fetch("https://g2plot.antv.vision/zh/examples/data/fireworks-sales.json")
        .then((res) => res.json())
        .then((data) => {
          const areaPlot = new Area(
            document.getElementById("materialStatistics"),
            {
              title: {
                visible: true,
                text: "物料2消耗统计",
              },
              data,
              xField: "Date",
              yField: "scales",
              xAxis: {
                type: "dateTime",
                tickCount: 5,
              },
            }
          );
          areaPlot.render();
          this.isLoading = false;
        });
    },
    getmaterialSistribution() {
      const data = this.data;
      const donutPlot = new Donut(
        document.getElementById("materialSistribution"),
        {
          forceFit: true,
          title: {
            visible: true,
            text: "物料分布",
          },
          radius: 0.8,
          padding: "auto",
          data,
          angleField: "value",
          colorField: "type",
        }
      );

      donutPlot.render();
    },
  },
};
</script>
<style >
.material .el-tabs__nav-scroll {
  height: 100px !important;
  padding: 16px !important;
}
.el-tabs__nav-wrap::after {
  display: none;
}
.material .el-tabs__active-bar {
  top: -16px !important;
}
.material .el-tabs__nav-prev {
  top: 22px !important;
}
.material .el-tabs__nav-next {
  top: 22px !important;
}
</style>
<style lang="scss" scoped>
.main {
  background: white;
  border-radius: 5px;
  padding: 0 16px 16px 16px;
  margin-top: 16px;
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

.circlePercent {
  height: 100px;
}
</style>