// https://nuxt.com/docs/api/configuration/nuxt-config
import i18n from "./config/i18n/i18n"

export default defineNuxtConfig({
    i18n,
    app: {
        head: {
            link: [
                {
                    rel: "stylesheet",
                    href: "/css/global.css"
                },
                // {
                //     rel: "stylesheet",
                //     href: "'https://fonts.googleapis.com/css2?family=Cormorant+Garamond:wght@300&display=swap"
                // }
            ],
        }
    },
    modules: [ 
        "@nuxtjs/tailwindcss", 
        "nuxt-icon", 
        '@nuxtjs/i18n'
    ],
})