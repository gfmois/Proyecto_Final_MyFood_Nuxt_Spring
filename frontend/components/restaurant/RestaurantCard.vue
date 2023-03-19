<script setup>
const { restaurant } = defineProps({
    restaurant: Object
})
</script>

<template>
    <div class="md:flex">
        <div class="md:flex-shrink-0">
            <div class="p-2">
                <img class="h-48 w-full object-cover md:w-48" src="/images/restaurants/11b346c806_1679092106944.png"
                    alt="Restaurante">
            </div>
        </div>
        <div class="p-8">
            <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">Restaurante</div>
            <p class="block mt-1 text-lg leading-tight font-medium text-black">{{ restaurant.name }}</p>
            <p class="mt-2 text-gray-500">{{ restaurant.category }}</p>
            <div class="flex mt-2 items-center">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-yellow-500" v-for="_ in 3" viewBox="0 0 20 20"
                    fill="currentColor">
                    <path
                        d="M10 12.585L3.951 16.4l1.599-7.425L.594 6.6l7.219-.622L10 0l2.187 5.978 7.219.622-4.957 2.375 1.599 7.425L10 12.585z" />
                </svg> <!-- average comments -->
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-gray-500" viewBox="0 0 20 20" fill="none"
                    stroke="currentColor" v-for="_ in 2"> <!-- Rest of average comments -->
                    <path
                        d="M10 12.585L3.951 16.4l1.599-7.425L.594 6.6l7.219-.622L10 0l2.187 5.978 7.219.622-4.957 2.375 1.599 7.425L10 12.585z" />
                </svg>

            </div>
        </div>
    </div>
</template>