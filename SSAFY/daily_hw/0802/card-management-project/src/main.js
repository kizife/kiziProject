import { createApp } from 'vue'
import App from './App.vue'
import mitt from 'mitt'

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";


const app = createApp(App)
const emitter = mitt();
app.provide('emitter', emitter);

app.mount('#app')