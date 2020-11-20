<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.dictKey')"
                size="small"
                style="width:95%"
                v-model="dictKey"
              ></el-input>
            </el-form-item>

            <el-form-item slot="second">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.dictValue')"
                size="small"
                style="width:95%"
                v-model="dictValue"
              ></el-input>
            </el-form-item>

            <el-form-item slot="three">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.dictTypeName')"
                size="small"
                style="width:95%"
                v-model="typeId"
              ></el-input>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:16px 16px 16px 0;"
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
          <div style="line-height:34px" class="subtit">{{$t('main.dictType')}}</div>
          <el-button
            size="small"
            v-if="haveBtn('dict:add')"
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
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="dictKey"
            :label="$t('table.dictName')"
            :show-overflow-tooltip="true"
          ></el-table-column>

          <el-table-column
            prop="dictValue"
            :label="$t('table.dictValue')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="dictType.name"
            :label="$t('table.dictTypeName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150px"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip content="修改" placement="top">
                <svg-icon
                  icon-class="edit"
                  v-if="haveBtn('dict:edit')"
                  @click.native="updateDict(scope.$index,tableData)"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  icon-class="delete"
                  v-if="haveBtn('dict:delete')"
                  @click.native="deleteRow(scope.$index, tableData)"
                ></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging
      :pageSize="pageSize"
      :pageNo="pageNo"
      :totalElements="totalElements"
      @currentChange="pageIndexChange"
    ></paging>
  </div>
</template>

<script>
import { getDict, deleteDict, searchDict } from "@/api/dict";
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
      dictKey: "",
      dictValue: "",
      typeId: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      pageTotal: 0,
      pageSize: 10,
      currentPage: 1,
      pageNo: 1,
      totalElements: 0,
      dict: {
        dictKey: "",
        dictValue: "",
        typeId: "",
      },
      num: 1,
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
    this.dictList();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddOrg() {
      this.$router.push("/addDict");
    },
    changBg(index) {
      this.ischange = index;
    },
    dictList() {
      searchDict({
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
        deleteDict(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            let number = rows.length === 0 ? this.num - 1 : this.num;
            this.pageIndexChange(number);
          }
        });
      });
    },
    pageIndexChange(val) {
      this.num = val;
      let curr = val - 1;
      searchDict({
        dictKey: this.dictKey,
        dictValue: this.dictValue,
        dictType: {
          name: this.typeId,
        },
        pageable: { pageNo: curr, pageSize: this.pageSize },
      }).then((response) => {
        this.tableData = response.data.content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    updateDict(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editDict", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      let dictType = {};
      dictType.name = this.typeId;
      searchDict({
        dictKey: this.dictKey,
        dictValue: this.dictValue,
        dictType: dictType,
        pageable: { pageNo: this.pageNo - 1, pageSize: this.pageSize },
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
