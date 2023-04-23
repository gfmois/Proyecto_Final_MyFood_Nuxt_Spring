<script setup>
import { useGetRestaurantsById } from '~~/composables/restaurants/useRestaurants';
import { useShoppingCart } from "~~/store"

const route = useRoute()
const store = useShoppingCart()

const { id } = route.params

const isModalVisible = ref(false)
const productSelected = ref(null)
const reserveModal = ref(false)
const token_client = useCookie('token_client').value

const data = reactive(await useGetRestaurantsById(id));

data.value.products.map((e) => {
    e.quantity = 0;
})

const totalToPay = reactive(computed(() => {
    const _total = ref(0)
    store.cart.value.map((item) => {
        _total.value += (item.quantity * item.price)
    })

    return _total.value
}))

// FIXME: This make an petition and delays the page
// const locationRes = await $fetch("https://trueway-geocoding.p.rapidapi.com/ReverseGeocode", {
//     query: { location: `${data.value.restaurant.lat},${data.value.restaurant.lng}`, lenguage: 'en' },
//     headers: {
//         'X-RapidAPI-Key': import.meta.env.VITE_RAPID_API_KEY,
//         'X-RapidAPI-Host': import.meta.env.VITE_RAPID_API_HOST
//     }
// })
</script>

<template>
    <Header>
        <Title>{{ data.restaurant.name || 'Restaurante' }}</Title>
    </Header>

    <div class="flex items-start flex-col justify-center relative">
        <section class="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div class="md:col-span-2">
                <img :src="`/${data.restaurant.image}`" alt="Imagen del Restaurante" class="w-full h-full object-cover">
            </div>
            <div class="p-5">
                <h2 class="text-3xl font-bold mb-4">{{ data.restaurant.name }}</h2>
                <div class="mb-4 flex flex-row gap-1">
                    <p class="font-bold">{{ $t('direction') }}:</p>
                    <p>Not Found</p><!-- locationRes.results[0].address.split(',')[0] || -->
                </div>
                <div class="mb-4 flex flex-row gap-1">
                    <p class="font-bold">{{ $t('phone') }}: </p>
                    <p> 123-456-7890</p>
                </div>
                <p class="mb-4 font-bold">{{ $t('schedule') }}:</p>
                <ul class="list-disc list-inside mb-4">
                    <li>Lunes a Viernes: 12pm - 9pm</li>
                    <li>Sábados y Domingos: 10am - 10pm</li>
                </ul>
                <p class="mb-4 font-bold">{{ $t('description') }}:</p>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam convallis eget arcu eu aliquet. In ac
                    nibh a
                    ante finibus eleifend vel vitae velit. Pellentesque habitant morbi tristique senectus et netus et
                    malesuada
                    fames ac turpis egestas. Vestibulum hendrerit, nunc nec faucibus eleifend, sapien lectus facilisis
                    sapien,
                    quis ultrices enim est in est. Praesent laoreet, dui in ullamcorper faucibus, justo leo auctor mi, sit
                    amet
                    faucibus velit est in libero. Proin et ullamcorper nulla. </p>
                <div class="mt-8 flex items-center justify-start p-2 flex-row gap-1">
                    <LayoutButton button-type="custom"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600"
                        :title="$t('reserve')" :action="() => reserveModal = true" />
                    <LayoutButton button-type="custom"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600"
                        :title="$t('make_order')" :action="() => isModalVisible = true" />
                    <!-- <LayoutButton button-type="custom"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600"
                        :title="$t('make_event')" /> -->
                    <LayoutButton button-type="custom" showOptionsButton="true"
                        custom-style="rounded-none dark:bg-crimson-500 dark:text-black ring-crimson-600" />
                </div>
            </div>
        </section>
        <section class="my-8 p-4 w-full">
            <div class="bg-crimson-600 text-white w-full">
                <h3 class="text-2xl font-bold mb-4 text-center p-2">Productos</h3>
            </div>
            <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8 p-2">
                <ProductCard :hasActionModal="true" :product="product" v-for="product in data.products" v-if="data.products.length > 0" />
                <div v-if="data.products.length == 0" class="text-center w-full bg-red-400">Sin productos todavía</div>
            </div>
        </section>

        <ProductCart :items="store.cart" />

        <AuthGoLoginModal :show="reserveModal && !token_client || isModalVisible && !token_client" @close="$e => { reserveModal = false; isModalVisible = false; }" />
        <ReserveModal :isModalVisible="reserveModal && token_client" @isVisible="$e => reserveModal = $e" />

        <ActionModal :isModalVisible="isModalVisible && token_client" @itemClicked="$e => productSelected = $e"
            :hasTotal="true"
            :total="totalToPay"
            @closeModal="$e => isModalVisible = $e" title="Realizar Pedido">
            <div class="w-fit h-fit" v-for="product in data.products" v-if="data.products.length > 0">
                <ProductCard :hasActionModal="true" :product="product" />
            </div>
        </ActionModal>
    </div>
</template>
