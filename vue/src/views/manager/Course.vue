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
      <div style="margin-bottom: 10px">
        <el-button type="primary" @click="handleAdd">新增</el-button>
      </div>
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
                <el-button type="primary"  @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="danger"  @click="del(scope.row.id)">删除</el-button>
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
      <el-dialog width="35%" title="课程信息" v-model="data.formVisible">
          <el-form :model="data.form" label-width="100px" label-position="right" style="padding-right: 40px">
              <el-form-item label="课程名称" >
                  <el-input v-model="data.form.name" autocomplete="off"/>
              </el-form-item>
              <el-form-item label="课程编号" >
                  <el-input v-model="data.form.no" autocomplete="off"/>
              </el-form-item>
              <el-form-item label="课程描述" >
                  <el-input v-model="data.form.descr" autocomplete="off"/>
              </el-form-item>
              <el-form-item label="课时" >
                  <el-input v-model="data.form.times" autocomplete="off"/>
              </el-form-item>
              <el-form-item label="任课老师" >
                  <el-input v-model="data.form.teacher" autocomplete="off"/>
              </el-form-item>
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
import {reactive} from "vue";
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const data=reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
   total:0,
    formVisible:false,
    form:{},
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

const handleAdd=()=>{
    data.form={}
    data.formVisible=true

}
//保存数据到后台
const save=()=>{
    request.request({
        url:data.form.id?'/course/update':'/course/add',
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

const handleEdit=(row)=>{
    data.form=JSON.parse(JSON.stringify(row))
    data.formVisible=true
}

const del=(id)=>{
    ElMessageBox.confirm('删除数据后无法恢复，您确认删除吗？','删除确认',{type:'warning'}).then(res=>{
        request.delete("/course/delete/"+id).then(res=>{
            if(res.code==='200'){
                load()
                ElMessage.success("操作成功")
            }else{
                ElMessage.error(res.msg)
            }
        })
    }).catch(res=>{})
}

</script>