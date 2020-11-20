<template>
  <div class="box">
    <div class="main">
      <!--父表单-->
      <div style="overflow:hidden;background:white;width:100%;padding:16px">
        <div>{{ $t('route.addInboundOrderProduct') }}</div>
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px">
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.code')" prop="code" size="small">
              <el-input v-model="ruleForm.code" maxlength="30" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="内仓名" prop="wareHouse" size="small">
              <el-select
                v-model="ruleForm.warehouse"
                filterable
                placeholder="请选择"
                style="width: 100%"
                value-key="id"
                @change="selectWarhouse"
                disabled
              >
                <el-option
                  v-for="item in warehouseList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.inOperator')" prop="inOperator" size="small">
              <el-input v-model="ruleForm.inOperator" maxlength="30" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.deliveryMan')" prop="deliveryMan" size="small">
              <el-input v-model="ruleForm.deliveryMan" maxlength="30" style="width: 100%" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item :label="$t('inoutBound.inTime')" prop="inTime" size="small">
              <el-date-picker
                v-model="ruleForm.inTime"
                type="date"
                format="yyyy 年 MM 月 dd 日"
                value-format="yyyy-MM-dd HH:mm:ss"
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-form>
      </div>

      <!--子列表-->
      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">新增详情</div>
          <!--添加按钮-->
          <div class="addChild" >
            <el-button
            icon="el-icon-plus"
            size="small"
            :class="{changBg:isChange === 1}"
            @click="addDetail"
            >新增详情</el-button>
            <!-- <el-button
              icon="el-icon-plus"
              size="small"
              :class="{changBg:isChange === 1}"
              @click="suggest"
            >库位推荐</el-button> -->
          </div>
        </div>
       
        <el-table
          :data="ruleForm.detailList"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')" />
          <el-table-column
            :label="$t('inoutBound.productName')"
            width="150"
            :show-overflow-tooltip="true"
          >
            <template slot-scope="scope" >
              <el-select v-model="scope.row.product" style="width: 130px" value-key="id">
                <el-option
                  v-for="item in productList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
                  :disabled="validateProductMap(item.id)"
                />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column prop="num" label="数量/剩余分配数量" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope"> <el-input type="text" v-model="scope.row.num" style="width: 80px"  /> / {{countLeftNum(scope.row)}}</template>
          </el-table-column>
          <el-table-column
            :label="$t('inoutBound.unitName')"
            width="150"
            :show-overflow-tooltip="true"
          ><template slot-scope="scope">
                {{scope.row.product.unitName}}
           </template>
          </el-table-column>
          <el-table-column label="期望推荐库位数" width="150" :show-overflow-tooltip="true">
            <template slot-scope="scope">
              <el-input
                type="text"
                size="mini"
                style="width:100px"
                v-model="scope.row.storageBinNum"
              />
            </template>
          </el-table-column>
          <el-table-column
            prop="avgNum"
            label="平均每个库位分配数量"
            width="200"
            :show-overflow-tooltip="true"
          >
            <template
              slot-scope="scope"
            >{{scope.row.storageBinNum?scope.row.num/scope.row.storageBinNum:0}}</template>
          </el-table-column>
          <el-table-column
            label="库位(成品-现有数量) / 入库数量 / 批次 "
            width="700"
            :show-overflow-tooltip="true"
          >
            <template slot-scope="scope">
              <tr v-for="storageBin  in scope.row.storageBins" :key="storageBin.id">
                <td>
                  <el-select
                    v-model="storageBin.id"
                    style="width: 200px"
                    @visible-change="sortStorageBins($event,scope.row.product)"
                    @change="updateStorageBinMap()"
                  >
                    <el-option
                      v-for="item in storageBinList"
                      :key="item.id"
                      :style="item.color"
                      :label="`${(item.code)} (${(item.productName?item.productName+'-':'')}${(item.num?item.num:0)})`"
                      :value="item.id"
                      :disabled="item.disable"
                    />
                  </el-select>
                </td>
                <td>
                  <el-input type="text" v-model="storageBin.inboundNum" />
                </td>
                <td>
                  <el-input type="text" v-model="storageBin.batch" />
                </td>
                <td>
                  <el-tooltip content="删除" placement="top">
                    <svg-icon
                      icon-class="delete"
                      @click.native="deleteStorageBin(storageBin, scope.row.storageBins)"
                    />
                  </el-tooltip>
                </td>
              </tr>
            </template>
          </el-table-column>
         
          <el-table-column
            prop="operation"
            :label="$t('table.operation')"
            fixed="right"
            align="right"
          >
            <template slot-scope="scope">
              <el-tooltip content="添加" placement="top">
                <svg-icon icon-class="add" @click.native="addStorageBin(scope.$index)" />
              </el-tooltip>
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index)" />
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!--提交按钮-->
    <div class="submit-button">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')">确定</el-button>
    </div>
  </div>
