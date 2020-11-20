<template>
  <div class="saleOrder">
    <div class="main">
      <el-collapse value="1">
        <el-collapse-item name="1">
          <template slot="title">
            <img
              src="@/assets/images/saleOrder.png"
              style="width:32px;heigth:32px;margin-right:16px"
            />
            <span style="font-size:17px;font-weight:bold">销售单号</span>
            ：{{saleDetail.orderNo}}
          </template>
          <div class="saleDetail">
            <span>签单人:</span>
            {{saleDetail.seller}}
            <span>期望到货时间:</span>
            {{saleDetail.expectDate}}
          </div>
          <table cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <th>序号</th>
              <th>成品</th>
              <th>数量</th>
              <th>单位</th>
            </tr>
            <tr v-for="(item,index) in saleDetailsList" :key="index">
              <td>{{index + 1}}</td>
              <td>{{item.product.name}}</td>
              <td>{{item.num}}</td>
              <td>{{item.unit}}</td>
            </tr>
          </table>
        </el-collapse-item>
      </el-collapse>
    </div>

    <div class="addResult">
      <div>
        <span>添加交付计划</span>
      </div>
      <!-- 添加生产计划弹窗 -->
      <el-dialog title="添加交付计划" :visible.sync="proDialogVisible">
        <el-form
          class="demo-ruleForm"
          label-width="150px"
          ref="saleForm"
          :rules="saleRules"
          :model="saleForm"
        >
          <el-row>
            <el-col :span="21">
              <el-form-item label="名称" prop="name">
                <el-input
                  placeholder="请输入名称"
                  maxlength="64"
                  v-model="saleForm.name"
                  clearable
                  size="small"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="21">
              <el-form-item label="选择交付日期" prop="deliveryDate">
                <el-date-picker
                  v-model="saleForm.deliveryDate"
                  type="date"
                  placeholder="选择交付日期"
                  size="small"
                  style="width:100%"
                  value-format="yyyy-MM-dd"
                ></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" class="proConfirmBtn">
              <el-button size="small" @click="proConfirmCancel('saleForm')">取消</el-button>
              <el-button type="primary" size="small" @click="addCollapse('saleForm')">确定</el-button>
            </el-col>
          </el-row>
        </el-form>
      </el-dialog>
    </div>
    <!-- 添加交付计划时间线 -->
    <el-row>
      <el-col :span="6">
        <div class="detailBtn">
          <el-button type="primary" @click="alertProDialog" size="mini">新增</el-button>
        </div>
        <el-scrollbar style="height:calc(100vh - 363px);background:white">
          <div class="collapse">
            <el-timeline>
              <el-timeline-item
                v-for="(activity, index) in activities"
                :key="index"
                :icon="activity.icon"
                :type="activity.type"
                :color="activity.color"
                :size="activity.size"
                @click.native="activeColor(index)"
              >
                <template>
                  <div style="font-size:14px;color:gray">
                    <span v-if="!activity.isEditItem">{{activity.timestamp}}</span>
                    <el-date-picker
                      type="date"
                      placeholder="选择日期"
                      size="mini"
                      style="width:70%"
                      v-model="activity.timestamp"
                      v-else
                      value-format="yyyy-MM-dd"
                    ></el-date-picker>
                  </div>
                  <div style="font-size:18px;margin-top:10px">
                    <span v-if="!activity.isEditItem">{{activity.content}}</span>
                    <el-input
                      type="text"
                      size="mini"
                      style="width:70%"
                      v-model="activity.content"
                      v-else
                    ></el-input>
                  </div>
                  <div style="position:absolute;right:10px;top:30px">
                    <svg-icon icon-class="edit" @click.native="editDeliverItme(activity)"></svg-icon>
                    <svg-icon icon-class="delete" @click.native="deleteDeleveryRow(index)"></svg-icon>
                  </div>
                </template>
              </el-timeline-item>
            </el-timeline>
          </div>
        </el-scrollbar>
      </el-col>
      <el-col :span="18">
        <div style="width:98%;background:white;float:right;">
          <div class="detailBtn">
            <el-button
              type="primary"
              style="float:right;margin:16px;"
              @click="addTableData"
              size="mini"
            >新增</el-button>
          </div>
          <el-scrollbar style="height:calc(100vh - 395px);">
            <el-table
              :data="selectItem.tableData"
              style="width: 98%;margin-left:1%"
              :header-cell-style="tableHeaderColor"
            >
              <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
              <el-table-column
                width="240"
                label="成品"
                prop="product.name"
                :show-overflow-tooltip="true"
              >
                <template slot-scope="scope">
                  <el-select
                    :placeholder="$t('main.placeInsert')"
                    size="small"
                    filterable
                    clearable
                    style="width:100%"
                    v-model="scope.row.product"
                    v-if="scope.row.isEdit"
                    value-key="id"
                    @change="autoComplateUnit(scope.row.product.id,selectItem.tableData,scope.$index)"
                  >
                    <el-option
                      v-for="item in productOptions"
                      :key="item.id"
                      :label="item.name"
                      :value="item"
                    ></el-option>
                  </el-select>
                </template>
              </el-table-column>

              <el-table-column prop="num" label="数量" width="135" :show-overflow-tooltip="true">
                <template slot-scope="scope">
                  <el-input
                    type="text"
                    size="small"
                    :placeholder="$t('main.placeInsert')"
                    v-model.number="scope.row.num"
                    style="width:100%"
                    v-if="scope.row.isEdit"
                  ></el-input>
                  <span v-else>{{scope.row.num}}</span>
                </template>
              </el-table-column>
              <el-table-column label="操作" fixed="right" align="right">
                <template slot-scope="scope">
                  <svg-icon icon-class="edit" @click.native="edit(scope.$index, scope.row)"></svg-icon>
                  <svg-icon
                    icon-class="delete"
                    @click.native="deleteRow(scope.$index, selectItem.tableData)"
                  ></svg-icon>
                </template>
              </el-table-column>
            </el-table>
          </el-scrollbar>
        </div>
      </el-col>
    </el-row>
    <div class="submitBtn">
      <el-button size="small" @click="resetForm('saleForm'),changBg(2)">取消</el-button>
      <el-button size="small" type="primary" @click="submitForm('saleForm'),changBg(1)">确定</el-button>
    </div>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { prolist } from "@/api/product";
