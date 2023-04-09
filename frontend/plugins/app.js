import { useAuth } from "~~/store"

export default function ({ app }) {
    const { actCheckHasUser,  actCheckIsAdmin } = useAuth()

    // TODO: Endpoints
    function initializeUser() {
        // actCheckHasUser()
        // actCheckIsAdmin()
    }

    initializeUser();
}