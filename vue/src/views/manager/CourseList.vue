<template>
  <div>
    <div class="card" style="margin-bottom: 10px">
      <el-input style="width: 260px;margin-right: 10px" placeholder="请输入课程名称查询" :prefix-icon="Search" v-model="data.name"/>
      <el-input style="width: 260px;margin-right: 10px" placeholder="请输入课程编号查询" :prefix-icon="Search" v-model="data.no"/>
      <el-input style="width: 260px" placeholder="请输入任课老师查询" :prefix-icon="Search" v-model="data.teacher"/>
      <el-button type="primary" style="margin-left: 10px" @click="load">查询</el-button>
      <el-button type="info" @click="reset">重置</el-button>
    </div>
    <div class="card" style="margin-bottom: 10px">
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="70"/>
          <el-table-column prop="name" label="课程名称"/>
          <el-table-column prop="no" label="课程编号"/>
          <el-table-column prop="descr" label="课程描述"/>
          <el-table-column prop="times" label="时间"/>
          <el-table-column prop="teacher" label="任课老师"/>
          <el-table-column width="180" label="操作">
            <template #default="scope">
                <el-button type="primary" @click="selectCourse(scope.row)">选课</el-button>
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
  </div>
</template>
<script setup>

import {reactive} from "vue";
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data=reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
   total:0,
    student:JSON.parse(localStorage.getItem('student-user')||'{}'),
    pageSize:5,   //每页的个数
    pageNum:1     //当前的页码
})

const load=()=>{
    request.get("/course/selectPage",{
        params:{
            pageNum:data.pageNum,
            pageSize:data.pageSize,
            name:data.name,
            no:data.no,
            teacher:data.teacher
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
    data.teacher=''
    data.no=''
    load()
}
const selectCourse=(row)=>{
    request.post('/studentCourse/add',{studentId:data.student.id,name:row.name,no:row.no,courseId:row.id}).then(res=>{
        if(res.code==='200'){
            ElMessage.success("操作成功")
        }else{
            ElMessage.error(res.msg)
        }
    })
}
</script>