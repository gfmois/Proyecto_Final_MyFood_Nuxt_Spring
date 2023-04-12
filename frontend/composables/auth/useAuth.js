import AuthService from "~~/services/auth/AuthService";
import { useAuth } from "~~/store";

const clientCookie = useCookie('token_client')
const adminCookie = useCookie('token_admin')

const { actChangeHasUser } = useAuth()

export const useLogin = async (credentials) => {
    const loginData = ref({})

    try {
        const data = await AuthService.login({ email: credentials.email, password: credentials.contraseña })
        loginData.value = data
        clientCookie.value = data.token
        actChangeHasUser(true)

        // TODO: Admin Token
    } catch (e) {
        console.log(e);
    }

    return loginData
}