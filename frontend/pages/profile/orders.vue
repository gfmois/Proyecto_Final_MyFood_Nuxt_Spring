<script setup>
import { useGetClientOrders } from '~~/composables/orders/useOrder';

const values = ref(await useGetClientOrders())
const obj = reactive({ value: [] })

values.value = values.value.orders.forEach((e) => {
    e.total_quantity = 0
    delete e.id_client

    e.products.forEach((p) => {
        e.total_quantity += p.quantity
    })

    obj.value.push({
        "Order No": e.id_order,
        "Restaurant": e.restaurant,
        "Status": e.status,
        "Date": e.order_date,
        "Products": "Ver",
        "_Products": e.products
    })
})
</script>

<template>
    <Header>
        <Title>Mis Pedidos</Title>
    </Header>
    <LayoutTable :items="obj" @cancel="$e => ($e)" :hasCancel="true" :toModal="['Products']" />
</template>
