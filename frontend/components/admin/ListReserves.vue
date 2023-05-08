<script setup>
import { useGetRestaurantReserves, useUpdateReserve } from '~~/composables/reserves/useReserves';
import { useToast } from 'vue-toast-notification';
import { useAuth } from "~/store"

const { user } = useAuth()

const toast = useToast({
    position: 'top-right',
    pauseOnHover: true,
})

const restaurantReserves = (await useGetRestaurantReserves()).value
const keys = Object.keys(restaurantReserves.reserves[0])
const cancelReserve = ref()
const itemToUpdate = ref()

restaurantReserves.reserves = restaurantReserves.reserves.map((e) => {
    e.id_restaurant = user.value.id_restaurant
    return e
})

watch(cancelReserve, async (v) => {
    v.status = "CANCELLED"

    const res = (await useUpdateReserve(v)).value

    if (res.status != 400) {
        toast.success(res.message)
    }
})

watch(itemToUpdate, async (v, pv) => {
    const _keys = {
        id_reserve: 0,
        id_client: 1,
        name: 2,
        types: 3,
        date_reserve: 4,
        diners: 5,
        status: 6
    };

    const obj = ref({})

    Object.keys(_keys).map((e, index) => {
        console.log(e);
        obj.value[e] = v[index]
    })

    console.log(obj.value);

    const res = (await useUpdateReserve(obj.value)).value

    if (res.status != 400) {
        toast.success(res.message)
    }
})

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems
            :idObj="restaurantReserves.reserves[0].id_restaurant"
            :object="restaurantReserves.reserves"
            keyBanned="product_ordered"
            keyPreffered="Productos"
            :hasActionButtons="true"
            :onlyIsAdmin="false"
            @cancel="$e => cancelReserve = $e[0]" :toSee="['status', 'diners', 'types', 'date_reserve']"
            @update="$e => itemToUpdate = $e" />
    </div>
</template>
