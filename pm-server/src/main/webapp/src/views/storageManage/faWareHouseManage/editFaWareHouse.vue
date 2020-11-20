<template>
  <div class="main">
    <div id="container" style="float:left; width:calc(100% - 385px); height:100%"></div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
      style="width:384px;overflow-y:auto;height:calc(100vh - 122px);float:right;margin:0;"
    >
      <el-input
        id="tipinput"
        v-model="ruleForm.searchWord"
        placeholder="请输入关键字"
        style="width:260px;position:absolute;left:40px;top:40px"
      ></el-input>
      <div style="font-weight:700;margin:30px 0px 30px 20px;font-size:14px">编辑内仓</div>
      <el-form-item label="内仓名称" prop="name" size="small">
        <el-input v-model="ruleForm.name"></el-input>
      </el-form-item>
      <el-form-item label="内仓面积" prop="area" size="small">
        <el-input v-model="ruleForm.area"></el-input>
      </el-form-item>
      <el-form-item label="内仓地址" prop="address" size="small">
        <el-input v-model="ruleForm.address"></el-input>
      </el-form-item>
      <el-form-item label="仓库类型" prop="type" size="small">
        <el-select v-model="ruleForm.type">
          <el-option label="成品仓库" :value="0"></el-option>
          <el-option label="物料仓库" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="经度" prop="longitude" size="small">
        <el-input v-model="ruleForm.longitude" disabled></el-input>
      </el-form-item>
      <el-form-item label="纬度" prop="latitude" size="small">
        <el-input v-model="ruleForm.latitude" disabled></el-input>
      </el-form-item>
      <el-form-item label="所属组织" prop="orgList" size="small">
        <el-select
          v-model="ruleForm.orgList"
          placeholder="请选择"
          style="width:100%"
          multiple
          collapse-tags
        >
          <el-option
            v-for="item in organizationOps"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属用户" prop="userList" size="small">
        <el-select
          v-model="ruleForm.userList"
          placeholder="请选择"
          style="width:100%"
          multiple
          collapse-tags
        >
          <el-option v-for="item in users" :key="item.id" :label="item.realName" :value="item.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="是否满仓" prop="isFull" size="small">
        <el-select v-model="ruleForm.isFull">
          <el-option label="是" :value="1"></el-option>
          <el-option label="否" :value="0"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否起用" prop="isOpenBind" size="small">
        <el-switch v-model="ruleForm.isOpenBind"></el-switch>
      </el-form-item>
      <el-form-item class="btn">
        <el-button @click="toWareHouseManage" size="small" :class="{changBg:ischange == 2}">取消</el-button>
        <el-button
          @click="submitForm('ruleForm'),changBg(1)"
          size="small"
          :class="{changBg:ischange == 1}"
        >确定</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { btn } from "@/components/minxs/button";
