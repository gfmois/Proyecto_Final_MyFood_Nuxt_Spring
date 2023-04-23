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

export const useGetRestaurantsInfo = async () => {
  const restaurants = ref([])
  try {
    const data = await RestaurantService.getRestaurantsInfo()
    restaurants.value = data
  } catch (error) {
    console.log(error)
  }
  return restaurants
}

export const useGetNRestaurants = async () => {
  const restaurants = ref([])
  try {
    const data = await RestaurantService.getNRestaurants()
    restaurants.value = data
  } catch (error) {
    console.log(error)
  }
  return restaurants
}

export const useGetNOrders = async () => {
  const orders = ref()
  try {
    const data = await RestaurantService.getNOrders()
    orders.value = data
  } catch (error) {
      console.log(error);
  }

  return orders
}

export const useGetFilteredRestaunrats = async (filters) => {
  const restaurants = ref([])
  try {
      const data = await RestaurantService.getFilteredRestaurants(filters)
      restaurants.value = data
  } catch (error) {
    console.log(error);
  }

  return restaurants
}
