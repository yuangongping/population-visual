<template>
    <div class="container" >
        <div class="item">
             <map-com></map-com>
        </div>
        <div class="item">
            <Bar />
        </div>
        <div class="item">
            <BarSex />
        </div>

        <div class="item">
            <BarSexMW />
        </div>

        <div class="item">
            <BarAge />
        </div>

        <div class="item">
            <AgePie />
        </div>

         <div class="item">
            <BornDead />
        </div>

        <div class="item">
            <Marry />
        </div>
       
    </div>
</template>

<style lang="scss" scoped>

</style>

<script>
import MapCom from "./Map.vue";
import  Bar from "./Bar"
import BarSex from "./BarSex"
import BarSexMW from "./BarSexMW"
import BarAge from "./BarAge"
import AgePie from "./AgePie"
import BornDead from "./BornDead"
import Marry from "./Marry"

export default {
    components: { MapCom, Bar, BarSex, BarSexMW, BarAge, AgePie, BornDead, Marry },
    data() {
        return {
          
        }
    },
    created(){
       
    },
    methods:{
        // 后端查询
        async query(){
            try{
                let params = {page: 1, limit: 100, isPage: true}
                var response = null
                if(this.$root.modelCreatedType === 'modelFileType'){
                    response = await this.$http.get('/ciem/ciemdomain/page', {params: params})
                } else{
                    response = await this.$http.get('/ciem/manualdomain/page', {params: params})
                }
                this.dataList = response.result.list
                this.domain = this.dataList[0].domain

            }catch(err){
            }
        },
    }
}
</script>

<style lang="scss" scoped>
.container{
    background-color: white;
    .item{
        margin-bottom: 100px;
    }
}
</style>
