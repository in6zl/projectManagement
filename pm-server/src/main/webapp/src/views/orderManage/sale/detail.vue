<template>
  <div class="main">
    <div class="detail">
      <div class="detailLeft">
        <div>
          <img src="@/assets/images/saleOrder.png" alt />
          订单号：{{saleOrder.orderNo}}
        </div>
        <ul>
          <li>
            <div>
              <span>签单人：</span>
              {{saleOrder.seller}}
            </div>
            <div>
              <span>期望到货时间：</span>
              {{saleOrder.expectDate}}
            </div>
          </li>
          <li>
            <div>
              <span>联系电话：</span>
              {{saleOrder.sellerPhone}}
            </div>
            <div>
              <span>签单时间：</span>
              {{saleOrder.orderTime}}
            </div>
          </li>
          <li>
            <span>出货地址：</span>
            {{saleOrder.address}}
          </li>
        </ul>
      </div>
      <div class="detailRight">
        <div class="detailRightBtn">
          <el-button size="small" type="primary" v-print="'#print'">打印</el-button>
          <el-button size="small" type="primary" @click="back">返回</el-button>
        </div>
        <div>签单金额</div>
        <div>¥ {{saleOrder.amount}}</div>
      </div>
    </div>
    <div class="detailtable">
      <div style="margin-bottom:16px" class="subtit">产品信息</div>
      <el-table
        :data="saleOrder.saleDetailsList"
        style="width: 100%"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
        <el-table-column label="成品" prop="product.name" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="num" label="数量" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="unit" label="单位" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="deliveryNum" label="已交付数量" :show-overflow-tooltip="true"></el-table-column>
        <!-- <el-table-column label="操作" fixed="right" align="right">
          <template slot-scope="scope">
            <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
          </template>
        </el-table-column>-->
      </el-table>
    </div>
    <!-- 打印单 -->
    <div id="print">
      <div>销售单</div>
      <div class="subtit">订单号:{{saleOrder.orderNo}}</div>
      <div id="qrcode" ref="qrcode"></div>
      <div class="prtTable">
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <th>成品名称</th>
            <th>数量</th>
            <th>单位</th>
          </tr>
          <tr v-for="item in saleOrder.saleDetailsList" :key="item">
            <td>{{item.product.name}}</td>
            <td>{{item.num}}</td>
            <td>{{item.unit}}</td>
          </tr>
        </table>
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td>签单人</td>
            <td>{{saleOrder.seller}}</td>
            <td>签单金额</td>
            <td style="font-weight:bold">￥{{saleOrder.amount}}</td>
            <td>联系方式</td>
            <td>18626399721</td>
          </tr>
          <tr>
            <td>签单时间</td>
            <td>{{saleOrder.orderTime}}</td>
            <td>期望到货时间</td>
            <td>{{saleOrder.expectDate}}</td>
            <td>出货地址</td>
            <td>{{saleOrder.address}}</td>
          </tr>
        </table>
      </div>
      <div class="ordState">
        <el-form>
          <el-form-item label="订单状态">
            <el-checkbox-group v-model="chechkedItem " style="float:left">
              <el-checkbox :lable="checkedData[0].id">{{checkedData[0].name}}</el-checkbox>
            </el-checkbox-group>
            <el-checkbox-group v-model="chechkedItem1" style="float:left;margin-left:16px">
              <el-checkbox :lable="checkedData[1].id">{{checkedData[1].name}}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { find } from "@/api/sellOrder";
import { deleteSellOrder } from "@/api/sellOrder";
import QRCode from "qrcodejs2";
export default {
  components: {
    QRCode,
  },
  data() {
    return {
      printObj: {
        id: "print",
        extraCss: "https://www.google.com,https://www.google.com",
        extraHead: '<meta http-equiv="Content-Language"content="zh-cn"/>',
      },
      checkedData: [
        {
          id: 0,
          name: "未完成",
        },
        {
          id: 1,
          name: "已完成",
        },
      ],
      chechkedItem: false,
      chechkedItem1: false,
      tableHeight: window.innerHeight - 415,
      saleOrder: {},
    };
  },
  methods: {
    back() {
      this.$router.push("/sale");
    },
    qrcode() {
      //这里是调用的方法
      let id = this.saleOrder.id;
      let qrcode = new QRCode("qrcode", {
        width: 80, // 设置宽度
        height: 80, // 设置高度
        text: id,
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    addpro() {
      let id = this.saleOrder.id;
      this.$router.push({ path: "/editSale", query: { id: id, step: 1 } });
    },
    edit() {
      let id = this.saleOrder.id;
      this.$router.push({ path: "/editSale", query: { id: id } });
    },
    deleteSale() {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let id = this.saleOrder.id;
          deleteSellOrder(id).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "删除成功",
                type: "success",
              });
              this.$router.push("/sale");
            }
          });
        })
    },
  },

  created() {
    let id = this.$route.query.id;
    find(id)
      .then((res) => {
        this.saleOrder = res.data;
        this.saleOrder.expectDate = this.saleOrder.expectDate.slice(0, 11);
      })
      .then(() => {
        this.saleOrder.state == 0
          ? (this.chechkedItem = true)
          : (this.chechkedItem1 = true);
        this.qrcode();
      });
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/systemStyle/saleDetail.scss";
#print {
  text-align: center;
  width: 100%;
  background: white;
  padding: 16px;
  position: relative;
  z-index: -10;
}
#qrcode {
  position: absolute;
  top: 30px;
  right: 80px;
}
#print > div:nth-child(1) {
  color: #333333;
  font-size: 20px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  margin: 32px 0 8px 0;
}
#print > div:nth-child(2) {
  color: #666666;
  font-size: 14px;
  margin-bottom: 50px;
}
.ordState {
  text-align: left;
}
.prtTable {
  text-align: left;
  font-size: 18px;
}
table {
  color: rgba(51, 51, 51, 1);
  background: #eeeeee;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  margin-top: 16px;
  border-collapse: collapse;
}
.prtTable > table:nth-of-type(1) th,
.prtTable > table:nth-of-type(1) td {
  width: 33.33%;
}
table td {
  width: 16.66%;
  // height: 44px;
  background: #fff;
  border-left: 1px solid #333333;
  border-top: 1px solid #333333;
  padding-left: 16px;
  word-break: break-word;
  padding: 5px 16px;
}
table th {
  // height: 44px;
  padding-left: 16px;
  border-left: 1px solid #333333;
  border-top: 1px solid #333333;
  padding: 5px 16px;
}
.main {
  height: calc(100vh - 130px);
  overflow: hidden;
}
.detail {
  padding-top: 10px;
}
</style>
