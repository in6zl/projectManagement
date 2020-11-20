<template>
  <div>
    <div class="main">
      <div style="width: 100%;display: flex;justify-content: space-between;padding-right:30px;height:30px" class="subtit"> {{ $t('route.inboundOrderProductDetail') }}
        <el-button @click="fanhui()" type="primary" size="small">返回</el-button>
      </div>
      <!-- <div class="userEdit">
        <el-button
          icon="el-icon-plus"
          size="small"
          :class="{changBg:isChange === 1}"
          @click="toAdd(),changBg(1)"
        >{{ $t('button.add') }}
        </el-button>
      </div> -->
      <div class="table">
        <el-table
          :data="tableData"
          style="min-height:460px;padding:0 16px;width:100%;overflow:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            prop="productName"
            :label="$t('inoutBound.productName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="num"
            :label="$t('inoutBound.num')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="unitName"
            :label="$t('inoutBound.unitName')"
            :show-overflow-tooltip="true"
          />
          <el-table-column
            prop="binCode"
            :label="$t('inoutBound.binName')"
            :show-overflow-tooltip="true"
          />
          <!------------------------------->
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import { getInboundOrderProduct } from '@/api/inboundOrderProduct'
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
      code: this.$route.query.code,
      // -----------------
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 250,
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
      this.$router.push({ path: 'addInboundOrderProductDetail', query: { inboundProductId: this.$route.query.id, code: this.code }})
    },
    changBg(index) {
      this.isChange = index
    },
    page() {
      getInboundOrderProduct(this.$route.query.id).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.detailDtoList
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
        deleteInboundOrderProductDetail(rows[index].id).then(res => {
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
      this.$router.push({ path: 'editInboundOrderProductDetail', query: { id: id, inboundProductId: this.$route.query.id, code: this.code }})
    },
    search() {
      const data = {
        product: {
          id: this.searchForm.productId
        },
        bin: {
          id: this.searchForm.binId
        },
        inboundOrderProduct: {
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
      pageInboundOrderProductDetail(data)
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
    },
    fanhui(){
      this.$router.push("/inboundOrderProduct");
    }
  }
}
</script>

<style lang='scss' scoped>
  @import "~@/styles/variables.scss";
  @import "~@/styles/systemStyle/car.scss";
</style>
