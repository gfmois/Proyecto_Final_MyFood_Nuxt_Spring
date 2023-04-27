<script setup>
import { useGetRestaurantOrders } from '~~/composables/orders/useOrder';

const { restaurant } = defineProps({
    restaurant: Object
})

const orders = await useGetRestaurantOrders()
const restaurantOrders = {
    id_restaurant: "ttest",
    name: "Restaurante Lu"
}
</script>

<template>
    <pre>
        {{ restaurant }}
    </pre>

    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems :object="restaurantOrders" />
    </div>
</template>
