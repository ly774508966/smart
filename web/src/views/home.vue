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
  import AMap from 'AMap'

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
      //初始化地图
      var map = new AMap.Map('container', {
        resizeEnable: true,
        center: [117.285149, 31.813265],
        zoom: 13,
        mapStyle: 'amap://styles/whitesmoke'
      });

      //计算地图高度
      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp () {
        that.clientHeight = computeClientHeight();
      };

      //添加坐标点
      var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});
      var list = [
        [
          {
            position: [117.189896, 31.839933],
            visible: false,
            carID: 1,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 张三<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          }, {
          position: [117.221707, 31.840603],
          visible: true,
          carID: 1,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }, {
          position: [117.26856, 31.841642],
          visible: true,
          carID: 1,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }
        ],
        [{
          position: [117.189896, 31.839933],
          visible: false,
          carID: 12,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 张三<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }, {
          position: [117.208683, 31.781633],
          visible: true,
          carID: 12,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }, {
          position: [117.183452, 31.749422],
          visible: true,
          carID: 12,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }],
        [{
          position: [117.189896, 31.839933],
          visible: false,
          carID: 20,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 张三<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }, {
          position: [117.257178, 31.878205],
          visible: true,
          carID: 20,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }, {
          position: [117.300363, 31.879504],
          visible: true,
          carID: 20,
          content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
        }]];

      AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
        //启动页面
        list.forEach(function (markers) {
          markers.forEach(function (marker) {
            var newMarker = new SimpleMarker({
              map: map,
              position: marker.position,
              iconTheme: 'numv1',
              containerClassNames: 'my-marker',
              iconStyle: 'red-' + marker.carID,
              visible: marker.visible
            });
            newMarker.content = marker.content;
            newMarker.on('click', openInfo);
          })
        });
      });
      function openInfo (e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
      }

      //添加路线
      list.forEach(function (markers) {
        var path = [];
        var truckOptions = {
          map: map,
          policy: 0,
          size: 1,
          city: 'hefei',
          hideMarkers: true,
          showTraffic: false,
          autoFitView: false
        };
        var driving = new AMap.TruckDriving(truckOptions);
        var moveCar = new AMap.Marker({
          map: map,
          position: markers[1].position,
          icon: "http://webapi.amap.com/images/car.png",
          offset: new AMap.Pixel(-26, -13),
          autoRotation: true
        });
        moveCar.content = "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>车辆信息</b><br/>车牌号码 : 皖AH12345<br/>司机信息 : 张三（13321456765）<br/>上楼工人 : 王五（13212345678）</div>"
        moveCar.on('click', openInfo);
        markers.forEach(function (marker) {
          path.push({lnglat: marker.position})
        });
        driving.search(path, function (status, result) {});
      });
    }
  }

</script>
