<template>
  <div style="position: relative">
    <div id="routeMap" :style="{height:clientHeight+'px'}"></div>
    <div class="right_bar">
      <div class="right_bar_top" :style="{height:clientTopHeight+'px'}">
        <div class="right_bar_inner">
          <!--<div class="item">未分配订单数：6</div>-->
          <!--<div class="item">未下单订单数：6</div>-->
          <!--<div class="item">未接单订单数：6</div>-->

          <div v-for="index in 10" class="order">
            <div>
              <span class="num" v-text="index"></span>
              <el-tag type="warning" size="mini">已接单</el-tag>
              <span>小型货车：皖AX12345</span>
            </div>
            <div class="division"></div>
            <div><span>司机：张三 14312345678</span></div>
            <div><span>搬运：张三 14312345678</span></div>
            <div>取货：2018-06-23 09:00:00</div>
            <div class="division"></div>
            <div>张先生 13720198732 北京市朝阳区望京阜荣街10号首开广场4层</div>
            <div>张先生 13720198732 北京市朝阳区望京阜荣街10号首开广场4层</div>
            <div class="division"></div>
            <el-button-group>
              <el-button type="warning" size="mini" icon="el-icon-edit">编</el-button>
              <el-button type="success" size="mini" icon="el-icon-share">群</el-button>
              <el-button type="success" size="mini" icon="el-icon-share">单</el-button>
              <el-button type="danger" size="mini" icon="el-icon-delete">删</el-button>
            </el-button-group>
          </div>
        </div>
      </div>
      <div class="right_bar_bottom" @click="addDialogVisible = true">创建路线</div>
    </div>
    <el-dialog title="添加路线" width="600px" :visible.sync="addDialogVisible">
      <el-form size="mini" label-width="80px">
        <el-form-item label="货车型号">
          <el-select v-model="form.carType" placeholder="请选择货车型号">
            <el-option label="小型货车" value="1"></el-option>
            <el-option label="中型货车" value="2"></el-option>
            <el-option label="大型货车" value="3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通知司机">
          <el-select v-model="form.driver" filterable placeholder="请选择">
            <el-option
              v-for="item in drivers"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <span>（若不选，则通知所有司机抢单）</span>
        </el-form-item>
        <el-form-item label="取货时间">
          <el-date-picker
            v-model="form.dateTime"
            type="datetime"
            placeholder="请选择取货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单列表">
          <el-autocomplete
            v-model="form.order"
            style="width: 400px"
            :fetch-suggestions="querySearchAsync"
            placeholder="请输入订单信息"
            @select="handleSelect"
          ></el-autocomplete>
          <div style="height: 10px"></div>
          <el-tag style="margin-bottom: 3px;"
            v-for="(order, index) in form.orders"
            :key="order.value"
            closable>
            {{order.value}}
          </el-tag>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="addDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addDialogVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style scoped>
  .right_bar {
    font-size: 12px;
    position: absolute;
    top: 0;
    right: 0;
    width: 300px;
    /*box-shadow: 1px 1px 5px rgba(0, 73, 239, 0.55);*/
    /*border: 1px #0080ff solid;*/
    background-color: rgba(10, 68, 138, 0.88);
    font-size: 14px;
    color: white;
  }

  .division {
    height: 5px;
  }

  .order {
    margin-bottom: 20px;
  }

  .right_bar_top {
    overflow-y: auto;
  }

  .right_bar_inner {
    padding: 15px;
  }

  .right_bar_inner .item {
    line-height: 28px;
    font-size: 14px;
  }

  .right_bar_bottom {
    background-color: #42B983;
    text-align: center;
    height: 50px;
    line-height: 50px;
    font-size: 20px;
    cursor: pointer;
  }

  .num {
    width: 20px;
    height: 20px;
    border-radius: 2px;
    display: inline-block;
    background-color: #F78989;
    text-align: center;
    line-height: 20px;
  }
