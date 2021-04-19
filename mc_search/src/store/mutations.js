import {SET_RESULT_ARRAY, SET_SEARCH_CONTENT, SET_ALL_ARRAY, SET_SEARCH_fORM} from './mutation-types'

export default {
    [SET_RESULT_ARRAY] (state, {tempList}) {
        state.indexSearchResults = tempList
    },

    [SET_SEARCH_CONTENT] (state, {val}) {
        state.searchForm.indexSearchContent = val
    },

    [SET_ALL_ARRAY] (state, {hot_search}) {
        state.hotSearch = hot_search
        // console.log(state.hotSearch[0].knowledge_point)
    },

    [SET_SEARCH_fORM] (state, {searchContent}) {
        state.searchContent = searchContent
    }
}