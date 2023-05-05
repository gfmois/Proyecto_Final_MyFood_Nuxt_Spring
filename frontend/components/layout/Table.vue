<script setup>
const { items } = defineProps({
    items: Object
})

const keys = ref(Object.keys(items.value[0]))
const valuesArr = ref(computed(() => Object.values(items.value)).value)


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
                                                                        {{ key }}
                                                                    </th>
                                                                    <th
                                                                        class="text-md font-medium text-gray-900 px-6 py-4 text-left">
                                                                        Actions</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="h-96 overflow-y-auto">
                                                                <tr v-for="item in valuesArr"
                                                                    class="bg-white border-b transition duration-300 ease-in-out hover:bg-gray-100">
                                                                    <td v-for="value, index in item"
                                                                        :class="`text-sm text-gray-900  px-6 py-4 whitespace-nowrap ${index == 0 ? 'font-bold' : ''}`">
                                                                        <p>{{ value }}</p>
                                                                    </td>
                                                                    <td v-if="item.Status != 'CANCELLED' && item.Status != 'COMPLETE'"
                                                                        class="text-sm text-red-600 font-medium px-6 py-4 whitespace-nowrap">
                                                                        <p class="cursor-pointer" @click="() => $emit('cancel', item)">
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
                    </div>
                </div>
            </div>
        </main>
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
