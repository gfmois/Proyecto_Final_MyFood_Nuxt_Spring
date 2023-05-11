import AuthService from "~~/services/auth/AuthService";
import { useAuth } from "~~/store";

const clientCookie = useCookie('token_client')
const adminCookie = useCookie('token_admin')

const { actChangeHasUser, actChangeIsAdmin } = useAuth()

export const useLogin = async (credentials) => {
    const loginData = ref({})

    try {
        const data = await AuthService.login({ email: credentials.email, password: credentials.contraseña })
        loginData.value = data

        if (data.status != 500) {
            console.log(data);
            if (data.user_type == "NONE") {
                clientCookie.value = data.token
            } else {
                adminCookie.value = data.token
                actChangeIsAdmin(data.user_type != 'NONE')
            }

            actChangeHasUser(true)
        }

        // TODO: Admin Token
    } catch (e) {
        console.log(e);
    }

    return loginData
}

export const useRegister = async (credentials) => {
    const loginData = ref({})

    try {
        const user = {
            email: credentials.email,
            password: credentials.contraseña || credentials.password,
            phone: credentials["teléfono"] || credentials.phone,
            name: credentials.nombre || credentials.name
        }

        if (credentials.id_restaurant && credentials.type) {
            user.id_restaurant = credentials.id_restaurant
            user.type = credentials.type
        }

        const data = await AuthService.register(user)
        loginData.value = data
        clientCookie.value = data.token
        actChangeHasUser(true)

        // TODO: Admin Token
    } catch (e) {
        console.log(e);
    }

    return loginData
}
