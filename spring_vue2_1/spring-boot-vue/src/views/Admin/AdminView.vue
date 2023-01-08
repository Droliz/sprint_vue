<template>
  <div>
    <!-- 工具栏 -->
    <div class="search">
      <el-input
          v-model="parma.name"
          prefix-icon="el-icon-search"
          placeholder="请输入名字"
          style="width: 200px; margin-right: 10px;"
          @keyup.enter.native="loadLost"></el-input>
      <el-select
          v-model="parma.sex"
          placeholder="请选择性别"
          style="margin-right: 10px;">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="success" @click="loadLost">查询</el-button>
      <el-button type="primary" @click="reset">重置</el-button>
      <el-button type="primary" @click="addForm">新增</el-button>
    </div>
    <!-- 表格 -->
    <el-table
        stripe
        :data="tableData"
        :header-cell-style="{color: '#555'}">
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="180">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="80">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="80">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.sex === 1 ? 'primary' : 'success'"
          disable-transitions>
              {{scope.row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="180">
        <template slot-scope="scope">
          <el-tag size="small" :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
                  disable-transitions>
            {{scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '普通用户')}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column prop="isvalid" label="是否启用" width="80">
        <template slot-scope="scope">
          <el-switch
              :value="scope.row.isvalid"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="Y"
              inactive-value="N">
          </el-switch>
        </template>
      </el-table-column>
       <el-table-column prop="prefsession" label="登录状态" width="80">
        <template slot-scope="scope">
          <el-switch
              :value="scope.row.prefsession"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value="1"
              :inactive-value="0">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="primary"
              @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[10, 20, 30, 50]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <!-- 新增表单，工具栏唤起 -->
    <el-dialog
        title="新增"
        :visible.sync="dialogVisible"
        width="45%"
        :before-close="closeDialog">
      <el-form ref="formRef" :rules="rules" :model="form" label-width="80px">
        <el-form-item label="账号" prop="no">
          <el-input :disabled="typeModel===1" v-model="form.no"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input show-password v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input-number v-model="form.age" :max="120" :min="1"></el-input-number>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="roleId">
          <el-radio-group v-model="form.roleId">
            <el-radio :label="0">超级管理员</el-radio>
            <el-radio :label="1">管理员</el-radio>
            <el-radio :label="2">普通用户</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否启用" prop="isvalid">
          <el-switch
              v-model="form.isvalid"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-value="Y"
              inactive-value="N">
          </el-switch>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
        <el-button type="danger" @click="closeDialogFromBtn">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import {getList, saveOrMod, findByNo, deleteById} from "@/api/user";

export default {
  name: "MainView",
  data() {
    // 账号的唯一验证
    const checkDuplicate = (rule, value, callback) => {
      if (this.typeModel === 1) {callback()}
      findByNo(this.form.no).then(({data}) => {
        // console.log(data)
        if (data.code !== 200) {
          callback()
        } else {
          callback(new Error("该账号已存在"))
        }
      })
    }

    // 账号校验
    const validateCode = (rule, value, callback) => {
      if (this.typeModel === 1) {callback()}
      if (!/^(?![0-9]*$)(?![a-zA-Z]*$)[a-zA-Z0-9]{6,20}$/.test(value)) {
        callback(new Error('账号必须为6-20位字母和数字组合'))
      } else {
        callback()
      }
    }

    return {
      tableData: [],   // 表格数据
      pageSize: 10,   // 单页数量
      pageNum: 1,   // 页码
      total: 0,   // 总数
      parma: {
        name: "",
        sex: "",
      },   // 获取列表参数
      options: [
        {
          value: '1',
          label: '男'
        },
        {
          value: '0',
          label: '女'
        }],
      dialogVisible: false,   // 表单显示隐藏
      form: {
        id: 0,
        no: "",
        name: "",
        password: "",
        sex: 1,
        age: 18,
        phone: "",
        roleId: 2,
        isvalid: 'Y',
        prefsession: 0
      },   // 表单
      rules: {
        no: [
          {required: true, message: "请输入账号", trigger: 'blur'},
          {min: 3, max: 12, message: '长度在 3 - 12 之间', trigger: 'blur'},
          {validator: checkDuplicate, trigger: 'blur'},
          {validator: validateCode, trigger: 'blur'}
        ],
        name: [
          {required: true, message: "请输入名字", trigger: 'blur'},
        ],
        password: [
          {required: true, message: "请输入密码", trigger: 'blur'},
          {min: 6, message: '密码不少于6位', trigger: 'blur'},
          {max: 15, message: '密码不大于15位', trigger: 'blur'},
        ],
        age: [
          {required: true, message: "请输入年龄", trigger: 'blur'},
        ],
        phone: [
          {required: true, message: "请输入电话", trigger: 'blur'},
          {pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/, message: '请输入正确的手机号', trigger: 'blur'}
        ],
      },   // 表单校验规则
      typeModel: 0,   // 0、通过新增打开的表单  1、通过编辑打开表单
      baseForm: {
        id: 0,
        no: "",
        name: "",
        password: "",
        sex: 1,
        age: 18,
        phone: "",
        roleId: 2,
        isvalid: 'Y',
        prefsession: 0
      },
    }
  },
  methods: {
    // 新增
    addForm() {
      this.typeModel = 0
      this.dialogVisible = true
    },
    // 编辑
    handleEdit(_, row) {
      this.typeModel = 1
      Object.keys(this.form).forEach(i => {this.form[i] = row[i]});
      this.dialogVisible = true
      // console.log(this.form)
    },
    // 删除
    handleDelete(_, row) {
      console.log(row)
      this.$confirm('请确认删除该成员信息？')
          .then(() => {
            deleteById(row.id).then(({data}) => {
              if (data.code === 200) {
                this.loadLost()
                this.$message.success("删除成功")
                return
              }
              this.$message.error("删除失败")
            })
          })
          .catch(() => {
            this.$message.warning("取消删除")
          });
    },
    // 修改每页数量
    handleSizeChange(val) {
      this.pageNum = 1
      this.pageSize = val
      this.loadLost()
    },
    // 修改页码
    handleCurrentChange(val) {
      this.pageNum = val
      this.loadLost()
    },
    // 重置搜索框
    reset() {
      this.parma.name = ''
      this.parma.sex = ''
      this.handleCurrentChange(1)
    },
    // 清空表单
    clearForm() {
      // this.$refs.formRef.resetFields()  // 必须添加 prop 属性，才能生效
      // if (!this.isObjectValueEqual(this.form, this.baseForm)) {
      Object.keys(this.form).forEach(i => {
        this.form[i] = this.baseForm[i]
      })
      // }
    },
    // 通过点击空白退出表单
    closeDialog(done) {    // 关闭表单
      this.$confirm('输入内容会清空，确认关闭？')
          .then(() => {
            this.clearForm()
            done();
          })
          .catch(() => {});
    },
    // 通过取消退出表单
    closeDialogFromBtn() {
      this.clearForm()
      this.dialogVisible = false
    },
    // 提交新增
    submit() {

      this.$refs.formRef.validate(valid => {

        if (!valid) {
          console.log(this.form)
          return;
        }
        saveOrMod(this.form).then(({data}) => {
          if (data.code === 200) {
            this.loadLost()
            this.closeDialogFromBtn()
            this.$message.success(data.msg)
            return
          }
          this.$message.error('添加失败，请联系管理员')
        }).catch(({response}) => {
          this.$message.error(`${response.status}添加失败，请联系管理员`)
        })
      })
    },
    // 加载列表数据
    loadLost() {
      getList({
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        parma: this.parma
      }).then(({data}) => {
        if (data.code === 200) {

          this.tableData = data.data
          this.total = data.total
          return
        }
        this.$message.error('获取列表失败，请联系管理员')
      }).catch(({response}) => {
        this.$message.error(`${response.status}获取列表失败，请联系管理员`)
      })
    }
  },
  mounted() {
    this.loadLost()
    this.$store.commit('login/login', JSON.parse(localStorage.getItem('UserInfo')))
  }
}
</script>

<style scoped>
.search {
  margin: 5px 0 5px;
}

.el-pagination {
  margin-top: 10px;
}
</style>