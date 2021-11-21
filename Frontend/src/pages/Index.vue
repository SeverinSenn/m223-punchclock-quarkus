<template>
  <q-page>
    <div class="row">
      <q-select
        outlined
        v-model="project"
        :options="projectOptions"
        label="Project"
        emit-value
        map-options
        class="col-3"
      />
      <DateTimePicker ref="updatecheckIn" @cahnged="updatecheckIn" />
      <DateTimePicker ref="updatecheckOut" @cahnged="updatecheckOut" />
      <q-btn
        color="white"
        text-color="black"
        label="Update or Create"
        @click="createOrUpdateTime"
      />
    </div>

    <TimeTable ref="editTimeTable" @editTime="editTime" />
  </q-page>
</template>

<script>
import TimeTable from "components/Time/TimeTable.vue";
import DateTimePicker from "components/shared/DateTimePicker.vue";
import { Notify } from "quasar";

export default {
  components: { TimeTable, DateTimePicker },
  data() {
    return {
      id: 0,
      checkIn: null,
      checkOut: null,

      project: null,
      projectOptions: [],
    };
  },
  mounted() {
    this.getProjects();
  },
  methods: {
    async createOrUpdateTime() {
      if (this.id !== 0) {
        let res = await axios.put("Time", {
          id: this.id,
          checkIn: this.checkIn,
          checkOut: this.checkOut,
          projectId: this.project,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Time Updated",
          });
        }
      } else {
        let res = await axios.post("Time", {
          checkIn: new Date(this.checkIn).toISOString(),
          checkOut: new Date(this.checkOut).toISOString(),
          projectId: this.project,
        });
        if (res.status === 204) {
          Notify.create({
            position: "top",
            type: "positive",
            message: "Time Created",
          });
        }
      }
      this.clearData();
      this.$refs.editTimeTable.getAlltimes();
    },
    clearData() {
      this.checkIn = null;
      this.checkOut = null;
      this.$refs.updatecheckIn.clear();
      this.$refs.updatecheckOut.clear();

      this.project = null;
    },
    updatecheckIn(val) {
      this.checkIn = val;
    },
    updatecheckOut(val) {
      this.checkOut = val;
    },
    async getProjects() {
      let res = await axios.get("/Project/users");
      if (res.status == 200) {
        this.projectOptions = res.data;
      }
    },
    editTime(time) {
      this.id = time.id;
      this.project = time.project.id + "";

      this.checkIn = new Date(time.checkIn);
      this.checkOut = new Date(time.checkOut);
      this.$refs.updatecheckIn.setDate(new Date(time.checkIn));
      this.$refs.updatecheckOut.setDate(new Date(time.checkOut));
    },
  },
};
</script>

<style></style>
