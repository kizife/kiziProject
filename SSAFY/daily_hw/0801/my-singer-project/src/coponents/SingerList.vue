<script setup>

import { ref } from 'vue';
import SingerDetail from './singers/SingerDetail.vue';
let id = ref
const singers = ref([
  { id: id++, name: 'adele' }
  , { id: id++, name: 'maroon 5' }
  , { id: id++, name: 'Dua Lipa' }
]);

</script>

<template>
  <div>
    <h1  id="title2">가수 목록</h1>
  </div>
  <div id="allCards">
  <SingerDetail id="singerDetail" v-for="singer in singers" :key="singer.id"></SingerDetail>
</div>
</template>

<style scoped>
#title2 {
  color: orangered;
  margin-top: 80px;
  text-align: center;
}

#allCards {
  display: flex;
  justify-content: center;
}

#singerDetail {
  margin-top: 50px;
  text-align: center;
}
</style>