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