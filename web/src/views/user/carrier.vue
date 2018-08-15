<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="上楼工姓名"></el-input>
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
        width="180"
        align="center"
        label="是否可用">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.able">可用</el-tag>
          <el-tag size="mini" type="danger" v-if="!scope.row.able">不可用</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        width="180"
        align="center"
        label="是否绑定微信">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.isBindWX">已绑定</el-tag>
          <el-tag size="mini" type="danger" v-if="!scope.row.isBindWX">未绑定</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="备注">
      </el-table-column>
      <el-table-column label="操作" align="center" width="250">
        <template slot-scope="scope">
          <el-button size="mini" type="primary">资质</el-button>
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
        tableData: [
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: false
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: false,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          }, {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: false
          }
          , {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          },
          {
            name: '张三',
            phone: '13281821921',
            plateNumber: '皖AH12344',
            able: true,
            isBindWX: true
          }
        ]
      }
    }
  }
</script>
