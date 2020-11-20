<template>
  <div class="content">
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">成品详情</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          class="demo-ruleForm"
          label-width="110px"
        >
          <top>
            <el-form-item :label="$t('product.productName')" prop="productName" slot="first">
              <el-input v-model="ruleForm.productName" class="test" style="width:100%" disabled></el-input>
            </el-form-item>
            <el-form-item :label="$t('product.specification')" prop="specification" slot="second">
              <el-input v-model="ruleForm.specification" style="width:100%" disabled></el-input>
            </el-form-item>
            <el-form-item :label="$t('product.unit') " prop="units" slot="three">
              <el-input v-model="ruleForm.unitName" style="width:100%" disabled></el-input>
            </el-form-item>
          </top>
        </el-form>
      </div>
      <lef :top="37"></lef>
      <div class="roleManageTable">
        <div class="userEdit">
          <span class="subtit" style="line-height:34px">物料信息</span>
          <el-button @click="resetForm('ruleForm')" size="small" type="primary">返回</el-button>
        </div>
        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" :label="$t('table.serial')"></el-table-column>
          <el-table-column
            :label="$t('product.materialName')"
            prop="materialName"
            :show-overflow-tooltip="true"
          ></el-table-column>
          <el-table-column prop="num" :label="$t('product.number')" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column
            prop="unitName"
            :label="$t('product.unit')"
            :show-overflow-tooltip="true"
          ></el-table-column>
        </el-table>
      </div>
    </con>
  </div>
</template>
<script>
import { btn } from "@/components/minxs/button";
import { updateProduct, getProductInfo, getByName } from "@/api/product";
import { list } from "@/api/unit";
import { materialList } from "@/api/productBom";

import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
export default {
  components: {
    top,
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    return {
      id: this.$route.query.id,
      ruleForm: {
        productName: "",
        specification: "",
        unitName: "",
      },
      materialForm: {
        material: "",
        unitName: "",
        num: "",
      },
      tableHeight: window.innerHeight - 390,
      tableData: [],
      units: [],
      value: "",
    };
  },
  mounted() {
    let data = {
      id: "",
      code: "",
      name: "",
    };
    getProductInfo(this.id).then((res) => {
      let data = res.data;
      let list = res.data.productBomDTOList;
      let table = [];
      list.forEach((productBom) => {
        let item = {
          materialId: productBom.materialId,
          materialName: productBom.materialName,
          unitName: productBom.unitName,
          num: productBom.num,
        };
        table.push(item);
      });
      this.tableData = table;
      this.ruleForm.productName = data.name;
      this.ruleForm.specification = data.spec;
      this.ruleForm.unitName = data.unitName;
    });
  },
  methods: {
    resetForm(formName) {
      this.$router.push("/product");
    },
    tableHeaderColor({ row, column, rowIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
  },
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
  & >>> .el-form-item {
    margin-bottom: 5px;
  }
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
}

.userEdit {
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-bottom: 16px;
}
.dialogBtn {
  display: flex;
  justify-content: flex-end;
  margin-top: 16px;
  margin-right: 9px;
}
.content >>> .el-dialog__body {
  padding: 5px 20px !important;
}
</style>
