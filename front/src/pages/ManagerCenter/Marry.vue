<template>
    <div class="data-center-project-data">
    <div class="filter" >
      <el-select v-model="node" placeholder="请选择" @change="getData">
          <el-option label="第6次普查" value="第6次普查"> </el-option>
          <el-option label="第5次普查" value="第5次普查"></el-option>
      </el-select>
    </div>
        <div id="marry" class="chart" style="height:900px"></div>
    </div>
</template>


<script>
import echarts from 'echarts'
export default {
    data(){
        return {
            data:{
                weihun: [],
                weihunM: [],
                weihunW: [],
                yihun: [],
                yihunM: [],
                yihunW: [],
                lihun: [],
                lihunM: [],
                lihunW: []
            },
            node: "第6次普查",
            timeline: "",
            xAxislLabel: "",
            option: "",
        }
    },
    mounted() {
        this.getData()
        
    },
    methods: {
        async getData(){
            this.data = {
                weihun: [],
                weihunM: [],
                weihunW: [],
                yihun: [],
                yihunM: [],
                yihunW: [],
                lihun: [],
                lihunM: [],
                lihunW: []
            }
            let res = await this.$http.get("/population/population/marry", {params: { nodeName: this.node}})
            for(let i=0, len=res.data.length; i < len; i ++){
                this.data.weihun.push(res.data[i].weihun)
                this.data.weihunM.push(res.data[i].weihunM)
                this.data.weihunW.push(res.data[i].weihunW)

                this.data.yihun.push(res.data[i].yihun)
                this.data.yihunM.push(res.data[i].yihunM)
                this.data.yihunW.push(res.data[i].yihunW)

                this.data.lihun.push(res.data[i].lihun)
                this.data.lihunM.push(res.data[i].lihunM)
                this.data.lihunW.push(res.data[i].lihunW)
            
            }
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#marry'))
            const option = {
                backgroundColor: "white",
                title: {
                    text: "婚姻状况",
                    subtext: '数据来自国家统计局'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['未婚', '未婚男', '未婚女', '已婚', '已婚男', '已婚女', '离婚', '离婚男', '离婚女']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['北京','天津','河北','山西','内蒙古','辽宁','吉林','黑龙江',
                                '上海','江苏','浙江','安徽','福建','江西','山东','河南',
                                '湖北','湖南','广东','广西','海南','重庆','四川','贵州',
                                '云南','西藏','陕西','甘肃','青海','宁夏','新疆']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '未婚',
                        type: 'line',
                        stack: '总量',
                        data: this.data.weihun
                    },
                    {
                        name: '未婚男',
                        type: 'line',
                        stack: '总量',
                        data: this.data.weihunM
                    },
                    {
                        name: '未婚女',
                        type: 'line',
                        stack: '总量',
                        data: this.data.weihunW
                    },
                    {
                        name: '已婚',
                        type: 'line',
                        stack: '总量',
                        data: this.data.yihun
                    },
                    {
                        name: '已婚男',
                        type: 'line',
                        stack: '总量',
                        data: this.data.yihunM
                    },
                     {
                        name: '已婚女',
                        type: 'line',
                        stack: '总量',
                        data: this.data.yihunW
                    },
                      {
                        name: '离婚',
                        type: 'line',
                        stack: '总量',
                        data: this.data.lihun
                    },
                    {
                        name: '离婚男',
                        type: 'line',
                        stack: '总量',
                        data: this.data.lihunM
                    },
                     {
                        name: '离婚女',
                        type: 'line',
                        stack: '总量',
                        data: this.data.lihunW
                    }
                ]
            }
            chart.setOption(option)
        },
    }
}
</script>
<style lang="scss" scoped>
.data-center-project-data{
    width: 100%;
    .chart{
        width: 100%;
        height: 400px;
    }
}
</style>