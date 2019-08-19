<template>
    <ul class="pagination pagination-sm no-margin">
        <li :class="!isPreBlock?'disabled':''" @click.stop.prevent="prevBlock()"><a href="#">&laquo;</a></li>
        <li v-for="page in pages" v-if="isPages" :class="currentPage==page.num? 'active':''" @click.prevent="setPage(page.num)"><a href="#" >{{ page.num }}</a></li>
        <li :class="!isNextBlock?'disabled':''" @click.stop.prevent="nextBlock()"><a href="#"  >&raquo;</a></li>
    </ul>
</template>
<script>
    export default {
        name: 'paging-navigation',
        // 부모로부터 전달받는 데이터 정의
        props: ['totalRows', 'page', 'pageSize'],
        //  컴포넌트 안에서 사용할 데이터 정의
        data: function() {
            return {
                blockSize: 10,
                pageData: [],
                currentPage: 1,
                totalPages: 0,
                blocks: 0,
                startPage: 1,
                endPage: 1
            }
        },
        // 데이터 바인딩의 완료되었을때 작동되는 훅
        created: function() {
            this.setTotalpage();
        },
        computed: {
            // 페이징 목록 존재여부 확인
            isPages: function() {
                return this.pages.length > 0;
            },
            // 이전 블록 존재여부 확인
            isPreBlock: function() {
                return this.startPage > this.blockSize;
            },
            // 다음 블록 존재여부 확인
            isNextBlock: function() {
                return this.endPage < this.totalPages;
            },
            // 페이징 목록 계산
            pages: {
                get: function() {
                    return this.pageData;
                },
                set: function(pageNum) {
                    this.currentPage = pageNum;
                    // 블록 계산
                    this.blocks = parseInt(this.currentPage / this.blockSize);
                    if (this.currentPage % this.blockSize) {
                        this.blocks++;
                    }
                    // 시작 페이지 계산
                    this.startPage = (this.blocks - 1) * this.blockSize + 1;
                    if (this.startPage < 1) this.startPage = 1;
                    // 종료 페이지 계산
                    this.endPage = this.startPage + (this.blockSize - 1);
                    if (this.endPage > this.totalPages) {
                        this.endPage = this.totalPages;
                    }
                    // 페이지 목록 생성
                    let pageArray = [];
                    for (let i = this.startPage; i <= this.endPage; i++) {
                        pageArray.push({
                            num: i
                        });
                    }
                    this.pageData = pageArray;
                }
            }
        },
        watch: {
            'totalRows': 'setTotalpage',
            'page': function(num) {
                this.pages = num;
            }
        },
        methods: {
            // 페이지 버튼 클릭 이벤트
            setPage: function(num) {
                this.pages = num;
                // 부모 이벤트 호출
                this.$emit('interface', num);
                $(window).scrollTop(0);
            },
            setTotalpage: function() {
                // 토탈페이지 계산
                this.totalPages = parseInt(this.totalRows / this.pageSize);
                if (this.totalRows % this.pageSize) {
                    this.totalPages++;
                }
                // computed 계산(setter 호출)
                this.pages = this.currentPage;
            },
            // 이전 블록 버튼 클릭 이벤트
            prevBlock: function() {
                if (this.isPreBlock) {
                    let num = (parseInt((this.startPage - 1) / this.blockSize) - 1) * this.blockSize + 1;
                    this.pages = num;
                    if (this.pages < 1) this.pages = 1;
                    // 부모 이벤트 호출
                    this.$emit('interface', num);
                }
            },
            // 다음 블록 버튼 클릭 이벤트
            nextBlock: function() {
                if (this.isNextBlock) {
                    let num = this.endPage + 1;
                    this.pages = num;
                    if (this.pages > this.totalPages) this.pages = this.totalPages;
                    // 부모 이벤트 호출
                    this.$emit('interface', num);
                }
            }
        }
    }
</script>
<style scoped>

</style>