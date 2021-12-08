const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      {
        path: "",
        component: () => import("pages/Index.vue"),
        meta: { requiredRole: "*" },
      },
      {
        path: "UserManagement",
        component: () => import("pages/UserManagement.vue"),
        meta: { requiredRole: "*" },
      },
      {
        path: "GroupManagement",
        component: () => import("pages/GroupManagement.vue"),
        meta: { requiredRole: "*" },
      },
      {
        path: "ProjectManagement",
        component: () => import("pages/ProjectManagement.vue"),
        meta: { requiredRole: "*" },
      },
    ],
  },
  {
    path: "/",
    component: () => import("layouts/Empty.vue"),
    children: [
      {
        path: "Login",
        name: "Login",
        component: () => import("pages/Login.vue"),
      },
      {
        path: "SetPasswort",
        component: () => import("pages/SetPasswort.vue"),
        meta: { requiredRole: "*" },
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/Error404.vue"),
  },
];

export default routes;
