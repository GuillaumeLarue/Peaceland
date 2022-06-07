<template>
  <div
      class="v-widget-template"
      :class="{ active }"
      @click="onClick"
      @mousedown="active = true"
      @mouseup="active = false"
      @mousemove="active = false"
      @touchstart="active = true"
      @touchend="active = false"
      @touchmove="active = false"
  >
    <div class="main">
      <div class="titles" v-if="title">
        <div class="title">{{ title }}</div>
        <div v-if="subtitle" class="subtitle">{{ subtitle }}</div>
      </div>

      <div @mousedown.stop @touchstart.stop v-if="$slots['top-right']">
        <slot name="top-right"/>
      </div>
    </div>

    <div class="control">
      <slot name="control"/>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: 'AlertTemplate',
  data() {
    return {
      active: false
    }
  },
  props: {
    subtitle: String,
    title: String,
  },
  methods: {
    addPrice() {
      console.log('clic')
      var json = require('/Users/youennloie/WebstormProjects/untitled/package.json')
      console.log(json.name)
      console.log(json);
    },
    onClick() {
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
}

.clickable.active {
  opacity: 0.5;
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
  justify-content: flex-start;
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
  z-index: 3;
}

.control:empty {
  display: none;
}
</style>
