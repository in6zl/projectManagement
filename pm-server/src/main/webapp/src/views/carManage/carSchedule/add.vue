<template>
  <div class="page">
    <el-card shadow="never" style="margin-bottom: 10px">
      <div style="width: 100px" class="search-title">{{ $t('route.addCarSchedule') }}</div>
      <div class="search-content">
        <el-form
          ref="ruleForm"
          :model="ruleForm"
          :rules="rules"
          label-width="300px"
          class="demo-ruleForm"
        >
          <el-form-item :label="$t('car.carNo')" prop="carId" size="small">
            <el-select
              v-model="ruleForm.carId"
              filterable
              placeholder="请选择"
              value
              style="width:420px"
              clearable
            >
              <el-option
                v-for="item in carList"
                :key="item.id"
                :label="item.carNo"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-form>
      </div>
    </el-card>

    <el-row :gutter="10">
      <el-col :xs="24" :sm="14" :md="14" :lg="14" :xl="14">
        <el-card shadow="never" style="height: calc(100vh - 280px)">
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            :rules="rules"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item
              :label="$t('car.outProductOrderNo')"
              prop="outProductOrderId"
              size="small"
            >
              <el-select
                v-model="ruleForm.outProductOrderId"
                filterable
                placeholder="请选择"
                value
                style="width:450px"
                clearable
              >
                <el-option
                  v-for="item in outProductOrderList"
                  :key="item.id"
                  :label="item.orderNo"
                  :value="item.id"
                />
              </el-select>
            </el-form-item>
          </el-form>
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            :rules="rules"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-form-item
              :label="$t('car.startTimeAndEndTime')"
              filterable
              prop="startTimeAndEndTime"
              size="small"
            >
              <el-date-picker
                v-model="ruleForm.startTime"
                style="width:450px"
                format="yyyy 年 MM 月 dd 日"
                type="daterange"
                value-format="yyyy-MM-dd HH:mm:ss"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="10" :md="10" :lg="10" :xl="10">
        <el-card shadow="never" style="height: calc(100vh - 280px)">
          <el-form
            ref="ruleForm"
            :model="ruleForm"
            :rules="rules"
            label-width="100px"
            class="demo-ruleForm"
          >
            <el-date-picker
              v-model="ruleForm.value2"
              size="small"
              style="width: 400px"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form>
          <el-row>
            <el-col :span="22">
              <div class="detailList">
                <div>
                  <span>排版1</span>
                </div>
                <div>
                  <div>出货单号码</div>
                  <div>2020.03.16 00:00:00</div>
                </div>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="22">
              <div class="detailList">
                <div>
                  <span>排版2</span>
                </div>
                <div>
                  <div>出货单号码</div>
                  <div>2020.03.16 00:00:00</div>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <div class="bottom-buttons">
      <el-button type="default" size="small" @click="toIndex">取消</el-button>
      <el-button type="primary" size="small" @click="submitForm('ruleForm')" :loading="isLoading">确定</el-button>
    </div>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { addCarSchedule } from "@/api/carSchedule";
import { listCar } from "@/api/car";
import { getFaOutProductOrderList } from "@/api/faOutProductOrder";

export default {
  mixins: [btn],
  data() {
    return {
      value1: "",
      value2: "",
      isLoading: false,
      ruleForm: {
        carId: "",
        outProductOrderId: "",
        startTime: undefined,
        endTime: undefined
      },
      carList: [],
      outProductOrderList: [],
      rules: {
        carId: [{ required: true, message: "请输入", trigger: "blur" }],
        outProductOrderId: [
          { required: true, message: "请输入", trigger: "blur" }
        ],
        startTime: [{ required: true, message: "请输入", trigger: "blur" }],
        endTime: [{ required: true, message: "请输入", trigger: "blur" }]
      }
    };
  },
  mounted() {
    listCar({}).then(res => {
      if (res.code === "0") {
        this.carList = res.data;
      }
    });
    getFaOutProductOrderList({}).then(res => {
      if (res.code === "0") {
        this.outProductOrderList = res.data;
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
        car: {
          id: this.ruleForm.carId
        },
        outProductOrder: {
          id: this.ruleForm.outProductOrderId
        },
        startTime: this.ruleForm.startTime,
        endTime: this.ruleForm.endTime
      };
      console.log(data.startTime);
      this.$refs[formName].validate(valid => {
        if (valid) {
          addCarSchedule(data).then(res => {
            if (res.code === "0") {
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push("carSchedule");
            } else {
              this.$message.error(res.message);
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
    },
    toIndex() {
      this.$router.push("carSchedule");
    }
  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";

.main > .el-button {
  margin-bottom: 30px;
}

.page {
  position: relative;
  height: calc(100vh - 110px);
}

.bottom-buttons {
  width: calc(100% + 32px);
  padding: 10px;
  margin: -16px -16px 0px -16px;
  background: #ffffff;
  position: absolute;
  bottom: 0px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.detailList {
  height: 72px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  overflow-y: auto;
  background: #f5f7fa;
  margin-top: 19px;
  & > div:nth-of-type(1) {
    width: 33px;
    height: 64px;
    text-align: center;
    background: url("../../../assets/images/lable.png");
    background-size: 100% 100%;
    font-size: 12px;
    color: #f5b102;
    padding: 10px;
  }
  & > div:nth-of-type(2) {
    padding-left: 7px;
    & > div:nth-child(1) {
      font-size: 14px;
      font-family: PingFangSC-Medium, PingFang SC;
      font-weight: 500;
      color: rgba(51, 51, 51, 1);
    }
    & > div:nth-child(2) {
      font-size: 12px;
      font-family: PingFangSC-Regular, PingFang SC;
      font-weight: 400;
      color: rgba(153, 153, 153, 1);
      margin-top: 4px;
    }
  }
}
</style>
