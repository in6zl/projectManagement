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
          <div style="padding-top:10px;font-size:20px;font-weight:700;margin:0 auto;">出库单</div>
          <div style="padding-top:20px;font-size:14px;font-weight:500;margin:0 auto;">出库单号：{{tableData.id}}</div>
          <div style="padding-top:35px;font-size:14px;font-weight:500;margin:0 auto;">
            <input type="checkbox" > 成品出库 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input type="checkbox" checked> 原料出库
          </div>
        </div>
        <div style="width:100px;height:100px;float:left" class="qrcode" ref="qrCodeUrl"></div>
      </div>
      <div style="width:100%;padding:10px 0px;">
        <table id="sendOrder" style="width:100%;border-collapse: collapse;border:1px solid rgb(59, 43, 43);font-size: 16px">
          <tr style="height:50px;border:1px solid #000;font-size:14px">
            <!-- <td style=" border:1px solid #000;text-align: center;width:12.5%">送货单号</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">20200604001</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">供应商</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%">苏州市嘉禾食品</td> -->
            <td style=" border:1px solid #000;text-align: center;width:25%">仓库</td>
            <td style=" border:1px solid #000;text-align: center;width:25%">{{tableData.warehouseName}}</td>
            <td style=" border:1px solid #000;text-align: center;width:25%">出库日期</td>
            <td style=" border:1px solid #000;text-align: center;width:25%">{{tableData.outTime}}</td>
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
            <!-- <td style=" border:1px solid #000;text-align: center;width:12.5%;">收货人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;"></td> -->
            <td style=" border:1px solid #000;text-align: center;width:25%;">出库人</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;">{{tableData.outOperator}}</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;">制单人</td>
            <td style=" border:1px solid #000;text-align: center;width:25%;">{{tableData.operator}}</td>
            <!-- <td style=" border:1px solid #000;text-align: center;width:12.5%;">录入人</td>
            <td style=" border:1px solid #000;text-align: center;width:12.5%;">{{tableData.creator}}</td> -->
          </tr>
        </table>
      </div>


      <!-- <el-table :data="tableData" :show-header="false" :span-method="cellMerge"> 
        <el-table-column  prop="field1"></el-table-column>
        <el-table-column  prop="value1"></el-table-column>
        <el-table-column  prop="field2"></el-table-column>
        <el-table-column  prop="value2"></el-table-column>
      </el-table>
      <div >
        <div style="margin:10px;float:left;font-size:25px">原 料 出 库 单 详 情</div>
      </div>
      <el-table :data="tableDataDetail"  > 
        <el-table-column  prop="id" label="id"></el-table-column>
        <el-table-column  prop="binName" label="库位名称"></el-table-column>
        <el-table-column  prop="materialName" label="物料名称"></el-table-column>
        <el-table-column  prop="num" label="物料数量"></el-table-column>
        <el-table-column  prop="unitName" label="物料单位"></el-table-column>
      </el-table> -->
      <div style="width:100%;padding:10px 0px;"  id="isFinished">
           
      </div>
    </div >
     
    <div style="padding:10px">
      <el-button style="float:right;" @click="print"  type="primary">打印</el-button>
    </div>
  </div>
  <!-- <el-form
      ref="tableData"
      :model="tableData"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('inoutBound.materialRequirePlanId')" prop="materialRequirePlanName" size="small">
        <el-select v-model="ruleForm.materialRequirePlanId" filterable placeholder="请选择" value="">
          <el-option
            v-for="item in materialRequirePlanList"
            :key="item.id"
            :label="item.businessId"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.outOperator')" prop="outOperator" size="small">
        <el-input v-model="ruleForm.outOperator" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.receiver')" prop="receiver" size="small">
        <el-input v-model="ruleForm.receiver" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.outTime')" prop="outTime" size="small">
        <el-date-picker
          v-model="ruleForm.outTime"
          type="date"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd HH:mm:ss"
        />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.isFinished')" prop="isFinished" size="small">
        <el-select v-model="ruleForm.isFinished" filterable placeholder="请选择" value="">
          <el-option
            v-for="item in isFinishedList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          size="small"
          @click="resetForm('ruleForm')"
        >取消</el-button>
        <el-button
          size="small"
          type="primary"
          @click="submitForm('ruleForm')"
        >确定</el-button>
      </el-form-item>
    </el-form> -->
</template>

