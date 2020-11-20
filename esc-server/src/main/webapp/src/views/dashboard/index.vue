<template>
  <div class="main">
    <el-row>
      <el-col :span="24">
        <tags></tags>
      </el-col>
       <el-col :span="18">
        <endLineMap></endLineMap>
      </el-col>
       <el-col :span="6">
        <carList></carList>
      </el-col>
      <el-col :span="15">
        <supplierLineChart></supplierLineChart>
      </el-col>
       <el-col :span="9">
        <agency></agency>
      </el-col>
      <el-col :span="24">
        <material></material>
      </el-col>
      <!-- <el-col :span="24">
        <taskList></taskList>
      </el-col> -->
    </el-row>
    <!-- <end-line-map style="margin-top:100px"></end-line-map> -->
  </div>
</template>

<script>
import tag from "./tag.vue";
import tags from "./tags.vue";
import endLineMap from "./endLineMap.vue";
import taskList from "./taskList.vue";
import supplierLineChart from "./supplierLineChart.vue";
import agency from "./agency.vue";
import material from "./material.vue";
import carList from "./carList.vue";
import { mapGetters } from "vuex";
import en from "element-ui/lib/locale/lang/en";
import { getcurrentUser } from "@/api/user";
import AMap from "AMap";
export default {
  components: {
    tag,
    tags,
    endLineMap,
    supplierLineChart,
    taskList,
    agency,
    carList,
    material
  },
  name: "Dashboard",
  computed: {
    ...mapGetters(["name"]),
  },
  methods: {},
  mounted() {
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.el-scrollbar {
  position: unset;
}
.main {
  padding-bottom: 16px;
}
</style>
