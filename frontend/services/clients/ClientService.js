import secret from "../secret";

export default {
    async getProfile() {
        const token = useCookie('token_client')

        try {
            const response = await fetch(`${secret.DEFAULT_URL}/client/profile`, {
                headers: {
                    'Authorization': `Bearer ${token.value}`
                }
            })

            return await response.json()
        } catch (e) {
            console.log(e);
            return null
        }
    },
    async updateProfile (newProfile) {
        const json = JSON.stringify(newProfile)
        const token = useCookie('token_client')

        try {
            const response = await fetch(`${secret.DEFAULT_URL}/client/profile`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token.value}`,
                    'Content-Type': 'application/json'
                },
                body: json
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async getClientReserves() {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/client/reserves`, {
                headers: {
                    'Authorization': `Bearer ${useCookie('token_client').value}`,
                    'Content-Type': 'application+/json'
                },
                method: 'GET'
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