<script>
import { btn } from '@/components/minxs/button'
import {  getOutboundOrderMaterial } from '@/api/outboundOrderMaterial'
import {  listOutboundOrderMaterialDetail } from '@/api/outboundOrderMaterialDetail'
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
        operator:""
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
    getOutboundOrderMaterial(id).then(res => {
      this.tableData=res.data;
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
      document.getElementById("isFinished").innerHTML=isFinished;
      console.log('res.data',res.data);
      // for(let key in res.data){
      //    if(key=='id'){
      //      if(this.tableData[0]){
      //        this.tableData[0].field1='id';
      //        this.tableData[0].value1=res.data[key];
      //      }else{
      //        let obj=new Object();
      //        obj.field1='id';
      //        obj.value1=res.data[key];
      //        this.tableData[0]=obj;
      //      }
      //    }
      //    if(key=='materialRequirePlanId'){
      //      if(this.tableData[0]){
      //        this.tableData[0].field2='物料需求计划id';
      //        this.tableData[0].value2=res.data[key];
      //      }else{
      //        let obj=new Object();
      //        obj.field2='物料需求计划id';
      //        obj.value2=res.data[key];
      //        this.tableData[0]=obj;
      //      }
      //    }
      //    if(key=='outOperator'){
      //      if(this.tableData[1]){
      //        this.tableData[1].field1='出库人';
      //        this.tableData[1].value1=res.data[key];
      //      }else{
      //        let obj=new Object();
      //        obj.field1='出库人';
      //        obj.value1=res.data[key];
      //        this.tableData[1]=obj;
      //      }
      //    }
      //    if(key=='receiver'){
      //      if(this.tableData[1]){
      //        this.tableData[1].field2='收货人';
      //        this.tableData[1].value2=res.data[key];
      //      }else{
      //        let obj=new Object();
      //        obj.field2='收货人';
      //        obj.value2=res.data[key];
      //        this.tableData[1]=obj;
      //      }
      //    }
      //    if(key=='outTime'){
      //      if(this.tableData[2]){
      //        this.tableData[2].field1='出库时间';
      //        this.tableData[2].value1=res.data[key];
      //      }else{
      //        let obj=new Object();
      //        obj.field1='出库时间';
      //        obj.value1=res.data[key];
      //        this.tableData[2]=obj;
      //      }
      //    }
      //    if(key=='isFinished'){
      //      if(this.tableData[2]){
      //        this.tableData[2].field2='是否完成';
      //        this.tableData[2].value2=res.data[key]?'完成':'未完成';
      //      }else{
      //        let obj=new Object();
      //        obj.field2='收货人';
      //        obj.value2=res.data[key]?'完成':'未完成';
      //        this.tableData[2]=obj;
      //      }
      //    }
      // }
      // this.tableData.push({'field1':'原料入库单','value1':'原料入库单','field2':'原料入库单','value2':'原料入库单'});
      // console.log('this.tableData',this.tableData);
      // this.tableData = res.data
    })
    this.creatQrCode(id);
    listOutboundOrderMaterialDetail({'outboundOrderMaterial':{'id':id}}).then(res =>{
      // this.tableDataDetail=res.data;
      console.log('this.tableDataDetail',this.tableDataDetail);
      //  console.log('innerHTML',document.getElementById("materialOutboundOrderDetail").innerHTML);
      if(res.data&&res.data.length>0){
         let obj=new Object();
         for(let i=0;i<res.data.length;i++){
             if(obj[res.data[i].materialId]){
                obj[res.data[i].materialId].num+=res.data[i].num;
                obj[res.data[i].materialId].dataList.push(res.data[i]);
             }else{
               obj[res.data[i].materialId]=new Object();
               obj[res.data[i].materialId].materialId=res.data[i].materialId;
               obj[res.data[i].materialId].materialCode=res.data[i].materialCode;
               obj[res.data[i].materialId].num=res.data[i].num;
               obj[res.data[i].materialId].unitName=res.data[i].unitName;
               obj[res.data[i].materialId].dataList=new Array();
               obj[res.data[i].materialId].dataList.push(res.data[i]);
             }
             
         }
         console.log(obj);
         let materialOutboundOrderDetailInnerHTML="<tr style=\"background-color:#ccc; -webkit-print-color-adjust: exact;height:50px\">"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700;\" >物料编号</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">单位</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">出库数量</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">库位</td>"+
            "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">实出数量</td>"+
          "</tr>";
         for(let key in obj){
           let detailList=obj[key];
           materialOutboundOrderDetailInnerHTML+="<tr style=\"height:50px\">"+
            "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\""+detailList.dataList.length+"\">"+detailList.materialCode+"</td>"+
            "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\""+detailList.dataList.length+"\">"+detailList.unitName+"</td>"+
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
      
      // document.getElementById("materialOutboundOrderDetail").innerHTML="<tr style=\"background-color:#ccc; -webkit-print-color-adjust: exact;height:50px\">"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700;\" >物料编号</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">单位</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:25%;font-weight:700\">出库数量</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">库位</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;font-weight:700\">实出数量</td>"+
      //     "</tr>"+
      //      "<tr style=\"height:50px\">"+
      //       "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\"2\">9000888</td>"+
      //       "<td  style=\" border:1px solid #000;text-align: center;width:25%;\" rowspan=\"2\">公斤</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:25%;\" class=\"doubleTdWidth\"  rowspan=\"2\">201</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">A11</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">100</td>"+
      //     "</tr>"+
      //     "<tr style=\"height:50px\">"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">A22</td>"+
      //       "<td style=\" border:1px solid #000;text-align: center;width:12.5%;\">100</td>"+
      //     "</tr>";
      
    })
  },
  methods: {
    cellMerge({ row, column, rowIndex, columnIndex }) {
        //  console.log("row",row);
        //  console.log("column",column);
        //  console.log("rowIndex",rowIndex);
        //  console.log("columnIndex",columnIndex);
    //      if (rowIndex==0&&columnIndex == 0) {
    //             return {
    //                  rowspan: 1,
    //                  colspan: 4
    //             }
    //      }else if(rowIndex==0&&columnIndex != 0){
    //           return {
    //                  rowspan: 0,
    //                  colspan: 0
    //            }
    //      }
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
      if(!iframe){  
              var el = document.getElementById("printcontent");
              iframe = document.createElement('IFRAME');
              var doc = null;
              iframe.setAttribute("id", "print-iframe");
              iframe.setAttribute('style', 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;');
              document.body.appendChild(iframe);
              doc = iframe.contentWindow.document;
              //这里可以自定义样式
              //doc.write("<LINK rel="stylesheet" type="text/css" href="css/print.css">");
              doc.write('<div>' + el.innerHTML + '</div>');
              //  doc.write(document.getElementsByTagName('html')[0].innerHTML);
              doc.close();
              iframe.contentWindow.focus();            
      }
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
