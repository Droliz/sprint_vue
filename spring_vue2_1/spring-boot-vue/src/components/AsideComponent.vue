<template>
    <el-menu
        router
        text-color="#fff"
        :collapse="isCollapse"
        background-color="#545c64"
        active-text-color="#ffd04b"
        :collapse-transition="false"
        :default-active='$route.path'>
      <el-menu-item index="/home/main">
        <i class="el-icon-s-home"></i>
        <span>首页</span>
      </el-menu-item>
      <el-menu-item index="/home/admin">
        <i class="el-icon-s-flag"></i>
        <span>管理员管理</span>
      </el-menu-item>
      <el-menu-item index="/home/user">
        <i class="el-icon-s-flag"></i>
        <span>用户管理</span>
      </el-menu-item>
      <el-menu-item index="/home/warehouse">
        <i class="el-icon-s-opportunity"></i>
        <span>仓库管理</span>
      </el-menu-item>
    </el-menu>
</template>

<script>
export default {
  name: "AsideComponent",
  computed: {
    isCollapse() {
      return this.$store.state.aside.isCollapse
    }
  },
}
</script>

<style scoped>
.el-menu {
  border-right: none;
}

.el-dropdown-menu__item, .el-menu-item {
  min-width: 200px;
}
</style>