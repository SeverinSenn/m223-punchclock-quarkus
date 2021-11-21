<template>
  <q-dialog v-model="opened" persistent>
    <q-card>
      <q-card-section>
        <div class="text-h6">{{ $t("createNewProjekt") }}</div>
      </q-card-section>

      <q-card-section>
        <div class="row q-gutter-sm q-pa-xs">
          <q-input v-model="email" outlined label="Email" />
          <q-checkbox v-model="is_Admin" outlined label="IsAdmin" />
        </div>
        <div class="row q-gutter-sm q-pa-xs">
          <q-input v-model="vorname" outlined label="Vorname" />
          <q-input v-model="nachname" outlined label="Nachname" />
        </div>
        <q-select
          outlined
          v-model="projects"
          :options="projectOptions"
          label="Projects"
          emit-value
          map-options
          use-chips
          multiple
          class="q-pa-xs"
        />

        <q-select
          outlined
          v-model="groups"
          :options="groupsOptions"
          label="Groups"
          emit-value
          map-options
          use-chips
          multiple
          class="q-pa-xs"
        />
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Cancel" color="primary" @click="close" />
        <q-btn
          flat
          :label="$t('Create')"
          color="primary"
          @click="createOrUpdateUser"
        />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { Notify } from "quasar";
export default {
  data() {
    return {
      opened: false,
      id: 0,
      email: "",
      vorname: "",
      nachname: "",
      is_Admin: false,

      projects: [],
      projectOptions: [],
      groups: [],
      groupsOptions: [],
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    open() {
      this.opened = true;
    },
    close() {
      this.opened = false;
    },
    editUser(user) {
      if (user !== undefined) {
        this.id = user.id;
        this.email = user.email;
        this.vorname = user.vorname;
        this.nachname = user.nachname;
        this.is_Admin = user.is_Admin;
        this.projects = user.projects.map((x) => x.value);
        this.groups = user.groups.map((x) => x.value);
      }
      this.open();
    },
    async createOrUpdateUser() {
      if (this.id !== 0) {
        let res = await axios.put("User", {
          id: this.id,
          email: this.email,
          vorname: this.vorname,
          nachname: this.nachname,
          is_Admin: this.is_Admin,
          projectids: this.projects,
          groupids: this.groups,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "User Updated",
          });
        }
      } else {
        let res = await axios.post("User", {
          email: this.email,
          vorname: this.vorname,
          nachname: this.nachname,
          is_Admin: this.is_Admin,
          projectids: this.projects,
          groupids: this.groups,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "User Created",
          });
        }
      }
      this.$emit("changed");
      this.clearData();
      this.close();
    },
    async getData() {
      let res = await axios.get("Project/options");
      if (res.status === 200) {
        this.projectOptions = res.data;
      }
      res = await axios.get("Group/options");
      if (res.status === 200) {
        this.groupsOptions = res.data;
      }
    },
    clearData() {
      this.id = 0;
      this.email = "";
      this.vorname = "";
      this.nachname = "";
      this.is_Admin = false;

      this.projects = [];
      this.groups = [];
    },
  },
};
</script>

<style></style>
