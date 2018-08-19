<template>
  <div class="table_container">
    <div class="filter-container">
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus">添加部门</el-button>
    </div>
    <tree-table :data="tableData" border size="mini" :columns="columns" :expandAll="true">
      <el-table-column label="操作" align="center" width="500">
        <template slot-scope="scope">
          <el-button size="mini" type="success">添加子部门</el-button>
          <el-button size="mini" type="warning">编辑</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </tree-table>
  </div>
</template>

<script>
  import treeTable from '@/components/TreeTable'
  import store from '@/store'


  export default {
    components: {treeTable},
    methods: {
      ajax_query: function () {
        var that = this
        store.dispatch("departments", {fromCache: false}).then(departments => {
          that.tableData = departments
        })
      }
    },
    data() {
      return {
        columns: [
          {
            value: "name",
            text: "部门名称",
            width: 180
          }, {
            value: "description",
            text: "部门描述"
          }],
        tableData: []
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
