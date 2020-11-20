<template>
  <div class="login-container">
    <div class="login-container-right">
      <div class="right-title">
        <h1>工时管理平台</h1>
        <h3 style="font-weight:500;color:#C4E6FF;margin-top:-5px">工时管理</h3>
      </div>
    </div>
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <img src="@/assets/images/login-title.png" />
      </div>

      <el-form-item prop="username">
        <el-input
          ref="username"
          v-model="loginForm.username"
          :placeholder="$t('login.username')"
          name="username"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          :placeholder="$t('login.password')"
          name="password"
          tabindex="2"
          auto-complete="on"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:327px;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >{{$t("login.logIn")}}</el-button>
    </el-form>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import Layout from "@/layout";
import { constantRoutes } from "@/router/index";
import { getToken } from "@/utils/auth";
import { login, getcurrentUser } from "@/api/user";
import { setToken } from "@/utils/auth";
export default {
  name: "Login",
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("Please enter the correct user name"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error("The password can not be less than 6 digits"));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: "",
        password: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername }
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword }
        ]
      },
      loading: false,
      passwordType: "password",
      redirect: undefined
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },
    async currentUser() {
      await getcurrentUser().then(res => {
        localStorage.setItem("currentUser", JSON.stringify(res));
      });
      this.$router.push("/");
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (!valid) {
          return false;
        }
        this.loading = true
        this.$store.dispatch('user/login', this.loginForm).then(res => {
          this.$router.push("/")
          this.loading = false
        }).catch(e => {
          this.loading = false
        });
      });
    }
  }
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #fff;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}
.right-title {
  width: 100%;
  height: 196px;
  background: #2e85ff;
  color: rgba(255, 255, 255, 1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  letter-spacing: 2px;
  padding-left: 44px;
}
/* reset element-ui css */
.login-container {
  display: flex;
  .login-container-right {
    position: relative;
    width: 60%;
    background-color: white !important;
    background-image: url("../../assets/images/login-bg.png");
    background-repeat: no-repeat;
    background-size: cover;
    display: flex;
    align-items: center;
  }
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;
    color: #333333;
    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: #333333;
      height: 47px;
      // caret-color: $cursor;
      border-bottom: 1px solid #e6e8ed;
      width: 327px;
      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: #333333 !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #ffffff;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    height: 100vh;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: -60px;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;
    bottom: 53px;
    img {
      width: 90px;
      height: 91px;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
