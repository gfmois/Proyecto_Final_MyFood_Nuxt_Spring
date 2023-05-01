<script setup>
import { Stripe } from "stripe"
import { useShoppingCart } from "~/store"
import { useCreateOrder } from "~~/composables/orders/useOrder";
import { useToast } from 'vue-toast-notification';

const stripe = new Stripe(import.meta.env.VITE_STRIPE_SECRET_KEY)
const emit = defineEmits()

const isLoading = ref(false)
const toast = useToast({
    position: 'top-right',
    pauseOnHover: true,
})

const { isVisible } = defineProps({
    isVisible: false
})

const { cart } = useShoppingCart()

const expirationDate = ref(null)

const obj = reactive({
    name: "",
    number: "4000056655665556",
    expiration: "2025-08",
    cvc: "729"
})

const loadObject = async () => {
    isLoading.value = true
    try {
        const splittedData = obj.expiration.split("-");
        const { id } = await stripe.tokens.create({
            card: {
                number: obj.number,
                cvc: obj.cvc,
                exp_month: splittedData[1],
                exp_year: splittedData[0],
                name: obj.name
            }
        })

        const res = useCreateOrder({ products: cart.value, card_token: id, id_restaurant: cart.value[0].restaurant })
        const tRes = (await res).value

        if (tRes.order.status != 'saved') {
            toast.error('No se pudo crear el pedido, intentelo más tarde')
            emit('closeModal', true)
        } else {
            toast.success(tRes.stripe)
        }

        isLoading.value = false
        emit('closeModal', true)
        useRouter().replace('/orders')
    } catch (error) {
        console.log(error);
    }


    return obj;
}
</script>

<template>
    <div class="flex items-center justify-center fixed left-0 top-0 bg-[#a3a2a7]/90 w-screen h-screen z-50"
        v-if="isVisible">
        <div class="p-8 bg-white rounded-lg w-1/3 h-fit flex flex-col gap-4">
            <div class="flex justify-between">
                <div class="font-bold text-2xl text-[#2d2d2d] mb-4">Add Payment Method</div>
                <Icon name="ci:close-md" class="cursor-pointer hover:border rounded-lg" size="1.5rem"
                    @click="() => $emit('closeModal', true)" />
            </div>
            <div class="flex flex-col w-full h-full">
                <div class="flex gap-3 flex-col p-2">
                    <p class="text-sm text-[#7b7a7f]">Card Holder*</p>
                    <input class="border p-2 rounded-lg" type="text" name="Credit Card Holder" placeholder="John Doe"
                        v-model="obj.name">
                </div>
                <div class="flex gap-3 flex-col p-2">
                    <p class="text-sm text-[#7b7a7f]">Credit/Debit Card Number*</p>
                    <input class="border p-2 rounded-lg text-black" type="text" name="Credit Card Number"
                        v-model="obj.number" placeholder="4224 4224 4224 4224">
                </div>
                <div class="flex gap-3 xs:flex-col lg:flex-row w-full items-center p-2">
                    <div>
                        <p class="text-sm text-[#7b7a7f]">Expriation month and year*</p>
                        <input class="border p-2 rounded-lg text-black" type="month" name="Credit Card Number"
                            v-model="obj.expiration" placeholder="05/2027" ref="expirationDate">
                    </div>
                    <div>
                        <p class="text-sm text-[#7b7a7f]">CVC*</p>
                        <input class="border p-2 rounded-lg text-black" type="number" name="Credit Card Number"
                            v-model="obj.cvc" placeholder="737">
                    </div>
                </div>
                <div class="flex gap-3 xs:flex-col lg:flex-row w-full items-center p-2 mt-2">
                    <p class="mr-4 text-sm flex-1">*This field is mandatory</p>
                    <div class="flex-1 flex flex-row gap-2 justify-end items-center w-full">
                        <LayoutButton title="Cancel" buttonType="reverse" />
                        <LayoutButton title="Add and Pay" :action="() => loadObject()" />
                    </div>
                </div>
            </div>
        </div>
        <div v-if="isLoading"
            class="absolute flex flex-col items-center justify-center gap-5 bg-crimson-600 text-white p-6 shadow-xl rounded-lg">
            <LayoutLoading />
            <p>Porfavor espere...</p>
        </div>
    </div>
</template>
