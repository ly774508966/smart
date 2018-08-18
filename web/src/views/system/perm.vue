<template>
  <div class="table_container">
    <div class="filter-container">
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" v-waves>添加根权限</el-button>
    </div>
    <tree-table :data="tableData" border size="mini" :columns="columns">
      <el-table-column label="类型" align="center" width="100">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.type==1">菜单</el-tag>
          <el-tag size="mini" type="success" v-if="scope.row.type==2">菜单分类</el-tag>
          <el-tag size="mini" v-if="scope.row.type==3">请求</el-tag>
          <el-tag size="mini" v-if="scope.row.type==4">请求分类</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" width="100">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.status==1">启用</el-tag>
          <el-tag size="mini" type="danger" v-if="scope.row.status==2">停用</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="success">添加子权限</el-button>
          <el-button size="mini" type="warning">编辑</el-button>
        </template>
      </el-table-column>
    </tree-table>
  </div>
</template>

<script>
  import treeTable from '@/components/TreeTable'
  import {permission_query_tree} from '@/api/permission'

  export default {
    components: {treeTable},
    methods: {
      ajax_query: function () {
        function convertDate(items, nodes) {
          nodes.forEach(node => {
            var tmp = {
              id: node.node.id,
              code: node.node.code,
              name: node.node.name,
              url: node.node.url,
              type: node.node.type,
              menuSort: node.node.menuSort,
              menuIcon: node.node.menuIcon,
              status: node.node.status,
              isLog: node.node.isLog,
              children: []
            }
            convertDate(tmp.children, node.subs);
            items.push(tmp)
          })
          return items;

        }

        var that = this
        permission_query_tree().then(res => {
          that.tableData = convertDate([], res.data.subs)
        })
      }
    },
    data() {
      return {
        columns: [
          {
            value: "name",
            text: "名称",
            width: 180
          }, {
            value: "code",
            text: "编号",
            width: 180
          }, {
            value: "url",
            text: "路径",
            width: 180
          }, {
            value: "menuIcon",
            text: "图标",
            width: 180
          }, {
            value: "isMenuOpen",
            text: "菜单是否打开",
            width: 100
          }, {
            value: "menuSort",
            text: "菜单排序",
            width: 100
          }, {
            value: "isLog",
            text: "请求日志记录",
            width: 100
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
