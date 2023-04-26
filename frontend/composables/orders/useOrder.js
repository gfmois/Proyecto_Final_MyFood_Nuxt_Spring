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

export const useGetRestaurantOrders = async (restaurant) => {
    const orderRes = ref([])
    try {
        const data = await OrderService.getRestaurantOrders()
        orderRes.value = data
    } catch (error) {
        console.log(error);
    }

    return orderRes
}
