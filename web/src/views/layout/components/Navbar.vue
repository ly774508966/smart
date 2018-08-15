<template>
  <el-menu class="navbar" mode="horizontal">
    <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>

    <breadcrumb class="breadcrumb-container"></breadcrumb>

    <div class="right-menu">
      <el-tooltip effect="dark" content="全屏" placement="bottom">
        <screenfull class="screenfull right-menu-item"></screenfull>
      </el-tooltip>
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <div class="avatar-wrapper">
          <img class="user-avatar" :src="avatar+'?imageView2/1/w/80/h/80'">
          <span class="user-name right-menu-item">张三</span>
          <i class="el-icon-arrow-down"></i>
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>
            <div @click="showUserInfo">个人信息</div>
          </el-dropdown-item>
          <el-dropdown-item>
            <div @click="showModifyPwd">修改密码</div>
          </el-dropdown-item>
          <el-dropdown-item divided>
            <div @click="logout">退出登录</div>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <el-dialog title="修改密码" width="600px" :visible.sync="userModifyPwdDialogVisible">
      <el-form size="mini" label-width="80px">
        <el-form-item label="原密码">
          <el-input placeholder="请输入原密码" type="password"></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input placeholder="请输入新密码" type="password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userModifyPwdDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="userModifyPwdDialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="个人信息" width="600px" :visible.sync="userInfoDialogVisible">
      <el-form size="mini" label-width="80px">
        <el-form-item label="头像">
          <el-tooltip effect="dark" content="点击可替换头像" placement="right-start">
            <img class="user-avatar" :src="avatar+'?imageView2/1/w/200/h/200'">
          </el-tooltip>
        </el-form-item>
        <el-form-item label="账号">
          <span>zhangsan</span>
        </el-form-item>
        <el-form-item label="角色">
          <span>系统管理员</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="userInfoDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="userInfoDialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>

  </el-menu>
</template>

<script>
  import { mapGetters } from 'vuex'
  import Breadcrumb from '@/components/Breadcrumb'
  import Hamburger from '@/components/Hamburger'
  import Screenfull from '@/components/Screenfull'
  import ElInput from '../../../../node_modules/element-ui/packages/input/src/input'

  export default {
    data() {
      return {
        userInfoDialogVisible: false,
        userModifyPwdDialogVisible: false
      }
    },
    components: {
      ElInput,
      Breadcrumb,
      Hamburger,
      Screenfull
    },
    computed: {
      ...mapGetters([
        'sidebar',
        'name',
        'avatar'
      ])
    },
    methods: {
      showUserInfo(){
        this.userInfoDialogVisible = true;
      },
      showModifyPwd(){
        this.userModifyPwdDialogVisible = true;
      },
      toggleSideBar() {
        this.$store.dispatch('toggleSideBar')
      },
      logout() {
        this.$store.dispatch('LogOut').then(() => {
          location.reload()// In order to re-instantiate the vue-router object to avoid bugs
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0px !important;
    .hamburger-container {
      line-height: 58px;
      height: 50px;
      float: left;
      padding: 0 10px;
    }
    .breadcrumb-container {
      float: left;
    }
    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }
    .right-menu {
      float: right;
      height: 100%;
      &:focus {
        outline: none;
      }
      .right-menu-item {
        display: inline-block;
        margin: 0 8px;
      }
      .screenfull {
        height: 20px;
        vertical-align: middle;
      }
      .international {
        vertical-align: top;
      }
      .theme-switch {
        vertical-align: 15px;
      }
      .avatar-container {
        height: 50px;
        /*margin-right: 30px;*/
        .avatar-wrapper {
          cursor: pointer;
          /*margin-top: 5px;*/
          position: relative;
          .user-avatar {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            vertical-align: middle;
          }
          .user-name {
            vertical-align: middle;
          }
        }
      }
    }
  }
</style>
