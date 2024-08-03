<script setup>
import { ref, inject } from 'vue';

const emitter = inject('emitter'); 

const name = ref('');
const title = ref(''); 

const createCardFunc = () => {
  const cardInfo = { // 입력을 cardInfo 객체에 담기
    name: name.value,
    title: title.value
};

emitter.emit('create-card', cardInfo); 

name.value = '';
title.value = '';
};

const handleSubmit = (event) => {
  event.preventDefault(); //form의 기본 이벤트 동작 막기
  createCardFunc(); //createCardFunc 호출
};
</script>

<template>
    <form class="input" @submit="handleSubmit">
        
        <label for="name">이름</label>
        <input type="text" id="name" v-model="name" />
        <label for="title">직함</label>
        <input type="text" id="title" v-model="title" />
        
        <button type="submit">명함 추가</button>
    </form>
</template>

<style scoped>
.input {
    margin: 20px;
}

</style>