// 格式化处理时间

import { App } from 'vue'

export default function formateTime(app: App<Element>) {

    app.directive('formateTime', {
        updated(el, binding) {
            const textContent = binding.value
            const temp = new Date(textContent)
            console.log(textContent)
            el.textContent = `${temp.getFullYear()}-${temp.getMonth()+1}-${temp.getDate()} ${temp.getHours()}:${temp.getMinutes()}:${temp.getSeconds()}`
        }
    })
}