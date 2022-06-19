<template>
<div>
  <div class="progress">
    <div id="vuebar" class="progress-bar" role="progressbar" :style="`width: ${Vue_vote_ret}%`" aria-valuemin="0" aria-valuemax="100"></div>
    <div id="reactbar" class="progress-bar" role="progressbar" :style="`width: ${React_vote_ret}%`" aria-valuemin="0" aria-valuemax="100"></div>
    <div id="angularbar" class="progress-bar" role="progressbar" :style="`width: ${Angular_vote_ret}%`" aria-valuemin="0" aria-valuemax="100"></div>
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