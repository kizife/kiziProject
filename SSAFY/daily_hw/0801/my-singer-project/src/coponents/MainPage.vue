<script setup>
const micro = "src/assets/microphone.jpg"
</script>

<template>
  <div id="title">
  <h1>가수 정보 페이지에 오신 것을 환영합니다.</h1>
  <p>여러분이 좋아하는 가수와 아티스트에 대한 정보를 탐색해보세요.</p>
  <img :src="micro" id="mic">
</div>
</template>

<style scoped>
#title{
  text-align: center;
}

#mic{
  width:200px;
}
</style>