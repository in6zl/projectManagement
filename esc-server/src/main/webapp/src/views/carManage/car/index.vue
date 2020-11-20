<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form ref="form" :model="searchForm">
          <search-form>
            <el-form-item prop="carNo" slot="first">
              <el-input
                v-model="searchForm.carNo"
                :placeholder="'请输入'+$t('car.carNo')"
                size="small"
                style="width:95%"
              />
            </el-form-item>
            <el-form-item prop="type" slot="second">
              <el-select
                v-model="searchForm.type"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('car.type')"
                value
                clearable
              >
                <el-option
                  v-for="item in typeList"
                  :key="item.id"
                  :label="item.dictKey"
                  :value="item.dictValue"
                />
              </el-select>
            </el-form-item>
            <el-form-item prop="inUse" slot="three">
              <el-select
                v-model="searchForm.inUse"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('car.inUse')"
                value
                clearable
              >
                <el-option
                  v-for="item in inUseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item prop="isOpen" slot="first">
              <el-select
                v-model="searchForm.isOpen"
                size="small"
                style="width:95%"
                filterable
                :placeholder="'请选择'+$t('car.isOpen')"
                value
                clearable
              >
                <el-option
                  v-for="item in isOpenList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item slot="three">
              <el-button
                style="float:right;margin:8px 16px 0 0;"
                type="primary"
                size="mini"
                @click="search"
              >{{ $t('button.search') }}</el-button>
            </el-form-item>
          </search-form>

          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="15"></lef>
        <div class="userEdit">
          <div style="line-heigth:34px" class="subtit">{{ $t('route.car') }}</div>
          <el-button size="small" type="primary" @click="toAdd(),changBg(1)">{{ $t('button.add') }}</el-button>
        </div>
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column prop="carNo" :label="$t('car.carNo')" :show-overflow-tooltip="true" />
          <el-table-column
            prop="driverName"
            :label="$t('car.driverName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="driverPhone"
            :label="$t('car.driverPhone')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="type"
            :label="$t('car.type')"
            :formatter="typeFormatter"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="isOpen"
            :label="$t('car.isOpen')"
            :formatter="isOpenFormatter"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="inUse"
            :label="$t('car.inUse')"
            :formatter="inUseFormatter"
            :show-overflow-tooltip="true"
          />
          <!------------------------------->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            width="150"
            align="center"
          >
            <template slot-scope="scope">
              <svg-icon icon-class="edit" @click.native="update(scope.$index,tableData)" />
              <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)" />
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共{{ totalPages }}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="totalElements"
          background
          small
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { pageCar, deleteCar } from "@/api/car";
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";
import { getValue } from "@/api/dict";
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      // 查询参数
      searchForm: {
        carNo: "",
        type: "",
        inUse: "",
        isOpen: "",
        pageNo: 0,
        pageSize: 10,
      },
      typeList: [],
      inUseList: [
        { id: 0, name: "暂未使用" },
        { id: 1, name: "已使用" },
      ],
      isOpenList: [
        { id: 0, name: "停用" },
        { id: 1, name: "非停用" },
      ],
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 400,
      tableData: [],
      totalElements: 0,
      totalPages: 0,
      currentPage: 1,
      num: 1,
      car: {
        carNo: "",
        type: "",
        inUse: "",
        isOpen: "",
      },
    };
  },
  mounted() {
    this.page();
    getValue("车辆类型").then((res) => {
      this.typeList=res.data
    });
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAdd() {
      this.$router.push("addCar");
    },
    changBg(index) {
      this.isChange = index;
    },
    page() {
      const data = {
        carNo: this.searchForm.carNo,
        type: this.searchForm.type,
        inUse: this.searchForm.inUse,
        isOpen: this.searchForm.isOpen,
        pageParam: {
          pageNo: this.searchForm.pageNo,
          pageSize: this.searchForm.pageSize,
        },
      };
      pageCar(data).then((res) => {
        if (res.code === "0") {
          this.tableData = res.data.content;
          this.totalElements = res.data.totalElements;
          this.totalPages = res.data.totalPages;
        }
      });
    },
    handleCurrentChange(cpage) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.num = cpage;
      pageCar({
        carNo: this.car.carNo,
        type: parseInt(this.car.type) ,
        inUse: this.car.inUse,
        isOpen: this.car.isOpen,
        pageParam: {
          pageNo: cpage - 1,
          pageSize: 10,
        },
      })
        .then((res) => {
          if (res.code === "0") {
            loading.close();
            this.tableData = res.data.content;
            this.totalElements = res.data.totalElements;
            this.totalPages = res.data.totalPages;
          } else {
            loading.close();
          }
        })
        .catch(() => {
          loading.close();
        });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        deleteCar(rows[index].id).then((res) => {
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
            this.$message.error(res.message);
          }
        });
      });
    },
    update(index, rows) {
      const id = rows[index].id;
      this.$router.push({ path: "editCar", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      this.car.carNo = this.searchForm.carNo;
      this.car.type = this.searchForm.type;
      this.car.inUse = this.searchForm.inUse;
      this.car.isOpen = this.searchForm.isOpen;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      pageCar({
        carNo: this.car.carNo,
        type: parseInt(this.car.type),
        inUse: this.car.inUse,
        isOpen: this.car.isOpen,
        pageParam: {
          pageNo: 0,
          pageSize: 10,
        },
      })
        .then((res) => {
          if (res.code === "0") {
            loading.close();
            this.tableData = res.data.content;
            this.totalElements = res.data.totalElements;
            this.totalPages = res.data.totalPages;
          } else {
            loading.close();
          }
        })
        .catch(() => {
          loading.close();
        });
    },
    typeFormatter(row, column) {
      let name=""
      this.typeList.forEach(type=>{
        if(row.type+""=== type.dictValue){
          name=type.dictKey;
        }
      })
      return name;
    },
    isOpenFormatter(row, column) {
      if (row.isOpen === 0) {
        return "停用";
      } else {
        return "非停用";
      }
    },
    inUseFormatter(row,column){
      if (row.inUse === 0) {
        return "未使用";
      } else {
        return "已使用";
      }
    }
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>
