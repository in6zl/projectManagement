<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.dictTypeName')"
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
          <div style="line-height:34px" class="subtit">{{$t('main.dictType')}}</div>
          <el-button
            size="small"
            v-if="haveBtn('dictType:add')"
            @click="toAddOrg(),changBg(1)"
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
          <el-table-column type="index" width="60px" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="name"
            :label="$t('table.dictTypeName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            align="center"
            width="150px"
          >
            <template slot-scope="scope">
              <el-tooltip content="编辑" placement="top">
                <svg-icon
                  v-if="haveBtn('dictType:edit')"
                  icon-class="edit"
                  @click.native="updateDictType(scope.$index,tableData)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  icon-class="delete"
                  v-if="haveBtn('dictType:delete')"
                  @click.native="deleteRow(scope.$index, tableData)"
                ></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging :pageSize="pageSize" :pageNo="pageNo" :totalElements="totalElements" @currentChange="currentChange"></paging>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import { getDictType, deleteDictType, searchDictType } from "@/api/dict";
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
      name: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      pageTotal: 0,
      pageSize: 10,
      pageNo: 1,
      totalElements: 0,
      dictTypeName: "",
      currentPage: 1,
    };
  },
  computed: {
    pageStart() {
      return (this.pageNo - 1) * this.pageSize + 1;
    },
    pageEnd() {
      return this.pageNo * this.pageSize;
    },
  },
  mounted() {
    this.dictTypeList();
  },
  methods: {
    currentChange(val){
      this.pageNo = val
       this.dictTypeList()
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddOrg() {
      this.$router.push("/addDictType");
    },
    changBg(index) {
      this.ischange = index;
    },
    dictTypeList() {
      searchDictType({
        pageable: { pageNo: this.pageNo - 1, pageSize: this.pageSize },
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteDictType(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            if (rows.length === 0 && this.pageNo != 1) {
              this.pageNo = this.pageNo - 1;
            }
            this.pageIndexChange(this.pageNo);
          }
        });
      });
    },
    pageIndexChange(index) {
      this.pageNo = index;
      searchDictType({
        name: this.dictTypeName,
        pageable: { pageNo: this.pageNo - 1, pageSize: this.pageSize },
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    updateDictType(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editDictType", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      this.dictTypeName = this.name;
      searchDictType({
        name: this.name,
        pageable: { pageNo: 0, pageSize: this.pageSize },
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
