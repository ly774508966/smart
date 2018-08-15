<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="角色名称"></el-input>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-search">搜索</el-button>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-edit" style="margin-left: 10px;">添加</el-button>
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
        width="180"
        align="center"
        label="是否可用">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.able">可用</el-tag>
          <el-tag size="mini" type="danger" v-if="!scope.row.able">不可用</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="备注">
      </el-table-column>
      <el-table-column label="操作" align="center" width="350">
        <template slot-scope="scope">
          <el-button size="mini" type="primary">查看权限</el-button>
          <el-button size="mini" type="warning" @click="addDialogVisible=true">编辑权限</el-button>
          <el-button size="mini" type="warning">编辑</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑权限" width="600px" :visible.sync="addDialogVisible">
      <el-tree
        :data="data2"
        show-checkbox
        node-key="id"
        :default-expanded-keys="[2, 3]"
        :default-checked-keys="[5]"
        :props="defaultProps">
      </el-tree>
      <div slot="footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addDialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令

  export default {
    directives: {
      waves
    },
    data() {
      return {
        tableData: [
          {
            name: "超级管理员",
            able: true
          },
          {
            name: "订单录入员",
            able: true
          },
          {
            name: "客服人员",
            able: true
          },
          {
            name: "数据分析员",
            able: true
          },
          {
            name: "资质审核员",
            able: true
          }
        ],
        data2: [
          {
            id: 1,
            label: '首页',
            children: [{
              id: 4,
              label: '订单管理',
              children: [{
                id: 9,
                label: '商品管理'
              }, {
                id: 10,
                label: '配送管理'
              }]
            }]
          }, {
            id: 2,
            label: '系统管理',
            children: [{
              id: 5,
              label: '角色管理'
            }, {
              id: 6,
              label: '日志管理'
            }]
          }, {
            id: 3,
            label: '数据统计',
            children: [{
              id: 7,
              label: '上楼工统计'
            }, {
              id: 8,
              label: '司机统计'
            }]
          }],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        addDialogVisible: false
      }
    }
  }
</script>
