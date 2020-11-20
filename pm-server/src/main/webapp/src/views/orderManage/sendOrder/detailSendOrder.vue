<template>
  <div>
    <div class="mian">
      <div class="deliveryCode">
        <svg-icon icon-class="transferCode"></svg-icon>
        <ul>
          <li>
            <span>
              <span>送货单号：</span>
              {{sendOrder.sendOrderNo}}
            </span>
          </li>
          <li>
            <span>
              <span>车牌号：</span>
              {{sendOrder.carNo}}
            </span>
            <span>
              <span>司机姓名：</span>
              {{sendOrder.driverName}}
            </span>
          </li>
          <li>
            <span>
              <span>送货日期：</span>
              {{sendOrder.sendDate}}
            </span>
            <span>
              <span>司机电话：</span>
              {{sendOrder.driverPhone}}
            </span>
          </li>
          <li>
            <span>
              <span>送货地点：</span>
              {{sendOrder.sendAddress}}
            </span>
            <span>
              <span>备注：</span>
              {{sendOrder.note}}
            </span>
          </li>
        </ul>
      </div>
      <div>
        <el-button size="small" @click="changBg(2)" type="primary" v-print="'#print'">打印</el-button>
        <el-button size="small" @click="fanhui" type="primary">返回</el-button>
      </div>
    </div>
    <div class="deliveryTable">
      <div>
        <el-tabs
          v-model="editableTabsValue"
          type="card"
          style="margin-top:15px"
        >
          <el-tab-pane
            :key="invoiceDeliveryInfo.id"
            v-for="invoiceDeliveryInfo in sendOrder.invoiceDeliveryInfos"
            :label="invoiceDeliveryInfo.warehouse.name"
            :name="invoiceDeliveryInfo.warehouse.name"
          >
            <el-table
              :data="invoiceDeliveryInfo.invoiceDeliveryInfoDetails"
              style="width: 100%"
              :height="tableHeight"
            >
              <el-table-column label="物料号" prop="material.code" fixed="left"></el-table-column>
              <el-table-column label="物料名" prop="material.name" fixed="left"></el-table-column>
              <el-table-column prop="num" label="数量" :show-overflow-tooltip="true"></el-table-column>
              <el-table-column prop="receiveNum" label="实收" :show-overflow-tooltip="true"></el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </div>
    </div>

    <!-- 打印单 -->
    <div id="print">
      <div>送货单</div>
      <div>送货单号:{{sendOrder.sendOrderNo}}</div>
      <div id="qrcode" ref="qrcode"></div>
      <div class="prtTable">
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td>车牌号</td>
            <td class="normalFontSize">{{sendOrder.carNo}}</td>
            <td>送货类型</td>
            <td class="normalFontSize">{{sendOrder.sendType}}</td>
          </tr>
          <tr>
            <td>司机姓名</td>
            <td class="normalFontSize">{{sendOrder.driverName}}</td>
            <td>司机联系方式</td>
            <td class="normalFontSize">{{sendOrder.driverPhone}}</td>
          </tr>
          <tr>
            <td>送货时间</td>
            <td class="normalFontSize">{{sendOrder.sendDate}}</td>
            <td>送货地址</td>
            <td class="normalFontSize">{{sendOrder.sendAddress}}</td>
          </tr>
          <tr>
            <td>期望送达时间</td>
            <td class="normalFontSize">{{sendOrder.expectArriveTime}}</td>
            <td>备注</td>
            <td class="normalFontSize">{{sendOrder.note}}</td>
          </tr>
        </table>
      </div>
      <div
        v-for="invoiceDeliveryInfo in sendOrder.invoiceDeliveryInfos"
        :key="invoiceDeliveryInfo"
      >
        <div style="float:left;margin-bottom:10px">{{invoiceDeliveryInfo.warehouse.name}}</div>
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td>物料编号</td>
            <td>物料名称</td>
            <td>数量</td>
            <td>单位</td>
          </tr>
          <tbody
            v-for="supplierSendOrderDetail in invoiceDeliveryInfo.invoiceDeliveryInfoDetails"
            :key="supplierSendOrderDetail"
          >
            <tr>
              <td>{{supplierSendOrderDetail.material.code}}</td>
              <td>{{supplierSendOrderDetail.material.name}}</td>
              <td>{{supplierSendOrderDetail.num}}</td>
              <td>{{supplierSendOrderDetail.material.unitName}}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { findSendOrder } from "@/api/sendOrder";
import QRCode from "qrcodejs2";
export default {
  components: {
    QRCode,
  },
  data() {
    return {
      sendOrder: {},
      warehouseName: "",
      tableHeight: window.innerHeight - 320,
      tableData: [],
      editableTabsValue: "undefined",
    };
  },
  mounted() {
    let id = this.$route.query.id;
    findSendOrder(id).then((res) => {
      if (res.code === "0") {
        this.sendOrder = res.data;
        this.editableTabsValue = this.sendOrder.invoiceDeliveryInfos[0].warehouse.name;
        console.log("this.editableTabsValue", this.editableTabsValue);
        this.qrcode();
      }
    });
  },
  methods: {
    qrcode() {
      //这里是调用的方法
      let id = this.sendOrder.id;
      let qrcode = new QRCode("qrcode", {
        width: 80, // 设置宽度
        height: 80, // 设置高度
        text: id,
      });
    },
    fanhui() {
      this.$router.go(-1);
    },
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/systemStyle/delivery.scss";
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
.prtTable > table {
  text-align: left;
  font-size: 14px;
  border: 0px;
  margin-bottom: 30px;
}
.normalFontSize {
  font-weight: 200;
}
table {
  color: rgba(51, 51, 51, 1);
  background: #eeeeee;
  border-right: 1px solid #333333;
  border-bottom: 1px solid #333333;
  margin-top: 16px;
  border-collapse: collapse;
}
.prtTable > table:nth-of-type(1) tr,
.prtTable > table:nth-of-type(1) th,
.prtTable > table:nth-of-type(1) td {
  width: 25%;
  border: 0px;
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
