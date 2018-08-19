<template>
  <div class="login-container">
    <el-form class="login-form" autoComplete="off" :model="loginForm" :rules="loginRules" ref="loginForm" label-position="left">
      <div class="title-container">
        <h3 class="title">管理系统V1.0</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
         <i class="fa fa-user" aria-hidden="true"></i>
        </span>
        <el-input name="username" type="text" v-model="loginForm.account" autoComplete="off" placeholder="请输入账号"/>
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
        <i class="fa fa-key" aria-hidden="true"></i>
        </span>
        <el-input name="password" :type="passwordType" v-model="loginForm.password" autoComplete="off" placeholder="请输入密码"/>
        <span class="right-icon" @click="togglePassword">
         <i class="fa fa-eye" aria-hidden="true"></i>
        </span>
      </el-form-item>

      <el-form-item prop="captcha" style="width: 250px">
        <span class="svg-container">
        <i class="fa fa-picture-o" aria-hidden="true"></i>
        </span>
        <el-input name="captcha" @keyup.enter.native="handleLogin" v-model="loginForm.captcha" autoComplete="off" placeholder="请输入验证码"/>
        <img class="captcha" :src="captchaSrc" @click="refreshCaptcha"/>
        <span class="right-icon" style="right: -190px;font-size: 25px;">
         <i class="fa fa-refresh" aria-hidden="true" @click="refreshCaptcha"></i>
      </span>
      </el-form-item>


      <el-button type="primary" style="width:100%;margin-bottom:30px;" :loading="loading" @click.native.prevent="handleLogin">立即登录</el-button>

    </el-form>
  </div>
</template>

<script>
  import {account_captcha} from "@/api/account";
  import {Message} from 'element-ui'

  export default {
    name: 'login',
    data() {
      const validateUsername = (rule, value, callback) => {
        if (!value) {
          callback(new Error('用户名不能为空'))
        }
        else {
          callback()
        }
      }
      const validatePassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('密码不能为空'))
        }
        else {
          callback()
        }
      }
      const validateCaptcha = (rule, value, callback) => {
        if (!value) {
          callback(new Error('验证码不能为空'))
        }
        else {
          callback()
        }
      }
      return {
        loginForm: {
          account: undefined,
          password: undefined,
          captcha: undefined
        },
        loginRules: {
          account: [{required: true, trigger: 'blur', validator: validateUsername}],
          password: [{required: true, trigger: 'blur', validator: validatePassword}],
          captcha: [{required: true, trigger: 'blur', validator: validateCaptcha}]
        },
        passwordType: 'password',
        loading: false,
        captchaSrc: undefined
      }
    },
    methods: {
      togglePassword() {
        if (this.passwordType === 'password') {
          this.passwordType = ''
        } else {
          this.passwordType = 'password'
        }
      },
      refreshCaptcha() {
        this.captchaSrc = account_captcha();
      },
      //有后台交互
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (!valid) {
            return false;
          }
          this.loading = true
          this.$store.dispatch('login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({path: '/'})
          }).catch(() => {
            this.loading = false
          })
        })
      }
    },
    created() {
      this.refreshCaptcha();
      console.log("login created")
    },
    mounted() {
      console.log("login mounted")
    },
    destroyed() {
      console.log("login destroyed")
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss">
  $bg: #2d3a4b;
  $light_gray: #eee;

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        &:-webkit-autofill {
          -webkit-box-shadow: 0 0 0px 1000px $bg inset !important;
          -webkit-text-fill-color: #fff !important;
        }
      }
    }
    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: #454545;
    }
  }
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    position: fixed;
    height: 100%;
    width: 100%;
    background-color: $bg;
    .login-form {
      position: absolute;
      left: 0;
      right: 0;
      width: 520px;
      padding: 35px 35px 15px 35px;
      margin: 120px auto;
    }
    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;
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
      .title {
        font-size: 26px;
        font-weight: 400;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }
    .right-icon {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
    img.captcha {
      position: absolute;
      right: -160px;
      top: 0px;
      width: 150px;
      height: 52.5px;
      border: 1px solid rgba(255, 255, 255, 0.1);
      border-radius: 5px;
    }

  }
</style>
