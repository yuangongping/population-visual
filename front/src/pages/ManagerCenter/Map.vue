<template>
  <div class="echarts">
    <div class="filter" >
      <el-select v-model="node" placeholder="请选择" @change="getMapData">
          <el-option label="第6次普查" value="第6次普查"> </el-option>
          <el-option label="第5次普查" value="第5次普查"></el-option>
      </el-select>
    </div>

    <div :style="{height:'600px',width:'100%'}" ref="myEchart"></div>
  </div>
</template>
<script>
  import echarts from "echarts";
  import '../../../node_modules/echarts/map/js/china' // 引入中国地图数据
  export default {
    name: "echarts",
    props: ["userJson"],
    data() {
      return {
        node: "第6次普查",
        chart: null,
        data: []
      };
    },
    mounted() {
      this.getMapData()
      
    },

    created(){
      // this.getMapData()
    },
    beforeDestroy() {
      if (!this.chart) {
        return;
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
      async getMapData(){
        let res = await this.$http.get("/population/population/map?nodeName="+ this.node)
        this.data = res.data
        this.chinaConfigure();
      },
      chinaConfigure() {
        console.log(this.data)
        let myChart = echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置    
        window.onresize = myChart.resize;
        myChart.setOption({ 
          title: {
            text: "人口分布图"
          },
          backgroundColor: "white",
          tooltip: {}, // 鼠标移到图里面的浮动提示框
          dataRange: {
            show: false,
            min: 10000,
            max: 133936333,
            text: ['High', 'Low'],
            realtime: true,
            calculable: true,
            color: ['orangered', 'yellow', 'lightskyblue']
          },
          geo: { // 这个是重点配置区
            map: 'china', // 表示中国地图
            roam: true,
            label: {
              normal: {
                show: true, // 是否显示对应地名
                textStyle: {
                  color: 'rgba(0,0,0,0.4)'
                }
              }
            },
            itemStyle: {
              normal: {
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis: {
                areaColor: null,
                shadowOffsetX: 0,
                shadowOffsetY: 0,
                shadowBlur: 20,
                borderWidth: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          },
          series: [{
              type: 'scatter',
              coordinateSystem: 'geo' // 对应上方配置
            },
            {
              name: '启动次数', // 浮动框的标题
              type: 'map',
              geoIndex: 0,
              data: this.data
            }
          ]
        })
      }
    }
  }
</script>

<style lang="scss" scoped>
.filter{
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>