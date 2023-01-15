<template>
  <!--  <div style="position: relative">-->

  <!-- v-highlight"-->
  <div
      v-html="content"
      v-highlight="{once, copySuccess}"
      v-lazy-container="{select: 'img'}"
      class="blog-content-body markdown-here-wrapper" >

  </div>
  <!-- toc -->
  <div class="blog-toc" v-html="toc"></div>

<!--  </div>-->
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {onUpdated, onMounted, ref} from "vue";
import {getBlog} from "@/api/blog";
// import {ElNotification} from "element-plus"
import markdown from "@/utils/markdown/markdown";

const route = useRoute()

const blogInfo = ref<Blog_tag>({
  author: "",
  blogId: 0n,
  color: "",
  content: "",
  createTime: "",
  tags: [],
  title: "",
  updateTime: "",
})

const content = ref<HTMLElement>()
const toc = ref<string | undefined>("")
const once = ref<Boolean>(true)

onMounted(() => {
  loadBlog()
})

onUpdated(() => {
  once.value = false
})

// 加载数据
const loadBlog = () => {

  // console.log(BigInt(route.params.id as string))
  // console.log(route.params.id)
  // 获取文章
  getBlog(BigInt(route.params.id as string)).then(({data}) => {
    // console.log(data)

    blogInfo.value = data[0]
    parseMarkdown()
  })
}

// 解析 markdown
const parseMarkdown = () => {
  const article = markdown.marked(blogInfo.value.content)
  article.then(response => {
    content.value = response?.content
    toc.value = response?.toc
    // console.log(toc.value)
  })
}

// 复制成功
const copySuccess = (options: {type: string, message: string, title: string}) => {
  ElNotification(options)
}

</script>

<style scoped>

/* 代码块行号样式、复制 */
@import '../css/codeLine.css';

.blog-content-body {
  margin: 50px;
}

.blog-toc {
  position: absolute;
  right: 50px;
  top: 100px;
  max-width: 150px;
  color: #3e3e3e;
  line-height: 1.8; /*更改默认的行距*/
  word-spacing:2px; /*更改默认的词间距*/
  letter-spacing:2px;/*更改默认的字符间距*/
  font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;
}

.blog-toc:deep(a) {
  text-decoration: none
}

.blog-toc:deep(a:hover) {
  color: #55a532;
}

.blog-toc:deep(a:active) {
  color: #55a532;
}

</style>