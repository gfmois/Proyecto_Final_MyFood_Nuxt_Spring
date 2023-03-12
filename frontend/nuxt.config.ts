// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    app: {
        head: {
            link: [
                {
                    rel: "stylesheet",
                    href: "/css/global.css"
                },
            ],
        }
    },
    modules: [ "@nuxtjs/tailwindcss", "nuxt-icon" ]
})
