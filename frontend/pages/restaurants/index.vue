<script setup>
import { useGetRestaurants, useGetFilteredRestaunrats } from '~~/composables/restaurants/useRestaurants';
import { Suspense } from 'vue';

const restaurants = ref(await useGetRestaurants())
const seeFilters = ref(true)

const filters = reactive({ value: {} })

const resetFilters = () => {
    filters.value = {}
}

watch(filters, async (v, pv) => {
    restaurants.value = (await useGetFilteredRestaunrats(v.value)).value
})

</script>

<template>
    <Header>
        <Title>Restaurantes</Title>
    </Header>
    <div class="p-4 w-full h-full flex items-center justify-center flex-col bg-gray-100">
        <div class="p-4 mb-4 w-full h-full divide-y divide-y-reverse border-t-2 border-b-2">
            <div class="flex w-full h-full flex-wrap items-end justify-center">
                <div class="flex-1 items-center justify-center flex cursor-pointer h-full w-full"
                    v-on:click="() => seeFilters = !seeFilters">{{ seeFilters ? "Mostrar" : "Esconder" }} Filtros</div>
                <div class="flex-1 items-center justify-center flex cursor-pointer">Recientemente añadidos</div>
            </div>
        </div>
        <Suspense>
            <template #fallback>
                <div>Cargando datos de restaurantes...</div>
            </template>
            <template #default>
                <div class="flex gap-4 flex-col">
                    <div class="bg-gray-200 w-full h-full p-2 flex flex-row gap-3 items-center" v-if="!seeFilters">
                        <div class="p-2 ml-2 flex items-center justify-center">
                            <Icon name="ri:delete-bin-2-fill" class="cursor-pointer hover:text-red-600" size="1.25rem" @click="() => resetFilters()" />
                        </div>
                        <LayoutFilterBean filterName="Ciudad" :filterOptions="['Alicante', 'Valencia', 'Madrid']"
                            @value="$e => filters.value.city = $e" />
                        <!-- Will Come from DB -->
                        <LayoutFilterBean filterName="Categoria"
                            :filterOptions="['Chino', 'Japones', 'De la Terreta', 'Nacional']"
                            @value="$e => filters.value.category = $e" />
                        <!-- FIXME: Change this to line -->
                        <LayoutFilterBean filterName="Precio" :filterOptions="['5', '10', '15', '20']"
                            @value="$e => filters.value.price = $e" />
                    </div>
                    <div class="grid grid-cols-1 md:grid-cols-4 gap-4 sm:gap-6 md:gap-8 lg:gap-10">
                        <NuxtLink
                            class="max-w-4xl mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl cursor-pointer hover:shadow-2xl"
                            :to="`/restaurants/${restaurant.slug}`" v-for="restaurant in restaurants"
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
</div></template>
