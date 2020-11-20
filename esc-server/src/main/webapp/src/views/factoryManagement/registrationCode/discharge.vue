<template>
  <div>
    <con>
      <div class="main-top">
    <div>{{$t('registrationCode.discharge')}}</div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
    <top>
      <el-form-item
        :label="$t('registration.orderCode')"
        prop="invoiceCode"
        size="small"
        slot="first"
      >
        <el-input
          v-model="ruleForm.invoiceCode"
          disabled="true"
        ></el-input>
      </el-form-item>
      <el-form-item
        :label="$t('registration.warehouse')"
        prop="warehouse"
        size="small"
        slot="second"
      >
        <el-input
          v-model="ruleForm.warehouse.name"
          disabled="true"
        ></el-input>
      </el-form-item>
    </top>
    </el-form>
      </div>
    <div class="roleManageTable">
      <div class="title">送货清单详情</div>
      <el-table
        :data="tableData"
        style="width: 100%"
        :height="tableHeight"
        :header-cell-style="tableHeaderColor"
      >
        <el-table-column
          type="index"
          width="130"
          :label="$t('table.serial')"
        ></el-table-column>
        <el-table-column
          :label="$t('product.materialName')"
          prop="material.name"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          :label="$t('registrationCode.num')"
          prop="num"
          :show-overflow-tooltip="true"
        ></el-table-column>
        <el-table-column
          prop="receiveNum"
          :label="$t('registrationCode.receiveNum')"
          :show-overflow-tooltip="true"
        >
          <template slot-scope="scope">
            <el-input
              type="text"
              size="mini"
              style="width:100px"
              v-model="scope.row.receiveNum"
            ></el-input>
          </template>
        </el-table-column>
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
import { btn } from "@/components/minxs/button";
import { parseTime } from "@/utils/index";
import { discharge } from "@/api/callNumber";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
export default {
  mixins: [btn],
  components: {
    top,
    submit,
    con
  },
  data() {
    return {
      ruleForm: {},
      tableData: [],
      tableHeight: window.innerHeight - 340
    };
  },
  mounted() {
    let delivery = JSON.parse(localStorage.getItem("delivery"));
    this.tableData = delivery.invoiceDeliveryInfoDetails;
    this.ruleForm = delivery;
  },
  methods: {
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    submitForm(formName) {
      let details = this.tableData;
      let overload = false,
        isnum = false;
      details.forEach(detail => {
        if (parseInt(detail.num) < parseInt(detail.receiveNum) ) {
          overload = true;
        }
        if (!/^[1-9][0-9]*$/.test(detail.receiveNum)) {
          isnum = true;
        }
      });
      if (overload) {
        this.$message({
          message: "实收数量大于实际数量"
        });
        return;
      }
      if(isnum){
        this.$message({
          message: "请填写数字"
        });
        return;
      }
      let data = {
        id: this.ruleForm.id,
        invoiceDeliveryInfoDetails: this.tableData
      };
      discharge(data).then(res => {
        this.$message({
          message:"卸货成功",
          type:"success"
        })
        this.$router.push("/registrationCode");
      });
    },
    resetForm(formName) {
      this.$router.push("/registrationCode");
    },
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
