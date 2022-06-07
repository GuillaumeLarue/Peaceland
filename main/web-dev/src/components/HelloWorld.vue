<template>
  <div
      class="v-widget-template"
      :class="{ active, clickable }"
      @click="onClick"
  >
    <div class="main">
      <div class="titles">
        <div class="title">{{ date() }}</div>
        <div class="subtitle">Latitude : {{ this.alert.peacewatcherLat }}, Longitude : {{
            this.alert.peacewatcherLon
          }}
        </div>
      </div>

      <div v-if="handled" class="top-right" @click.stop="handled = !handled">
        ALERTE
      </div>

      <div v-else class="handled">
        OK
      </div>
    </div>

    <div v-if=showDetails :class="{control: 'control', showDetails}">
      <div>
        Le citoyen d'ID
        <span class="infos">{{ this.alert.citizenId }}</span>
        à un Peacescore de
        <span class="infos"> {{ this.alert.citizenPeacescore }}</span>
      </div>
      <div>
        Citoyen ID :
        {{ this.alert.citizenId }}
      </div>
      <div>
        Peacescore :
        {{ this.alert.citizenPeacescore }}
      </div>
    </div>
  </div>
  <br>
</template>

<script>
/* eslint-disable */
export default {
  name: 'HelloWorld',
  data() {
    return {
      active: false,
      showDetails: false,
      clickable: true,
      handled: true
    }
  },
  props: {
    alert: Object,
  },
  methods: {
    addPrice() {
      console.log('clic')
      let json = require('/Users/youennloie/WebstormProjects/untitled/package.json')
      console.log(json.name)
      console.log(json);
    },
    date() {
      const date = this.alert.datetime
      const [year, month, tail] = date.split('-')
      const [day, time] = tail.split('T')
      const [hour, minute, second] = time.split(':')

      const res = `Le ${day}/${month}/${year} à ${hour}:${minute}:${second.substr(0, 2)}`
      console.log("[res]=", res, '(line: 48)\n')

      return res
    },
    onClick() {
      this.showDetails = !this.showDetails
      return this.$emit('Click')
    }
  }
}
</script>


<style scoped>
.v-widget-template {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: stretch;

  background-color: #333333;
  border-radius: 0.5em;
  overflow: hidden;
  position: relative;
  z-index: 1;
  color: aliceblue;
  cursor: pointer;
}

.v-widget-template.active {
  opacity: 0.5;
  background-color: red;
}

.v-widget-template > .background-image {
  --background-image: "";

  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;

  background-size: 100%;
  border-radius: 0.5em;
  opacity: 0.5;
  z-index: 2;
}

.icon:only-child {
  flex: 1;
}

.main {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;

  z-index: 3;
}

.main > *:only-child {
  flex: 1;
}

.titles:only-child {
  text-align: center;
}

.titles {
  flex: 1;

  overflow: hidden;
  padding: 1rem;
  text-align: left;
  white-space: nowrap;
}

.title {
  overflow: hidden;
  text-overflow: ellipsis;
}

.icon + .titles {
  padding-left: 0;
}

.subtitle {
  font-size: 0.8em;
  overflow: hidden;
  text-overflow: ellipsis;
}

.control {
  padding: 0.5em;
  z-index: 3;
  background-color: rgba(128, 122, 122, 0.44);
}

.control:empty {
  display: none;
}

.top-right {
  padding: 1em;
  color: #e8604c;
}

.handled {
  padding: 1em;
  color: #77b048;
}

.infos {
  color: #77b048;
}
</style>
