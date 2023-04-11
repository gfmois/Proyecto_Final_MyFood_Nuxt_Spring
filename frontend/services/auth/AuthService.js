import secret from "../secret";

export default {
    async login(credentials) {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/auth/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials)
            })

            return await response.json();
        } catch (error) {
            console.log(error);
            return null
        }
    },

    async checkIsAdmin() {
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/auth/admin`)
            return await response.json()
        } catch (error) {
            console.log(error)
            return null
        }
    }
}