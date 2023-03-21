<script setup>
const { product } = defineProps({
    product: Object
})
</script>

<template>
    <div class="flex flex-row bg-white rounded-lg p-2">
        <img :src="`/` + product.image" alt="Imagen del Producto" class="w-1/3 object-cover mr-4 rounded-lg">
        <div>
            <h4 class="text-xl font-bold mb-2">{{ product.name }}</h4>
            <p class="mb-2">{{ product.description || 'None' }}</p>
            <p class="font-bold">Precio: {{ product.price || '--,--' }}€</p>
        </div>
    </div>
</template>