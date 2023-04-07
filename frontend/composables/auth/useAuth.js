import AuthService from "~~/services/auth/AuthService";

const clientCookie = useCookie('token_client')
const adminCookie = useCookie('token_admin')

export const useLogin = async (credentials) => {
    const loginData = ref({})

    try {
        const data = await AuthService.login({ email: credentials.email, password: credentials.contraseña })
        loginData.value = data
        clientCookie.value = data.token

        // TODO: Admin Token
    } catch (e) {
        console.log(e);
    }

    return loginData
}