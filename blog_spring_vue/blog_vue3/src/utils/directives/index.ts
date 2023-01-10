import { App } from 'vue'
import  highlight from './highlight'
import formateTime from './formate-time'

export default function registerDirectives(app: App<Element>) {
    highlight(app)
    formateTime(app)
}