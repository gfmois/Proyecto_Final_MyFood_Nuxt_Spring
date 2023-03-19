<script setup lang="ts">
const seeDropdown: Ref<Boolean> = ref(false)
const { filterName } = defineProps({
    filterName: String,
    filterOptions: Array<String>
})
</script>

<template>
    <div class="relative inline-block text-left">
        <button type="button"
            v-on:click="() => seeDropdown = !seeDropdown"
            class="inline-flex items-center justify-center w-full px-4 py-2 bg-gray-200 rounded-full hover:bg-gray-300 transition-colors duration-300 ease-in-out"
            id="filter-menu" aria-haspopup="true" aria-expanded="false">
            {{ filterName }}
            <svg class="w-4 h-4 ml-2 -mr-1" viewBox="0 0 20 20" fill="currentColor">
                <path fill-rule="evenodd"
                    d="M6.293 7.293a1 1 0 011.414 0L10 9.586l2.293-2.293a1 1 0 111.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z"
                    clip-rule="evenodd" />
            </svg>
        </button>

        <div class="absolute left-0 mt-2 w-56 bg-white border border-gray-300 rounded-lg shadow-md divide-y divide-gray-100"
            v-if="seeDropdown"
            aria-labelledby="filter-menu" role="menu">
            <div class="py-1" role="none">
                <a href="#" 
                    v-for="option in filterOptions"
                    class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 hover:text-gray-900"
                    role="menuitem">{{ option }}</a>
            </div>
        </div>
    </div>
</template>