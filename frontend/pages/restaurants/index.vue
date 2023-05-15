<script setup>
import { useGetRestaurants, useGetFilteredRestaunrats, useGetRestaurantsInfo } from '~~/composables/restaurants/useRestaurants';

const route = useRoute()

const restaurants = ref(await useGetRestaurants())
const info = ref(await useGetRestaurantsInfo())
const seeFilters = ref(true)

const filters = reactive({ value: {} })

const resetFilters = () => {
    filters.value = {}
}

watch(filters, async (v, pv) => {
    restaurants.value = (await useGetFilteredRestaunrats(v.value)).value
})

onMounted(() => {
    if (route.query) {
        filters.value = route.query
    }
})

const filtersCloses = reactive({ value: {
    0: false,
    1: false,
    2: false
} })

const closeOthers = (index) => {
    if (index == -1) {
        filtersCloses.value =  Object.values(filtersCloses.value).map((e, i) => filtersCloses.value[i] = false )
    } else {
        filtersCloses.value =  Object.values(filtersCloses.value).map((e, i) => i == index ? filtersCloses.value[index] = true : filtersCloses.value[index] = false )
    }
}

</script>

<template>
    <Header>
        <Title>Restaurantes</Title>
    </Header>

    <pre>{{ categories }}</pre>

    <div class="p-4 w-full h-full flex items-center justify-center flex-col bg-gray-100">
        <div class="p-4 mb-4 w-full h-full divide-y divide-y-reverse border-t-2 border-b-2">
            <div class="flex w-full h-full flex-wrap items-end justify-center">
                <div class="flex-1 items-center justify-center flex cursor-pointer h-full w-full"
                    v-on:click="() => {seeFilters = !seeFilters; closeOthers(-1)}">{{ seeFilters ? "Mostrar" : "Esconder" }} Filtros</div>

            </div>
        </div>
        <div class="bg-gray-200 mb-3 w-full h-full p-2 flex flex-row gap-3 items-center" v-if="!seeFilters">
            <div class="p-2 ml-2 flex items-center justify-center">
                <Icon name="ri:delete-bin-2-fill" class="cursor-pointer hover:text-red-600" size="1.25rem"
                    @click="() => resetFilters()" />
            </div>
            <LayoutFilterBean filterName="Ciudad" :filterOptions="info.cities"
                @closeOthers="$e => closeOthers(0)"
                @value="$e => filters.value.city = $e" :parentCloses="filtersCloses.value[0]" />
            <!-- Will Come from DB -->
            <LayoutFilterBean filterName="Categoria" :filterOptions="info.categories "
                @closeOthers="$e => closeOthers(1)"
                @value="$e => filters.value.category = $e" :parentCloses="filtersCloses.value[1]" />
            <!-- FIXME: Change this to line -->
            <LayoutFilterBean filterName="Precio" :filterOptions="['5', '10', '15', '20']"
                @closeOthers="$e => closeOthers(2)"
                @value="$e => filters.value.price = $e" :parentCloses="filtersCloses.value[2]" />
        </div>
        <div class="flex gap-4 flex-col">
            <div class="grid grid-cols-1 md:grid-cols-4 gap-4 sm:gap-6 md:gap-8 lg:gap-10" v-if="restaurants.length > 0">
                <NuxtLink
                    class="max-w-4xl mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl cursor-pointer hover:shadow-2xl"
                    :to="`/restaurants/${restaurant.slug}`" v-for="restaurant in restaurants"
                    :key="restaurant.id_restaurant">
                    <RestaurantCard :restaurant="restaurant" />
                </NuxtLink>

            </div>
            <div v-if="restaurants.length == 0"
                class="flex items-center justify-center w-full h-full p-2 font-medium text-xl">
                    <h1>Sin Restaurantes con estas características</h1>
                </div>
        </div>
    </div>
</template>
