<template>
  <div class="table_container">

    <div class="filter-container">
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" v-waves @click="open_add(0)">添加公司
      </el-button>
    </div>

    <tree-table :data="tableData" border size="mini" :columns="columns" :expandAll="true">
      <el-table-column label="操作" align="center" width="500">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="open_add(scope.row.id)">添加子部门</el-button>
          <el-button size="mini" type="warning" @click="open_edit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="handle_delete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </tree-table>

    <el-dialog :title="add_form.parentId==0?'添加公司':'添加子部门'" :visible.sync="add_form_visible" width="500px">
      <el-form size="mini" label-width="100px">
        <el-form-item label="部门名称">
          <el-input v-model="add_form.name" placeholder="请输入部门名称"/>
        </el-form-item>
        <el-form-item label="部门描述">
          <el-input v-model="add_form.description" placeholder="请输入部门描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="add_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_add">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑" :visible.sync="edit_form_visible" width="500px">
      <el-form size="mini" label-width="100px">
        <el-form-item label="部门名称">
          <el-input v-model="edit_form.name" placeholder="请输入部门名称"/>
        </el-form-item>
        <el-form-item label="部门描述">
          <el-input v-model="edit_form.description" placeholder="请输入部门描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="edit_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_update">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令
  import store from '@/store'
  import {Message, MessageBox} from 'element-ui'
  import {department_query, department_add, department_remove, department_modify} from '@/api/department'
  import treeTable from '@/components/TreeTable'
  export default {
    directives: {
      waves
    },
    components: {treeTable},
    methods: {
      open_add(id) {
        this.add_form = {
          parentId: id,
          name: undefined,
          description: undefined,
        }
        this.add_form_visible = true
      },
      open_edit: function (row) {
        this.edit_form = {
          id: row.id,
          parentId: row.parentId,
          name: row.name,
          description: row.description,
        }
        this.edit_form_visible = true
      },
      handle_add() {
        var that = this;
        department_add(this.add_form).then(res => {
          that.add_form_visible = false
          Message({message: '添加成功', type: 'success'});
          that.ajax_query()
        })
      },
      handle_update() {
        var that = this;
        department_modify(this.edit_form).then(res => {
          that.edit_form_visible = false
          Message({message: '修改成功', type: 'success'});
          that.ajax_query()
        })
      },
      handle_delete(id) {
        var that = this;
        MessageBox.confirm('此操作将删除此部门, 是否继续?', '提示').then(() => {
          department_remove(id).then(res => {
            Message({message: '删除成功', type: 'success'});
            that.ajax_query()
          })
        }).catch(() => {
        })
      },
      ajax_query: function () {
        var that = this
        store.dispatch("departments", {fromCache: false}).then(departments => {
          that.tableData = departments
        })
      }
    },
    data() {
      return {
        columns: [
          {
            value: "name",
            text: "部门名称",
            width: 180
          }, {
            value: "description",
            text: "部门描述"
          }],
        tableData: [],
        add_form: {},
        edit_form: {},
        add_form_visible: false,
        edit_form_visible: false,
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
    }
  }
</script>
