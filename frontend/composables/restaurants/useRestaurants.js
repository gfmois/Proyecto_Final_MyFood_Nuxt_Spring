import RestaurantService from "~~/services/restaurants/RestaurantService";

export const useGetRestaurants = () => {
    const restaurants = ref()
    RestaurantService.getRestaurants()
        .then(({ data }) => {
            restaurants.value = data
        })

    return restaurants
}

export const useGetRestaurantById = (id) => {
    const restaurant = ref()
    RestaurantService.getRestaurantById(id)
        .then(({ data }) => {
            restaurant.value = data
        })
        .catch((e) => {
            console.log(e);
        })

    return restaurant
}