import Vue from 'vue';
import Vuex from 'vuex';
import menu from '@/store/modules/menu';
//import user from '@/store/modules/user';
import http from '@/common/http-conf';
import createLogger from 'vuex/dist/logger';

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== 'production';

Vuex.Store.prototype.$axios = http;

export default new Vuex.Store({
    modules: {
        menu
    },
    strict: debug,
    plugins: [createLogger()]
});