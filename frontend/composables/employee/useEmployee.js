import EmployeeService from "~/services/employees/EmployeeService"

export const useGetEmployee = async () => {
    const profile = ref({})

    try {
        const data = await EmployeeService.getProfile()
        profile.value = data
    } catch (error) {
        console.log(error);
    }

    return profile
}
