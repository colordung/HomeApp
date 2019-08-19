<template>
  <div>
    <!-- Main Header -->
    <layoutHeader v-if="isFree"></layoutHeader>
    
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
      <div id="app">
        <router-view v-if="isLogin || isFree"/>
      </div>
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    
  </div>
</template>


<script>
  import {
    mapState,
    mapGetters,
    mapActions
  } from 'vuex'
  import LayoutHeader from '@/components/LayoutHeader';

export default {
  name: 'App',
  components: {
    LayoutHeader
  },
  data: function() {
    return {
      isLogin: false,
      isFree: false
    }
  },
  created: function() {
    this.checkIsLogin();
    if (!!sessionStorage.token) {
      this.$store.dispatch('menu/getMenus');
      this.$store.dispatch('menu/getAllMenus');
      this.isFree = true;
    }
  },
  methods: {
    checkIsLogin: function() {
      if (location.href.indexOf('/login') > -1) {
        this.isLogin = true;
      }
      if (location.href.indexOf('/useradd') > -1) {
        this.isLogin = true;
      }
    },
  },
  watch: {
    // 라우터 객체를 감시하고 있다가 checkIsLogin() 함수를 호출한다
    '$route': 'checkIsLogin'
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 10px;
}
</style>
