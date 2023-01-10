<template>
  <el-card
      @click="blogPage(blog.blog_id)"
      shadow="hover"
      class="box-card">
    <template #header>
      <div class="card-header">
        <span class="header-title">{{ blog.title }}</span>
        <el-tag
            size="small"
            class="header-tag"
            v-for="tag in blog.tags"
            :color="tagColorMap[1][tagColorMap[0].findIndex(i => i===tag)]"
            :style="{color: '#fff', fontWeight: 700}">
          {{ tag }}
        </el-tag>
      </div>
    </template>
    <div class="card-body">
      <div class="card-body-img">

      </div>
      <div class="card-body-content">
        <span>{{ blog.content.slice(0, 200) }}</span>
        <div class="info">
          <span>作者：{{ blog.author }}</span>
          <span>点赞</span>
          <span>踩</span>
          <span>更新时间：{{ blog.update_time }}</span>
        </div>
      </div>
    </div>
  </el-card>
</template>

<script setup lang="ts">
import {useRouter} from "vue-router";

const props = defineProps<{blog: Blog_tag, tagColorMap: string[][]}>()

const {blog, tagColorMap} = props

const router = useRouter();

// 跳转 blog 页面
const blogPage = (blog_id: number): void => {
  router.push({
    name: 'blogPage',
    params: {
      id: blog_id
    }
  });
};
</script>

<style scoped>
.box-card {
  /*width: 100%;*/
  margin: 15px auto;
  cursor: pointer;
}

.box-card:hover {
  transform: scale(1.01);
  /*width: 110%;*/
}

.card-header .header-title {
  /*max-width: 70%;*/
  overflow: hidden;
  text-overflow: ellipsis;
  font-weight: 700;
  font-size: 1.7rem;
}

.card-header .header-title:hover {
  border-bottom: 2px solid #1a1a1a;
}

.card-header .header-tag {
  margin-left: 10px;
}

.card-header .header-tag:first-child {
  margin-left: 15px;
}

.card-body {
  max-width: 100%;
  display: flex;
  align-items: center;
}

.card-body .card-body-img {
  min-width: 150px;
  height: 80px;
  background-color: #1a1a1a;
}

.card-body .card-body-content {
  display: flex;
  flex-direction: column;
  margin-left: 20px;
  flex: 1;
  height: 80px;
  /*background-color: #7B341E;*/
}

.card-body .card-body-content span {
  /*flex: 1;*/
  line-height: 1.5em;
  min-height: 3em;
  overflow: hidden;
  text-overflow: ellipsis;
  /* 多行溢出省略号 */
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
}

.card-body .card-body-content .info {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
  font-size: 14px;
  color: rgb(0 0 0 /30%);
}
</style>