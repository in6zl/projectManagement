<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('product.placeholder')+$t('unit.name')"
                size="small"
                style="width:95%"
                v-model="name"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t('unit.unitList')}}</div>
          <el-button
            size="small"
            @click="toAddUnit(),changBg(1)"
            type="primary"
            style="margin-bottom:10px"
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
            prop="name"
            align="center"
            :label="$t('unit.name')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="description"
            align="center"
            :label="$t('unit.description')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip content="修改" placement="top">
                <svg-icon
                  icon-class="edit"
                  @click="updataUnit(scope.$index,tableData)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top" hide-after="100">
                <svg-icon icon-class="delete" @click="deleteRow(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging
      :pageSize="unit.pageParam.pageSize"
      :pageNo="unit.pageParam.pageNo"
      :totalElements="totalElements"
      @currentChange="handleCurrentChange"
    ></paging>
  </div>
</template>
<script>
import { page, deleteUnit, getUnitInfo } from "@/api/unit";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import paging from "@/components/common/page";
export default {
  components: {
    searchForm,
    lef,
    paging,
  },
  data() {
    return {
      tableHeight: window.innerHeight - 350,
      totalElements: "",
      index: 1,
      ischange: 1,
      tableData: [],
      code: "",
      name: "",
      totalPages: "",
      size: "",
      num: 1,
      unit: {
        id: "",
        pageParam: {
          pageNo: 1,
          pageSize: 10,
        },
        code: "",
        name: "",
      },
      currentPage: 1,
      isDelete: "",
      deleteType: ["否", "是"],
    };
  },
  mounted() {
    this.unitList();
  },
  methods: {
    handleCurrentChange(val) {
      this.unit.pageParam.pageNo = val;
      page(this.unit).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          console.log(this.tableData);
          this.totalElements = res.data.totalElements;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddUnit() {
      this.$router.push("/addUnit");
    },
    changBg(index) {
      this.ischange = index;
    },
    unitList() {
      page(this.unit).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalPages = res.data.totalPages;
          this.totalElements = res.data.totalElements;
          this.size = res.data.totalPages * 10;
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteUnit(rows[index].id).then((res) => {
          if (res.code === "0") {
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
              message: res.message,
              type: "error",
            });
          }
        });
      });
    },
    updataUnit(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editUnit", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      this.unit.code = this.code;
      this.unit.name = this.name;
      this.unit.isDelete = this.isDelete;
      this.unit.pageParam.pageNo = 0;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      page(this.unit).then((res) => {
        if (res.code === "0") {
          loading.close();
          let content = res.data.content;
          content.forEach((element) => {
            if (element.isDelete === 0) {
              element["show"] = true;
            } else {
              element["show"] = false;
            }
          });
          this.tableData = content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalPages * 10;
        } else {
          loading.close();
        }
      });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
// .el-button:focus {
//   background-color: #409eff !important;
//   color: #ffffff;
// }
</style>