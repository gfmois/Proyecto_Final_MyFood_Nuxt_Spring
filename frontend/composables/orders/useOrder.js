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

export const useGetClientOrders = async () => {
    const clientOrders = ref([])
    try {
        const data = await OrderService.getClientOrders()
        clientOrders.value = data
    } catch (error) {
        console.log(error);
    }

    return clientOrders;
}

export const useCancelOrder = async (order) => {
    const orderUpdated = ref({})
    try {
        const data = await OrderService.cancelOrder(order);
        orderUpdated.value = data
    } catch (error) {
        console.log(error);
    }

    return orderUpdated
}
