<script setup>
import { useGetRestaurantById } from '~~/composables/restaurants/useRestaurants';

const route = useRoute()
const { id } = route.params

const restaurant = reactive(useGetRestaurantById(id))

</script>

<template>
    <Header>
        <Title>{{ restaurant.name || 'Restaurante' }}</Title>
    </Header>
    <Suspense>
        <template #default>
            <div class="flex flex-col w-full h-full">
                <div class="bg-red-400 w-full h-16 flex items-center justify-center">
                    <p class="text-xl font-semibold rounded-b-xl font-cormorant">{{ restaurant.name }}</p>
                </div>
                <div class="w-full h-full">
                    <img :src="`/${restaurant.image}`" alt="Restaurant Image"
                        class="w-full h-full object-cover">
                </div>
            </div>
        </template>
        <template #fallback>
            <div class="h-screen w-screen bg-black text-white">
                Loading...
            </div>
        </template>
    </Suspense>
</template>