<template>
  <div>
    <con>
    <div class="main-top">
      <div style="margin:16px">{{ $t('route.addSendOrder') }}</div>
      <!--父表单-->
      <div style="overflow:hidden">
        <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="120px">
          <search-form>
            <el-form-item
              :label="$t('supMana.sendOrder.sendOrderNo')"
              prop="code"
              slot="first"
            >
              <el-input
                v-model.number="ruleForm.code"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item
              :label="$t('table.supplier')"
              prop="supplierId"
              size="small"
              slot="second"
            >
              <el-select
                v-model="ruleForm.supplierId"
                filterable
                placeholder="请选择"
                style="width:95%"
                value
                disabled
                @change="generateSendOrderBySupplierId"
              >
                <el-option
                  v-for="item in supplierList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
            <el-form-item
              :label="$t('supMana.sendOrder.sendAddress')"
              prop="sendAddress"
              slot="three"
            >
              <el-input
                v-model="ruleForm.sendAddress"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item :label="$t('supMana.sendOrder.carNo')" prop="carNo" slot="first">
              <el-input
                v-model="ruleForm.carNo"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item label="送货类型" prop="sendType" slot="second">
              <el-select v-model="ruleForm.sendType" style="width:95%" size="small">
                <el-option
                  v-for="item in carTypeOptions"
                  :key="item.value"
                  :label="item.name"
                  :value="item.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item
              :label="$t('supMana.sendOrder.driverName')"
              prop="driverName"
              slot="three"
            >
              <el-input
                v-model="ruleForm.driverName"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
          </search-form>
          <search-form>
            <el-form-item
              :label="$t('supMana.sendOrder.driverPhone')"
              prop="driverPhone"
              slot="first"
            >
              <el-input
                v-model="ruleForm.driverPhone"
                :placeholder="$t('main.placeInsert')"
                size="small"
                style="width:95%"
                maxlength="30"
              />
            </el-form-item>
            <el-form-item :label="$t('supMana.sendOrder.sendDate')" prop="sendDate" slot="second">
              <el-date-picker
                v-model="ruleForm.sendDate"
                type="datetime"
                placeholder="选择日期时间"
                size="small"
                style="width:95%"
                format="yyyy-MM-dd HH:mm:ss"
              />
            </el-form-item>
            <el-form-item
              :label="$t('supMana.sendOrder.expectArriveTime')"
              prop="expectArriveTime"
              slot="three"
            >
              <el-date-picker
                v-model="ruleForm.expectArriveTime"
                type="datetime"
                placeholder="选择日期时间"
                size="small"
                style="width:95%"
                format="yyyy-MM-dd HH:mm:ss"
              />
            </el-form-item>
          </search-form>
          <div style="height:80px">
            <el-form-item label="备注" prop="note">
              <el-input type="textarea" v-model="ruleForm.note" size="small"></el-input>
            </el-form-item>
          </div>
        </el-form>
      </div>
    </div>
      <!--添加按钮-->
      <!-- <div class="addChild">
        <el-button
          icon="el-icon-plus"
          size="small"
          :class="{changBg:isChange === 1}"
          @click="openDialog"
        >新增详情</el-button>
      </div>-->
      <div class="roleManageTable">
        <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit" style="margin-top:15px">
          <el-tab-pane
            :key="invoiceDeliveryInfo.falseId"
            v-for="invoiceDeliveryInfo in ruleForm.invoiceDeliveryInfos"
            :label="invoiceDeliveryInfo.warehouse.name"
            :name="invoiceDeliveryInfo.warehouse.name"
          >
          <div class="addChild">
            <el-button
            icon="el-icon-plus"
            size="small"
            :class="{changBg:isChange === 1}"
            @click="addRowData(invoiceDeliveryInfo.invoiceDeliveryInfoDetails)"
            style="margin-left:20px"
            type="primary"
          >新增详情</el-button>
          </div>
            <el-table :data="invoiceDeliveryInfo.invoiceDeliveryInfoDetails" :height="tableHeight" style="width: 100%">
            <el-table-column type="index" width="100" label="序号" />
            <el-table-column
              prop="material.name"
              :label="$t('inoutBound.materialName')"
              width="150"
              :show-overflow-tooltip="true"
            >
              <template slot-scope="scope">
                <!-- <el-select
                  v-model="scope.row.material"
                  placeholder="请选择"
                  v-if="scope.row.isEdit"
                  size="mini"
                  clearable
                  value-key="id"
                  @focus="deleteRepeatMaterial(scope.$index,invoiceDeliveryInfo)"
                >-->
                <el-select
                  v-model="scope.row.material"
                  placeholder="请选择"
                  size="mini"
                  clearable
                  value-key="id"
                >
                  <el-option
                    v-for="item in materialList"
                    :key="item.id"
                    :label="item.name"
                    :value="item"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column
              prop="num"
              :label="$t('inoutBound.num')"
              width="150"
              :show-overflow-tooltip="true"
            >
              <template slot-scope="scope">
                <el-input
                  type="text"
                  size="mini"
                  style="width:100px"
                  v-model="scope.row.num"
                ></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="receiveNum" label="实收数量" width="150" :show-overflow-tooltip="true">
              <!-- <template slot-scope="scope">
                <el-input
                  type="text"
                  size="mini"
                  style="width:100px"
                  v-model="scope.row.receiveNum"
                  v-if="scope.row.isEdit"
                ></el-input>
                <span v-else>{{scope.row.receiveNum}}</span>
              </template>-->
            </el-table-column>
            <el-table-column prop="operation" fixed="right" align="right">
              <!-- <template slot="header">
                {{$t('table.operation')}}
                <svg-icon
                  icon-class="add"
                  @click.native="addRowData(invoiceDeliveryInfo.invoiceDeliveryInfoDetails)"
                />
                <svg-icon
                  icon-class="delete"
                  @click.native="deleteInvoiceDeliveryInfo(invoiceDeliveryInfo)"
                />
              </template> -->
              <template slot-scope="scope">
                <el-tooltip content="删除" placement="top">
                  <svg-icon
                    icon-class="delete"
                    @click.native="deleteRowData(scope.$index, invoiceDeliveryInfo.invoiceDeliveryInfoDetails)"
                  />
                </el-tooltip>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
       </el-tabs>
      </div>

    </con>
    <!--提交按钮-->
    <submit class="subBtn">
      <el-button size="small" @click="toIndex">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('ruleForm')">确定</el-button>
    </submit>
    <!--弹框-->
    <el-dialog title="新增详情" :visible.sync="dialogFormVisible" width="38%">
      <el-form
        ref="ruleFormInvoiceDeliveryInfo"
        :model="ruleFormInvoiceDeliveryInfo"
        :rules="rulesInvoiceDeliveryInfo"
        lable-width="100px"
      >
        <el-form-item label="内仓" prop="warehouse" size="small">
          <el-select
            v-model="ruleFormInvoiceDeliveryInfo.warehouse"
            filterable
            placeholder="请选择"
            style="width: 90%"
            value-key="id"
          >
            <el-option
              v-for="item in warehouseList"
              :key="item.id"
              :label="item.name"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button size="small" @click="closeDialog">取消</el-button>
          <el-button
            size="small"
            type="primary"
            @click="addInvoiceDeliveryInfo('ruleFormInvoiceDeliveryInfo')"
          >确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { parseTime } from "@/utils/index";
