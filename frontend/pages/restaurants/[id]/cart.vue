<script setup>
import { useShoppingCart } from "~~/store"

const { cart, removeItem } = useShoppingCart()

const openModal = ref(false)

const shippingCost = ref(4.99) // NOTE: This will be configured by the Restaurant on the admin panel
const totalCart = reactive(computed(() => {
    const _total = ref(0)
    cart.value.map((e) => {
        _total.value += (e.quantity * e.price)
    })

    return _total.value
}))

</script>

<template>
    <div>
        <Header>
            <Title>Carrito</Title>
        </Header>

        <div class="h-screen bg-gray-200 pt-20" v-if="cart.length > 0">
            <h1 class="mb-10 text-center text-2xl font-bold">Cart Items</h1>
            <div class="mx-auto max-w-5xl justify-center px-6 md:flex md:space-x-6 xl:px-0 w-full">
                <div class="rounded-lg md:w-2/3 w-1/2">
                    <div class="h-96 overflow-y-scroll noscrol">
                        <CartItemCard :removeAction="removeItem" :item="cartItem" v-bind:key="cartItem"
                            v-for="cartItem in cart" />
                    </div>
                </div>
                <!-- Sub total -->
                <div class="mt-6 h-full rounded-lg border bg-white p-6 shadow-md md:mt-0 md:w-1/3">
                    <div class="mb-2 flex justify-between">
                        <p class="text-gray-700">Subtotal</p>
                        <p class="text-gray-700">{{ totalCart }} €</p>
                    </div>
                    <div class="flex justify-between">
                        <p class="text-gray-700">Shipping</p>
                        <p class="text-gray-700">{{ shippingCost }} €</p>
                    </div>
                    <hr class="my-4" />
                    <div class="flex justify-between">
                        <p class="text-lg font-bold">Total</p>
                        <div class="">
                            <p class="mb-1 text-lg font-bold">{{ totalCart + shippingCost }} €</p>
                            <p class="text-sm text-gray-700">including IVA</p>
                        </div>
                    </div>
                    <button
                        class="mt-6 w-full rounded-md bg-crimson-500 py-1.5 font-medium text-blue-50 hover:bg-crimson-600"
                        @click="() => openModal = true">Check out</button>
                </div>
            </div>
        </div>

        <div v-if="cart.length == 0" class="h-screen bg-gray-200 pt-20 flex items-center justify-center">
            <div class="flex items-center justify-center flex-col gap-5">
                <p class="text-3xl uppercase">sin productos todavía</p>
                <NuxtLink class="bg-crimson-600 p-3  rounded-lg hover:bg-crimson-700 text-white hover:shadow-xl"
                    :to="`/restaurants/${$route.params.id}`">Volver atrás</NuxtLink>
            </div>
        </div>

        <CartCreditCardModal :isVisible="openModal" @closeModal="$e => openModal = false" />
</div></template>
