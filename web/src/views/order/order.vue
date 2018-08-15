<template>
  <div class="table_container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" size="mini" class="filter-item item" placeholder="客户姓名" v-model="listQuery.customerName"></el-input>
      <el-input @keyup.enter.native="handleFilter" size="mini" class="filter-item item" placeholder="客户号码" v-model="listQuery.customerPhone"></el-input>
      <el-date-picker class="filter-item item" v-model="listQuery.fromDate" size="mini" type="datetime" placeholder="开始时间"></el-date-picker>
      <el-date-picker class="filter-item item" v-model="listQuery.toDate" size="mini" type="datetime" placeholder="结束时间"></el-date-picker>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-edit" style="margin-left: 10px;" @click="handleCreate">添加</el-button>
      <el-button class="filter-item" size="mini" type="primary" v-waves icon="el-icon-download" :loading="downloadLoading" @click="handleDownload">导出</el-button>
    </div>
    <el-tabs type="border-card">
      <el-tab-pane label="全部订单">
        <el-table
          :height="clientHeight"
          size="mini"
          :data="tableData"
          :border="true"
          style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-row>
                <el-col :span="12" style="padding:5px ">
                  <el-card :body-style="{ padding: '10px',background: '#2D5A91'}">
                    <h2 style="text-align: center;color: white">货物信息
                      <div style="float: right"><i class="el-icon-circle-plus-outline"></i></div>
                    </h2>
                    <el-table
                      :data="goods"
                      stripe
                      :border="true"
                      style="width: 100%;">
                      <el-table-column
                        prop="no"
                        label="型号"
                        width="150">
                      </el-table-column>
                      <el-table-column
                        prop="name"
                        label="名称">
                      </el-table-column>
                      <el-table-column
                        prop="count"
                        label="数量"
                        align="center"
                        width="50">
                      </el-table-column>
                      <el-table-column
                        prop="unit"
                        label="单位"
                        align="center"
                        width="50">
                      </el-table-column>
                      <el-table-column align="center" label="操作" width="80">
                        <template slot-scope="scope">
                          <i class="el-icon-edit"></i>
                          <i class="el-icon-delete"></i>
                        </template>
                      </el-table-column>
                    </el-table>

                  </el-card>
                </el-col>
                <el-col :span="12" style="padding:5px ">
                  <el-card :body-style="{ padding: '10px',background: '#2D5A91' }">
                    <h2 style="text-align: center;color: white">订单变更日志</h2>
                    <el-table
                      :data="logs"
                      stripe
                      :border="true"
                      style="width: 100%;">
                      <el-table-column
                        prop="name"
                        label="用户"
                        width="180">
                      </el-table-column>
                      <el-table-column
                        prop="description"
                        label="操作">
                      </el-table-column>
                      <el-table-column
                        prop="date"
                        label="时间"
                        width="180">
                      </el-table-column>
                    </el-table>
                  </el-card>

                </el-col>
              </el-row>
            </template>
          </el-table-column>
          <el-table-column
            prop="no"
            label="订单编号"
            width="150px">
          </el-table-column>
          <el-table-column
            prop="date"
            label="创建时间"
            width="150px">
          </el-table-column>
          <!--<el-table-column-->
          <!--prop="goods"-->
          <!--label="货物名称"-->
          <!--:show-overflow-tooltip="true"-->
          <!--width="250px">-->
          <!--</el-table-column>-->
          <el-table-column
            prop="customerName"
            label="客户姓名"
            width="80px">
          </el-table-column>
          <el-table-column
            prop="customerPhone"
            label="客户电话"
            width="100px">
          </el-table-column>
          <el-table-column
            prop="toAddress"
            :show-overflow-tooltip="true"
            label="客户收货地址"
            width="250px">
          </el-table-column>
          <el-table-column
            label="客户星评"
            width="80px">
            <template slot-scope="scope">
              <i class="fa fa-star" aria-hidden="true" v-for="n in +scope.row.stars" :key="n"></i>
            </template>
          </el-table-column>
          <el-table-column
            prop="salesman"
            label="导购"
            width="80px">
          </el-table-column>
          <el-table-column
            prop="driverName"
            label="司机"
            width="80px">
          </el-table-column>
          <el-table-column
            prop="carrierName"
            label="上楼工"
            width="80px">
          </el-table-column>
          <el-table-column
            label="订单状态"
            width="80px">
            <template slot-scope="scope">
              <el-tag size="mini" :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            label="备注">
          </el-table-column>
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" icon="el-icon-location-outline" circle @click="handleShowMap"></el-button>
              <el-button size="mini" type="warning" icon="el-icon-edit" circle></el-button>
              <el-button size="mini" type="danger" icon="el-icon-delete" circle></el-button>
              <el-button size="mini" type="success" icon="el-icon-edit-outline" circle @click="updateStatusTableVisible=true"></el-button>
              <!--<el-button size="mini" type="success">货到</el-button>-->
              <!--<el-button size="mini" type="success">结算</el-button>-->
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          style="margin: 10px 0 0 0"
          background
          :small="true"
          layout="prev, pager, next"
          :total="1000">
        </el-pagination>
      </el-tab-pane>
      <el-tab-pane label="待处理"></el-tab-pane>
      <el-tab-pane label="已下单"></el-tab-pane>
      <el-tab-pane label="已接单"></el-tab-pane>
      <el-tab-pane label="派送中"></el-tab-pane>
      <el-tab-pane label="已签收"></el-tab-pane>
      <el-tab-pane label="已结算"></el-tab-pane>
      <el-tab-pane label="缺货中"></el-tab-pane>
      <el-tab-pane label="已删除"></el-tab-pane>
    </el-tabs>
    <el-dialog title="地图位置" :visible.sync="mapDialogVisible">
      <div style="position: relative;height: 500px;">
        <div id="container" style="width:100%;height: 500px;z-index: 9999;">
        </div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="mapDialogVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="mapDialogVisible = false" size="mini">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="添加订单" width="600px" :visible.sync="addDialogVisible">
      <el-form size="mini" label-width="80px">
        <el-form-item label="订单编号">
          <el-input placeholder="请输入订单编号"/>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input placeholder="请输入客户姓名"/>
        </el-form-item>
        <el-form-item label="客户电话">
          <el-input placeholder="请输入客户电话"/>
        </el-form-item>
        <el-form-item label="客户地址">
          <el-input placeholder="请输入客户地址"/>
        </el-form-item>
        <el-form-item label="导购">
          <el-select filterable v-model="saler" placeholder="请选择导购">
            <el-option
              v-for="item in salers"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单备注">
          <el-input placeholder="请输入订单备注" type="textarea" :rows="3"/>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="addDialogVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="addDialogVisible = false" size="mini">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改状态" width="600px" :visible.sync="updateStatusTableVisible">
      <div style="text-align: center">
        <el-button type="info">待处理</el-button>
        <el-button type="warning">暂缺货</el-button>
        <el-button type="info">已结算</el-button>
      </div>
      <div slot="footer">
        <el-button @click="updateStatusTableVisible = false" size="mini">取 消</el-button>
        <el-button type="primary" @click="updateStatusTableVisible = false" size="mini">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
