<template>
  <div>
    <el-row >
      <el-col :sm="23" :lg="8" :xs="23">
        <slot name="first"></slot>
      </el-col>
      <el-col :sm="23" :lg="8" :xs="23">
        <slot name="second"></slot>
      </el-col>
      <el-col :sm="23" :lg="8" :xs="23" >
        <slot name="three"></slot>
      </el-col>
      <el-col :sm="23" :lg="8" :xs="23" class="seachBtn">
        <slot name="four"></slot>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    lable: {
      type: String,
      defalut: ""
    }
  },
  data() {
    return {
      isShow: false
    };
  },
  mountd() {
    console.log(this.$slots)
  }
};
</script>

<style scoped>
.seachBtn{
  float:right;
}
.el-col{
  padding-bottom: 6px;
}
</style>