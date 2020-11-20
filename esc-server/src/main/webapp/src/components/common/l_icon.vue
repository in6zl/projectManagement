<template>
  <div :style="{'margin-top': top + 'px'}"></div>
</template>

<script>
export default {
  data() {
    return {
    }
  },
  props: {
    top: {
      type: Number,
      default: 15
    }
  },
};
</script>

<style lang='scss' scoped>
div {
  height: 20px;
  width: 2.5px;
  background-color: #3a8ee6;
  float: left;
  border-radius: 0px 3px 3px 0px;
}
</style>
