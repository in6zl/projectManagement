<template>
  <div ref="t3d">
    <div id="container">
      <div class="action">
        <!-- <el-button id="btn" type="primary">新建货架</el-button>
        <el-button id="btn" type="primary">进入内仓</el-button> -->
        <el-button id="btn" type="primary" @click="back" style="margin-top:55px">退出内仓</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import "../../../utils/3d.js";
export default {
  mounted() {
    new warehouse({
      rootElement: this.$refs.t3d
    });
  },
  methods:{
    back(){
      this.$router.go(-1)
    }
  }
};
</script>

<style scoped>
.action {
  position: absolute;
}
</style>