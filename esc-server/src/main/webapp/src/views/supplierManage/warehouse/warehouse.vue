<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="queryParams.name"
                :placeholder="$t('main.placeInsert')+$t('supMana.warehouse.name')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                v-model="queryParams.address"
                :placeholder="$t('main.placeInsert')+$t('supMana.warehouse.address')"
                size="small"
                style="width:95%"
                maxlength="60"
              />
            </el-form-item>
            <el-form-item slot="three">
              <el-input
                v-model="queryParams.phone"
                :placeholder="$t('main.placeInsert')+$t('supMana.warehouse.phone')"
                size="small"
                style="width:95%"
                maxlength="11"
              />
            </el-form-item>
          </search-form>
          <el-button
            style="float:right;margin:0px 16px 16px 0;"
            type="primary"
            size="mini"
            @click="search"
          >{{ $t('button.search') }}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{ $t('supMana.warehouse.warehouseList') }}</div>
          <el-button
            size="small"
            type="primary"
            style="margin-bottom:10px"
            @click="toAddWarehouse(),changBg(1)"
          >{{ $t('button.add') }}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            prop="name"
            :label="$t('supMana.warehouse.name')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="address"
            :label="$t('supMana.warehouse.address')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="phone"
            :label="$t('supMana.warehouse.phone')"
            :show-overflow-tooltip="true"
          />
          <el-table-column label="供应商" :show-overflow-tooltip="true">
            <template slot-scope="scope">{{ scope.row.suppliers.map(s => s.name).join(',') }}</template>
          </el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip content="修改" placement="top">
                <svg-icon icon-class="edit" @click.native="editWarehouse(scope.$index,tableData)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="queryParams.pageSize"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          small
        ></el-pagination>
      </div>
    </div>-->
    <paging
      :pageSize="queryParams.pageSize"
      :pageNo="queryParams.pageNum"
      :totalElements="queryParams.totalElements"
      @currentChange="handleCurrentChange"
    ></paging>
  </div>
</template>

<script>
import { deleteWarehouse, searchWarehouse } from "@/api/externalWarehouse";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
export default {
  components: {
    searchForm,
    lef,
    paging
  },
  data() {
    return {
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        totalElements: 0,
        name: "",
        address: "",
        phone: "",
      },
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      num: 1,
      currentPage: 1,
      totalPages: "0",
      warehouse: {
        name: "",
        address: "",
        phone: "",
      },
    };
  },
  mounted() {
    this.searchWarehouse();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },

    handleCurrentChange(val) {
      this.num = val;
      searchWarehouse({
        pageNum: val - 1,
        pageSize: 10,
        name: this.warehouse.name,
        address: this.warehouse.address,
        phone: this.warehouse.phone,
      }).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalPages = res.data.totalPages;
          this.pageSize = res.data.pageSize;
        }
      });
    },

    handleSizeChange() {},

    toAddWarehouse() {
      this.$router.push("/addWarehouse");
    },

    changBg(index) {
      this.ischange = index;
    },

    searchWarehouse() {
      this.currentPage = 1;
      this.warehouse.name = this.queryParams.name;
      this.warehouse.address = this.queryParams.address;
      this.warehouse.phone = this.queryParams.phone;
      searchWarehouse({
        pageNum: 0,
        pageSize: 10,
        name: this.warehouse.name,
        address: this.warehouse.address,
        phone: this.warehouse.phone,
      }).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalPages = res.data.totalPages;
          this.queryParams.totalElements = res.data.totalElements
          this.size = res.data.totalPages * 10;
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteWarehouse(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            let number =
              rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
            this.handleCurrentChange(number);
          }
        });
      });
    },
    editWarehouse(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editWarehouse", query: { id: id } });
    },
    search() {
      const data = this.queryParams;
      data.pageNum = 0;
      data.pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.searchWarehouse();
      loading.close();
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
