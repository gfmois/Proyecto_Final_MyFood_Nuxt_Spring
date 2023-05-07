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

export const useGetRestaurantEmployees = async (id_restaurant) => {
    const employees = ref([])
    try {
        const data = await EmployeeService.getRestaurantEmployees(id_restaurant)
        employees.value = data
    } catch (error) {
        console.log(error);
    }

    return employees
}
