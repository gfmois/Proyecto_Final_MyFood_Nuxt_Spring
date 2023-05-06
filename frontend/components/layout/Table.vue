<script setup>
const { items, hasCancel } = defineProps({
    items: Object,
    hasCancel: Boolean,
    toModal: Array
})

const keys = ref(Object.keys(items.value[0]))
const valuesArr = ref(computed(() => Object.values(items.value)).value)

const checkStartsWith = (value) => typeof value == "string" ? value.startsWith("_") : false
const isModalOpen = ref(false)
const modalTitle = ref("")
const modalItems = ref([])
const total = ref(0)

const openModal = (item, title) => {
    total.value = 0;
    isModalOpen.value = true
    modalTitle.value = title
    modalItems.value = item

    item._Products.forEach((e) => {
        total.value += e.quantity * e.product.price
    })
}

</script>

<template>
    <div class="min-h-screen bg-gray-200">
        <main>
            <div class="py-12">
                <div class="max-w-7xl mx-auto sm:px-6 lg:px-8">
                    <div class="bg-white overflow-hidden shadow-xl sm:rounded-lg py-4 px-4">
                        <form class="mb-4">
                            <div class="address">
                                <div class="item mb-2 md:flex md:flex-wrap md:justify-between">
                                    <div class="container w-full px-4 sm:px-8">
                                        <div class="flex flex-col">
                                            <div class="overflow-x-auto sm:-mx-6 lg:-mx-8">
                                                <div class="py-2 inline-block w-full sm:px-6 lg:px-8">
                                                    <div class="table-wrp block max-h-96">
                                                        <table class="w-full">
                                                            <thead class="bg-white border-b sticky top-0">
                                                                <tr>
                                                                    <th scope="col" v-for="key in keys"
                                                                        class="text-md font-medium text-gray-900 px-6 py-4 text-left">
                                                                        <p v-if="!checkStartsWith(key)">
                                                                            {{ key }}
                                                                        </p>
                                                                    </th>
                                                                    <th
                                                                        class="text-md font-medium text-gray-900 px-6 py-4 text-left">
                                                                        Actions</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="h-96 overflow-y-auto">
                                                                <tr v-for="item in valuesArr"
                                                                    class="bg-white border-b
                                                                    transition duration-300 ease-in-out hover:bg-gray-100">
                                                                    <td v-for="(value, index) in item"
                                                                        :class="`text-sm text-gray-900  px-6 py-4 whitespace-nowrap ${index == 0 ? 'font-bold' : ''}`">
                                                                        <p v-if="!checkStartsWith(index)"
                                                                            :class="`${typeof index == 'string' ? toModal.includes(index) ? 'text-blue-500 cursor-pointer w-fit h-fit' : null : null}`"
                                                                            @click="() => toModal.includes(index) ? openModal(item, index) : null">
                                                                            {{ value }}</p>
                                                                    </td>
                                                                    <td
                                                                        class="text-sm text-red-600 font-medium px-6 py-4 whitespace-nowrap">
                                                                        <p class="cursor-pointer"
                                                                            @click="() => $emit('cancel', item)"
                                                                            v-if="hasCancel && item.Status != 'CANCELLED' && item.Status != 'COMPLETED'">
                                                                            Cancel
                                                                        </p>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="flex items-center justify-center">
                            <LayoutButton title="Volver" :action="() => $router.replace('/')" />
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <div class="fixed top-0 left-0 flex items-center justify-center h-full w-full bg-black/50 gap-4" v-if="isModalOpen">
        <div class="w-2/3 h-2/3 rounded-lg shadow-xl bg-crimson-600 p-1">
            <div class="text-white w-full h-12 flex items-center p-4 justify-between">
                <h1 class="p-2">{{ modalTitle }}</h1>
                <div>
                    <Icon name="material-symbols:close-rounded" size="1.5rem" class="cursor-pointer border rounded-lg text-white border-white hover:text-black hover:border-black" @click="() => isModalOpen = false" />
                </div>
            </div>
            <div class="w-full h-[80%] flex items-center flex-col p-4 gap-4 overflow-y-auto justify-between">
                <div v-for="item in modalItems[`_${modalTitle}`]" class="w-full h-full">
                    <div class="flex gap-4 w-full h-full">
                        <ProductCard :product="Object.values(item)[0]" :has-action-modal="false">
                            <p class="font-bold">Cantidad: {{ item.quantity }}</p>
                            <p class="font-bold">Total: {{ item.quantity * Object.values(item)[0].price }} €</p>
                        </ProductCard>
                    </div>
                </div>
            </div>
            <div class="h-[10%] mt-2 w-full text-white flex items-center justify-end p-4">
                <p class="mr-4">
                    Total: {{ total }}€
                </p>
            </div>
        </div>
    </div>
</template>

<style>
.table-wrp {
    max-height: 75vh;
    overflow-y: auto;
    display: block;
}

thead {
    position: sticky;
    top: 0
}
</style>
