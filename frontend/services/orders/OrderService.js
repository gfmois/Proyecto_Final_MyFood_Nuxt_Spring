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
    },
    async getRestaurantOrders() {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/orders/restaurant`, {
                headers: {
                    'Authorization': `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': "application/json"
                },
                method: 'GET'
            })

            return await res.json()
        } catch (e) {
            console.log(e)
            return null
        }
    },
    async updateOrder(order) {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/orders`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': "application/json"
                },
                body: JSON.stringify(order)
            })

            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
