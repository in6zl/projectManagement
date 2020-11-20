<template>
  <div class="saleOrder">
    <div class="main">
      <div style="overflow:hidden;background:white;width:100%;padding:16px">
        <div class="subtit">{{$t('product.addProduct')}}</div>
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          class="demo-ruleForm"
          label-width="100px"
        >
          <div class="wrapper">
            <el-col :span="12">
              <el-form-item :label="$t('product.productName')" prop="productName" size="small">
                <el-input v-model="ruleForm.productName" style="width:95%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('product.specification')" prop="specification" size="small">
                <el-input v-model="ruleForm.specification" style="width:95%"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item :label="$t('product.unit') " prop="unitName" size="small">
                <el-select
                  v-model="ruleForm.unitName"
                  :placeholder="$t('product.select')"
                  style="width:96%"
                >
                  <el-option
                    v-for="(item,i) in unitOption"
                    :key="i"
                    :label="item.name"
                    :value="item.name"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </div>
        </el-form>
      </div>

      <div class="table" style="padding-top:1px;!important">
        <lef :top="23"></lef>
        <div style="justify-content: space-between;">
          <div
            style="margin-left:16px;margin-bottom: 16px;float:left;line-height:34px"
            class="subtit"
          >{{$t('product.addInfo')}}</div>
          <div class="tableBtn" style="padding-right:16px">
            <el-button size="small" style="float:right;" type="primary" @click="addMaterial()">新增物料</el-button>
          </div>
        </div>

        <el-table
          :data="tableData"
          style="width: 100%"
          :height="tableHeight"
          header-cell-style="background:rgba(250,250,250,1);
                             font-size:14px;
                             font-family:PingFangSC-Medium,PingFang SC;
                             font-weight:500;
                             color:rgba(153,153,153,1);
                             line-height:22px;"
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
    </div>
    <submit-btn>
      <el-button @click="resetForm('ruleForm')" size="small">{{$t('button.cancel')}}</el-button>
      <el-button
        @click="submitForm('ruleForm')"
        size="small"
        type="primary"
      >{{$t('button.confirm')}}</el-button>
    </submit-btn>
  </div>
</template>
<script>
import { btn } from "@/components/minxs/button";
import { addProduct, prolist, getByName } from "@/api/product";
import { list } from "@/api/unit";
import { materialList } from "@/api/productBom";
import submitBtn from "@/components/common/submitBtn";
import top from "@/components/commonTop/index";
import submit from "@/components/common/submitBtn";
import con from "@/components/common/content";
import lef from "@/components/common/l_icon";
import { deleteDict } from "@/api/dict";

export default {
  components: {
    submitBtn,
    top,
    submit,
    con,
    lef,
  },
  mixins: [btn],
  data() {
    const validateName = (rule, value, callback) => {
      getByName({
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
      products: [],
      tableHeight: window.innerHeight - 444,
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
        unitName: [{ required: true, message: "请选择单位", trigger: "blur" }],
      },
      materialOptions: [],
      unitOption: [],
      dialogVisible: false,
    };
  },
  mounted() {
    let data = {
      id: "",
      code: "",
      name: "",
    };
    list({}).then((res) => {
      this.unitOption = res.data;
    });
    materialList().then((res) => {
      this.materialOptions = res.data;
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
      this.$confirm("确定删除此数据吗?", "提示", {
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
      let data = "";
      data = {
        id: "",
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
          addProduct(data).then((res) => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success",
              });
              this.$router.push("/product");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
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
    resetForm(formName) {
      this.$router.push("/product");
    },
  },
};
</script>
<style lang="scss" scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/systemStyle/inOutBound.scss";
.wrapper {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
}
.el-input {
  width: 375px;
  margin-left: 32px;
}
.el-select {
  width: 375px;
  margin-left: 32px;
}
.title {
  margin: 16px 0px 0px 16px;
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  line-height: 24px;
}
.operation {
  margin: 24px 0px 16px 16px;
}
.demo-ruleForm {
  display: flex;
  flex-direction: column;
}
.saleOrder >>> .el-dialog__body {
  padding: 32px 16px 0px;
}
.saleOrder >>> .el-dialog__header {
  padding: 16px 0px 0px 16px;
  font-size: 16px;
  font-family: PingFangSC-Semibold, PingFang SC;
  font-weight: 600;
  color: rgba(51, 51, 51, 1);
  line-height: 22px;
}
.saleOrder >>> .el-form-item__label {
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(0, 0, 0, 0.85);
}
.main {
  background: none !important;
}
.table {
  background: white;
  padding: 16px 0;
  height: calc(100% - 160px);
}
.tableBtn {
  margin-left: 16px;
  margin-top: 16px;
}
</style>
<style lang="scss">
.wrapper {
  .el-form-item__label {
    font-size: 14px;
    font-family: PingFangSC-Regular, PingFang SC;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.85);
  }
  .el-form-item__error {
    margin-left: 32px;
  }
}
</style>
