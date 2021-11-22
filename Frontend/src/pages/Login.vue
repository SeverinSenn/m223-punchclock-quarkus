<template>
  <q-page>
    <div class="LoginPage">
      <h1>Login</h1>
      <div style="max-width: 300px" class="q-mx-auto">
        <form-wrapper >
          <form-group name="email">
            <q-input
              v-model="email"
              label="email"
            /><br />
          </form-group>

          <form-group name="password">
            <q-input
              v-model="password"
              type="password"
              label="Password"
            />
          </form-group>
        </form-wrapper>
      </div>
      <q-btn flat label="Submit" @click="submitLogin" />
    </div>
  </q-page>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import { Notify } from "quasar";
import { useStore } from "vuex";

export default {
  name: "Loginpage",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async submitLogin() {
      const $store = useStore();
      this.$store.dispatch("auth/logout");
      axios.defaults.headers.common["authorization"] = null;
      let res = await axios.post("/auth/Login", {
        email: this.email,
        passwort: this.password,
      });
      if (res.status === 200) {
        const token = res.data.jwt;
        this.$store.dispatch("auth/logout");
        this.$store.dispatch("auth/login", {
          jwt: token,
        });
        Notify.create({
          position: "top",
          type: "positive",
          message: "login sucssesd",
        });
        if(res.data.hasPasswort === true){
          this.$router.push("/");
        }else{
          this.$router.push("/SetPasswort");
        }
        
      } else {
        Notify.create({
          position: "top",
          type: "negative",
          message: "login failed",
        });
        this.password = "";
        return;
      }
    },
  },
};
</script>

<style>
.LoginPage {
  text-align: center;
  position: fixed;
  top: 24%;
  right: 0%;
  width: 100%;
}
</style>
