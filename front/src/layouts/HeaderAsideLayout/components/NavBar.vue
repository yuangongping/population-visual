<template>
  <el-menu class="navbar" mode="horizontal">
    <div class="logo" @click="goHome">
      <div class="logo-img"><img src="@/assets/logo.png"></div>
      <div class="logo-title"><span>人口可视化系统</span></div>
    </div>
    <div class="user-profile-container" trigger="click">
      <div class="user-profile-content">
        <el-dropdown>
          <div class="user-profile-body">
            <img class="user-avatar" src="https://img.alicdn.com/tfs/TB1ONhloamWBuNjy1XaXXXCbXXa-200-200.png">
            <span class="user-name">管理员</span>
          </div>
          <el-dropdown-menu class="user-dropdown" slot="dropdown">
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </el-menu>
</template>

<script>

export default {
  name: 'NavBar',
  data(){
    return {
      activeIndex: "1",
    }
  },
  methods: {
    goHome() {
      if(this.$route.path !== "/"){
        this.$router.push({path: "/"})
      }
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
  },
};
</script>

<style lang="scss" scoped>
$headerHeight: 60px;
.navbar {
  display: flex;
  height: $headerHeight;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  .logo{
    cursor: pointer;
    display: flex;
    height: 80%;
    margin-top: 5px;
    &-img{
      display: inline-flex;
      flex-direction: column;
      justify-content: center;;
      height: 100%;
      margin: 0 20px 0px 10px;
      img{
        height: 75%;
        width: auto;
      }
    }
    &-title{
      margin-top: 3px;
      align-self: center;
      font-size: 30px;
      font-family: "PingFangSC, PingFangSC-Semibold";
      font-weight: 600;
      text-align: left;
      color: #2c2d2e;
      line-height: 42px;
      letter-spacing: 1px;

    }
  }
  .menu{
    margin-left: 35%;
  }
  .user-profile-container {
    position: absolute;
    right: 20px;
    cursor: pointer;
    .user-profile-content {
      display: flex;
      padding: 20px 0;
    }
    .user-profile-body {
      position: relative;
      display: flex;
      flex-direction: row;
      align-items: center;
      justify-content: center;
      text-align: center;
      padding-right: 14px;
    }
    .user-avatar {
      width: 24px;
      height: 24px;
      margin: 0 8px 0 12px;
      border-radius: 4px;
    }
    .user-name {
      color: rgba(0,0,0,.65);
    }
    .user-department {
      font-size: 12px;
      color: rgba(102, 102, 102, 0.65);
    }
    .el-icon-caret-bottom {
      position: absolute;
      right: 0;
      top: 13px;
      font-size: 12px;
    }
  }
}
.el-menu--horizontal .el-menu .el-menu-item, .el-menu--horizontal .el-menu .el-submenu__title{
  float: left;
  padding: 10px 50px;
  height: $headerHeight;
}
</style>

