<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" v-model="form.account" placeholder="账号"></el-input>
      <el-input size="mini" class="filter-item item" v-model="form.name" placeholder="姓名"></el-input>
      <el-select size="mini" class="filter-item item" v-model="form.dept_id" placeholder="部门">
        <el-option
          v-for="item in all_dept"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-select size="mini" class="filter-item item" v-model="form.role_id" placeholder="角色">
        <el-option
          v-for="item in all_roles"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      <el-select size="mini" class="filter-item item" v-model="form.status" placeholder="状态">
        <el-option
          v-for="item in all_status"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-search" @click="ajax_query" v-waves>搜索</el-button>
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-plus" @click="add_form.visible=true" v-waves>添加</el-button>
    </div>

    <el-table
      :data="tableData"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        prop="account"
        label="账号"
        width="180">
      </el-table-column>
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
        prop="roleName"
        width="180"
        label="角色">
      </el-table-column>
      <el-table-column
        prop="departmentName"
        width="180"
        label="部门">
      </el-table-column>
      <el-table-column
        width="180"
        align="center"
        label="状态">
        <template slot-scope="scope">
          <el-tag size="mini" type="success" v-if="scope.row.status" v-waves>启用</el-tag>
          <el-tag size="mini" type="warning" v-if="!scope.row.status" v-waves>冻结</el-tag>
          <el-tag size="mini" type="danger" v-if="!scope.row.status" v-waves>删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" v-waves>编辑</el-button>
          <el-button size="mini" type="warning" v-waves>重置密码</el-button>
          <el-button size="mini" type="danger" v-waves>删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      :small="true"
      layout="total, prev, pager, next"
      @size-change="handle_size_change"
      @current-change="handle_current_change"
      :page-size="form.page_size"
      :current-page="form.page_index"
      :total="total">
    </el-pagination>

    <el-dialog title="添加用户" :visible.sync="add_form.visible" class="dialog_mini" width="500px">
      <el-form size="mini" label-width="100px">
        <el-form-item label="用户账号">
          <el-input v-model="add_form.account" placeholder="请输入用户账号"/>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="add_form.name" placeholder="请输入用户姓名"/>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="add_form.password" placeholder="请输入用户密码"/>
        </el-form-item>
        <el-form-item label="所属部门">
          <el-select v-model="add_form.dept_id" placeholder="所属部门">
            <el-option
              v-for="item in all_dept"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户角色">
          <el-select v-model="add_form.role_id" placeholder="用户角色">
            <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="add_form.visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="add_form.visible = false">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="update_form.visible">
      修改用户
      <div slot="footer">
        <el-button size="mini" @click="update_form.visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="update_form.visible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令
  import {user_query} from '@/api/user'
  import store from '@/store'

  export default {
    directives: {
      waves
    },
    data() {
      return {
        all_status: [
          {label: '全部状态', value: 0},
          {label: '启用', value: 1},
          {label: '冻结', value: 2},
          {label: '删除', value: 3}
        ],
        all_dept: [{label: "全部部门", value: 0}],
        roles: store.getters.roles,
        form: {
          status: 0,
          account: undefined,
          name: undefined,
          dept_id: 0,
          role_id: 0,
          page_index: 1,
          page_size: 10
        },
        add_form: {
          visible: false,
          password: undefined,
          account: undefined,
          name: undefined,
          dept_id: undefined,
          role_id: undefined
        },
        update_form: {
          visible: false,
          status: undefined,
          account: undefined,
          name: undefined,
          dept_id: undefined,
          role_id: undefined
        },
        tableData: [],
        total: undefined
      }
    },
    computed: {
      all_roles: function () {
        return [].concat([{name: '全部角色', id: 0}]).concat(this.roles)
      }
    },
    methods: {
      handle_size_change(val) {
        this.form.page_size = val
        this.form.page_index = 1
        this.ajax_query()
      },
      handle_current_change(val) {
        this.form.page_index = val
        this.ajax_query()
      },
      handle_reset() {

      },
      handle_edit() {

      },
      handle_delete() {

      },
      handle_add() {

      },
      ajax_query: function () {
        var that = this
        user_query(this.form).then(res => {
          that.total = res.data.total
          that.tableData = res.data.list
        })
      },
      ajax_get: function (account) {

      },
      ajax_add: function () {

      },
      ajax_modify: function () {

      },
      ajax_delete: function () {
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
