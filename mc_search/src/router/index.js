import Vue from 'vue'
import Router from 'vue-router'
// 导入组件
import Index from '@/views/index/Index.vue'
import Searchindex from '@/views/searchIndex/Searchindex.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',  // 去掉#

  routes: [
    {
      path: '/searchindex',
      neme: 'Searchindex',
      component: Searchindex
    },
    {
      path: '/index',
      name: 'Index',
      component: Index
    },
    {
      path: '/',
      redirect: '/searchindex'
    }
  ]
})
