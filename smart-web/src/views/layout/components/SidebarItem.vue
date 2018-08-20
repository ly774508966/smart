<template>
  <div class="menu-wrapper">
    <template v-for="menu in menus">
      <!--折叠标签-->
      <el-submenu v-if="menu.node.type==2" :index="menu.node.code" :key="menu.node.code">
        <template slot="title">
          <i v-if="menu.node.menuIcon" class="fa" :class="menu.node.menuIcon"></i>
          <span v-if="menu.node.name">{{menu.node.name}}</span>
        </template>
        <template v-for="sub in menu.subs">
          <!--有子菜单-->
          <sidebar-item :is-nest="true" class="nest-menu" v-if="sub.subs&&sub.subs.length>0" :routes="[sub]" :key="sub.node.code"></sidebar-item>
          <!--无子菜单-->
          <router-link v-else :to="sub.node.url" :key="sub.node.code">
            <el-menu-item :index="sub.node.code">
              <i v-if="sub.node.menuIcon" class="fa" :class="sub.node.menuIcon"></i>
              <span v-if="sub.node.name">{{sub.node.name}}</span>
            </el-menu-item>
          </router-link>
        </template>
      </el-submenu>
      <!--非折叠标签-->
      <router-link v-if="menu.node.type==1" :to="menu.node.url" :key="menu.node.code">
        <el-menu-item :index="menu.node.code">
          <i v-if="menu.node.menuIcon" class="fa" :class="menu.node.menuIcon"></i>
          <span v-if="menu.node.name">{{menu.node.name}}</span>
        </el-menu-item>
      </router-link>
    </template>
  </div>
</template>

<script>

  export default {
    name: 'SidebarItem',
    props: {
      menus: {
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

