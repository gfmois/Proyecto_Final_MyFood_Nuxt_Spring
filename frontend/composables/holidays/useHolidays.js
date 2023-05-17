import HolidaysService from "~/services/holidays/HolidaysService";

export const useGetRestaurantHolidays = async (id_restaurant) => {
    const response = ref([])
    try {
        response.value = await HolidaysService.getRestaurantHoldays(id_restaurant)
    } catch (error) {
        console.log(error);
    }

    return response
}

export const useDeleteHoliday = async (id_holiday) => {
    const result = ref()
    try {
        result.value = await HolidaysService.deleteHoliday(id_holiday)
    } catch (error) {
        console.log(error);
    }

    return result
}

export const useUpdateHoliday = async (holiday) => {
    const result = ref({})
    try {
        result.value = await HolidaysService.updateHoliday(holiday)
    } catch (error) {
        console.log(error);
    }

    return result
}

export const useCreateHoliday = async (holiday) => {
    const result = ref({})
    try {
        result.value = await HolidaysService.createHoliday(holiday)
    } catch (error) {
        console.log(error);
    }

    return result
}
