<script setup>

import { inject } from 'vue';

const props = defineProps({
  name: String,
  title: String
})

const emitter = inject('emitter'); 

const deleteCardFunc = () => {
  const businessCard = { name: props.name, title: props.title }; /* prop 데이터로 businessCard 객체 생성 */
  console.log(businessCard);
  emitter.emit('delete-card', businessCard); /* 'delete-card' 이벤트와 businessCard 객체 전달 */
};

</script>

<template>
  <div class="container">

    <div class="card bg-light text-dark">
      <div class="card-body">
        <h3 class="card-title" id="cardName">이름 : {{ name }}</h3>
        <p class="card-text" id="cardTitle">직함 : {{ title }}</p>
        <a href="#" class="btn btn-danger" @click="deleteCardFunc">명함 삭제</a>
      </div>
    </div>

  </div>
</template>

<style scoped>
.container {
  text-align: center;
  /* flex-wrap: wrap; */
  display: flex;
  align-items: center;
  width: 400px;
}

.card {
  /* display: flex; */
  margin: 20px;
  padding: 20px;
  width: 400px;
}

#cardName {
  font-weight: bold;
  font-size: 20px;
}

</style>