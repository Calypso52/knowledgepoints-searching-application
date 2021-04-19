<template>
    <div class="wrapper">
        <div class="search clearfix" id="app">
            <input type="text" @keydown="switchToIndex" />
            <button><i class="iconfont icon-fangdajing"></i>&nbsp;搜知识点</button>
        </div>
        <!-- 热搜榜（知识点热搜） -->
        <ul class="hot-points clearfix">
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
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: 'Searchindex',

  computed: {
    ...mapState(['hotSearch'])
  },

  methods: {
    // 转换到搜索结果页面
    switchToIndex () {
      this.$router.replace({ path: '/index' })
    },
    // 获取热搜榜
    get_hot_knowledge () {
      this.$store.dispatch('get_hot_knowledge')
    }
  },

  mounted () {
    this.get_hot_knowledge()
  }
}
</script>

<style scoped>
body {
  /* 设置弹性容器 */
  display: flex;
  justify-content: center;
  align-items: center;
  /* 让页面高度占浏览器可视区域高度 */
  height: 100vh;
  width: 100vw;
}

/* 设置搜索栏位置样式 */
.wrapper {
  position: relative;
  /* height: 314px;
  width: 1000px;
  background-color: #bfc; */
  top: 20vh;
}

/* 设置图片容器样式 */
.wrapper .images {
  position: absolute;
  left: calc(50% - 135px);
}

/* 设置图片大小样式 */
.images img {
  width: 270px;
  height: 129px;
}

/* 设置搜索输入框容器样式 */
.wrapper .search {
  position: absolute;
  left: calc(50% - 300px);
  top: 140px;
}

/* 设置input样式 */
.search input {
  position: absolute;
  width: 480px;
  height: 16px;
  padding: 12px 48px 12px 16px;
  border: 2px solid #c4c7ce;
  border-radius: 10px 0 0 10px;
  /* 设置文字开始空格长度 */
  text-indent: 3px;
  font-size: 16px;
  /* 取消input获得焦点时的边框 */
  outline: none;
}

/* 设置input获得焦点时样式 */
.search input:hover {
  border-color: #a7aab5;
}

/* 设置input被点击时的样式 */
.search input:focus {
  border-color: #4e6ef2;
}

/* 设置搜索按钮样式 */
.search button {
  position: absolute;
  left: 478px;
  width: 108px;
  height: 44px;
  background-color: #4e6ef2;
  color: #fff;
  font-size: 18px;
  border: none;
  border-radius: 0 10px 10px 0;
  cursor: pointer;
  /* 取消input获得焦点时的边框 */
  outline: none;
}

/* 设置搜索按钮选中时的样式 */
.search button:hover {
  background-color: #4662d9;
}

/* 热搜榜样式 */
.hot-points{
    position: absolute;
    top: 230px;
    left: calc(50% - 295px);
    width: 580px;
    height: 365px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}

.hot-points .title{
    width: 580px;
    height: 22px;
    margin-bottom: 4px;
    font-size: 14px;
    padding: 5px 1px 5px 10px;
}

.hot-points li{
    position: relative;
    display: flex;
    width: 580px;
    height: 22px;
    padding: 5px 1px 5px 10px;
}

.hot-points li:nth-child(2) .num{
    color: #fc8c45;
}
.hot-points li:nth-child(3) .num{
    color: #ff6600;
}
.hot-points li:nth-child(4) .num{
    color: #faa90e;
}

.hot-points li .num{
    display: block; 
    width: 18px;
    height: 22px;
    margin-right: 8px;
    color: #ad95a3;
    font-size: 15px;
}

.hot-points li a{
    color: #4285F4;
}

.hot-points li a:hover{
    color: #4DBEFD;
    text-decoration: underline;
}

.hot-points li .icon-huo{
    position: absolute;
    color: orange;
    left: 510px;
}

.hot-points li .hotDegree{
    position: absolute;
    left: 530px;
}
</style>
