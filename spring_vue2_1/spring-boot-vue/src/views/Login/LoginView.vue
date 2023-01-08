<template>
  <div class="login-body">
    <div class="login-content">
      <h1 class="login-title">用户登录</h1>
      <el-form
          ref="loginRef"
          :model="loginForm"
          :rules="rules"
          label-width="70px"
          size="medium"
          hide-required-asterisk>
        <el-form-item label="账号" prop="no">
          <el-input prefix-icon="el-icon-user" type="text" v-model.trim="loginForm.no"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input prefix-icon="el-icon-lock" type="password" v-model.trim="loginForm.password" show-password></el-input>
       </el-form-item>
      </el-form>
      <div class="footer">
        <el-button type="primary" plain @click="register">注 册</el-button>
        <el-button type="danger" plain @click="reset">重 置</el-button>
        <el-button type="success" plain @click="login">登 录</el-button>
      </div>
    </div>
  </div>
</template>

<script>

import {userLogin} from "@/api/user";

export default {
  name: "LoginView",
  data() {
    return {
      loginForm: {
        no: "",
        password: ""
      },
      rules: {
        no: [
          {required: true, message: "请输入账号", trigger: 'blur'},
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    register() {},
    reset() {
      this.$refs.loginRef.resetFields()
    },
    login() {
      this.$refs.loginRef.validate(valid => {
        if (valid) {
          userLogin(this.loginForm).then(({data}) => {
            console.log(data)
            if (data.code === 200) {
              // 持久化
              localStorage.setItem('UserInfo', JSON.stringify(data.data[0]))
              localStorage.setItem('lastLoginTime', JSON.stringify(new Date()))
              localStorage.setItem('token', "123")

              this.$router.push('/home')
              this.$message.success(data.msg)
            } else {
              this.$message.error(data.msg)
            }
          }).catch(({response}) => {
            console.log(response)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.login-body {
  width: 100vw;
  height: 100vh;
  background-image: url("../../assets/image/login_bg_snow.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-size: cover;
}

.login-content {
  display: flex;
  justify-content: center;
  flex-direction: column;
  position: absolute;
  backdrop-filter: blur(10px);   /* 将这个元素覆盖的区域下方进行模糊  */
  -webkit-backdrop-filter: blur(10px);
  /*filter: blur(10px);   !* 将这个元素模糊 *!*/
  top: 50%;
  left: 50%;
  height: 300px;
  width: 400px;
  letter-spacing: 0.7em;   /* 字符间的空白 */
  transform: translate(-50%, -70%);
  border-radius: 40px;
  background-color: rgba(235, 236, 222, .3);
  box-shadow: 0 0 30px 10px rgba(0, 0, 0, .3);
}

.login-content .login-title {
  margin: 20px auto;
}

.login-content .el-form {
  flex-grow: 1;
  padding: 20px;
  margin: 10px;
  line-height: 45px;
}

.el-input {
  opacity: .5;
}

.el-form-item .el-form-item--small {
  margin: 10px 0;
}

.footer {
  margin: 0 auto 30px;
}

.footer .el-button {
  background-color: transparent;
  border: none;
  font-weight: 700;
  font-size: 14px;
}
</style>