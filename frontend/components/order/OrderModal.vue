<script setup>
import { useShoppingCart } from "~~/store"

const store = useShoppingCart()
const total = ref(0)

const { quantity, product } = defineProps({
    product: Object,
})

const hasUser = ref(useCookie('token_client').value)

// Add Quantity to product and save it in the Cart Store
const addQuantity = () => {
    product.quantity++
    store.setItem(product)

    addToTotal()
}

// Check the quantity of the product in the cart and remove it from the cart store if needed
const lessQuantity = () => {
    if (product.quantity >= 1) {
        product.quantity--
        if (product.quantity == 0)
            store.removeItem(product)
    }

    addToTotal()
}

const addToTotal = () => {
    let _total = 0
    store.cart.value.map((product) => {
        for (let i = 0; i < product.quantity; i++) {
            console.log(parseInt(product.price));
            _total += parseInt(product.price)
        }
    })

    total.value = _total
}
</script>

<template>
    <div>
        <div
            class="z-[61] fixed xs:top-10 md:top-0 left-0 right-0 w-full md:w-full xs:h-3/4 p-4 overflow-x-hidden overflow-y-auto md:inset-0 flex items-center justify-center">
            <div class="xs:w-full md:w-1/3 h-full rounded-lg p-6 bg-white">
                <div class="flex items-center justify-between">
                    <h1 class="uppercase">{{ product.name }}</h1>
                    <Icon name="ic:baseline-close" size="2rem" class="cursor-pointer hover:border border-black rounded-lg"
                        @click="$emit('closeModal', false)" />
                </div>
                <div class="border-b border-gray-300 my-4" />
                <div class="h-3/4 w-full flex flex-col gap-3">
                    <img :src="`/${product.image}`" alt="Product Image">
                    <p class="p-2">
                        {{ product.description }}
                    </p>
                </div>
                <div class="border-b border-gray-300 my-4" />
                <div v-if="!hasUser" class="flex items-center justify-center">
                    <div class="bg-green-600 p-3 rounded mt-8 text-white hover:shadow-xl cursor-pointer"
                        @click="() => $router.replace('/auth')">Iniciar Sesión</div>
                </div>
                <div class="flex flex-col justify-center items-center gap-2">
                    <div class="flex flex-row items-center justify-center" v-if="hasUser">
                        <!-- Chane it to an span or something like that -->
                        <LayoutButton :disabled="product.quantity == 0" button-type="red" title="-"
                            :showOptionsButton="product.quantity == 1 ? true : false" iconName="mdi-light:delete"
                            :action="() => lessQuantity()" />
                        <LayoutButton button-type="yellow" :title="product.quantity" />
                        <!-- Chane it to an span or something like that -->
                        <LayoutButton button-type="green" title="+" :action="() => addQuantity()" />
                    </div>
                </div>
            </div>
        </div>
        <div class="h-screen w-screen bg-black/50 z-40 fixed left-0 top-0" />
    </div>
</template>
