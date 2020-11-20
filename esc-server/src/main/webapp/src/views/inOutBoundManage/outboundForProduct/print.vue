<template >
  <!-- <table :data="tableData" style="border:1px solid #000">
    <tr>
      <td colspan="4">原料入库单</td>
    </tr>
    <tr>
      <td >原料出库计划id</td>
      <td >{{tableData.id}}</td>
      <td >物料需求计划id</td>
      <td >{{tableData.materialRequirePlanId}}</td>
    </tr>
  </table> -->
  <div >
    <div  id="printcontent">
      <div style="height:150px">
        <div style="width:calc(100% - 100px);height:150px;float:left;text-align:center">
          <div style="padding-top:10px;font-size:20px;font-weight:700;margin:0 auto;">成品出库单</div>
          <div style="padding-top:20px;font-size:14px;font-weight:500;margin:0 auto;">出库单号：{{tableData.outProductOrderNo}}</div>
<!--          <div style="padding-top:35px;font-size:14px;font-weight:500;margin:0 auto;">-->
<!--            <input type="checkbox" checked> 成品出库 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--            <input type="checkbox" > 原料出库-->
<!--          </div>-->
        </div>
        <div style="width:100px;height:100px;float:left" class="qrcode" ref="qrCodeUrl"></div>
      </div>
      <div style="width:100%;padding:10px 0px;">
        <table id="sendOrder" style="width:100%;border-collapse: collapse;border:1px solid rgb(59, 43, 43);font-size: 16px">
          <tr style="height:50px;border:1px solid #000;font-size:14px">
            <td style=" border:1px solid #000;text-align: center;width:12.5%">出货单号</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">{{tableData.outProductOrderNo}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">出货人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">{{tableData.outProductOrderOutOperator}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">仓库</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">{{tableData.warehouseName}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">出库日期</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">{{tableData.outTime}}</td>
          </tr>
        </table>
      </div>
      <div style="width:100%">
        <table id="materialOutboundOrderDetail" style="width:100%;border-collapse: collapse;border:1px solid rgb(59, 43, 43);font-size: 14px">
          <!-- <tr style="background-color:#ccc; -webkit-print-color-adjust: exact;">
            <td style=" border:1px solid #000;text-align: center;width:25%;font-weight:700;" >物料编号</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;font-weight:700">单位</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;font-weight:700">出库数量</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;font-weight:700">库位</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;font-weight:700">实出数量</td>
          </tr>
          <tr>
            <td  style=" border:1px solid #000;text-align: center;width:25%;" rowspan="2">9000888</td>
            <td  style=" border:1px solid #000;text-align: center;width:25%;" rowspan="2">公斤</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;" class="doubleTdWidth"  rowspan="2">201</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">A11</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">100</td>
          </tr>
          <tr>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">A22</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">100</td>
          </tr> -->
        </table>
      </div>

      <div style="width:100%;padding:10px 0px;">
        <table id="sendOrder1" style="width:100%;border-collapse: collapse;border:1px solid rgb(59, 43, 43);font-size: 14px">
          <tr style="height:50px;border:1px solid #000;font-size:14px">
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">收货人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">{{tableData.receiver}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">出库人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">{{tableData.outOperator}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">制单人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">{{tableData.operatorName}}</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">录入人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">{{tableData.creatorName}}</td>
          </tr>
        </table>
      </div>
<!--      <div style="width:100%;padding:10px 0px;" id="isFinished">-->
<!--            -->
<!--      </div>-->
    </div >
    <div style="padding:10px">
      <el-button style="float:right;" @click="print"  type="primary">打印</el-button>
    </div>
  </div>
</template>

<script>
import { btn } from '@/components/minxs/button'
import {  getOutboundOrderProduct } from '@/api/outboundOrderProduct'
import {  listOutboundOrderProductDetail } from '@/api/outboundOrderProductDetail'
import QRCode from 'qrcodejs2'

export default {
  mixins: [btn],
  data() {
    return {
      tableData: {
        id: "",
        isFinished: "",
        outOperator: "",
        outTime: "",
        warehouseId: "",
        warehouseName: "",
        creator:"",
        creatorName:"",
        operator:"",
        operatorName:"",
        receiver:"",
        outProductOrderNo:"",
        outProductOrderOutOperator:""
      },
      tableDataDetail: [],
      materialRequirePlanList: [
        { id: '1', businessId: '1' }
      ],
      isFinishedList: [
        { id: 0, name: '未完成' },
        { id: 1, name: '已完成' }
      ],
      rules: {
        materialRequirePlanId: [{ required: true, message: '请输入', trigger: 'blur' }],
        outOperator: [{ required: true, message: '请输入', trigger: 'blur' }],
        receiver: [{ required: true, message: '请输入', trigger: 'blur' }],
        isFinished: [{ required: true, message: '请输入', trigger: 'blur' }]
      }
    }
  },
  mounted() {
    const id = this.$route.query.id
    getOutboundOrderProduct(id).then(res => {
      this.tableData=res.data;
      console.log('res.data',res.data);

      let isFinished="";
      if(res.data.isFinished){
         isFinished="&nbsp;&nbsp;&nbsp;&nbsp;是否出库完成&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<input type=\"checkbox\" checked> 是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<input type=\"checkbox\" > 否";
      }else{
          isFinished="&nbsp;&nbsp;&nbsp;&nbsp;是否出库完成&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<input type=\"checkbox\" > 是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<input type=\"checkbox\" checked> 否";
      }
      // document.getElementById("isFinished").innerHTML=isFinished;
      
    })
    this.creatQrCode(id);
    listOutboundOrderProductDetail({'outboundOrderMaterial':{'id':id}}).then(res =>{
      console.log('this.tableDataDetail',this.tableDataDetail);
      if(res.data&&res.data.length>0){
         let obj=new Object();
         for(let i=0;i<res.data.length;i++){
             if(obj[res.data[i].productId]){
                obj[res.data[i].productId].num+=res.data[i].num;
                obj[res.data[i].productId].dataList.push(res.data[i]);
             }else{
               obj[res.data[i].productId]=new Object();
               obj[res.data[i].productId].materialId=res.data[i].productId;
               obj[res.data[i].productId].productCode=res.data[i].productCode;
               obj[res.data[i].productId].productName=res.data[i].productName;
               obj[res.data[i].productId].num=res.data[i].num;
               obj[res.data[i].productId].unit=res.data[i].unit;
               obj[res.data[i].productId].dataList=new Array();
               obj[res.data[i].productId].dataList.push(res.data[i]);
             }
             
         }
         console.log(obj);
         let materialOutboundOrderDetailInnerHTML="<tr style=\"background-color:#ccc; -webkit-print-color-adjust: exact;height:50px\">"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700;\" >成品名称</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">单位</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">出库数量</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">库位</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">实出数量</td>"+
          "</tr>";
         for(let key in obj){
           let detailList=obj[key];
           materialOutboundOrderDetailInnerHTML+="<tr style=\"height:50px\">"+
            "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\""+detailList.dataList.length+"\">"+detailList.productName+"</td>"+
            "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\""+detailList.dataList.length+"\">"+detailList.unit+"</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;\" class=\"doubleTdWidth\"  rowspan=\""+detailList.dataList.length+"\">"+detailList.num+"</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">"+detailList.dataList[0].binName+"</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">"+detailList.dataList[0].num+"</td>"+
          "</tr>";
           if(detailList.dataList.length>1){
              for(let i=1;i<detailList.dataList.length;i++){
                  materialOutboundOrderDetailInnerHTML+="<tr style=\"height:50px\">"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">"+detailList.dataList[i].binName+"</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">"+detailList.dataList[i].num+"</td>"+
          "</tr>"
              }
           }
         }
         document.getElementById("materialOutboundOrderDetail").innerHTML=materialOutboundOrderDetailInnerHTML;
      }
      
    })
  },
  methods: {
    cellMerge({ row, column, rowIndex, columnIndex }) {
    },
    creatQrCode(id) {
      var qrcode = new QRCode(this.$refs.qrCodeUrl, {
          text: id,
          width: 100,
          height: 100,
          colorDark: '#000000',
          colorLight: '#ffffff',
          correctLevel: QRCode.CorrectLevel.H
      })
    },
    print(){
      //  判断iframe是否存在，不存在则创建iframe
      var iframe=document.getElementById("print-iframe");
      if(iframe){
         iframe.remove();
      }
              iframe = document.createElement('IFRAME');
              iframe.setAttribute("id", "print-iframe");
              var el = document.getElementById("printcontent");
              var doc = null;
              iframe.setAttribute('style', 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
              document.body.appendChild(iframe);
              doc = iframe.contentWindow.document;
              //这里可以自定义样式
              doc.write('<div>' + el.innerHTML + '</div>');
              doc.close();
              iframe.contentWindow.focus();            
     
      iframe.contentWindow.print();
      if (navigator.userAgent.indexOf("MSIE") > 0){
          document.body.removeChild(iframe);
      }
      
      // this.$print(this.$refs.print)
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
#sendOrderContainer{
  
}
table{
  width:100%;
  border-collapse: collapse;
  border:1px solid rgb(59, 43, 43);
  font-size: 16px
}
table tr{
  height:50px;
  border:1px solid #000;
}
table td{
  border:1px solid #000;
  text-align: center;
  width:12.5%
}
.doubleTdWidth{
  width:25%
}
.bold{
  font-weight: 700;background-color: #eee
}
</style>