</style>

<script>
  import AMap from 'AMap'
  import waves from '@/directive/waves' // 水波纹指令

  function computeClientHeight () {
    return (document.documentElement.clientHeight - 50 - 34 - 120 - 76) + "px";
  }

  export default {
    data() {
      return {
        saler: undefined,
        salers: [
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
          }],
        addDialogVisible: false,
        mapDialogVisible: false,
        updateStatusTableVisible: false,
        clientHeight: undefined,
        tableData: [
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已下单',
            stars: 4
          }, {
            no: '65865325365966',
            date: '2016-05-01 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1519 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 3
          }, {
            no: '65865325365966',
            date: '2016-05-03 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1516 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '待处理',
            stars: 2
          },
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 1
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 0
          }, {
            no: '65865325365966',
            date: '2016-05-01 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1519 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已下单',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-03 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1516 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          },
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          },
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '待处理',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-01 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1519 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '待处理',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-03 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1516 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '待处理',
            stars: 5
          },
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-01 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1519 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-03 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1516 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          },
          {
            no: '65865325365966',
            date: '2016-05-02 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1518 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }, {
            no: '65865325365966',
            date: '2016-05-04 11:22:43',
            fromAddress: '合肥市XXX路与XXX路交口',
            goods: "金龙鱼一级大豆油1.8L*5",
            customerName: '王小虎',
            customerPhone: '13754789654',
            toAddress: '上海市普陀区金沙江路 1517 弄',
            salesman: '王五',
            driverName: '张三',
            carrierName: '李四',
            status: '已结算',
            stars: 5
          }
        ],
        listQuery: {
          page: 1,
          limit: 20,
          customerName: undefined,
          customerPhone: undefined,
          fromDate: undefined,
          toDate: undefined
        },
        downloadLoading: false,
        goods: [
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          },
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          },
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          },
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          },
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          },
          {
            no: "TS5027-23",
            name: "xxx品牌干湿两用拖把",
            count: 4,
            unit: "把"
          }
        ],
        logs: [
          {date: '2018-06-17 09:00:00', name: '张三（管理员）', description: "创建订单"},
          {date: '2018-06-17 09:00:00', name: '张三（管理员）', description: "已下单"},
          {date: '2018-06-17 09:00:00', name: '李四（司机）', description: "已接单"},
          {date: '2018-06-17 09:00:00', name: '李四（司机）', description: "装货完毕"},
          {date: '2018-06-17 09:00:00', name: '李四（司机）', description: "已签收"},
          {date: '2018-06-17 09:00:00', name: '张三（管理员）', description: "已结算"}]
      }
    },
    directives: {
      waves
    },
    methods: {
      handleFilter() {
      },
      handleDownload(){
      },
      handleCreate(){
        this.addDialogVisible = true
      },
      handleShowMap(){
        this.mapDialogVisible = true;
        //初始化地图
        function mapInit () {
          var map = new AMap.Map('container', {
            resizeEnable: true,
            center: [117.285149, 31.813265],
            zoom: 13,
            mapStyle: 'amap://styles/whitesmoke'
          });
          var marker = new AMap.Marker({
            position: map.getCenter(),
            draggable: true,
            cursor: 'move',
            raiseOnDrag: true
          });
          marker.setMap(map);
        };
        requestAnimationFrame(mapInit);
      }
    },
    mounted: function () {
      const that = this;
      that.clientHeight = computeClientHeight()
      window.onresize = function temp () {
        that.clientHeight = computeClientHeight();
      };
    },
    filters: {
      statusFilter(status) {
        const statusMap = {
          "待处理": 'danger',
          "已下单": 'warning',
          "已结算": 'success'
        }
        return statusMap[status]
      }
    }

  }
</script>
