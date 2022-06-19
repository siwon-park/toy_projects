<template>
<div>
  <div @click="vote(vote_num)" class="card bg-dark" style="width: 18rem;">
    <div class="card-body">
      <h3 class="card-title text-white">{{framework_lst[vote_num]}}</h3>
      <div class="d-flex justify-content-center">
        <div class="img-border">
          <img alt="logo_image" :src="require(`@/assets/${logo_image}`)" width="100px" height="100px">
        </div>
      </div>
      <!-- <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p> -->
    </div>
  </div>
  <h6>Click Me!</h6>
</div>
</template>

<script>
import { mapMutations } from 'vuex'
export default {
  name:'CardItem',
  props: {
    logo_image: String,
    vote_num: Number
  },
  data() {
    return {
      framework_lst: ["Vue", "React", "Angular"]
    }
  },
  methods: {
    ...mapMutations(["VOTE_ONE",]),
    vote(num) {
      this.VOTE_ONE(num)
    }
  }


}
</script>

<style scoped>
.img-border {
  border-radius: 50%;
  border: 3px solid white;
  height: 150px;
  width: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
  /* background-color: white; */
}

.card {
  cursor: pointer;
  border-radius: 25px;
}
</style>