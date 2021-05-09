<template>
  <section class="app-main" :style="'height:' + fullHeihgt +'px;'">
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  data(){
    return {
      fullHeihgt: document.documentElement.clientHeight
    }
  },
  mounted(){
    this.getBodyHeight()
  },
  created(){
    this.init()
  }, 
  methods:{
    init(){
      window.fullHeihgt = document.documentElement.clientHeight
      this.fullHeihgt = window.fullHeihgt - 61
    },
    getBodyHeight(){
      const that = this
      window.onresize = () => {
        return ( () => {
          window.fullHeihgt = document.documentElement.clientHeight
          that.fullHeihgt = window.fullHeihgt - 61
        })()
      }
    },
  }
};
</script>
