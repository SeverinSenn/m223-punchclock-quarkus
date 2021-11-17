<template>
<q-page class="flex flex-center">
    <q-input filled v-model="checkin" label="Filled" />
</q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
    data() {
        return {
            checkin: "null"
        }
    },
    mounted() {
    }
}) 
</script>

<style>

</style>
