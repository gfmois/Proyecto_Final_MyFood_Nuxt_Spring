<script setup>
import { useGetRestaurantsById } from '~~/composables/restaurants/useRestaurants';

const route = useRoute()
const { id } = route.params

const data = await useGetRestaurantsById(id);
const { data: locationRes } = await useFetch("https://trueway-geocoding.p.rapidapi.com/ReverseGeocode", {
    query: { location: `${data.value.restaurant.lat},${data.value.restaurant.lng}`, lenguage: 'en' },
    headers: {
        'X-RapidAPI-Key': import.meta.env.VITE_RAPID_API_KEY,
        'X-RapidAPI-Host': import.meta.env.VITE_RAPID_API_HOST
    }
})
</script>

<template>
    <Header>
        <Title>{{ data.restaurant.name || 'Restaurante' }}</Title>
    </Header>
    <div class="flex items-start flex-col justify-center">
        <section class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="md:col-span-2">
                <img :src="`/${data.restaurant.image}`" alt="Imagen del Restaurante" class="w-full h-full object-cover">
            </div>
            <div class="p-5">
                <h2 class="text-3xl font-bold mb-4">{{ data.restaurant.name }}</h2>
                <div class="mb-4 flex flex-row gap-1">
                    <p class="font-bold">Dirección:</p>
                    <p>{{ locationRes.results[0].address.split(',')[0] }}</p>
                </div>
                <div class="mb-4 flex flex-row gap-1">
                    <p class="font-bold">Teléfono: </p>
                    <p> 123-456-7890</p>
                </div>
                <p class="mb-4 font-bold">Horario de atención:</p>
                <ul class="list-disc list-inside mb-4">
                    <li>Lunes a Viernes: 12pm - 9pm</li>
                    <li>Sábados y Domingos: 10am - 10pm</li>
                </ul>
                <p class="mb-4 font-bold">Descripción:</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam convallis eget arcu eu aliquet. In ac
                    nibh a
                    ante finibus eleifend vel vitae velit. Pellentesque habitant morbi tristique senectus et netus et
                    malesuada
                    fames ac turpis egestas. Vestibulum hendrerit, nunc nec faucibus eleifend, sapien lectus facilisis
                    sapien,
                    quis ultrices enim est in est. Praesent laoreet, dui in ullamcorper faucibus, justo leo auctor mi, sit
                    amet
                    faucibus velit est in libero. Proin et ullamcorper nulla. </p>
                <div class="mt-8 flex items-center justify-start p-2 flex-row gap-1">
                    <LayoutButton button-type="custom"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600"
                        title="Empezar la Reserva" />
                    <LayoutButton button-type="custom"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600"
                        title="Realizar un pedido" />
                </div>
            </div>
        </section>
        <section class="my-8 p-4 w-full">
            <div class="bg-crimson-600 text-white w-full">
                <h3 class="text-2xl font-bold mb-4 text-center p-2">Productos</h3>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 p-2">
                <ProductCard :product="product" v-for="product in data.products" v-if="data.products.length > 0" />
                <div v-if="data.products.length == 0" class="text-center w-full bg-red-400">Sin productos todavía</div>
            </div>
        </section>
    </div>
</template>