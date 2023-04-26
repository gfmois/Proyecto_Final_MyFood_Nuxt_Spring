<script setup>
const restaurantOrders = {
    id_restaurant: "ttest",
    name: "Restaurante Lu"
}
</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems :object="restaurantOrders" />
    </div>
</template>
