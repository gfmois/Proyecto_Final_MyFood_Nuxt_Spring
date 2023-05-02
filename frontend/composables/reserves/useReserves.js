import { ref } from "vue"
import ReserveService from "~~/services/reserves/ReserveService";

export const useGetBannedDays = async (restaurantInfo) => {
    const bannedDays = ref([])
    try {
        bannedDays.value = await ReserveService.getBannedDays(restaurantInfo)
    } catch (error) {
        console.log(error);
    }

    return bannedDays
}

export const useGetRestaurantReserves = async () => {
    const reserves = ref([])
    try {
        const data = await ReserveService.getRestaurantReserves()
        reserves.value = data
    } catch (error) {
        console.log(error);
    }

    return reserves
}

export const useUpdateReserve = async (reserve) => {
    const reserveUpdated = ref([])
    try {
        const data = await ReserveService.updateReserve(reserve)
        reserveUpdated.value = data
    } catch (error) {
        console.log(error);
    }

    return reserveUpdated
}

export const useCreateReserve = async (reserveInfo) => {
    const createResponse = ref({})
    try {
        createResponse.value = await ReserveService.createReserve(reserveInfo)
    } catch (error) {
        console.log(error)
    }

    return createResponse
}
