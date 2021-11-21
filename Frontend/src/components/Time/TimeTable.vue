<template>
  <q-table
    title="Time"
    :rows="times"
    :columns="columns"
    color="primary"
    row-key="name"
  >
    <template v-slot:body="props">
      <q-tr :props="props">
        <q-td>
          {{ props.row.project.name }}
        </q-td>
        <q-td>
          {{ props.row.checkIn }}
        </q-td>
        <q-td>
          {{ props.row.checkOut }}
        </q-td>
        <q-td>
          <q-btn
            color="white"
            text-color="black"
            label="Edit"
            class="q-mr-md"
            @click="$emit('editTime', props.row)"
          />
          <q-btn
            color="negative"
            text-color="white"
            label="Delete"
            @click="deleteTime(props.row.id)"
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
      times: [],
    };
  },
  computed: {
    columns() {
      return [
        {
          name: "project",
          label: "project",
          field: "project",
          align: "left",
          sortable: true,
        },
        {
          name: "checkIn",
          label: "checkIn",
          field: "checkIn",
          align: "left",
          sortable: false,
        },
        {
          name: "checkOut",
          label: "checkOut",
          field: "checkOut",
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
    this.getAlltimes();
  },
  methods: {
    async deleteTime(id) {
      let res = await axios.delete("/Time/" + id);
      if (res.status === 204) {
        Notify.create({
          position: "top",
          type: "positive",
          message: "Time Deleted",
        });
        this.getAlltimes();
      }
    },
    async getAlltimes() {
      let res = await axios.get("/Time");
      if (res.status === 200) {
        this.times = res.data;
      }
    },
  },
};
</script>

<style></style>
