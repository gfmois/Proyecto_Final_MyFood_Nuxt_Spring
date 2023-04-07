<script setup>

const { title, done, isLast } = defineProps({
    title: String,
    done: false,
    isLast: false
})

</script>

<template>
    <li
    :class="`flex md:w-full ${ done ? 'text-emerald-600 dark:text-emerald-500' : 'text-black dark:text-black' } items-center sm:after:content-[''] after:w-full after:h-1 ${ isLast ? '' : 'after:border-b' } after:border-gray-200 after:border-1 sm:after:inline-block after:mx-6 xl:after:mx-10 dark:after:border-gray-700`">
        <span
            class="flex items-center after:content-['/'] sm:after:hidden after:mx-2 after:text-gray-200 dark:after:text-gray-500">
            <svg v-if="done" aria-hidden="true" class="w-4 h-4 mr-2 sm:w-5 sm:h-5" fill="currentColor" viewBox="0 0 20 20"
                xmlns="http://www.w3.org/2000/svg">
                <path fill-rule="evenodd"
                    d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z"
                    clip-rule="evenodd"></path>
            </svg>
            <span class="hidden sm:inline-flex sm:ml-2">{{ title }}</span>
        </span>
    </li>
</template>