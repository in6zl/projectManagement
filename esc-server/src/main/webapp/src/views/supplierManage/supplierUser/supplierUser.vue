<template>
  <div class="main" style="padding-bottom:16px">
    <div class="main-top" style="padding-left:10px">
      <el-form>
        <top>
          <el-form-item slot="first">
            <el-input
              :placeholder="$t('input.pleaseEnter')+$t('user.username')"
              size="small"
              style="width:95%"
              v-model="usernameValue"
            ></el-input>
          </el-form-item>
          <el-form-item slot="second">
            <el-input
              :placeholder="$t('input.pleaseEnter')+$t('user.phone')"
              size="small"
              style="width:95%"
              v-model="phoneValue"
            ></el-input>
          </el-form-item>
          <el-form-item slot="three">
            <el-button
              type="primary"
              size="small"
              style="float:right;margin:5px 16px 0 0"
              @click="search"
            >{{$t('button.search')}}</el-button>
          </el-form-item>
        </top>
        <div style="clear:both"></div>
      </el-form>
    </div>
    <div class="roleManageTable">
      <div
        style="height: 20px;margin-top:23px;width:2.5px;background-color: blue;float:left;border-radius:0px 3px 3px 0px"
      ></div>
      <div class="userEdit">
        <div style="line-height:34px;font-weight: 700;" class="subtit">供应商账号</div>
        <el-button
          size="small"
          @click="toAddUser(),changBg(1)"
          type="primary"
          v-if="haveBtn('user:add')"
        >{{$t('button.add')}}</el-button>
      </div>
      <div
        class="muchUser"
        :style="{height:userCardHeight}"
        v-infinite-scroll="load"
        style="overflow:auto"
      >
        <div class="userCard" v-for="(item,i) in userList" :key="i">
          <div class="userCard-head" v-if="userList.length > 0">
            <span>{{item.type==1? $t('user.normalUsers') : item.type==2? $t('user.supplier'): $t('user.superAdmin') }}</span>
            <div class="editIcon">
              <el-tooltip
                class="item"
                effect="dark"
                :content="$t('tip.edit')"
                placement="top"
                v-if="haveBtn('user:edit')"
                @click.native="updataCard(item.id)"
              >
                <svg-icon icon-class="edit"></svg-icon>
              </el-tooltip>
              <el-popover :ref="item.$index" v-model="item.visible" placement="top" width="180">
                <p>确定删除此数据吗</p>
                <div style="text-align: right; margin: 0">
                  <el-button size="mini" type="text" @click="item.visible = false">取消</el-button>
                  <el-button
                    :loading="delLoading"
                    type="primary"
                    size="mini"
                    @click="deleteCard(item.id)"
                  >确定</el-button>
                </div>
                <svg-icon slot="reference" v-if="haveBtn('user:delete')" icon-class="delete"></svg-icon>
              </el-popover>
            </div>
          </div>
          <div class="userCard-main">
            <div>{{item.username.substr(0,1).toUpperCase()}}</div>
            <div>
              <span>{{item.username}}</span>
              <span>{{item.phone}}</span>
              <span>供应商:{{item.supplierName}}</span>
              <!-- <span
                v-if="item.roleNames != null && item.roleNames.length > 9"
                class="user Card-roles"
              >
                {{$t('user.roles')}}：
                <el-tooltip class="item" effect="dark" placement="top" offset="550">
                  <div slot="content" style="width: 200px">{{ item.roleNames }}</div>
                  <span>{{item.roleNames}}</span>
                </el-tooltip>
              </span>
              <span v-else>
                {{$t('user.roles')}}：
                <el-tooltip class="item" effect="dark" placement="top">
                  <div slot="content">{{ item.roleNames }}</div>
                  <span>{{item.roleNames}}</span>
                </el-tooltip>
              </span>-->
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="paging">
      <div>显示第 1 至 12 项结果，共{{totalPages}}页</div>
      <div>
        <el-pagination
          layout="prev, pager, next"
          :total="totalElements"
          background
          small
          :page-size="pageParam.pageSize"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
        ></el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
import { page, deleteUser, searchUser } from "@/api/user";
import { findSupplier } from "@/api/supplier";
import lef from "@/components/common/l_icon";
import top from "@/components/commonTop/index";
import { format } from "echarts/lib/export";

