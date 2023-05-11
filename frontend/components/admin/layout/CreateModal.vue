<template>
    <div class="fixed w-screen h-screen bg-black/50 top-0 left-0 flex z-40 items-center justify-center text-white flex-col">
        <ClientOnly>
            <div class="w-1/2 h-1/2 bg-slate-300 flex p-2 gap-2 rounded-lg shadow-2xl z-50" v-if="selected == null">
                <div class="flex-1 bg-slate-400 hover:shadow-2xl cursor-pointer flex items-center justify-center flex-col"
                    @click="() => selected = 0">
                    <LottieAnimation :animationData="employeeJson" :height="200" :width="200" />
                    <h1>Crear Empleado</h1>
                </div>
                <div class="flex-1 bg-slate-400 hover:shadow-2xl cursor-pointer flex items-center justify-center flex-col"
                    @click="() => selected = 1">
                    <LottieAnimation :animationData="productsJson" :height="200" :width="200" />
                    <h1>Crear Producto</h1>
                </div>
            </div>
        </ClientOnly>

        <div class="w-1/2 h-1/2 bg-slate-200 shadow-2xl rounded-lg" v-if="selected != null">
            <div class="p-2 text-black w-full h-full">
                <div class="flex flex-row px-2 py-1">
                    <Icon @click="() => {selected = null; objModel = []}" name="material-symbols:arrow-back" size="1.5rem"
                        class="cursor-pointer" />
                </div>
                <div class="p-2 w-full h-full">
                    <div class="p-2 w-full h-full grid grid-cols-1">
                        <div v-for="key, index in keys[selected]" class="p-2 flex items-center justify-center">
                            <div class="flex gap-1 flex-col w-1/2 h-full items-start justify-center" v-if="key == 'file'">
                                <label for="inputItem" class="block font-bold float-left ml-1 text-sm text-black">{{ capitalize(key) }}</label>
                                <input type="file" id="inputItem" :placeholder="key"  class="block text-sm p-2 w-full placeholder-gray-400/70 dark:placeholder-gray-500 rounded-lg border border-gray-200 bg-white px-5 text-gray-700 focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40 dark:border-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:focus:border-blue-300" @change="handleFileInputChange(index, $event)" />
                            </div>
                            <div class="flex gap-1 flex-col w-1/2 h-full items-start justify-center" v-if="key != 'file'">
                                <label for="inputItem" class="block font-bold float-left ml-1 text-sm text-black">{{ capitalize(key) }}</label>
                                <input type=" text" id="inputItem" :placeholder="key"  class="block text-sm p-2 w-full placeholder-gray-400/70 dark:placeholder-gray-500 rounded-lg border border-gray-200 bg-white px-5 text-gray-700 focus:border-blue-400 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-40 dark:border-gray-600 dark:bg-gray-900 dark:text-gray-300 dark:focus:border-blue-300" v-model="objModel[index]" />
                            </div>
                        </div>
                        <div></div>
                        <div class="p-4 flex justify-end items-center">
                            <LayoutButton title="Guardar" buttonType="green" :action="() => createItem()" />
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</template>

<script>
import { useToast } from 'vue-toast-notification'
import { useRegister } from '~/composables/auth/useAuth'
import { useCreateProduct } from "~/composables/products/useProducts"
import { useAuth } from "~/store"

export default {
    props: {
        employeeJson: Object,
        productsJson: Object
    },
    setup({ employeeJson, productsJson }) {
        const { user } = useAuth()
        const selected = ref(null)
        const objModel = ref([])
        const objToCreate = ref({})
        const keys = [
            ["email", "name", "password", "phone", "type"],
            ["file", "name", "description", "price"]
        ]

        const handleFileInputChange = (index, event) => {
            const file = event.target.files[0];

            objToCreate.value[keys[selected.value][index]] = file;
        };

        const capitalize = (value) => {
            if (!value) {
                return ''
            }

            return value[0].toUpperCase() + value.slice(1);
        }


        const createItem = async () => {
            let res = undefined
            objToCreate.value.id_restaurant = user.value.id_restaurant
            objModel.value.forEach((e, index) => {
                objToCreate.value[keys[selected.value][index]] = e
            })

            if (selected.value == 0) {
                res = await useRegister(objToCreate.value)
            }

            if (selected.value == 1) {
                res = await useCreateProduct(objToCreate.value)
            }

            if (res.value.status == 400) {
                useToast().error(res.value.message)
                return
            }

            useToast().success('Elemento Creado')
            objModel.value = []
            objToCreate.value = {}
            selected.value = null
        }

        return { employeeJson, productsJson, selected, objModel, keys, capitalize, createItem, handleFileInputChange }
    }
}
</script>
