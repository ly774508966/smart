<template>
  <div class="table_container">
    <tree-table :data="data" border size="mini" :columns="columns">
      <el-table-column label="操作" align="center" width="300">
        <template slot-scope="scope">
          <el-button size="mini" type="success">添加子菜单</el-button>
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
          value: "zhName",
          text: "菜单中文",
          width: 180
        }, {
          value: "name",
          text: "菜单英文",
          width: 180
        }, {
          value: "link",
          text: "路径",
          width: 180
        }, {
          value: "icon",
          text: "图标",
          width: 180
        }, {
          value: "visible",
          text: "是否可见",
          width: 180
        }, {
          text: "备注"
        }],
        data: [
          {
            name: 'home',
            zhName: '首页',
            icon: 'fa-home',
            link: '',
            visible: true
          },
          {
            name: '404',
            zhName: '404页面',
            link: '/404',
            visible: false
          },
          {
            name: 'order',
            zhName: '订单管理',
            icon: 'fa-file-text',
            visible: true,
            children: [
              {
                name: 'good',
                zhName: '商品管理',
                link: '/order/good',
                visible: true
              },
              {
                name: 'send',
                zhName: '配送管理',
                link: '/order/send',
                visible: true
              }
            ]
          },
          {
            name: 'user',
            zhName: '人员管理',
            icon: 'fa-user',
            visible: true,
            children: [
              {
                name: 'good',
                zhName: '商品管理',
                link: '/order/good',
                visible: true
              },
              {
                name: 'send',
                zhName: '配送管理',
                link: '/order/send',
                visible: true
              }
            ]
          },
          {
            name: 'users',
            zhName: '组织管理',
            icon: 'fa-users',
            visible: true,
            children: [
              {
                name: 'good',
                zhName: '商品管理',
                link: '/order/good',
                visible: true
              },
              {
                name: 'send',
                zhName: '配送管理',
                link: '/order/send',
                visible: true
              }
            ]
          }
        ]
      }
    }
  }
</script>
