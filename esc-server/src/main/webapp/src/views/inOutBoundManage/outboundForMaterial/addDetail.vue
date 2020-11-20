<template>
  <div class="main">
    <div>{{ $t('route.addOutboundOrderMaterialDetail') }}</div>
    <el-form
      ref="ruleForm"
      :model="ruleForm"
      :rules="rules"
      label-width="150px"
      class="demo-ruleForm"
    >
      <el-form-item :label="$t('inoutBound.materialName')" prop="materialId" size="small">
        <el-select v-model="ruleForm.materialId" filterable placeholder="请选择" value>
          <el-option
            v-for="item in materialList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.num')" prop="num" size="small">
        <el-input v-model="ruleForm.num" />
      </el-form-item>
      <el-form-item :label="$t('inoutBound.unitName')" prop="unitId" size="small">
        <el-select v-model="ruleForm.unitId" filterable placeholder="请选择" value>
          <el-option v-for="item in unitList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.binName')" prop="binId" size="small">
        <el-select v-model="ruleForm.binId" filterable placeholder="请选择" value>
          <el-option v-for="item in binList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item :label="$t('inoutBound.unitNum')" prop="unitNum" size="small">
        <el-input v-model="ruleForm.unitNum" />
      </el-form-item>

      <el-form-item>
        <el-button size="small" @click="resetForm('ruleForm')">取消</el-button>
        <el-button
          size="small"
          type="primary"
          @click="submitForm('ruleForm')"
          :loading="isLoading"
        >确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addOutboundOrderMaterialDetail } from "@/api/outboundOrderMaterialDetail";
import { list as unitList } from "@/api/unit";
import { getFaStorehouseList } from "@/api/faStorehouse";
import { findMaterialListData } from "@/api/material";

export default {
  mixins: [btn],
  data() {
    return {
      isLoading: false,
      ruleForm: {
        materialId: undefined,
        num: undefined,
        binId: undefined,
        unitId: undefined,
        unitNum: undefined
      },
      materialList: [],
      unitList: [],
      binList: [],
      rules: {
        materialId: [{ required: true, message: "请输入", trigger: "blur" }],
        num: [{ required: true, message: "请输入", trigger: "blur" }],
        unitId: [{ required: true, message: "请输入", trigger: "blur" }],
        binId: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    unitList({ code: "", name: "" }).then(res => {
      if (res.code === "0") {
        this.unitList = res.data;
      }
    });
    getFaStorehouseList({}).then(res => {
      if (res.code === "0") {
        this.binList = res.data;
      }
    });
    findMaterialListData({}).then(res => {
      if (res.code === "0") {
        this.materialList = res.data;
      }
    });
  },
  methods: {
    submitForm(formName) {
      this.isLoading = true;
      setTimeout(() => {
        this.isLoading = false;
      }, 1000);
      const data = {
        material: {
          id: this.ruleForm.materialId
        },
        unit: {
          id: this.ruleForm.unitId
        },
        bin: {
          id: this.ruleForm.binId
        },
        unitNum: this.ruleForm.unitNum,
        num: this.ruleForm.num,
        outboundOrderMaterial: {
          id: this.$route.query.id
        }
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          addOutboundOrderMaterialDetail(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push({
                path: "outboundOrderMaterialDetail",
                query: {
                  id: this.$route.query.id,
                  materialRequirePlanName: this.$route.query
                    .materialRequirePlanName
                }
              });
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.main >>> .el-button {
  margin-bottom: 30px;
}
</style>
