import AuthService from "~~/services/auth/AuthService"

const clientCookie = useCookie('token_client')
const adminCookie = useCookie('token_admin')

export const useAuth = () => {
    // State
    const hasUser = useState('hasUser', () => false)
    const isAdmin = useState('isAdmin', () => false)
    
    // Mutations
    const mutCheckIsAdmin = (valIsAdmin) => {
        isAdmin.value = valIsAdmin
    }
    const mutChangeHasUser = (valHasUser) => {
        hasUser.value = valHasUser
    }

    // Actions
    const actCheckIsAdmin = (payload) => {
        if (clientCookie.value) {
            AuthService.checkIsAdmin().then((res) => {
                if (res.status == "success") {
                    mutCheckIsAdmin(true)
                }
            }).catch((e) => {
                mutCheckIsAdmin(false)
                useRouter().push('/auth')
            })
        }
    }

    const actCheckHasUser = (payload) => {
        clientCookie.value
            ? mutChangeHasUser(true)
            : mutChangeHasUser(false)
    }

    const actChangeHasUser = (payload) => {
        mutChangeHasUser(payload)
    }

    return {
        hasUser,
        isAdmin,
        mutChangeHasUser,
        mutCheckIsAdmin,
        actChangeHasUser,
        actCheckHasUser,
        actCheckIsAdmin
    }
}