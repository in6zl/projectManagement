<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('product.placeholder')+$t('product.productName')"
                size="small"
                style="width:95%"
                v-model="name"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                :placeholder="$t('product.placeholder')+$t('product.specification')"
                size="small"
                style="width:95%"
                v-model="spec"
              ></el-input>
            </el-form-item>
            <el-form-item slot="four" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
            <!-- <div style="clear:both"></div> -->
          </search-form>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit" style="display:flex">
          <div>
            <div style="line-height:34px" class="subtit">{{$t('product.productList')}}</div>
          </div>
          <div style="display:flex">
            <el-button
              size="small"
              @click="toAddProduct(),changBg(1)"
              type="primary"
              style="margin-bottom:10px"
            >{{$t('button.add')}}</el-button>

            <!-- <el-button size="small" @click="addTag" type="primary" style="margin-bottom:10px">快速创建标签</el-button>
              :class="{changBg:ischange == 1}"
              style="height:34px"
            >{{$t('button.add')}}</el-button>-->
            <el-upload
              action="api/product/file"
              accept=".xlsx, .xls"
              :on-success="upload"
              :on-error="error"
              :show-file-list="false"
            >
              <!-- <el-button
                size="small"
                type="primary"
                style="margin:0px 5px;height:33px"
              >{{$t('button.import')}}</el-button>-->
            </el-upload>
            <el-button
              size="small"
              @click="showBatchBindTag"
              :class="{changBg:ischange == 1}"
              style="margin-bottom:10px"
            >快速分配标签</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="60" align="center" :selectable="selectable"></el-table-column>
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="name"
            :label="$t('product.productName')"
            :show-overflow-tooltip="true"
          >
            <template slot-scope="scoped">
              <a
                @click="openDetial(scoped.$index,tableData)"
                style="color:#00f;"
              >{{scoped.row.name}}</a>
            </template>
          </el-table-column>
          <el-table-column
            prop="spec"
            :label="$t('product.specification')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="unitName"
            :label="$t('product.unit') "
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column label="标签" :show-overflow-tooltip="true">
            <template slot-scope="scope">{{transfer(scope.row.tags)}}</template>
          </el-table-column>
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
                  @click="updataProduct(scope.$index,tableData)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="编辑标签" placement="top" v-if="haveBtn('faWareHouseTagManage')">
                <svg-icon icon-class="example" @click="tagRelationship(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  icon-class="delete"
                  @click="deleteRow(scope.$index, tableData)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <paging
      :pageSize="pageSize"
      :pageNo="pageIndex"
      :totalElements="totalElements"
      @currentChange="handleCurrentChange"
    ></paging>
    <el-dialog title="编辑标签" :visible.sync="dialogVisible" width="50%">
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标签" prop="tag" size="small">
          <el-checkbox-group v-model="ruleForm.faWarehouseTagSel" id="tagList">
            <el-checkbox
              v-for="obj in faWarehouseTagList"
              :label="obj.id"
              :key="obj.id"
            >{{obj.name}}</el-checkbox>
          </el-checkbox-group>

          <!-- <el-button type="text" @click="openDialog">点击新增标签</el-button> -->
        </el-form-item>

        <el-form-item>
          <el-button @click="hiddenDialog" size="small" :class="{changBg:ischange == 2}">取消</el-button>
          <el-button
            @click="submitTageFrom()"
            type="primary"
            size="small"
            :class="{changBg:ischange == 1}"
          >确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="快速分配标签" :visible.sync="batchDialogVisible" width="50%">
      <el-form :model="ruleForm" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="标签" prop="tag" size="small">
          <el-checkbox-group v-model="ruleForm.faWarehouseTagBatchSel" id="tagList">
            <el-checkbox
              v-for="obj in faWarehouseTagList"
              :label="obj.id"
              :key="obj.id"
            >{{obj.name}}</el-checkbox>
          </el-checkbox-group>

          <!-- <el-button type="text" @click="openDialog">点击新增标签</el-button> -->
        </el-form-item>

        <el-form-item>
          <el-button @click="hiddenDialog" size="small" :class="{changBg:ischange == 2}">取消</el-button>
          <el-button @click="submitTageBatchFrom()" size="small" :class="{changBg:ischange == 1}">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
