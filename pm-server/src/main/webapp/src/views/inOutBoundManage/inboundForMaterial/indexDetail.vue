<template>
  <div class="main">
    <div class="detail">
      <div class="detailLeft">
        <div>
          <img src="@/assets/images/saleOrder.png" alt />
          入库单号：{{inboundOrderMaterial.id}}
        </div>
        <ul>
          <li>
            <div>
              <span>内仓：</span>
              {{inboundOrderMaterial.faWarehouse.name}}
            </div>
            <div>
              <span>入库员：</span>
              {{inboundOrderMaterial.inOperator}}
            </div>
          </li>
          <li>
            <div>
              <span>入库时间：</span>
              {{inboundOrderMaterial.inTime}}
            </div>
          </li>
        </ul>
      </div>
      <div class="detailRight">
        <div class="detailRightBtn">
          <el-button size="small" type="primary" v-print="'#print'">打印</el-button>
        </div>
      </div>
    </div>
    <div class="detailtable">
      <div style="margin-bottom:16px">入库详情</div>
      <el-table
        :data="inboundOrderMaterial.detailList"
        style="width: 100%"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column type="index" width="80" :label="$t('table.serial')"></el-table-column>
        <el-table-column width="240" label="物料" prop="material.name" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="num" label="数量" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="material.unitName" label="单位" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="bin.name" label="库位" width="135" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column label="操作" fixed="right" align="right">
          <template slot-scope="scope">
            <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 打印单 -->
    <div id="print">
      <div>入库单</div>
      <div>入库单号:{{inboundOrderMaterial.id}}</div>
      <div id="qrcode" ref="qrcode"></div>
      <div class="prtTable">
        <table cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <th style="width:10%">送货单号</th>
            <th style="width:15%">{{inboundOrderMaterial.invoice.sendOrderNo}}</th>
            <th>仓库</th>
            <th>{{inboundOrderMaterial.faWarehouse.name}}</th>
            <th>入库员</th>
            <th>{{inboundOrderMaterial.inOperator}}</th>
            <th style="width:10%">入库时间</th>
            <th style="width:15%">{{inboundOrderMaterial.inTime}}</th>
          </tr>
        </table>
        <table cellpadding="0" cellspacing="0" width="100%" id="detailTable">
          <tr>
            <th>物料编号</th>
            <th>收货总数</th>
            <th>单位</th>
            <th>库位</th>
            <th>批次</th>
            <th>数量</th>
          </tr>
          <tbody  v-for="detailGroup in inboundOrderMaterial.transformation" :key="detailGroup">
            <span v-for="detail in detailGroup.datas" :key="detail">
              <tr>
                <td class="twoRow" v-if="detail.falseId == 0" :rowspan="detailGroup.datas.length">{{detailGroup.materialCode}}</td>
                <td class="oneRow" v-if="detail.falseId == 0" :rowspan="detailGroup.datas.length">{{detailGroup.sum}}</td>
                <td class="oneRow" v-if="detail.falseId == 0" :rowspan="detailGroup.datas.length">{{detailGroup.unitName}}</td>
                <td class="twoRow" v-if="detail.falseId != 0" >{{detailGroup.materialCode}}</td>
                <td class="oneRow" v-if="detail.falseId != 0" >{{detailGroup.sum}}</td>
                <td class="oneRow" v-if="detail.falseId != 0" >{{detailGroup.unitName}}</td>
                <td class="oneRow" >{{detail.bin.name}}</td>
                <td class="twoRow" >{{detail.batch}}</td>
                <td class="oneRow" >{{detail.num}}</td>
              </tr>
            </span>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { getInboundOrderMaterial } from "@/api/inboundOrderMaterial";
import { deleteSellOrder } from "@/api/sellOrder";
import QRCode from "qrcodejs2";
export default {
  components: {
    QRCode
  },
  data() {
    return {
      printObj: {
        id: "print",
        extraCss: "https://www.google.com,https://www.google.com",
        extraHead: '<meta http-equiv="Content-Language"content="zh-cn"/>'
      },
      checkedData: [
        {
          id: 0,
          name: "未完成"
        },
        {
          id: 1,
          name: "已完成"
        }
      ],
      chechkedItem: false,
      chechkedItem1: false,
      tableHeight: window.innerHeight - 415,
      inboundOrderMaterial: {}
    };
  },
  methods: {
    qrcode() {
      //这里是调用的方法
      let id = this.inboundOrderMaterial.id;
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
    addpro() {
      let id = this.saleOrder.id;
      this.$router.push({ path: "/editSale", query: { id: id, step: 1 } });
    },
    transformation(inboundOrderMaterial){
      let collectionObj=new Object();
      inboundOrderMaterial.detailList.forEach(detail => {
        if(!collectionObj[detail.materialId]){
          collectionObj[detail.materialId]=new Object();
          collectionObj[detail.materialId]['sum']=parseFloat(detail.num);
          collectionObj[detail.materialId]['materialCode']=detail.material.code;
          collectionObj[detail.materialId]['batch']=detail.batch;
          collectionObj[detail.materialId]['unitName']=detail.unit.name;
          collectionObj[detail.materialId]['datas']=new Array();
          detail['falseId']=collectionObj[detail.materialId]['datas'].length;
          collectionObj[detail.materialId]['datas'].push(detail);
        }else{
          detail['falseId']=collectionObj[detail.materialId]['datas'].length;
          collectionObj[detail.materialId].datas.push(detail);
          collectionObj[detail.materialId]['sum']+=parseFloat(detail.num);
        }
      });
      let collectionArray=new Array();
      for(let key in collectionObj){
         collectionArray.push(collectionObj[key])
      }
      inboundOrderMaterial['transformation']=collectionArray;

      return inboundOrderMaterial;
    }
  },
  created() {
    let id = this.$route.query.id;
    getInboundOrderMaterial(id)
      .then(res => {

        this.inboundOrderMaterial = this.transformation(res.data);
        console.log('this.inboundOrderMaterial',this.inboundOrderMaterial);
      })
      .then(() => {
        this.inboundOrderMaterial.state == 0
          ? (this.chechkedItem = true)
          : (this.chechkedItem1 = true);
        this.qrcode();
      });
  }
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
  font-size: 12px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  margin: 32px 0 8px 0;
}
#print > div:nth-child(2) {
  color: #666666;
  font-size: 12px;
  margin-bottom: 50px;
}
.ordState {
  text-align: left;
}
.prtTable {
  text-align: left;
  font-size: 12px;
}
.twoRow {
  width: 25%;
}
.oneRow {
  width: 12.5%;
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
  width: 12.5%;
}
table td {
  width: 12.5%;
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
