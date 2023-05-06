<script setup>
import { useToast } from 'vue-toast-notification';
import { useGetClientOrders, useCancelOrder } from '~~/composables/orders/useOrder';

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

const cancelOrder = async (item) => {
    const objToUpdate = {
        id_order: item["Order No"],
        restaurant: item["Restaurant"],
        status: item.Status,
        order_date: item.Date
    }

    const res = await useCancelOrder(objToUpdate)
    console.log(res.value);
    if (res.value.status != 200) {
        useToast().error(res.value.message)
        return
    }

    useToast().success('Order Cancelled')
    obj.value[obj.value.findIndex((e) => e["Order No"] == objToUpdate.id_order)].Status = res.value.order.status
}
</script>

<template>
    <Header>
        <Title>Mis Pedidos</Title>
    </Header>
    <LayoutTable :items="obj" @cancel="$e => cancelOrder($e)" :hasCancel="true" :toModal="['Products']" />
</template>
