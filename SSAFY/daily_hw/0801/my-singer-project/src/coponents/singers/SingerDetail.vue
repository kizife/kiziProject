<script setup>
import SingerAlbum from './SingerAlbum.vue';

const singer = "src/assets/singer.jpg"
</script>

<template>
  <!-- <div class="card"> -->
    <div class="card" id="box">
      <h1 id="title3">가수 이름</h1>
      <img :src="singer" id="singer">
      <p>가수 소개글</p>
      <SingerAlbum></SingerAlbum>
    </div>
    <!-- <div class="card" id="box">
      <h1>가수 이름</h1>
      <img :src="singer" id="singer">
      <p>가수 소개글</p>
      <SingerAlbum></SingerAlbum>
    </div> -->
  <!-- </div> -->
</template>

<style scoped>

.card {
  display: inline-block;
  width: 40%;
  margin: 20px;
  padding: 30px;
  text-align: center;
  max-width: 500px;
  border: 1px solid #ff1361;
  background: #ffffff;
  color: #ff1361
}

#singer {
  width: 200px;
  border-radius: 50%;
}

</style>