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