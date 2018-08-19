<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input size="mini" class="filter-item item" v-model="form.account" placeholder="请输入账号"></el-input>
      <el-input size="mini" class="filter-item item" v-model="form.name" placeholder="请输入姓名"></el-input>
      <el-cascader size="mini" class="filter-item item" :options="all_departments" :show-all-levels="false" change-on-select @change="change_form_department" placeholder="请选择所属部门"></el-cascader>
      <el-select size="mini" class="filter-item item" v-model="form.roleId" placeholder="请选择用户角色">
        <el-option
          v-for="item in all_roles"
          :key="item.id"
          :label="item.name"
          :value="item.id">
        </el-option>
      </el-select>
      <el-select size="mini" class="filter-item item" v-model="form.status" placeholder="请选择用户状态">
        <el-option
          v-for="item in all_status"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-search" @click="ajax_query" v-waves>搜索</el-button>
      <el-button size="mini" class="filter-item" type="primary" icon="el-icon-plus" @click="open_add">添加</el-button>
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
        label="手机号"
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
          <el-tag size="mini" type="success" v-if="scope.row.status==1">启用</el-tag>
          <el-tag size="mini" type="warning" v-if="scope.row.status==2">冻结</el-tag>
          <el-tag size="mini" type="danger" v-if="scope.row.status==3">删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="open_edit(scope.row.account)">编辑</el-button>
          <el-button size="mini" type="danger" :disabled="scope.row.status==3" @click="handle_delete(scope.row.account)">删除</el-button>
        </template>
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

    <el-dialog title="添加用户" :visible.sync="add_form_visible" width="500px">
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
          <el-cascader :options="departments" change-on-select v-model="add_form.deptIds" :show-all-levels="false" @change="change_add_form_department"></el-cascader>
        </el-form-item>
        <el-form-item label="用户角色">
          <el-select v-model="add_form.roleId" placeholder="用户角色">
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
        <el-button size="mini" @click="add_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_add">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="edit_form_visible" width="500px">
      <el-form size="mini" label-width="100px">
        <el-form-item label="用户账号">
          <el-input v-model="edit_form.account" placeholder="请输入用户账号" disabled/>
        </el-form-item>
        <el-form-item label="用户姓名">
          <el-input v-model="edit_form.name" placeholder="请输入用户姓名"/>
        </el-form-item>
        <el-form-item label="用户密码">
          <el-input v-model="edit_form.password" placeholder="请输入用户密码"/>
        </el-form-item>
        <el-form-item label="所属部门">
          <el-cascader :options="departments" change-on-select v-model="edit_form.deptIds" :show-all-levels="false" @change="change_edit_form_department"></el-cascader>
        </el-form-item>
        <el-form-item label="用户角色">
          <el-select v-model="edit_form.roleId" placeholder="用户角色">
            <el-option
              v-for="item in roles"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户状态">
          <el-select v-model="edit_form.status" placeholder="用户状态">
            <el-option
              v-for="item in status"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
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
  import {user_query, user_get, user_add, user_delete, user_modify} from '@/api/user'
  import store from '@/store'
  import {Message, MessageBox} from 'element-ui'

  export default {
    directives: {
      waves
    },
    data() {
      return {
        status: [
          {label: '启用', value: 1},
          {label: '冻结', value: 2},
          {label: '删除', value: 3}
        ],
        roles: [],
        departments: [],
        form: {
          status: undefined,
          account: undefined,
          name: undefined,
          deptId: undefined,
          roleId: undefined,
          pageIndex: 1,
          pageSize: 15
        },
        add_form: {},
        edit_form: {},
        tableData: [],
        total: undefined,
        add_form_visible: false,
        edit_form_visible: false
      }
    },
    computed: {
      all_departments: function () {
        return [].concat({label: "全部部门", value: 0}).concat(this.departments)
      },
      all_roles: function () {
        return [].concat([{name: '全部角色', id: 0}]).concat(this.roles)
      },
      all_status: function () {
        return [].concat([{label: '全部状态', value: 0},]).concat(this.status)
      }
    },
    methods: {
      change_add_form_department(val) {
        if (val == undefined || val.length == 0) {
          this.add_form.deptId = undefined
        } else {
          this.add_form.deptId = val[val.length - 1]
        }
      },
      change_edit_form_department(val) {
        if (val == undefined || val.length == 0) {
          this.edit_form.deptId = undefined
        } else {
          this.edit_form.deptId = val[val.length - 1]
        }
      },
      change_form_department(val) {
        if (val == undefined || val.length == 0) {
          this.form.deptId = undefined
        } else {
          this.form.deptId = val[val.length - 1]
        }
      },
      open_add() {
        this.add_form = {
          password: undefined,
          account: undefined,
          name: undefined,
          deptId: undefined,
          deptIds: undefined,
          roleId: undefined
        }
        this.add_form_visible = true
      },
      open_edit(account) {
        this.edit_form = {
          status: undefined,
          account: undefined,
          password: undefined,
          name: undefined,
          deptId: undefined,
          deptIds: undefined,
          roleId: undefined
        }
        var that = this
        this.edit_form_visible = true
        user_get(account).then(res => {
          that.edit_form = {
            status: res.data.status,
            account: res.data.account,
            name: res.data.name,
            deptIds: store.getters.departmentsMap[res.data.departmentId],
            deptId: res.data.departmentId,
            roleId: res.data.roleId
          }
        })
      },
      handle_size_change(val) {
        this.form.pageSize = val
        this.form.pageIndex = 1
        this.ajax_query()
      },
      handle_current_change(val) {
        this.form.pageIndex = val
        this.ajax_query()
      },
      handle_update() {
        var that = this;
        user_modify(this.edit_form).then(res => {
          that.edit_form_visible = false
          Message({message: '修改成功', type: 'success'});
          that.ajax_query()
        })

      },
      handle_delete(account) {
        var that = this;
        MessageBox.confirm('此操作将删除此用户, 是否继续?', '提示').then(() => {
          user_delete(account).then(res => {
            Message({message: '删除成功', type: 'success'});
            that.ajax_query()
          })
        }).catch(() => {
        })
      },
      handle_add() {
        var that = this;
        user_add(this.add_form).then(res => {
          that.add_form_visible = false
          Message({message: '添加成功', type: 'success'});
          that.ajax_query()
        })
      },
      ajax_query: function () {
        var that = this
        user_query(this.form).then(res => {
          that.total = res.data.total
          that.tableData = res.data.list
        })
      }
    },
    created() {
      console.log("【view created】->" + this.$route.path)
    },
    mounted() {
      var that = this;
      this.ajax_query();
      store.dispatch("roles", {fromCache: true}).then(roles => that.roles = roles)
      store.dispatch("departments", {fromCache: true}).then(departments => that.departments = departments)
      console.log("【view mounted】->" + this.$route.path)
    },
    destroyed() {
      console.log("【view destroyed】->" + this.$route.path)
    }
  }
</script>
