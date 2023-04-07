import { useAuth } from "~~/store"
export default function ({ app }) {
    const { actCheckHasUser,  actCheckIsAdmin } = useAuth()
    // Define una función que quieres lanzar al inicio de la aplicación
    function initializeUser() {
        actCheckHasUser()
        actCheckIsAdmin()
    }

    // Lanza la función `miFuncion()` al inicio de la aplicación
    initializeUser();
}