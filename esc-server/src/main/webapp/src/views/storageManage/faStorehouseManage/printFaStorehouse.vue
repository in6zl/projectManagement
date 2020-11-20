<template style="overflow-y: auto;">
  <div class="content">
    <div class="top">
      <div class="info">库位信息核对</div>
      <div>
        <el-button
          type="primary"
          size="small"
          v-print="'#print'"
        >打印</el-button>
        <el-button size="small" @click="cancel()"
          :class="{changBg:ischange == 2}">返回</el-button>
      </div>
    </div>
    <div id="print">
      <div
        class="prtTable"
        v-for="item in faStorehouseList"
        :key="item.id"
      >
        <table
          cellpadding="0"
          cellspacing="0"
          class="kuwei"
        >
          <tr>
            <td
              rowspan="4"
              :id="'qrcode'+item.id"
              ref="qrcode"
            >
            </td>
            <td class="name">内仓名称:</td>
            <td class="value">{{item.warehouseName}}</td>
          </tr>
          <tr>
            <td class="name">货架:</td>
            <td class="value">{{item.storageBinCode}}</td>
          </tr>
          <tr>
            <td class="name">库位编码:</td>
            <td class="value">{{item.code}}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>
<script>
import QRCode from "qrcodejs2";
export default {
  data() {
    return {
      faStorehouseList: JSON.parse(localStorage.getItem("faStorehouseList"))
    };
  },
  mounted() {
    this.faStorehouseList.forEach(faStorehouse => {
      this.QRCode = new QRCode("qrcode" + faStorehouse.id, {
        width: 100,
        height: 100,
        text: faStorehouse.id,
        colorDark: "#000",
        colorLight: "#fff"
      });
    });
  },
  methods: {
    cancel(){
      this.$router.push("/faStorehouseManage");
    }
  }
};
</script>
<style scoped>
@import "~@/styles/variables.scss";
@import "~@/styles/sroll.scss";
.top {
  display: flex;
  justify-content: space-between;
}
.content {
  width: 100%;
  height: 530px;
  overflow-y: auto;
  background: white;
  border: 1px solid #dddddd;
  padding: 30px;
}
.kuwei {
  margin-top: 16px;
}
.kuwei >>> td {
  width: 120px;
}
.name {
  text-align: right;
}
.value {
  padding-left: 6px;
}
</style>