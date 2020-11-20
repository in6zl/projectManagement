<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('main.orderId')"
                size="small"
                style="width:95%"
                v-model="searchParam.orderNo"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                :placeholder="$t('main.placeInsert')+$t('table.materialName')"
                size="small"
                style="width:95%"
                v-model="searchParam.materialName"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-select
                v-model="searchParam.supplierId"
                filterable
                remote
                clearable
                :remote-method="searchSupplier"
                :loading="searching"
                placeholder="选择供应商"
                size="small"
                style="width:95%"
              >
                <el-option
                  v-for="item in supplierOptions"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item slot="three">
              <el-button
                type="primary"
                size="small"
                @click="search"
                style="float:right;margin:0 16px 0 0;"
              >{{$t('button.search')}}</el-button>
            </el-form-item>
          </search-form>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">原材料订单</div>
          <div>
            <el-button
              size="small"
              v-if="haveBtn('supplierOrder:add')"
              @click="toAddOrg(),changBg(1)"
              type="primary"
              style="float:left;margin-right:20px"
            >{{$t('button.add')}}</el-button>
            <el-button
              size="small"
              v-if="haveBtn('supplierOrder:add')"
              @click="downloadExcel(),changBg(1)"
              type="primary"
              style="float:left;margin-right:20px"
            >{{$t('button.download')}}</el-button>
            <el-upload
              class="upload"
              action="123"
              :http-request="upload"
              :file-list="fileList"
              :show-file-list="false"
              accept=".xlsx"
            >
              <el-button v-if="haveBtn('supplierOrder:add')" size="small" type="primary">点击上传</el-button>
            </el-upload>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
          ref="table"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            prop="orderNo"
            :label="$t('table.orderName')"
            :show-overflow-tooltip="true"
          ></el-table-column>

          <el-table-column
            prop="supplier"
            :label="$t('main.supplierName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="material.name"
            :label="$t('table.materialName')"
            :show-overflow-tooltip="true"
          ></el-table-column>

          <el-table-column
            prop="unit"
            :label="$t('inoutBound.unitName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="num" :label="$t('table.num')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="deliveryDate"
            :label="$t('table.deliveryDate')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="deliveryNum"
            :label="$t('table.item')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="amount" :label="$t('table.amount')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="documentDate"
            :label="$t('table.documentDate')"
            :show-overflow-tooltip="true"
          ></el-table-column>

          <el-table-column
            prop="opration"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <svg-icon
                v-if="haveBtn('supplierOrder:delete')"
                icon-class="delete"
                @click.native="deleteRow(scope.$index, tableData)"
              ></svg-icon>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <page
      :totalElements="pageParam.totalElements"
      :pageNo="pageParam.pageNo"
      :pageSize="pageParam.pageSize"
      @currentChange="pageIndexChange"
    />
  </div>
</template>

<script>
import {
  listMaterialOrder,
  deleteMaterialOrder,
  listMaterialOrderPage,
  downloadTemplate,
  uploadExcel,
} from "@/api/materialOrder";
import { listSupplier } from "@/api/supplier";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import page from "@/components/common/page";
export default {
  components: {
    searchForm,
    lef,
    page,
  },
  data() {
    return {
      url: "",
      orderNo: "",
      materialId: "",
      index: 1,
      ischange: 1,
      tableHeight: window.innerHeight - 330,
      tableData: [],
      fileList: [],
      supplierOptions: [],
      searchParam: {
        orderNo: "",
        materialName: "",
        supplierId: "",
      },
      pageParam: {
        pageNo: 0,
        pageSize: 10,
        totalPages: 0,
        totalElements: 0,
      },
      searching: false,
    };
  },

  mounted() {
    this.listPage();
    this.url = process.env.VUE_APP_BASE_URL + "/api/materialOrder/readExcel";
  },
  methods: {
    searchSupplier(query) {
      if (query !== "") {
        this.searching = true;
        listSupplier(`name=${query}`).then((res) => {
          this.searching = false;
          let { data } = res;
          this.supplierOptions = data;
        });
      } else {
        this.supplierOptions = [];
      }
    },
    downloadExcel() {
      downloadTemplate().then((res) => {
        let url = window.URL.createObjectURL(res);
        let link = document.createElement("a");
        link.style.display = "none";
        link.href = url;
        link.setAttribute("download", "supplierOrder.xlsx");
        document.body.appendChild(link);
        link.click();
      });
    },
    upload(file) {
      let formData = new FormData();
      formData.set("file", file.file);
      uploadExcel(formData).then((res) => {
        // debugger
        if (res.code == 0) {
          this.$message({
            type: "success",
            message: "上传成功",
          });
          this.listPage();
        } else {
          this.$message({
            message: res.message,
          });
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddOrg() {
      this.$router.push("/addSupplierOrderDetails");
    },
    changBg(index) {
      this.ischange = index;
    },
    listPage() {
      this.$refs.table.bodyWrapper.scrollTop = 0;
      this.searchParam = { ...this.searchParam, ...this.pageParam };
      this.searchParam.pageNo = this.searchParam.pageNo - 1;
      listMaterialOrderPage(this.searchParam).then((response) => {
        let { totalPages, totalElements, content, pageable } = response.data;
        this.tableData = content;
        this.pageParam = {
          totalPages,
          totalElements,
          pageSize: pageable.pageSize,
          pageNo: pageable.pageNumber + 1,
        };
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteMaterialOrder(rows[index].id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              rows.splice(index, 1);
              this.pageParam.deliveryNum =
                rows.length === 0 && this.searchParam.pageParam.deliveryNum != 0
                  ? this.searchParam.pageParam.deliveryNum - 1
                  : this.searchParam.pageParam.deliveryNum;
                 this.listPage();
            } else {
              this.$message.error(res.message);
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    pageIndexChange(index) {
      this.pageParam.pageNo = index;
      this.listPage();
    },
    updateDictType(index, rows) {
      let id = rows[index].id;
      this.$router.push({
        path: "editSupplierOrderDetails",
        query: { id: id },
      });
    },
    search() {
      this.listPage();
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.upload {
  float: left;
}
</style>
