<template>
    <div class="data-center-project-data">
        <div class="filter" >
            <el-select v-model="sex" placeholder="请选择" @change="getData">
                <el-option label="男" value="M"> </el-option>
                <el-option label="女" value="W"></el-option>
            </el-select>
            </div>
        <div id="barSex" class="chart" style="height:900px"></div>
    </div>
</template>


<script>
import echarts from 'echarts'
export default {
    data(){
        return {
            data5: [],
            data6:[],
            sex: "M",
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
            let params = { nodeName: "第6次普查", sex: this.sex}
            let res = await this.$http.get("/population/population/sex", {params: params})
            this.data6 = res.data
            params = { nodeName: "第5次普查", sex: this.sex}
            res = await this.$http.get("/population/population/sex", {params: params})
            this.data5 = res.data
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#barSex'))
            const option = {
                backgroundColor: "white",
                title: {
                    text: this.sex == 'M'?"男性人口对比":"女性人口对比",
                    subtext: '数据来自国家统计局'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['第6次人口普查', '第5次人口普查']
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
                        name: '第6次人口普查',
                        type: 'bar',
                        itemStyle: {
                            color: '#CC6600'
                        },
                        data: this.data6
                    },
                    {
                        name: '第5次人口普查',
                        type: 'bar',
                         itemStyle: {
                            color: '#336699'
                        },
                        data: this.data5
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