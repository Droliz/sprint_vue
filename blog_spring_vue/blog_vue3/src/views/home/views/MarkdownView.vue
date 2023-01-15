<template>
  <el-tabs class="demo-tabs">
    <el-tab-pane label="markdown">
<!--      <v-md-editor-->
<!--          v-model="text"-->
<!--          height="100vh"-->
<!--          class="edit"-->
<!--          left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code | save upload"-->
<!--          @save="saveFile"-->
<!--          preview-class="vuepress-markdown-body"-->
<!--          :toolbar="toolbar"></v-md-editor>-->
      <blog-write/>
    </el-tab-pane>
    <el-tab-pane label="临时链接">Role</el-tab-pane>
  </el-tabs>

<!--  <el-dialog v-model="centerDialogVisible" title="" width="20%" center>-->
<!--    <template #footer>-->
<!--      <el-dropdown class="select" @command="handleCommand">-->
<!--        <el-button type="primary">-->
<!--          选择下载类型<i-ep-arrowDown />-->
<!--        </el-button>-->
<!--        <template #dropdown>-->
<!--          <el-dropdown-menu>-->
<!--            <el-dropdown-item command=".md">markdown</el-dropdown-item>-->
<!--            <el-dropdown-item command=".html">html</el-dropdown-item>-->
<!--          </el-dropdown-menu>-->
<!--        </template>-->
<!--      </el-dropdown>-->
<!--    </template>-->
<!--  </el-dialog>-->
</template>

<script setup lang="ts">
import BlogWrite from '../components/BlogWrite.vue'

</script>

<style scoped>

.select {
  margin: 0 auto 10px;
}

.demo-tabs {
  height: 200px;
  margin: 20px 20px 50px 10px;
}
</style>


