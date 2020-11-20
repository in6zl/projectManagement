<template>
  <div>
    <con>
      <lef :top="23"></lef>
      <div class="main-top">
        <div style="line-height:34px" class="subtit">系统设置
          <el-button
            size="small"
            @click="save()"
            type="primary"
            style="margin-bottom:10px;float:right"
            v-if="haveBtn('workshop:add')"
            v-loading.fullscreen.lock="fullscreenLoading"
          >{{$t('button.update')}}</el-button>
        </div>
        
        <el-form
          :model="paramsForm"
          :rules="rules"
          ref="paramsForm"
          label-width="120px"
        >
          <el-form-item label="需求冻结时间" prop="frozenRequire" size="mini">
            <el-input v-model.number="paramsForm.frozenRequire" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="调货冻结时间" prop="frozenTransfer" size="small">
            <el-input v-model.number="paramsForm.frozenTransfer" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="厂商地址" prop="address" size="small">
            <el-input id="addr-search" v-model="paramsForm.address" style="width:569px"></el-input>
          </el-form-item>
          <el-form-item label="经纬度" prop="location" size="small">
            <el-input v-model="paramsForm.location" style="width:569px" disabled></el-input>
          </el-form-item>
          <el-form-item size="small">
            <div id="map-container"></div>
          </el-form-item>
        </el-form>
        
    </div>
    </con>
  </div>
</template>
<script>
import { get, save } from "@/api/systemSetting";
import lef from "@/components/common/l_icon";
import con from "@/components/common/content";
import { createMap } from '@/utils/mapUtil'
export default {
  components: {
    lef,
    con
  },
  data() {
    return {
      fullscreenLoading: false,
      paramsForm: {
        frozenRequire: 0,
        frozenTransfer: 0,
        address: "",
        location: ""
      },
      rules: {
        frozenRequire: [
          { required: true, message: "请输入需求冻结时间", trigger: "blur" },
          {
            type: 'number',
            message: "请输入数字",
            trigger: ["blur", "change"],
          }
        ],
        frozenTransfer: [
          { required: true, message: "请输入调货计划冻结时间", trigger: "blur" },
          {
            type: 'number',
            message: "请输入数字",
            trigger: ["blur", "change"],
          }
        ],
        address: [
          { required: true, message: "请输入地址", trigger: "blur" },
          {
            min: 10,
            max: 128,
            message: "长度在10到128个字符串",
            trigger: ["blur", "change"],
          }
        ],
        location: [
          { required: true, message: "请输入经纬度", trigger: "blur" }
        ],
      }
    };
  },
  mounted() {
    get().then(res => {
      this.paramsForm = res.data || this.paramsForm
      let location = this.paramsForm.location? JSON.parse(this.paramsForm.location) : null
      
      createMap({
        el: 'map-container',
        searchInput: 'addr-search',
        location: location
      }, this.locationChangeListener)
    })
  },
  methods: {
    locationChangeListener(e){
      this.paramsForm.address = e.address
      this.paramsForm.location = `[${e.lng}, ${e.lat}]`
    },
    save() {
      this.$refs['paramsForm'].validate((valid) => {
        if (valid) {
          this.fullscreenLoading = true
          save(this.paramsForm).then(res => {
            if(res.code === '0') {
              this.$message({
                message: '更新成功',
                type: 'success'
              })
            }
          }).finally(() => {
            this.fullscreenLoading = false
          })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    }
  }
};
</script>
<style lang='scss' scoped>
.main-top {
  background: white;
  padding: 16px;
  min-height: calc(100vh - 132px);
}
#map-container {  
  width: 568px;
  height: 350px;
  border: 2px solid #E4E7ED;
}
</style>