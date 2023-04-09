import AuthService from "~~/services/auth/AuthService"

export const useAuth = () => {
    const clientCookie = useCookie('token_client')
    const adminCookie = useCookie('token_admin')

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
    const actCheckIsAdmin = () => {
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

    const actCheckHasUser = () => {
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