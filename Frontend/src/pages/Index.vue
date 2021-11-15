<template>
  <q-page class="flex flex-center">
    <q-input filled v-model="checkin" label="Filled" mask="YYYY-MM-DD HH:mm" />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'PageIndex',
  data () {
    return {
      checkin: null
    }
  }
})
</script>
