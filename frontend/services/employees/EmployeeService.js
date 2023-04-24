import secret from "../secret";

export default {
    async getProfile() {
        const token = useCookie('token_admin')

        try {
            const response = await fetch(`${secret.DEFAULT_URL}/employees/profile`, {
                headers: {
                    'Authorization': `Bearer ${token.value}`
                }
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
