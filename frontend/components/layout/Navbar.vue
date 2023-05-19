<script setup>
import { useAuth } from "~~/store"

const { locale } = useI18n()
const { actCheckHasUser, mutCheckIsAdmin, user: storeUser, actChangeUser } = useAuth()

const router = useRouter()

const client_token = useCookie('token_client')
const admin_token = useCookie('token_admin')

const user = computed(() => storeUser.value)

const dropdown = ref([
    {name: "Mi perfil", src: "/profile", icon: "fa-user"},
    {name: "Mis pedidos", src: "/profile/orders", icon: "bi-basket2-fill"},
    {name: "Mis reservas", src: "/profile/reserves", icon: "md-daterange"},
    {name: "Logout", click: "logout", icon: "md-logout", action: () => logout()}
])

const dropdownVisible = ref(false)

const logout = () => {
    client_token.value = null
    admin_token.value = null

    actCheckHasUser()
    mutCheckIsAdmin(false)
    actChangeUser(false)

    router.replace("/auth")
}
</script>

<template>
    <nav class="bg-crimson-600 w-full h-[7vh] p-4 shadow-md flex items-center justify-between" :key="user">
        <!-- Logo and Site Name -->
        <div class="p-4 w-full flex flex-row gap-8 items-center justify-between">
            <NuxtLink to="/" class="h-fit w-fit p-2 flex items-center justify-center gap-3">
                <img src="/images/logo.png" alt="Site Logo" class="cursor-pointer w-12 h-12">
                <h1 class="text-white text-lg">MyFood</h1>
            </NuxtLink>
            <!-- Search Bar -->
            <div class="bg-custom_orange flex items-center justify-center">

            </div>
            <!-- Items -->
            <div>
                <ul class="flex flex-row gap-5 p-3">
                    <li>
                        <NuxtLink to="/restaurants">
                            <Icon name="ri:calendar-check-fill" size="2rem" style="color: rgb(228 228 231 / 1);"
                                class="cursor-pointer" />
                        </NuxtLink>
                    </li>
                    <li>
                        <NuxtLink to="/auth" v-if="!user">
                            <Icon name="ri:account-box-fill" size="2rem" style="color: rgb(228 228 231 / 1);"
                            class="cursor-pointer" />
                        </NuxtLink>

                        <div v-if="user" class="rounded-full relative w-8 h-8 z-50 cursor-pointer" @mouseenter="dropdownVisible = true" @mouseleave="dropdownVisible = false">
                            <img :key="user" :src="user.avatar" alt="">
                            <div class="relative w-full h-full right-0 inline-block text-left z-50" v-if="dropdownVisible">
                                <div class="absolute right-0 w-48 mt-2 origin-top-right bg-white divide-y divide-gray-100 rounded-md shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none" role="menu" aria-orientation="vertical" aria-labelledby="options-menu">
                                  <div class="py-1" role="none">
                                    <NuxtLink v-bind:key="item" :to="item.src" @click="item.action" v-for="item in dropdown" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900">
                                        {{ item.name }}
                                    </NuxtLink>
                                  </div>
                                </div>
                              </div>
                        </div>
                    </li>
                    <li >
                        <select v-model="locale">
                            <option value="es">
                                Español
                            </option>
                            <option value="en">
                                Inglés
                            </option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>
