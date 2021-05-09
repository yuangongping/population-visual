<template>
    <div class="data-center-project-data">
        <div class="filter" >
            <el-select v-model="node" placeholder="请选择" @change="getData">
                <el-option label="第6次普查" value="第6次普查"> </el-option>
                <el-option label="第5次普查" value="第5次普查"></el-option>
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
            this.source.push(['product', '男性', '女性'])
            let params = { nodeName: this.node, sex: "M"}
            let res = await this.$http.get("/population/population/sex", {params: params})
            this.data6 = res.data
            params = { nodeName: this.node, sex: "W"}
            res = await this.$http.get("/population/population/sex", {params: params})
            this.data5 = res.data
            for(let i =0, len= this.data6.length; i < len; i++){
                for(let j =0, lenj= this.data6.length; j < lenj; j++){
                    if(this.data6[i].name == this.data5[j].name){
                        this.source.push([this.data6[i].name, this.data6[i].value, this.data5[j].value])
                    }
                }
            }
            console.log(this.source)
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#barSex'))
            const option = {
                title: {text: "各省人口性别对比"},
                backgroundColor: "white",
                legend: {},
                tooltip: {},
                dataset: {
                    source: this.source
                },
                xAxis: {type: 'category'},
                yAxis: {},
                series: [
                    {type: 'bar',
                    itemStyle: {
                            color: '#339999'
                        },},
                    {type: 'bar',
                    itemStyle: {
                            color: '#996633'
                        },},
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