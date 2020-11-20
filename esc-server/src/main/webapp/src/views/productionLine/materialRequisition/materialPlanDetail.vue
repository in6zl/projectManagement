<template>
  <div>
    <div class="main">
      <!-- 数据展示栏 -->
      <lef :top="23"></lef>
      <div class="roleManageTable">
        <div class="userEdit">
          <div class="subtit" style="line-height:34px">物料需求计划列表</div>
          <el-button size="small" type="primary" @click="fanhui">返回</el-button>
        </div>
        <el-table
          :data="tableData"
          style="width: 100%;height:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column
            prop="warehouse.name"
            label="内仓"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="material.name"
            label="物料 "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="requireTime"
            label="需求时间"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="num" label="数量" :show-overflow-tooltip="true"></el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
import { getMaterialRequire } from "@/api/productLine";
import {getByProductPlan} from "@/api/material"
import lef from "@/components/common/l_icon";

export default {
  components: {
    lef,
  },
  data() {
    return {
      tableData: [],
      tableHeight: window.innerHeight - 250,
    };
  },
  mounted() {
    let type = this.$route.query.type,
      id = this.$route.query.id;
    if (type == 0) {
      getMaterialRequire(id).then((res) => {
        this.tableData = res.data;
      });
    } else {
      getByProductPlan(id).then((res) => {
        this.tableData = res.data;
      });
    }
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    fanhui() {
      let type = this.$route.query.type;
      if (type == 0) {
        this.$router.push("/materialRequisitionMgr");
      } else {
        this.$router.push("/productionPlanMgr");
      }
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>