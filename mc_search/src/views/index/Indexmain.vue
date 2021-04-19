<template>
    <div class="main_body">
        <div class="sep20"></div>
        <div class="container_wrapper">
            <div class="top" :style="{opacity: top_opacity}"><p>共&nbsp;{{ filterSearchResults.length }}&nbsp;条&nbsp;<span class="keyword">{{ searchContent }}</span>&nbsp;知识点相关的结果</p></div>
            <div class="sep20b"></div>
            <div class="lists">
                <!-- 状态选择栏 -->
                <div class="mid-tag-bar">
                    <ul>
                        <li @click="returnAll"><span>全部（{{ filterSearchResults.length }}）</span></li>
                        <li class="state-dropdown">
                            <div class="course-state" id="course-state">课程状态</div>
                            <div class="state-dropdown-content">
                                <div @click="stateAll"><a href="javascript:;">全部</a></div>
                                <div @click="stateIng"><a href="javascript:;" id="state-ing">正在进行</a></div>
                                <div @click="stateTodo"><a href="javascript:;" id="state-todo">即将开始</a></div>
                                <div @click="stateEd"><a href="javascript:;" id="state-ed">已结束</a></div>
                            </div>
                        </li>
                        <div class="line1"></div>
                        <li class="style-dropdown">
                            <div class="teacher-style" id="teacher-style">教师风格</div>
                            <div class="style-dropdown-content">
                                <div @click="styleAll"><a href="javascript:;">全部</a></div>
                                <div @click="style1"><a href="javascript:;" id="style-1">严厉</a></div>
                                <div @click="style2"><a href="javascript:;" id="style-2">自然</a></div>
                                <div @click="style3"><a href="javascript:;" id="style-3">幽默</a></div>
                                <div @click="style4"><a href="javascript:;" id="style-4">技巧</a></div>
                            </div>
                        </li>
                        <div class="line2"></div>
                    </ul>
                    <div class="tag-left">
                        <input type="checkbox" class="tl-checkbox" v-model="isquality" @change="equality_change">
                        <span>国家精品课</span>
                    </div>
                </div>
                <!-- 词条栏 -->
                <div class="courseCardListBox">
                    <div>
                        <div class="wait_input" v-if="beforeSearch">请按“回车”键发起检索</div>
                        <paginate
                            name="result_lists"
                            :list="filterSearchResults"
                            :per="20"
                        >
                        <!-- 单个词条 -->
                            <div class="clist" v-for="(filterSearchResult, index) in paginated('result_lists')" :key="index">
                            <div class="imgWrapper"><img :src="filterSearchResult.course_img" :style="{}"></div>
                            <div class="contents">
                                <!-- 知识点名 -->
                                <div class="knowledgeName">
                                    <a href="javascript:;">{{ filterSearchResult.knowledge_point }}</a>
                                </div>
                                <!-- 学校与作者 -->
                                <div class="first-row">
                                    <a class="univ" href="javascript:;">{{ filterSearchResult.school }}</a>
                                    <!-- 作者名字 -->
                                    <span class="auth-wrapper">
                                        <a class="auth" href="#">{{ filterSearchResult.teacher }}</a>
                                        <div class="teachStyle">
                                            <i class="iconfont icon-laoshi" :style="{color: filterSearchResult.teacher_style === '严厉'?'red':filterSearchResult.teacher_style === '幽默'?'purple':filterSearchResult.teacher_style === '自然'?'yellowgreen':'rgb(207, 207, 102)'}">&nbsp;{{ filterSearchResult.teacher_style }}</i>
                                        </div>
                                    </span>
                                </div>
                                <!-- 课程名 -->
                                <div class="contents-title">
                                    <a href="javascript:;">所属课程：{{ filterSearchResult.course_name }}</a>
                                    <div class="uniq" v-if="filterSearchResult.isquality === '1'">
                                        <span>国家精品</span>
                                    </div>
                                </div>
                                <!-- 简介 -->
                                <div class="brief-row" id="brief-row">课程简介：{{ filterSearchResult.introduction.slice(0, 70) }}...</div>
                                <!-- 正在进行图标 -->
                                <div class="clock-icon">
                                    <span :style="{color: filterSearchResult.status === '正在进行'?'#00C758':filterSearchResult.status === '已结束'?'#BAB8B8':'orange'}">{{ filterSearchResult.status }}&nbsp;&nbsp;</span>
                                    <i class="iconfont icon-shizhong" :style="{color: filterSearchResult.status === '正在进行'?'#00C758':filterSearchResult.status === '已结束'?'#BAB8B8':'orange'}"></i>
                                </div>
                            </div>
                            </div>
                        </paginate>
                        <!-- 找不到结果的显示 -->
                        <div class="notfound" v-if="filterSearchResults.length === 0" :style="{opacity: transparent}">
                            <img src="../../../static/img/notfound.png" alt="notfound">
                        </div>
                        <!-- 分页 -->
                        <!-- <paginate-links
                            for="result_lists"
                            :show-step-links="true"
                        >
                        </paginate-links> -->
                    </div>
                </div>
                <!-- 热搜榜（知识点热搜） -->
                <ul class="hot-points">
                    <div class="title"><span>知识点热榜</span></div>
                    <li><span class="num">1</span><a href="javascript:;">{{ hotSearch[0].knowledge_point }}</a><i class="iconfont icon-huo"></i><span class="hotDegree">{{ hotSearch[0].hot_knowledge }}</span></li>
                    <li><span class="num">2</span><a href="javascript:;">{{ hotSearch[1].knowledge_point }}</a><i class="iconfont icon-huo"></i><span class="hotDegree">{{ hotSearch[1].hot_knowledge }}</span></li>
                    <li><span class="num">3</span><a href="javascript:;">{{ hotSearch[2].knowledge_point }}</a><i class="iconfont icon-huo"></i><span class="hotDegree">{{ hotSearch[2].hot_knowledge }}</span></li>
                    <li><span class="num">4</span><a href="javascript:;">{{ hotSearch[3].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[3].hot_knowledge }}</span></li>
                    <li><span class="num">5</span><a href="javascript:;">{{ hotSearch[4].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[4].hot_knowledge }}</span></li>
                    <li><span class="num">6</span><a href="javascript:;">{{ hotSearch[5].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[5].hot_knowledge }}</span></li>
                    <li><span class="num">7</span><a href="javascript:;">{{ hotSearch[6].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[6].hot_knowledge }}</span></li>
                    <li><span class="num">8</span><a href="javascript:;">{{ hotSearch[7].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[7].hot_knowledge }}</span></li>
                    <li><span class="num">9</span><a href="javascript:;">{{ hotSearch[8].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[8].hot_knowledge }}</span></li>
                    <li><span class="num">10</span><a href="javascript:;">{{ hotSearch[9].knowledge_point }}</a><span class="hotDegree">{{ hotSearch[9].hot_knowledge }}</span></li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
    import {mapState} from 'vuex'
    import PubSub from 'pubsub-js'

    export default {
        computed: {
            ...mapState(['indexSearchResults']),  //从state里面读取搜索结果数组
            ...mapState(['searchForm']),
            ...mapState(['hotSearch']),
            ...mapState(['searchContent'])
        },

        data () {
            return {
                bgColor: '#fff',
                isquality: false,
                transparent: 0,
                top_opacity: 0,
                beforeSearch: true,
                filter_conditions: {
                    status: '',
                    teacher_style: '',
                    isquality: ''
                },
                hot_search: [],
                filterSearchResults: [],
                paginate: ['result_lists']
            }
        },

        methods: {
            // 国家精品课勾选响应函数
            equality_change () {
                this.isquality?this.filter_conditions.isquality = '1' : this.filter_conditions.isquality = ''
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
            },

            // 筛选函数
            filter (condition, indexSearchResults) {
                return indexSearchResults.filter( item => {  // item 是 indexSearchResult 即返回数组的每一项
                    return Object.keys(condition).every( key => {  // key 是 上述每一项键值对的键名，如
                        // item[key]是后端传来的每一项，condition是筛选数组的每一项，因为共用 key 键名，所以要求两个数组键名相同
                        return item[key].includes(condition[key])
                    })
                })
            },

            // 返回全部知识点
            returnAll () {
                this.filter_conditions.status = ''
                this.filter_conditions.teacher_style = ''
                this.filter_conditions.isquality = ''
                this.isquality = false
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
            },

            // 课程状态设置
            stateAll () {  //初始状态，包含所有
                this.filter_conditions.status = ''
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('course-state').innerHTML = '课程状态'
            },
            stateIng () {  //正在进行状态
                this.filter_conditions.status = '正在进行'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('course-state').innerHTML = document.getElementById('state-ing').innerHTML
            },
            stateTodo () {  //即将开始状态
                this.filter_conditions.status = '即将开始'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('course-state').innerHTML = document.getElementById('state-todo').innerHTML
            },
            stateEd () {  //已结束状态
                this.filter_conditions.status = '已结束'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('course-state').innerHTML = document.getElementById('state-ed').innerHTML
            },

            // 教师风格设置
            styleAll () {
                this.filter_conditions.teacher_style = ''
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('teacher-style').innerHTML = '教师风格'
            },
            style1 () {
                this.filter_conditions.teacher_style = '严厉'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('teacher-style').innerHTML = document.getElementById('style-1').innerHTML
            },
            style2 () {
                this.filter_conditions.teacher_style = '自然'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('teacher-style').innerHTML = document.getElementById('style-2').innerHTML
            },
            style3 () {
                this.filter_conditions.teacher_style = '幽默'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('teacher-style').innerHTML = document.getElementById('style-3').innerHTML
            },
            style4 () {
                this.filter_conditions.teacher_style = '技巧'
                this.filterSearchResults = this.filter(this.filter_conditions, this.indexSearchResults)
                document.getElementById('teacher-style').innerHTML = document.getElementById('style-4').innerHTML
            },
            // 获取热搜榜
            get_hot_knowledge () {
                this.$store.dispatch('get_hot_knowledge')
            }
        },

        mounted () {
            this.get_hot_knowledge(),
            // 订阅消息
            PubSub.subscribe ('initialization', (msg) => {
                this.returnAll()
                this.transparent = 1
                this.beforeSearch = false,
                this.top_opacity = 1
            })
        }
    }
