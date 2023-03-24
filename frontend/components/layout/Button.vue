<script setup>
const { title, buttonType, customStyle } = defineProps({
    title: String,
    buttonType: String,
    customStyle: String,
    action: Promise
})

const styles = {
    default: "text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800",
    red: "text-white bg-red-700 hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900",
    green: "text-white bg-green-700 hover:bg-green-800 focus:outline-none focus:ring-4 focus:ring-green-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800",
    custom: "text-white focus:outline-none focus:ring-4 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2",
    empty_custom: ''
}

if (customStyle && buttonType == 'custom' || buttonType == 'custom_empty') {
    styles[buttonType] = customStyle == 'custom_empty' ? `${customStyle}` : `${styles["custom"]} ${customStyle}`
}
</script>

<template>
    <!-- FIXME: Slot can't be centered -->
    <div class="flex items-center justify-center relative">
        <button type="button"
            @click="action"
            :class="`${styles[buttonType] || styles['default']} !p-4`">{{ title }}</button>
            <slot />
    </div>
</template>