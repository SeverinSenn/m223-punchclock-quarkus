<template>
  <q-table
    title="Projekt"
    :rows="projects"
    :columns="columns"
    color="primary"
    row-key="name"
  >
    <template v-slot:top-right>
      <q-btn
        color="primary"
        label="Add Project"
        no-caps
        @click="$emit('editProject')"
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
            @click="$emit('editProject', props.row)"
          />
          <q-btn
            color="negative"
            text-color="white"
            label="Delete"
            @click="deleteProject(props.row.id)"
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
      projects: [],
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
          name: "groups",
          label: "groups",
          field: "groups",
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
    this.getAllProjects();
  },
  methods: {
    async deleteProject(id) {
      let res = await axios.delete("/Project/" + id);
      if (res.status === 204) {
        Notify.create({
          position: "top",
          type: "positive",
          message: "Project Deleted",
        });
        this.getAllProjects();
      }
    },
    async getAllProjects() {
      let res = await axios.get("/Project");
      if (res.status === 200) {
        this.projects = res.data;
      }
    },
  },
};
</script>

<style></style>