</style>
<script>
  import AMap from 'AMap'

  function computeClientHeight () {
    return (document.documentElement.clientHeight - 50 - 34);
  }

  export default {
    data() {
      return {
        timeout: undefined,
        clientHeight: undefined,
        orders: [
          {
            position: [117.221707, 31.840603],
            visible: true,
            carID: 1,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          },
          {
            position: [117.26856, 31.841642],
            visible: true,
            carID: 1,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          },
          {
            position: [117.208683, 31.781633],
            visible: true,
            carID: 2,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          },
          {
            position: [117.183452, 31.749422],
            visible: true,
            carID: 2,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          },
          {
            position: [117.257178, 31.878205],
            visible: true,
            carID: 3,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 李四<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          },
          {
            position: [117.300363, 31.879504],
            visible: true,
            carID: 3,
            content: "<div style='padding:0px 0px 0px 4px;'><b style='font-size: 14px'>订单信息</b><br/>客户 : 王五<br/>手机 : 13321456765<br/>地址 :北京市朝阳区望京阜荣街10号首开广场4层</div>"
          }],
        warehouse: [117.189896, 31.839933],
        addDialogVisible: undefined,
        form: {
          carType: undefined,
          dateTime: undefined,
          driver: undefined,
          order: undefined,
          orders: []
        },
        drivers: [
          {
            value: '选项1',
            label: '张三'
          }, {
            value: '选项2',
            label: '李四'
          }, {
            value: '选项3',
            label: '王五'
          }, {
            value: '选项4',
            label: '小红'
          }, {
            value: '选项5',
            label: '小白'
          }]
      }
    },
    computed: {
      clientTopHeight: function () {
        if (this.clientHeight) {
          return this.clientHeight - 50;
        } else {
          return undefined;
        }
      }
    },
    mounted: function () {
      //初始化地图
      var map = new AMap.Map('routeMap', {
        resizeEnable: true,
        center: [117.285149, 31.813265],
        zoom: 12,
        mapStyle: 'amap://styles/whitesmoke'
      });

      //计算地图高度
      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp () {
        that.clientHeight = computeClientHeight();
      };

      AMapUI.loadUI(['overlay/SimpleMarker'], function (SimpleMarker) {
        that.orders.forEach(function (order) {
          var orderMarker = new SimpleMarker({
            map: map,
            position: order.position,
            iconTheme: 'numv1',
            containerClassNames: 'my-marker',
            iconStyle: 'red-' + order.carID,
          });
          orderMarker.content = order.content;
          orderMarker.on('click', openInfo);
        })

        var warehouseMarker = new SimpleMarker({
          map: map,
          position: that.warehouse,
          containerClassNames: 'my-marker',
          iconLabel: '仓',
          iconStyle: 'green',
        });
      });

      var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30)});

      function openInfo (e) {
        infoWindow.setContent(e.target.content);
        infoWindow.open(map, e.target.getPosition());
      }
    },
    methods: {
      querySearchAsync(queryString, cb) {
        var restaurants = [
          {"value": "张三 13728219283 长宁区新渔路144号"},
          {"value": "张三 13728219283 上海市长宁区淞虹路661号"},
          {"value": "张三 13728219283 上海市普陀区真北路988号创邑金沙谷6号楼113"},
          {"value": "张三 13728219283 天山西路438号"},
          {"value": "张三 13728219283 上海市长宁区金钟路968号1幢18号楼一层商铺18-101"},
          {"value": "张三 13728219283 上海市长宁区金钟路633号"},
          {"value": "张三 13728219283 上海市嘉定区曹安公路曹安路1685号"},
          {"value": "张三 13728219283 上海市普陀区同普路1435号"},
          {"value": "张三 13728219283 上海市北翟路1444弄81号B幢-107"},
          {"value": "张三 13728219283 上海市嘉定区新郁路817号"},
          {"value": "张三 13728219283 嘉定区曹安路1611号"},
          {"value": "张三 13728219283 嘉定区曹安公路2383弄55号"},
          {"value": "张三 13728219283 嘉定区江桥镇曹安公路2409号1F，2383弄62号1F"}
        ];
        var results = queryString ? restaurants.filter(this.createStateFilter(queryString)) : restaurants;

        clearTimeout(this.timeout);
        this.timeout = setTimeout(() => {
          cb(results);
        }, 1000 * Math.random());
      },
      createStateFilter(queryString) {
        return (state) => {
          return (state.value.toLowerCase().indexOf(queryString.toLowerCase()) >= 0);
        };
      },
      handleSelect(item){
        this.form.order = undefined;
        this.form.orders.push(item);
      }
    }
  }
</script>
