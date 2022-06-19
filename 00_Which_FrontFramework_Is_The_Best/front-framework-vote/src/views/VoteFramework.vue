<template>
  <div class="container">
    <div class="row">
      <div class="col col-sm-12 col-md-12 col-lg-4">
        <div class="d-flex justify-content-center align-items-center">
          <CardItem :logo_image="images_path[0]" :vote_num="vote_num[0]"></CardItem>
        </div>
      </div>
      <div class="col col-sm-12 col-md-12 col-lg-4">
        <div class="d-flex justify-content-center align-items-center">
          <CardItem :logo_image="images_path[1]" :vote_num="vote_num[1]"></CardItem>
        </div>
      </div>
      <div class="col col-sm-12 col-md-12 col-lg-4">
        <div class="d-flex justify-content-center align-items-center">
          <CardItem :logo_image="images_path[2]" :vote_num="vote_num[2]"></CardItem>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="my-5">
        <VoteProgress></VoteProgress>
      </div>
    </div>
  </div>

</template>

<script>
import CardItem from '@/components/CardItem.vue'
import VoteProgress from '@/components/VoteProgress.vue'

export default {
  name: 'HomeView',
  components: {
    CardItem,
    VoteProgress,
  },
  data() {
    return {
      images_path: ["Vue_logo.png", "React_logo.png", "Angular_logo.png"],
      vote_num: [0, 1, 2],
    }
  },
}
</script>

<style scoped>

</style>