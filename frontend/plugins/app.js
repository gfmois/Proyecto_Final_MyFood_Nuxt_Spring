import { useAuth } from "~~/store"
import { useGetProfile } from "~~/composables/client/useClient";
import { useGetEmployee } from "~~/composables/employee/useEmployee";

export default function ({ app }) {
    const { actChangeUser } = useAuth()

    // Checks if user has Token & if it ask for the profile to the server
    async function initializeUser() {
        if (useCookie('token_admin').value) {
            actChangeUser((await useGetEmployee()).value)
            return
        }

        if (useCookie('token_client').value) {
            actChangeUser((await useGetProfile()).value)
            return
        }
    }

    initializeUser();
}
