// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'es6-promise/auto'
import Vue from 'vue'
import Vuex from 'vuex'
import App from '@/App'
import router from '@/router'
import store from '@/store';
import http from '@/common/http-conf';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(ElementUI);
Vue.prototype.$axios = http;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
