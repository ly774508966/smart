<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="导购姓名"></el-input>
      <el-select size="mini" v-model="form.distributor" class="filter-item item" placeholder="请选择所属代理商">
        <el-option label="全部" value="all"></el-option>
        <el-option label="代理商一" value="shanghai"></el-option>
        <el-option label="代理商二" value="beijing"></el-option>
      </el-select>
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
        label="姓名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="phone"
        label="手机"
        width="180">
      </el-table-column>
      <el-table-column
        prop="distributor"
        width="180"
        label="所属代理商">
      </el-table-column>
      <el-table-column
        label="备注">
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
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
        form: {
          distributor: undefined
        },
        tableData: [
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          }, {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          }
          , {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          },
          {
            name: '张三',
            phone: '13281821921',
            distributor: '代理商1'
          }
        ]
      }
    }
  }
</script>