import { updateSupplier, findSendOrder,getByName } from "@/api/sendOrder";
import { getFaWarehouseList } from "@/api/faWareHouse";
import { findMaterialListData } from "@/api/material";
import { getOrganization } from "@/api/organization";
import searchForm from "@/components/commonTop/index";
import { getSupplier } from "@/api/supplier";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  components: {
    searchForm,
     top,
    submit,
    con,
  },
  data() {
    const validateWaHouse = (rule, value, callback) => {
      for (
        let i = 0;
        i < this.ruleForm.invoiceDeliveryInfos.length;
        i++
      ) {
        if (
          this.ruleForm.invoiceDeliveryInfos[i].warehouse.id ==
          value.id
        ) {
          callback(new Error("该内仓的清单已被创建"));
        }
      }
      return callback();
    };
    const validateName = (rule, value, callback) => {
      getByName({
        id:this.$route.query.id,
        sendOrderNo: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
    return {
      // --------布局------------
      editableTabsValue:undefined,
      isUpdate: false,
      tableDataIndex: "",
      dialogFormVisible: false,
      isChange: 1,
      index: 1,
      tableHeight: window.innerHeight - 420,
      tableData: [],
      // -----------父业务--------------
      ruleForm: {
        id: undefined,
        sendOrderNo: "",
        sendDate: "",
        sendAddress: "",
        sendType: "",
        expectArriveTime: "",
        note: "",
        carNo: "",
        state: "",
        carType: "",
        driverName: "",
        driverPhone: "",
        supplierId: "",
        // warehouseId: '',
        invoiceDeliveryInfos: [],
        createTime: undefined
      },
      falseIdMax: 0,
      warehouseList: [],
      materialList: [],
      supplierList: [],
      carTypeOptions: [
        {
          name: "厢式货车",
          value: 0
        },
        {
          name: "仓栅式货车",
          value: 1
        },
        {
          name: "槽罐车",
          value: 2
        }
      ],
      rules: {
        code: [
          { required: true, message: "请输入送货单号", trigger: "blur" },
          {
            validator: validateName,
            trigger: "blur",
          },
        ],
        sendAddress: {
          required: true,
          message: "请输入送货地址",
          trigger: "blur"
        },
        sendDate: {
          required: true,
          message: "请选择期望到货日期",
          trigger: "blur"
        },
        carNo: [{ required: true, message: "请输入车牌号", trigger: "blur" }],
        supplierId: [
          { required: true, message: "请输入供应商", trigger: "blur" }
        ],
        sendType: {
          required: true,
          message: "请选择送货类型",
          trigger: "blur"
        },
        driverName: {
          required: true,
          message: "请输入司机姓名",
          trigger: "blur"
        },
        driverPhone: [
          { required: true, message: "请输入司机手机", trigger: "blur" },
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change"
          }
        ],
        expectArriveTime: {
          required: true,
          message: "请选择期望到货日期",
          trigger: "blur"
        }
      },
      //------清单SupplierSendOrderDelivery----
      //清单模型
      ruleFormInvoiceDeliveryInfo: {
        invoiceDeliveryInfoDetails: undefined,
        invoice: undefined,
        warehouse: undefined,
        registration: undefined,
        status: undefined
      },
      rulesInvoiceDeliveryInfo: {
        warehouse: [
          { required: true, message: "请输入", trigger: "blur" },
          { validator: validateWaHouse, trigger: ["blur", "change"] }
        ]
      },
      //调货单生成的以调货单id为key的Map
      selectedMaterialTransferMap: {},
      // -----------子业务-------------------
      ruleFormDetail: {
        material: undefined,
        num: undefined,
        receiveNum: undefined
      },
      productList: [],
      unitList: [],
      binList: [],
      rulesDetail: {
        material: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }]
        // receiveNum: [{ required: true, message: '请输入', trigger: 'blur' }]
      }
    };
  },
  async mounted() {
    await getFaWarehouseList({type:1}).then(res => {
      if (res.code === "0") {
        this.warehouseList = res.data;
      }
    });
    await getSupplier({ type: 1 }).then(res => {
      if (res.code === "0") {
        this.supplierList = res.data;
      }
    });
    await findMaterialListData({}).then(res => {
      if (res.code === "0") {
        this.materialList = res.data;
      }
    });

    findSendOrder(this.$route.query.id).then(res=>{
      let i=0;
      res.data.invoiceDeliveryInfos.forEach(invoiceDeliveryInfo=>{
        if(i== 0){
            this.editableTabsValue=invoiceDeliveryInfo.warehouse.name;
            i++;
        }

        invoiceDeliveryInfo.falseId=this.falseIdMax;
        this.falseIdMax++;
        invoiceDeliveryInfo.invoiceDeliveryInfoDetails.forEach(invoiceDeliveryInfoDetails=>{
          invoiceDeliveryInfoDetails.isEdit=false;
        })
      })
      this.ruleForm=res.data;
      this.ruleForm.supplierId=res.data.supplier.id;
      if(res.data.materialTransportPlanDTOs && res.data.materialTransportPlanDTOs.length!=0){
         if(!this.selectedMaterialTransferMap[res.data.supplier.id]){
            this.selectedMaterialTransferMap[res.data.supplier.id]=new Array();
         }
         res.data.materialTransportPlanDTOs.forEach( materialTransportPlanDTO => {
            this.selectedMaterialTransferMap[res.data.supplier.id].push(
              materialTransportPlanDTO
            );
         })
      }
      console.log('this.selectedMaterialTransferMap',this.selectedMaterialTransferMap);
    });
  },
  methods: {
    handleTabsEdit(targetName, action) {
        // console.log('targetName',targetName);
        //   console.log('action',action);
      if (action === 'add') {
         this.openDialog();
      }else if(action === 'remove'){
        for(let i=this.ruleForm.invoiceDeliveryInfos.length-1;i>=0;i--){
          if(targetName == this.ruleForm.invoiceDeliveryInfos[i].warehouse.name){
            //如果删除的是当前正在展示的标签
            if(targetName == this.editableTabsValue){
               //如果当前展示的标签是最后一个则展示前一个标签，如果没有则置空
               //如果不是最后一个标签，则展示后一个标签
               if(i+1 == this.ruleForm.invoiceDeliveryInfos.length){
                 if(i>0){
                   this.editableTabsValue=this.ruleForm.invoiceDeliveryInfos[i-1].warehouse.name
                 }else{
                   this.editableTabsValue=undefined;
                 }
               }else{
                 this.editableTabsValue=this.ruleForm.invoiceDeliveryInfos[i+1].warehouse.name
               }
            }
            this.ruleForm.invoiceDeliveryInfos.splice(i,1);
          }
        }
      }
    },
    /**
     * 删除清单
     */
    deleteInvoiceDeliveryInfo(invoiceDeliveryInfo) {
      console.log(invoiceDeliveryInfo);
      if (
        this.ruleForm.invoiceDeliveryInfos &&
        this.ruleForm.invoiceDeliveryInfos.length > 0
      ) {
        for (
          let i = 0;
          i < this.ruleForm.invoiceDeliveryInfos.length;
          i++
        ) {
          if (
            this.ruleForm.invoiceDeliveryInfos[i].falseId ==
            invoiceDeliveryInfo.falseId
          ) {
            this.ruleForm.invoiceDeliveryInfos.splice(i, 1);
            break;
          }
        }
      }
    },
    /**
     * 检查清单表中是否存在相同物料,如果有则将其清除
     */
    deleteRepeatMaterial(index, data) {
      // debugger;
      console.log("index", index);
      let materialList = JSON.parse(JSON.stringify(this.materialList));
      if (materialList.length > 0 && data.invoiceDeliveryInfoDetails.length > 0) {
        for (let i = materialList.length - 1; i >= 0; i--) {
          for (let j = 0; j < data.invoiceDeliveryInfoDetails.length; j++) {
            if (
              materialList[i].id == data.invoiceDeliveryInfoDetails[j].material.id
            ) {
              materialList.splice(i, 1);
              if (materialList.length == 0) {
                data.invoiceDeliveryInfoDetails[index].materialList = [];
                if (
                  data.invoiceDeliveryInfoDetails[index].material &&
                  data.invoiceDeliveryInfoDetails[index].material.id
                ) {
                  data.invoiceDeliveryInfoDetails[index].materialList.push(
                    data.invoiceDeliveryInfoDetails[index].material
                  );
                }
                console.log(
                  "data.invoiceDeliveryInfoDetails[index].material",
                  data.invoiceDeliveryInfoDetails[index].material
                );
                console.log(
                  "data.invoiceDeliveryInfoDetails[index].materialList",
                  data.invoiceDeliveryInfoDetails[index].materialList
                );
                return;
              }
              break;
            }
          }
        }
        data.invoiceDeliveryInfoDetails[index].materialList = materialList;
        let aa = data.invoiceDeliveryInfoDetails[index].material;
        if (
          data.invoiceDeliveryInfoDetails[index].material &&
          data.invoiceDeliveryInfoDetails[index].material.id
        ) {
          data.invoiceDeliveryInfoDetails[index].materialList.push(
            data.invoiceDeliveryInfoDetails[index].material
          );
        }

        console.log("data", data);
      }
    },
    /**
     * 删除单行数据
     */
    addRowData(data) {
      data.push({
        material: { id: undefined, name: undefined },
        num: undefined,
        receiveNum: undefined,
        isEdit: false
      });
      console.log('data',data);
    },
    /**
     * 删除单行数据
     */
    deleteRowData(index, data) {
      data.splice(index, 1);
    },
    /**
     * 锁定单行数据
     */
    lockRowData(index, data) {
      data[index].isEdit = false;
    },

    /**
     * 编辑清单行数据,使该行数据解锁
     */
    unlockRowData(index, data) {
      data.invoiceDeliveryInfoDetails[index].isEdit = true;
    },
    /**
     * 新增清单
     * ** */
    addInvoiceDeliveryInfo(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.falseIdMax += 1;
          const invoiceDeliveryInfoModel = {
            falseId: this.falseIdMax,
            invoiceDeliveryInfoDetails: [
              {
                material: { id: undefined, name: undefined },
                num: undefined,
                receiveNum: undefined,
                isEdit: false,
                materialList: []
              }
            ],
            invoice: {},
            warehouse: this.ruleFormInvoiceDeliveryInfo.warehouse,
            // registration: {},
            status: undefined
          };
          this.ruleForm.invoiceDeliveryInfos.push(
            invoiceDeliveryInfoModel
          );
          this.$refs[formName].resetFields();
          this.dialogFormVisible = false;
          this.editableTabsValue=invoiceDeliveryInfoModel.warehouse.name;
        }
      });
    },
    /**
     * 从物料调货计划跳转至新增送货单需要初始化送货单信息
     */
    initAddSendOrder(materialTransferList) {
      // console.log('materialTransferList',materialTransferList);
      if (materialTransferList) {
        let supplierId = "";
        //selectedMaterialTransferMap组成以供应商id为key，物料调货计划列表(按需求时间顺序排列)为value的Map
        materialTransferList.forEach(materialTransfer => {
          if (!supplierId) {
            supplierId = materialTransfer.supplier.id;
          }
          if (!this.selectedMaterialTransferMap[materialTransfer.supplier.id]) {
            this.selectedMaterialTransferMap[
              materialTransfer.supplier.id
            ] = new Array();
            this.selectedMaterialTransferMap[materialTransfer.supplier.id].push(
              materialTransfer
            );
          } else {
            for (
              var i = 0;
              i <
              this.selectedMaterialTransferMap[materialTransfer.supplier.id]
                .length;
              i++
            ) {
              let requireTimeInArray = new Date(
                this.selectedMaterialTransferMap[materialTransfer.supplier.id][
                  i
                ].requireTime.replace("-", "/")
              );
              let requireTime = new Date(
                materialTransfer.requireTime.replace("-", "/")
              );
              if (requireTime.getTime() >= requireTimeInArray.getTime()) {
                this.selectedMaterialTransferMap[
                  materialTransfer.supplier.id
                ].splice(i + 1, 0, materialTransfer);
                break;
              }
            }
          }
        });
        //生成送货单
        this.generateSendOrderBySupplierId(supplierId);
      }
    },

    /**
     * 根据供应商生成送货单
     */
    generateSendOrderBySupplierId(supplierId) {
      this.ruleForm.invoiceDeliveryInfos = [];
      if (this.selectedMaterialTransferMap[supplierId]) {
        this.selectedMaterialTransferMap[supplierId].forEach(
          materialTransfer => {
            let hasFaWaHouse = false;
            let breakFlag = false;
            //如果存在清单
            if (
              this.ruleForm.invoiceDeliveryInfos &&
              this.ruleForm.invoiceDeliveryInfos.length > 0
            ) {
              for (
                let i = 0;
                i < this.ruleForm.invoiceDeliveryInfos.length;
                i++
              ) {
                //调货计划与送货单清单内仓一致
                if (
                  this.ruleForm.invoiceDeliveryInfos[i].warehouse.id ==
                  materialTransfer.faWarehouse.id
                ) {
                  hasFaWaHouse = true;
                  let hasMaterial = false;
                  //存在清单详情
                  if (
                    this.ruleForm.invoiceDeliveryInfos[i]
                      .invoiceDeliveryInfoDetails &&
                    this.ruleForm.invoiceDeliveryInfos[i]
                      .invoiceDeliveryInfoDetails.length > 0
                  ) {
                    for (
                      let j = 0;
                      j <
                      this.ruleForm.invoiceDeliveryInfos[i]
                        .invoiceDeliveryInfoDetails.length;
                      j++
                    ) {
                      //物料一致，合并
                      if (
                        this.ruleForm.invoiceDeliveryInfos[i]
                          .invoiceDeliveryInfoDetails[j].material.id ==
                        materialTransfer.material.id
                      ) {
                        this.ruleForm.invoiceDeliveryInfos[
                          i
                        ].invoiceDeliveryInfoDetails[j] += parseFloat(
                          materialTransfer.num
                        );
                        hasMaterial = true;
                        breakFlag = true;
                        break;
                      }
                    }
                  }
                  if (breakFlag) {
                    break;
                  }
                  if (!hasMaterial) {
                    this.ruleForm.invoiceDeliveryInfos[
                      i
                    ].invoiceDeliveryInfoDetails.push({
                      material: materialTransfer.material,
                      num: materialTransfer.num,
                      receiveNum: undefined,
                      isEdit: false,
                      materialList: []
                    });
                  }
                }
              }
              //不存在该条物料调货计划所属内仓的清单
              if (!hasFaWaHouse) {
                //不存在清单，创建清单
                this.falseIdMax += 1;
                let materialList = JSON.parse(
                  JSON.stringify(this.materialList)
                );
                let invoiceDeliveryInfoModel = {
                  falseId: this.falseIdMax,
                  invoiceDeliveryInfoDetails: [
                    {
                      material: materialTransfer.material,
                      num: materialTransfer.num,
                      receiveNum: undefined,
                      isEdit: false,
                      materialList: []
                    }
                  ],
                  invoice: {},
                  warehouse: materialTransfer.faWarehouse,
                  // registration: {},
                  status: undefined
                };
                this.ruleForm.invoiceDeliveryInfos.push(
                  invoiceDeliveryInfoModel
                );
              }
            } else {
              //不存在清单，创建清单
              this.falseIdMax += 1;
              let materialList = JSON.parse(JSON.stringify(this.materialList));
              // for(let i=materialList.length-1;i>=0;i--){
              //     if(materialList[i].id == materialTransfer.material.id){
              //       materialList.splice(i,1);break;
              //     }
              // }
              let invoiceDeliveryInfoModel = {
                falseId: this.falseIdMax,
                invoiceDeliveryInfoDetails: [
                  {
                    material: materialTransfer.material,
                    num: materialTransfer.num,
                    receiveNum: undefined,
                    isEdit: false,
                    materialList: []
                  }
                ],
                invoice: {},
                warehouse: materialTransfer.faWarehouse,
                // registration: {},
                status: undefined
              };
              this.ruleForm.invoiceDeliveryInfos.push(
                invoiceDeliveryInfoModel
              );
            }
          }
        );
        this.ruleForm.supplierId = supplierId;
        this.ruleForm.expectArriveTime = this.selectedMaterialTransferMap[
          supplierId
        ][0].requireTime;
        console.log("this.ruleForm", this.ruleForm);
      }
    },
    /**
     * 打开修改弹框
     */
    update(index) {
      this.tableDataIndex = index;
      this.ruleFormDetail = this.tableData[index];
      this.dialogFormVisible = true;
      this.isUpdate = true;
    },
    /**
     * 子表单内容添加到父页面
     */
    submitFormDetail(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          const ruleFormDetail = this.ruleFormDetail;
          const { material, num, receiveNum } = {
            material: ruleFormDetail.material,
            num: ruleFormDetail.num,
            receiveNum: ruleFormDetail.receiveNum
          };
          let materialHasFlag = false;
          this.tableData.forEach(sendOrderDetail => {
            if (sendOrderDetail.material.id == material.id) {
              materialHasFlag = true;
              return;
            }
          });
          if (materialHasFlag) {
            this.$message({
              type: "info",
              message: "已添加同类型产品"
            });
            return;
          }
          if (!this.isUpdate) {
            this.tableData.push({
              material: material,
              num: num,
              receiveNum: receiveNum
            });
          } else {
            if (this.tableData[this.tableDataIndex] === ruleFormDetail) {
              this.dialogFormVisible = false;
              return;
            } else {
              this.tableData[this.tableDataIndex] = {
                material: material,
                num: num,
                receiveNum: receiveNum
              };
            }
          }
          this.$refs[formName].resetFields();
          this.dialogFormVisible = false;
        } else {
          return false;
        }
      });
    },
    /**
     * 提交父表单
     */
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // check detail
          console.log("this.ruleForm", this.ruleForm);
          console.log(
            "this.selectedMaterialTransferMap",
            this.selectedMaterialTransferMap
          );
          if (
            !this.ruleForm.invoiceDeliveryInfos ||
            this.ruleForm.invoiceDeliveryInfos.length === 0
          ) {
            this.$message.error("请添加清单");
            return false;
          }
          for (
            let i = 0;
            i < this.ruleForm.invoiceDeliveryInfos.length;
            i++
          ) {
            let invoiceDeliveryInfo = this.ruleForm
              .invoiceDeliveryInfos[i];
            if (
              invoiceDeliveryInfo.invoiceDeliveryInfoDetails &&
              invoiceDeliveryInfo.invoiceDeliveryInfoDetails.length > 0
            ) {
              let materialNumChecker = new Object();
              for (
                let j = 0;
                j < invoiceDeliveryInfo.invoiceDeliveryInfoDetails.length;
                j++
              ) {
                let invoiceDeliveryInfoDetails =
                  invoiceDeliveryInfo.invoiceDeliveryInfoDetails[j];
                if (
                  !invoiceDeliveryInfoDetails.material ||
                  !invoiceDeliveryInfoDetails.material.id
                ) {
                  this.$message.error(
                    "请" +
                      invoiceDeliveryInfo.warehouse.name +
                      "清单第" +
                      (j + 1) +
                      "行选择物料"
                  );
                  return false;
                } else {
                  if (materialNumChecker[invoiceDeliveryInfoDetails.material.id]) {
                    this.$message.error(
                      invoiceDeliveryInfo.warehouse.name +
                        "清单物料:" +
                        invoiceDeliveryInfoDetails.material.name +
                        "存在重复"
                    );
                    return false;
                  } else {
                    materialNumChecker[invoiceDeliveryInfoDetails.material.id] = 1;
                  }
                }
                if (!invoiceDeliveryInfoDetails.num) {
                  this.$message.error(
                    "请" +
                      invoiceDeliveryInfo.warehouse.name +
                      "清单第" +
                      (j + 1) +
                      "行填写数量"
                  );
                  return false;
                } else {
                  let pattern = /\d+\.{0,1}\d*$/;
                  if (!pattern.test(invoiceDeliveryInfoDetails.num)) {
                    this.$message.error(
                      invoiceDeliveryInfo.warehouse.name +
                        "清单第" +
                        (j + 1) +
                        "行数量必须为数字"
                    );
                    return false;
                  }
                }
              }
            } else {
              this.$message.error(
                "请添加" +
                  invoiceDeliveryInfo.warehouse.name +
                  "清单详情"
              );
              return false;
            }
          }

          console.log('selectedMaterialTransferMap',this.selectedMaterialTransferMap);
          console.log('selectedMaterialTransferMap[supplierId]',this.selectedMaterialTransferMap[this.ruleForm.supplierId]);
          const data = {
            id: this.ruleForm.id,
            sendOrderNo: this.ruleForm.sendOrderNo,
            sendDate: parseTime(this.ruleForm.sendDate),
            sendAddress: this.ruleForm.sendAddress,
            sendType: this.ruleForm.sendType,
            expectArriveTime: parseTime(this.ruleForm.expectArriveTime),
            carNo: this.ruleForm.carNo,
            catType: this.ruleForm.sendType,
            driverName: this.ruleForm.driverName,
            driverPhone: this.ruleForm.driverPhone,
            note: this.ruleForm.note,
            supplier: {
              id: this.ruleForm.supplierId
            },
            state: "0",
            createTime: this.ruleForm.createTime,
            invoiceDeliveryInfos: this.ruleForm
              .invoiceDeliveryInfos,
            materialTransportPlanDTOs: this.selectedMaterialTransferMap[
              this.ruleForm.supplierId
            ]
          };
          updateSupplier(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push("sendOrder");
            } else {
              this.$message.error(res.message);
            }
          });
        } else {
          return false;
        }
      });
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    changBg(index) {
      this.isChange = index;
    },
    deleteRow(index) {
      this.tableData.splice(index, 1);
    },
    /**
     * 添加弹框
     */
    openDialog() {
      this.ruleFormDetail = {};
      this.isUpdate = false;
      this.dialogFormVisible = true;
    },
    closeDialog() {
      this.dialogFormVisible = false;
    },
    toIndex() {
      this.$router.push("sendOrder");
    }
  }
};
</script>

<style lang='scss' scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.box {
  position: relative;
  height: calc(100vh + 500px);
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
.main .addChild {
  margin-top: 0px !important;
}
.main >>> .el-textarea__inner {
  width: 99.6%;
  height: 70px;
  resize: none;
}

.subBtn {
  margin-top: -18px;
}
.main-top {
  background: white;
  padding: 16px;
  & >>> .el-form-item {
    margin-bottom: 10px;
  }
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
  min-height: calc(100vh - 520px);
}
</style>
