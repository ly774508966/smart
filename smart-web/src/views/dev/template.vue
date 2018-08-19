<template>
  <div class="table_container">
    <div class="filter-container">
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-search" v-waves>搜索按钮样式（注意点击效果）</el-button>
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-plus">添加按钮样式</el-button>
      <el-button size="mini" class="filter-item" type="primary" @click="open_form">弹框</el-button>
    </div>

    <el-dialog title="弹出框" width="500px" :visible.sync="form_visible">
      <el-form size="mini" label-width="100px">
        <el-form-item label="表单项1">
          <el-input placeholder="请输入表单项1" v-model="form.val_1"></el-input>
        </el-form-item>
        <el-form-item label="表单项2">
          <el-input placeholder="请输入表单项2" v-model="form.val_2"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="form_visible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style scoped>

</style>
<script>
  import waves from '@/directive/waves' // 水波纹指令
  export default {
    components: {},
    directives: {
      waves
    },
    computed: {},
    methods: {
      open_form: function () {
        this.form_visible = true
        this.form = {
          val_1: undefined,
          val_2: undefined
        }
      }
    },
    data() {
      return {
        form_visible: false,
        form: {}
      }
    },
    created() {
      console.log("【view created】->" + this.$route.path)
    },
    mounted() {
      console.log("【view mounted】->" + this.$route.path)
    },
    destroyed() {
      console.log("【view destroyed】->" + this.$route.path)
    }
  }
</script>
