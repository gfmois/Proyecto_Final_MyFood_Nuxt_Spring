import AuthService from "~~/services/auth/AuthService"

export const useAuth = () => {
    const clientCookie = useCookie('token_client')

    // State
    const user = useState('user', () => {})
    const hasUser = useState('hasUser', () => false)
    const isAdmin = useState('isAdmin', () => false)

    // Mutations
    const mutCheckIsAdmin = (valIsAdmin) => {
        isAdmin.value = valIsAdmin
    }
    const mutChangeHasUser = (valHasUser) => {
        hasUser.value = valHasUser
    }

    const mutChangeUser = (userInfo) => {
        user.value = userInfo
    }

    const mutChangeIsAdmin = (value) => {
        isAdmin.value = value
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

        return hasUser.value
    }

    const actChangeHasUser = (payload) => {
        mutChangeHasUser(payload)
    }

    const actChangeIsAdmin = (payload) => {
        mutChangeIsAdmin(payload)
    }

    const actChangeUser = (user) => {
        mutChangeUser(user)
    }

    return {
        hasUser,
        isAdmin,
        user,
        mutChangeHasUser,
        mutCheckIsAdmin,
        mutChangeUser,
        mutChangeIsAdmin,
        actChangeHasUser,
        actCheckHasUser,
        actCheckIsAdmin,
        actChangeUser,
        actChangeIsAdmin
    }
}
