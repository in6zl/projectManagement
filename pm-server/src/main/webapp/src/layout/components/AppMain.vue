<template>
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <router-view :key="key" v-if="hardReset" />
    </transition>
  </section>
</template>

<script>
import screenfull from "screenfull";
export default {
  name: "AppMain",
  data() {
    return {
      hardReset: true,
    };
  },
  computed: {
    key() {
      return this.$route.path;
    },
  },
  mounted() {
    let that = this;
    screenfull.onchange(() => {
      that.hardReset = false;
      setTimeout(() => {
        that.hardReset = true;
      }, 10);
    });
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
.app-main {
  /*50 = navbar  */
  height: calc(100vh - 93px);
  width: 100%;
  position: relative;
  overflow-y: auto;
  padding: 12px 16px 0px 16px;
}
/* .el-scrollbar{
  position: unset;
}

.el-scrollbar > .is-horizontal {
  display: none !important;
} */
.fixed-header + .app-main {
  padding-top: 50px;
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}
</style>
