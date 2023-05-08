import secret from "../secret";

export default {
    // Get Holidays + Banned Days of the restaurant depending day, type and diners
    async getBannedDays(restaurantInfo) {
        try {
            const params = new URLSearchParams({
                diners: restaurantInfo.diners,
                types: restaurantInfo.type,
                id_restaurant: restaurantInfo.id_restaurant
            })

            const res = await fetch(`${secret.DEFAULT_URL}/reserves/available?${params}`)
            return await res.json()

        } catch (error) {
            console.log(error);
            return null
        }
    },
    async getRestaurantReserves() {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/reserves/restaurant`, {
                headers: {
                    'Authorization': `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': "application/json"
                },
                method: 'GET'
            })

            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async createReserve(restaurantInfo) {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/reserves`, {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${useCookie('token_client').value}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(restaurantInfo.value)
            })

            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async updateReserve(reserve) {
        const json = JSON.stringify(reserve)
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/reserves`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': "application/json"
                },
                body: json
            })

            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
