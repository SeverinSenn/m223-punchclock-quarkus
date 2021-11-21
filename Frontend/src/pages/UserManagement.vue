<template>
  <q-page>
     {{users}}
</q-page>
</template>

<script>
export default {
    data(){
        return{
            users:[]
        }
    },
    mounted(){
        this.getAllUsers();
    },
    methods:{
        async getAllUsers(){
            let res = await axios.get("/User")
            if(res.status === 200){
                this.users = res.data;
            }
        }
    }
}
</script>

<style>

</style>