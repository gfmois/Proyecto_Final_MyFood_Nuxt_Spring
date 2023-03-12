<script>
import "vue3-carousel/dist/carousel.css"
import { Carousel, Slide, Navigation } from "vue3-carousel"

export default {
    components: {
        Carousel,
        Slide,
        Navigation
    },
    setup() {
        return { images: ["restaurant_1.png", "restaurant_2.png", "restaurant_3.png", "restaurant_4.png", "restaurant_5.png", "restaurant_6.png",] }
    }
}
</script>

<template>
    <div class="h-[75vh] w-full p-[10px] box-border grid grid-cols-1 grid-rows-1 gap-y-[10px] gap-x-[10px] tracking-[2px] text-[1.5rem]">
        <div class="w-full h-full bg-red-400 overflow-hidden slide-item">
            <carousel :autoplay="3000" :wrap-around="true" :transition="1500">
                <slide v-for="image in images" :key="image">
                    <img :src="`/images/${image}`" alt="Restaurant Image" class="w-full h-full object-none object-center md:object-bottom">
                </slide>
            </carousel>
        </div>
    </div>
</template>

<style>
.slide-item {
    grid-area: 1 / 1 / 3 / 4;
    background-position: center bottom;
}

img {
    object-fit: none;
    object-position: center bottom;
  }
</style>