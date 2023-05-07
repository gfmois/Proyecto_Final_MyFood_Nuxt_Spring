import { useAuth } from "~~/store";

export default defineNuxtRouteMiddleware((to, from) => {
  const { isAdmin, hasUser } = useAuth();
  const router = useRouter();

  if (!isAdmin.value && hasUser.value && to.fullPath == "/profile") {
    router.push("/profile");
    return;
  }

  if (
    (!hasUser.value && to.fullPath == "/profile") ||
    (!hasUser.value && to.fullPath == "/admin") ||
    (!isAdmin.value && to.fullPath == "/admin") ||
    (hasUser.value && to.fullPath == '/admin')
  ) {
    router.push("/");
    return;
  }
});
