import hljs from "highlight.js";
// 按需引入语言包
import xml from 'highlight.js/lib/languages/xml';

// 扩展 vue 为 xml 语法
hljs.registerLanguage('vue', xml);

import 'highlight.js/styles/atom-one-dark.css' //样式
import { App } from 'vue'
// @ts-ignore
import ClipboardJS from "clipboard";

// 全局指令：代码高亮显示
export default function highlight(app:  App<Element>) {
    app.directive('highlight', {
        updated(el: Element, binding) {
            // let blocks = el.querySelectorAll('pre code');
            // blocks.forEach((block)=>{
            //     hljs.highlightBlock(<HTMLElement>block)
            // })

            let pres = el.querySelectorAll('pre')

            pres.forEach((pre, index) => {
                let block = pre.querySelectorAll('code')[0]

                let lang = block.classList[0].split('-')[1]
                block.classList.add("code-main")
                // 保存代码块
                let code: string
                code = block.innerText
                // console.log('block', block)
                if  (binding.value.once) {
                    hljs.highlightBlock(block)
                    block.innerHTML = `<ol><li>${block.innerHTML.replace(
                        /\n/g,   // 此处如果是  /\n/g 全局匹配会导致重新渲染时匹配错误
                        `</li><li class="line">`
                    )}</li></ol>`;
                }

                const a = `copy-${index}`

                // 复制功能
                if (!pre.querySelector('.copy-ico')) {

                    pre.innerHTML = `<div class="code-menu">
                                        <span class="code-lang">${lang}</span>
                                        <span class="copy-ico ${a}">复制</span>
                                     </div>` + pre.innerHTML

                    let clipboard = new ClipboardJS(
                        `.${a}`,
                        {
                            text: () => code,

                        }) // 初始化
                    // console.log(clipboard)
                    clipboard.on('success', () => {  // 还可以添加监听事件，如：复制成功后提示
                        // console.log(code)
                        binding.value.copySuccess({
                            title: 'Success',
                            message: '复制成功',
                            type: 'success',
                        })
                    })

                    clipboard.on('error', () => {  // 还可以添加监听事件，如：复制成功后提示
                        binding.value.copySuccess({
                            title: 'Error',
                            message: '复制失败',
                            type: 'error',
                        })
                    })
                }
            })
        }
    })
}

