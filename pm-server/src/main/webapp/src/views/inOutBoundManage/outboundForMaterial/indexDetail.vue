<template>
  <div>
    <div class="main">
      <div style="width: 100%;display: flex;justify-content: space-between;padding-right:30px;height:30px">
        {{ $t('route.outboundOrderMaterialDetail') }}
        <el-button @click="fanhui()" type="primary" size="small">返回</el-button>
      </div>
      <div class="table">
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column
            prop="materialName"
            :label="$t('inoutBound.materialName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unit"
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="binName"
            :label="$t('inoutBound.binName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unitNum"
            :label="$t('inoutBound.unitNum')"
            fixed="right"
            align="right"
          />
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import { getOutboundOrderMaterial } from "@/api/outboundOrderMaterial";

export default {
  data: function () {
    return {
      // 查询参数
      searchForm: {
        materialId: "",
        binId: "",
        pageNo: 0,
        pageSize: 3,
      },
      materialList: [],
      binList: [],
      materialRequirePlanName: this.$route.query.materialRequirePlanName,
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 240,
      tableData: [],
      totalElements: 0,
      totalPages: 0,
      num: 1,
      currentPage: 1,
    };
  },
  mounted() {
    this.page();
    getFaStorehouseList({}).then((res) => {
      if (res.code === "0") {
        this.binList = res.data;
      }
    });
    findMaterialListData({}).then((res) => {
      if (res.code === "0") {
        this.materialList = res.data;
      }
    });
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      getOutboundOrderMaterial(this.$route.query.id).then((res) => {
        this.tableData = res.data.detailDtoList;
      });
    },
    handleCurrentChange(cpage) {
      this.num = val;
      this.searchForm.pageNo = cpage - 1;
      const data = {
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
        },
      };
      this.page(data);
    },
    fanhui(){
      this.$router.push("/outboundOrderMaterial");
    }
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/car.scss";
</style>
