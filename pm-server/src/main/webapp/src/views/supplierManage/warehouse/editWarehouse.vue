<template>
  <div>
    <lef :top="15"></lef>
    <con>
      <div class="main-top">
        <div style="margin:0 0 16px 0" class="subtit">{{ $t('route.addWarehouse') }}</div>
        <el-form
          ref="external_warehouse_form"
          :model="formObj"
          :rules="rules"
          label-width="100px"
          class="external-warehouse-form"
        >
          <el-form-item :label="$t('supMana.warehouse.name')" prop="name" size="small">
            <el-input v-model="formObj.name" maxlength="30" style="width:100%" />
          </el-form-item>
          <el-form-item :label="$t('supMana.warehouse.address')" prop="address" size="small">
            <el-input id="addr-search" v-model="formObj.address" maxlength="30" style="width:100%" />
          </el-form-item>
          <el-form-item label="经度" prop="longitude" size="small">
            <el-input v-model="formObj.longitude" disabled></el-input>
          </el-form-item>
          <el-form-item label="纬度" prop="latitude" size="small">
            <el-input v-model="formObj.latitude" disabled></el-input>
          </el-form-item>
          <el-form-item :label="$t('supMana.warehouse.phone')" prop="phone" size="small">
            <el-input v-model="formObj.phone" maxlength="11" style="width:100%" />
          </el-form-item>
          <el-form-item :label="$t('supMana.warehouse.supplier')" prop="suppliers" size="small">
            <el-select v-model="formObj.suppliers" multiple placeholder="请选择" style="width:100%">
              <el-option
                v-for="item in suppliers"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-form>
        <div id="map-container"></div>
      </div>
    </con>
    <submit>
      <el-button size="small" @click="toIndex">{{ $t('permission.cancel') }}</el-button>
      <el-button
        size="small"
        @click="submitForm('external_warehouse_form')"
        type="primary"
      >{{ $t('permission.confirm') }}</el-button>
    </submit>
  </div>
</template>

<script>
  import { btn } from '@/components/minxs/button'
  import {
    findWarehouse,
    updateWarehouse,
    getWarehouse,
    getByName,
  } from '@/api/externalWarehouse'
  import { getOrganization } from '@/api/organization'
  import submit from '@/components/common/submitBtn'
  import con from '@/components/common/content'
  import { getSupplier } from '@/api/supplier'
  import lef from '@/components/common/l_icon'
  import { createMap } from '@/utils/mapUtil'

  export default {
    components: {
      submit,
      con,
      lef,
    },
    mixins: [btn],
    data() {
      const validateName = (rule, value, callback) => {
        getByName({
          id: this.$route.query.id,
          name: value,
        }).then((res) => {
          if (res.code != '0' && value != '') {
            return callback(new Error(res.message))
          } else {
            return callback()
          }
        })
      }
      return {
        formObj: {
          name: '',
          address: '',
          phone: '',
          description: '',
          longitude: '',
          latitude: '',
          suppliers: [],
        },
        suppliers: [],
        orgs: [],
        name: '',
        rules: {
          name: [
            { required: true, message: '请输入外仓名称', trigger: 'blur' },
            {
              validator: validateName,
              trigger: 'blur',
            },
          ],
          address: [
            { required: true, message: '请输入外仓地址', trigger: 'blur' },
          ],
          phone: [
            { required: true, message: '请输入外仓联系电话', trigger: 'blur' },
            {
              min: 11,
              max: 11,
              message: '手机号码长度在11位',
              trigger: ['blur', 'change'],
            },
          ],
          suppliers: [
            { required: true, message: '请选择供应商', trigger: 'blur' },
          ],
          longitude: [{ required: true, message: '请输入经度', trigger: 'blur' }],
          latitude: [{ required: true, message: '请输入纬度', trigger: 'blur' }],
        },
      }
    },
    mounted() {
      const id = this.$route.query.id
      this.getSuppliers()
      findWarehouse(id).then((res) => {
        this.formObj = res.data        
        this.formObj.suppliers = res.data.suppliers.map(s => s.id)

        createMap({
          el: 'map-container',
          searchInput: 'addr-search',
          location: [res.data.longitude, res.data.latitude]
        }, this.locationChangeListener)
      })
      
    },
    methods: {
      locationChangeListener(e){
        this.formObj.address = e.address   
        this.formObj.longitude = e.lng
        this.formObj.latitude = e.lat
      },
      getSuppliers() {
        getSupplier().then((res) => {
          if (res.code === '0') {
            this.suppliers = res.data
          }
        })
      },
      submitForm(formName) {
        let warehouse = {}
        warehouse = this.formObj
        warehouse.suppliers = this.formObj.suppliers.map((supplierId) => {
          return { id: supplierId }
        })
        this.$refs[formName].validate((valid) => {
          if (valid) {
            updateWarehouse(warehouse).then((res) => {
              if (res.code === '0') {
                this.$message({
                  message: '修改成功',
                  type: 'success',
                })
                this.$router.push('/warehouse')
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },
      toIndex() {
        this.$router.push('/warehouse')
      },
    },
  }
</script>

<style lang="scss" scoped>
@import '~@/styles/systemStyle/addUser.scss';
.main >>> .el-button {
  margin-bottom: 30px;
}
.main-top {
  background: white;
  padding: 16px;
  min-height: calc(100vh - 132px);
}
.external-warehouse-form {
  width: 600px;
  float: left;
  padding-right: 20px;
}
#map-container {
  width: calc(100% - 600px);
  height: 500px;
  float: right;
}
.roleManageTable {
  background: white;
  margin-top: 16px;
  padding: 16px;
}
.box {
  padding: 0;
}
</style>