import {
  pageProduct,
  deleteProduct,
  getProductInfo,
  addTags,
  addProduct,
  batchBindTag,
  isUpdate,
} from "@/api/product";
import { Icon } from "element-ui";
import searchForm from "@/components/commonTop/index";
import { getWareHouseTagList } from "@/api/faWareHouseTag";
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
      index: 1,
      ischange: 1,
      tableData: [],
      name: "",
      spec: "",
      pageSize: 10,
      pageIndex: 1,
      totalElements: 0,
      size: "",
      num: 1,
      product: {
        name: "",
        spec: "",
        isDelete: "",
      },
      currentPage: 1,
      isDelete: "",
      deleteType: ["否", "是"],
      selectedProduct: undefined,
      dialogVisible: false,
      ruleForm: { faWarehouseTagSel: [], faWarehouseTagBatchSel: [] },
      faWarehouseTagList: [],
      //选中的用于编辑标签的物料对象
      selectedProductTag: undefined,
      batchDialogVisible: false,
    };
  },
  mounted() {
    this.productList();
    getWareHouseTagList(new Object()).then((res) => {
      this.faWarehouseTagList = res.data;
    });
  },
  methods: {
    showBatchBindTag() {
      if (!this.selectedProduct || this.selectedProduct.length == 0) {
        this.$message({
          message: "请先选择需要分配标签的成品",
          type: "success",
        });
        return;
      }
      this.ruleForm.faWarehouseTagBatchSel = [];
      this.batchDialogVisible = true;
    },
    submitTageFrom() {
      this.selectedProductTag.tags = [];
      if (this.ruleForm.faWarehouseTagSel) {
        this.ruleForm.faWarehouseTagSel.forEach((tagSel) => {
          this.faWarehouseTagList.forEach((tagListSingle) => {
            if (tagSel == tagListSingle.id) {
              this.selectedProductTag.tags.push(tagListSingle);
            }
          });
        });
      }
      addProduct(this.selectedProductTag).then((res) => {
        if (res.code == 0) {
          this.$message({
            message: "编辑成功",
            type: "success",
          });
          this.dialogVisible = false;
          this.productList();
        }
      });
    },
    submitTageBatchFrom() {
      console.log(
        "this.ruleForm.faWarehouseTagBatchSel",
        this.ruleForm.faWarehouseTagBatchSel
      );
      let selectedProduct = JSON.parse(JSON.stringify(this.selectedProduct));
      selectedProduct.forEach((product) => {
        product.tags = [];
      });
      if (
        this.ruleForm.faWarehouseTagBatchSel &&
        this.ruleForm.faWarehouseTagBatchSel.length > 0
      ) {
        this.ruleForm.faWarehouseTagBatchSel.forEach((tag) => {
          selectedProduct.forEach((product) => {
            product.tags.push({ id: tag });
          });
        });
      }
      batchBindTag(selectedProduct).then((res) => {
        this.$message({
          message: "分配成功",
          type: "success",
        });
        this.batchDialogVisible = false;
        this.productList();
      });
      // this.selectedProductTag.tags = [];

      // addProduct(this.selectedProductTag).then((res) => {
      //   if (res.code == 0) {
      //     this.$message({
      //       message: "编辑成功",
      //       type: "success",
      //     });
      //     this.dialogVisible = false;
      //     this.productList();
      //   }
      // });
    },
    hiddenDialog() {
      this.dialogVisible = false;
    },
    tagRelationship(index, tableData) {
      this.selectedProductTag = tableData[index];
      this.ruleForm.faWarehouseTagSel = [];
      if (tableData[index].tags) {
        tableData[index].tags.forEach((tag) => {
          this.ruleForm.faWarehouseTagSel.push(tag.id);
        });
      }
      this.dialogVisible = true;
    },
    transfer(tags) {
      let str = "";
      if (tags) {
        for (let i = 0; i < tags.length; i++) {
          if (i + 1 == tags.length) {
            str += tags[i].name;
          } else {
            str += tags[i].name + ",";
          }
        }
      }
      return str;
    },
    selectable(row, index) {
      if (row.tagState == 1) {
        return false;
      } else {
        return true;
      }
    },
    addTag() {
      if (!this.selectedProduct) {
        this.$message({
          message: "请先选择要添加标签的成品",
          type: "warning",
        });
        return false;
      }
      this.$confirm("是否确认添加标签", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          addTags(this.selectedProduct).then((res) => {
            if (res.code == 0) {
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.productList();
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    handleSelectionChange(rows) {
      this.selectedProduct = rows;
    },
    handleCurrentChange(val) {
      this.num = val;
      let curr = val - 1;
      let pageNum = curr,
        pageSize = 10;
      pageProduct(this.product, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalPages = res.data.totalPages;
          this.totalElements = res.data.totalElements;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddProduct() {
      this.$router.push("/addProduct");
    },
    upload() {
      this.$message({
        message: "添加完成",
        icon: "success",
      });
      this.productList();
    },
    error() {
      this.$message({
        message: "文件内容出错，添加失败",
        icon: "error",
      });
      this.productList();
    },
    changBg(index) {
      this.ischange = index;
    },
    productList() {
      let data = {},
        pageNum = 0,
        pageSize = 10;
      pageProduct(this.product, pageNum, pageSize).then((res) => {
        if (res.code === "0") {
          let content = res.data.content;
          this.tableData = content;
          this.totalPages = res.data.totalPages;
         this.totalElements = res.data.totalElements;
        }
      });
    },
    deleteRow(index, rows) {
      let id = rows[index].id;
      isUpdate(id).then((res) => {
        if (res.code === "0") {
          this.$confirm("确定删除此数据吗", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning",
          })
            .then(() => {
              deleteProduct(rows[index].id).then((res) => {
                if (res.code === "0") {
                  this.$message({
                    message: "删除成功",
                    type: "success",
                  });
                  rows.splice(index, 1);
                  let number =
                    rows.length === 0 && this.num != 1
                      ? this.num - 1
                      : this.num;
                  this.handleCurrentChange(number);
                }
              });
            })
            .catch(() => {
              return;
            });
        } else {
          this.$message({
            message: "已被引用，不能删除",
            type: "error",
          });
        }
      });
      //   debugger
    },
    updataProduct(index, rows) {
      let id = rows[index].id;
      isUpdate(id).then((res) => {
        if (res.code === "0") {
          this.$router.push({ path: "/editProduct", query: { id: id } });
        } else {
          this.$message({
            message: "已被引用，不能修改",
            type: "error",
          });
        }
      });
    },
    search() {
      this.currentPage = 1;
      this.product.name = this.name;
      this.product.spec = this.spec;
      this.product.isDelete = this.isDelete;
      let pageNum = 0,
        pageSize = 10;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageProduct(this.product, pageNum, pageSize).then((res) => {
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
          this.totalElements = res.data.totalElements;
        } else {
          loading.close();
        }
      });
    },
    openDetial(index, rows) {
      this.$router.push({
        path: "/productDetail",
        query: { id: rows[index].id },
      });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.el-table-column {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
