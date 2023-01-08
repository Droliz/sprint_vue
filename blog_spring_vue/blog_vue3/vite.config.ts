import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
// @ts-ignore
import IconsResolver from 'unplugin-icons/resolver'
// @ts-ignore
import Icons from 'unplugin-icons/vite'

import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src') // 配置别名
    }
  },
  plugins: [
    vue(),
    AutoImport({
      // 自动导入 ref、reactive、等
      imports: ['vue'],
      resolvers: [
        ElementPlusResolver(),
        // 自动导入图标
        IconsResolver({
          prefix: 'Icon'
        })
      ],
    }),
    Components({
      resolvers: [
        ElementPlusResolver(),
        IconsResolver({
          enabledCollections: ['ep']
        })
      ]
    }),
    Icons({
      autoInstall: true
    })
  ],
})
