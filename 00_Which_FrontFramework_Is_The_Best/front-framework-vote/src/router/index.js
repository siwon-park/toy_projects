import Vue from 'vue'
import VueRouter from 'vue-router'
import VoteFramework from '../views/VoteFramework.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'vote',
    component: VoteFramework
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
