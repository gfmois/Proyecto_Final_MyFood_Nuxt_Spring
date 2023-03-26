<script setup>
    const { isModalVisible, itemSelected, title } = defineProps({
        isModalVisible: false,
        itemSelected: Object,
        title: String
    })
</script>

<template>
    <div v-if="isModalVisible">
        {{ productSelected }}
        <div class="fixed top-0 left-0 p-8 w-screen h-screen z-[51]">
            <div class="bg-gray-100 rounded-lg p-4 w-full h-full">
                <div class="w-full flex items-center justify-between">
                    <h1 class="uppercase text-xl">{{ title }}</h1>
                    <Icon name="ic:baseline-close" size="2rem" class="cursor-pointer hover:border border-black rounded-lg" @click="$emit('closeModal', false)"/>
                </div>
                <div class="border-b border-gray-300 my-4" />
                <div class="grid grid-cols- md:grid-cols-2 gap-3 overflow-y-scroll box-border h-[90%]">
                    <slot/>
                </div>
            </div>
        </div>
        <div class="bg-black/50 w-screen h-screen z-50 fixed top-0"></div>
    </div>
</template>