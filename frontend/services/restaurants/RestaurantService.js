import s from "../secret"

export default {
  async getRestaurants() {
    try {
      const response = await fetch(`${s.DEFAULT_URL}/restaurants`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getRestaurantsInfo() {
    try {
      const response = await fetch(`${s.DEFAULT_URL}/restaurants/info`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getRestaurantById(id) {
    try {
      const response = await fetch(`${s.DEFAULT_URL}/restaurants/${id}`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getRestaurantBySlug(slug) {
    try {
      const response = await fetch(`${s.DEFAULT_URL}/restaurants/${slug}`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getNRestaurants() {
    try {
      const response = await fetch(`${s.DEFAULT_URL}/restaurants/count`)
      return await response.json()
    } catch (error) {
      console.log(error)
      return null
    }
  },

  async getFilteredRestaurants(filters) {
    try {
      let url = `${s.DEFAULT_URL}/restaurants`

      Object.keys(filters).forEach((k, index) => {
        if (index == 0) {
          url += `?${k}=${filters[k]}`
        } else {
          url += `&${k}=${filters[k]}`
        }
      })

      console.log(url);

      const response = await fetch(url)

      return await response.json()

    } catch (error) {
      console.log(error);
      return null
    }
  }
}
