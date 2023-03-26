<script setup>
const { quantity, product } = defineProps({
    product: Object
})
</script>

<template>
    <div class="z-[61] fixed xs:top-10 md:top-0 left-0 right-0 w-full md:w-full xs:h-3/4 p-4 overflow-x-hidden overflow-y-auto md:inset-0 flex items-center justify-center">
        <div class="xs:w-full md:w-1/3 h-full rounded-lg p-6 bg-white">
            <div class="flex items-center justify-between">
                <h1 class="uppercase">{{ product.name }}</h1>
                <Icon name="ic:baseline-close" size="2rem" class="cursor-pointer hover:border border-black rounded-lg" @click="$emit('closeModal', false)"/>
            </div>
            <div class="border-b border-gray-300 my-4" />
            <div class="h-3/4 w-full flex flex-col gap-3">
                <img :src="`/${product.image}`" alt="Product Image">
                <p class="p-2">
                    {{ product.description }}
                </p>
            </div>
            <div class="border-b border-gray-300 my-4" />
            <div class="flex flex-row justify-center items-center">
                <LayoutButton button-type="red" title="-" :action="product.quantity > 0 ? () => product.quantity-- : null" />
                <LayoutButton button-type="yellow" :title="product.quantity" /> <!-- Chane it to an span or something like that -->
                <LayoutButton button-type="green" title="+" :action="() => product.quantity++" />

            </div>
        </div>
    </div>
    <div class="h-screen w-screen bg-black/50 z-40 fixed left-0 top-0"/>
</template>