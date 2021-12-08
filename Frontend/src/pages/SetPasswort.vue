<template>
<q-page>
    <div class="LoginPage">
        <h1>Set Passwort</h1>
        <div style="max-width: 300px" class="q-mx-auto">
            <q-input v-model="password" type="Password" label="Password" />

            <q-input v-model="reppassword" type="Repeat Password" label="Repeat Password" />
        </div>
        <q-btn flat label="Submit" @click="submitLogin" />
    </div>
</q-page>
</template>

<script>
import {
    required
} from "vuelidate/lib/validators";
import {Notify} from "quasar";
import {
    useStore
} from "vuex";

export default {
    name: "Loginpage",
    data() {
        return {
            password: "",
            reppassword: "",
        };
    },
    methods: {
        async submitLogin() {
            if (this.password != this.reppassword) {
                Notify.create({
                    position: "top",
                    type: "negative",
                });
            }

            let res = await axios.post("/auth/setpasswort", {
                passwort: this.password,
            });
            if (res.status === 204) {
                Notify.create({
                    position: "top",
                    type: "positive",
                    message: "Passwort set sucssesd",
                });
                this.$router.push("/");

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
