<script setup>
import { useGetRestaurantByEmployee } from "~/composable/restaurants/useRestaurants"
import { useAuth } from "~/store"

const { user } = useAuth()
// const restaurant = ref(await useGetRestaurantByEmployee(user.value.id_restaurant))

</script>

<template>
    {{ user }}
</template>
