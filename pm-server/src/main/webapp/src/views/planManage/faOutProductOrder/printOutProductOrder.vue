<template>
  <div>
    <div class="detail">
      <div class="detailLeft">
        <div>
          <img
            src="@/assets/images/saleOrder.png"
            alt
          />
          出货单号：{{outProductOrder.orderNo}}
        </div>
        <ul>
          <li>
            <div>
              <span>销售单号：</span>
              {{outProductOrder.saleOrderNo}}
            </div>
            <div>
              <span>出货人：</span>
              {{outProductOrder.outOperator}}
            </div>
          </li>
          <li>
            <div>
              <span>出货日期：</span>
              {{outProductOrder.outDate}}
            </div>
            <div>
              <span>车牌号：</span>
              {{outProductOrder.carNo}}
            </div>
          </li>
          <li>
            <div>
              <span>签收人：</span>
              {{outProductOrder.receiver}}
            </div>
            <div>
              <span>联系方式：</span>
              {{outProductOrder.receiverPhone}}
            </div>
          </li>
          <li>
            <span>出货地址：</span>
            {{outProductOrder.address}}
          </li>
        </ul>
      </div>
      <div class="detailRight">
        <div class="detailRightBtn">
          <el-button
            size="small"
            type="primary"
            v-print="'#print'"
          >打印</el-button>
          <el-button size="small" @click="fanhui">
            返回
          </el-button>
        </div>
      </div>
    </div>
    <div class="detailtable">
      <div>产品信息</div>
      <el-table
        :data="outProductOrder.outProductOrderDetailsList"
        style="width: 100%"
        :height="320"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column
          type="index"
          width="260"
          :label="$t('table.serial')"
        ></el-table-column>
        <el-table-column
          label="成品"
          prop="product.name"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="num"
          label="数量"
          :show-overflow-tooltip="true"
        ></el-table-column>
      </el-table>
    </div>
    <!-- 打印单 -->
    <div id="print">
      <div>出货单</div>
      <div>出货单号:{{outProductOrder.orderNo}}</div>
      <div
        id="qrcode"
        ref="qrcode"
      ></div>
      <div class="prtTable">
        <table
          cellpadding="0"
          cellspacing="0"
          width="100%"
        >
          <tr>
            <td>销售单号</td>
            <td>{{outProductOrder.saleOrderNo}}</td>
            <td>出货人</td>
            <td>{{outProductOrder.outOperator}}</td>
            <td>出货时间</td>
            <td>{{outProductOrder.outDate}}</td>
          </tr>
        </table>
        <table
          cellpadding="0"
          cellspacing="0"
          width="100%"
        >
          <tr>
            <th>成品名称</th>
            <th>数量</th>
          </tr>
          <tr
            v-for="item in outProductOrder.outProductOrderDetailsList"
            :key="item"
          >
            <td>{{item.product.name}}</td>
            <td>{{item.num}}</td>
          </tr>
        </table>
        <table
          cellpadding="0"
          cellspacing="0"
          width="100%"
        >
          <tr>
            <td>客户名称</td>
            <td>{{outProductOrder.receiver}}</td>
            <td>客户联系方式</td>
            <td>{{outProductOrder.receiverPhone}}</td>
            <td>出货地址</td>
            <td>{{outProductOrder.address}}</td>
          </tr>
          <tr>
            <td>车牌号</td>
            <td>{{outProductOrder.carNo}}</td>
            <td>司机名称</td>
            <td>{{outProductOrder.driverName}}</td>
            <td>司机联系方式</td>
            <td>{{outProductOrder.driverPhone}}</td>
          </tr>
        </table>
      </div>
      <div class="ordState">
        <el-form>
          <el-form-item label="订单状态">
            <el-checkbox-group v-model="type">
              <el-checkbox
                label="未完成"
                name="type"
              ></el-checkbox>
              <el-checkbox
                label="已完成"
                name="type"
              ></el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import { findFaOutProductOrder } from "@/api/faOutProductOrder";
import { listCarSchedule } from "@/api/carSchedule";
import { getCar } from "@/api/car";
import QRCode from "qrcodejs2";
import { Form } from "element-ui";
export default {
  components: {
    QRCode
  },
  data() {
    return {
      type: [],
      id: this.$route.query.id,
      outProductOrder: [],
      QRCode: ""
    };
  },
  mounted() {
    let id = this.id;
    findFaOutProductOrder(id).then(res => {
      this.outProductOrder = res.data;
      this.qrcode();
    });
  },
  methods: {
    qrcode() {
      //这里是调用的方法
      let id = this.outProductOrder.id;
      let qrcode = new QRCode("qrcode", {
        width: 80, // 设置宽度
        height: 80, // 设置高度
        text: id
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    fanhui(){
      this.$router.push("faOutProductOrder");
    }
  }
};
</script>
<style lang='scss' scoped>
.detail {
  position: relative;
  top: 0px;
  top: -15px;
  left: -1px;
  height: 166px;
  width: 100%;
  overflow-y: auto;
  background-color: white;
  & > .detailLeft {
    width: 60%;
    height: 100%;
    float: left;
    padding: 16px;
    & > div:nth-child(1) {
      font-size: 18px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
      line-height: 28px;
      & > img {
        width: 28px;
        height: 28px;
        vertical-align: middle;
        margin-right: 16px;
      }
    }
    & > ul > li {
      list-style: none;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: space-between;
      font-size: 14px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(51, 51, 51, 1);
      margin-top: 8px;
      & > div {
        display: flex;
        flex: 1;
        & > span {
          width: 120px;
          text-align: right;
        }
      }
    }
    & > ul > li:last-of-type {
      display: flex;
      justify-content: flex-start;
      word-break: break-word;
      & > span {
        width: 120px;
        text-align: right;
        flex-shrink: 0;
      }
    }
  }
  & > .detailRight {
    width: 40%;
    float: left;
    height: 100%;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    padding-right: 20px;
    & > .detailRightBtn {
      margin-top: 21px;
    }
  }
}
.detailtable {
  width: 100%;
  height: calc(100vh - 300px);
  background-color: white;
  padding: 16px;
  & > .btn {
    margin: 24px 0;
  }
}
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
}
.prtTable > table:nth-of-type(2) th,
.prtTable > table:nth-of-type(2) td {
  width: 33.33%;
}
table td {
  width: 16.66%;
  height: 44px;
  background: #fff;
  border-left: 1px solid #333333;
  border-top: 1px solid #333333;
  padding-left: 16px;
  word-break: break-word;
}
table th {
  height: 44px;
  padding-left: 16px;
  border-left: 1px solid #333333;
  border-top: 1px solid #333333;
}
</style>