</template>

<script>
import {
  updateInboundOrderProduct,
  findSurplusNumGroupByProduct,
  getInboundOrderProduct
} from "@/api/inboundOrderProduct";
import {  findInboundList } from "@/api/sendOrder";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { prolist } from "@/api/product";
import ja from "element-ui/lib/locale/lang/ja";
import lef from "@/components/common/l_icon";
import { getSaleNo } from "@/api/sellOrder";
export default {
  components: {
    lef,
  },
  data() {
    const validateNum = (rule, value, callback) => {
      if (!/^[-]{0,1}[0-9]\d*$/.test(value)) {
        return callback(new Error("请输入数字"));
      } else {
        if (value.length > 10) {
          return callback(new Error("长度在1到10个字符串"));
        } else {
          if (parseFloat(value) <= 0) {
            return callback(new Error("入库数量必须为正数"));
          }
          return callback();
        }
      }
    };
    return {
      resetVal: null,
      // --------布局------------
      isUpdate: false,
      tableDataIndex: "",
      dialogFormVisible: false,
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 420,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        code:"",
        invoiceId: undefined,
        warehouse: undefined,
        inOperator: undefined,
        deliveryMan: undefined,
        inTime: undefined,
        detailList:[],
        productionRecordIdList:[]
      },
      rules: {
        code: [{ required: true, message: "请输入", trigger: "blur" }],
        invoiceId: [{ required: true, message: "请输入", trigger: "blur" }],
        inOperator: [{ required: true, message: "请输入", trigger: "blur" }],
        deliveryMan: [{ required: true, message: "请输入", trigger: "blur" }],
      },
      invoiceList:[],
      //内仓列表
      warehouseList:[],
      productList:[],
      //详情中成品已被选择的Map
      productMap:{},
      storageBinList:[],
      storageBinMap:{},
    };
  },
  async mounted() {
    getSaleNo().then(res=>{
      this.ruleForm.code=res.data
    });
    await getFaWarehouseList({ type: 0 }).then((res) => {
      if (res.code === "0") {
        this.warehouseList=res.data;
      }
    });

    await prolist({}).then((res) => {
      if (res.code === "0") {
        this.productList = res.data;
      }
    });
    //初始化详情
    await getFaStorehouseList({"warehouseId":this.$route.query.warehouseId}).then(res=>{
         res.data.forEach(storageBin=>{
           storageBin.color="color:#000";
           this.ruleForm.detailList.forEach(detail=>{
             if(detail.storageBins){
               detail.storageBins.forEach(storageBinDetail=>{
                 if(storageBinDetail.id == storageBin.id){
                   storageBin.state =0;
                 }
               })
             }
           })
         })
         this.storageBinList=JSON.parse(JSON.stringify( res.data));
    })
    await getInboundOrderProduct(this.$route.query.id).then(res => {
       
      
      let inboundOrderProduct=res.data;
      let detailMap={};
      inboundOrderProduct.detailDtoList.forEach(detail=>{
        let productEntity=undefined
        this.productList.forEach(product=>{
          if(product.id == detail.productId){
            productEntity=product
          }
        })
        this.productMap[detail.productId]=productEntity;
        let bin=undefined;
        this.storageBinList.forEach(storageBin=>{
          if(storageBin.id == detail.binId){
            bin=storageBin;
            bin.inboundNum=detail.num;
          }
        })
        
        this.storageBinMap[detail.binId]= bin;
        if(!detailMap[detail.productId]){
          detailMap[detail.productId]={
            "product":productEntity,
            "num":parseFloat(detail.num),
            "storageBinNum":1,
            "storageBins":[]
          }
          detailMap[detail.productId].storageBins.push(bin)
        }else{
          detailMap[detail.productId].num=parseFloat(detailMap[detail.productId].num)+detail.num;
          detailMap[detail.productId].storageBins.push(bin)
        }
      })
      let detailList=[];
      for(let key in detailMap){
         detailList.push(detailMap[key]);
      }
      res.data.detailList=detailList
      this.warehouseList.forEach(warehouse=>{
        if(warehouse.id== res.data.warehouseId){
           res.data.warehouse=warehouse;
        }
      })
      this.ruleForm = JSON.parse(JSON.stringify(res.data));
      
      console.log('this.ruleForm',this.ruleForm);
    });
  },
  methods: {
    
    //选择内仓
    async selectWarhouse(){
      console.log('this.ruleForm.warehouse',this.ruleForm.warehouse);
      //获取库位列表
      await getFaStorehouseList({"warehouseId":this.ruleForm.warehouse.id}).then(res=>{
         res.data.forEach(storageBin=>{
           storageBin.color="color:#000";
           this.ruleForm.detailList.forEach(detail=>{
             if(detail.storageBins){
               detail.storageBins.forEach(storageBinDetail=>{
                 if(storageBinDetail.id == storageBin.id){
                   storageBin.state =0;
                 }
               })
             }
           })
         })
         this.storageBinList=JSON.parse(JSON.stringify( res.data));
      })
      // this.ruleForm.detailList=[];
      // this.productMap={};
    },
    suggest(){
      if(!this.ruleForm.warehouse){
        this.$message({
          message: "内仓不能为空",
          type: "warning",
        });
        return;
      }
      this.storageBinMap={};
      this.ruleForm.detailList.forEach(detail=>{
        detail.storageBins=[];
        this.distributionBin(detail);
      })
    },
    //库位分配
    distributionBin(detail){
      if(!detail.product){
        this.$message({
          message: "成品不能为空",
          type: "success",
        });
        return;
      }
      if(typeof(detail.num) =="undefined" ){
        this.$message({
          message: "成品数量不能为空",
          type: "success",
        });
        return;
      }
      if(!detail.storageBinNum || !/^[-]{0,1}[0-9]\d*$/.test(detail.storageBinNum) ){
        this.$message({
          message: "请填入合适的期望推荐库位数",
          type: "success",
        });
        return;
      }
      for(let i=0;i<this.storageBinList.length;i++){
        let storageBin=this.storageBinList[i];
        //对于实际分配数量小于需要库位数量且具有相同标签/无标签且未被占用(其他入库单未完成导致的占用/本次入库单其他成品导致的占用)时，则该库位予以推荐
        if(detail.storageBinNum == detail.storageBins.length){
             break;
        }
         if( !this.storageBinMap[storageBin.id] && storageBin.state !=2 ){
            if(storageBin.productId == null || (detail.product.id == storageBin.productId) || (detail.product.id != storageBin.productId && storageBin.num ==0)){
              if(!storageBin.faStorehouseTag || storageBin.faStorehouseTag.length ==0){
                detail.storageBins.push(storageBin);
                this.storageBinMap[storageBin.id]=storageBin;
              }else{
                if(detail.product.tags && detail.product.tags.length>0){
                   detail.product.tags.forEach(productTag=>{
                    storageBin.faStorehouseTag.forEach(binTag=>{
                      if(productTag.id == binTag.id){
                          detail.storageBins.push(storageBin);
                          this.storageBinMap[storageBin.id]=storageBin;
                      }
                    })
                  })
                }
              }
            }
         }
      }
      //平摊成品数量
      if(detail.storageBins.length >0){
        let inboundNum=Math.floor(detail.num / detail.storageBins.length);
        for(let i=0;i<detail.storageBins.length;i++){
          if(i+1==detail.storageBins.length){
            detail.storageBins[i].inboundNum=inboundNum+detail.num % detail.storageBins.length;
          }else{
            detail.storageBins[i].inboundNum=inboundNum;
          }
        }
      }
      console.log('detail',detail);
      return detail;
    },
    //计算剩余未分配数量
    countLeftNum(detail){
      let num=parseFloat(detail.num);
       if(detail.storageBins && detail.storageBins.length>0){
          detail.storageBins.forEach(storageBin=>{
            num=num-parseFloat(storageBin.inboundNum?storageBin.inboundNum:0);
          })
       }
       return num;
    },
    //添加详情
    addDetail(){
      let product=undefined;
      for(let i=0;i<this.productList.length;i++){
        if(!this.productMap[this.productList[i].id]){
          product=this.productList[i];
          break;
        }
      }
      if(typeof(product) == "undefined"){
        this.$message({
          message: "已无成品可添加",
          type: "success",
        });
        return;
      }
      let detail={
        "product":product,
        "num":0,
        "storageBinNum":1,
        "storageBins":[]
      }
      this.productMap[product.id]=product;
      this.ruleForm.detailList.push(detail)
    },
    //验证成品是否被选过
    validateProductMap(productId){
      return this.productMap[productId]?true:false;
    },
    addStorageBin(index){
      this.ruleForm.detailList[index].storageBinNum=parseFloat(this.ruleForm.detailList[index].storageBinNum) +1;
      this.ruleForm.detailList[index]=this.distributionBin(this.ruleForm.detailList[index]);
    },
    deleteStorageBin(storageBin, storageBins){
      for(let i=0;i<storageBins.length;i++){
        if(storageBins[i].id == storageBin.id){
          storageBins.splice(i,1);
          this.storageBinMap[storageBin.id]=undefined;
        }
      }
       
    },
    deleteRow(index){
      this.productMap[this.ruleForm.detailList[index].product.id]=undefined;
      
      if(this.ruleForm.detailList[index].storageBins && this.ruleForm.detailList[index].storageBins.length>0){
        this.ruleForm.detailList[index].storageBins.forEach(storageBin=>{
           this.storageBinMap[storageBin.id]=undefined;
        })
      }
      this.ruleForm.detailList.splice(index,1);
    },

    sortStorageBins(callback,product){
      //颜色定义   #绿色--推荐(标签匹配)  #淡黄--缺省(库位不存在标签)  #橙色--(标签不匹配)  #红色--已被其他成品占用   #灰色--已被其他成品入库单占用  #蓝色--被本成品入库单其他占用
      //下拉选择框时触发
      if(callback){
         //颜色赋值
         this.storageBinList.forEach(storageBin=>{
           if(storageBin.state == 2){
             //灰色
             storageBin.color="color:#ccc";
             storageBin.disable=true;
           }else{
             if(this.storageBinMap[storageBin.id]){
               //蓝色
               storageBin.color="color:#00f";
               storageBin.disable=true;
             }else{
               if(storageBin.productId && storageBin.num && storageBin.productId != product.id){
                 //红色
                 storageBin.color="color:#f00";
                  storageBin.disable=true;
               }else{
                 if(!storageBin.faStorehouseTag || storageBin.faStorehouseTag.length==0){
                   //淡黄
                    storageBin.color="color:#990";
                     storageBin.disable=false;
                 }else{
                    let hasTag=false;
                    storageBin.faStorehouseTag.forEach(faStorehouseTag=>{
                      if(product.tags){
                         product.tags.forEach(tag=>{
                           if(faStorehouseTag.id==tag.id){
                              hasTag=true;
                           }
                         })
                      }
                    })
                    if(hasTag){
                      //绿色
                      storageBin.color="color:#0f0";
                      storageBin.disable=false;
                    }else{
                      //橙色
                      storageBin.color="color:#f90";
                      storageBin.disable=false;
                    }
                 }
               }
             }
           }
         })
         //分组
         let storageBinMap={}
         this.storageBinList.forEach(storageBin=>{
           if(!storageBinMap[storageBin.color]){
             storageBinMap[storageBin.color]=new Array();
           }
           storageBinMap[storageBin.color].push(storageBin);
         })
         //排序
         this.storageBinList=[...(storageBinMap['color:#0f0']?storageBinMap['color:#0f0']:new Array()),
         ...(storageBinMap['color:#990']?storageBinMap['color:#990']:new Array()),
         ...(storageBinMap['color:#f90']?storageBinMap['color:#f90']:new Array()),
         ...(storageBinMap['color:#f00']?storageBinMap['color:#f00']:new Array()),
         ...(storageBinMap['color:#ccc']?storageBinMap['color:#ccc']:new Array()),
         ...(storageBinMap['color:#00f']?storageBinMap['color:#00f']:new Array())]
      }
      console.log(this.storageBinList);
    },
    updateStorageBinMap(){
      this.storageBinMap={};
      this.ruleForm.detailList.forEach(detail=>{
        if(detail.storageBins && detail.storageBins.length>0){
          detail.storageBins.forEach(storageBin=>{
            this.storageBinMap[storageBin.id]=storageBin;
          })
        }
      })
    },
    toIndex() {
      this.$router.push("inboundOrderProduct");
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    submitForm(){
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          if(!this.ruleForm.detailList){
            this.$message({
                message: "必须填写详情",
                type: "warning",
              });
              return;
          }else{
            let numValidator=true;
            this.ruleForm.detailList.forEach(detail=>{
              if(detail.storageBins){
                detail.storageBins.forEach(storageBin=>{
                  if(!storageBin.inboundNum || !/^[-]{0,1}[0-9]\d*$/.test(storageBin.inboundNum) || storageBin.inboundNum<=0){
                    numValidator=false;
                  }
                })
              }
            })
            if(!numValidator){
              this.$message({
                message: "请填入合适的入库数量",
                type: "warning",
              });
              return;
            }
          }
          //转换成后端数据结构
          let data=JSON.parse(JSON.stringify(this.ruleForm));
          let detailList=[];
          data.detailList.forEach(detail=>{
            if(detail.storageBins){
              detail.storageBins.forEach(storageBin=>{
                detailList.push({'product':detail.product,'num':storageBin.inboundNum,'bin':storageBin,'batch':storageBin.batch})
              })
            }
          })
          data.detailList=detailList;
          updateInboundOrderProduct(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("inboundOrderProduct");
            } else {
              if(res.message){
                this.$message.error(res.message);
              }else{
                this.$message.error('创建失败');
              }
            }
          });
        }
        
      })
    }
  },
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.box {
  position: relative;
  height: calc(100vh - 120px);
}
.submit-button {
  width: 100%;
  display: flex;
  position: absolute;
  bottom: 0px;
  padding: 15px 0 15px 0;
  justify-content: center;
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  background: white;
}
.demo-ruleForm /deep/.el-form-item__content {
  line-height: 40px;
  position: unset;
  font-size: 14px;
}
.main {
  background: none !important;
}
.table {
  background: white;
  padding: 16px 0;
}
</style>


