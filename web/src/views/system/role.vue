<template>
  <div class="table_container">
    <div class="filter-container">
      <el-button class="filter-item" size="mini" type="primary" icon="el-icon-plus" @click="open_add" style="margin-left: 10px;">添加</el-button>
    </div>

    <el-table :data="tableData" size="mini" border style="width: 100%">
      <el-table-column prop="name" label="角色名称" width="180">
      </el-table-column>
      <el-table-column prop="description" label="角色描述">
      </el-table-column>
      <el-table-column label="操作" align="center" width="500">
        <template slot-scope="scope">
          <el-button size="mini" type="warning" @click="open_edit(scope.row)">编辑</el-button>
          <el-button size="mini" type="primary" @click="open_edit_permission(scope.row.id)">编辑权限</el-button>
          <el-button size="mini" type="danger" @click="handle_delete(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="添加角色" width="500px" :visible.sync="add_form_visible">
      <el-form size="mini" label-width="100px">
        <el-form-item label="角色名称">
          <el-input v-model="add_form.name" placeholder="请输入角色名称"/>
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input v-model="add_form.description" placeholder="请输入角色描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="add_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_add">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑角色" width="500px" :visible.sync="edit_form_visible">
      <el-form size="mini" label-width="100px">
        <el-form-item label="角色名称">
          <el-input v-model="edit_form.name" placeholder="请输入角色名称"/>
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input v-model="edit_form.description" placeholder="请输入角色描述"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button size="mini" @click="edit_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_edit">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="编辑角色权限" width="500px" :visible.sync="edit_permission_form_visible">
      <el-tree
        :data="permissions"
        show-checkbox
        role_get_permissions
        :check-strictly="true"
        ref="tree"
        node-key="id">
      </el-tree>
      <div slot="footer">
        <el-button size="mini" @click="edit_permission_form_visible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="handle_edit_permission">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import waves from '@/directive/waves' // 水波纹指令
  import store from '@/store'
  import {Message, MessageBox} from 'element-ui'
  import {role_add, role_remove, role_modify, role_get_permissions, role_modify_permissions} from '@/api/role'
  import {permission_query_tree_simple} from '@/api/permission'

  export default {
    directives: {
      waves
    },
    methods: {
      open_add: function () {
        this.add_form = {
          name: undefined,
          description: undefined
        }
        this.add_form_visible = true
      },
      open_edit: function (row) {
        this.edit_form = {
          id: row.id,
          name: row.name,
          description: row.description
        }
        this.edit_form_visible = true
      },
      open_edit_permission: function (id) {
        function convertPermissions(items, nodes) {
          nodes.forEach(node => {
            var tmp = {
              id: node.node.id,
              label: node.node.name
            }
            if (node.subs != null && node.subs.length > 0) {
              tmp.children = []
              convertPermissions(tmp.children, node.subs);
            }
            items.push(tmp)
          })
          return items;
        }

        var that = this;
        this.edit_permission_id = id
        //TODO 权限变化不大，客户端可以缓存下
        permission_query_tree_simple().then(res => {
          return new Promise((resolve, reject) => {
            that.permissions = convertPermissions([], res.data.subs)
            that.edit_permission_form_visible = true;
            resolve()
          })
        }).then(() => {
          role_get_permissions(id).then(res => {
            that.$refs.tree.setCheckedKeys(res.data);
          })
        })

      },
      handle_add: function () {
        var that = this;
        role_add(this.add_form).then(res => {
          that.add_form_visible = false
          Message({message: '添加成功', type: 'success'});
          that.ajax_query()
        })
      },
      handle_edit: function () {
        var that = this;
        role_modify(this.edit_form).then(res => {
          that.edit_form_visible = false
          Message({message: '修改成功', type: 'success'});
          that.ajax_query()
        });

      },
      handle_edit_permission: function () {
        var that = this;
        role_modify_permissions(this.edit_permission_id, this.$refs.tree.getCheckedKeys().join(",")).then(res => {
          that.edit_permission_form_visible = false;
          Message({message: '修改成功', type: 'success'});
        })
      },
      handle_delete(id) {
        var that = this;
        MessageBox.confirm('此操作将删除此角色, 是否继续?', '提示').then(() => {
          role_remove(id).then(res => {
            Message({message: '删除成功', type: 'success'});
            that.ajax_query()
          })
        }).catch(() => {
        })
      },
      ajax_query: function () {
        var that = this
        store.dispatch("roles", {fromCache: false}).then(roles => {
          that.tableData = roles
        })
      }
    },
    data() {
      return {
        tableData: [],
        permissions: [],
        edit_permission_id: undefined,
        add_form: {},
        edit_form: {},
        add_form_visible: false,
        edit_form_visible: false,
        edit_permission_form_visible: false
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
