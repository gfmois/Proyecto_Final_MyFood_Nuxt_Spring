import secret from "../secret";

export default {
    async getProfile() {
        const token = useCookie('token_admin')

        try {
            const response = await fetch(`${secret.DEFAULT_URL}/employees/profile`, {
                headers: {
                    'Authorization': `Bearer ${token.value}`
                }
            })

            return await response.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async getRestaurantEmployees(id_restaurant) {
            const token = useCookie("token_admin").value

            try {
                const res = await fetch(`${secret.DEFAULT_URL}/employees/restaurant?id_restaurant=${id_restaurant}`, {
                    method: 'GET',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                })

                return await res.json()
            } catch (error) {
                return null
            }
    },
    async deleteEmployee(id_employee) {
        const token = useCookie('token_admin').value

        console.log(id_employee);

        try {
            const res = await fetch(`${secret.DEFAULT_URL}/employees`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ id_employee })
            })

            return await res.json()
        } catch (error) {
            console.log(error);
            return null
        }
    },
    async updateEmployee(employee) {
        const token = useCookie('token_admin').value

        try {
            const res = await fetch(`${secret.DEFAULT_URL}/employees`, {
                method: 'PUT',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': "application/json"
                },
                body: JSON.stringify(employee)
            })

            return await res.json()
        } catch (error) {
            console.log(error)
            return null
        }
    }
}
