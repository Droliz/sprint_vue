<template>
  <el-dropdown>
    <span class="el-dropdown-link">
      年度
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item>年度</el-dropdown-item>
        <el-dropdown-item>月度</el-dropdown-item>
        <el-dropdown-item>周度</el-dropdown-item>
        <el-dropdown-item>日度</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>

  <!-- 归档 -->
  <el-timeline class="time-line-body">
    <el-timeline-item timestamp="2018/4/12" placement="top">
      <el-card>
        <h4>Update Github template</h4>
        <p>Tom committed 2018/4/12 20:46</p>
      </el-card>
    </el-timeline-item>
    <el-timeline-item timestamp="2018/4/3" placement="top">
      <el-card>
        <h4>Update Github template</h4>
        <p>Tom committed 2018/4/3 20:46</p>
      </el-card>
    </el-timeline-item>
    <el-timeline-item timestamp="2018/4/2" placement="top">
      Event start
    </el-timeline-item>
    <el-timeline-item timestamp="2018/4/2" placement="top">
      Event end
    </el-timeline-item>
  </el-timeline>
</template>

<script setup lang="ts">
import BlogCard from '../components/BlogCard.vue';
import {ref} from "vue";

// 分类
const type = ref();

</script>

<style scoped>
.el-dropdown {
  margin: 30px 50px 0;
  font-size: 30px;
  font-weight: 700;
  color: #1a1a1a;
}

.el-dropdown-link {
  cursor: pointer;
  display: flex;
  align-items: center;
}

.time-line-body {
  margin: 50px;
  color: #3e3e3e;
  /*line-height: 1.8; !*更改默认的行距*!*/
  /*word-spacing:2px; !*更改默认的词间距*!*/
  /*letter-spacing:2px;!*更改默认的字符间距*!*/
  /*font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;*/
}



</style>