<template>
  <div>
  <div class="login-container"  >
    <div style="width: 350px" class="login-box">
      <div style="font-weight: bold;font-size: 24px;text-align: center;margin-bottom: 30px">
          <img src="src/assets/imgs/school.jpg" style="width: 100px;height: 100px">
          <div>安徽理工大学</div>
      </div>
      <el-form  :model="data.form"  :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="User" v-model="data.form.username" placeholder="请输入账号"/>
        </el-form-item>
        <el-form-item prop="password">
          <el-input show-password prefix-icon="Lock" v-model="data.form.password" placeholder="请输入密码"/>
        </el-form-item>

        <el-form-item >
        <el-button type="primary" style="width: 100%" @click="register">注 册</el-button>
        </el-form-item>
        </el-form>
      <div style="margin-top: 30px;text-align: right">
        已有账号?请<a href="/login">登录</a>
      </div>
    </div>

  </div>
  </div>
</template>
<script setup>
import {reactive,ref} from "vue";
import request from "@/utils/request";
import {ElMessage} from "element-plus";
import router from "@/router";

const data=reactive({
  form: {

  }
  })
//表单校验
const rules=reactive({
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
  ]
})

const formRef=ref()

const register=()=>{
  formRef.value.validate((valid)=>{
    if(valid){
      request.post("/register",data.form).then(res=>{
        if(res.code==='200'){
          localStorage.setItem('student-user',JSON.stringify(res.data))
          ElMessage.success("注册成功")
          router.push('/login')  //跳转到登录
        }else{
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

</script>

<style scoped>
.login-container{
min-height: 100vh;
  overflow: hidden;
  display:flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/rbg.jpg");
  background-size: cover;

}
.login-box{
  background-color: rgba(255,255,255,.8);
  box-shadow:0 0 10px rgba(0,0,0,0.1);
  padding: 30px;
  border-radius: 5px;
  /*border: 1px solid #ddd;*/
}

</style>