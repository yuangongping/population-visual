<template>
    <div class="data-center-project-data">
        <div id="project-data-histogram" class="chart"></div>
    </div>
</template>


<script>
import echarts from 'echarts'
export default {
    data(){
        return {
            data5: [],
            data6:[],
            timeline: "",
            xAxislLabel: "",
            option: ""
        }
    },
    mounted() {
        this.getData()
        
    },
    methods: {
        async getData(){
            let res = await this.$http.get("/population/population/map?nodeName="+ "第6次普查")
            this.data6 = res.data
            res = await this.$http.get("/population/population/map?nodeName="+ "第5次普查")
            this.data5 = res.data
            this.render()
        },
        init(){
        },
        render () {
            const chart = echarts.init(this.$el.querySelector('#project-data-histogram'))
            var dataMap = {};
            const option = {
                backgroundColor: "white",
                baseOption: {
                    timeline: {
                        axisType: 'category',
                        autoPlay: true,
                        playInterval: 1000,
                        data: ['第6次人口普查','第5次人口普查'],
                        label: { formatter : function(s) {return s;}}
                    },
                    title: {text: "各省人口状态图"},
                    tooltip: {
                    },
                    legend: {
                        left: 'right',
                        data: ['人口数']
                    },
                    calculable : true,
                    grid: {
                        top: 80,
                        bottom: 100,
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {
                                type: 'shadow',
                                label: {
                                    show: true,
                                    formatter: function (params) {
                                        return params.value.replace('\n', '');
                                    }
                                }
                            }
                        }
                    },
                    xAxis: [
                        {
                            'type':'category',
                            'axisLabel':{'interval':0},
                            'data':[
                                '北京','\n天津','河北','\n山西','内蒙古','\n辽宁','吉林','\n黑龙江',
                                '上海','\n江苏','浙江','\n安徽','福建','\n江西','山东','\n河南',
                                '湖北','\n湖南','广东','\n广西','海南','\n重庆','四川','\n贵州',
                                '云南','\n西藏','陕西','\n甘肃','青海','\n宁夏','新疆'
                            ],
                            splitLine: {show: false}
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            name: '人数'
                        }
                    ],
                    series: [
                        {
                            name: '人口数', 
                            type: 'bar',
                            itemStyle : {
                                normal : {
                                    color: function(params) {
                                        let colorList = [
                                            '#ff0000', '#239676', '#eb4310', '#f6941d', '#fbb417', '#c23531', '#2f4554', '#61a0a8', '#24998d', '#1f9baa', 
                                            '#d48265', '#91c7ae', '#749f83', '#ca8622', '#cdd541', '#99cc33', '#3f9337', '#219167', '#0080ff', '#3366cc', 
                                            '#333399', '#003366', '#800080', '#a1488e', '#c71585', '#bd2158'                      
                                        ];
                                        return colorList[params.dataIndex]
                                    },
                                    label: {
                                        show: true,
                                        position: 'top',                              
                                        // padding: [0,0,10,0],                  
                                    }
                                },
                            },
                            barMaxWidth: '20',
                        }
                    ]
                },
                options: [
                    {
                        title: {text: '第6次人口普查'},
                        series: [
                            {data: this.data6}
                        ]
                    },
                    {
                        title : {text: '第5次人口普查'},
                        series : [
                            {data: this.data5}
                        ]
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