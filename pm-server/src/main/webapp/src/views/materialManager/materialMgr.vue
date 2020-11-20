<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <!-- 用户搜索栏 -->
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input placeholder="请输入物料名称" v-model="searchName" size="small" style="width:95%"></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input placeholder="请输入物料单位" v-model="searchUnit" size="small" style="width:95%"></el-input>
            </el-form-item>
            <el-form-item slot="three" class="seachBtn">
              <el-button type="primary" size="small" @click="search">{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <!-- 数据展示栏 -->
      <div class="roleManageTable">
        <!-- 用户编辑栏 -->
        <lef :top="23"></lef>
        <div class="userEdit">
          <div>
            <div style="line-height:34px" class="subtit">物料管理</div>
          </div>
          <div>
            <el-button
              size="small"
              @click="toAddMaterial(),changBg(1)"
              type="primary"
              style="margin-bottom:10px"
            >新增</el-button>
            <!-- <el-button size="small" @click="addTag" :class="{changBg:ischange == 1}">快速创建标签</el-button> -->
            <el-button
              size="small"
              @click="showBatchBindTag"
              :class="{changBg:ischange == 1}"
              style="height:34px"
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
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="code" label="编码" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="name" label="物料名称 " :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="spec" label="规格" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="unitName" label="物料单位" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column label="标签" :show-overflow-tooltip="true">
            <template slot-scope="scope">{{transfer(scope.row.tags)}}</template>
          </el-table-column>
          <el-table-column prop="operation" label="操作" width="150" align="center">
            <template slot-scope="scope">
              <el-tooltip content="编辑标签" placement="top" v-if="haveBtn('faWareHouseTagManage')">
                <svg-icon icon-class="example" @click="tagRelationship(scope.$index,tableData)"></svg-icon>
              </el-tooltip>
              <el-tooltip content="修改" placement="top">
                <svg-icon
                  icon-class="edit"
                  @click="editItem(scope.row)"
                  :hidden="scope.row.show==false"
                ></svg-icon>
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon
                  icon-class="delete"
                  @click="deleteItem(scope.$index, tableData)"
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
      @currentChange="pageIndexChange"
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
            size="small"
            type="primary"
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
  findAllMaterialData,
  deleteItem,
  addTags,
  add,
  batchBindTag,
  edit,
  updateAll,
} from "@/api/material";
import { parseTime } from "@/utils/index";
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
      searchName: "",
      searchUnit: "",
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      pageTotal: 0,
      pageSize: 10,
      pageIndex: 1,
      totalElements: "",
      deleteType: ["否", "是"],
      isDelete: "",
      material: {
        isDelete: "",
        name: "",
        unitName: "",
        pageIndex: "",
        pageSize: "",
      },
      num: 1,
      currentPage: 1,
      //checkbox选中的用于快速生成标签的物料列表
      selectedMaterial: undefined,
      dialogVisible: false,
      ruleForm: { faWarehouseTagSel: [], faWarehouseTagBatchSel: [] },
      faWarehouseTagList: [],
      //选中的用于编辑标签的物料对象
      selectedMaterialTag: undefined,
      batchDialogVisible: false,
    };
  },
  mounted() {
    this.initFindAllMaterialData();
    getWareHouseTagList(new Object()).then((res) => {
      this.faWarehouseTagList = res.data;
    });
  },
  methods: {
    showBatchBindTag() {
      if (!this.selectedMaterial || this.selectedMaterial.length == 0) {
        this.$message({
          message: "请先选择需要分配标签的成品",
          type: "success",
        });
        return;
      }
      this.ruleForm.faWarehouseTagBatchSel = [];
      this.batchDialogVisible = true;
    },
    submitTageBatchFrom() {
      let selectedMaterial = JSON.parse(JSON.stringify(this.selectedMaterial));
      selectedMaterial.forEach((product) => {
        product.tags = [];
      });
      if (
        this.ruleForm.faWarehouseTagBatchSel &&
        this.ruleForm.faWarehouseTagBatchSel.length > 0
      ) {
        this.ruleForm.faWarehouseTagBatchSel.forEach((tag) => {
          selectedMaterial.forEach((product) => {
            product.tags.push({ id: tag });
          });
        });
      }
      console.log(selectedMaterial)
      batchBindTag(selectedMaterial).then((res) => {
        this.$message({
          message: "分配成功",
          type: "success",
        });
        this.batchDialogVisible = false;
        this.initFindAllMaterialData();
      });
    },

    submitTageFrom() {
      this.selectedMaterialTag.tags = [];
      if (this.ruleForm.faWarehouseTagSel) {
        this.ruleForm.faWarehouseTagSel.forEach((tagSel) => {
          this.faWarehouseTagList.forEach((tagListSingle) => {
            if (tagSel == tagListSingle.id) {
              this.selectedMaterialTag.tags.push(tagListSingle);
            }
          });
        });
      }
      updateAll(this.selectedMaterialTag).then((res) => {
        if (res.code == 0) {
          this.$message({
            message: "编辑成功",
            type: "success",
          });
          this.dialogVisible = false;
          this.initFindAllMaterialData();
        }
      });
    },
    hiddenDialog() {
      this.dialogVisible = false;
    },
    tagRelationship(index, tableData) {
      this.selectedMaterialTag = tableData[index];
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
      if (!this.selectedMaterial) {
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
          addTags(this.selectedMaterial).then((res) => {
            if (res.code == 0) {
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.initFindAllMaterialData();
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    handleSelectionChange(rows) {
      this.selectedMaterial = rows;
    },
    search() {
      this.currentPage = 1;
      this.material.name = this.searchName;
      this.material.isDelete = this.isDelete;
      this.material.unitName = this.searchUnit;
      this.material.pageIndex = this.pageIndex - 1;
      this.material.pageSize = this.pageSize;
      findAllMaterialData(this.material).then((response) => {
        let content = response.data.content;
        content.forEach((element) => {
          if (element.isDelete === 0) {
            element["show"] = true;
          } else {
            element["show"] = false;
          }
        });
        this.tableData = content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    deleteItem(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteItem(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              let number =
                rows.length === 0 && this.num != 1 ? this.num - 1 : this.num;
              this.pageIndexChange(number);
            } else {
              this.$message({
                message: res.message,
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    //编辑数据
    editItem(item) {
      this.$router.push({
        path: "/editMaterial",
        query: { id: item.id },
      });
    },
    //点击页码
    pageIndexChange(index) {
      let data = this.material;
      data.pageIndex = index - 1;
      data.pageSize = this.pageSize;
      this.pageIndex = index;
      this.num = index;
      findAllMaterialData(data).then((response) => {
        let content = response.data.content;
        content.forEach((element) => {
          if (element.isDelete === 0) {
            element["show"] = true;
          } else {
            element["show"] = false;
          }
        });
        this.tableData = content;
        this.pageTotal = response.data.totalPages;
        this.totalElements = response.data.totalElements;
      });
    },
    //初始化DataGrid数据
    initFindAllMaterialData() {
      findAllMaterialData({
        isDelete: 0,
        pageIndex: this.pageIndex - 1,
        pageSize: this.pageSize,
      }).then((response) => {
        if (response && response.code) {
          this.tableData = response.data.content;
          this.pageTotal = response.data.totalPages;
          this.totalElements = response.data.totalElements;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddMaterial() {
      this.$router.push("/addMaterial");
    },
    changBg(index) {
      this.ischange = index;
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
</style>