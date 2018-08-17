<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" placeholder="操作"></el-input>
      <el-select v-model="listQuery.customerPhone" placeholder="操作类型" size="mini" class="filter-item item">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-date-picker class="filter-item item" v-model="listQuery.fromDate" size="mini" type="datetime" placeholder="开始时间"></el-date-picker>
      <el-date-picker class="filter-item item" v-model="listQuery.toDate" size="mini" type="datetime" placeholder="结束时间"></el-date-picker>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-search">搜索</el-button>
    </div>

    <el-table
      :data="tableData"
      size="mini"
      border
      :height="clientHeight"
      style="width: 100%">
      <el-table-column
        prop="name"
        label="账号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="role"
        label="账号类型"
        width="180">
      </el-table-column>
      <el-table-column
        prop="time"
        width="180"
        label="操作时间">
      </el-table-column>
      <el-table-column
        width="180"
        prop="opType"
        label="操作类型">
      </el-table-column>
      <el-table-column
        label="描述">
      </el-table-column>
    </el-table>

    <el-pagination
      style="margin: 10px 0 0 0"
      background
      :small="true"
      layout="prev, pager, next"
      :total="1000">
    </el-pagination>
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
        clientHeight: undefined,
        tableData: [
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          }, {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          }
          , {
            name: 'admin',
            phone: '13281821921',
            role: '系统管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '客服人员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          }, {
            name: '张三',
            phone: '13281821921',
            role: '订单管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          }
          , {
            name: 'admin',
            phone: '13281821921',
            role: '系统管理员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '数据分析员',
            time: '2018-10-10 12:12:12',
            opType: '修改权限'
          }
        ],
        listQuery: {
          page: 1,
          limit: 20,
          customerName: undefined,
          customerPhone: undefined,
          fromDate: undefined,
          toDate: undefined
        },
        options: [
          {
            value: '选项1',
            label: '添加订单'
          }, {
            value: '选项3',
            label: '修改订单'
          }, {
            value: '选项4',
            label: '删除订单'
          }]
      }
    },
    mounted: function () {
      function computeClientHeight() {
        return (document.documentElement.clientHeight - 50 - 34 - 68 - 56 ) + "px";
      }

      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp() {
        that.clientHeight = computeClientHeight();
      };
    }
  }
</script>
