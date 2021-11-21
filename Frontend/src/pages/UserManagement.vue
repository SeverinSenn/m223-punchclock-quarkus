<template>
  <q-page>
    <EditUserDialog
      ref="EditUserDialog"
      @changed="$refs.UserTable.getAllUsers()"
    />
    <UserTable ref="UserTable" @editUser="editUser" />
  </q-page>
</template>

<script>
import UserTable from "components/User/UserTable.vue";
import EditUserDialog from "components/User/EditUserDialog.vue";

export default {
  components: { UserTable, EditUserDialog },
  data() {
    return {};
  },
  computed: {},
  mounted() {},
  methods: {
    editUser(val) {
      this.$refs.EditUserDialog.editUser(val);
    },
  },
};
</script>

<style></style>
