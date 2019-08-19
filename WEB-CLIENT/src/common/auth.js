export default {
    login(id, pass, cb, _this) {
        if (sessionStorage.token) {
            if (cb) cb(true);
            this.onChange(true);
            return;
        }
        pretendRequest(id, pass, (res) => {

            if (res.authenticated) {
                sessionStorage.id = id;
                sessionStorage.token = res.token;
                if (cb) cb(true);
                this.onChange(true);
            } else {
                if (cb) cb(false);
                this.onChange(false);
            }
        }, _this);
    },

    getToken() {
        return sessionStorage.token;
    },
    getDate() {
        var base64Url = sessionStorage.token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));
        var result = JSON.parse(jsonPayload);
        var date = new Date(result.exp*1000);
        return date;
        
    },
    logout(cb) {
        delete sessionStorage.id;
        delete sessionStorage.token;
        if (cb) cb();
        this.onChange(false);
    },

    loggedIn() {
        return !!sessionStorage.token;
    },

    onChange() {}

};

function pretendRequest(userId, userPwd, cb, _this) {
    setTimeout(() => {
        let data = {
            "userId": userId,
            "userPwd": userPwd
        };

        _this.$axios.post('/login', data).then(result => {
            if (result.headers['jwt-header']) {
                cb({
                    authenticated: true,
                    token: result.headers['jwt-header']
                });
            } else {
                cb({ authenticated: false });
            }
        }).catch(err => {
            NProgress.done();
            // alert('로그인 실패!');
            // console.log('err => ', err);
            let message = "아이디와 패스워드를 확인해주세요.";
            if (err.response.data.message !== undefined) {
                message = err.response.data.message;
            }
            _this.$alert(message, 'Alert', {
                confirmButtonText: 'OK',
                callback: action => {
                    // this.$refs.userId.focus();
                }
            });
        });

    }, 0);
}
