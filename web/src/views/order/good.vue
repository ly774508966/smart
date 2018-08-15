<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input class="filter-item item" size="mini" placeholder="商品编号"></el-input>
      <el-input class="filter-item item" size="mini" placeholder="商品名称"></el-input>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-search">搜索</el-button>
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-edit" style="margin-left: 10px;" @click="addDialogVisible = true">添加</el-button>
    </div>
    <el-table
      size="mini"
      :height="clientHeight"
      :data="tableData"
      :border="true"
      style="width: 100%">
      <el-table-column
        prop="no"
        label="商品编号"
        width="150px">
      </el-table-column>
      <el-table-column
        prop="name"
        label="商品名称"
        width="300px">
      </el-table-column>
      <el-table-column
        prop="unit"
        label="计量单位"
        align="center"
        width="80px">
      </el-table-column>
      <el-table-column
        prop="description"
        label="商品备注">
      </el-table-column>
      <el-table-column align="center" label="操作" width="250px">
        <template slot-scope="scope">
          <el-button size="mini" type="warning">编辑</el-button>
          <el-button size="mini" type="danger">删除</el-button>
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
    <el-dialog title="添加商品" width="600px" :visible.sync="addDialogVisible">
      <el-form size="mini" label-width="80px">
        <el-form-item label="商品编号">
          <el-input placeholder="请输入商品编号"/>
        </el-form-item>
        <el-form-item label="商品名称">
          <el-input placeholder="请输入商品名称"/>
        </el-form-item>
        <el-form-item label="计量单位">
          <el-input placeholder="请输入计量单位"/>
        </el-form-item>
        <el-form-item label="商品备注">
          <el-input placeholder="请输入商品备注" type="textarea" :rows="3"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addDialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        addDialogVisible: false,
        clientHeight: undefined,
        tableData: [
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          },
          {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
          }, {
            no: 'TS5017-01',
            name: '兔宝宝E0级别1.7免漆木工板（杉木芯）银丝胡桃',
            unit: '把', description: ''
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
