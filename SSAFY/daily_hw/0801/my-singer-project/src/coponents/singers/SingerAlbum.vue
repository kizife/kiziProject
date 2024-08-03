<script setup>

</script>

<template>
  <div class="card" id="box">
  <h3 id="albumTitle">앨범 제목</h3>
  <p>노래 목록1</p>
  <p>노래 목록2</p>
  <p>노래 목록3</p>
</div>
<div class="card" id="box">
  <h3 id="albumTitle">앨범 제목</h3>
  <p>노래 목록1</p>
  <p>노래 목록2</p>
  <p>노래 목록3</p>
</div>
</template>

<style scoped>
.card {
  display: inline-block;
  max-width: 100px;
  margin: 20px;
  padding: 20px 40px 20px 40px;
  text-align: center;
  border: 1px solid #ffffff;
  border-radius: 50%;
  background: #000000;
}

#albumTitle {
  color: #ffffff !important;
}

#box {
  color: #44ff53;
  font-weight: bold;
}


</style>