<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
  ul, menu, dir {
    display: block;
    list-style-type: none;
    -webkit-margin-before: 0px;
    -webkit-margin-after: 0px;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
    -webkit-padding-start: 0px;
  }

  .table_container {
    padding: 20px;
  }

</style>

<script>
  export default {
    name: 'App'
  }
</script>
