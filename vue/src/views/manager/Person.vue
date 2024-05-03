<template>
<div>
  <div class="card" style="width: 50%;padding: 40px">
      <el-form :ref="formRef" :model="data.form" label-width="100px" label-position="right" style="padding-right: 40px">
          <el-form-item label="学生账号" prop="username">
              <el-input  v-model="data.form.username" autocomplete="off" disabled/>
          </el-form-item>
          <el-form-item label="学生密码" prop="password" >
              <el-input show-password v-model="data.form.password" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="学生名称" >
              <el-input v-model="data.form.name" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="手机号" >
              <el-input v-model="data.form.phone" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="邮箱" >
              <el-input v-model="data.form.email" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="性别" >
              <el-radio-group v-model="data.form.sex">
                  <el-radio label="男"></el-radio>
                  <el-radio label="女"></el-radio>
              </el-radio-group>
          </el-form-item>
          <el-form-item label="生日">
              <el-date-picker  style="width: 100%" format="YYYY-MM-DD" value-format="YYYY-MM-DD" v-model="data.form.birth"></el-date-picker>
          </el-form-item>
          <el-form-item >
              <el-button type="primary" @click="update">保 存</el-button>
          </el-form-item>

      </el-form>
  </div>
</div>
</template>

<script setup>
    import {reactive} from "vue";
    import request from "@/utils/request";
    import {ElMessage} from "element-plus";
    import router from "@/router";

    const data=reactive({
        form: JSON.parse(localStorage.getItem('student-user')||"{}")
    })
    const update=()=> {
        request.put('/student/update', data.form).then(res => {
            if (res.code === '200') {
                router.push('/login')
                data.formVisible = false //关闭弹窗
                ElMessage.success("操作成功")
            } else {
                ElMessage.error(res.msg)
            }
        })
    }
</script>
