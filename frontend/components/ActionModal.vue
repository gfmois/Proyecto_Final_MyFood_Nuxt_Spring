<script setup>
    const { isModalVisible } = defineProps({
        isModalVisible: false
    })
</script>

<template>
    <div v-if="isModalVisible">
        <div class="fixed top-0 left-0 p-8 w-screen h-screen z-[51]">
            <div class="bg-gray-100 rounded-lg p-4 w-full h-full">
                <div class="w-full flex items-center justify-between">
                    <h1 class="uppercase text-xl">Realizar Pedido</h1>
                    <Icon name="ic:baseline-close" size="2rem" class="cursor-pointer hover:border border-black rounded-lg" @click="$emit('closeModal', false)"/>
                </div>
                <div class="border-b border-gray-300 my-4" />
                <div class="flex flex-wrap xs:gap-2 md:gap-0 overflow-y-scroll box-border h-[90%]">
                    <div class="w-full lg:w-1/4 px-4">
                      <div class="h-24">
                        <ProductCard :product="{ image: 'images/products/35bd4e4a82_1679740197616.jpeg', id_product: '', name: 'Tortilla de Patatas', price: '34', description: 'AAAAA' }" :isModalVisible="false" />
                      </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="bg-black/50 w-screen h-screen z-50 fixed top-0"></div>
    </div>
</template>