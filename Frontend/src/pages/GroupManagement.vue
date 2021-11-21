<template>
  <q-page>
    <EditGroupDialog
      ref="EditGroupDialog"
      @changed="$refs.GroupTable.getAllGroups()"
    />
    <GroupTable ref="GroupTable" @editGroup="editGroup" />
  </q-page>
</template>

<script>
import GroupTable from "components/Group/GroupTable.vue";
import EditGroupDialog from "components/Group/EditGroupDialog.vue";

export default {
  components: { GroupTable, EditGroupDialog },
  data() {
    return {};
  },
  computed: {},
  mounted() {},
  methods: {
    editGroup(val) {
      this.$refs.EditGroupDialog.editGroup(val);
    },
  },
};
</script>

<style></style>
