<template>
  <div>
    <div class="main">
      <!-- <div class="main-top">
        <el-form label-width="100px">
          <search-form>
            <el-form-item :label="$t('resource.resourceName')" slot="first">
              <el-input
                :placeholder="$t('product.placeholder')"
                size="small"
                style="width:95%"
                v-model="name"
              ></el-input>
            </el-form-item>
            <el-form-item :label="$t('resource.url')" slot="second">
              <el-input
                :placeholder="$t('product.placeholder')"
                size="small"
                style="width:95%"
                v-model="url"
              ></el-input>
            </el-form-item>
            <el-form-item :label="$t('resource.type')" slot="three">
              <el-select
                v-model="type"
                :placeholder="$t('product.select')"
                size="small"
                clearable="true"
                style="width:95%"
              >
                <el-option
                  v-for="item in types"
                  :label="item.label"
                  :value="item.value"
                  :key="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </search-form>
          <el-form-item>
            <el-button
              style="float:right;margin:0 16px 16px 0;"
              type="primary"
              size="small"
              @click="search"
            >{{$t('button.search')}}</el-button>
          </el-form-item>
          <div style="clear:both"></div>
        </el-form>
      </div>-->
      <div class="roleManageTable">
        <div class="userEdit">
          <div style="line-height:34px" class="subtit">{{$t('resource.resourceList')}}</div>
          <el-button
            size="small"
            @click="toAddResource(),changBg(1)"
            :class="{changBg:ischange == 1}"
            v-if="haveBtn('resource:add')"
          >{{$t('button.add')}}</el-button>
        </div>
        <div class="tree-title">
          <div class="title-left">中文名称</div>

          <div class="title-right">操作</div>
        </div>
        <div ref="treeDiv" class="tree-container">
          <el-tree
            id="tree"
            class="tree"
            :expand-on-click-node="true"
            :indent="0"
            :data="data"
            ref="tree"
            @node-click="assignment"
          >
            <div
              class="custom-tree-node"
              slot-scope="{ node, data }"
              style="width: 100%; display: flex;flex-direction: row;align-items: center;justify-content: space-between;font-size:14px;font-family:PingFangSC-Regular,PingFang SC;font-weight:400;color:rgba(51,51,51,1);"
            >
              <span>{{ node.label }}</span>
              <span>
                <el-tooltip class="item" effect="dark" content="编辑" placement="top">
                  <el-button
                    size="mini"
                    type="text"
                    icon="el-icon-edit"
                    v-if="haveBtn('resource:edit')"
                    @click="() => edit(data)"
                    style="color:gray"
                  ></el-button>
                </el-tooltip>
                <el-tooltip class="item" effect="dark" content="删除" placement="top">
                  <el-popconfirm title="确定删除该资源吗？" @onConfirm="() => remove(node, data)">
                    <el-button
                      style="margin-left: 10px;color:gray"
                      v-if="haveBtn('resource:delete')"
                      slot="reference"
                      size="mini"
                      type="text"
                      icon="el-icon-delete"
                    ></el-button>
                  </el-popconfirm>
                </el-tooltip>
              </span>
            </div>
          </el-tree>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getResource,
  page,
  findResource,
  list,
  deleteResource,
  deleteRoleResource,
} from "@/api/resource";
import { getResourceTree } from "@/api/role";
import searchForm from "@/components/commonTop/index";
export default {
  components: {
    searchForm,
  },
  data() {
    return {
      data: [], //资源树数据源
      tableHeight: window.innerHeight - 350,
      index: 1,
      ischange: 1,
      tableData: [],
      name: "",
      url: "",
      totalElements: "",
      totalPages: "",
      types: [
        {
          label: "菜单",
          value: "0",
        },
        {
          label: "按钮",
          value: "1",
        },
      ],
      type: "",
      parentNames: [],
      size: "",
      flag: false,
      seachForm: {
        name: "",
        url: "",
        type: "",
        pageParam: { pageNo: 0, pageSize: 10 },
      },
      currentPage: 1,
      num: 1,
      dataTemp: {},
    };
  },
  mounted() {
    this.resourceList();
  },
  methods: {
    assignment(data) {
      this.dataTemp = data;
    },
    edit(data) {
      this.$router.push({
        path: "/editResource",
        query: { id: data.id },
      });
    },
    details(data) {
      this.$router.push({
        path: "/resourceManage/detail",
        query: { id: data.id },
      });
    },

    remove(node, data) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      deleteResource(data.id).then((res) => {
        if (res.code === "0") {
          this.$message({
            message: "删除成功",
            type: "success",
          });
          this.getResourceTree();
        } else {
          this.$message.error(res.message);
        }
      });
      setTimeout(() => {
        loading.close();
      }, 2000);
    },
    tableHeaderColor({ row, column, rowIndex, columnIndex }) {
      if (rowIndex === 0) {
        return "background-color: #FAFAFA;color: #999999;font-weight: 500;";
      }
    },
    toAddResource() {
      this.$router.push("/addResource");
    },
    changBg(index) {
      this.ischange = index;
    },
    resourceList() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      getResourceTree().then((res) => {
        if (res.code == 0) {
          this.data = this.filterChild(res.data);
          loading.close();
        } else {
          loading.close();
        }
      });
    },
    filterChild(data) {
      if (data.length == 0) return;
      data.map((item) => {
        item.label = this.$t("route." + item.name);
        if (item.children) {
          switch (item.children.length) {
            case 0:
              break;
            default:
              this.filterChild(item.children);
          }
        }
      });
      return data;
    },
    handleCurrentChange(cpage) {
      this.num = cpage;
      this.seachForm.pageParam.pageNo = cpage - 1;
      page(this.seachForm).then((res) => {
        if (res.code === "0") {
          let data = res.data;
          let content = data.content;
          list({}).then((res) => {
            let list = res.data;
            content.forEach((resource) => {
              resource["show"] = resource.type === 0 ? "是" : "否";
              list.forEach((item) => {
                if (resource.parentId === "0") {
                  resource["parentName"] = "";
                } else if (item.id === resource.parentId) {
                  resource["parentName"] = item.name;
                }
              });
              this.tableData = content;
              this.totalElements = data.totalElements;
              this.totalPages = data.totalPages;
              this.size = data.totalPages * 10;
            });
          });
        }
      });
    },
    deleteRow(index, rows) {
      this.$confirm("确定删除此数据吗", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          deleteRoleResource({
            resource: {
              id: rows[index].id,
            },
          }).then((res) => {
            if (res.code === "0") {
              deleteResource(rows[index].id).then((res) => {
                if (res.code === "0") {
                  this.$message({
                    message: "删除成功",
                    type: "success",
                  });
                  rows.splice(index, 1);
                  let number = rows.length === 0 ? this.num - 1 : this.num;
                  this.handleCurrentChange(number);
                }
              });
            } else {
              this.$message.error(res.message);
            }
          });
        })
        .catch(() => {
          return;
        });
    },
    updataResource(index, rows) {
      let id = rows[index].id;
      this.$router.push({ path: "/editResource", query: { id: id } });
    },
    search() {
      this.currentPage = 1;
      this.seachForm.name = this.name;
      this.seachForm.type = this.type;
      this.seachForm.url = this.url;
      this.seachForm.pageParam.pageNo = 0;
      // const loading = this.$loading({
      //   lock: true,
      //   text: "Loading",
      //   spinner: "el-icon-loading",
      //   background: "rgba(0, 0, 0, 0.7)"
      // });
      page(this.seachForm).then((res) => {
        if (res.code === "0") {
          let data = res.data;
          let content = data.content;
          list({}).then((res) => {
            let list = res.data;
            content.forEach((resource) => {
              resource["show"] = resource.type === 0 ? "是" : "否";
              list.forEach((item) => {
                if (resource.parentId === "0") {
                  resource["parentName"] = "";
                } else if (item.id === resource.parentId) {
                  resource["parentName"] = item.name;
                }
              });
              this.tableData = content;
              this.totalElements = data.totalElements;
              this.totalPages = data.totalPages;
              this.size = data.totalPages * 10;
            });
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
</style>
<style scoped>
.el-button:focus {
  background-color: #409eff !important;
  color: #ffffff;
}
.roleManageTable {
  min-height: calc(100vh - 333px);
}
.tree /deep/ .el-tree-node__content {
  height: 40px;
  margin-right: 16px;
}
.tree /deep/ .el-tree-node {
  position: relative;
}

.tree /deep/ .el-tree-node__children {
  padding-left: 16px;
}

.tree /deep/ .el-tree-node :last-child:before {
  height: 40px;
}

.tree /deep/ .el-tree > .el-tree-node:before {
  border-left: none;
}

.tree-container /deep/ .el-tree > .el-tree-node:after {
  border-top: none;
}

.tree /deep/ .el-tree-node:before {
  content: "";
  left: -4px;
  position: absolute;
  right: auto;
  border-width: 1px;
}

.tree /deep/ .el-tree-node:after {
  content: "";
  left: -4px;
  position: absolute;
  right: auto;
  border-width: 1px;
}

.tree /deep/ .el-tree-node:before {
  border-left: 1px dotted #c0c4cc;
  bottom: 0px;
  height: 100%;
  top: -20px;
  width: 1px;
  z-index: 999;
}

.tree /deep/ .el-tree-node:after {
  border-top: 1px dotted #c0c4cc;
  height: 20px;
  top: 20px;
  width: 27px;
  z-index: 999;
}
.tree /deep/ .el-tree-node:before {
  border: none;
}

.custom-tree-node {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  font-family: PingFangSC-Regular, PingFang SC;
  font-weight: 400;
  color: rgba(51, 51, 51, 1);
}
.tree-title {
  width: 100%;
  height: 44px;
  padding: 0px 16px 0px 16px;
  background: #fafafa;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: justify;
  -ms-flex-pack: justify;
  justify-content: space-between;
}
.tree-title .title-left {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #999;
}
.tree-title .title-right {
  font-size: 14px;
  font-family: PingFangSC-Medium, PingFang SC;
  font-weight: 500;
  color: #999;
}
.main {
  height: calc(100vh - 120px) !important;
}
/* .roleManageTable /deep/.el-scrollbar__wrap {
  padding-right: 16px !important;
} */
.roleManageTable /deep/.el-scrollbar {
  height: calc(100vh - 354px);
  background: white;
}
.roleManageTable {
  padding-bottom: 16px;
}
</style>
