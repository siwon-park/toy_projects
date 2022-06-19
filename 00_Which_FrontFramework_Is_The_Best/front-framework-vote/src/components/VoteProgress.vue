<template>
<div>
  <div class="card bg-light" style="height:150px">
    <div class="my-3">
      <h3>{{BestFramework}}</h3>
    </div>
    <div class="container px-4">
      <div class="progress">
        <div id="vuebar" class="progress-bar fw-bold" role="progressbar" :style="`width: ${Vue_vote_ret}%`" aria-valuemin="0" aria-valuemax="100">{{Vue_vote_ret}}%</div>
        <div id="reactbar" class="progress-bar fw-bold" role="progressbar" :style="`width: ${React_vote_ret}%`" aria-valuemin="0" aria-valuemax="100">{{React_vote_ret}}%</div>
        <div id="angularbar" class="progress-bar fw-bold" role="progressbar" :style="`width: ${Angular_vote_ret}%`" aria-valuemin="0" aria-valuemax="100">{{Angular_vote_ret}}%</div>
      </div>
    </div>
  </div>

</div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'VoteProgress',
  computed: {
    ...mapGetters(['vote_state',]),
    vote_result() {
      return this.vote_state
    },
    Vue_vote_ret() {
      const ret = this.vote_state[0] / (this.vote_state[0] + this.vote_state[1] + this.vote_state[2]) * 100
      if (isNaN(ret)) {
        return 0
      }
      return ret.toFixed(2)
    },
    React_vote_ret() {
      const ret = this.vote_state[1] / (this.vote_state[0] + this.vote_state[1] + this.vote_state[2]) * 100
      if (isNaN(ret)) {
        return 0
      }
      return ret.toFixed(2)
    },
    Angular_vote_ret() {
      const ret = this.vote_state[2] / (this.vote_state[0] + this.vote_state[1] + this.vote_state[2]) * 100
      if (isNaN(ret)) {
        return 0
      }
      return ret.toFixed(2)
    },
    BestFramework() {
      const V = this.Vue_vote_ret
      const R = this.React_vote_ret
      const A = this.Angular_vote_ret
      if (V === 0 && R === 0 && A === 0) {
        return "Nothing Has Been Voted Yet!"
      } else if ( V === R && R === A && V === A) {
        return "Each One Has Highest Popularity!"
      } else {
        if ( V > R && V > A) {
          return "Vue Is Best Front-End Framework Now!"
        } else if ( R > V && R > A) {
          return "React Is Best Front-End Framework Now!"
        } else if ( A > V && A > R) {
          return "Angular Is Best Front-End Framework Now!"
        } else {
          return "Everything Is In Competition Now!"
        }
      }
    }
  },
}
</script>

<style scoped>
#vuebar {
  background-color: #42b983;
}

#reactbar {
  background-color: #61dafb;
}

#angularbar {
  background-color: #dd1b16;
}

.progress {
  height: 25px;
}
</style>