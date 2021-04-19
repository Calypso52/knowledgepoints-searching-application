/* 状态对象，存放多个组件共享的状态 */
export default {
    hotSearch: [],  // 数据库所有结果
    indexSearchResults: [],  // 符合搜索结果的数组
    searchContent: '',  //这个搜索内容是在点击提交搜索之后才会更新
    searchForm: {
        indexSearchContent: ''
    }
}