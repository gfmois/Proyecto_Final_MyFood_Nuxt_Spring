<script setup>
import { useGetNRestaurants, useGetRestaurantsInfo } from '~~/composables/restaurants/useRestaurants';

const nInfo = await useGetNRestaurants();
const selectInfo = await useGetRestaurantsInfo();

const obj = reactive({ value: {} })

const goRestaurants = () => {
    // NOTE: Ask if information can be passed across pages
    useRouter().replace('/restaurants')
}

</script>

<template>
    <Header>
        <Title>Inicio</Title>
    </Header>

    <div
        class="h-full w-full p-[10px] box-border grid grid-cols-1 grid-rows-1 gap-y-[10px] gap-x-[10px] tracking-[2px] text-[1.5rem] relative">
        <div class="w-full h-[75vh] bg-red-400 overflow-hidden filter brightness-50 relative">
            <img src="/images/home.jpg" alt="MyFood Welcome Image" class="h-full w-full object-cover object-center">
        </div>
        <div
            class="absolute xs:w-3/4 xs:h-2/3 sm:h-1/2 lg:w-1/3 lg:h-2/4 z-50 top-20 xs:left-12 lg:left-20 bg-white/80 p-6 text-black rounded-lg shadow-lg font-mono text-center">
            <h1 class="font-bold">Bienvenido a MyFood</h1>
            <p class="text-sm xs:text-[0.95rem]">¿A donde nos dirigimos hoy?</p>
            <div class="w-full h-1/2 flex items-start justify-center">
                <div class="w-1/2 p-4 flex flex-col gap-2 border border-gray-500/60 mt-4">
                    <!-- <LayoutInput input-type="text" input-placeholder="Valencia" inputLabel="Ciudad" />
                    <LayoutInput input-type="text" input-placeholder="Indio" inputLabel="Tipo de Comida" /> -->
                    <select name="city" v-model="obj.value.city" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                        <option disabled selected value="">Selecciona una ciudad</option>
                        <option :value="city" v-for="city in selectInfo.cities">{{ city }}</option>
                    </select>

                    <select name="cateogory" v-model="obj.value.category" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5">
                        <option disabled selected>Selecciona una categoria</option>
                        <option :value="category" v-for="category in selectInfo.categories">{{ category }}</option>
                    </select>
                </div>
            </div>
            <LayoutButton button-type="custom" :action="() => goRestaurants()"
                custom-style="bg-crimson-500 hover:bg-crimson-700 hover:dark:bg-crimson-600 dark:focus:ring-crimson-700"
                title="Comienza tu Experiencia" class="text-center mt-8" />
        </div>
        <div class="flex items-center flex-wrap justify-center gap-2 h-full w-full">
            <div class="bg-red-400 flex-1 h-full w-full p-4 flex items-center justify-center flex-col gap-2 rounded-md">
                <Icon name="grommet-icons:restaurant" size="4rem" />
                <p class="text-center text-sm">Actualmente tenemos </p>
                <p><strong>{{ nInfo.restaurants }}</strong></p>
                <p class="text-sm">restaurante a tu disposición, para que los disfutes</p>
            </div>
            <div class="bg-red-400 flex-1 h-full w-full p-4 flex items-center justify-center flex-col gap-2 rounded-md">
                <Icon name="grommet-icons:send" size="4rem" />
                <p class="text-center text-sm">Contamos con exactamente</p>
                <p><strong>{{ nInfo.orders }}</strong></p>
                <p class="text-sm">pedidos realizados</p>
            </div>
            <div class="bg-red-400 flex-1 h-full w-full p-4 flex items-center justify-center rounded-md">
                <Icon name="grommet-icons:restaurant" size="4rem" />
            </div>
        </div>
    </div>
</template>
