<template>
  <div class="header-main">
    <div class="header-icon">
      <i :class="this.$store.state.aside.isCollapse ? 'el-icon-s-fold': 'el-icon-s-unfold'" @click="collapse"></i>
    </div>
    <div class="header-title">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <el-dropdown @command="handleCommand">
      <span>{{$store.state.login.userInfo.no}}</span>
      <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item command="userInfo">个人中心</el-dropdown-item>
        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
      </el-dropdown-menu>

    </el-dropdown>
  </div>
</template>

<script>
import {saveOrMod} from "@/api/user";

export default {
  name: "HeaderComponent",
  methods: {
    // 前往个人中心
    toUser() {
      this.$router.push({
        name: 'user'
      })
    },
    // 退出登录
    logout() {
      // 修改登录状态
      let temp = this.$store.state.login.userInfo
      temp.prefsession = 0
      saveOrMod(temp).then(({data}) => {
        if (data.code === 200) {
          this.$message.success("登出成功")
          localStorage.clear()
          // 跳转login
          this.$router.push('/login')
        } else {
          this.$message.error("登出失败")
        }
      }).catch(({response}) => {
        this.$message.error(`${response.status}，请联系管理员`)
      })
    },
    handleCommand(a) {
      switch (a) {
        case 'userInfo':
          this.toUser()
          break;
        case 'logout':
          this.logout()
          break;
        default:
          this.$message.error(`未识别选项${a}，请联系管理员`);
      }
    },
    // 菜单折叠
    collapse() {
      this.$store.commit('aside/editCollapse')
    }
  }
}
</script>

<style scoped>
.header-main {
  display: flex;
  line-height: 60px;
}
.header-icon {
  font-size: 20px;
}
.header-icon i {
  cursor: pointer;
}

.header-title {
  flex: 1;
  text-align: center;
  font-size: 34px;
  font-weight: 700;
}
</style>