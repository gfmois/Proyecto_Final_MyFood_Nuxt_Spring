import secret from "../secret";
import { useAuth } from "~~/store"; 


export default {
    async login(credentials) {
        const { actChangeUser } = useAuth()
        try {
            const response = await fetch(`${secret.DEFAULT_URL}/auth/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials)
            })

            const user = await response.json();

            console.log(user);
            actChangeUser(user)
            return user;
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