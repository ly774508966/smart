<template>
  <div style="position: relative">
    <div id="container" :style="{height:clientHeight}"></div>
    <div id="rightBar" style="display: none">
      <div class="pointList">
        <div class="pointListTitle">
          <ul>
            <li style="width: 50px;text-align: center">编号</li>
            <li style="width: 100px;text-align: center">车牌</li>
            <li style="width: 100px;text-align: center">司机</li>
            <li style="width: 50px;text-align: center">状态</li>
          </ul>
        </div>
        <div class="pointListBody">
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
          <ul>
            <li style="width: 50px;text-align: center"><b>1</b></li>
            <li style="width: 100px;text-align: center">皖AH12345</li>
            <li style="width: 100px;text-align: center">王师傅</li>
            <li style="width: 50px;text-align: center">运送中</li>
          </ul>
        </div>
      </div>
    </div>
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
  function computeClientHeight () {
    return (document.documentElement.clientHeight - 50 - 34) + "px";
  }

  export default{
    name: 'home',
    data() {
      return {
        clientHeight: undefined
      }
    },
    mounted: function () {
      //计算地图高度
      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp () {
        that.clientHeight = computeClientHeight();
      };

    }
  }

</script>
