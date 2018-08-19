<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="角色名称"></el-input>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" v-waves>搜索</el-button>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" style="margin-left: 10px;">添加</el-button>
    </div>

    <el-table
      :data="tableData"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        prop="name"
        label="角色名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="description"
        label="角色描述">
      </el-table-column>
      <el-table-column label="操作" align="center" width="500">
        <template slot-scope="scope">
          <el-button size="mini" type="warning">编辑</el-button>
          <el-button size="mini" type="primary" @click="edit_permission_visible=true">编辑权限</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑权限" width="500px" :visible.sync="edit_permission_visible">
      <el-tree
        :data="permissions"
        show-checkbox
        node-key="id"
        :default-expanded-keys="[2, 3]">
      </el-tree>
      <div slot="footer">
        <el-button size="mini" @click="edit_permission_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="edit_permission_visible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令
  import store from '@/store'

  export default {
    directives: {
      waves
    },
    methods: {
      ajax_query: function () {
        var that = this
        store.dispatch("roles",{fromCache:false}).then(roles => {
          that.tableData = roles
        })
      }
    },
    data() {
      return {
        tableData: [],
        permissions: [],
        edit_permission_visible: false
      }
    },
    created() {
      console.log("【view created】->" + this.$route.path)
    },
    mounted() {
      this.ajax_query();
      console.log("【view mounted】->" + this.$route.path)
    },
    destroyed() {
      console.log("【view destroyed】->" + this.$route.path)
    }
  }
</script>