</script>

<style scoped>
/* 中间主体部分 */
.main_body{
    width: 100%;
    height: 4080px;
    background-color: #F5F5F5;
}

.sep20{
    width: 100%;
    height: 20px;
}

.container_wrapper{
    position: relative;
    width: 1450px;
    height: 4060px;
    margin: 0 auto;
}

.container_wrapper .keyword{
    color: #0eccee;
}

.container_wrapper .top{
    font-size: 13px;
    width: 928px;
    height: 18px;
    margin: 10px 0 20px 0;
}

.container_wrapper .sep20b{
    width: 928px;
    height: 20px;
}

.container_wrapper .lists{
    width: 928px;
    height: 3992px;
}

.mid-tag-bar{
    position: relative;
    width: 916px;
    height: 16px;
    padding: 10px 5px;
    border: 1px solid rgb(230, 230, 230);
}

.mid-tag-bar li{
    float: left;
    /* border-right: 1px solid rgb(230, 230, 230); */
}

.mid-tag-bar li:first-child{
    cursor: pointer;
}

.mid-tag-bar li:first-child:hover{
    color: #4DBEFD;
}

.mid-tag-bar li:last-child{
    border-right: none;
}

.mid-tag-bar li span{
    display: block;
    font-size: 14px;
    padding: 0 15px;
}

