import { boot } from 'quasar/wrappers'
import axios from 'axios'

export default boot(({ app, store }) => {
  const instance = axios.create({
    baseURL: process.env.API_URL,
    headers: {
      authorization: "Bearer " + store.getters["auth/JWT"]
    }
  });

  instance.interceptors.response.use(
    response => {
      Vue.$log.debug("Response: " + response.request.responseURL, response);

      if (response.status == 500) {
        Notify.create({
          message: "Internal Server error!",
          type: "negative",
          position: "top"
        });
      }

      return response;
    },
    error => {
      Vue.$log.debug("Network error:", error);
      let response = error.response;

      if (response.status == 403 || response.status == 401) {
        store.dispatch("auth/logout");
        Notify.create({
          message: "Not allowed!",
          type: "negative",
          position: "top"
        });
        this.$router.push({ name: "Login" });
      }
      if (response) {
        console.log("Response error:", response);

        Notify.create({
          message: response.status + " " + response.statusText,
          type: "negative",
          position: "top"
        });
        return response;
      } else {
        Notify.create({
          message: "Unable to connect to server!",
          type: "negative",
          position: "top"
        });
        return error;
      }
    }
  );


  app.config.globalProperties.$axios = axios
  // ^ ^ ^ this will allow you to use this.$axios (for Vue Options API form)
  //       so you won't necessarily have to import axios in each vue file

  app.config.globalProperties.$api = instance
  // ^ ^ ^ this will allow you to use this.$api (for Vue Options API form)
  //       so you can easily perform requests against your app's API
  window.axios
})


