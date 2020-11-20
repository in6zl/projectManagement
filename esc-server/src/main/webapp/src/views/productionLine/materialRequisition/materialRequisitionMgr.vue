<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <!-- 用户搜索栏 -->
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+'领料单号'"
                size="small"
                style="width:95%"
                v-model="searchForm.code"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-select
                v-model="searchForm.isCompile"
                :placeholder="$t('main.placeInsert')"
                style="width:95%"
                size="small"
              >
                <el-option
                  v-for="item in options"
                  :key="item.name"
                  :label="item.name"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>

            <el-form-item slot="three">
              <el-date-picker
                v-model="searchForm.searchTime"
                type="date"
                placeholder="选择日期"
                size="small"
                style="width:95%"
              ></el-date-picker>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            v-if="haveBtn('materialRequisition:list')"
            type="primary"
            size="mini"
            @click="serchCommit"
          >{{$t('button.search')}}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <!-- 数据展示栏 -->
      <div class="roleManageTable">
        <!-- 用户编辑栏 -->
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">领料单</div>
          <div>
            <el-button
              size="small"
              v-if="haveBtn('materialRequisition:add')"
              @click="toAddMaterialRequisition()"
              type="primary"
            >新增</el-button>
            <el-button
              size="small"
              v-if="haveBtn('materialRequisition:add')"
              @click="downloadExcel()"
              type="primary"
            >{{$t('button.download')}}</el-button>
            <el-button size="small" v-if="haveBtn('materialRequisition:add')" type="primary">
              <el-upload
                action="api/materialRequisition/fileUpload"
                :show-file-list="false"
                :on-success="loadMaterialRequisition"
              >
                <el-link size="small" :underline="false" class="uploadFont">点击上传</el-link>
              </el-upload>
            </el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="code" label="领料单号" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scoped">
              <el-tooltip effect="dark" content="查看物料需求计划" placement="top">
                <a
                  @click="viewMaterialPlanList(scoped.$index,tableData)"
                  style="color:#00f;"
                >{{scoped.row.code}}</a>
              </el-tooltip>
            </template>
          </el-table-column>
          <el-table-column
            prop="compile"
            :formatter="isCompile"
            label="是否完成 "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="workShop.name" label="所属车间" width="150" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="description" label="备注 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="createTime" label="创建时间" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <svg-icon
                v-if="haveBtn('materialRequisition:edit')"
                icon-class="edit"
                @click="editItem(scope.row)"
              ></svg-icon>
              <svg-icon
                v-if="haveBtn('materialRequisition:delete')"
                icon-class="delete"
                @click="deleteItem(scope.$index,tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{pageStart}} 至 {{pageEnd}} 项结果，共{{ searchParam.pageable.totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          @current-change="pageIndexChange"
          :page-count="searchParam.pageable.totalPages"
          :current-page.sync="searchParam.pageable.pageIndex"
          background
          small
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import {
  findMaterialRequisitionPageData,
  deleteRequisitionItem,
} from "@/api/material";
import { parseTime } from "@/utils/index";
import { findMaterialListData,downloadTemplate } from "@/api/material";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      materialList: [],
      searchForm: {
        code: "",
        materialNo: -1,
        isCompile: -1,
        searchTime: "",
      },
      searchParam: {
        code: undefined,
        isCompile: undefined,
        createTime: undefined,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
      options: [
        { name: "已完成", value: 1 },
        { name: "未完成", value: 0 },
        { name: "所有", value: -1 },
      ],
      searchName: "",
      searchTime: "",
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      pageTotal: 0,
      pageSize: 10,
      pageIndex: 1,
      totalElements: 0,
      dialogTableVisible: false,
    };
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageable.pageNo *
          this.searchParam.pageable.pageSize +
        1
      );
    },
    pageEnd() {
      return (
        (this.searchParam.pageable.pageNo + 1) *
        this.searchParam.pageable.pageSize
      );
    },
  },
  mounted() {
    this.getAllMaterialList();
    this.page();
  },
  methods: {
    loadMaterialRequisition(response, file, fileList) {
      if (response.data === true) {
        this.$message({
          message: "导入成功",
          type: "success",
        });
        this.page();
      }
    },
    isCompile(row) {
      return row.compile ? "已完成" : "未完成";
    },
    //获取所有物料，查询使用
    getAllMaterialList() {
      findMaterialListData().then((res) => {
        this.materialList = res.data;
        this.materialList.unshift({
          id: -1,
          name: "所有",
        });
      });
    },
    downloadExcel() {
      downloadTemplate().then((res) => {
        let url = window.URL.createObjectURL(res);
        let link = document.createElement("a");
        link.style.display = "none";
        link.href = url;
        link.setAttribute("download", "MaterialRequisition.xlsx");
        document.body.appendChild(link);
        link.click();
      });
    },
    //查询点击
    serchCommit() {
      this.searchParam = {
        code: this.searchForm.code,
        isCompile:
          this.searchForm.isCompile == -1 ? "" : this.searchForm.isCompile,
        createTime: this.searchForm.searchTime,
        pageable: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      this.page();
    },
    deleteItem(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteRequisitionItem(rows[index].id).then((res) => {
          this.pageIndex = 1;
          rows.splice(index, 1);
          this.searchParam.pageable.pageNo =
            rows.length === 0 && this.searchParam.pageable.pageNo != 0
              ? this.searchParam.pageable.pageNo - 1
              : this.searchParam.pageable.pageNo;
          this.page();
          this.$message({
            message: "删除成功",
            type: "success",
          });
        });
      });
    },
    //编辑数据
    editItem(item) {
      this.$router.push({
        path: "/editMaterialRequisition",
        query: { id: item.id },
      });
    },
    //点击页码
    pageIndexChange(index) {
      this.searchParam.pageable.pageNo = index - 1;
      this.page();
    },
    //初始化DataGrid数据
    page() {
      findMaterialRequisitionPageData(this.searchParam).then((response) => {
        this.tableData = response.data.content;
        this.searchParam.pageable.totalPages = res.data.totalPages;
        this.searchParam.pageable.pageNo = res.data.pageable.pageNumber;
        this.searchParam.pageable.pageIndex = res.data.pageable.pageNumber + 1;
        this.searchParam.pageable.pageSize = res.data.pageable.pageSize;
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddMaterialRequisition() {
      this.$router.push("/addMaterialRequisition");
    },
    changBg(index) {
      this.ischange = index;
    },
    viewMaterialPlanList(index, rows) {
      this.$router.push({
        path: "/materialPlanDetail",
        query: { id: rows[index].id, type: 0 },
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
.uploadFont {
  color: white !important;
  font-size: 12px;
  height: 8px;
}
</style>
