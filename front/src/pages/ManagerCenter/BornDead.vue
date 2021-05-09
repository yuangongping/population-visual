<template>
    <div class="data-center-project-data">
        <div class="filter" >
            <el-select v-model="node" placeholder="请选择" @change="getData">
                <el-option label="第6次普查" value="第6次普查"> </el-option>
                <el-option label="第5次普查" value="第5次普查"></el-option>
            </el-select>
            </div>
        <div id="borndead" class="chart" style="height:1500px"></div>
    </div>
</template>


<script>
import echarts from 'echarts'
export default {
    data(){
        return {
            data:{
                born: [],
                bornM: [],
                bornW: [],
                dead: [],
                deadM: [],
                deadW: [],
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
                born: [],
                bornM: [],
                bornW: [],
                dead: [],
                deadM: [],
                deadW: [],
            }
            let params = { nodeName: this.node}
            let res = await this.$http.get("/population/population/born-dead", {params: params})
            for(let i=0, len=res.data.length; i < len; i ++){
                this.data.born.push({name: res.data[i].name, value: res.data[i].born})
                this.data.bornM.push({name: res.data[i].name, value: res.data[i].bornM})
                this.data.bornW.push({name: res.data[i].name, value: res.data[i].bornW})
                this.data.dead.push({name: res.data[i].name, value: res.data[i].dead})
                this.data.deadM.push({name: res.data[i].name, value: res.data[i].deadM})
                this.data.deadW.push({name: res.data[i].name, value: res.data[i].deadW})
            }
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#borndead'))
            const option = {
                backgroundColor: "white",
                title: {
                    text: this.node == "第6次普查"?"2009.11-2000.10出生死亡人数":"1999.11-2000.10出生死亡人数",
                    subtext: '数据来自国家统计局'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['出生', '出生男', '出生女','死亡', '死亡男', '死亡女']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
                },
                yAxis: {
                    type: 'category',
                    data: ['北京','天津','河北','山西','内蒙古','辽宁','吉林','黑龙江',
                                '上海','江苏','浙江','安徽','福建','江西','山东','河南',
                                '湖北','湖南','广东','广西','海南','重庆','四川','贵州',
                                '云南','西藏','陕西','甘肃','青海','宁夏','新疆']
                },
                series: [
                    {
                        name: '出生',
                        type: 'bar',
                        data: this.data.born
                    },
                    {
                        name: '出生男',
                        type: 'bar',
                        data: this.data.bornM
                    },
                      {
                        name: '出生女',
                        type: 'bar',
                        data: this.data.bornW
                    },
                    {
                        name: '死亡',
                        type: 'bar',
                        data: this.data.dead
                    },
                      {
                        name: '死亡男',
                        type: 'bar',
                        data: this.data.deadM
                    },
                    {
                        name: '死亡女',
                        type: 'bar',
                        data: this.data.deadW
                    }
                ]

            };
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