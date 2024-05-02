<!--<template>-->
<!--  <div>-->

<!--    <div class="card" style="margin-bottom: 10px;">-->
<!--      <el-input style="width: 300px; margin-right: 10px" placeholder="请输入关键字查询"></el-input>-->
<!--      <el-button type="primary">查询</el-button>-->
<!--      <el-button type="info" style="margin: 0 10px">重置</el-button>-->
<!--    </div>-->

<!--    <div class="card" style="margin-bottom: 10px">-->
<!--      <div style="margin-bottom: 10px">-->
<!--        <el-button type="primary" @click="handleAdd">新增</el-button>-->
<!--      </div>-->
<!--      <el-table stripe :data="data.tableData">-->
<!--        <el-table-column label="名称" prop="name"></el-table-column>-->
<!--        <el-table-column label="描述" prop="descr"></el-table-column>-->
<!--        <el-table-column label="课时" prop="times"></el-table-column>-->
<!--        <el-table-column label="操作" align="center" width="160">-->
<!--          <template v-slot="scope">-->
<!--            <el-button type="primary" @click="handleEdit">编辑</el-button>-->
<!--            <el-button type="danger" @click="handleDelete">删除</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->
<!--    </div>-->

<!--    <div class="card">-->
<!--      <el-pagination background layout="prev, pager, next" :page-size="8" v-model:current-page="data.pageNum" :total="1000"/>-->
<!--    </div>-->

<!--    <el-dialog title="信息" width="40%" v-model="data.formVisible" :close-on-click-modal="false" destroy-on-close>-->
<!--      <el-form :model="data.form" label-width="100px" style="padding-right: 50px">-->
<!--        <el-form-item label="名称" prop="name">-->
<!--          <el-input v-model="data.form.name" autocomplete="off" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="描述" prop="descr">-->
<!--          <el-input v-model="data.form.descr" autocomplete="off" />-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--      <template #footer>-->
<!--      <span class="dialog-footer">-->
<!--        <el-button @click="data.formVisible = false">取 消</el-button>-->
<!--        <el-button type="primary" @click="data.formVisible = false">保 存</el-button>-->
<!--      </span>-->
<!--      </template>-->
<!--    </el-dialog>-->

<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import request from "@/utils/request";-->
<!--import {reactive} from "vue";-->
<!--import {ElMessageBox} from "element-plus";-->

<!--request.get('/').then(res => {-->
<!--  console.log(res)-->
<!--})-->

<!--const data = reactive({-->
<!--  pageNum: 1,-->
<!--  formVisible: false,-->
<!--  form: {},-->
<!--  tableData: [-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--    {name: '大学英语', descr: '大学英语真有趣', times: '24课时'},-->
<!--  ]-->
<!--})-->

<!--const handleAdd = () => {-->
<!--  data.form = {}-->
<!--  data.formVisible = true-->
<!--}-->
<!--const handleEdit = (row) => {-->
<!--  let form = JSON.parse(JSON.stringify(row))-->
<!--  data.formVisible = true-->
<!--}-->
<!--const handleDelete = (id) => {-->
<!--  ElMessageBox.confirm('删除后数据无法恢复，您确定删除吗?', '删除确认', { type: 'warning' }).then(res => {-->
<!--    console.log('删除')-->
<!--  }).catch(err => {-->
<!--    console.error(err)-->
<!--  })-->
<!--}-->
<!--</script>-->
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
        <el-button type="primary">新增</el-button>
      </div>
      <div>
        <el-table :data="data.tableData" style="width: 100%">
          <el-table-column prop="id" label="序号" width="70"/>
          <el-table-column prop="name" label="课程名称"/>
          <el-table-column prop="no" label="课程编号"/>
          <el-table-column prop="descr" label="课程描述"/>
          <el-table-column prop="times" label="时间"/>
          <el-table-column prop="teacher" label="任课老师"/>
          <el-table-column>
            <template #default="scope">
                <el-button type="primary" plain>编辑</el-button>
                <el-button type="danger" plain>删除</el-button>
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
import {Search} from "@element-plus/icons-vue";
import {reactive} from "vue";
import request from "@/utils/request";

const data=reactive({
  name:'',
  no:'',
  teacher:'',
  tableData:[],
   total:0,
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
</script>