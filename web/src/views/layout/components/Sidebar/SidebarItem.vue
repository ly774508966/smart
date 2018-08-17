<template>
  <div class="menu-wrapper">
    <template v-for="item in routes">
      <!--折叠标签-->
      <el-submenu v-if="item.alwaysShow" :index="item.name||item.path" :key="item.name">
        <template slot="title">
          <i v-if="item.meta&&item.meta.icon" class="fa" :class="item.meta.icon"></i>
          <span v-if="item.meta&&item.meta.title">{{item.meta.title}}</span>
        </template>
        <template v-for="child in item.children">
          <!--有子菜单-->
          <sidebar-item :is-nest="true" class="nest-menu" v-if="child.children&&child.children.length>0" :routes="[child]" :key="child.path"></sidebar-item>
          <!--无子菜单-->
          <router-link v-else :to="child.path" :key="child.name">
            <el-menu-item :index="child.path">
              <i v-if="child.meta&&child.meta.icon" class="fa" :class="child.meta.icon"></i>
              <span v-if="child.meta&&child.meta.title">{{child.meta.title}}</span>
            </el-menu-item>
          </router-link>
        </template>
      </el-submenu>
      <!--非折叠标签-->
      <router-link v-else :to="item.path" :key="item.name">
        <el-menu-item :index="item.path">
          <i v-if="item.meta&&item.meta.icon" class="fa" :class="item.meta.icon"></i>
          <span v-if="item.meta&&item.meta.title">{{item.meta.title}}</span>
        </el-menu-item>
      </router-link>
    </template>
  </div>
</template>

<script>

  export default {
    name: 'SidebarItem',
    props: {
      routes: {
        type: Array
      },
      isNest: {
        type: Boolean,
        default: false
      }
    },
    methods: {}
  }
</script>