export default {
  components: {
    lef,
    top,
  },
  data() {
    return {
      totalPages: "123",
      totalElements: "32432",
      count: 0,
      ischange: 1,
      userList: [],
      usernameValue: "",
      phoneValue: "",
      userCardHeight: window.innerHeight - 300 + "px",
      delLoading: false,
      pageParam: {
        pageNo: 0,
        pageSize: 12,
      },
      user: {
        username: "",
        phone: "",
        type: 2,
      },
      initData: [],
      loading: false,
      num: 1,
      currentPage: 1,
    };
  },
  mounted() {
    this.getUserList();
  },
  methods: {
    toAddUser() {
      this.$router.push("/addSupplierUser");
    },
    changBg(index) {
      this.ischange = index;
    },
    async getUserList() {
      let { data } = await page({
        pageParam: this.pageParam,
        type: 2,
      });
      this.totalPages = data.totalPages;
      let users = data.content;
      this.userList = users;
      this.totalElements = data.totalElements;
    },
    handleCurrentChange(cpage) {
      this.num = cpage;
      searchUser({
        username: this.user.username,
        phone: this.user.phone,
        type: 2,
        pageParam: {
          pageNo: cpage - 1,
          pageSize: 12,
        },
      })
        .then((res) => {
          if (res.code === "0") {
            this.userList = res.data.content;
            this.totalPages = res.data.totalPages;
            this.totalElements = res.data.totalElements;
            loading.close();
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    deleteCard(id) {
      this.delLoading = true;
      let userlist = this.userList;
      let index = 0;
      for (let i = 0; i < userlist.length; i++) {
        if (userlist[i].id === id) {
          index = i;
        }
      }
      deleteUser(id).then((res) => {
        if (res.code === "0") {
          userlist.splice(index, 1);
          let number =
            this.userList.length === 0 && this.num != 1
              ? this.num - 1
              : this.num;
          this.handleCurrentChange(number);
        } else {
          this.$message.error(res.message);
        }
        this.delLoading = false;
      });
    },
    updataCard(id) {
      this.$router.push({ path: "/editSupplierUser", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      let data = {};
      data.username = this.usernameValue;
      data.phone = this.phoneValue;
      data.type = 2;
      data.pageParam = {
        pageNo: 0,
        pageSize: 12,
      };
      this.user.username = this.usernameValue;
      this.user.phone = this.phoneValue;
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      searchUser(data)
        .then((res) => {
          if (res.code === "0") {
            this.userList = res.data.content;
            this.totalPages = res.data.totalPages;
            this.totalElements = res.data.totalElements;
            loading.close();
          }
        })
        .catch((err) => {
          console.log(res);
        });
    },
  },
};
</script>
<style lang='scss' scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/systemStyle/roleManage.scss";
</style>

<style scoped>
.changBg {
  background: #409eff !important;
  color: white;
  margin-bottom: 10px;
}
.userList {
  width: 100%;
  background: white;
  border-radius: 2px;
  overflow: hidden;
  margin-bottom: 24px;
  padding-bottom: 16px;
}
.userList > div:nth-child(1) {
  font-size: 14px;
  font-weight: 500;
  color: rgba(51, 51, 51, 1);
  margin-top: 16px;
  margin-left: 16px;
}
.muchUser {
  display: flex;
  flex-flow: wrap;
  justify-content: flex-start;
  align-content: flex-start;
  position: relative;
  /* left: -16px; */
  overflow-y: auto !important;
  background: white;
}
/* .userList > div:nth-child(2) {
 margin-top: 29px;
} */
.searchInput {
  padding-left: 22px;
  margin-top: 9px;
}
.userCard {
  border: 1px solid lightgray;
  width: 23.333%;
  height: 162px;
  background: rgba(255, 255, 255, 1);
  border-radius: 2px;
  margin-top: 1.5%;
  margin-left: 1.35%;
}
.svg-icon {
  width: 20px;
  height: 20px;
  color: #606266;
}
.svg-icon:hover {
  color: #2f7fe1 !important;
}
.userCard-head {
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  padding: 0 16px;
  background: white;
  border-bottom: 1px solid lightgray;
  justify-content: space-between;
}

.userCard-head > span:nth-child(1) {
  font-size: 12px;
  color: #999999;
  font-weight: 600;
}
.userCard-main {
  display: flex;
  justify-content: center;
  margin-left: 16px;
}
.userCard-main > div:nth-child(1) {
  width: 40px;
  height: 40px;
  background: rgba(242, 104, 86, 1);
  border-radius: 4px;
  text-align: center;
  line-height: 40px;
  color: white;
  font-size: 24px;
  font-weight: 700;
  margin-top: 16px;
}
.userCard-main > div:nth-child(2) {
  display: flex;
  flex: 1 2 150px;
  height: 100px;
  flex-direction: column;
  margin-left: 16px;
}
.userCard-main > div:nth-child(2) > span:nth-child(1) {
  margin-top: 25px;
  font-size: 16px;
  height: 16px;
}
.userCard-main > div:nth-child(2) > span:nth-child(2) {
  margin-top: 4px;
  font-size: 14px;
  font-weight: 400;
  display: block !important;
  height: 16px;
  color: rgba(51, 51, 51, 1);
}
.userCard-main > div:nth-child(2) > span:nth-child(3) {
  margin-top: 12px;
  color: #909399;
  font-size: 14px;
}
.userCard-roles {
  display: inline-block;
  white-space: nowrap;
  width: 150px;
  overflow: hidden !important;
  text-overflow: ellipsis !important;
}
/* .el-form {
  display: flex;
  flex-wrap: wrap;
} */
.el-form-item {
  height: 15px;
  margin-top: 14px;
}
.el-form-item:nth-of-type(3) {
  float: right;
}
</style>
<style lang="scss" scoped>
@import "~@/styles/sroll.scss";
@import "~@/styles/systemStyle/roleManage.scss";
$cardBgColor: #f26856, #f0a612, rgb(36, 41, 48), #2ba9dd, #6f70dc, #eb2f96,
  #3972b6, #f26856;
@function randomColor() {
  $index: floor(random(8));
  @return nth($cardBgColor, $index);
}
@for $i from 1 through 8 {
  .userCard:nth-child(#{$i + 1}) {
    .userCard-main {
      & > div:nth-child(1) {
        background-color: randomColor() !important;
      }
    }
  }
}
.loading {
  margin: 10px auto;
  color: gray;
  font-size: 14px;
  width: 100%;
  text-align: center;
}
.userEdit {
  // padding: 16px 0 0px 16px;
  background: white;
  margin-bottom: -10px;
}
.paging {
  border-top: 1px solid #ebeef5;
}
</style>
