<template>
  <div>
  <div style="min-height: 100vh; overflow: hidden; display:flex">
    <div style="width: 350px">
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="活动名称">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        </el-form>
    </div>

  </div>
  </div>
</template>
<script setup>

</script>