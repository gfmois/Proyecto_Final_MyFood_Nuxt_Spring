<script setup>
import { useGetRestaurants } from '~~/composables/restaurants/useRestaurants';
import { Suspense } from 'vue';

const restaurants = ref(useGetRestaurants())
const seeFilters = ref(true)
</script>

<template>
    <Header>
        <Title>Restaurantes</Title>
    </Header>
    <div class="p-4 w-full h-full flex items-center justify-center flex-col bg-gray-100">
        <div class="p-4 mb-4 w-full h-full divide-y divide-y-reverse border-t-2 border-b-2">
            <div class="flex w-full h-full flex-wrap items-end justify-center">
                <div class="flex-1 items-center justify-center flex cursor-pointer h-full w-full"
                    v-on:click="() => seeFilters = !seeFilters">{{ !seeFilters ? "Mostrar" : "Esconder" }} Filtros</div>
                <div class="flex-1 items-center justify-center flex cursor-pointer">Recientemente añadidos</div>
            </div>
        </div>
        <Suspense>
            <template #fallback>
                <div>Cargando datos de restaurantes...</div>
            </template>
            <template #default>
                <div class="flex gap-4 flex-col">
                    <div class="bg-gray-200 w-full h-full p-2 flex flex-row gap-3" v-if="seeFilters">
                        <LayoutFilterBean filterName="Ciudad" :filterOptions="['Alicante', 'Valencia', 'Madrid']" />
                        <!-- Will Come from DB -->
                        <LayoutFilterBean filterName="Categoria"
                            :filterOptions="['Chino', 'Japones', 'De la Terreta', 'Nacional']" />
                        <LayoutFilterBean filterName="A Domicilio" :filterOptions="['Si', 'No']" />
                    </div>
                    <div class="grid grid-cols-1 md:grid-cols-4 gap-4 sm:gap-6 md:gap-8 lg:gap-10">
                        <NuxtLink
                            class="max-w-4xl mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl cursor-pointer hover:shadow-2xl"
                            :to="`/restaurants/${restaurant.id_restaurant}`" v-for="restaurant in restaurants"
                            :key="restaurant.id_restaurant">
                            <Suspense>
                                <RestaurantCard :restaurant="restaurant" />
                                <template #fallback>
                                    Loading...
                                </template>
                            </Suspense>
                        </NuxtLink>
                    </div>
                </div>
            </template>
        </Suspense>
    </div>
</template>