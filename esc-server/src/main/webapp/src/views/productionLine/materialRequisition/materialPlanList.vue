<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <!-- 用户搜索栏 -->
      <div class="main-top" style="padding-left:10px">
        <el-form ref="materialRes">
          <search-form>
            <el-form-item slot="first">
              <el-select
                v-model="warehouseValue"
                placeholder="请选择内仓"
                size="small"
                clearable
                filterable
                style="width:95%"
              >
                <el-option
                  v-for="item in fahouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="second" prop="materialState">
              <el-select
                v-model="materialState"
                placeholder="请选择状态"
                size="small"
                clearable
                filterable
                style="width:95%"
              >
                <el-option
                  v-for="item in stateList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item slot="three">
              <el-select
                v-model="materialValue"
                placeholder="请选择物料"
                size="small"
                clearable
                filterable
                style="width:95%"
              >
                <el-option
                  v-for="item in materialList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item slot="first" size="small">
              <el-date-picker
                v-model="searchTime"
                type="date"
                placeholder="选择日期"
                size="small"
                style="width:95%"
              ></el-date-picker>
            </el-form-item>
            <el-form-item slot="three" size="small">
              <el-button
                type="primary"
                size="small"
                @click="serchCommit('materialRes')"
                style="float:right;margin:0 16px 0 0;"
              >搜索</el-button>
            </el-form-item>
          </search-form>

          <div style="clear:both"></div>
        </el-form>
      </div>
      <!-- 数据展示栏 -->
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px">物料需求计划列表</div>
          <el-button type="primary" size="small" @click="outFahouse">出库</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-table
                style="width:100%;overflow:auto"
                :header-cell-style="tableHeaderColor"
                :data="props.row.dtos"
                label-position="left"
                inline
                class="demo-table-expand"
                @selection-change="selectionChange"
              >
                <el-table-column type="selection" width="120" align="center" v-if="showSelect==0"></el-table-column>
                <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
                <el-table-column prop="material.name" label="物料 " :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="num" label="需求数量" :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="outNum" label="已出库数量" :show-overflow-tooltip="true"></el-table-column>
                <el-table-column prop="requireTime" label="需求时间" :show-overflow-tooltip="true"></el-table-column>
                <el-table-column
                  prop="state"
                  :formatter="stateFormat"
                  label="状态"
                  :show-overflow-tooltip="true"
                ></el-table-column>
                <el-table-column
                  prop="operation"
                  :label="$t('table.operation')"
                  width="150"
                  align="center"
                >
                  <template slot-scope="scope">
                    <el-tooltip content="完成物料需求计划" placement="top">
                      <svg-icon
                        icon-class="complete"
                        @click="completeMaterialPlan(scope.$index,props.row.dtos)"
                        v-if="scope.row.state==0"
                      ></svg-icon>
                    </el-tooltip>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="workShopDTO.name" label="车间名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="workShopDTO.address" label="车间地址" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="workShopDTO.manager" label="联系人" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="workShopDTO.phone" label="联系方式" :show-overflow-tooltip="true"></el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import { materialPlanList } from "@/api/productLine";
import { parseTime } from "@/utils/index";
import { getFaWarehouseList } from "@/api/faWareHouse.js";
import { findMaterialListData, completeMaterialPlan } from "@/api/material.js";
import lef from "@/components/common/l_icon";
import { deleteWorkshop } from "@/api/workshop";
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      stateList: [
        {
          name: "未完成",
          id: 0,
        },
        {
          name: "已完成",
          id: 1,
        },
      ],
      materialState: "",
      showSelect: false,
      selectList: [],
      fahouseList: [],
      materialList: [],
      warehouseValue: "",
      materialValue: "",
      warehouse: "",
      material: "",
      searchTime: "",
      tableHeight: window.innerHeight - 240,
      index: 1,
      ischange: 1,
      tableData: [],
      innerTable: [],
      pageTotal: 0,
      pageSize: 10,
      pageIndex: 1,
      totalElements: 0,
      searchParam: {
        warehouseId: undefined,
        materialId: undefined,
        requireDate: undefined,
        state: undefined,
        pageParam: {
          pageNo: undefined,
          pageSize: undefined,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
    };
  },
  computed: {},
  mounted() {
    this.initFindAllProductionLineData();
    getFaWarehouseList({}).then((res) => {
      this.fahouseList = res.data;
    });
    findMaterialListData().then((res) => {
      this.materialList = res.data;
    });
  },
  methods: {
    stateFormat(row, column, cellValue) {
      if (cellValue == 0) {
        return "未完成";
      } else {
        return "已完成";
      }
    },
    selectionChange(val) {
      this.selectList = val;
    },
    outFahouse() {
      // localStorage.setItem("outOrderData", JSON.stringify(this.selectList));
      let warehouse = {};
      let isSameWareHouse = true;
      if (!this.selectList || this.selectList.length == 0) {
        this.$message({
          message: "请先选择需要出库的物料",
          type: "success",
        });
        return;
      }
      console.log('this.selectList',this.selectList);
      this.selectList.forEach((materialRequire) => {
        if (!warehouse.id) {
          warehouse = materialRequire.warehouse;
        } else {
          if (warehouse.id != materialRequire.warehouse.id) {
            isSameWareHouse = false;
          }
        }
      });
      if (!isSameWareHouse) {
        this.$message({
          message: "必须选择相同内仓的物料",
          type: "success",
        });
        return;
      }
      this.$router.push({
        name: "addOutboundOrderMaterial",
        params: { selectList: this.selectList, url: "materialPlanList"},
      });
    },
    serchCommit(formName) {
      // const loading = this.$loading({
      //   lock: true,
      //   text: "Loading",
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)",
      // });
      this.searchParam = {
        warehouseId: this.warehouseValue,
        materialId: this.materialValue,
        requireDate: this.searchTime,
        state: this.materialState,
        pageParam: {
          pageNo: 0,
          pageSize: this.pageSize,
        },
      };
      this.initFindAllProductionLineData();
    },
    //初始化DataGrid数据
    initFindAllProductionLineData() {
      materialPlanList(this.searchParam).then((response) => {
        this.tableData = response.data;
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddProductionLine() {
      this.$router.push("/addProductionLine");
    },
    changBg(index) {
      this.ischange = index;
    },
    completeMaterialPlan(index, rows) {
      this.$confirm("是否手动完成物料需求计划", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        completeMaterialPlan({
          id: rows[index].id,
          state: 1,
        }).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "已完成物料需求计划",
              type: "success",
            });
            this.initFindAllProductionLineData();
          } else {
            this.$message({
              message: "失败",
              type: "error",
            });
          }
        });
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
<style>
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.main .el-form-item:nth-of-type(3) {
  float: none !important;
}

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
