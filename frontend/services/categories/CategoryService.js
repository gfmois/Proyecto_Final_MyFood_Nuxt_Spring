import secret from "../secret"

export default {
    async getCategories() {
        try {
            const res = await fetch(`${secret.DEFAULT_URL}/categories`)
            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    }
}