import { getOrganization } from "@/api/organization";
import { getUserList } from "@/api/user";
import {
  updateFaWarehouse,
  findFaWarehouse,
  transformFaWarehouseList,
  findByLnglat,
  getByName
} from "@/api/faWareHouse";
import AMap from "AMap";
export default {
  mixins: [btn],
  data() {
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
    const longitudeValidator = (rule, value, callback) => {
      if (!value) {
        return callback();
      } else {
        if (!/^\d+\.?\d*$/.test(value)) {
          return callback(new Error("经度必须是数字"));
        }
        return callback();
      }
    };
    const latitudeValidator = (rule, value, callback) => {
      if (!value) {
        return callback();
      } else {
        if (!/^\d+\.?\d*$/.test(value)) {
          return callback(new Error("纬度必须是数字"));
        }
        return callback();
      }
    };
    const areaValidator = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("请输入仓库面积"));
      } else {
        if (!/^\d+$/.test(value)) {
          return callback(new Error("仓库面积必须是整数"));
        }
        if (value.length > 11) {
          return callback(new Error("长度不能超过11位"));
        }
        return callback();
      }
    };
    return {
      organizationOps: [],
      users: [],
      ruleForm: {
        name: "",
        area: "",
        address: "",
        longitude: "",
        latitude: "",
        type: "",
        isFull: "",
        isOpen: null,
        isOpenBind: null,
        orgList: [],
        userList: [],
      },
      rules: {
        name: [
          { required: true, message: "请输入内仓名称", trigger: "blur" },
          { max: 32, message: "最大长度为32个字符", trigger: "blur" },
          { validator: validateName, trigger: "blur" },
        ],
        area: [
          //  {validator:areaValidator,trigger: "blur"}
          { required: true, message: "请输入仓库面积", trigger: "blur" },
          { max: 32, message: "最大长度为32个字符", trigger: "blur" },
        ],
        address: [
          { required: true, message: "请输入仓库地址", trigger: "blur" },
          { max: 64, message: "最大长度为64个字符", trigger: "blur" },
        ],
        type: [{ required: true, message: "请选择仓库类型", trigger: "blur" }],
        isFull: [
          { required: true, message: "请选择是否满仓", trigger: "blur" },
        ],
        // isOpen: [
        //   { required: true, message: "请选择是否起用", trigger: "blur" }
        // ],
        longitude: [{ validator: longitudeValidator, trigger: "blur" }],
        latitude: [{ validator: latitudeValidator, trigger: "blur" }],
        orgList: [{ required: true, message: "请选择组织", trigger: "blur" }],
        userList: [{ required: true, message: "请选择用户", trigger: "blur" }],
      },
    };
  },
  mounted() {
    getOrganization({}).then((res) => {
      this.organizationOps = res.data;
    });

    getUserList({}).then((res) => {
      this.users = res.data.filter((item) => item.id != "1");
    });
    let id = this.$route.query.id;
    this.find(id);
  },
  methods: {
    submitForm(formName) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this[formName].isOpenBind) {
            this[formName].isOpen = 1;
          } else {
            this[formName].isOpen = 0;
          }
          updateFaWarehouse(this[formName]).then((res) => {
            loading.close();
            this.$message({
              message: "修改成功",
              type: "success",
            });
            this.$router.push("/faWareHouseManage");
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
    toWareHouseManage() {
      this.$router.push("/faWareHouseManage");
    },
    find(id) {
      findFaWarehouse(id).then((res) => {
        if (res.code === "0") {
          console.log("res.data.isOpen", res.data.isOpen);
          if (res.data.isOpen) {
            res.data.isOpenBind = true;
          } else {
            res.data.isOpenBind = false;
          }
          console.log("res.data.isOpenBind", res.data.isOpenBind);
          this.ruleForm = res.data;

          var autoOptions = {
            input: "tipinput",
          };
          var auto = new AMap.Autocomplete(autoOptions);
          let initPosition = [120.280879, 31.536335];
          if (this.ruleForm.longitude && this.ruleForm.latitude) {
            initPosition = [this.ruleForm.longitude, this.ruleForm.latitude];
          }
          let map = new AMap.Map("container", {
            center: initPosition,
            resizeEnable: true,
            zoom: 10,
          });
          var geocoder = new AMap.Geocoder();
          var placeSearch = new AMap.PlaceSearch({
            // map: map
          });
          var marker = [];
          AMap.event.addListener(auto, "select", (e) => {
            map.remove(marker);
            placeSearch.setCity(e.poi.adcode);
            placeSearch.search(e.poi.name, (status, result) => {
              console.log("result", result);
              var pois = result.poiList.pois;
              for (let i = 0; i < pois.length; i++) {
                var poi = pois[i];
                marker[i] = new AMap.Marker({
                  position: poi.location, // 经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
                  title: poi.name,
                  draggable: true,
                  cursor: "move",
                  raiseOnDrag: true,
                });
                marker[i].on("click", (obj) => {
                  this.ruleForm.longitude = obj.lnglat.lng;
                  this.ruleForm.latitude = obj.lnglat.lat;
                  geocoder.getAddress(
                    [obj.lnglat.lng, obj.lnglat.lat],
                    (status, result) => {
                      if (status === "complete" && result.regeocode) {
                        this.ruleForm.address =
                          result.regeocode.formattedAddress;
                      } else {
                        log.error("根据经纬度查询地址失败");
                      }
                    }
                  );
                });
                marker[i].on("mouseup", (obj) => {
                  console.log(i);
                  this.ruleForm.longitude = obj.lnglat.lng;
                  this.ruleForm.latitude = obj.lnglat.lat;
                  geocoder.getAddress(
                    [obj.lnglat.lng, obj.lnglat.lat],
                    (status, result) => {
                      if (status === "complete" && result.regeocode) {
                        this.ruleForm.address =
                          result.regeocode.formattedAddress;
                      } else {
                        log.error("根据经纬度查询地址失败");
                      }
                    }
                  );
                });
                // 将创建的点标记添加到已有的地图实例：
                // marker[i].setMap(map);
                map.add(marker[i]);
                if (i == 0) {
                  this.ruleForm.longitude = poi.location.lng;
                  this.ruleForm.latitude = poi.location.lat;
                  // findByLnglat(poi.location.lng,poi.location.lat).then((res)=>{
                  //   this.ruleForm.address=res.data.regeocode.formatted_address;
                  // })
                  geocoder.getAddress(
                    [poi.location.lng, poi.location.lat],
                    (status, result) => {
                      if (status === "complete" && result.regeocode) {
                        console.log(result.regeocode.formattedAddress);
                        this.ruleForm.address =
                          result.regeocode.formattedAddress;
                      } else {
                        log.error("根据经纬度查询地址失败");
                      }
                    }
                  );
                }
              }
              map.setFitView();
            }); //关键字查询查询
          }); //注册监听，当选中某条记录时会触发

          map.on("click", (obj) => {
            map.remove(marker);
            let maker = new AMap.Marker({
              position: [obj.lnglat.lng, obj.lnglat.lat],
              draggable: true,
              cursor: "move",
              raiseOnDrag: true,
            });
            maker.setMap(map);
            this.ruleForm.longitude = obj.lnglat.lng;
            this.ruleForm.latitude = obj.lnglat.lat;
            // findByLnglat(obj.lnglat.lng,obj.lnglat.lat).then((res)=>{
            //             this.ruleForm.address=res.data.regeocode.formatted_address;
            //           })
            geocoder.getAddress(
              [obj.lnglat.lng, obj.lnglat.lat],
              (status, result) => {
                if (status === "complete" && result.regeocode) {
                  this.ruleForm.address = result.regeocode.formattedAddress;
                } else {
                  log.error("根据经纬度查询地址失败");
                }
              }
            );
            maker.on("mouseup", (obj) => {
              console.log(obj);
              this.ruleForm.longitude = obj.lnglat.lng;
              this.ruleForm.latitude = obj.lnglat.lat;
              geocoder.getAddress(
                [obj.lnglat.lng, obj.lnglat.lat],
                (status, result) => {
                  if (status === "complete" && result.regeocode) {
                    this.ruleForm.address = result.regeocode.formattedAddress;
                  } else {
                    log.error("根据经纬度查询地址失败");
                  }
                }
              );
            });
            marker.push(maker);
          });

          //初始化地图标记
          if (this.ruleForm.longitude && this.ruleForm.latitude) {
            map.remove(marker);
            let maker = new AMap.Marker({
              position: [this.ruleForm.longitude, this.ruleForm.latitude],
              title: this.ruleForm.address,
              draggable: true,
              cursor: "move",
              raiseOnDrag: true,
            });
            maker.setMap(map);
            geocoder.getAddress(
              [this.ruleForm.longitude, this.ruleForm.latitude],
              (status, result) => {
                if (status === "complete" && result.regeocode) {
                  this.ruleForm.address = result.regeocode.formattedAddress;
                } else {
                  log.error("根据经纬度查询地址失败");
                }
              }
            );
            // findByLnglat(this.ruleForm.longitude,this.ruleForm.latitude).then((res)=>{
            //             this.ruleForm.address=res.data.regeocode.formatted_address;
            //           })
            maker.on("mouseup", (obj) => {
              console.log(obj);
              this.ruleForm.longitude = obj.lnglat.lng;
              this.ruleForm.latitude = obj.lnglat.lat;
              geocoder.getAddress(
                [obj.lnglat.lng, obj.lnglat.lat],
                (status, result) => {
                  if (status === "complete" && result.regeocode) {
                    this.ruleForm.address = result.regeocode.formattedAddress;
                  } else {
                    log.error("根据经纬度查询地址失败");
                  }
                }
              );
              // findByLnglat(obj.lnglat.lng,obj.lnglat.lat).then((res)=>{
              //           this.ruleForm.address=res.data.regeocode.formatted_address;
              //         })
            });
            marker.push(maker);
          }
        }
      });
    },
  },
};
</script>

<style lang="scss" scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/sroll.scss";
.main >>> .el-textarea__inner {
  height: 88px;
}
.main >>> .el-button {
  margin-bottom: 10px;
}
.main >>> el-form-item {
  margin-bottom: 16px;
}
.btn {
  // position: fixed !important;
  // bottom: 16px !important;
  margin-bottom: 0px !important;
  margin-top: 34px;
}
.main {
  width: 100%;
  height: calc(100vh - 122px);
  background-color: #ffffff;
  & > div:nth-child(1) {
    padding: 16px;
    font-weight: 500;
    color: rgba(51, 51, 51, 1);
    font-size: 14px;
  }
  .changBg {
    background: #409eff !important;
    color: white;
  }
  form {
    width: 60%;
    margin: 10px 0px 0px 41px;
    .address {
      margin-top: 10px;
    }
    & >>> .el-input {
      width: 240px !important;
    }
    & >>> .el-select {
      width: 240px !important;
    }
    & >>> .el-button {
      width: 100px !important;
    }
  }
  &::-webkit-scrollbar {
    height: 0;
    width: 0;
    color: transparent;
  }
}
</style>