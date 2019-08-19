import auth from '@/common/auth'
import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/pages/Login'
import UserAdd from '@/pages/user/UserAdd';
import MaterialsList from '@/components/Materials'
import NotFound from '@/pages/NotFound'

Vue.use(Router)
// 로그인여부 체크
const requireAuth = (to, from, next) => {
  if (!auth.loggedIn()) {
      next({
          path: '/login',
          query: { redirect: to.fullPath }
      });
  } else {
      next();
  }
};

const route = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld,
      // 인증 여부를 체크하는 requreAuth를 beforeEnter 속성에 추가했다
      beforeEnter: requireAuth

    },
    { /* 로그인 */
      path: '/login',
      name: 'Login',
      component: Login,
    },
    { /* 로그아웃 */
      path: '/logout',
      beforeEnter(to, from, next) {
          auth.logout();
          Vue.prototype.$axios.defaults.headers.common['jwt-header'] = '';
          // next('/');
          location.href = '/';
      }
    },
    { /* 사용자 등록 */
      path: '/useradd',
      name: 'UserAdd',
      component: UserAdd
    },
    { /* 식재료관리 목록 */
      path: '/contents/materials-list',
      name: 'MaterialsList',
      component: MaterialsList,
      beforeEnter: requireAuth
    },
    { /* 정의되지 않는 URL 호출시 처리 */
      path: '*',
      name: 'NotFound',
      component: NotFound,
      beforeEnter: requireAuth
    }
  ],
  // 페이지 이동시 스크롤바를 top위치로 이동시킨다(뒤로가기의 경우 스크롤위치 기억)
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
        return savedPosition;
    } else {
        return { x: 0, y: 0 };
    }
  }
  // // 페이지 이동전 페이지로딩 바 호출
  // beforeResolve(to, from, next) {
  //   if (to.name) {
  //       NProgress.start();
  //   }
  //   next();
  // },
  // // 페이지 이동 후 페이지로딩 바 종료
  // afterEach(to, from) {
  //   NProgress.done();
  // }
});
// 페이지 이동전 페이지로딩 바 호출
route.beforeResolve((to, from, next) => {
  if (to.name) {
      NProgress.start();
  }
  next();
});
// 페이지 이동 후 페이지로딩 바 종료
route.afterEach((to, from) => {
  NProgress.done();
});
export default route;