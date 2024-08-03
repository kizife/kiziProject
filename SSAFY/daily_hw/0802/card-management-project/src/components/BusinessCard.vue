{{!-- 개인적인 리뷰 ~
기능은 구현했지만,
헤매면서 덕지덕지 붙이고 try하느라
코드가 많이 더러워진 것 같다.
구조가 엉킨 느낌...?
그리고 디자인이 안 예쁘다
간격 엉킨 거 봐서는 구조를 잘못 짠 듯... --}}

<script setup>
import BusinessCardDetailVue from "./BusinessCardDetail.vue"
import { inject, ref, onMounted, onUnmounted, computed } from 'vue';

const businessCards = ref([
  {name: '일론 머스크', title: '테슬라 테크노킹'}
  , {name: '래리 엘리슨', title: '오라클 창업주'}
  , {name: '빌 게이츠', title: '마이크로소프트 공동창업주'}
  , {name: '래리 페이지', title: '구글 공동창업주'}
  , {name: '세르게이 브린', title: '구글 공동창업주'}
]);

const emitter = inject('emitter'); //Inject 'emitter'

const deleteCard = (card) => {
  console.log(card);
  // businessCards 배열에서 카드와 동일한 객체 찾아서 제거
  businessCards.value = businessCards.value.filter(b => b.name !== card.name || b.title !== card.title);
};

const deleteCardEvent = (card) => {
  deleteCard(card); 
};

const updateCard = (cardInfo) => {
  businessCards.value.push(cardInfo); // 명함 배열에 새 명함 추가
};


// 컴포넌트가 마운트될 때 이벤트를 처리하도록 설정
onMounted(() => {
  emitter.on('delete-card', deleteCardEvent);
  emitter.on('create-card', updateCard);
});

// 컴포넌트가 언마운트될 때 이벤트를 해제
onUnmounted(() => {
  emitter.off('delete-card', deleteCardEvent); 
  emitter.off('create-card', updateCard);
});

//배열 길이
const cardNumber = computed(() => businessCards.value.length);

// emits 정의
const emit = defineEmits(['update:cardNumber']);
emit('update:cardNumber', cardNumber.value);
</script>

<template>

    <p v-if="cardNumber > 0">
    현재 보유중인 명함 수 : {{ businessCards.length }}
    </p>
    <p v-else>
      명함이 없습니다. 새로운 명함을 추가해 주세요.
    </p>

    <div class="details">
    <BusinessCardDetailVue
    v-for="(card, index) in businessCards"
      :key="index"
      :name="card.name"
      :title="card.title"
      @update:cardNumber="emit('update:cardNumber', $event)"
      id="cards"
    />
  </div>
</template>

<style scoped>
.details {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  text-align: center;
}

.details > * {
  flex: 0 0 calc(50% - 20px); /* 한 행에 카드 두 개만 표시 */
  box-sizing: border-box;
  justify-content: center;
  text-align: center;
}

</style>