/* 课程状态下拉栏 */
.mid-tag-bar .state-dropdown .course-state{
    display: block;
    position: absolute;
    font-size: 14px;
    padding: 0 15px;
    width: 56px;
    line-height: 36px;
    left: 100px;
    top: 0;
    height: 36px;
}

.mid-tag-bar .state-dropdown .state-dropdown-content{
    display: none;
    z-index: 100;
    position: absolute;
    left: 100px;
    top: 36px;
    width: 86px;
    height: 116px;
    background-color: #fff;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.state-dropdown-content div{
    padding: 5px 15px;
    cursor: pointer;
    transition: all 0.1s;
}

.state-dropdown-content div:hover{
    background-color: #4DBEFD;
}

.state-dropdown-content div:hover a{
    color: #fff;
}

.state-dropdown-content div a{
    font-size: 14px;
}

.mid-tag-bar .state-dropdown:hover .course-state{
    z-index: 101;
    background-color: #fff;
    cursor: pointer;
    color: #4285F4;
}

.mid-tag-bar .state-dropdown:hover .state-dropdown-content{
    display: block;
}

.mid-tag-bar .line1{
    position: absolute;
    width: 1px;
    height: 18px;
    left: 186px;
    background-color: #E6E6E6;
}

/* 教师风格下拉栏 */
.mid-tag-bar .style-dropdown .teacher-style{
    display: block;
    position: absolute;
    font-size: 14px;
    width: 56px;
    padding: 0 15px;
    line-height: 36px;
    left: 187px;
    top: 0;
    height: 36px;
}

.mid-tag-bar .style-dropdown .style-dropdown-content{
    display: none;
    z-index: 100;
    position: absolute;
    left: 187px;
    top: 36px;
    width: 86px;
    height: 145px;
    background-color: #fff;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.style-dropdown-content div{
    padding: 5px 15px;
    cursor: pointer;
    transition: all 0.1s;
}

.style-dropdown-content div:hover{
    background-color: #4DBEFD;
}

.style-dropdown-content div:hover a{
    color: #fff;
}

.style-dropdown-content div a{
    font-size: 14px;
}

.mid-tag-bar .style-dropdown:hover .teacher-style{
    z-index: 101;
    background-color: #fff;
    cursor: pointer;
    color: #4285F4;
}

.mid-tag-bar .style-dropdown:hover .style-dropdown-content{
    display: block;
}

.mid-tag-bar .line2{
    position: absolute;
    width: 1px;
    height: 18px;
    left: 273px;
    background-color: #E6E6E6;
}

/* 精品课下拉栏 */
.tag-left{
    position: relative;
    margin-left: 300px;
}

