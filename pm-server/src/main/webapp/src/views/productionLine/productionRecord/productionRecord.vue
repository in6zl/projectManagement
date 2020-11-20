<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                v-model="queryParams.batch"
                :placeholder="$t('main.placeInsert')+$t('productionRecord.batch')"
                size="small"
                style="width:95%"
              />
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                v-model="queryParams.productName"
                :placeholder="$t('main.placeInsert')+$t('productionRecord.productName')"
                size="small"
                style="width:95%"
              />
            </el-form-item>
            <el-form-item prop="status" slot="three">
              <el-select
                v-model="queryParams.status"
                size="small"
                style="width:95%"
                filterable
                placeholder="请选择状态"
                value
                clearable
              >
                <el-option
                  v-for="item in statusList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </search-form>

          <el-button
            style="float:right;margin:0 16px 16px 0;"
            v-if="haveBtn('productionRecord:list')"
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
          <div style="line-height:34px" class="subtit">生产记录</div>
          <div>
            <!--<el-button
              v-if="haveBtn('productionRecord:add')"
              size="small"
              :class="{changBg:ischange == 1}"
              @click="toAdd(),changBg(1)"
            >{{ $t('button.add') }}</el-button>-->
            <el-button
              v-if="haveBtn('productionRecord:add')"
              size="small"
              type="primary"
              @click="inbound(),changBg(1)"
            >入库</el-button>
          </div>
        </div>
        <el-table
          ref="tableData"
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="selection" width="60" align="center" :selectable="isSelect" />
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            prop="batch"
            :label="$t('productionRecord.batch')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="productName"
            :label="$t('productionRecord.productName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="num"
            :label="$t('productionRecord.num')"
            :show-overflow-tooltip="true"
          />
          <el-table-column prop="productionDate" label="生产时间" :show-overflow-tooltip="true" />
          <el-table-column
            prop="status"
            label="状态"
            :show-overflow-tooltip="true"
            :formatter="statusFormatter"
          />
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <el-tooltip v-if="scope.row.status=== 0" content="检查" placement="top">
                <svg-icon
                  v-if="haveBtn('productionRecord:edit')"
                  icon-class="check"
                  @click="openDialog(scope.$index, tableData)"
                />
              </el-tooltip>
              <el-tooltip content="详情" placement="top">
                <svg-icon
                  v-if="haveBtn('productionRecord:list')"
                  icon-class="look"
                  @click.native="lookRow(scope.$index, tableData)"
                />
              </el-tooltip>
              <!--<el-tooltip content="编辑" placement="top">
                <svg-icon icon-class="edit" @click.native="edit(scope.$index,tableData)" />
              </el-tooltip>-->
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  v-if="haveBtn('productionRecord:delete')"
                  icon-class="delete"
                  @click.native="deleteRow(scope.$index, tableData)"
                />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 {{pageStart}} 至 {{pageEnd}} 项结果，共{{ searchParam.pageParam.totalPages }}页</div>
      <div>
        <el-pagination
          :hide-on-single-page="true"
          layout="prev, pager, next"
          background
          small
          @current-change="handleCurrentChange"
          :page-count="searchParam.pageParam.totalPages"
          :current-page.sync="searchParam.pageParam.pageIndex"
        />
      </div>
    </div>
    <!--弹框-->
    <el-dialog title="新增检查记录" :visible.sync="dialogFormVisible" width="50%">
      <el-form
        ref="ruleFormDetail"
        :model="ruleFormDetail"
        :rules="rulesDetail"
        label-width="150px"
        class="demo-ruleForm"
      >
        <el-form-item label="批次号" prop="batch" size="small">
          <el-input v-model="ruleFormDetail.batch" :disabled="true" style="width:80%" />
        </el-form-item>
        <el-form-item label="是否通过" prop="pass" size="small">
          <el-select v-model="ruleFormDetail.pass" style="width:80%">
            <el-option label="是" value="1" />
            <el-option label="否" value="0" />
          </el-select>
        </el-form-item>
        <el-form-item label="检查人" prop="examiner" size="small">
          <el-input v-model="ruleFormDetail.examiner" style="width:80%" />
        </el-form-item>
        <el-form-item label="图片" prop="url" size="small">
          <el-upload
            ref="upload"
            :file-list="imgList"
            name="goodTakenImages"
            action="/api/common/upload"
            list-type="picture-card"
            :on-remove="handleRemove"
            :limit="1"
            :on-preview="handlePictureCardPreview"
            :on-exceed="handleLimit"
            :on-success="handleSuccess"
            accept=".jpg, .jpeg, .png, .JPG, .JPEG, .bmp"
          >
            <i class="el-icon-plus" />
          </el-upload>
        </el-form-item>
        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button size="small" type="primary" @click="submitFormDetail('ruleFormDetail')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { deletePr, searchPr } from "@/api/productionRecord";
