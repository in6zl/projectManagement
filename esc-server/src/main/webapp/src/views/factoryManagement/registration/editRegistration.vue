<template>
  <div>
    <con>
      <div class="main-top">
        <div>{{$t('registration.register')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <top>
            <el-form-item
              :label="$t('registration.carNo')"
              prop="carNo"
              size="small"
              slot="first"
            >
              <el-input v-model="ruleForm.carNo"></el-input>
            </el-form-item>
            <el-form-item
              :label="$t('registration.driverName')"
              prop="driverName"
              size="small"
              slot="second"
            >
              <el-input v-model="ruleForm.driverName"></el-input>
            </el-form-item>
            <el-form-item
              :label="$t('registration.driverPhone')"
              prop="driverPhone"
              size="small"
              slot="three"
            >
              <el-input v-model="ruleForm.driverPhone"></el-input>
            </el-form-item>
          </top>
          <top>
            <el-form-item
              :label="$t('registration.carType')"
              prop="carType"
              size="small"
              slot="first"
            >
              <el-select
                v-model="ruleForm.carType"
                :placeholder="$t('product.select')"
                style="width:100%"
              >
                <el-option
                  v-for="(item,i) in carTypes"
                  :key="i"
                  :label="item.dictKey"
                  :value="item.dictValue"
                >
                </el-option>
              </el-select>
            </el-form-item>
          </top>
        </el-form>
      </div>
      <div class="roleManageTable">
        <div class="title">送货清单信息</div>
        <el-table
          ref="multipleTable"
          :data="tableData"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          :header-cell-style="tableHeaderColor"
          :height="tableHeight"
        >
          <el-table-column
            type="selection"
            :selectable="handleSelection"
            width="55"
          >
          </el-table-column>
          <el-table-column
            type="index"
            width="130"
            :label="$t('table.serial')"
          ></el-table-column>
          <el-table-column
            :label="$t('supMana.sendOrder.sendOrderNo')"
            prop="invoiceCode"
            :show-overflow-tooltip="true"
          ></el-table-column>

          <el-table-column
            prop="warehouse.name"
            :label="$t('registration.warehouse')"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column
            prop="statusName"
            :label="$t('supMana.sendOrder.state')"
            :show-overflow-tooltip="true"
          ></el-table-column>
        </el-table>
      </div>
    </con>
    <submit class="subBtn">
      <el-button
        @click="resetForm('ruleForm')"
        size="small"
      >{{$t('button.cancel')}}</el-button>
      <el-button
        @click="submitForm('ruleForm')"
        size="small"
        type="primary"
      >{{$t('button.confirm')}}</el-button>
    </submit>
  </div>

</template>
<script>
import { getDict } from "@/api/dict";
import { btn } from "@/components/minxs/button";
import {
  updateRegistration,
  list,
  getRegistrationInfo
} from "@/api/registration";
import { findSendOrder } from "@/api/sendOrder";
import { Icon } from "element-ui";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import { getValue } from "@/api/dict";
export default {
  mixins: [btn],
  components: {
    top,
    submit,
    con
  },
  data() {
    return {
      id: this.$route.query.item,
      ruleForm: {
        carNo: "",
        driverName: "",
        driverPhone: "",
        carType: "",
        deliveryDTOList: []
      },
      tableHeight: window.innerHeight - 390,
      carTypes: [],
      orders: [],
      tableData: [],
      orderIds: [],
      rules: {
        carNo: [
          { required: true, message: "请输入车牌号", trigger: "blur" },
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"]
          }
        ],
        driverName: [
          {
            min: 1,
            max: 30,
            message: "长度在1到30个字符串",
            trigger: ["blur", "change"]
          }
        ],
        driverPhone: [
          {
            pattern: /^1[34578]\d{9}$/,
            message: "请输入正确的手机号码",
            trigger: "change"
          }
        ],
        carType: [
          { required: true, message: "请选择车辆类型", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    let data = {};
    getValue("登记车辆类型").then(res => {
      this.carTypes = res.data;
      getRegistrationInfo(this.id).then(res => {
        let registration = res.data;
        registration.carType = registration.carType + "";
        this.ruleForm = registration;
        this.tableData = res.data.deliveryDTOList;
      });
    });
  },
  methods: {
    submitForm(formName) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });
      this.$refs[formName].validate(valid => {
        if (valid) {
          let data = {
            id: this.ruleForm.id,
            carNo: this.ruleForm.carNo,
            driverName: this.ruleForm.driverName,
            driverPhone: this.ruleForm.driverPhone,
            carType: this.ruleForm.carType
          };
          updateRegistration(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "修改成功!",
                type: "success"
              });
              loading.close();
              this.$router.push("/registration");
            }
          });
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/registration");
    },
    handleSelection(row, index) {
      if (row.status === 2) {
        return true;
      } else {
        return false;
      }
    },
    handleSelectionChange(val) {
      this.ruleForm.deliveryDTOList = val;
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/inOutBound.scss";
.subBtn {
  margin-top: -18px;
}
.main-top {
  background: white;
  padding: 16px;
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
}
</style>
