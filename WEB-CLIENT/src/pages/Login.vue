<template>
  <div class="container">
    <div class="login-box">
      <div class="login-logo">
        <b>COLORDUNG </b>Platform
      </div>
      <div class="login-box-body">

        <div class="login-box-msg">
            {{"관리자 로그인"}}
          <!-- 관리자 로그인 -->
        </div>

        <form @submit.prevent="login">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="로그인 아이디" v-model="userId"><!--로그인 아이디-->
            <span class="fa fa-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="비밀번호" v-model="userPwd"><!--비밀번호-->
            <span class="fa fa-lock form-control-feedback"></span>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-danger btn-block btn-flat">{{"로그인"}}<!--로그인 --></button>
            <button type="button" class="btn btn-info btn-block btn-flat" @click="useradd">{{"회원가입"}}<!--회원가입 --></button>
          </div>
        </form>

      </div>

      <div class="login-box-footer-msg">
          {{"아이디/비밀번호를 잊으셨을 경우 어떻게 해야할까."}}
    
      </div>
    </div>
  </div>
</template>

<script>
  import auth from '@/common/auth'

  export default {
    name: 'login',
    data: function() {
      return {
        userId: '',
        userPwd: '',
      }
    },
    created: function() {
    },
    methods: {
      login: function() {
        const redirect = this.$route.query.redirect || '/main'
        auth.login(this.userId, this.userPwd, function(loggedIn) {
          if (!loggedIn) {
            alert('로그인에 실패하였습니다.\n아이디와 비밀번호를 확인해주세요!');
          } else {
            // $router.replace($route.query.redirect || '/')
            location.href = redirect;
          }
        }, this)
      },
      useradd: function() {
        console.log("test");
        location.href = '/useradd';
      }
    }
  }
</script>

<style scoped>

</style>
