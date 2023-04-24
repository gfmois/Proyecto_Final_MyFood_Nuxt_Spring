import { useAuth } from "~~/store";

export default defineNuxtRouteMiddleware((to, from) => {
  const { isAdmin } = useAuth();

  const router = useRouter();

  console.log(from.fullPath, isAdmin.value);

  if (isAdmin.value && to.fullPath !== "/admin") {
    router.push("/admin");
  }

  if (!isAdmin.value && to.fullPath !== "/") {
    router.push("/");
  }
});
