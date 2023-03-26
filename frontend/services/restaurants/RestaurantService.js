import s from "../secret"

export default {
  async getRestaurants() {
    try {
      const response = await fetch(`${s.DEFAULT_URL}restaurants`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getRestaurantById(id) {
    try {
      const response = await fetch(`${s.DEFAULT_URL}restaurants/${id}`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getNRestaurants() {
    try {
      const response = await fetch(`${s.DEFAULT_URL}restaurants/count`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  }
}