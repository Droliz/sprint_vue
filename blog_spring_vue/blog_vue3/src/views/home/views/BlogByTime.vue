<template>
  <el-dropdown @command="handleCommand">
    <span class="el-dropdown-link">
      {{ title }}
    </span>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item :disabled="title === '年度'" :command="{title: '年度', format: '%Y'}">年度</el-dropdown-item>
        <el-dropdown-item :disabled="title === '月度'" :command="{title: '月度', format: '%Y-%m'}">月度</el-dropdown-item>
        <el-dropdown-item :disabled="title === '周度'" :command="{title: '周度', format: '%x-%v'}">周度</el-dropdown-item>
        <el-dropdown-item :disabled="title === '日度'" :command="{title: '日度', format: '%Y-%m-%d'}">日度</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>

  <!-- 归档 -->
  <div v-loading="loading">
    <el-timeline class="time-line-body">
      <el-timeline-item
          v-for="blog in blogs"
          :key="blog.key"
          :timestamp="title==='周度' ? `${blog.key.split('-')[0]} 第 ${blog.key.split('-')[1]} 周` : blog.key"
          placement="top">
        <blog-card v-for="i in blog.blog" :blog="i" :tag-color-map="tagColorMap"></blog-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script setup lang="ts">
import BlogCard from '../components/BlogCard.vue'
import {onMounted, reactive, ref, toRefs} from "vue";
import {getBlogsTime} from "@/api/blog";
import {getTags} from "@/api/tags";


const loading = ref<boolean>(true)

onMounted(() => {
  loadBlogs()
})

const data = reactive<{
  title: string,
  format: string,
  blogs: BlogGroup[],
  tagColorMap: string[][]}>({
  title: "年度",
  format: "%Y",
  blogs: [],
  tagColorMap: []
});

const { title, format, blogs, tagColorMap } = toRefs(data)

const handleCommand = (command: { title: string, format: string }) => {
  title.value = command.title
  format.value = command.format
  loadBlogs()
}

// 获取tag-color映射
getTags().then(({data}) => {
  let tags: string[] =[], colors:string[] =[]
  data.forEach(item => {
    tags.push(item.tag)
    colors.push(item.color)
  })
  tagColorMap.value.push(tags)
  tagColorMap.value.push(colors)
})

const loadBlogs = () => {
  getBlogsTime(format.value).then(({data}) => {
    loading.value = false
    // console.log(data)
    blogs.value = orchesData(data, 'createTime' as keyof Blog_tag)
    blogs.value.forEach(i =>
        i.blog.forEach(blog => {
          const temp = new Date(blog.updateTime)
          blog.tags = blog.tags.toString().split(',')
          blog.updateTime = `${temp.getFullYear()}-${temp.getMonth()+1}-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`
        }))

    console.log(blogs)
  })
}

// 数据分组  [[]]  => [{key: 字段值, blog: []}]
const orchesData = (data: Blog_tag[], key: keyof Blog_tag): BlogGroup[] => {
    const groupBy = (list: Blog_tag[], fn: Function) => {
      let groups: BlogGroup[] = []
      list.forEach(i => {
        let groupName = fn(i)
        if (groups.findIndex(item => item.key === groupName) === -1) {
          groups.push({
            key: groupName,
            blog: list.filter(item => item.createTime === groupName)
          })
        }
      })
      return groups
    }

  return groupBy(data, (item: Blog_tag) => {
      return item[key]
    })
}

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