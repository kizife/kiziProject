<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import BusinessCard from './components/BusinessCard.vue';
import CreateCardForm from './components/CreateCardForm.vue';

const cardNumber = ref(0);

const updateCardNumber = (count) => {
  cardNumber.value = count;
};

const handleCreateCard = (cardInfo) => {
  console.log('New card info:', cardInfo);
};

onMounted(() => {
  const emitter = inject('emitter');
  emitter.on('create-card', handleCreateCard); // 'create-card' 이벤트 수신
});

onUnmounted(() => {
  const emitter = inject('emitter');
  emitter.off('create-card', handleCreateCard); // 'create-card' 이벤트 해제
});


</script>

<template>
  <div class="container">
    <div class="header">
      <h1>명함 관리 페이지</h1>
    </div>
  
    <div class="main">
      <p>명함을 관리하는 페이지입니다. 여기에 명함 목록이 표시됩니다.</p>
    </div>
  
    <div class="article">
      <h2 id="list">보유 명함 목록</h2>

      <CreateCardForm />
      <BusinessCard @update:cardNumber="updateCardNumber" />
    </div>
  
    <div class="footer">
      <p>&#169; 2023 My Buisness Cards</p>
    </div>
  </div>  
  </template>

<style scoped>
.container {
  text-align: center;
  margin-top: 10px;
}

.header {
  padding: 20px;
  background:rgb(58, 113, 231) ;
  width: 400px;
  color: white;
  margin: 0 auto;
}

.main {
  margin-top: 30px;
}

.article {
  margin-top: 80px;
}

#list {
  font-size: 25px;
  font-weight: bold;
  text-align: center;
}

</style>