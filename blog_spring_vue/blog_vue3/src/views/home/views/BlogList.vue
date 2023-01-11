<template>
  <div class="context-search">
    <div class="search-input">
      <el-input v-model="parma.title" clearable placeholder="search something"></el-input>
      <el-button @click="reset" circle>
        <template #icon>
          <i-ep-refreshRight/>
        </template>
      </el-button>
      <el-button @click="loadBlogList">搜索</el-button>
    </div>
    <div class="search-tags">
      <el-button
          v-for="tag in tagColorMap[0]"
          :key="tag"
          :class="isPitchTag === tag ? 'btnPitchTag' : ''"
          @click="pitchTag(tag)">
        {{ tag }}
      </el-button>
    </div>
  </div>

  <div class="context-body">
    <!-- 主体 -->
    <blog-card v-for="blog in blogList" :key="blog.blogId" :blog="blog" :tag-color-map="tagColorMap"/>
  </div>

  <!-- 分页 -->

</template>

<script setup lang="ts">
import {reactive, ref, toRefs} from "vue";
import { getBlogList } from "@/api/blog";
import { getTags } from "@/api/tags";
import {useRouter} from "vue-router";

import BlogCard from '../components/BlogCard.vue'

const query = reactive<Query>({
  pageNum: 1,
  pageSize: 10,
  parma: {
    title: "",
    tag: ""
  }
})

const { pageNum, pageSize, parma } = toRefs(query)

const data = reactive<{blogList: Blog_tag[], tagColorMap: string[][]}>({
  blogList: [],
  tagColorMap: [],
})

const {blogList, tagColorMap} = toRefs(data)

// 获取tag-color映射
getTags().then(({data}) => {
  let tags: string[] =[], colors:string[] =[]
  data.forEach(item => {
    tags.push(item.tag)
    colors.push(item.color)
  })
  tagColorMap.value.push(tags)
  tagColorMap.value.push(colors)
  loadBlogList()
})

// 加载博客列表数据
const loadBlogList = (): void => {
  getBlogList(query).then(res => {
    res.data.map(item => {
      // 修改 tag 形式
      item.tags = item.tags.toString().split(',')
      let temp = new Date(item.createTime)
      // 修改时间
      item.createTime = `${temp.getFullYear()}-${temp.getMonth()+1}-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`
      temp = new Date(item.updateTime)
      item.updateTime = `${temp.getFullYear()}-${temp.getMonth()+1}-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`
    })
    blogList.value = res.data
  })
}

// 控制选中的标签
const isPitchTag = ref("")

// 选中标签
const pitchTag = (tag: string): void => {
  // 更新样式
  isPitchTag.value = tag
  // 更新 query
  parma.value.tag = tag
}

// 清空 input 以及 标签
const reset = (): void => {
  isPitchTag.value = ""
  parma.value.title = ""
  parma.value.tag = ""
  // 获取blog数据
  loadBlogList()
}

const router = useRouter()

// 跳转 blog 页面
const blogPage = (blog_id: number): void => {
  router.push({
    name: 'blogPage',
    params: {
      id: blog_id
    }
  })
}
</script>

<style scoped>

.context-search {
  margin: 50px;
  display: flex;
  flex-direction: column;
}

.context-search .search-input {
  display: flex;
}

.context-search .search-input .el-input {
  max-width: 300px;
  min-width: 200px;
  margin-right: 20px;
}

.context-search .search-tags {
  display: flex;
  flex-wrap: wrap;
  margin-top: 20px;
}

.context-search .search-tags .el-button {
  margin-top: 10px;
  margin-right: 10px;
  color: #333;
  border: none;
}

.btnPitchTag {
  color: var(--el-button-hover-text-color);
  border-color: var(--el-button-hover-border-color);
  background-color: var(--el-button-hover-bg-color);
  outline: 0;
}

.context-body {
  margin: 50px;
  padding: 0;
}

/* 自适应 */
@media screen and (max-width: 960px){
  .context-body {
    margin: 1rem;
  }
}
</style>