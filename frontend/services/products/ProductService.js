import s from "../secret"

export default {
    async getProducts() {
        try {
            const response = await fetch(`${s.DEFAULT_URL}/products`)
            return await response.json()
          } catch (error) {
            console.log(error)
            return null
          }
    },
    async getRestaurantProducts(id_restaurant) {
        try {
            const response = await fetch(`${s.DEFAULT_URL}/restaurants`)
            return await response.json()
          } catch (error) {
            console.log(error)
            return null
          }
    }
}