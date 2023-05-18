<script setup>
import { useLogin, useRegister } from "~/composables/auth/useAuth"
import { useToast } from 'vue-toast-notification';
import { useRegisterRestaurant } from "~/composables/restaurants/useRestaurants"

const toast = useToast({
    position: 'top-right',
    pauseOnHover: true,
})
const router = useRouter()

const formType = ref(0)
const credentials = ref({})
const restaurant = ref({})
const direcction = reactive({
    value: {}
})

const url = 'https://address-from-to-latitude-longitude.p.rapidapi.com/geolocationapi?address='
const options = {
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': import.meta.env.VITE_X_RAPIDAPI_ATC_KEY,
        'X-RapidAPI-Host': import.meta.env.VITE_X_RAPIDAPI_ATC_HOST
    }
};

const handleLoginButton = async () => {
    const response = await useLogin(credentials.value)

    if (response.value.status != 500) {
        toast.success('Has Iniciado Sesión')
        router.replace('/admin')
        return
    }

    toast.error('Usuario o contraseña incorrectos')
}

const handleRegister = async () => {
    const restaurantResult = await useRegisterRestaurant(direcction.value)
    if (restaurantResult.value.status == 200) {
        credentials.value.name = `ADMINISTRADOR ${direcction.value.name}`
        credentials.value.type = "ADMIN"
        credentials.value.phone = "000000000"
        credentials.value.id_restaurant = restaurantResult.value.id_restaurant
        const userResult = await useRegister(credentials.value)

        if (userResult.value.token) {
            useRouter().replace('/admin')
        }
    }
}

const handleFileImage = (index, event) => {
    const file = event.target.files[0];

    direcction.value.file = file
};

const handleFileLogo = (index, event) => {
    const file = event.target.files[0];

    direcction.value.logo = file;
};

let typingTimer; // Variable para almacenar el temporizador de escritura
const doneTypingInterval = 1000; // Intervalo de tiempo después de terminar de escribir (en milisegundos)

watch(restaurant.value, async (v, pv) => {
    direcction.value.slug = v.name.replaceAll(' ', "_")
    direcction.value.name = v.name
    clearTimeout(typingTimer); // Limpiar el temporizador existente
    typingTimer = setTimeout(async () => {
        try {
            const response = await fetch(url + restaurant.value.direction.replaceAll(" ", "%20"), options);
            const result = await response.text();

            const address = JSON.parse(result).Results[0]

            console.log(address);

            direcction.value.lng = address.longitude
            direcction.value.lat = address.latitude
            direcction.value.city = address.city

        } catch (error) {
            console.error(error);
        }
    }, doneTypingInterval);
})




</script>

<template>
    <Header>
        <Title>Empleados</Title>
    </Header>
    <div class="mt-10 flex items-center justify-center">
        <div class="flex flex-col items-center justify-center">
            <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900">
                <img class="w-8 h-8 mr-2" src="/images/logo.png" alt="logo">
                MyFood
            </a>
            <div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
                v-if="formType == 0">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                        {{ $t('auth.singin') }}
                    </h1>
                    <div class="space-y-4 md:space-y-6">
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('inputs.name') }}</label>
                            <input type="email" v-model="credentials.email" name="email" id="email"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                placeholder="name@company.com" required>
                        </div>
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('inputs.password') }}</label>
                            <input type="password" v-model="credentials.contraseña" name="password" id="password"
                                placeholder="••••••••"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required>
                        </div>
                        <button @click="() => handleLoginButton()"
                            class="w-full bg-crimson-600 text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">{{
                                $t('auth.login') }}</button>
                        <p class="text-sm font-light text-gray-500 dark:text-gray-400 flex gap-1">
                            {{ $t('auth.noaccount') }}
                        <p class="font-medium text-crimson-500 cursor-pointer hover:underline dark:text-crimson-500"
                            @click="() => formType = 1">{{ $t('auth.createit') }}</p>
                        </p>
                    </div>
                </div>
            </div>
            <div class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700"
                v-if="formType == 1">
                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
                    <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                        {{ $t('auth.create_restaurant') }}
                    </h1>
                    <div class="space-y-4 md:space-y-6">
                        <div>
                            <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('inputs.email') }}</label>
                            <input type="email" v-model="credentials.email" name="email" id="email"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                placeholder="name@company.com" required>
                        </div>
                        <div>
                            <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('inputs.password') }}</label>
                            <input type="password" v-model="credentials.contraseña" name="password" id="password"
                                placeholder="••••••••"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required>
                        </div>
                        <div>
                            <label for="restaurant" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('auth.restaurant.name') }}</label>
                            <input type="text" v-model="restaurant.name" name="restaurant" id="restaurant"
                                placeholder="Restaurante Gustavo"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required>
                        </div>
                        <div class="flex flex-row gap-2 items-center justify-center">
                            <div>
                                <label for="capacity" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                    $t('auth.restaurant.capacity') }}</label>
                                <input type="number" v-model="direcction.value.capacity" name="capacity" id="capacity" min="20" max="250"
                                    placeholder="6"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required>
                            </div>
                            <div>
                                <label for="category" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                    $t('auth.restaurant.category') }}</label>
                                <input type="text" v-model="direcction.value.category" name="category" id="category"
                                    placeholder="Italiano"
                                    class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                    required>
                            </div>
                        </div>
                        <div>
                            <label for="direction" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">{{
                                $t('auth.restaurant.direction') }}</label>
                            <input type="text" v-model="restaurant.direction" name="direction" id="direction"
                                placeholder="Av. Cristóbal Colón, Ontinyent, España"
                                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                                required>
                        </div>
                        <div class="flex items-center justify-center gap-2">
                            <div
                                class="flex items-center justify-center p-6 border rounded-lg hover:shadow-2xl cursor-pointer">
                                <label for="fileInputLogo"
                                    class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                    <span class="cursor-pointer">Seleccionar Logo</span>
                                    <input id="fileInputLogo" type="file" class="sr-only"
                                        @change="handleFileLogo(index, $event)">
                                </label>
                            </div>

                            <div
                                class="flex items-center justify-center p-6 border rounded-lg hover:shadow-2xl cursor-pointer">
                                <label for="fileInput" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
                                    <span class="cursor-pointer">Seleccionar Imágen</span>
                                    <input id="fileInput" type="file" class="sr-only"
                                        @change="handleFileImage(index, $event)">
                                </label>
                            </div>
                        </div>
                    </div>
                    <button @click="() => handleRegister()"
                        class="w-full bg-crimson-600 text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center">{{
                            $t('auth.register') }}</button>
                    <p class="text-sm font-light text-gray-500 dark:text-gray-400 flex gap-1">
                        {{ $t('auth.haveAccount') }}
                    <p class="font-medium text-crimson-500 cursor-pointer hover:underline dark:text-crimson-500"
                        @click="() => formType = 0">{{ $t('auth.goLogin') }}</p>
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>
