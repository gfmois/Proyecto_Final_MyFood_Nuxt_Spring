// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
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
    modules: [ "@nuxtjs/tailwindcss", "nuxt-icon" ],
})