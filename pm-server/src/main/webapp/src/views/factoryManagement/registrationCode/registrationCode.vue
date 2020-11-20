<template>
  <div style="padding-bottom:16px">
    <div class="main">
      <div class="main-top" style="padding-left:10px">
        <el-form>
          <search-form>
            <el-form-item slot="first">
              <el-input
                placeholder="请输入卸货仓库"
                size="small"
                style="width:95%"
                v-model="warehouseName"
              ></el-input>
            </el-form-item>
            <el-form-item slot="second">
              <el-input placeholder="请输入送货单编号" size="small" style="width:95%" v-model="sendOrderNo"></el-input>
            </el-form-item>
            <el-form-item slot="three">
              <el-input placeholder="请输入车牌号" size="small" style="width:95%" v-model="carNo"></el-input>
            </el-form-item>
          </search-form>
          <el-button
            style="float:right;margin:0 16px 16px 0;"
            type="primary"
            size="small"
            @click="search()"
            v-if="haveBtn('faWareHouseTagManage')"
          >搜索</el-button>
          <div style="clear:both"></div>
        </el-form>
      </div>

      <div class="roleManageTable">
        <lef :top="23"></lef>
        <div class="userEdit">
          <div style="width:200px;line-height:34px" class="subtit">叫号列表</div>
        </div>
        <el-tabs v-model="callNumberForm.tableName" @tab-click="switchTab">
          <el-tab-pane
            v-for="item in editableTabs"
            :key="item.name"
            :label="item.title"
            :name="item.name"
          ></el-tab-pane>
        </el-tabs>
        <el-table
          :data="tableData"
          style="width: 100%;height:auto"
          :height="tableHeight"
          :header-cell-style="tableHeaderColor"
        >
          <el-table-column type="index" width="60" align="center" label="序号"></el-table-column>
          <el-table-column prop="invoiceCode" label="送货单号" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="warehouse.name" label="仓库名称" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="registrationTime" label="登记时间" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="enterFaWaHouseTime" label="进厂时间" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="outFaWaHouseTime" label="出厂时间" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="sendTypeName" label="送货方式" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="driverPhone" label="司机手机" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="carNo" label="车牌号" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="statusName" label="送货单状态" :show-overflow-tooltip="true"></el-table-column>
          <el-table-column prop="operation" label="操作" fixed="right" align="right">
            <template slot-scope="scope">
              <el-dropdown trigger="click" v-if="haveBtn('discharge')">
                <span class="el-dropdown-link">
                  <el-tooltip content="操作" width="150" align="center" placement="top">
                    <svg-icon icon-class="line"></svg-icon>
                  </el-tooltip>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item
                    @click.native="call(scope.$index,tableData,)"
                    v-if="scope.row.status==3"
                  >叫号</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="enterStorage(scope.$index,tableData)"
                    v-if="scope.row.status==6"
                  >进厂</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="discharge(scope.$index,tableData)"
                    v-if="scope.row.status==7"
                  >卸货</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="discharge(scope.$index,tableData)"
                    v-if="scope.row.status==9"
                  >修改卸货</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="reject(scope.$index,tableData)"
                    v-if="scope.row.status==7"
                  >拒收</el-dropdown-item>
                  <el-dropdown-item
                    @click.native="exitStorage(scope.$index,tableData)"
                    v-if="scope.row.status==7||scope.row.status==8||scope.row.status==9||scope.row.status==11"
                  >出厂</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- 拒收弹框 -->
    <el-dialog title="拒收" :visible.sync="dialogVisible" width="55%" :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rejectRules" label-width="80px">
        <el-form-item label="拒收原因" prop="reason">
          <el-select v-model="form.reason" placeholder="请选择拒收原因" style="width:100%">
            <el-option v-for="item in reasons" :label="item" :key="item" :value="item"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="详细信息" prop="desc">
          <el-input type="textarea" v-model="form.desc" placeholder="请描述拒收的具体信息" :rows="5"></el-input>
        </el-form-item>
        <div class="operation">
          <el-button type="primary" size="small" @click="submitRejectForm('form')">确 定</el-button>
          <el-button size="small" @click="rejectCancel()">取 消</el-button>
        </div>
      </el-form>
    </el-dialog>
    <!-- 拒收弹框 -->

    <!-- 带货弹框 -->
    <el-dialog title="带货" :visible.sync="goodTakenIsShow" width="55%" :before-close="handleClose">
      <el-form
        ref="goodTakenForm"
        :model="goodTakenForm"
        :rules="goodTakenRules"
        label-width="80px"
      >
        <el-form-item label="备注" prop="note">
          <el-input type="textarea" v-model="goodTakenForm.note" placeholder="请简单描述带货" :rows="5"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="fileList">
          <el-upload
            name="goodTakenImages"
            action="/api/common/upload"
            list-type="picture-card"
            :on-remove="handleRemove"
            limit="3"
            :on-preview="handlePictureCardPreview"
            :on-exceed="handleLimit"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="goodTakenForm.fileList"
            accept=".jpg, .jpeg, .png, .JPG, .JPEG, .bmp"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <div class="operation">
          <el-button type="primary" size="small" @click="goodTaken('goodTakenForm')">确 定</el-button>
          <el-button size="small" @click="cancelGoodTaken()">取 消</el-button>
        </div>
      </el-form>
    </el-dialog>
    <!-- 带货弹框 -->
    <el-dialog :visible.sync="imageIsShow">
      <img width="100%" :src="dialogImageUrl" alt />
    </el-dialog>
    <div class="paging">
      <div>显示第 1 至 10 项结果，共 {{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="size"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          small
        ></el-pagination>
      </div>
    </div>
  </div>
</template>
<script>
import searchForm from "@/components/commonTop/index";
import lef from "@/components/common/l_icon";

import {
  pageCallNumber,
  call,
  enterOrExitStorage,
  goodTaken,
  discharge,
  reject,
} from "@/api/callNumber";
export default {
  components: {
    searchForm,
    lef,
  },
  data() {
    return {
      tableHeight: window.innerHeight - 385,
      warehouseName: "",
      sendOrderNo: "",
      carNo: "",
      editableTabs: [
        {
          title: "全部",
          name: "3",
        },
        {
          title: "等待进厂",
          name: "0",
        },
        {
          title: "正在卸货",
          name: "1",
        },
        {
          title: "已离厂",
          name: "2",
        },
      ],
      callNumberForm: {
        tableName: "3",
      },
      registrationForm: {
        warehouse: {
          name: "",
        },
        invoiceCode: "",
        carNo: "",
      },
      pageNum: 0,
      pageSize: 10,
      tableData: [],
      num: 1,
      form: {
        deliveryId: "",
        reason: "",
        desc: "",
      },
      goodTakenForm: {
        deliveryId: "",
        note: "",
        fileList: [],
      },
      goodTakenIsShow: false,
      imageIsShow: false,
      dialogVisible: false,
      currentPage: 1,
      goodTakenRules: {
        note: [
          { required: true, message: "请描述带货信息", trigger: "blur" },
          {
            min: 1,
            max: 100,
            message: "长度在1到100个字符串",
            trigger: ["blur", "change"],
          },
        ],
        fileList: [{ required: true, message: "请上传照片", trigger: "blur" }],
      },
      dialogImageUrl: "",
      reasons: ["实物与送货单据不符", "多送", "少送", "送错", "其他"],
      rejectRules: {
        reason: [
          { required: true, message: "请选择拒收原因", trigger: "blur" },
        ],
        desc: [
          { required: true, message: "请输入拒收原因", trigger: "blur" },
          {
            min: 1,
            max: 100,
            message: "长度在1到100个字符串",
            trigger: ["blur", "change"],
          },
        ],
      },
    };
  },
  mounted() {
    this.switchTab({ name: this.callNumberForm.tableName, returnFlag: 1 });
  },
  methods: {
    switchTab(obj) {
      this.currentPage = 1;
      this.callNumberList(obj.name);
    },
    callNumberList(state) {
      pageCallNumber({}, this.pageNum, this.pageSize, state).then((res) => {
        let content = res.data.content;
        content.forEach((callnum) => {
          callnum["sendTypeName"] =
            callnum.sendType == 0
              ? "厢式货车"
              : callnum.sendType == 1
              ? "仓栅式货车"
              : "槽罐车";
        });
        this.tableData = content;
        this.totalPages = res.data.totalPages;
        this.size = res.data.totalPages * 10;
      });
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    handleCurrentChange(val) {
      this.num = val;
      let curr = val - 1;
      let data = {},
        pageNum = curr,
        pageSize = 10;
      pageCallNumber({}, pageNum, pageSize, this.callNumberForm.tableName).then(
        (res) => {
          let content = res.data.content;
          content.forEach((callnum) => {
            callnum["sendTypeName"] =
              callnum.sendType == 0
                ? "厢式货车"
                : callnum.sendType == 1
                ? "仓栅式货车"
                : "槽罐车";
          });
          this.tableData = content;
          this.totalPages = res.data.totalPages;
          this.size = res.data.totalPages * 10;
        }
      );
    },
    search() {
      this.currentPage = 1;
      let pageNum = 0,
        pageSize = 10;
      this.registrationForm.warehouse.name = this.warehouseName;
      this.registrationForm.invoiceCode = this.sendOrderNo;
      this.registrationForm.carNo = this.carNo;
      pageCallNumber(
        this.registrationForm,
        pageNum,
        pageSize,
        this.callNumberForm.tableName
      ).then((res) => {
        let content = res.data.content;
        content.forEach((callnum) => {
          callnum["sendTypeName"] =
            callnum.sendType == 0
              ? "厢式货车"
              : callnum.sendType == 1
              ? "仓栅式货车"
              : "槽罐车";
        });
        this.tableData = content;
        this.totalPages = res.data.totalPages;
        this.size = res.data.totalPages * 10;
      });
    },
    call(index, rows) {
      let deliveryId = rows[index].id;
      this.$confirm("是否叫号", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        call(deliveryId).then((res) => {
          this.$message({
            message: "叫号成功",
            type: "success",
          });
          this.callNumber();
        });
      });
    },
    enterStorage(index, rows) {
      let deliveryId = rows[index].id;
      //&&(scope.row.registration.state==4||scope.row.registration.state==6)
      if (
        rows[index].status == 6 &&
        (rows[index].registration.state == 4 ||
          rows[index].registration.state == 6)
      ) {
        this.$confirm("是否进厂", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }).then(() => {
          enterOrExitStorage(deliveryId, 5, 7).then((res) => {
            this.$message({
              message: "进厂成功",
              type: "success",
            });
            this.callNumber();
          });
        });
      } else {
        this.$message({
          message: "送货车辆正在别的仓库卸货，不能进厂",
          type: "error",
        });
      }
    },
    exitStorage(index, rows) {
      let deliveryId = rows[index].id;
      this.$confirm("是否出厂", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => {
        this.$confirm("是否带货", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.goodTakenForm.deliveryId = rows[index].id;
            this.goodTakenIsShow = true;
          })
          .catch(() => {
            this.exit(deliveryId);
          });
      });
    },
    exit(deliveryId) {
      enterOrExitStorage(deliveryId, 6, 10).then((res) => {
        this.$message({
          message: "出厂成功",
          type: "success",
        });
        this.callNumber();
      });
    },
    callNumber() {
      pageCallNumber(
        {},
        this.pageNum,
        this.pageSize,
        this.callNumberForm.tableName
      ).then((res) => {
        let content = res.data.content;
        content.forEach((callnum) => {
          callnum["sendTypeName"] =
            callnum.sendType == 0
              ? "厢式货车"
              : callnum.sendType == 1
              ? "仓栅式货车"
              : "槽罐车";
        });
        this.tableData = content;
        this.totalPages = res.data.totalPages;
        this.size = res.data.totalPages * 10;
      });
    },
    discharge(index, rows) {
      var item = rows[index];
      this.$router.push({
        path: "/discharge",
        query: {
          item: item.id,
        },
      });
      localStorage.setItem("delivery", JSON.stringify(item));
    },
    reject(index, rows) {
      this.form.deliveryId = rows[index].id;
      this.dialogVisible = true;
    },
    // 拒收
    submitRejectForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            id: this.form.deliveryId,
            note: this.form.reason + "-" + this.form.desc,
          };
          reject(data).then((res) => {
            if (res.code === "0") {
              this.rejectCancel();
              this.callNumber();
            }
          });
        }
      });
    },
    rejectCancel() {
      this.dialogVisible = false;
      this.form.reason = "";
      this.form.desc = "";
      this.form.deliveryId = "";
    },
    handleClose(done) {
      done();
      this.form.reason = "";
      this.form.desc = "";
      this.form.deliveryId = "";
      this.goodTakenForm.note = "";
      this.goodTakenForm.fileList = [];
    },
    //取消带货弹框，信息清空
    cancelGoodTaken() {
      this.goodTakenIsShow = false;
      this.goodTakenForm.note = "";
      this.goodTakenForm.fileList = [];
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    //上传照片预览
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.imageIsShow = true;
    },
    //设置照片限制
    handleLimit(file, fileList) {
      this.$message({
        message: "图片不得超过3张",
        icon: "error",
      });
    },
    handleSuccess(file, fileList) {
      this.goodTakenForm.fileList.push(file.data);
    },
    goodTaken(formName) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let data = {
            deliveryId: this.goodTakenForm.deliveryId,
            note: this.goodTakenForm.note,
            imagePath: this.goodTakenForm.fileList,
          };
          goodTaken(data).then((res) => {
            this.exit(this.goodTakenForm.deliveryId);
            this.goodTakenIsShow = false;
            this.goodTakenForm = {
              registrationCodeId: "",
              note: "",
              orderId: "",
            };
            loading.close();
            this.goodTakenForm.fileList = [];
          });
        }
      });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.wareHouseManageTable {
  background-color: white;
  margin-top: 16px;
}
.main >>> .el-tabs__header {
  margin: 0px !important;
}
.main >>> .el-tabs__nav-scroll {
  margin-bottom: 1px;
  background: white;
  padding-left: 16px;
}
</style>
