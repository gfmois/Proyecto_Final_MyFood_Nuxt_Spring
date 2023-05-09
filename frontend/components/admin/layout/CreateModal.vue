<template>
    <div class="fixed w-screen h-screen bg-black/50 z-[70] top-0 left-0 flex items-center justify-center text-white">
        <div class="w-1/2 h-1/2 bg-slate-300 flex p-2 gap-2 rounded-lg shadow-2xl">
            <div class="flex-1 bg-slate-400 hover:shadow-2xl cursor-pointer flex items-center justify-center flex-col">
                <client-only>
                    <LottieAnimation
                    :animationData="productsJson"
                  :height="200"
                  :width="200"
                />
                </client-only>
                <h1>Crear Empleado</h1>
            </div>
            <div class="flex-1 bg-slate-400 hover:shadow-2xl cursor-pointer flex items-center justify-center flex-col">
            <client-only>
                <LottieAnimation
                    :animationData="employeeJson"
                  :height="200"
                  :width="200"
                />
              </client-only>
                <h1>Crear Producto</h1>
            </div>
        </div>
    </div>
</template>

<script setup>

const { employeeJson, productsJson } = defineProps({
    employeeJson: Object,
    productsJson: Object
})

</script>