import { addInspectionRecord } from "@/api/inspectionRecord";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      dialogFormVisible: false,
      imageIsShow: false,
      dialogImageUrl: "",
      queryParams: {
        pageNum: 0,
        pageSize: 10,
        totalElements: 0,
        batch: "",
        productName: "",
      },
      searchParam: {
        batch: undefined,
        productName: undefined,
        status: undefined,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      },
      statusList: [
        { id: 0, name: "待检测" },
        { id: 1, name: "检测通过" },
        { id: 2, name: "检测未通过" },
        { id: 3, name: "已入库" },
      ],
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 350,
      tableData: [],
      ruleFormDetail: {},
      multipleSelection: [],
      rulesDetail: {
        productionPlanItemId: [
          { required: true, message: "请输入生产计划项", trigger: "blur" },
        ],
        pass: [{ required: true, message: "请输入是否通过", trigger: "blur" }],
        examiner: [
          { required: true, message: "请输入检查人", trigger: "blur" },
        ],
        // url: [{ required: true, message: "请上传图片", trigger: "blur" }]
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
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },

    handleCurrentChange(val) {
      this.searchParam.pageParam.pageNo = index - 1;
      this.page();
    },

    handleSizeChange() {},

    toAdd() {
      this.$router.push("/addProductionRecord");
    },
    inbound() {
      const arr = this.$refs.tableData.selection;
      if (arr.length !== 0) {
        // 数据带到成品入库页面
        this.$router.push({
          path: "addInboundOrderProduct",
          query: { productList: arr },
        });
      } else {
        this.$message.info("请选择入库项");
      }
    },
    isSelect(row, index) {
      if (row.status === 1) {
        return 1;
      } else {
        return 0;
      }
    },
    changBg(index) {
      this.ischange = index;
    },

    page() {
      searchPr(this.queryParams).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.searchParam.pageParam.totalPages = res.data.totalPages;
          this.searchParam.pageParam.pageNo = res.data.pageable.pageNumber;
          this.searchParam.pageParam.pageIndex =
            res.data.pageable.pageNumber + 1;
          this.searchParam.pageParam.pageSize = res.data.pageable.pageSize;
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deletePr(rows[index].id).then((res) => {
          if (res.code === "0") {
            rows.splice(index, 1);
            this.searchParam.pageParam.pageNo =
              rows.length === 0 && this.searchParam.pageParam.pageNo != 0
                ? this.searchParam.pageParam.pageNo - 1
                : this.searchParam.pageParam.pageNo;
            this.page();
            this.$message({
              message: "删除成功",
              type: "success",
            });
            // rows.splice(index, 1);
          }
        });
      });
    },
    edit(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "/editProductionRecord", query: { id: id } });
    },
    search() {
      this.searchParam = {
        batch: this.queryParams.batch,
        productName: this.queryParams.productName,
        status: this.queryParams.status,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
          totalPages: undefined,
          pageIndex: undefined,
        },
      };
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.page();
      loading.close();
    },
    lookRow(index, tableData) {
      const id = tableData[index].id;
      this.$router.push({ path: "/detailProductionRecord", query: { id: id } });
    },
    statusFormatter(row) {
      if (row.status === 1) {
        return "检查通过";
      } else if (row.status === 2) {
        return "检查未通过";
      } else if (row.status === 3) {
        return "待入库";
      } else if (row.status === 4) {
        return "已入库";
      } else {
        return "待检查";
      }
    },
    /**
     * 添加弹框
     */
    openDialog(index, rows) {
      this.ruleFormDetail = {};
      this.ruleFormDetail.batch = rows[index].batch;
      this.ruleFormDetail.recordId = rows[index].id;
      this.dialogFormVisible = true;
      this.$refs.upload.clearFiles();
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    submitFormDetail(formName) {
      const data = {
        examiner: this.ruleFormDetail.examiner,
        pass: this.ruleFormDetail.pass,
        url: this.ruleFormDetail.url,
        record: {
          id: this.ruleFormDetail.recordId,
        },
      };
      this.$refs[formName].validate((valid) => {
        if (valid) {
          addInspectionRecord(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "操作成功",
                type: "success",
              });
              this.dialogFormVisible = false;
              this.search();
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.imageIsShow = true;
    },
    handleLimit(file, fileList) {
      this.$message({
        message: "图片不得超过1张",
        icon: "error",
      });
    },
    handleSuccess(file, fileList) {
      this.ruleFormDetail.url = file.data;
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
