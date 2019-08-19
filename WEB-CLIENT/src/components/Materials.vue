<template>
    <div class="container">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                {{"식재료 관리"}} 
                <small>
                    {{"식재료 정보를 관리합니다."}} 
                </small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-home"></i> {{"HOME"}}</a></li>
                <li>{{"음식 관리"}}</li> 
                <li class="active">{{"식재료 관리"}}</li> 
            </ol>
        </section>
        <!-- / Content Header (Page header) -->

        <!-- Main content -->
        <section class="content">

            <div class="row">

                <div class="col-xs-12">
                    <div class="box input-box box-info">
                        <div class="box-body">

                            <div class="col-sm-2 col-xs-12">
                                <label class="col-xs-4 control-label text-ellipsis"> <!-- 등록기간 -->
                                    {{"이름"}} <!-- 등록기간 -->
                                </label>
                                <div class="col-xs-8 no-padding">
                                    <input type="text" class="form-control" ref="name" v-model="materials.name" >
                                </div>
                            </div>
                            <div class="col-sm-2 col-xs-12">
                                <label class="col-xs-6 control-label text-ellipsis"> <!-- 등록기간 -->
                                    {{"탄수화물"}} <!-- 등록기간 -->
                                </label>
                                <div class="col-xs-6 no-padding">
                                    <input type="text" class="form-control" v-model="materials.carbon" >
                                </div>
                            </div>
                            <div class="col-sm-2 col-xs-12">
                                <label class="col-xs-6 control-label text-ellipsis"> <!-- 등록기간 -->
                                    {{"단백질"}} <!-- 등록기간 -->
                                </label>
                                <div class="col-xs-6 no-padding">
                                    <input type="text" class="form-control" v-model="materials.protein" >
                                </div>
                            </div>
                            <div class="col-sm-2 col-xs-12">
                                <label class="col-xs-6 control-label text-ellipsis"> <!-- 등록기간 -->
                                    {{"지방"}} <!-- 등록기간 -->
                                </label>
                                <div class="col-xs-6 no-padding">
                                    <input type="text" class="form-control" v-model="materials.fat" >
                                </div>
                            </div>
                            <div class="col-sm-2 col-xs-12">
                                <label class="col-xs-6 control-label text-ellipsis"> <!-- 사용여부 -->
                                    {{"나트륨"}} <!-- 사용여부 -->
                                </label>
                                <div class="col-xs-6 no-padding form-normal-text">
                                    <input type="text" class="form-control" v-model="materials.na" >
                                </div>
                            </div>

                            

                            <div class="col-sm-1 col-xs-12">
                                <button class="btn btn-info" @click="saveMaterials()">
                                    <i class="fa fa-file-text"></i> {{"등록"}} <!-- 등록 -->
                                </button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header box-header-tools">
                            <div class="text-sm text-gray">{{"총"}} {{ contentsList.totalRows }}{{"건"}}</div> <!-- 총 ? 건이 조회 되었습니다. -->
                        </div>

                        <div class="box-body table-responsive no-padding" v-if="contentsList.totalRows !== 0">
                            <table class="table table-hover text-center text-nowrap">
                                <tbody>
                                <tr>
                                    <th>{{"No"}}</th><!-- No. -->
                                    <th>{{"이름"}}</th><!-- 컨텐츠명 -->
                                    <th>{{"탄수화물"}}</th> <!-- 광고주 -->
                                    <th>{{"단백질"}}</th><!-- 유효기간 -->
                                    <th>{{"지방"}}</th><!-- 구분 -->
                                    <th>{{"나트륨"}}</th><!-- 비율 -->
                                </tr>

                                <tr v-for="(contents, index) in contentsList.dataList" v-if="isContentsList" :key="index">
                                    <td>{{ contentsList.totalRows - (index+(searchData.pageSize*(searchData.page-1))) }}</td>
                                    <td>{{ contents.name }}</td>
                                    <td>{{ contents.carbon }}</td>
                                    <td>{{ contents.protein }}</td>
                                    <td>{{ contents.fat }}</td>
                                    <td>{{ contents.na }}</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>

                        <div class="box-body table-responsive no-padding" v-else align="center">
                            {{"조회결과가 없습니다."}}<!-- 조회결과가 없습니다. 입력하신 내용을 다시 확인하세요. -->
                        </div>

                        <div class="box-footer">
                            <paging-navigation :totalRows="contentsList.totalRows" :page="searchData.page"
                                               :pageSize="searchData.pageSize" @interface="goPage"></paging-navigation>
                        </div>
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
        <!-- /Main content -->
    </div>
