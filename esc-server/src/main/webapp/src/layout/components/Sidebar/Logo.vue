<template>
  <div class="sidebar-logo-container" :class="{'collapse':collapse}">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapse" key="collapse" class="sidebar-logo-link" to="/">
        <span v-if="logo">
          <svg-icon icon-class="logo"></svg-icon>
        </span>
        <h1 v-else class="sidebar-title">{{$t("sidebar.title")}}</h1>
      </router-link>
      <router-link v-else key="expand" class="sidebar-logo-link" to="/">
        <span v-if="logo">
          <svg-icon icon-class="logo"></svg-icon>
        </span>
        <h1 class="sidebar-title">{{$t("sidebar.title")}}</h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
export default {
  name: "SidebarLogo",
  props: {
    collapse: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      title: "末端供应链管理",
      logo: "logn"
    };
  }
};
</script>

<style lang="scss" scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 50px;
  line-height: 50px;
  background: #001b40;
  text-align: center;
  overflow: hidden;
  & .sidebar-logo-link {
    height: 100%;
    width: 100%;

    & .sidebar-logo {
      width: 25px;
      height: 25px;
      vertical-align: middle;
      margin-right: 12px;
    }

    & .sidebar-title {
      display: inline-block;
      margin: 0;
      color: rgba(255, 255, 255, 1);
      font-weight: 500;
      line-height: 50px;
      font-size: 18px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
      letter-spacing: 2px;
    }
  }

  &.collapse {
    .sidebar-logo {
      margin-right: 0px;
    }
    .svg-icon {
      margin-left: 5px;
    }
  }
  .svg-icon {
    width: 32px;
    height: 32px;
    vertical-align: middle;
    margin-left: -15px;
  }
}
</style>
