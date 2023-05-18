<script setup>
const { restaurant } = defineProps({
    restaurant: Object
})
</script>

<template>
    <div class="md:flex xs:flex-col md:flex-col xl:flex-row column-card">
        <div class="md:flex-shrink-0">
          <div class="p-2 relative">
            <div class="absolute right-4 top-4">
              <div class="rounded-full border-[3px] border-amber-500 bg-transparent w-8 h-8">
                <img class="h-full w-full" :src="restaurant.logo" alt="Restaurant Logo">
              </div>
            </div>
            <img class="h-48 w-full object-cover md:w-48" :src="'/' + restaurant.image" alt="Restaurante Img">
          </div>
        </div>
        <div class="p-8 md:pl-4 md:flex-1 md:flex md:flex-col md:items-start md:justify-center">
          <div class="uppercase tracking-wide text-sm text-indigo-500 font-semibold">Restaurante</div>
          <p class="block mt-1 text-lg leading-tight font-medium text-black">{{ restaurant.name }}</p>
          <p class="mt-2 text-gray-500">{{ restaurant.category }}</p>
          <p class="mt-2 text-gray-500">{{ restaurant.city }}</p>
          <div class="flex mt-2 items-center"></div>
        </div>
      </div>
</template>

<style>
@media (max-width: 1285px) {
  .column-card {
        flex-direction: column;
    }
}
</style>
