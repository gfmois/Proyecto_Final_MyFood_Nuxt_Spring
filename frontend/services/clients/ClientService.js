import secret from "../secret";

export default {
    async getProfile(fromToken) {
        const token = useCookie('token_client')
        console.log(token.value, fromToken);

        try {
            const response = await fetch(`${secret.DEFAULT_URL}/client/profile`, {
                headers: {
                    'Authorization': `Bearer ${fromToken ? fromToken : token.value}`
                }
            })

            return await response.json()
        } catch (e) {
            console.log(e);
            return null
        }
    },
    async updateProfile (newProfile) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/client/profile`, {
                method: 'PUT',
                body: JSON.parse(newProfile)
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}