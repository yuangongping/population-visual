<template>
    <div class="data-center-project-data">
        <div id="agepie" class="chart" style="height:900px"></div>
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
            let params = { nodeName: "第6次普查"}
            let res = await this.$http.get("/population/population/age", {params: params})
            let age0Sum = 0
            let age1Sum = 0
            let age2Sum = 0
            for(let i =0, len= res.data.length; i < len; i++){
                age0Sum += res.data[i].age0
                age1Sum += res.data[i].age1
                age2Sum += res.data[i].age2
            }
            this.data6 = [
                {name: "0-14岁", value: age0Sum},
                {name: "15-59岁", value: age1Sum},
                {name: "60岁以上", value: age2Sum}
            ]


            params = { nodeName: "第5次普查"}
            res = await this.$http.get("/population/population/age", {params: params})
            age0Sum = 0
            age1Sum = 0
            age2Sum = 0
            for(let i =0, len= res.data.length; i < len; i++){
                age0Sum += res.data[i].age0
                age1Sum += res.data[i].age1
                age2Sum += res.data[i].age2
            }
            this.data5 = [
                {name: "0-14岁", value: age0Sum},
                {name: "15-59岁", value: age1Sum},
                {name: "60岁以上", value: age2Sum}
            ]
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#agepie'))
            const option = {
                title: [{
                    text: '年龄结构分布',
                    subtext: '数据来源国家统计局人口普查',
                    left: 'center'
                },
                {
                    subtext: '第6次人口普查',
                    left: '26.67%',
                    top: '75%',
                    textAlign: 'center'
                },
                {
                    subtext: '第5次人口普查',
                    left: '76.67%',
                    top: '75%',
                    textAlign: 'center'
                }],
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b} : {c} ({d}%)'
                },
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '第6次普查',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['25%', '50%'],
                        roseType: 'area',
                        itemStyle: {
                            borderRadius: 5
                        },
                        data: this.data6
                    },
                    {
                        name: '第5次普查',
                        type: 'pie',
                        radius: [20, 140],
                        center: ['75%', '50%'],
                        roseType: 'area',
                        itemStyle: { borderRadius: 5},
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