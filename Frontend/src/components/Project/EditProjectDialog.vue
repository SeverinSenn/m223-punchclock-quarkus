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
          v-model="group"
          :options="groupOptions"
          label="Group"
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
          @click="createOrUpdateProject"
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

      group: [],
      groupOptions: [],
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
      this.clearData();
      this.opened = false;
    },
    editProject(Project) {
      if (Project !== undefined) {
        this.id = Project.id;
        this.name = Project.name;
        this.group = Project.groups.map((x) => x.value);
        this.users = Project.users.map((x) => x.value);
      }
      this.open();
    },
    async createOrUpdateProject() {
      if (this.id !== 0) {
        let res = await axios.put("Project", {
          id: this.id,
          name: this.name,
          userids: this.users,
          groupids: this.group,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Project Updated",
          });
        }
      } else {
        let res = await axios.post("Project", {
          name: this.name,
          userids: this.users,
          groupids: this.group,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Project Created",
          });
        }
      }
      this.$emit("changed");
      this.clearData();
      this.close();
    },
    async getData() {
      let res = await axios.get("Group/options");
      if (res.status === 200) {
        this.groupOptions = res.data;
      }
      res = await axios.get("User/options");
      if (res.status === 200) {
        this.usersOptions = res.data;
      }
    },
    clearData() {
      this.id = 0;
      this.name = "";

      this.users = [];
      this.group = [];
    },
  },
};
</script>

<style></style>
