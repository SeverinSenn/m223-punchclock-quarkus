<template>
<q-layout view="lHh Lpr lFf">
    <q-header elevated>
        <q-toolbar>
            <q-btn flat dense round icon="menu" aria-label="Menu" @click="toggleLeftDrawer" />

            <q-toolbar-title>
                Quasar App
            </q-toolbar-title>

            <div>Quasar v{{ $q.version }}</div>
        </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered>
        <q-list>
            <q-item-label header>
                Essential Links
            </q-item-label>

            <EssentialLink v-for="link in essentialLinks" :key="link.title" v-bind="link" />
        </q-list>
    </q-drawer>

    <q-page-container>
        <router-view />
    </q-page-container>
</q-layout>
</template>

<script>
import EssentialLink from 'components/EssentialLink.vue'
import globalscript from './../global-Script'
import {
    defineComponent,
    ref
} from 'vue'

export default defineComponent({
    name: 'MainLayout',

    components: {
        EssentialLink
    },
    computed: {
        essentialLinks() {
            return [{
                title: 'Docs',
                caption: 'quasar.dev',
                icon: 'school',
                link: 'https://quasar.dev'
            }, ]
        }
    },
    setup() {
        const leftDrawerOpen = ref(false)

        return {
            leftDrawerOpen,
            toggleLeftDrawer() {
                leftDrawerOpen.value = !leftDrawerOpen.value
            }
        }
    },
    mounted(){
      globalscript.GetJWTData();
    }
})
</script>
