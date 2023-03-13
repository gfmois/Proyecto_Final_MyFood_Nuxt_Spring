<script setup>
    const { inputType, inputPlaceholder, inputLabel } = defineProps({
        inputPlaceholder: String,
        inputType: String,
        inputLabel: String
    })
</script>

<template>
    <div>
        <label for="inputItem" class="block float-left ml-1 text-sm text-black">{{ inputLabel }}</label>
        <input :type="inputType" id="inputItem" :placeholder="inputPlaceholder" class="block text-sm p-2 w-full placeholder-gray-400/70 dark:placeholder-gray-500 rounded-lg border border-gray-200 bg-white px-5 text-gray-700 focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40 dark:border-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:focus:border-blue-300" />
    </div>
</template>