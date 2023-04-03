<script setup>
const { isModalVisible, itemSelected, title, hasTotal, total } = defineProps({
    isModalVisible: false,
    itemSelected: Object,
    title: String,
    hasTotal: Boolean,
    total: String
})

</script>

<template>
    <div v-if="isModalVisible">
        {{ productSelected }}
        <div class="fixed top-0 left-0 p-8 w-screen h-screen z-[51]">
            <div class="bg-gray-100 rounded-lg p-4 w-full h-full relative">
                <div class="w-full flex items-center justify-between">
                    <h1 class="uppercase text-xl">{{ title }}</h1>
                    <Icon name="ic:baseline-close" size="2rem" class="cursor-pointer hover:border border-black rounded-lg"
                        @click="$emit('closeModal', false)" />
                </div>
                <div class="border-b border-gray-300 my-4" />
                <div
                    :class="`grid grid-cols- md:grid-cols-2 gap-3 overflow-y-scroll box-border ${hasTotal ? 'h-[80%]' : 'h-[90%]'}`">
                    <slot />
                </div>
                <div class="bg-crimson-600 h-20 w-full absolute bottom-5 p-4 left-0 text-white flex items-center justify-end gap-4"
                    v-if="hasTotal">
                    <div class="flex items-center justify-center gap-2 p-3 text-lg rounded-lg bg-crimson-700">
                        <h1 class="uppercase">Total: </h1>
                        <p>{{ total }}€</p>
                    </div>
                    <NuxtLink :to="`/restaurants/${$route.params.id}/cart`" class="flex items-center justify-center gap-2 p-4 hover:shadow-xl text-lg mr-20 rounded-lg bg-crimson-700 cursor-pointer">
                        <Icon name="grommet-icons:cart" />
                    </NuxtLink>
                </div>
            </div>
        </div>
        <div class="bg-black/50 w-screen h-screen z-50 fixed top-0" />
    </div>
</template>