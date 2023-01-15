<template>
  <v-md-editor
      v-model="text"
      height="100vh"
      class="edit"
      left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code | save upload"
      @save="saveFile"
      preview-class="vuepress-markdown-body"
      :toolbar="toolbar">

  </v-md-editor>

  <el-dialog v-model="centerDialogVisible" title="" width="20%" center>
    <template #footer>
      <el-dropdown class="select" @command="handleCommand">
        <el-button type="primary">
          选择下载类型<i-ep-arrowDown />
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item command=".md">markdown</el-dropdown-item>
            <el-dropdown-item command=".html">html</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import {ref} from "vue";
// import {ElMessage} from 'element-plus'
const text = ref<string>("")
const Html = ref<string>("")
const toolbar = ref<object>({})
const centerDialogVisible = ref<boolean>(false)

const saveFile = (text: string, html: string) => {
  centerDialogVisible.value = true
  Html.value = html
}

const handleCommand = (type: string) => {
  centerDialogVisible.value = false
  if (!text.value || !Html.value) {
    ElMessage.error('内容不能为空')
    return
  }
  type === '.md' ? saveForMd() : ""
  type === '.html' ? saveForHtml() : ""
}

const saveForMd = () => {
  //定义文件内容，类型必须为Blob 否则createObjectURL会报错，this.code 为文本内容
  let content = new Blob([text.value])
  //生成url对象
  let urlObject = window.URL || window.webkitURL || window
  let url = urlObject.createObjectURL(content)
  //生成<a></a>DOM元素
  let el = document.createElement('a')
  //链接赋值
  el.href = url
  el.download = "markdown" + '.md'
  //必须点击否则不会下载
  el.click()
  //移除链接释放资源
  urlObject.revokeObjectURL(url)
  // this.fileName 重置为空
}

const saveForHtml = async () => {

  ElMessage.info('功能开发中')
  return

  const html = `<!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <title>网页HTML存本地</title>
                    <link href="src/assets/css/markdown/custom_light_01.css">
                    <style>
                    body {
                      position: absolute;
                      margin: 10px auto;
                      min-width: 150px;
                      max-width: 90%;
                      width: 70%;
                      color: #3e3e3e;
                      line-height: 1.8; /*更改默认的行距*/
                      word-spacing:2px; /*更改默认的词间距*/
                      letter-spacing:2px;/*更改默认的字符间距*/
                      font-family: "Helvetica Neue",Helvetica,"Hiragino Sans GB","Microsoft YaHei",Arial,sans-serif;
                    }
                    </style>
                </head>
                <body>
                    <div class="markdown-here-wrapper">
                        ${Html.value}
                    </div>
                </body>
                </html>`

  //定义文件内容，类型必须为Blob 否则createObjectURL会报错，this.code 为文本内容
  let content = new Blob([html])
  //生成url对象
  let urlObject = window.URL || window.webkitURL || window
  let url = urlObject.createObjectURL(content)
  //生成<a></a>DOM元素
  let el = document.createElement('a')
  //链接赋值
  el.href = url
  el.download = "markdown" + '.html'
  //必须点击否则不会下载
  el.click()
  //移除链接释放资源
  urlObject.revokeObjectURL(url)
  // this.fileName 重置为空
}

// 自定义文件上传
toolbar.value = {
  upload: {
    title: "上传文件",
    icon: "v-md-icon-tip",
    action: async () => {
      // ts 对 window 定义为 declare var window: Window & typeof globalThis;
      // 如下解决 或添加 Window 接口类型 或使用方括号形式
      const a = await (window as any).showOpenFilePicker({
        types: [{
          accept: {
            'markdown/*': ['.md']
          }
        }],
        // 只能选择一个
        multiple: false
      });
      const file = await a[0].getFile()
      text.value = await file.text()
    }
  }
}
</script>

<style scoped>
.edit:deep(img) {
  max-width: 100%;
}
</style>