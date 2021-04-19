<template>
    <!-- 顶部导航条 -->
    <div class="nav_container">
        <div class="topBanner">
            <!-- 左侧logo图标 -->
            <div class="logoImg">
                <a href="javascript:;"><img src="../../../static/img/Logo.png" alt="logo"></a>
            </div>
            <!-- 右侧搜索框 -->
            <div class="search">
                <!-- 搜索框 -->
                <input type="text" placeholder="搜索感兴趣的知识点" maxlength="20" autofocus="autofocus" v-model="indexSearchContent" @keyup.enter="search();initialization()">
                <button @click="search();initialization()">
                    <i class="iconfont icon-fangdajing"></i>
                </button>

                <!-- 登陆注册 -->
                <div class="login">
                    <a href="javascript:;">登录&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;注册</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import PubSub from 'pubsub-js'
import {mapState} from 'vuex'
    export default {
        computed: {
            indexSearchContent: {
                get () {
                    return this.$store.state.searchForm.indexSearchContent
                },
                set (val) {
                    this.$store.dispatch('setSearchContent', val.trim())
                }
            },
            ...mapState(['searchForm'])
        },

        methods: {
            search () {
                if(this.indexSearchContent === ''){
                    alert('【没有输入呀！！！】')
                }else{
                    this.$store.dispatch('searchClasses', this.indexSearchContent)
                    setTimeout(() => {
                        this.$store.dispatch('setSearchForm', this.searchForm.indexSearchContent)
                    }, 300)
                }
            },
            initialization () {
                if(this.indexSearchContent != ''){
                    setTimeout(() => {
                        // 发布消息，我要使用这个函数
                        PubSub.publish('initialization')
                    }, 300)
                }
            }
        }
    }
</script>

<style scoped>
/* 顶部导航条 */
.nav_container{
    width: 100%;
    height: 64px;
    background-color: #4285F4;
}

.topBanner{
    width: 1200px;
    height: 64px;
    position: relative;
    color: #000;
    font-size: 16px;
    margin: 0 auto;
}

.topBanner .logoImg{
    position: absolute;
    /* width: 217px; */
    height: 40px;
    top: 17px;
    float: left;
    margin-left: -120px;
}

.topBanner .logoImg img{
    /* width: 217px; */
    margin-top: -5px;
    height: 40px;
    border-radius: 10px;
    /* 盒子阴影 向右，下黑阴影  向左，上白阴影 */
    box-shadow: 18px 18px 30px rgba(0,0,0,.2), -18px -18px 30px #4285F4;
    /* 过渡效果 ease-out是先快速后慢速 */
    transition: all 0.2s ease-out;
}

.topBanner .logoImg img:hover{
    /* inset是向内阴影  默认是外部阴影outset */
    box-shadow: 0 0 0 rgba(0, 0, 0, 0.2),
                0 0 0 #4285F4,
                inset 18px 18px 30px rgba(0, 0, 0, 0.1),
                inset -18px -18px 30px #4285F4;
}

.topBanner .nav{
    position: absolute;
    float: left;
    width: 450px;
    line-height: 64px;
    left: 217px;
    padding-left: 70px;
}

.topBanner .search{
    position: absolute;
    float: right;
    right: 0;
    top: 8px;
}

.topBanner .search input{
    position: absolute;
    right: 400px;
    width: 440px;
    height: 45px;
    border-radius: 10px;
    border: 1px solid #fff;
    /* 设置文字开始空格长度 */
    text-indent: 25px;
    font-size: 14px;
    /* 取消input获得焦点时的边框 */
    outline: none;
}

.topBanner .search input:hover{
    border: 1px solid #dddada;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.topBanner .search input:focus{
    border: 1px solid #dddada;
}

/* 设置placeholder样式 */
input::placeholder{
    color: #B8B8B8;
}

.topBanner .search button{
    position: absolute;
    background-color: #fff;
    height: 45px;
    width: 56px;
    border: none;
    color: #fff;
    font-size: 15px;
    border-radius: 0 10px 10px 0;
    right: 400px;
    top: 1px;
    cursor: pointer;
    outline: none;
    transition: all 0.4s;
}

.topBanner .search button:hover{
    background-color: #dddada;
}

.topBanner .search button:hover i{
    color: #fff;
}

.topBanner .search button i{
    font-size: 25px;
    color: #B8B8B8;
}

.topBanner .search .login{
    float: right;
    margin-top: 10px;
    font-size: 18px;
}

.topBanner .search .login a{
    transition: all 0.4s;
    font-weight: bold;
    color: #fff;
}

.topBanner .search .login a:hover{
    color: #dddada;
}
</style>
