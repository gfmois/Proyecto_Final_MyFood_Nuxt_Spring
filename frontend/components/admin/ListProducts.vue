<script setup>
import { useToast } from 'vue-toast-notification';
import { useGetProductsByRestaurant, useUpdateProduct, useDeleteProduct } from "~/composables/products/useProducts"
import { useAuth } from "~/store"

const { user } = useAuth()
const toast = useToast({
    position: 'top-right',
    pauseOnHover: true,
})

const indexItem = ref(0)
const restaurant = await useGetProductsByRestaurant(user.value.id_restaurant)

restaurant.value.products.map((e) => {
    delete e.restaurant
})

const deleteProduct = async (product) => {
    const res = await useDeleteProduct(product[indexItem.value].id_product)
    if (res.value.status == 200) {
        useToast().success(res.value.message.message)
        return
    }

    useToast().error(res.value.message.message)
    const filteredProducts = restaurant.value.products.filter((e) => e.id_product != product[indexItem.value].id_product)
    restaurant.value = computed(() => ({ ...restaurant.value, products: filteredProducts })).value
}

const updateProduct = async (product) => {
    const objToUpdate = {
        id_product: product[0],
        name: product[1],
        slug: product[1].replaceAll(" ", "_"),
        desciption: product[3],
        price: product[4],
        file: product[5],
        id_restaurant: user.value.id_restaurant
    }

    const res = await useUpdateProduct(objToUpdate)
    if (res.value.status == 200) {
        useToast().success(res.value.message)
        return
    }

    useToast().error(res.value.message)
}

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems
            :key="restaurant.products"
            :object="restaurant.products"
            :hasActionButtons="true"
            :onlyIsAdmin="true"
            :toSee="['image', 'price', 'name']"
            :actionsKeys="user.type == 'ADMIN' ? ['Editar', 'Eliminar'] : ['Ver', '']"
            @indexCancel="$e => indexItem = $e"
            @cancel="$e => deleteProduct($e)"
            @update="$e => updateProduct($e)"
        />
    </div>
</template>
