<template>
  <el-container style="height: 100%; min-height: 100vh;">
    <!-- 侧边菜单 -->
    <el-aside width="auto">
      <aside-component/>
    </el-aside>

    <el-container style="height: 100%;">
      <!-- 头部 -->
      <el-header>
        <header-component/>
      </el-header>

      <!-- 主体 -->
      <el-main>
<!--        <main-view/>-->
        <router-view/>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
import AsideComponent from "@/components/AsideComponent";
import HeaderComponent from "@/components/HeaderComponent";
// import MainView from "@/views/Home/MainView";

export default {
  name: "IndexView",
  components: {
    AsideComponent,
    HeaderComponent,
    // MainView
  },
}
</script>

<style scoped>

/*侧边的样式*/
.as-main {
  height: 100%;
  min-height: 100vh;
  border-right: none;
}

.el-aside {
  /*min-width: 200px;*/
  color: #333;
  /*border-right: 1px solid #e3e3e3;*/
  background-color: #545c64;    /* 背景色 */
  overflow: hidden;
}

.el-header {
  /*background-color: #B3C0D1;*/
  color: #333;
  line-height: 60px;
  border-bottom: 2px solid rgba(168, 168, 168, .3);
}

.el-main {
  height: 100%;
  padding: 5px;
}

</style>