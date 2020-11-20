<template>
  <div class="main">
    <div class="tag">
      <div class="tag-top">
        <div>送货单数量</div>
        <div>{{formatNum(234)}}</div>
        <el-progress
          :percentage="50"
          :stroke-width="8"
          color="white"
          :format="format"
          style="margin-top:25px;"
        ></el-progress>
      </div>
      <div class="tag_bot" style="line-height: 41px">
        <span>日环比:</span>
        <svg-icon icon-class="up" />
        <span>60%</span>
      </div>
      <!-- <div class="chart" style="width:100%;height:100px"></div> -->
    </div>
    <div class="tag tag1">
      <div class="tag-top">
        <div>入库单数量</div>
        <div>{{formatNum(771)}}</div>
        <el-progress
          :percentage="70"
          :stroke-width="8"
          color="white"
          :format="format"
          style="margin-top:25px;"
        ></el-progress>
      </div>
      <div class="tag_bot" style="display: flex; justify-content: space-between;">
        <p>
          <span>周同比:</span>
          <svg-icon icon-class="up" />
          <span>12%</span>
        </p>
        <p>
          <span>日环比:</span>
          <svg-icon icon-class="down" />
          <span>11%</span>
        </p>
      </div>
      <!-- <div class="chart" style="width:100%;height:100px"></div> -->
    </div>
    <div class="tag tag2">
      <div class="tag-top">
        <div>出库单数量</div>
        <div>{{formatNum(88823)}}</div>
        <el-progress
          :percentage="90"
          :stroke-width="8"
          color="white"
          :format="format"
          style="margin-top:25px;"
        ></el-progress>
      </div>
      <div class="tag_bot" style="display: flex; justify-content: space-between;">
        <p>
          <span>周同比:</span>
          <svg-icon icon-class="up" />
          <span>12%</span>
        </p>
        <p>
          <span>日环比:</span>
          <svg-icon icon-class="down" />
          <span>11%</span>
        </p>
      </div>
      <!-- <div class="chart" style="width:100%;height:100px"></div> -->
    </div>
    <div class="tag tag3">
      <div class="tag-top">
        <div>总销单数量</div>
        <div>{{formatNum(126560)}}</div>
        <div class="tag_bot" style="display: flex;margin-top:16px; justify-content: space-between;">
          <p>
            <span>周同比:</span>
            <svg-icon icon-class="up" />
            <span>60%</span>
          </p>
          <p>
            <span>日环比:</span>
            <svg-icon icon-class="down" />
            <span>60%</span>
          </p>
        </div>
      </div>
      <div class="tag_bot" style="display: flex; justify-content: space-between;">
        <p>
          <span>年度销售量:</span>
          <span>12423笔</span>
        </p>
      </div>
      <!-- <div class="chart" style="width:100%;height:100px"></div> -->
    </div>
  </div>
</template>

<script>
import { formatNum } from "@/utils/index";
import { Bar } from "@antv/g2plot";
export default {
  data() {
    return {
      data: [{ 地区: "华东", 销售额: 4684506.442 }],
    };
  },
  methods: {
    formatNum,
    format(percentage) {
      return percentage === "";
    },
  },
  mounted() {
    // this.getbasic();
  },
};
</script>
<style lang="scss" scoped >
.tag_bot {
  font-size: 12px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(255, 255, 255, 1);
  opacity: 0.5;
  margin: 0 16px 0 16px;
}
.tag-top {
  height: 130px;
  margin: 0 16px 0 16px;
  border-bottom: solid 1px rgba(255, 255, 255, 0.5);
}
.main .tag1 {
  background: linear-gradient(
    135deg,
    rgba(43, 194, 93, 1) 0%,
    rgba(0, 183, 103, 1) 46%,
    rgba(0, 169, 138, 1) 100%
  );
}
.main .tag2 {
  background: linear-gradient(
    135deg,
    rgba(250, 173, 20, 1) 0%,
    rgba(245, 150, 1, 1) 50%,
    rgba(240, 139, 0, 1) 100%
  );
}
.main .tag3 {
  background: linear-gradient(
    135deg,
    rgba(106, 148, 194, 1) 0%,
    rgba(103, 142, 180, 1) 51%,
    rgba(72, 111, 150, 1) 100%
  );
}
.tag {
  width: 24%;
  height: 184px;
  background: linear-gradient(
    135deg,
    rgba(25, 172, 246, 1) 0%,
    rgba(27, 140, 244, 1) 52%,
    rgba(4, 126, 242, 1) 100%
  );
  border-radius: 4px;
  color: white;
  float: left;
  overflow: hidden;
  margin-bottom: 16px;
  & > div div:first-child {
    width: 100%;
    font-size: 12px;
    color: rgba(255, 255, 255, 1);
    opacity: 0.5;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    margin: 16px 0 0 0;
  }
  & > div div:nth-child(2) {
    font-size: 28px;
    font-family: HelveticaNeue-Bold, HelveticaNeue;
    font-weight: bold;

    margin: 12px 0 0 0;
  }
  & > div div:nth-child(3) {
    font-size: 12px;
    font-family: PingFangSC-Regular, PingFang SC;

    font-weight: 400;
    margin: 12px 0 0 0;
    & > span:nth-child(2) {
      color: #30bf78;
    }
  }
}
.main > div:not(:first-child) {
  margin-left: 1.3%;
}
</style>