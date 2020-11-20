<template>
  <div class="paging">
    <div>
      <span v-if="totalPages > 0">显示第 {{ pageStart }} 至 {{ pageEnd }} 项结果，共 {{ totalPages }} 页</span>
    </div>
    <div>
      <el-pagination
        layout="prev, pager, next"
        :current-page.sync="current"
        @current-change="currentChange"
        :page-count="totalPages"
        background
        small
      ></el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      current: this.pageNo + 1,
    };
  },
  props: {
    pageNo: {
      type: Number,
      default: 0,
    },
    pageSize: {
      type: Number,
      default: 10,
    },
    totalElements: {
      type: Number,
      required: true,
    },
  },
  computed: {
    pageStart: function () {
      return (this.pageNo - 1) * this.pageSize + 1;
    },
    pageEnd: function () {
      let end = this.pageStart * this.pageSize;
      console.log(end);
      return end > this.totalElements ? this.totalElements : end;
    },
    totalPages: function () {
      return Math.ceil(this.totalElements / this.pageSize);
    },
  },
  methods: {
    currentChange(current) {
      this.current = current;
      this.$emit("currentChange", current);
    },
  },
};
</script>

<style lang='scss' scoped>
.paging {
  background: white;
  width: 100%;
  min-height: 51px;
  padding: 0 16px;
  & > div:nth-of-type(1) {
    float: left;
    margin-top: 20px;
    font-weight: 400;
    font-size: 12px;
    color: rgba(51, 51, 51, 1);
  }
  & > div:nth-of-type(2) {
    float: right;
    margin-top: 16px;
    position: relative;
    right: -8px;
  }
  & >>> .el-pagination.is-background .el-pager li {
    background-color: white;
  }
  & >>> .el-pagination.is-background .el-pager li:not(.disabled).active {
    background-color: #409eff;
  }
}
</style>