<template>
  <div class="body">
    <div class="userinfo">
      <!-- 用户 -->
      <div class="logo">
        <img alt="large" src="@/assets/vue.svg" />
      </div>
      <div class="category">
        <div class="category-title"
            v-for="title in titles"
            :key="title.text">
          <p>{{ title.text }}</p>
          <span>{{ title.count }}</span>
        </div>
      </div>
      <div class="btn-list">
        <ul>
          <li>
            <el-button @click="handleClickBtn">
              <template #icon>
                <i-ep-house />
              </template>
              <span>回到首页</span>
            </el-button>
          </li>
          <li>
            <el-button @click="blogByTime">
              <template #icon>
                <i-ep-notebook />
              </template>
              <span>文章归档</span>
            </el-button>
          </li>
          <li>
            <el-button @click="blogList">
              <template #icon>
                <i-ep-document />
              </template>
              <span>文章分类</span>
            </el-button>
          </li>
          <li>
            <el-button @click="drinkTea">
              <template #icon>
                <i-ep-coffeeCup />
              </template>
              <span>请我喝茶</span>
            </el-button>
          </li>
          <li>
            <el-button @click="handleClickBtn">
              <template #icon>
                <i-ep-warningFilled />
              </template>
              <span>关于作者</span>
            </el-button>
          </li>
        </ul>
      </div>
      <div class="little_dinosaur">
<!--        <input type="text" placeholder="搜索"/>-->
      </div>
      <div class="other-link">
        TODO 外链
      </div>
      <div class="tags">
        <div
            v-for="tagInfo in tags"
            :key="tagInfo.tagId"
            class="tag">
          <el-tag :color="tagInfo.color"
                  :style="{color: '#fff', fontWeight: 700}">
            {{ tagInfo.tag }}
          </el-tag>
        </div>
      </div>
    </div>

    <div class="context">
      <!-- 正文 -->
      <router-view/>
    </div>
  </div>

  <el-backtop/>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref, toRefs } from 'vue'
import { getTags } from '@/api/tags'
import {useRouter} from "vue-router";

const input = ref("")

interface Title {
  text: string,
  count: number
}

interface Data {
  titles: Title[],
  tags: tagInfo[]
}

const router = useRouter()

const data = reactive<Data>({
  titles: [
    {text: "文章", count: 100},
    {text: "标签", count: 20},
    {text: "分类", count: 10},
  ],
  tags: []
})

onMounted(() => {
  getTags().then(res => {
    data.tags = res.data
  })
})

const { titles, tags } = toRefs(data)

// 前往文章分类
const blogList = (): void => {
  router.push({
    name: "blogList"
  })
}

// 喝茶界面
const drinkTea = (): void => {
  router.push({
    name: 'drinkTea'
  })
}

// 文章归档
const blogByTime = (): void => {
  router.push({
    name: 'blogByTime'
  })
}

const handleClickBtn = () => {

}

</script>

<style scoped>
@import './css/index.css';
</style>