<template>
  <div class="table_container">
    <div class="filter-container">
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
        prop="time"
        width="180"
        label="日志时间">
      </el-table-column>
      <el-table-column
        prop="log"
        label="异常堆栈">
        <template slot-scope="scope">
          2018-05-17 13:05:03.164 [schedulerFactoryBean_Worker-5] ERROR com.hongwan.web.task.GetDataJob -<br/>
          ### Error updating database.  Cause: java.sql.SQLRecoverableException: 关闭的语句<br/>
          ### SQL: update djgl_t_sbxx         set zt = ?         where sbip = ?<br/>
          ### Cause: java.sql.SQLRecoverableException: 关闭的语句<br/>
          ; SQL []; 关闭的语句; nested exception is java.sql.SQLRecoverableException: 关闭的语句<br/>
          org.springframework.dao.RecoverableDataAccessException: <br/>
          ### Error updating database.  Cause: java.sql.SQLRecoverableException: 关闭的语句<br/>
          ### SQL: update djgl_t_sbxx         set zt = ?         where sbip = ?<br/>
          ### Cause: java.sql.SQLRecoverableException: 关闭的语句<br/>
          ; SQL []; 关闭的语句; nested exception is java.sql.SQLRecoverableException: 关闭的语句<br/>
          at org.springframework.jdbc.support.SQLExceptionSubclassTranslator.doTranslate(SQLExceptionSubclassTranslator.java:98)<br/>
          at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:73)<br/>
          at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)<br/>
          at org.mybatis.spring.MyBatisExceptionTranslator.translateExceptionIfPossible(MyBatisExceptionTranslator.java:73)<br/>
          at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:446)<br/>
          at com.sun.proxy.$Proxy71.update(Unknown Source)<br/>
          at org.mybatis.spring.SqlSessionTemplate.update(SqlSessionTemplate.java:294)<br/>
          at org.apache.ibatis.binding.MapperMethod.execute(MapperMethod.java:62)<br/>
          at org.apache.ibatis.binding.MapperProxy.invoke(MapperProxy.java:59)<br/>
          at com.sun.proxy.$Proxy75.updateSbzt(Unknown Source)<br/>
          at com.hongwan.web.task.SbipStatus.execute(SbipStatus.java:40)<br/>
          at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br/>
          at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br/>
          Caused by: java.sql.SQLRecoverableException: 关闭的语句<br/>
          at oracle.jdbc.driver.OracleClosedStatement.exitImplicitCacheToActive(OracleClosedStatement.java:4667)<br/>
          at oracle.jdbc.driver.OraclePreparedStatementWrapper.exitImplicitCacheToActive(OraclePreparedStatementWrapper.java:1607)<br/>
          at com.alibaba.druid.util.OracleUtils.exitImplicitCacheToActive(OracleUtils.java:79)<br/>
          at com.alibaba.druid.pool.PreparedStatementPool.get(PreparedStatementPool.java:66)<br/>
          at com.alibaba.druid.pool.DruidPooledConnection.prepareStatement(DruidPooledConnection.java:326)<br/>
          at org.apache.ibatis.executor.statement.PreparedStatementHandler.instantiateStatement(PreparedStatementHandler.java:87)<br/>
          at org.apache.ibatis.executor.statement.BaseStatementHandler.prepare(BaseStatementHandler.java:88)<br/>
          at org.apache.ibatis.executor.statement.RoutingStatementHandler.prepare(RoutingStatementHandler.java:59)<br/>
          at org.apache.ibatis.executor.SimpleExecutor.prepareStatement(SimpleExecutor.java:85)<br/>
          at org.apache.ibatis.executor.SimpleExecutor.doUpdate(SimpleExecutor.java:49)<br/>
          at org.apache.ibatis.executor.BaseExecutor.update(BaseExecutor.java:117)<br/>
          at org.apache.ibatis.executor.CachingExecutor.update(CachingExecutor.java:76)<br/>
          at sun.reflect.GeneratedMethodAccessor355.invoke(Unknown Source)<br/>
          at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)<br/>
          at java.lang.reflect.Method.invoke(Method.java:498)<br/>
          at org.apache.ibatis.plugin.Plugin.invoke(Plugin.java:63)<br/>
          at com.sun.proxy.$Proxy162.update(Unknown Source)<br/>
          at org.apache.ibatis.session.defaults.DefaultSqlSession.update(DefaultSqlSession.java:198)<br/>
          at sun.reflect.GeneratedMethodAccessor604.invoke(Unknown Source)<br/>
          at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)<br/>
          at java.lang.reflect.Method.invoke(Method.java:498)<br/>
          at org.mybatis.spring.SqlSessionTemplate$SqlSessionInterceptor.invoke(SqlSessionTemplate.java:433)
        </template>
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
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
          },
          {
            name: '张三',
            phone: '13281821921',
            role: '资质审核员',
            time: '2018-10-10 12:12:12',
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
        options: [{
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
      function computeClientHeight () {
        return (document.documentElement.clientHeight - 50 - 34 - 68 - 56 ) + "px";
      }

      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp () {
        that.clientHeight = computeClientHeight();
      };
    }
  }
</script>
