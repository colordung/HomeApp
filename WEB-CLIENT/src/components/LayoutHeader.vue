<template>
  <!-- Main Header -->
  <header class="main-header">
    <nav class="navbar navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <a href="/" class="navbar-brand"><b>COLORDUNG</b> Platform</a>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
            <i class="fa fa-bars"></i>
          </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown" v-for="menu in menus" v-bind:key="menu.menuSeq">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">{{ menu.menuName }} <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu" v-if="menu.subMenus.length > 0">
                <li v-for="subMenu in menu.subMenus" v-bind:key="subMenu.menuSeq">
                    <router-link :to="subMenu.menuUrl" >{{ subMenu.menuName }}</router-link>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->
        <!-- Navbar Right Menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <!-- Messages: style can be found in dropdown.less-->
            <!-- User Account Menu -->
            <li class="dropdown user user-menu" v-if="isLogin">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                <span class="hidden-xs fa fa-cog"></span>
              </a>
              <ul class="dropdown-menu">
                <li class="user-header">
                  <p>{{"userName"}}</p>
                </li>
                <li class="user-footer">

                  <div class="pull-left info-buttons">
                    <!-- <router-link :to="`/account`" class="btn btn-default btn-flat" >정보수정</router-link> -->
                    <button class="btn btn-default btn-flat" data-toggle="modal" data-target="#modal-account" @click.prevent="onProfileEdit()">{{'EDIT_INFO'}}</button>
                  </div>
                  <div class="pull-right info-buttons" @click.prevent="logout()">
                      <router-link :to="`/logout`" class="btn btn-default btn-flat">{{'LOGOUT'}}</router-link>
                  </div>
                </li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- /.navbar-custom-menu -->
      </div>
      <!-- /.container-fluid -->

    </nav>

    
  </header>


</template>

<script>
    import {
        mapState,
        mapGetters,
        mapActions
    } from 'vuex'

    export default {
        name: 'layoutHeader',
        components: {
        },
        data: function() {
            return {
                modalName: ''
                               
            }
        },
        created: function() {
            
        },
        computed: {
            ...mapState({
                menus: (state) => state.menu.menus
            }),
            ...mapGetters('menu', {
                isMenus: 'isMenus'
            }),
            isLogin: function() {
                return !!sessionStorage.token
            },
        },
        methods: {
            
            

            // 수정 요청
            onSaved: function() {
                this.modalName = '';
            },
            onProfileEdit: function() {
                this.modalName = 'Account';
            },
            onAccountModalClose: function() {
                this.modalName = '';
            },

            // dropdown 부분을 클릭 했을 때 펼쳐짐 유지
            keepDropdown: function (e) {
                e.stopPropagation();
            },
        }
    }
</script>