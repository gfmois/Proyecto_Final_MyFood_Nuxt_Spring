import secret from "../secret"

export default {
    async getRestaurantHoldays(id_restaurant) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/holidays?id_restaurant=${id_restaurant}`)
            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async deleteHoliday(id_holiday) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/holidays`, {
                method: 'DELETE',
                headers: {
                    Authorization: `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ id_holiday })
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async updateHoliday(holiday) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/holidays`, {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(holiday)
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async createHoliday(holiday) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/holidays`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer ${useCookie('token_admin').value}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(holiday)
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
