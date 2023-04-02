<script setup>
const { item, removeAction } = defineProps({
    item: Object,
    removeAction: Promise
})

const total = reactive(computed(() => item.price * item.quantity))

const lessQuantity = () => {
    if (item.quantity >= 1) {
        item.quantity--
        if (item.quantity == 0)
            removeAction(item)
    }
}

const moreQuantity = () => {
    item.quantity++
}
</script>

<template>
    <div class="justify-between mb-6 rounded-lg bg-white p-6 shadow-md sm:flex sm:justify-start">
        <img :src="`/${item.image}`"
            alt="product-image" class="w-full rounded-lg sm:w-40" />
        <div class="sm:ml-4 sm:flex sm:w-full sm:justify-between">
            <div class="mt-5 sm:mt-0">
                <h2 class="text-lg font-bold text-gray-900">{{ item.name }}</h2>
                <p class="mt-1 text-xs text-gray-700"></p>
            </div>
            <div class="mt-4 flex justify-between items-center sm:space-y-6 sm:mt-0 sm:block sm:space-x-6">
                <div class="flex items-center border-gray-100">
                    <span class="cursor-pointer rounded-l bg-gray-100 py-1 px-3.5 duration-100 hover:bg-blue-500 hover:text-blue-50" @click="() => lessQuantity()"> <Icon name="ic:baseline-minus" /> </span>
                    <input class="h-8 w-8 border bg-white text-center text-xs outline-none" type="number" :value="item.quantity" min="0" />
                    <span class="cursor-pointer rounded-r bg-gray-100 py-1 px-3 duration-100 hover:bg-blue-500 hover:text-blue-50" @click="() => moreQuantity()"> <Icon name="ic:baseline-plus" /> </span>
                </div>
                <div class="flex items-center space-x-4">
                    <p class="text-sm">{{ total }}€</p>
                    <div class="border-2 hover:border-red-300 rounded-lg" @click="() => removeAction(item)">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                        stroke="currentColor" class="h-5 w-5 cursor-pointer duration-150 hover:text-red-500">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                    </svg>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>