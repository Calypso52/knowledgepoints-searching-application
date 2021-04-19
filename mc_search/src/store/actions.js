import axios from 'axios'
import {SET_RESULT_ARRAY, SET_SEARCH_CONTENT, SET_ALL_ARRAY, SET_SEARCH_fORM} from './mutation-types'

export default {
    searchClasses ({commit}, indexSearchContent) {
        // 与后台通信
        axios
            .post('/index', {
                searchContent: indexSearchContent
            })
            .then((successResponse) => {
                //console.log(successResponse.data[0])  // {course_id: 1, url: "", course_name: "数据库", term: null, platform: null, …}

                let tempList = successResponse.data
                commit(SET_RESULT_ARRAY, {tempList})

                // 错误提示！
                if (successResponse.data.length === 0) {
                    alert('【没有这样的结果！！！】')
                }
            })
            .catch((failResponse) => { })
    },

    setSearchContent ({commit}, val) {
        commit(SET_SEARCH_CONTENT, {val})
    },

    get_hot_knowledge ({commit}) {
        axios
            .get('/searchindex')
            .then(response => {
                // console.log(response.data[0]) 

                // 第一次处理数据，筛出只保留包含知识点名和热度的数组
                let tempList1 = []
                
                for (let i = 0; i < response.data.length; i++) {
                    const tempObj = {
                        knowledge_point: '',   //知识点
                        hot_knowledge: ''   //热度
                    }
                    tempObj.knowledge_point = response.data[i].knowledge_point
                    tempObj.hot_knowledge = response.data[i].hot_knowledge
                    tempList1.push(tempObj)
                }
                // console.log(tempList1)  //数组每一项对象只有 knowledge_point 和 hot_knowledge 两个元素

                // 第二次处理数据，将数据按照热度从高到低排列
                let hot_search = []  // 存前十名
                for (let i = 0; i < 10; i++) {
                    for (let j = 0; j < tempList1.length - 1; j++) {
                        if (tempList1[j].hot_knowledge > tempList1[j + 1].hot_knowledge) {
                            let temp = tempList1[j].hot_knowledge
                            tempList1[j].hot_knowledge = tempList1[j + 1].hot_knowledge
                            tempList1[j + 1].hot_knowledge = temp
                        }
                    }
                    const tempObj = {
                        knowledge_point: '',   //知识点
                        hot_knowledge: ''   //热度
                    }
                    tempObj.knowledge_point = tempList1[tempList1.length - 1 - i].knowledge_point
                    tempObj.hot_knowledge = tempList1[tempList1.length - 1 - i].hot_knowledge
                    hot_search.push(tempObj)
                }
                // console.log(hot_search)  //按照热度从高到低排列的十个知识点

                commit(SET_ALL_ARRAY, {hot_search})

                // 简陋的错误提示！
                if (successResponse.data.length === 0) {
                    alert('【没有这样的结果！！！】')
                }
            })
            .catch((failResponse) => { })
    },

    setSearchForm ({commit}, searchContent) {
        commit(SET_SEARCH_fORM, {searchContent})
    }
}