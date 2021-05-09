import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'normalize.css/normalize.css';
import './styles/index.scss';
import App from './App.vue';
import router from './router';
import echarts from 'echarts';

import http from '@/utils/request'
Vue.use(ElementUI);

Vue.config.productionTip = false;
Vue.prototype.$echarts = echarts;
// 挂载全局
Vue.prototype.$http = http;
new Vue({
  router,
  data: function(){
    return {
      modelCreatedType: "modelFileType"
    }
  },
  render: h => h(App),
}).$mount('#app');
