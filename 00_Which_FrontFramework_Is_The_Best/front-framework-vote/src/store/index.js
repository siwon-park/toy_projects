import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    vote_state: [0, 0, 0]
  },
  getters: {
    vote_state: state => state.vote_state
  },
  mutations: {
    VOTE_ONE: (state, vote_num) => {
      state.vote_state[vote_num] += 1
      const new_vote_state = state.vote_state
      state.vote_state = [] // vote_state 자체를 변경시켜 변화를 감지하게 함
      state.vote_state = new_vote_state // vote_state에 기존에 있던 값을 넣음
    }
  },
  actions: {
  },
})
