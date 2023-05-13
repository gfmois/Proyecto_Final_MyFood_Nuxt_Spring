<script setup>
import { useToast } from 'vue-toast-notification';
import { useGetRestaurantByEmployee } from '~/composables/restaurants/useRestaurants';
import { useGetProductsByRestaurant, useUpdateProduct } from "~/composables/products/useProducts"
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

const deleteProduct = (product) => {
    console.log(product[indexItem.value]);
}

const updateProduct = async (product) => {
    console.log(product);
    const objToUpdate = {
        id_product: product[0],
        name: product[1],
        slug: product[1].replaceAll(" ", "_"),
        desciption: product[3],
        price: product[4],
        file: product[5],
        id_restaurant: user.value.id_restaurant
    }

    console.log(objToUpdate);

    const res = await useUpdateProduct(objToUpdate)
    console.log(res.value);
}

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems
            :object="restaurant.products"
            :hasActionButtons="true"
            :onlyIsAdmin="true"
            :toSee="['image', 'price', 'name']"
            :actionsKeys="['Editar', 'Eliminar']"
            @indexCancel="$e => indexItem = $e"
            @cancel="$e => deleteProduct($e)"
            @update="$e => updateProduct($e)"
        />
    </div>
</template>
