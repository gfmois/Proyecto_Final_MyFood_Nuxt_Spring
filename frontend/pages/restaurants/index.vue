<script setup>
import { useGetRestaurants } from '~~/composables/restaurants/useRestaurants';

const restaurants = ref(useGetRestaurants())
</script>

<template>
    <div class="p-4 w-full h-full flex items-center justify-center flex-col bg-gray-100">
        <div class="p-4 mb-4 w-full h-full divide-y divide-y-reverse border-t-2 border-b-2">
            <div class="flex w-full h-full flex-wrap items-end justify-center">
                <div class="flex-1 items-center justify-center flex hover:font-bold cursor-pointer h-full w-full">Todos los
                    restaurantes</div>
                <div class="flex-1 items-center justify-center flex">Recientemente añadidos</div>
            </div>
        </div>
        <Suspense>
            <template #fallback>
                <div>Cargando datos de restaurantes...</div>
            </template>
            <template #default>
                <div class="mx-auto w-full h-full">
                    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6" v-for="restaurant in restaurants" :key="restaurant.id_restaurant">
                        <div class="flex justify-center text-6xl border-2 border-gray-300 rounded-xl p-6 bg-gray-100">{{ restaurant.name }}</div>
                    </div>
                </div>
            </template>
        </Suspense>
    </div>
</template>