.tag-left .tl-checkbox{
    -webkit-appearance:none;
    -moz-appearance:none;
    outline: none;
    width:15px;
    height:15px;
    background-color:#ffffff;
    border:solid 1px #dddddd;
    border-radius:50%;
    margin-top: 1px;
    padding:0;
    position:relative;
    display:inline-block;
    /*文字对齐方式*/
    vertical-align:top;
    transition:background-color ease 0.1s;
}

.tag-left .tl-checkbox:checked{
    background:#4285F4;
}

.tag-left .tl-checkbox:checked::after{
    content:'';
    top:3px;
    left:3px;
    position:absolute;
    border:#fff solid 2px;
    border-top:none;
    border-right:none;
    height:3px;
    width:6px;
    transform:rotate(-45deg);
}

.tag-left span{
    position: absolute;
    margin-left: 5px;
    top: -2px;
    font-size: 14px;
}

.courseCardListBox{
    margin-top: 30px;
    width: 928px;
    height: 3924px;
}

.courseCardListBox .wait_input{
    font-size: 14px;
}

.courseCardListBox .clist{
    position: relative;
    width: 928px;
    height: 150px;
    padding: 20px 0;
    border-bottom: 1px solid rgb(230, 230, 230);
    background-color: #fff;
}

.courseCardListBox .clist:hover{
    background-color: #F5F5F5;
}

.clist .imgWrapper img{
    position: absolute;
    left: 1px;
    width: 265px;
    height: 150px;
}

.clist .contents{
    width: 648px;
    height: 150px;
    margin-left: 280px;
}

.clist .contents .knowledgeName a{
    font-size: 25px;
}

.clist .contents .knowledgeName a:hover{
    color: #4285F4;
}

.contents-title .uniq{
    display: inline;
    padding: 3px 5px;
    margin-left: 15px;
    background-color: #CBA265;
    box-sizing: content-box;
    border: 1px solid #c58f43;
}

.contents-title .uniq span{
    font-size: 13px;
    color: #fff;
}

.clist .contents .contents-title{
    width: 648px;
    height: 20px;
    font-size: 16px;
    margin-top: 12px;
}

.clist .contents .contents-title a:hover{
    color: #4285F4;
}

.clist .contents .first-row{
    width: 648px;
    height: 15px;
    margin-top: 10px;
    font-size: 12px;
}

.clist .contents .first-row a{
    color: #999;
}

.clist .contents .first-row .univ{
    margin-right: 20px;
}

.clist .contents .first-row .univ:hover{
    color:#4285F4;
}

.clist .contents .first-row .auth-wrapper:last-child span{
    display: none;
}

.clist .contents .first-row .auth:hover{
    color: #4285F4;
}

.clist .contents .first-row .teachStyle{
    position: absolute;
    width: 200px;
    height: 20px;
    top: 61px;
    left: 430px;
}

.clist .contents .first-row .teachStyle i{
    font-size: 14px;
}

.clist .contents .brief-row{
    width: 638px;
    height: 44px;
    margin: 15px 10px 20px 0;
    color: #666;
    font-size: 13px;
}

.clist .clock-icon{
    position: absolute;
    top: 20px;
    right: 20px;
}

.clist .clock-icon span{
    font-size: 14px;
}

.clist .clock-icon i{
    font-weight: bold;
    font-size: 20px;
}

.courseCardListBox .notfound{
    margin-left: 250px;
    margin-top: 100px;
}

/* --------------------------热搜榜------------------------- */
.lists .hot-points{
    /* position: absolute;
    top: 126px;
    right: 120px; */
    position: fixed;
    top: 220px;
    /* right: 300px; */
    margin-left: 1050px;
    width: 350px;
    height: 450px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.lists .hot-points .title{
    width: 350px;
    height: 22px;
    margin-top: 4px;
    margin-bottom: 4px;
    margin-left: 6px;
    font-size: 14px;
    padding: 5px 1px 5px 10px;
}

.lists .hot-points li{
    position: relative;
    display: flex;
    width: 350px;
    height: 22px;
    padding: 5px 1px 5px 10px;
}

.lists .hot-points li:nth-child(2) .num{
    color: #fc8c45;
}
.lists .hot-points li:nth-child(3) .num{
    color: #ff6600;
}
.lists .hot-points li:nth-child(4) .num{
    color: #faa90e;
}

.lists .hot-points li .num{
    display: block; 
    width: 18px;
    height: 22px;
    margin-right: 8px;
    margin-top: 3px;
    margin-left: 4px;
    color: #ad95a3;
    font-size: 15px;
}

.lists .hot-points li a{
    color: #4285F4;
}

.lists .hot-points li a:hover{
    color: #4DBEFD;
    text-decoration: underline;
}

.lists .hot-points li .icon-huo{
    position: absolute;
    color: orange;
    left: 280px;
}

.lists .hot-points li .hotDegree{
    position: absolute;
    left: 300px;
}

</style>