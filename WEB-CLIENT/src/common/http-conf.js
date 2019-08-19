import axios from 'axios';

/* element-ui init. */

// axios 객체 생성
const http = axios.create({
    //baseURL: process.env.TARGET_URL,
    baseURL: 'http://localhost:8090',
    headers: {
        "jwt-header": sessionStorage.token, //,
        "Content-Type": "application/json"
    }
});

// let isAlertView = false;
// // request 호출 전 로딩바 호출(오류시 로딩바 종료)
// http.interceptors.request.use(
//     (config) => {
//         NProgress.start();
//         return config;
//     },
//     function(error) {
//         if (error.status === 401) {
//             console.error('error 401');
//         }
//         NProgress.done();
//         return Promise.reject(error);
//     }
// );
// // response 종료시 로딩바 종료
// http.interceptors.response.use(
//     response => {
//         if (isAlertView) isAlertView = false;
//         NProgress.done();
//         return response;
//     },
//     error => {
//         NProgress.done();
//         if (!error.response) {
//             if (!isAlertView) {
//                 alert("서버와 통신 연결이 원활하지 않습니다. 다시 시도해주세요.");
//                 isAlertView = true;
//             }
//             // delete sessionStorage.id;
//             // delete sessionStorage.token;
//             // location.href = "/login";
//             return Promise.reject();
//         } else {
//             if (error.response.status === 401) {
//                 console.error('error 401');
//             }
//             if (error.response.status === 500) {
//                 if (error.response.data.message.indexOf('Bad token') > -1) {
//                     // alert("세션이 끊어졌습니다 다시 로그인 해주세요.");
//                     delete sessionStorage.id;
//                     delete sessionStorage.token;
//                     location.href = "/login";
//                 }
//             }
//             return Promise.reject(error.response);
//         }
//     }
// );

export default http;