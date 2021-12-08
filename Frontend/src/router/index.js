import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import routes from './routes'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

export default route(function ({ store }) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory)



  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,

    // Leave this as is and make changes in quasar.conf.js instead!
    // quasar.conf.js -> build -> vueRouterMode
    // quasar.conf.js -> build -> publicPath
    history: createHistory(process.env.MODE === 'ssr' ? void 0 : process.env.VUE_ROUTER_BASE)
  })

  Router.beforeEach((to, from, next) => {
    console.log("coming from", from)
    console.log("going to", to)

    var token = null
    var jwt = store.getters["auth/JWT"]
    if (jwt !== null) {
      var base64Url = jwt.split('.')[1]
      var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
      token = JSON.parse(window.atob(base64))
    }



    if (to.meta !== undefined) {
      if (to.meta.requiredRole) {

        if (to.meta.requiredRole == "*" && store.getters["auth/authorized"] == false) {
          next("Login")
          return
        }
      }

    }
    next()
    return
  })

  return Router
})
