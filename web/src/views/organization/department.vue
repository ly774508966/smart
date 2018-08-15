<template>
  <div class="table_container">
    <tree-table :data="data" border size="mini" :columns="columns" :expandAll="true">
      <el-table-column label="操作" align="center" width="300">
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

  export default {
    name: 'treeTableDemo',
    components: {treeTable},
    data() {
      return {
        columns: [
          {
            value: "event",
            text: "部门名称",
            width: 180
          }, {
            value: "id",
            text: "部门编号",
            width: 180
          }, {
            value: "timeLine",
            text: "主管",
            width: 180
          }, {
            value: "comment",
            text: "备注"
          }],
        data: [
          {
            id: 1,
            event: 'XXX公司',
            timeLine: '张三',
            comment: '无',
            children: [
              {
                id: 2,
                event: '财务部',
                timeLine: '张三',
                comment: '无'
              },
              {
                id: 3,
                event: '市场部',
                timeLine: '张三',
                comment: '无',
                children: [
                  {
                    id: 4,
                    event: '市场一部',
                    timeLine: '张三',
                    comment: '无'
                  },
                  {
                    id: 5,
                    event: '市场二部',
                    timeLine: '张三',
                    comment: '无'
                  }
                ]
              }
            ]
          }
        ]
      }
    }
  }
</script>
