<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" v-model="form.account" class="filter-item item" placeholder="操作账号"></el-input>
      <el-select v-model="form.type" placeholder="操作类型" size="mini" class="filter-item item">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-date-picker class="filter-item item" v-model="form.fromDate" size="mini" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="开始时间"></el-date-picker>
      <el-date-picker class="filter-item item" v-model="form.toDate" size="mini" type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="结束时间"></el-date-picker>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search" @click="ajax_query" v-waves>搜索</el-button>
    </div>

    <el-table
      :data="tableData"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        prop="account"
        label="操作账号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="isLogin"
        label="操作类型"
        width="180">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.isLogin==1">登录</el-tag>
          <el-tag size="mini" type="danger" v-if="scope.row.isLogin==0">注销</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="ip"
        width="180"
        label="操作IP">
      </el-table-column>
      <el-table-column
        width="250"
        prop="result"
        label="操作结果">
      </el-table-column>
      <el-table-column
        prop="createtime"
        label="操作时间">
      </el-table-column>
    </el-table>

    <el-pagination
      style="margin-top:10px "
      background
      :small="true"
      layout="total, prev, pager, next"
      @size-change="handle_size_change"
      @current-change="handle_current_change"
      :page-size="form.pageSize"
      :current-page="form.pageIndex"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令
  import {log_login} from '@/api/log'

  export default {
    directives: {
      waves
    },
    data() {
      return {
        tableData: [],
        form: {
          pageIndex: 1,
          pageSize: 15,
          account: undefined,
          type: 0,
          fromDate: undefined,
          toDate: undefined
        },
        options: [
          {
            value: 0,
            label: '全部操作类型'
          }, {
            value: 1,
            label: '登录'
          }, {
            value: 2,
            label: '注销'
          }],
        total: undefined
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
    },
    methods: {
      handle_size_change(val) {
        this.form.pageSize = val
        this.form.pageIndex = 1
        this.ajax_query()
      },
      handle_current_change(val) {
        this.form.pageIndex = val
        this.ajax_query()
      },
      ajax_query: function () {
        var that = this
        log_login(this.form).then(res => {
          that.total = res.data.total
          that.tableData = res.data.list
        })
      }
    }
  }
</script>
