<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                :placeholder="$t('product.placeholder')+$t('supMana.sendOrder.sendOrderNo')"
                size="small"
                style="width:95%"
                v-model="code"
                maxlength="30"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input
                :placeholder="$t('product.placeholder')+$t('car.carNo')"
                size="small"
                style="width:95%"
                v-model="carNo"
                maxlength="60"
              ></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-input
                :placeholder="$t('product.placeholder')+$t('supMana.warehouse.supplier')"
                size="small"
                style="width:95%"
                v-model="warehouseName"
                maxlength="11"
              ></el-input>
            </el-form-item>
          </search-form>
          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="small"
            @click="search"
          >{{$t('button.search')}}</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{ $t('route.preRegistration') }}</div>
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
            :label="$t('supMana.sendOrder.sendOrderNo')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="driverName"
            :label="$t('supMana.sendOrder.driverName')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="driverPhone"
            :label="$t('supMana.sendOrder.driverPhone')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="carNo"
            :label="$t('supMana.sendOrder.carNo')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="supplier.name"
            :label="$t('supMana.warehouse.supplier')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="stateName"
            :label="$t('supMana.sendOrder.state')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            align="center"
            width="150"
          >
            <template slot-scope="scope">
              <el-dropdown v-if="haveBtn('registration:edit')">
                <span class="el-dropdown-link">
                  <el-tooltip content="操作" placement="top" v-if="haveBtn('registration:list')">
                    <svg-icon icon-class="line"></svg-icon>
                  </el-tooltip>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item @click.native="registration(scope.$index,tableData)">登记</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="size"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          small
        ></el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import { pagePreRegistration } from "@/api/preRegistration";
import { registartionList, addRegistration } from "@/api/registration";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      tableData: [],
      preRegistrationForm: {
        code: "",
        carNo: "",
        supplier: {
          name: "",
        },
        pageNum: 0,
        pageSize: 10,
      },
      currentPage: 1,
      code: "",
      carNo: "",
      warehouseName: "",
      num: 1,
      tableHeight: window.innerHeight - 350,
      registrationList: [],
    };
  },
  mounted() {
    pagePreRegistration({
      pageNum: 0,
      pageSize: 10,
    }).then((res) => {
      this.tableData = res.data.content;
      this.totalPages = res.data.totalPages;
      this.size = res.data.totalPages * 10;
      registartionList({}).then((res) => {
        this.registrationList = res.data;
      });
    });
  },
  methods: {
    handleCurrentChange(val) {
      this.num = val;
      let curr = val - 1;
      this.preRegistrationForm.pageNum = curr;
      pagePreRegistration(this.preRegistrationForm).then((res) => {
        this.tableData = res.data.content;
        this.totalPages = res.data.totalPages;
        this.size = res.data.totalPages * 10;
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    registration(index, rows) {
      let registrationId = "";
      this.registrationList.forEach((re) => {
        if (re.carNo === rows[index].carNo && re.state <= 6) {
          registrationId = re.id;
        }
      });
      if (registrationId) {
        this.$confirm("此车牌号已被登记是否合并", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            let data = {
              id: registrationId,
              deliveryDTOList: rows[index].supplierSendOrderDeliveries,
            };
            addRegistration(data).then((res) => {
              this.$message({
                message: "合并成功!",
                type: "success",
              });
              this.$router.push({
                path: "/registration",
              });
            });
          })
          .catch(() => {
            var item = rows[index].id;
            this.$router.push({
              path: "/addRegistration",
              query: { item: item },
            });
          });
      } else {
        var item = rows[index].id;
        this.$router.push({
          path: "/addRegistration",
          query: { item: item },
        });
      }
    },
    search() {
      this.currentPage = 1;
      this.preRegistrationForm.code = this.code;
      this.preRegistrationForm.carNo = this.carNo;
      this.preRegistrationForm.supplier.name = this.warehouseName;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pagePreRegistration(this.preRegistrationForm).then((res) => {
        if (res.code === "0") {
          loading.close();
          let content = res.data.content;
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
</style>