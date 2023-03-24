<script setup>
const { product } = defineProps({
    item: Object,
    showActionBtn: false,
    actionBtnName: String
})
</script>

<template>
    <div id="defaultModal" tabindex="-1" aria-hidden="true"
        class="z-60 fixed top-0 left-0 right-0 w-full p-4 overflow-x-hidden overflow-y-auto md:inset-0 flex items-center justify-center">
        <div class="relative w-full h-full max-w-2xl md:h-auto z-50">
            <!-- Modal content -->
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <!-- Modal header -->
                <div class="flex items-start justify-between p-4 border-b rounded-t dark:border-gray-600">
                    <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                        {{ item.name || $t('not_found') }}
                    </h3>
                    <button type="button" v-on:click="$emit('cancelAction', false)"
                        class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white"
                        data-modal-hide="defaultModal">
                        <svg aria-hidden="true" class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20"
                            xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd"
                                d="M4.293 4.293a1 1 0 011.414 0L10 8.586l4.293-4.293a1 1 0 111.414 1.414L11.414 10l4.293 4.293a1 1 0 01-1.414 1.414L10 11.414l-4.293 4.293a1 1 0 01-1.414-1.414L8.586 10 4.293 5.707a1 1 0 010-1.414z"
                                clip-rule="evenodd"></path>
                        </svg>
                        <span class="sr-only">Close modal</span>
                    </button>
                </div>
                <!-- Modal body -->
                <div class="p-6 space-y-6">
                    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
                        {{ item.description || item.body }}
                    </p>
                </div>
                <!-- Modal footer -->
                <div class="flex items-center p-6 space-x-2 border-t justify-end border-gray-200 rounded-b dark:border-gray-600">
                    <LayoutButton v-if="showActionBtn" :title="actionBtnName" button-type="custom_empty"
                        custom-style="text-white bg-yellow-700 hover:bg-yellow-800 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm text-center dark:bg-yellow-600 dark:hover:bg-yellow-700 dark:focus:ring-yellow-800" />
                    <LayoutButton :title="$t('close')" button-type="custom_empty"
                        @click="$emit('cancelAction', false)"
                        custom-style="text-white bg-crimson-700 hover:bg-crimson-800 focus:ring-4 focus:outline-none focus:ring-crimson-300 font-medium rounded-lg text-sm text-center dark:bg-crimson-600 dark:hover:bg-crimson-700 dark:focus:ring-crimson-800" />
                </div>
            </div>
        </div>
        <div class="h-screen w-screen bg-black/50 z-40 fixed left-0 top-0"></div>
    </div>
</template>