<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title> Punchclock App </q-toolbar-title>

        <div>
          <q-btn flat label="logout" no-caps @click="logout"></q-btn>
        </div>
      </q-toolbar>
    </q-header>

    <q-drawer v-model="leftDrawerOpen" show-if-above bordered v-if="jwtToken">
      <q-list>
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import EssentialLink from "components/EssentialLink.vue";
import globalscript from "./../global-Script";
import { defineComponent, ref } from "vue";

export default defineComponent({
  name: "MainLayout",

  components: {
    EssentialLink,
  },
  data() {
    return {
      leftDrawerOpen: false,
      jwtToken: null,
    };
  },
  computed: {
    essentialLinks() {
      var result = [
        {
          title: "Zeit",
          caption: "Zeit Eintragen",
          icon: "manage_accounts",
          link: "/",
        },
      ];
      if (this.jwtToken.groups.includes("Admin")) {
        result.unshift({
          title: "Gruppen",
          caption: "Gruppen Verwaltung",
          icon: "groups",
          link: "/GroupManagement",
        });
        result.unshift({
          title: "Projekt",
          caption: "Projekt Verwaltung",
          icon: "location_city",
          link: "/ProjectManagement",
        });
        result.unshift({
          title: "Admin",
          caption: "Benutzer Verwaltung",
          icon: "manage_accounts",
          link: "/UserManagement",
        });
      }
      return result;
    },
  },

  mounted() {
    this.jwtToken = globalscript.GetJWTData();
  },
  methods: {
    toggleLeftDrawer() {
      this.leftDrawerOpen = !this.leftDrawerOpen;
    },
    logout() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/Login");
    },
  },
});
</script>
