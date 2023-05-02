<script setup>
import { useGetRestaurantOrders, useUpdateOrder } from '~~/composables/orders/useOrder';
import { useToast } from 'vue-toast-notification';

const toast = useToast({
    position: 'top-right',
    pauseOnHover: true,
})

const restaurantOrders = (await useGetRestaurantOrders()).value
const keys = Object.keys(restaurantOrders.orders[0])
const cancelOrder = ref()
const itemToUpdate = ref()

restaurantOrders.orders = restaurantOrders.orders.map((e) => {
    e.product_ordered.map((i) => {
        delete i.product.restaurant
        return i
    })

    return e
})

watch(cancelOrder, async (v) => {
    v.status = "CANCELLED"
    delete v.product_ordered
    const res = (await useUpdateOrder(v)).value

    if (res.status != 400) {
        toast.success(res.message)
    }
})

watch(itemToUpdate, async(v, pv) => {
    const obj = {}
    keys.forEach((e, index) => obj[e] = v[index])
    const res = (await useUpdateOrder(obj)).value

    if (res.status != 400) {
        toast.success(res.message)
    }
})

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems :object="restaurantOrders.orders" keyBanned="product_ordered" keyPreffered="Productos" :hasActionButtons="true" @cancel="$e => cancelOrder = $e[0]" :toSee="['status']" @update="$e => itemToUpdate = $e" />
    </div>
</template>
