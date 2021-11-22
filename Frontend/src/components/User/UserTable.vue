<template>
  <q-table
    title="Users"
    :rows="users"
    :columns="columns"
    color="primary"
    row-key="name"
  >
    <template v-slot:top-right>
      <q-btn
        color="primary"
        label="Add User"
        no-caps
        @click="$emit('editUser')"
      />
    </template>
    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td>
          {{ props.row.email }}
        </q-td>
        <q-td>
          {{ props.row.vorname }}
        </q-td>
        <q-td>
          {{ props.row.nachname }}
        </q-td>
        <q-td>
          <q-checkbox v-model="props.row.is_Admin" disable />
        </q-td>
         <q-td>
          <q-checkbox v-model="props.row.hasPasswort" disable />
        </q-td>
        <q-td>
          <q-chip
            color="primary"
            text-color="white"
            v-for="(item, index) in props.row.projects.map((x) => x.label)"
            :key="index"
            >{{ item }}</q-chip
          >
        </q-td>
        <q-td>
          <q-chip
            color="primary"
            text-color="white"
            v-for="(item, index) in props.row.groups.map((x) => x.label)"
            :key="index"
            >{{ item }}</q-chip
          >
        </q-td>
        <q-td>
          <q-btn
            color="white"
            text-color="black"
            label="Edit"
            class="q-mr-md"
            @click="$emit('editUser', props.row)"
          />
          <q-btn
            color="negative"
            text-color="white"
            label="Delete"
            @click="deleteUser(props.row.id)"
          />
        </q-td>
      </q-tr>
    </template>
  </q-table>
</template>

<script>
import { Notify } from "quasar";

export default {
  data() {
    return {
      users: [],
    };
  },
  computed: {
    columns() {
      return [
        { name: "email", label: "Email", field: "email", sortable: true },
        { name: "vorname", label: "Vorname", field: "vorname", sortable: true },
        {
          name: "nachname",
          label: "Nachname",
          field: "nachname",
          sortable: true,
        },
        { name: "is_Admin", label: "Admin", field: "is_Admin", sortable: true },
        { name: "hasPasswort", label: "hasPasswort", field: "hasPasswort", sortable: true },
        {
          name: "projects",
          label: "projects",
          field: "projects",
          sortable: true,
          align: "center",
        },
        {
          name: "groups",
          label: "groups",
          field: "groups",
          sortable: true,
          align: "center",
        },
        {
          name: "actions",
          label: "actions",
          field: "actions",
          sortable: false,
          align: "left",
        },
      ];
    },
  },
  mounted() {
    this.getAllUsers();
  },
  methods: {
    async deleteUser(id) {
      let res = await axios.delete("/User/" + id);
      if (res.status === 204) {
        Notify.create({
          position: "top",
          type: "positive",
          message: "User Deleted",
        });
        this.getAllUsers();
      }
    },
    async getAllUsers() {
      let res = await axios.get("/User");
      if (res.status === 200) {
        this.users = res.data;
      }
    },
  },
};
</script>

<style></style>
