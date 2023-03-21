import { ref } from 'vue'
import RestaurantService from '~/services/restaurants/RestaurantService'

export const useGetRestaurants = async () => {
  const restaurants = ref([])
  try {
    const data = await RestaurantService.getRestaurants()
    restaurants.value = data
  } catch (error) {
    console.log(error)
  }
  return restaurants
}

export const useGetRestaurantsById = async (id) => {
  const restaurants = ref([])
  try {
    const data = await RestaurantService.getRestaurantById(id)
    restaurants.value = data
  } catch (error) {
    console.log(error)
  }
  return restaurants
}
