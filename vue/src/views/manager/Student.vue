<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input style="width: 260px;margin-right: 10px" placeholder="请输入学生账号查询" :prefix-icon="Search" v-model="data.username"/>
      <el-input style="width: 260px;margin-right: 10px" placeholder="请输入学生名称查询" :prefix-icon="Search" v-model="data.name"/>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <div class="card" style="margin-bottom: 10px">
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="70"/>
          <el-table-column prop="username" label="学生账号"/>
          <el-table-column prop="name" label="学生名称"/>
          <el-table-column prop="phone" label="学生手机号"/>
          <el-table-column prop="email" label="学生邮箱"/>
          <el-table-column prop="birth" label="生日"/>
            <el-table-column prop="sex" label="性别"/>
<!--          <el-table-column prop="avatar" label="头像"/>-->


            <el-table-column width="180" label="操作">
            <template #default="scope">
                <el-button type="primary"  @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="card">
      <el-pagination v-model:current-page="data.pageNum" v-model:page-size="data.pageSize"
                     @current-change="handleCurrentChange"
                     background layout="prev, pager, next" :total="data.total"/>
    </div>
      <el-dialog width="35%" title="学生信息" v-model="data.formVisible" destroy-on-close>
          <el-form :rules="rules" :ref="formRef" :model="data.form" label-width="100px" label-position="right" style="padding-right: 40px">
              <el-form-item label="学生账号" prop="username">
                  <el-input  v-model="data.form.username" autocomplete="off"/>
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
<!--              <el-form-item label="学生头像">-->
<!--                  <el-upload action="http://localhost:9090/files/upload" list-type="picture" :on-success="handleImgUploadSuccess">-->
<!--                      <el-button type="primary">上传头像</el-button>-->
<!--                  </el-upload>-->
<!--              </el-form-item>-->
          </el-form>

          <div slot="footer" class="dialog-footer" style="text-align: right;margin-top: 50px">
              <el-button @click="data.formVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">保 存</el-button>
          </div>
      </el-dialog>
  </div>
</template>
<script setup>
import {Search} from "@element-plus/icons-vue";
import {ref,reactive} from "vue";
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const baseUrl='/student'

const data=reactive({
    username:'',
    name:'',
    tableData:[],
    total:0,
    formVisible:false,
    form:{
    },
    pageSize:5,   //每页的个数
    pageNum:1     //当前的页码
})

const load=()=>{
    request.get(baseUrl+'/selectPage',{
        params:{
            pageNum:data.pageNum,
            pageSize:data.pageSize,
            name:data.name,
            username:data.username
        }
    }).then(res=>{
        data.tableData=res.data?.list||[]
        data.total=res.data?.total||0

    })
}
//调用方法获取后台数据
load()

const handleCurrentChange=()=>{
    //当翻页的时候，重新加载数据即可
    load()
}

const reset=()=>{
    data.name=''
    data.username=''
    load()
}

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

const handleAdd=()=>{
    data.form={}
    data.formVisible=true

}
//保存数据到后台
const save=()=>{
    formRef.value.validate((valid)=>{
        if(valid){
            request.request({
                url:data.form.id?baseUrl+'/update':baseUrl+'/add',
                method:data.form.id?'PUT':'POST',
                data:data.form
            }).then(res=>{
                if(res.code==='200'){
                    load()  //重新获取数据
                    data.formVisible=false //关闭弹窗
                    ElMessage.success("操作成功")
                }else {
                    ElMessage.error(res.msg)
                }
            })
        }
    })
}

const handleEdit=(row)=>{
    data.form=JSON.parse(JSON.stringify(row))
    data.formVisible=true
}

const del=(id)=>{
    ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？','删除确认',{type:'warning'}).then(res=>{
        request.delete(baseUrl+'/delete/'+id).then(res=>{
            if(res.code==='200'){
                load()
                ElMessage.success("操作成功")
            }else{
                ElMessage.error(res.msg)
            }
        })
    }).catch(res=>{})
}

const handleImgUploadSuccess=(res)=>{
    data.form.avatar=res.data
}
</script>