<template>
  <div>
    首页
  </div>
</template>
<style scoped>
  #rightBar {
    position: absolute;
    width: 318px;
    top: 0px;
    right: 0px;
    font-size: 14px;
    color: white;
  }

  #rightBar .pointList {
    box-shadow: 1px 1px 5px rgba(0, 73, 239, 0.55);
    border: 1px #0080ff solid;
    background-color: rgba(10, 68, 138, 0.88);
  }

  #rightBar .pointListTitle {
    background-color: #0d5ab1;
    color: #ccc;
  }

  #rightBar .pointListBody {

  }

  #rightBar .pointListBody ul {
    background-color: #0d5ab1;
  }

  #rightBar .pointListBody ul:hover {
    background-color: #0d5ab1;
  }

  ul, li {
    list-style: none;
  }

  ul {
    clear: both;
    overflow: hidden;
    padding: 8px;
  }

  li {
    float: left;
  }

</style>
<script>

  export default {
    name: 'home',
    data() {
      return {}
    },
    mounted: function () {


    }
  }

</script>
