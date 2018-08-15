<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="代理商名称"></el-input>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-search">搜索</el-button>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-edit" style="margin-left: 10px;">添加</el-button>
    </div>

    <el-table
      :data="tableData"
      border
      size="mini"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="代理商"
        width="300">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址">
      </el-table-column>
      <el-table-column
        prop="leader"
        label="负责人"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="联系电话"
        width="180">
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="180">
        <template slot-scope="scope">
          <el-button size="mini" type="warning">编辑</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
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
        tableData: [{
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        }, {
          name: '红星美凯龙XXX',
          leader: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: "13718219876"
        },]
      }
    }
  }
</script>
