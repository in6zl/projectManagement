<template>
   <div class="btn">
      <slot></slot>
   </div>
</template>

<script>
export default {

}
</script>

<style lang='scss' scoped>
.btn {
  width: 106%;
  display: flex;
  padding: 10px 0;
  margin-left: -16px;
  position: sticky!important;
  bottom: 0px;
  justify-content: center;
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  background: white;
  z-index: 1000;
}
</style>