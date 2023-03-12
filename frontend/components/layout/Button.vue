<script setup>
const { title, buttonType, customStyle } = defineProps({
    title: String,
    buttonType: String,
    customStyle: String
})

const styles = {
    default: "text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800",
    red: "text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900",
    green: "text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800",
}

if (customStyle && buttonType == 'custom') {
    styles["custom"] = `${styles["default"]} ${customStyle}`
}
</script>

<template>
    <div>
        <button type="button"
            :class="`${styles[buttonType] || styles['default']} !p-4`">{{ title }}</button>
    </div>
</template>