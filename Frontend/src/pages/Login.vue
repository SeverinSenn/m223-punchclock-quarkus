<template>
<q-page>
    <div class="LoginPage">
        <h1>Login</h1>
        <div style="max-width: 300px;" class="q-mx-auto">
            <form-wrapper :validator="$v">
                <form-group name="username">
                    <q-input v-model="username" label="Username" slot-scope="{attrs}" v-bind="attrs" /><br />
                </form-group>

                <form-group name="password">
                    <q-input v-model="password" type="password" label="Password" slot-scope="{attrs}" v-bind="attrs" />
                </form-group>
            </form-wrapper>
        </div>
        <q-btn flat  label="Submit" @click="submitLogin" />
    </div>

</q-page>
</template>

<script>
import { required } from 'vuelidate/lib/validators'
import { Notify } from 'quasar'

export default {
    name: 'Loginpage',
    data() {
        return {
            username: "",
            password: "",
        }
    },
    /*
    TODO: Fixen der Valdiation
    validations: {
        password: {
            required
        },
        username: {
            required
        }
    },*/
    methods: {
        async submitLogin() {
            this.$v.$touch()
            if (this.$v.$invalid) {
                return
            }
            console.log("asd")
            let res = await axios.post('/User/Login', {
                Username: this.username,
                Password: this.password
            })
            if (res.status === 200) {
                const token = res.data.token
                if (token == undefined) {
                    Notify.create({
                        position: 'top',
                        type: 'negative',
                        message: 'login failed'
                    })
                    this.password = ''
                    this.$v.$reset()
                    return;
                }
                $store.dispatch("auth/logout");
                $store.dispatch("auth/login", {
                    jwt: token
                });
                Notify.create({
                    position: 'top',
                    type: 'positive',
                    message: 'login sucssesd'
                })
                this.$router.push('/')
            }
        }
    }
}
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
