<template>
    <div class="data-center-project-data">
        <div class="filter" >
            <el-select v-model="node" placeholder="请选择" @change="getData">
                <el-option label="第6次普查" value="第6次普查"> </el-option>
                <el-option label="第5次普查" value="第5次普查"></el-option>
            </el-select>
        </div>
        <div id="barage" class="chart" style="height:900px"></div>
    </div>
</template>


<script>
import echarts from 'echarts'
export default {
    data(){
        return {
            data: [],
            node: "第6次普查",
            source: [],
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
            this.source = []
            this.source.push(['product', '0-14岁', '15-59岁', '60岁以上'])
            let params = { nodeName: this.node}
            let res = await this.$http.get("/population/population/age", {params: params})
            this.data = res.data
            for(let j =0, len= this.data.length; j < len; j++){
                this.source.push([this.data[j].name, this.data[j].age0, this.data[j].age1, this.data[j].age2])
            }
            
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#barage'))
            const option = {
                title: {text: "各省人口年龄状态图"},
                backgroundColor: "white",
                legend: {},
                tooltip: {},
                dataset: {
                    source: this.source
                },
                xAxis: {type: 'category'},
                yAxis: {},
                series: [
                    {type: 'bar'},
                    {type: 'bar'},
                    {type: 'bar'},
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