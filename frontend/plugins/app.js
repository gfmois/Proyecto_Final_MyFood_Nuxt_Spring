import { useAuth } from "~~/store"
import { useGetProfile } from "~~/composables/client/useClient";

export default function ({ app }) {
    const { actChangeUser } = useAuth()
    
    // Checks if user has Token & if it ask for the profile to the server
    async function initializeUser() {
        if (useCookie('token_client').value) {
            actChangeUser((await useGetProfile()).value)
        }
    }

    initializeUser();
}