<script setup>
const { product, hasActionModal } = defineProps({
    product: Object,
    hasActionModal: Boolean
})

const isModalVisible = ref(false)
</script>

<template>
        <div class="flex flex-row bg-white rounded-lg p-2 hover:shadow-lg cursor-pointer" v-on:click="isModalVisible = true">
            <img :src="'/' + product.image" alt="Imagen del Producto" class="w-1/3 object-cover mr-4 rounded-lg">
            <div>
                <h4 class="text-xl font-bold mb-2">{{ product.name }}</h4>
                <p class="mb-2">{{ product.description || 'None' }}</p>
                <p class="font-bold">{{ $t('price') }}: {{ Number.parseFloat(product.price) || '--,--' }}€</p>
            </div>
        </div>
        <div v-if="hasActionModal" class="fixed">
            <BasicModal v-if="isModalVisible" actionBtnName="Añadir al carrito" showActionBtn="true" :item="product" class="z-[51]" @cancelAction="$e => isModalVisible = $e" />
        </div>
</template>