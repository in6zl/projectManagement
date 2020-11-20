<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <!--<el-form-item :label="$t('main.productionPlanItemId')" style="float:left">
            <el-select
              v-model="productionPlanId"
              filterable
              placeholder="请选择"
              value
              style="width:95%"
              clearable
              size="small"
            >
              <el-option
                v-for="item in productionPlanItemList"
                :key="item.id"
                :label="item.orderNo"
                :value="item.id"
              />
            </el-select>
            </el-form-item>-->
            <el-form-item slot="first">
              <el-input
                v-model="examiner"
                :placeholder="$t('main.placeInsert')+$t('main.examiner')"
                size="small"
                style="width:95%"
              />
            </el-form-item>

            <el-form-item slot="second">
              <el-select
                v-model="pass"
                :placeholder="$t('main.placeInsert')+$t('main.pass')"
                style="width:95%"
                size="small"
                clearable
              >
                <el-option label="是" value="1" />
                <el-option label="否" value="0" />
              </el-select>
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
          <div style="line-height:34px" class="subtit">{{ $t('main.inspectionRecordName') }}</div>
          <!-- <el-button
            size="small"
            v-if="haveBtn('inspectionRecord:add')"
            :class="{changBg:ischange == 1}"
            @click="toAddOrg(),changBg(1)"
          >{{ $t('button.add') }}</el-button>-->
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />

          <el-table-column prop="recordBatch" label="批次号"  align="center" :show-overflow-tooltip="true" />
          <el-table-column prop="passName" :label="$t('table.pass')" align="center" :show-overflow-tooltip="true" />
          <el-table-column
            prop="examiner"
            :label="$t('table.examiner')"
            :show-overflow-tooltip="true"
             align="center"
          />
          <el-table-column prop="url" :label="$t('table.url')" align="center" :show-overflow-tooltip="true">
            <!-- 图片的显示 -->
            <template slot-scope="scope">
              <el-image
                :src="scope.row.url"
                :preview-src-list="preview(scope.row.url)"
                style="width: 50%; height: 50%"
              />
            </template>
          </el-table-column>

          <el-table-column
            prop="createTime"
            :label="$t('table.createTime')"
            :show-overflow-tooltip="true" 
            align="center"
          />
          <!--<el-table-column
            prop="updateTime"
            :label="$t('table.updateTime')"
            width="140"
            :show-overflow-tooltip="true"
          />-->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <!--<svg-icon icon-class="edit" @click.native="updateDict(scope.$index,tableData)" />-->
              <svg-icon
                v-if="haveBtn('inspectionRecord:delete')"
                icon-class="delete"
                @click.native="deleteRow(scope.$index, tableData)"
              />
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{ pageStart }} 至 {{ pageEnd }} 项结果，共 {{ searchParam.pageParam.totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          background
          small
          @current-change="pageIndexChange"
          :page-count="searchParam.pageParam.totalPages"
          :current-page.sync="searchParam.pageParam.pageIndex"
        />
      </div>
    </div>
  </div>
</template>

<script>
import searchForm from "@/components/commonTop/index";
import {
  deleteInspectionRecord,
  searchInspectionRecord,
} from "@/api/inspectionRecord";
import { list } from "@/api/productionPlanItem";
import lef from "@/components/common/l_icon";

const id = 0;
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      productionPlanItemList: [],
      centerDialogVisible: false,
      productionPlanId: "",
      examiner: "",
      pass: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      pageTotal: 0,
      pageSize: 5,
      pageNo: 1,
      totalElements: 0,
      searchParam: {
        examiner: undefined,
        pass: undefined,
        productionPlanItem: undefined,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
    };
  },
  computed: {
    pageStart() {
      return (
        this.searchParam.pageParam.pageNo *
          this.searchParam.pageParam.pageSize +
        1
      );
    },
    pageEnd() {
      return (
        (this.searchParam.pageParam.pageNo + 1) *
        this.searchParam.pageParam.pageSize
      );
    },
  },
  mounted() {
    this.page();
    // this.findProductionPlanData();
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddOrg() {
      this.$router.push("/addInspectionRecord");
    },
    changBg(index) {
      this.ischange = index;
    },
    page() {
      searchInspectionRecord(this.searchParam).then((response) => {
        let inspections = response.data.content;
        inspections.forEach((inspection) => {
          inspection["passName"] = inspection.pass === "0" ? "不通过" : "通过";
        });
        this.tableData = inspections;
        this.searchParam.pageParam.totalPages = response.data.totalPages;
        this.searchParam.pageParam.pageNo = response.data.pageable.pageNumber;
        this.searchParam.pageParam.pageIndex =
          response.data.pageable.pageNumber + 1;
        this.searchParam.pageParam.pageSize = response.data.pageable.pageSize;
      });
    },
    // findProductionPlanData() {
    //   findAllProductionPlanData({}).then((res) => {
    //     if (res.code === "0") {
    //       this.productionPlanItemList = res.data.content;
    //     }
    //   });
    // },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteInspectionRecord(rows[index].id).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "删除成功",
              type: "success",
            });
            rows.splice(index, 1);
            this.searchParam.pageParam.pageNo =
              rows.length === 0 && this.searchParam.pageParam.pageNo != 0
                ? this.searchParam.pageParam.pageNo - 1
                : this.searchParam.pageParam.pageNo;
            this.page();
          }
        });
      });
    },
    pageIndexChange(index) {
      this.searchParam.pageParam.pageNo = index - 1;
      this.page();
    },
    updateDict(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editInspectionRecord", query: { id: id } });
    },
    preview(url) {
      let list = [];
      list.push(url);
      return list;
    },
    search() {
      this.searchParam = {
        examiner: this.examiner,
        pass: this.pass,
        productionPlanItem: { productionPlanId: this.productionPlanId },
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      const productionPlanItem = {};
      productionPlanItem.productionPlanId = this.productionPlanId;
      this.page();
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";

.main > .el-upload {
  width: 100% !important;
}

.main > .el-upload-dragger {
  width: 100% !important;
}
</style>
