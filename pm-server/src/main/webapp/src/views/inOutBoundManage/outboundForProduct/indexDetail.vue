<template>
  <div>
    <div class="main">
      <div style="width:300px"> {{ $t('route.outboundOrderProductDetail') }}</div>
      <div style="overflow:hidden">
        <el-form ref="form" :model="searchForm" :inline="true">
          <el-form-item prop="outProductOrderNo" :label="$t('inoutBound.outProductOrderNo')" style="float:left">
            <el-input
              v-model="outProductOrderNo"
              size="small"
              style="width:224px"
              disabled
            />
          </el-form-item>
          <el-form-item prop="productId" :label="$t('inoutBound.productName')" style="float:left">
            <el-select v-model="searchForm.productId" size="small" style="width:224px" filterable placeholder="请选择" value="">
              <el-option
                v-for="item in productList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item prop="binId" :label="$t('inoutBound.binName')" style="float:left">
            <el-select v-model="searchForm.binId" size="small" style="width:224px" filterable placeholder="请选择" value="">
              <el-option
                v-for="item in binList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
          <el-form-item style="float:right">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="search">{{ $t('button.search') }}</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetForm('form')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="userEdit">
        <el-button
          icon="el-icon-plus"
          size="small"
          :class="{changBg:isChange === 1}"
          @click="toAdd(),changBg(1)"
        >{{ $t('button.add') }}
        </el-button>
      </div>
      <div class="table">
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="100" :label="$t('table.serial')" />
          <el-table-column
            prop="productName"
            :label="$t('inoutBound.productName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unitName"
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="binName"
            :label="$t('inoutBound.binName')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unitNum"
            :label="$t('inoutBound.unitNum')"
            width="150"
            :show-overflow-tooltip="true"
          />
          <!------------------------------->
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            fixed="right"
            align="right"
          >
            <template slot-scope="scope">
              <svg-icon
                icon-class="edit"
                @click.native="update(scope.$index,tableData)"
              />
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
        />
      </div>
    </div>
  </div>
</template>

<script>
import { pageOutboundOrderProductDetail, deleteOutboundOrderProductDetail } from '@/api/outboundOrderProductDetail'
import { getFaStorehouseList } from '@/api/faStorehouse'
import { prolist } from '@/api/product'

export default {
  data: function() {
    return {
      // 查询参数
      searchForm: {
        productId: '',
        binId: '',
        pageNo: 0,
        pageSize: 10
      },
      productList: [],
      binList: [],
      outProductOrderNo: this.$route.query.outProductOrderNo,
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 400,
      tableData: [],
      totalElements: 0,
      totalPages: 0
    }
  },
  mounted() {
    this.page()
    getFaStorehouseList({}).then(res => {
      if (res.code === '0') {
        this.binList = res.data
      }
    })
    prolist({}).then(res => {
      if (res.code === '0') {
        this.productList = res.data
      }
    })
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return 'background-color: #FAFAFA;color: #999999;font-weight: 500;'
      }
    },
    toAdd() {
      this.$router.push({ path: 'addOutboundOrderProductDetail', query: { id: this.$route.query.id, outProductOrderNo: this.outProductOrderNo }})
    },
    changBg(index) {
      this.isChange = index
    },
    page() {
      const data = {
        product: {
          id: this.searchForm.productId
        },
        bin: {
          id: this.searchForm.binId
        },
        outboundOrderProduct: {
          id: this.$route.query.id
        },
        pageParam: { pageNo: this.searchForm.pageNo, pageSize: this.searchForm.pageSize }
      }
      pageOutboundOrderProductDetail(data).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.content
          this.totalElements = res.data.totalElements
          this.totalPages = res.data.totalPages
        }
      })
    },
    handleCurrentChange(cpage) {
      this.searchForm.pageNo = cpage - 1
      const data = {
        pageParam: { pageNo: this.searchForm.pageNo, pageSize: this.searchForm.pageSize }
      }
      this.page(data)
    },
    deleteRow(index, rows) {
      this.$confirm('确定删除此数据吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteOutboundOrderProductDetail(rows[index].id).then(res => {
          if (res.code === '0') {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.page()
          }
        })
      })
    },
    update(index, rows) {
      const id = rows[index].id
      this.$router.push({ path: 'editOutboundOrderProductDetail', query: { id: id, outProductOrderId: this.$route.query.id, outProductOrderNo: this.outProductOrderNo }})
    },
    search() {
      const data = {
        product: {
          id: this.searchForm.productId
        },
        bin: {
          id: this.searchForm.binId
        },
        outboundOrderProduct: {
          id: this.$route.query.id
        },
        pageParam: { pageNo: this.searchForm.pageNo, pageSize: this.searchForm.pageSize }
      }
      const loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      })
      pageOutboundOrderProductDetail(data)
        .then(res => {
          if (res.code === '0') {
            loading.close()
            this.tableData = res.data.content
            this.totalElements = res.data.totalElements
            this.totalPages = res.data.totalPages
          } else {
            loading.close()
          }
        })
        .catch(() => {
          loading.close()
        })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style lang='scss' scoped>
  @import "~@/styles/variables.scss";
  @import "~@/styles/systemStyle/car.scss";
</style>
