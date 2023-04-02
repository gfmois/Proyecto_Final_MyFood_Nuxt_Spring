<script setup>
const { cart, removeItem } = useShoppingCart()
const route = useRoute()

const { id } = route.params
</script>

<template>
    <div class="h-screen bg-gray-200 pt-20" v-if="cart.length > 0">
        <h1 class="mb-10 text-center text-2xl font-bold">Cart Items</h1>
        <div class="mx-auto max-w-5xl justify-center px-6 md:flex md:space-x-6 xl:px-0 w-full">
            <div class="rounded-lg md:w-2/3 w-1/2">
                <div class="h-96 overflow-y-scroll">
                    <CartItemCard  :removeAction="removeItem" :item="cartItem" v-for="cartItem in cart" />
                </div>
            </div>
            <!-- Sub total -->
            <div class="mt-6 h-full rounded-lg border bg-white p-6 shadow-md md:mt-0 md:w-1/3">
                <div class="mb-2 flex justify-between">
                    <p class="text-gray-700">Subtotal</p>
                    <p class="text-gray-700">$129.99</p>
                </div>
                <div class="flex justify-between">
                    <p class="text-gray-700">Shipping</p>
                    <p class="text-gray-700">$4.99</p>
                </div>
                <hr class="my-4" />
                <div class="flex justify-between">
                    <p class="text-lg font-bold">Total</p>
                    <div class="">
                        <p class="mb-1 text-lg font-bold">$134.98 USD</p>
                        <p class="text-sm text-gray-700">including VAT</p>
                    </div>
                </div>
                <button class="mt-6 w-full rounded-md bg-crimson-500 py-1.5 font-medium text-blue-50 hover:bg-crimson-600">Check out</button>
            </div>
        </div>
        <pre>{{ cart }}</pre>
    </div>

    <div v-if="cart.length == 0" class="h-screen bg-gray-200 pt-20 flex items-center justify-center">
        <div class="flex items-center justify-center flex-col gap-5">
            <p class="text-3xl uppercase">sin productos todavía</p>
            <NuxtLink class="bg-crimson-600 p-3  rounded-lg hover:bg-crimson-700 text-white hover:shadow-xl" :to="`/restaurants/${id}`">Volver atrás</NuxtLink>
        </div>
    </div>
</template>