<template>
  <q-table
    title="Gruppen"
    :rows="Groups"
    :columns="columns"
    color="primary"
    row-key="name"
  >
    <template v-slot:top-right>
      <q-btn
        color="primary"
        label="Add Group"
        no-caps
        @click="$emit('editGroup')"
      />
    </template>
    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td>
          {{ props.row.name }}
        </q-td>
        <q-td>
          <q-chip
            color="primary"
            text-color="white"
            v-for="(item, index) in props.row.users.map((x) => x.label)"
            :key="index"
            >{{ item }}</q-chip
          >
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
          <q-btn
            color="white"
            text-color="black"
            label="Edit"
            class="q-mr-md"
            @click="$emit('editGroup', props.row)"
          />
          <q-btn
            color="negative"
            text-color="white"
            label="Delete"
            @click="deleteGroup(props.row.id)"
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
      Groups: [],
    };
  },
  computed: {
    columns() {
      return [
        {
          name: "name",
          label: "Nale",
          field: "name",
          align: "left",
          sortable: true,
        },
        {
          name: "users",
          label: "users",
          field: "users",
          align: "left",
          sortable: false,
        },
        {
          name: "projects",
          label: "projects",
          field: "projects",
          align: "left",
          sortable: false,
        },
        {
          name: "actions",
          label: "actions",
          field: "actions",
          sortable: false,
        },
      ];
    },
  },
  mounted() {
    this.getAllGroups();
  },
  methods: {
    async deleteGroup(id) {
      let res = await axios.delete("/Group/" + id);
      if (res.status === 204) {
        Notify.create({
          position: "top",
          type: "positive",
          message: "Group Deleted",
        });
        this.getAllGroups();
      }
    },
    async getAllGroups() {
      let res = await axios.get("/Group");
      if (res.status === 200) {
        this.Groups = res.data;
      }
    },
  },
};
</script>

<style></style>
