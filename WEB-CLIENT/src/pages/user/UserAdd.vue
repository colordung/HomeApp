<template>
    <div class="container">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <h1>
              {{"회원가입"}}
              <small>{{"회원정보를 입력합니다."}}</small>
          </h1>
        </section>
        <!-- / Content Header (Page header) -->
         <!-- Main content -->
         <section class="content">

            <div class="row">

              <div class="col-xs-12">

                <div>
                    <div class="box input-box box-info">
                        <div class="box-header">
                        <h3>{{"사용자 정보 입력"}}</h3>
                        <!-- 사용자 정보 입력 -->
                        </div>
                        <div class="box-body">

                        <div class="col-sm-12 col-xs-12">
                            <label class="col-xs-4 control-label">
                            <i class="fa fa-check text-red"></i> {{'ID'}}
                            </label>
                            <div class="col-xs-8 no-padding control-input">
                                <div class="col-xs-4 no-padding">
                                    <input type="text" ref="userId" class="form-control" placeholder="20자이내" maxlength="20"
                                    v-model="userInfo.userId"/>
                                    <!-- 20자이내 -->
                                </div>
                                <div class="col-xs-4 control-input no-padding-right">
                                    <button class="btn btn-default form-control" @click.prevent="checkDup()">{{"중복확인"}}</button>
                                    <!-- 중복확인 -->
                                </div>
                                <div class="col-xs-4 form-normal-text control-input" :class="isDup?'text-blue':'text-red'" v-if="isNewCreate">
                                    {{ isDupText }}
                                </div>
                            </div>
                            <label class="col-xs-4 control-label">
                            <i class="fa fa-check text-red"></i> {{'NAME'}}
                            </label>
                            <div class="col-xs-8 no-padding control-input">
                                <div class="col-xs-4 no-padding">
                                    <input type="text" ref="userName" class="form-control" maxlength="25" 
                                    v-model="userInfo.userName"/>
                                </div>
                            </div>
                            <label class="col-xs-4 control-label">
                            <i class="fa fa-check text-red"></i> {{'PASSWORD'}}
                            </label>
                            <div class="col-xs-8 no-padding control-input">
                                <div class="col-xs-4 no-padding">
                                    <input type="password" ref="userPwd" class="form-control" maxlength="25" 
                                    v-model="userInfo.userPwd"/>
                                </div>
                            </div>
                            <label class="col-xs-4 control-label">
                            <i class="fa fa-check text-red"></i> {{'PASSWORD'}}
                            </label>
                            <div class="col-xs-8 no-padding control-input">
                                <div class="col-xs-4 no-padding">
                                    <input type="password" ref="chkUserPwd" class="form-control" maxlength="25" 
                                    v-model="chkUserPwd"/>
                                </div>
                            </div>
                            
                        </div>

                        </div>


                    </div>

                </div>
              </div>

            </div>

          </section>
          <!-- /Main content -->

          <div class="button-group">
              <button class="btn btn-primary btn-wide" @click.prevent="saveMember()"> {{"저장"}} </button>
              <button class="btn btn-default" @click.prevent="historyback()"> {{"취소"}} </button>
          </div>

    </div>
