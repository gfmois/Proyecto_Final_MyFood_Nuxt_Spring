<script setup>
import { useGetRestaurantOrders } from '~~/composables/orders/useOrder';

const { restaurant } = defineProps({
    restaurant: Object
})

const restaurantOrders = (await useGetRestaurantOrders()).value

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems :object="restaurantOrders.orders" keyBanned="product_ordered" keyPreffered="Productos" />
    </div>
</template>
