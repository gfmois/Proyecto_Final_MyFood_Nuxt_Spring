<script setup>
import { useAuth } from "~/store"
import { useUpdateProfile } from "~/composables/client/useClient"
import { useToast } from 'vue-toast-notification'

const { user, actChangeUser } = useAuth()

const profile = reactive({
    value: {
        name: null,
        phone: null,
        password: null,
        re_password: null
    }
})

const saveNewProfile = async () => {
    const result = await useUpdateProfile(profile.value)

    if (result.value.status && result.value.status != 200) {
        useToast().error('Error Mofificando tu Perfil')
        return
    }

    useToast().success('Perfil Actualizado')
    actChangeUser(result.value)
}

</script>

<template>
    <Header>
        <Title>Mi Perfil</Title>
    </Header>
    <LazyClientOnly placeholder="Loading">
        <div class="flex items-center justify-center">
            <div class="w-[70vw] h-[90vh] p-4 flex items-center justify-center">
                <div class="w-full h-full left-0 p-1 relative">
                    <div
                        class="bg-gradient-to-r from-crimson-600 via-crimson-700 to-crimson-800 w-full h-1/5 p-2 rounded-t-lg" />
                    <div
                        class="absolute z-50 bg-white rounded-full w-48 h-48 top-[5.5rem] md:left-12 xs:left-1/4 flex items-center justify-center">
                        <img :src="user.avatar" alt="User Avatar" class="w-44 h-44">
                    </div>
                    <div class="bg-white w-full xs:rounded-b-xl xs:h-[85vh] md:w-full md:h-3/4 p-4">
                        <div
                            class="bg-gray-100 rounded-lg h-fit xs:w-[90%] md:w-1/3 absolute top-[20.4rem] xs:left-5 md:left-32 p-4">
                            <h1 class="font-semibold text-2xl">Información de Contacto</h1>
                            <div class="py-4">
                                <p class="font-medium text-lg">{{ $t('inputs.name') }}: {{ user.name }}</p>
                                <p class="font-medium text-sm">{{ $t('inputs.email') }}: {{ user.email }}</p>
                                <p class="font-medium text-sm">{{ $t('inputs.phone') }}: {{ user.phone }}</p>
                            </div>
                        </div>
                        <div
                            class="bg-gray-100 rounded-lg h-fit xs:w-[90%] md:w-1/3 absolute top-[30.4rem] xs:left-5 md:left-32 p-4 flex items-center justify-center">
                            <div class="flex gap-2">
                                <LayoutButton buttonType="green" button :title="$t('reserves')"
                                :action="() => useRouter().replace('/profile/reserves')" />
                                <LayoutButton buttonType="green" button :title="$t('orders')"
                                    :action="() => useRouter().replace('/profile/orders')" />
                            </div>
                        </div>
                        <div
                            class="bg-gray-100 rounded-lg xs:h-3/6 md:h-1/2 xs:w-3/4 md:w-1/3 absolute xs:-bottom-32 md:bottom-32 xs:right-12 md:right-32 p-4 flex flex-col gap-4">
                            <div>
                                <label for="name" class="block mb-2 text-sm font-medium text-gray-900">{{ $t('inputs.name')
                                }}</label>
                                <input type="text" id="name"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                                    :placeholder="user.name" v-model="profile.value.name" required>
                            </div>
                            <div>
                                <label for="phone" class="block mb-2 text-sm font-medium text-gray-900">{{
                                    $t('inputs.phone') }}</label>
                                <input type="text" id="phone"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                                    :placeholder="user.phone" v-model="profile.value.phone" required>
                            </div>
                            <div>
                                <label for="password" class="block mb-2 text-sm font-medium text-gray-900">{{
                                    $t('inputs.password') }}</label>
                                <input type="password" id="password"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                                    :placeholder="$t('inputs.password')" v-model="profile.value.password" required>
                            </div>
                            <div>
                                <label for="rePassword" class="block mb-2 text-sm font-medium text-gray-900">{{
                                    $t('inputs.repassword') }}</label>
                                <input type="password" id="rePassword"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5"
                                    :placeholder="$t('inputs.repassword')" v-model="profile.value.re_password" required>
                            </div>
                            <div class="flex items-center justify-center">
                                <LayoutButton buttonType="green" button :title="$t('save')"
                                    :action="() => saveNewProfile()" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </LazyClientOnly>
</template>
