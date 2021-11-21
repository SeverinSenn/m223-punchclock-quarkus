<template>
  <q-page>
    <EditProjectDialog
      ref="EditProjectDialog"
      @changed="$refs.ProjectTable.getAllProjects()"
    />
    <ProjectTable ref="ProjectTable" @editProject="editProject" />
  </q-page>
</template>

<script>
import ProjectTable from "components/Project/ProjectTable.vue";
import EditProjectDialog from "components/Project/EditProjectDialog.vue";

export default {
  components: { ProjectTable, EditProjectDialog },
  data() {
    return {};
  },
  computed: {},
  mounted() {},
  methods: {
    editProject(val) {
      this.$refs.EditProjectDialog.editProject(val);
    },
  },
};
</script>

<style></style>
