import ClientService from "~~/services/clients/ClientService"

export const useGetProfile = async () => {
    const profile = ref({})

    try {
        const data = await ClientService.getProfile()
        profile.value = data
    } catch (error) {
        console.log(error);
    }

    return profile
}

export const useUpdateProfile = async (newProfile) => {
    const profile = ref({})
    try {
        const data = await ClientService.updateProfile(newProfile)
        profile.value = data
    } catch (error) {
        console.log(error);
    }

    return profile
}

export const useGetClientReserves = async () => {
    const reserves = ref([])
    try {
        const data = await ClientService.getClientReserves()
        reserves.value = data
    } catch (error) {
        console.log(error);
    }

    return reserves
}

export const useCancelReserve = async (reserve) => {
    const reserveUpdated = ref({})
    try {
        const data = await ClientService.updateReserve(reserve)
        reserveUpdated.value = data
    } catch (error) {
        console.log(error);
    }

    return reserveUpdated
}
