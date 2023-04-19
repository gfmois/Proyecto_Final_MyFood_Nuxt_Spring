import secret from "../secret";

export default {
    async createOrder(order) {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/orders`, {
                headers: {
                    'Authorization': `Bearer ${useCookie('token_client').value}`,
                    'Content-Type': "application/json"
                },
                method: 'POST',
                body: JSON.stringify(order)
            })


            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}