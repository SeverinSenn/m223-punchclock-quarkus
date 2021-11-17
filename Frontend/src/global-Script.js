import {
    useStore
} from 'vuex'

export default {
    GetJWTData() {
        const store = useStore()
        var token = null
        var jwt = store.getters["auth/JWT"]
        if (jwt !== null) {
            var base64Url = jwt.split('.')[1]
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
            token = JSON.parse(window.atob(base64))
        }
        return token;
    }
}