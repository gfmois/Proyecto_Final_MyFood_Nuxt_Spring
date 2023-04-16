import secret from "../secret";

export default {
    async createOrder(order) {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/orders`, {
                headers: {
                    'Authorization': `Bearer ${useCookie('token_client').value}`
                },
                method: 'POST',
                body: order
            })
        } catch (error) {
            console.log(error);
            return null
        }
    }
}