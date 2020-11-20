<template>
  <div class="main">
    <div class="main-top">在途车辆</div>
    <div
      class="carNum"
      style="display: flex; justify-content: space-between;"
      v-for="i in 10"
      :key="i"
    >
      <div>
        <span class="serialnum" v-if="i>3">{{i}}</span>
        <span class="top3" v-if="i==1">
          <img src="@/assets/images/no1.png"/>
        </span>
        <span class="top3" v-if="i==2">
          <img src="@/assets/images/no2.png"/>
        </span>
        <span class="top3" v-if="i==3">
          <img src="@/assets/images/no3.png"/>
        </span>
        <span>苏B1234E</span>
      </div>
      <div style="color:#999999">
        <span>
          <svg-icon icon-class="address" />
        </span>
        <span>10km</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {},
  methods: {},
  mounted() {},
};
</script>

<style lang="scss" scoped>
.main {
  height: 434px;
  padding: 16px;
  background: white;
  border: 2px;
}
.main-top {
  margin-bottom: 15px;
  height: 22px;
  font-size: 14px;
  font-weight: 700;
  font-family: PingFangSC-Medium, PingFang SC;
  color: rgba(0, 0, 0, 0.85);
  line-height: 22px;
}
.carNum {
  height: 22px;
  margin-top: 14px;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
}
.serialnum {
  width: 20px;
  height: 20px;
  line-height: 20px;
  margin-right: 16px;
  background: rgba(240, 242, 245, 1);
  display: inline-block;
  text-align: center;
  font-size: 12px;
  border-radius: 50%;
}
.top3 {
  width: 20px;
  height: 20px;
  line-height: 20px;
  margin-right: 16px;
}
</style>
