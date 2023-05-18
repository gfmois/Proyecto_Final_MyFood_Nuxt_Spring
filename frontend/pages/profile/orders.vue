<script setup>
import { useToast } from 'vue-toast-notification';
import { useGetClientOrders, useCancelOrder } from '~~/composables/orders/useOrder';

const values = ref(await useGetClientOrders())

const obj = reactive({ value: [] })
const isEmpty = ref(false)

isEmpty.value = values.value.orders.length > 0 ? false : true

if (!isEmpty.value) {
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
}

const cancelOrder = async (item) => {
    const objToUpdate = {
        id_order: item["Order No"],
        restaurant: item["Restaurant"],
        status: item.Status,
        order_date: item.Date
    }

    const res = await useCancelOrder(objToUpdate)
    if (res.value.status != 200) {
        useToast().error(res.value.message)
        return
    }

    useToast().success('Order Cancelled')
    obj.value[obj.value.findIndex((e) => e["Order No"] == objToUpdate.id_order)].Status = res.value.order.status
}
</script>

<template>
    <div>
        <Header>
            <Title>Mis Pedidos</Title>
        </Header>
        <div>
            <LayoutTable :items="obj" @cancel="$e => cancelOrder($e)" :hasCancel="true" :toModal="['Products']"
                v-if="!isEmpty" />
            <h1 v-if="isEmpty" class="w-screen h-screen">
                <div class="min-w-full flex items-center justify-center min-h-full font-medium text-xl">
                    Sin items todavía
                </div>
            </h1>
        </div>
    </div>
</template>
