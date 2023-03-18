import RestaurantService from "~~/services/restaurants/RestaurantService";

export const useGetRestaurants = () => {
    const restaurants = ref()
    RestaurantService.getRestaurants()
        .then(({ data }) => {
            restaurants.value = data
        })

    return restaurants
}