import {
  addDeliver,
  supplyList,
  deleteDeliver,
  deleteDetailDeliver,
} from "@/api/deliveryPlan";
import { selectSellParams, find, getDeliverData } from "@/api/sellOrder";
import { parseTime } from "@/utils/index";

export default {
  mixins: [btn],
  data() {
    const validatePro = (rule, value, callback) => {
      if (this.productOptions.length == 0) {
        return callback(new Error("请先创建成品"));
      } else {
        for (let item of this.productOptions) {
          if (item.name == value) {
            return callback();
          }
        }
        callback(new Error("请选择正确的成品"));
      }
    };
    const validateSaleOrder = (rule, value, callback) => {
      console.log(this.saleOrderOptions);
      if (this.saleOrderOptions.length == 0) {
        return callback(new Error("请先创建销售单"));
      } else {
        for (let item of this.saleOrderOptions) {
          if (item.text == value) {
            return callback();
          }
        }
        callback(new Error("请选择正确的销售单"));
      }
    };
    const validateDate = (rule, value, callback) => {
      let time = this.saleDetail.orderTime.substring(0, 10);
      if (value < time) {
        return callback(new Error("签单日期不能小于交付日期"));
      } else {
        return callback();
      }
    };
    return {
      deleteDetailsList: [], //要删除的交付计划项
      deleteList: [], //要删除的交付计划
      activities: [],
      selectItem: "", //当前交付计划对象
      selectIndex: "", //当前交付计划对象
      saleDetailsList: [],
      saleDetail: "",
      productOptions: [],
      saleId: "",
      description: "",
      proDialogVisible: false,
      saleOrderOptions: [],
      productForm: {
        product: {
          id: "",
          name: "",
        },
        num: "",
      },
      saleForm: {
        saleOrder: {
          text: "",
          value: "",
        },
        name: "",
        deliveryDate: "",
      },
      saleRules: {
        name: [{ required: true, message: "请填写计划名称", trigger: "blur" }],
        deliveryDate: [
          {
            required: true,
            message: "请选交付日期",
            trigger: "blur",
          },
          {
            validator: validateDate,
            trigger: "blur",
          },
        ],
      },
      productRules: {
        product: {
          id: [{ required: true, message: "请选择成品", trigger: "blur" }],
        },
        num: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          {
            type: "number",
            message: "数量必须是数字",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    editDeliverItme(tableData) {
      tableData.isEditItem = !tableData.isEditItem;
    },
    deleteDeleveryRow(index) {
      let activities = this.activities;
      let id = activities[index].id;
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        if (id) {
          this.deleteList.push(id);
          this.activities[index].tableData = [];
          this.activities.splice(index, 1);
        } else {
          this.activities[index].tableData = [];
          this.activities.splice(index, 1);
        }
        this.$message({
          type: "success",
          message: "删除成功!",
        });
        this.activeColor(index != 0 ? index - 1 : 0);
      });
    },
    //自动填充单位
    async autoComplateUnit(id, tableData, index) {
      let isRepeat = this.activities[this.selectIndex].tableData.filter(
        (item) => item.product.id == id
      );
      if (isRepeat.length > 1) {
        this.$message({
          message: "请不要选择重复相同的成品",
        });
        tableData[index].product = {};
      }
    },
    //保存交付计划项
    edit(index, tableData) {
      if (tableData.product.id == "" || tableData.num == "") {
        this.$message({
          type: "error",
          message: "请填写完整数据后在保存",
        });
        return;
      }
      this.$set(tableData, "isEdit", !tableData.isEdit);
      this.$forceUpdate();
    },
    addTableData() {
      this.selectItem.tableData.push({
        product: {
          id: "",
          name: "",
        },
        num: "",
        isEdit: true,
      });
    },
    activeColor(index) {
      if (this.activities.length > 0) {
        this.activities.forEach((item) => (item.color = ""));
        this.activities[index].color = "#0bbd87";
        this.selectItem = this.activities[index];
        this.selectIndex = index;
      } else {
        return;
      }
    },
    //添加交付计划
    addCollapse(formName) {
      this.$refs[formName].validate((validate) => {
        if (validate) {
          this.activities.push({
            content: this.saleForm.name,
            timestamp: this.saleForm.deliveryDate,
            color: "",
            isEditItem: false,
            tableData: [
              {
                product: {
                  id: "",
                  name: "",
                },
                num: "",
                isEdit: true,
              },
            ],
            saleId: this.saleDetail.id,
          });
          if (this.activities.length == 1) {
            this.activeColor(0);
          }
          this.$refs[formName].resetFields();
          this.proDialogVisible = false;
          this.activeColor(this.activities.length - 1);
        }
      });
    },
    proConfirmCancel(formName) {
      this.$refs[formName].resetFields();
      this.proDialogVisible = false;
    },
    alertProDialog() {
      if (this.productOptions.length === 0) {
        this.$message({
          message: "请先选择销售单",
        });
      } else {
        this.proDialogVisible = true;
      }
    },
    resetForm(formName) {
      this.$router.push("/deliveryPlan");
    },
    //校验交付数量再对应销售单里是否合法
    checkNum() {
      let array = this.groupArray(this.activities);
      let flag = true;
      for (let item of this.saleDetailsList) {
        let n = this.contains(array, item.product);
        if (n != -1 && array[n].value > item.num) {
          flag = false;
          this.$message({
            message:
              item.product.name +
              "交付数量(" +
              array[n].value +
              ")大于销售数量",
          });
          break;
        }
      }
      return flag;
    },
    contains(array, item) {
      let index = -1;
      for (let i = 0; i < array.length; i++) {
        if (array[i].key.id === item.id) {
          index = i;
          break;
        }
      }
      return index;
    },
    groupArray(arr) {
      const dest = [];
      arr.forEach((item) => {
        item.tableData.forEach((i) => {
          let n = this.contains(dest, i.product);
          if (n != -1) {
            dest[n].value += i.num;
          } else {
            dest.push({
              key: i.product,
              value: i.num,
            });
          }
        });
      });
      return dest;
    },
    submitForm(formName) {
      //校验数量是否合法
      if (!this.checkNum()) {
        return;
      }
      let saveOrUpdateList = [];
      let istrue = false;
      let submitData = {};
      saveOrUpdateList = this.activities.map((item) => {
        istrue = item.tableData.every((d) => {
          return d.product.id != "" || d.num != "";
        });
        item.tableData.map((d) => {
          delete d.id;
        });
        return {
          deliveryPlanDetailsList: item.tableData,
          deliveryDate: item.timestamp,
          name: item.content,
          saleId: item.saleId,
          id: item.id,
        };
      });
      if (istrue) {
        saveOrUpdateList.map((item) => {
          if (!item.id) {
            delete item.id;
          }
        });
        submitData.deleteList = this.deleteList;
        submitData.deleteDetailsList = this.deleteDetailsList;
        submitData.saveOrUpdateList = saveOrUpdateList;
        supplyList(submitData).then((res) => {
          if (res.code === "0") {
            this.$message({
              message: "创建成功",
              type: "success",
            });
            this.$router.push("/deliveryPlan");
            // this.$refs[formName].resetFields();
          }
        });
      } else {
        this.$message({
          message: "请填写完整参数",
          type: "error",
        });
        return;
      }
    },
    getSaleOpetions() {
      selectSellParams().then((res) => {
        if (res.code === "0") {
          this.saleOrderOptions = res.data;
        }
      });
    },
    deleteRow(index, tableData) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        let id = tableData[index].id;
        if (id) {
          // deleteDetailDeliver(id).then(res => {
          //   if (res.code == 0) {
          //   }
          // });
          this.deleteDetailsList.push(id);
          tableData.splice(index, 1);
        } else {
          tableData.splice(index, 1);
        }
        this.$message({
          type: "success",
          message: "删除成功!",
        });
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    querySearchAsyncPro(queryString, cb) {
      console.log(this.productOptions);
      var productOptions = this.productOptions;
      for (let item of productOptions) {
        item.value = item.name;
      }
      var results = queryString
        ? productOptions.filter(this.createStateFilter(queryString))
        : productOptions;
      cb(results);
    },
    createStateFilter(queryString) {
      return (state) => {
        return (
          state.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    proSelect(item) {
      this.productForm.product.id = item.id;
      this.productForm.product.name = item.name;
    },
    getProductList(item) {
      this.productOptions = [];
      find(item).then((res) => {
        let productList = [];
        this.saleDetail = res.data;
        res.data.saleDetailsList.forEach((d) => {
          productList.push(d.product);
        });
        this.saleDetailsList = res.data.saleDetailsList;
        this.productOptions = productList;
        this.deliveryDate(res.data);
      });
    },
    deliveryDate(data) {
      getDeliverData({ saleNo: data.orderNo })
        .then((res) => {
          res.data.map((item, index) => {
            item.deliveryPlanDetailsList.map((item) => {
              item.isEdit = true;
            });
            this.activities.push({
              tableData: item.deliveryPlanDetailsList,
              content: item.name,
              timestamp: item.deliveryDate,
              color: "",
              id: item.id,
              saleId: item.saleId,
              isEditItem: false,
            });
          });
        })
        .then(() => {
          this.activities.map((item, index) => {
            this.activeColor(index);
            this.selectItem = this.activities[index];
          });
          this.activeColor(0);
        });
    },
  },
  mounted() {
    let productData = {
      productName: "",
      specification: "",
    };
    this.getSaleOpetions();
    this.saleOrderOptions.push(this.$route.query);
    this.saleForm.saleOrder = this.$route.query.value;
    // prolist(productData).then(res => {
    //   this.productOptions = res.data;
    // });
    if (this.$route.query.value) {
      this.getProductList(this.$route.query.value);
    }
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/sale.scss";

.addResult >>> .el-form-item:nth-child(1) {
  margin-left: 0 !important;
}

.addResult .el-form-item {
  margin-left: 20px;
}

.addResult {
  overflow: hidden;
}
.proConfirmBtn {
  padding: 0 0 10px 0;
  display: flex;
  justify-content: center;
}
.submitBtn {
  padding: 15px 0 15px 0;
  background: rgba(255, 255, 255, 1);
  box-shadow: 0px -4px 8px 0px rgba(0, 0, 0, 0.05);
  position: absolute;
  bottom: 0;
  display: flex;
  justify-content: center;
  z-index: 10;
  position: fixed;
  left: -16px;
  width: calc(100% + 16px);
  padding-left: 20%;
}
.roleManageTable {
  position: relative;
  height: calc(100vh - 444px);
}
table {
  color: rgba(51, 51, 51, 1);
  background: #eeeeee;
  // border-right: 1px solid #333333;
  // border-bottom: 1px solid #333333;
  margin-top: 16px;
  border-collapse: collapse;
}
table td {
  width: 16.66%;
  // height: 44px;
  background: #fff;
  // border-left: 1px solid #333333;
  // border-top: 1px solid #333333;
  padding-left: 16px;
  word-break: break-word;
  padding: 8px 16px;
  border-bottom: 1px solid lightgray;
}
table th {
  // height: 44px;
  padding-left: 16px;
  // border-left: 1px solid #333333;
  // border-top: 1px solid #333333;
  padding: 8px 16px;
  text-align: left;
}
.el-collapse {
  border-top: none;
  // border-bottom: none;
}
.saleDetail span {
  font-weight: bold;
}
.saleDetail span:nth-child(2) {
  margin-left: 16px;
}
.collapse {
  background: white;
  padding: 16px;
}
.addResult > div:nth-child(1) {
  font-weight: 500;
  color: #333;
  font-size: 14px;
  background: #f0f2f5;
  font-size: 18px;
  font-weight: blod;
  font-weight: blod;
  font-weight: bold;
  padding-bottom: 16px;
  padding-top: 0;
}
.detailBtn {
  background: white;
  display: flex;
  justify-content: flex-end;
  padding: 16px;
}
.collapse /deep/.el-timeline-item__wrapper:hover {
  margin-left: 12px;
  background: #ecf5ff;
  border-radius: 4px;
  padding: 5px 10px;
}
.collapse /deep/.el-timeline-item {
  padding-bottom: 0px;
}
.collapse /deep/.el-timeline-item__wrapper {
  margin-left: 12px;
  padding: 5px 10px;
}
.collapse /deep/ .el-timeline {
  padding-left: 0;
}
</style>
