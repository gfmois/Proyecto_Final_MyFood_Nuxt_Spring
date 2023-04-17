import secret from "../secret";

export default {
    // Get Holidays + Banned Days of the restaurant depending day, type and diners
    async getBannedDays(restaurantInfo) {
        try {
            const params = new URLSearchParams({
                diners: restaurantInfo.diners,
                type: restaurantInfo.type,
                id_restaurant: restaurantInfo.id_restaurant
            })

            const res = await fetch(`${secret.DEFAULT_URL}/reserves/available?${params}`)
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
    }
}