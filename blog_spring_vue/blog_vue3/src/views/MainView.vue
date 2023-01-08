<template>
  <div class="common-layout">
    <el-container>
      <el-main>
        <router-view/>
      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>

<script setup lang="ts">
</script>

<style scoped>
.el-header {
  padding: 0;
}
.el-main {
  padding: 0;
  min-height: calc(100vh - 60px - 60px);
}
</style>
