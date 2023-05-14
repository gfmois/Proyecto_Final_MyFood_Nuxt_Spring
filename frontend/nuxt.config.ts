import i18n from "./config/i18n/i18n"

export default defineNuxtConfig({
    i18n,
    css: ['vue-toast-notification/dist/theme-sugar.css'],
    routeRules: {
        '/reserves/**': { ssr: false }
    },
    app: {
        head: {
            link: [
                { rel: "stylesheet", href: "/css/global.css" },
            ],
        }
    },
    build: {
        transpile: ['chart.js']
    },
    modules: [
        "@nuxtjs/tailwindcss",
        '@nuxtjs/i18n',
        '@nuxt/devtools',
        "nuxt-icon",
    ]
})
