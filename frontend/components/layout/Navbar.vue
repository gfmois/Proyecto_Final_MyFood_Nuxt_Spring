<script setup>
import { useAuth } from "~~/store"
import { useGetProfile } from "~~/composables/client/useClient"

const $cookie = inject('$cookies')

const { locale } = useI18n()
const { actCheckHasUser, mutCheckIsAdmin, hasUser, isAdmin } = useAuth()

const userIsAdmin = reactive(computed(() => isAdmin))
const userInfo = ref({})

const getUserInfo = async () => {
    watch(await useGetProfile().value, (userProfile) => {
        userInfo.value = userProfile
    })
}

if (hasUser.value) {
    getUserInfo()
}

watch(hasUser, () => {
    if (hasUser.value) {
        getUserInfo()
    }
})

const logout = () => {
    $cookie.remove("token_client")
    $cookie.remove("token_admin")

    actCheckHasUser(false)
    mutCheckIsAdmin(false)
}
</script>

<template>
    <nav class="bg-crimson-600 w-full h-[7vh] p-4 shadow-md flex items-center justify-between">
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
                        <Icon name="ri:article-fill" size="2rem" style="color: rgb(228 228 231 / 1);"
                            class="cursor-pointer" />
                    </li>
                    <li>
                        <NuxtLink to="/auth">
                            <Icon v-if="hasUser" name="ri:account-box-fill" size="2rem" style="color: rgb(228 228 231 / 1);"
                            class="cursor-pointer" />
                        </NuxtLink>
                        {{ hasUser }}
                    </li>
                    <li>
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
</nav></template>