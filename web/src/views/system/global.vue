<template>
  <div class="table_container">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="公司名称">
        <el-input v-model="form.region" style="width: 200px;" size="mini"></el-input>
        （管理系统登录时展示，小程序端展示）
      </el-form-item>
      <el-form-item label="惩罚天数">
        <el-input v-model="form.name" style="width: 200px;" size="mini"></el-input>
        （司机取消接单后，N天内不许接单）
      </el-form-item>
      <el-form-item label="自动下发">
        <el-switch v-model="form.delivery"></el-switch>
        （路线创建成功后，是否自动下发通知）
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">保存</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          name: '3',
          region: 'XXX有限公司',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }
</script>
