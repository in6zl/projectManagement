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
              <el-input v-model="ruleForm.productName" class="test" style="width:100%"></el-input>
            </el-form-item>
            <el-form-item :label="$t('product.specification')" prop="specification" slot="second">
              <el-input v-model="ruleForm.specification" style="width:100%"></el-input>
            </el-form-item>
            <el-form-item :label="$t('product.unit') " prop="units" slot="three">
              <el-select
                v-model="ruleForm.unitName"
                :placeholder="$t('product.select')"
                style="width:100%"
              >
                <el-option v-for="(item,i) in units" :key="i" :label="item.name" :value="item.name"></el-option>
              </el-select>
            </el-form-item>
          </top>
        </el-form>
      </div>
      <lef :top="37"></lef>
      <div class="roleManageTable">
        <div class="userEdit">
          <span class="subtit" style="line-height:34px">物料信息</span>
          <el-button icon="el-icon-plus" size="small" @click="addMaterial()" type="primary">新增物料</el-button>
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
            prop="materialId"
            :show-overflow-tooltip="true"
            align="center"
          >
            <template slot-scope="scope">
              <el-select
                v-model="scope.row.materialId"
                :placeholder="$t('product.select')"
                size="small"
                style="width:50%"
                @change="getUnit(scope.row.materialId,scope.$index)"
              >
                <el-option
                  v-for="(item,i) in materialOptions"
                  :key="i"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column
            prop="num"
            :label="$t('product.number')"
            :show-overflow-tooltip="true"
            align="center"
          >
            <template slot-scope="scope">
              <el-input
                type="text"
                size="small"
                :placeholder="$t('main.placeInsert')"
                v-model.number="scope.row.num"
                @change="format(scope.row.num,scope.$index)"
                style="width:50%"
              ></el-input>
            </template>
          </el-table-column>
          <el-table-column
            prop="unitName"
            :label="$t('product.unit')"
            :show-overflow-tooltip="true"
            align="center"
          ></el-table-column>
          <el-table-column :label="$t('table.operation')" fixed="right" align="right">
            <template slot-scope="scope">
              <el-tooltip content="删除" placement="top">
                <svg-icon icon-class="delete" @click.native="deleteRow(scope.$index, tableData)"></svg-icon>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </con>
    <submit class="subBtn">
      <el-button
        @click="submitForm('ruleForm')"
        size="small"
        type="primary"
      >{{$t('button.confirm')}}</el-button>
      <el-button @click="resetForm('ruleForm')" size="small">{{$t('button.cancel')}}</el-button>
    </submit>
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
    const validateNum = (rule, value, callback) => {
      if (!/^[1-9]\d*$/.test(this.materialForm.num)) {
        return callback(new Error("请输入数量"));
      } else {
        if ((this.materialForm.num + "").length > 10) {
          return callback(new Error("长度在1到10个字符串"));
        } else {
          return callback();
        }
      }
    };
    const validateName = (rule, value, callback) => {
      getByName({
        id: this.$route.query.id,
        name: value,
      }).then((res) => {
        if (res.code != "0" && value != "") {
          return callback(new Error(res.message));
        } else {
          return callback();
        }
      });
    };
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
      rules: {
        productName: [
          { required: true, message: "请输入成品名称", trigger: "blur" },
          {
            min: 1,
            max: 10,
            message: "长度在1到10个字符串",
            trigger: ["blur", "change"],
          },
          {
            validator: validateName, // 自定义验证
            trigger: "blur",
          },
        ],
        specification: [
          {
            min: 1,
            max: 20,
            message: "长度在1到20个字符串",
            trigger: ["blur", "change"],
          },
        ],
      },
      materialOptions: [],
      unitOption: [],
      dialogVisible: false,
      productRules: {
        material: [{ required: true, message: "请选择物料", trigger: "blur" }],
        num: [
          {
            required: true,
            message: "请输入数量",
            trigger: "blur",
          },
          {
            validator: validateNum, // 自定义验证
            trigger: ["blur", "change"],
          },
        ],
        unitName: [
          {
            required: true,
            message: "请选择单位",
            trigger: "blur",
          },
        ],
      },
    };
  },
  mounted() {
    let data = {
      id: "",
      code: "",
      name: "",
    };
    list(data).then((res) => {
      this.units = res.data;
      this.unitOption = res.data;
    });
    materialList().then((res) => {
      this.materialOptions = res.data;
    });
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
    getUnit(materialId, index) {
      let flag = false;
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].materialId === materialId && i != index) {
          flag = true;
        }
      }
      if (flag) {
        this.$message({
          message: "已添加此物料",
          type: "info",
        });
        this.tableData[index].materialId = "";
        this.tableData[index].num = "";
        this.tableData[index].unitName = "";
      } else {
        this.materialOptions.forEach((material) => {
          if (material.id === materialId) {
            this.tableData[index].unitName = material.unitName;
          }
        });
      }
    },
    format(num, index) {
      if (!/^[1-9][0-9]*$/.test(num)) {
        this.$message({
          message: "请输入数字",
          type: "info",
        });
        this.tableData[index].num = "";
      }
    },
    deleteRow(index, tableData) {
      this.$confirm("确定删除此数据吗, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          tableData.splice(index, 1);
          this.$message({
            type: "success",
            message: "删除成功!",
          });
        })
        .catch(() => {});
    },
    submitForm(formName) {
      let data = {
        id: this.id,
        name: this.ruleForm.productName,
        spec: this.ruleForm.specification,
        unitName: this.ruleForm.unitName,
        productBomDTOList: this.tableData,
      };
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.tableData.length == 0) {
            this.$message({
              message: "请先新增产品",
            });
            return;
          }
          updateProduct(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/product");
            }
          });
          let size = this.tableData.length - 1;
          if (this.tableData[size].materialId === "") {
            this.$message({
              message: "第" + parseInt(size + 1) + "行物料为空",
              type: "info",
            });
            return;
          }
          if (this.tableData[size].num === "") {
            this.$message({
              message: "第" + parseInt(size + 1) + "行数量为空",
              type: "info",
            });
            return;
          }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$router.push("/product");
    },
    addMaterial() {
      if (this.tableData.length == 0) {
        this.tableData.push({
          materialName: "",
          materialId: "",
          unitName: "",
          num: "",
        });
      } else {
        let size = this.tableData.length - 1;
        if (this.tableData[size].materialId === "") {
          this.$message({
            message: "第" + parseInt(size + 1) + "行物料为空",
            type: "info",
          });
          return;
        }
        if (this.tableData[size].num === "") {
          this.$message({
            message: "第" + parseInt(size + 1) + "行数量为空",
            type: "info",
          });
          return;
        }
        this.tableData.push({
          materialName: "",
          materialId: "",
          unitName: "",
          num: "",
        });
      }
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
