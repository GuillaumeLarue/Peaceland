<template>
  <div>
    <button @click="addAlert">add alert</button>
    <Alert v-for="alert in alerts" :alert="alert" v-bind:key="alert"/>
  </div>
</template>

<script>
import Alert from './components/Alert.vue'

export default {
  name: 'App',
  components: {
    Alert
  },
  data() {
    return {
      alerts: [require('./assets/alert.json')],
      connection: null
    }
  },
  created() {
    console.log("Starting Connection to WebSocket Server")
    this.connection = new WebSocket('ws://localhost:9000')
    this.connection.onopen = function (event) {
      console.log(event)
      console.log('Successfully connected to the echo WebSocket Server')
    }
    this.connection.onmessage = function (event) {
      console.log(event)
    }
  },
  methods: {
    addAlert() {
      this.alerts.push(require('./assets/alert.json'))
    }
  }
}
</script>

<style>
html {
  background-color: rgba(5, 5, 5, 0.5)
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