</template>
<script>

    export default {
        name: 'UserAdd',
        components: {
        },
        data: function() {
            return {
                // 사용여부옵션
                useYNOtion: [{
                    name: 'USE',
                    value: 'Y'
                }, {
                    name: 'UNUSED',
                    value: 'N'
                }],
                // 사용자구분
                corpKind: "",
                // 수정모드
                editMode: false,
                chkUserPwd: null,
                userInfo: {
                        userId : "",
                        userPwd : null,
                        userName : null
                },
                isDup: false,
                isDupText: ''
            
            }
        },
        created: function() {
            this.newCreate = true;
        },
        computed: {
            

            // 수정보드 확인
            isEditMode: {
                get: function() {
                    return this.editMode;
                },
                set: function(val) {
                    this.editMode = val;
                }
            },
            isNewCreate: function() {
                return this.newCreate;
            }
            
        },
        watch: {
            // 아이디 변경시 감지
            'userInfo.userId': function(newData) {
                if (this.newCreate) {
                    this.isDupText = "";
                    this.isDup = false;
                }
            },
            'chkUserPwd': function(newData) {
                if (newData != this.userInfo.userId) {
                    this.isDupText = "패스워드가 동일하지 않아";
                    this.isDup = false;
                }
            }
        },
        methods: {
            
            // 아이디 중복체크
            checkDup: function() {
                this.isDupText = "";
                this.isDup = false;
                let userId = this.userInfo.userId;
                let alertMsg = '';
                // 영문과 숫자만 입력 가능하도록 정규식 체크
                let reg = new RegExp(/^[A-Za-z0-9+]*$/g);

                if (userId === '') {
                    alertMsg = "아이디를 입력해주세요."; //아이디를 입력해주세요.
                } else if (!reg.test(userId.trim())) {
                    alertMsg = "영문/숫자로 기입해주세요. 특수문자 및 한글은 사용할 수 없습니다."; //영문/숫자로 기입해주세요. 특수문자 및 한글은 사용할 수 없습니다.'
                } else if (userId.trim().length > 20) {
                    alertMsg = "아이디는 20자 이내 영문/숫자로 기입해주세요.";//'아이디는 20자 이내 영문/숫자로 기입해주세요.'
                }
                // 체크메세지가 있는 경우 alert 표시
                if (alertMsg !== '') {
                    this.$alert(alertMsg, 'Alert', {
                        confirmButtonText: 'OK',
                        callback: action => {
                            this.$refs.userId.focus();
                        }
                    });
                } else {
                    this.$axios.get(`/api/user/duplicationUserIdCheck/${userId}`).then((result) => {
                        if (result.data === 0) {
                            this.isDup = true;
                            this.isDupText = "사용가능한 아이디 입니다.";
                        } else {
                            this.isDup = false;
                            this.isDupText = "이미 등록된 아이디 입니다.";
                            this.$refs.userId.focus();
                        }
                    }).catch(error => NProgress.done());
                }
            },
            saveMember: function() {
                let alertMsg = "";
                let target = "";
                // 관리자 저장시 체크
                if (this.corpKind === this.ADMIN_GROUP) {
                    if (this.userInfo.userId === '') {
                        alertMsg = "아이디를 입력해주세요.";
                        // 아이디를 입력해주세요.
                        target = "userId";
                    } else if (this.userInfo.userName === '') {
                        alertMsg = "이름을 입력해주세요.";
                        // 이름을 입력해주세요.
                        target = "userName";
                    } else if (!this.isEditMode) {
                        alertMsg = "아이디 중복확인을 해주세요.";
                        //아이디 중복확인을 해주세요.
                        target = "userId";
                    }
                    // 대행사 저장시 체크
                }

                if (alertMsg !== "") {
                    this.$alert(alertMsg, 'Alert', {
                        confirmButtonText: 'OK',
                        callback: action => {
                            children[target].focus();
                        }
                    });
                } else {
                    this.$confirm("저장하시겠습니까?", 'Warning', {
                        //저장하시겠습니까?
                        confirmButtonText: 'OK',
                        cancelButtonText: 'Cancel',
                        type: 'warning'
                    }).then(() => {
                        this.$axios.post(`/api/user/saveUser`, this.userInfo).then(result => {
                            if (result.status == 200) {
                                this.$alert("저장되었습니다.", 'Alert', {
                                    //저장되었습니다.
                                    confirmButtonText: 'OK',
                                    callback: action => {
                                        this.$router.push({
                                            path: '/'
                                        });
                                    }
                                });
                            }
                        }).catch(error => {
                            NProgress.done();
                            this.$alert("저장시 오류가 발생했습니다. 다시 시도해주세요.", 'Alert', {
                                //저장시 오류가 발생했습니다. 다시 시도해주세요.
                                confirmButtonText: 'OK',
                                callback: action => {}
                            });
                        });
                    }).catch(() => {});
                }
            },
            historyback: function() {
                this.$router.go(-1);
            }
        }
    }
</script>
<style scoped></style>