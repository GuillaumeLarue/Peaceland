<template>
  <div>
    <button @click="addAlert">add alert</button>
    <HelloWorld v-for="alert in alerts " :alert="alert" v-bind:key="alert"/>
  </div>

</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  data() {
    return {
      alerts: [require('./assets/alert.json')]
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
