import OrderService from "~~/services/orders/OrderService"

export const useCreateOrder = async (order) => {
    const orderRes = ref({})
    try {
        const data = await OrderService.createOrder(order)
        orderRes.value = data
    } catch (e) {
        console.log(e);
    }

    return orderRes
}

export const useGetRestaurantOrders = async () => {
    const orderRes = ref([])
    try {
        const data = await OrderService.getRestaurantOrders()
        orderRes.value = data
    } catch (error) {
        console.log(error);
    }

    return orderRes
}

export const useUpdateOrder = async(order) => {
    const orderRes = ref()
    try {
        const data = await OrderService.updateOrder(order)
        orderRes.value = data
    } catch (error) {
        console.log(error);
    }

    return orderRes
}
