<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('supMana.supplier.name')"
                size="small"
                style="width:95%"
                v-model="queryParams.name"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('supMana.supplier.address')"
                size="small"
                style="width:95%"
                v-model="queryParams.address"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('supMana.supplier.phone')"
                size="small"
                style="width:95%"
                v-model="queryParams.phone"
              ></el-input>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="mini"
            @click="search"
          >{{$t('button.search')}}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t('supMana.supplier.supplierList')}}</div>
          <el-button
            size="small"
            @click="toAddSupplier(),changBg(1)"
            type="primary"
            v-if="haveBtn('supplier:add')"
          >{{$t('button.add')}}</el-button>
        </div>

        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="code"
            :label="$t('supMana.supplier.code')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="name"
            :label="$t('supMana.supplier.name')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="address"
            :label="$t('supMana.supplier.address')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="phone"
            :label="$t('supMana.supplier.phone')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="createTime"
            :label="$t('table.createTime')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <svg-icon icon-class="look" @click.native="lookRow(scope.$index, tableData)"></svg-icon>
              <svg-icon
                icon-class="edit"
                @click.native="editSupplier(scope.$index,tableData)"
                v-if="haveBtn('supplier:edit')"
              ></svg-icon>
              <svg-icon
                icon-class="delete"
                @click.native="deleteRow(scope.$index, tableData)"
                v-if="haveBtn('supplier:delete')"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{queryParams.totalElements}} 项</div>
      <div>
        <el-pagination
          :current-page="queryParams.pageNum + 1"
          :page-size="queryParams.pageSize"
          :total="queryParams.totalElements"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          background
          small
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import { deleteSupplier, searchSupplier } from "@/api/supplier";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      queryParams: {
        pageNum: 0,
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
      supplier: {
        name: "",
        address: "",
        phone: "",
      },
    };
  },
  mounted() {
    this.searchSupplier();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },

    handleCurrentChange(val) {
      this.num = val;
      this.queryParams.pageNum = val - 1;
      searchSupplier({
        pageNum: val - 1,
        pageSize: 10,
        totalElements: 0,
        name: this.supplier.name,
        address: this.supplier.address,
        phone: this.supplier.phone,
      }).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.queryParams.totalElements = res.data.totalElements;
        }
      });
    },

    handleSizeChange() {},

    toAddSupplier() {
      this.$router.push("/addSupplier");
    },

    changBg(index) {
      this.ischange = index;
    },

    searchSupplier() {
      this.supplier.name = this.queryParams.name;
      this.supplier.address = this.queryParams.address;
      this.supplier.phone = this.queryParams.phone;
      searchSupplier(this.queryParams).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.queryParams.totalElements = res.data.totalElements;
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteSupplier(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.searchSupplier();
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            let number =
              rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
            this.handleCurrentChange(number);
          } else {
            this.$message({
              message: "已被引用不能删除",
              type: "error",
            });
          }
        });
      });
    },
    editSupplier(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editSupplier", query: { id: id } });
    },
    search() {
      let data = this.queryParams;
      data.pageNum = 0;
      data.pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.searchSupplier();
      loading.close();
    },
    lookRow(index, tableData) {
      let id = tableData[index].id;
      this.$router.push({ path: "/lookSupplier", query: { id: id } });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
