<template>
  <q-dialog v-model="opened" persistent>
    <q-card>
      <q-card-section>
        <div class="text-h6">{{ $t("createNewProjekt") }}</div>
      </q-card-section>

      <q-card-section>
        <q-input v-model="name" outlined label="Name" class="q-pa-xs" />

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
          v-model="users"
          :options="usersOptions"
          label="Users"
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
          label="Create or Update"
          color="primary"
          @click="createOrUpdateGroup"
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
      name: "",

      projects: [],
      projectOptions: [],
      users: [],
      usersOptions: [],
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
    editGroup(group) {
      if (group !== undefined) {
        this.id = group.id;
        this.name = group.name;
        this.projects = group.projects.map((x) => x.value);
        this.users = group.users.map((x) => x.value);
      }
      this.open();
    },
    async createOrUpdateGroup() {
      if (this.id !== 0) {
        let res = await axios.put("Group", {
          id: this.id,
          name: this.name,
          userids: this.users,
          projectids: this.projects,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Group Updated",
          });
        }
      } else {
        let res = await axios.post("Group", {
          name: this.name,
          userids: this.users,
          projectids: this.projects,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Group Created",
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
      res = await axios.get("User/options");
      if (res.status === 200) {
        this.usersOptions = res.data;
      }
    },
    clearData() {
      this.id = 0;
      this.name = "";

      this.projects = [];
      this.groups = [];
    },
  },
};
</script>

<style></style>
