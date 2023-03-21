<script setup>
import { useGetRestaurantsById } from '~~/composables/restaurants/useRestaurants';

const route = useRoute()
const { id } = route.params

const restaurant = await useGetRestaurantsById(id);
</script>

<template>
    <Header>
        <Title>{{ restaurant.name || 'Restaurante' }}</Title>
    </Header>
    <div class="flex items-start flex-col justify-center">
        <section class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="md:col-span-2">
                <img :src="`/${restaurant.image}`" alt="Imagen del Restaurante" class="w-full h-full object-cover">
            </div>
            <div class="p-5">
                <h2 class="text-3xl font-bold mb-4">{{ restaurant.name }}</h2>
                <p class="mb-4">Dirección: Calle Principal #123</p>
                <p class="mb-4">Teléfono: 123-456-7890</p>
                <p class="mb-4">Horario de atención:</p>
                <ul class="list-disc list-inside mb-4">
                    <li>Lunes a Viernes: 12pm - 9pm</li>
                    <li>Sábados y Domingos: 10am - 10pm</li>
                </ul>
                <p class="mb-4">Descripción:</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam convallis eget arcu eu aliquet. In ac
                    nibh a
                    ante finibus eleifend vel vitae velit. Pellentesque habitant morbi tristique senectus et netus et
                    malesuada
                    fames ac turpis egestas. Vestibulum hendrerit, nunc nec faucibus eleifend, sapien lectus facilisis
                    sapien,
                    quis ultrices enim est in est. Praesent laoreet, dui in ullamcorper faucibus, justo leo auctor mi, sit
                    amet
                    faucibus velit est in libero. Proin et ullamcorper nulla. </p>
                <div class="bg-red-400 mt-8 flex items-start justify-center flex-col p-2">
                    <button type="button" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800">Default</button>

                </div>
            </div>
        </section>
        <section class="my-8 p-4 w-full">
            <div class="bg-crimson-600 text-white w-full">
                <h3 class="text-2xl font-bold mb-4 text-center p-2">Productos</h3>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 p-2">
                <ProductCard :product="restaurant" />
                <ProductCard :product="restaurant" />
                <ProductCard :product="restaurant" />
                <ProductCard :product="restaurant" />
                <ProductCard :product="restaurant" />
            </div>
        </section>
    </div>
</template>