</template>

<script>
    import {
        mapState,
        mapGetters,
        mapActions,
    } from 'vuex'
    import PagingNavigation from '@/components/PagingNavigation'

    export default {
        name: 'MaterialsList',
        components: {
            PagingNavigation,
        },
        data: function() {
            return {
                
                // 검색어 옵션
                searchKindOption: [{
                    name: "컨텐츠명", // '컨텐츠명',
                    value: 'name',
                }],
                // 검색조건
                searchData: {
                    'searchKind': 'name',
                    'searchText': '',
                    'page': 1,
                    'pageSize': 15,
                },
                // 
                materials: {
                    'name': '',
                    'carbon': 0,
                    'protein': 0,
                    'fat': 0,
                    'na': 0,
                },
                // 컨텐츠목록
                contentsList: {
                    totalRows: 0,
                    dataList: [],
                },
                currentPage: 1,
                pageSize: 15,
                
            }
        },

        created: function() {
            this.$nextTick(() => {
                if (!this.isHistoryBack) {
                    // 기준 검색일자 셋팅
                    this.currentPage = 1;
                    this.searchData.pageSize = this.pageSize;
                    this.getMaterialsList(1);
                }
            })
        },
        computed: {
            ...mapState({
                
            }),
            
            // 검색조건 옵션값 체크
            isSearchKindOption: function() {
                return this.searchKindOption.length > 0
            },
            // 컨텐츠목록 데이터 체크
            isContentsList: function() {
                return this.contentsList.dataList.length > 0
            },
            classObject: function() {
                return {
                    labelSuccess: true,
                }
            },
        },
        
        methods: {
            // 컨텐츠 목록 조회
            getMaterialsList: function(pageNum) {
                console.log('materialsList>>>>>',this.searchData)
                this.searchData.page = pageNum;
                this.$axios.post('/api/materials/list', this.searchData).then(result => this.contentsList = result.data)
            },
            
            // 페이징처리
            goPage: function(pageNum) {
                this.searchData.page = pageNum
                this.getMaterialsList(pageNum)
            },
            goFocus: function(target) {
                this.$refs[target].focus();
            },
            saveMaterials: function() {
                let alertMsg = "";
                let target = "";
                if (this.materials.name === '') {
                    alertMsg = "식재료명을 입력해주세요."; // "컨텐츠명을 입력해주세요.";
                    target = "name";
                }
                
                if (alertMsg !== "") {
                    this.$alert(alertMsg, 'ALERT', {  // 'Alert'
                        confirmButtonText: 'OK',    // OK
                        callback: action => this.goFocus(target)
                    });                   
                } else {
                    this.$axios.post('/api/materials/save', this.materials)
                        .then(result => {
                            if (result.status == 200) {
                                this.$alert("저장되었습니다", 'NOTIFY', {    // 저장되었습니다 // 알림
                                    confirmButtonText: 'OK',    // OK
                                    callback: action => {
                                        this.getMaterialsList(1);
                                        this.materials.name = '';
                                        this.materials.carbon = 0;
                                        this.materials.protein = 0;
                                        this.materials.fat = 0;
                                        this.materials.na = 0;
                                    }
                                });
                            }
                        })
                        .catch(error => {
                            NProgress.done();
                            this.$alert("컨텐츠 저장시 오류가 발생했습니다. 다시 시도해주세요.", 'ALERT', { // 컨텐츠 저장시 오류가 발생했습니다. 다시 시도해주세요. // Alert
                                confirmButtonText: 'OK',    // OK
                                callback: action => {}
                            });
                        });
                    
                }
            }
        },
    }
</script>

<style>
    table.table td:nth-child(2) {
        text-align: left;
    }
</style>