import i18n from "./config/i18n/i18n"

export default defineNuxtConfig({
    i18n,
    css: ['vue-toast-notification/dist/theme-sugar.css'],
    app: {
        head: {
            link: [
                { rel: "stylesheet", href: "/css/global.css" },
            ],
        }
    },
    modules: [ 
        "@nuxtjs/tailwindcss", 
        "nuxt-icon", 
        '@nuxtjs/i18n',
        '@nuxt/devtools'
    ]
})