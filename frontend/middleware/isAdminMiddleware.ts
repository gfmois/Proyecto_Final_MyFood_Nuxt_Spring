import { useAuth } from "~~/store"

export default defineNuxtRouteMiddleware((to, from) => {
    const { isAdmin } = useAuth()

    if (!isAdmin.value) {
        useRouter().replace("/auth")
    }
})
