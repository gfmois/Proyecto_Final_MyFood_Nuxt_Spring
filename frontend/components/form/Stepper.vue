<script setup>
const {} = defineProps({
    steps: Object
})
</script>

<template>
    <div class="p-8">
        <ol class="flex items-center w-full text-sm font-medium text-center text-gray-500 dark:text-gray-400 sm:text-base">
            <FormStepperEntry :inputs="step.inputs" :title="step.title" v-for="step in steps" />
        </ol>
    </div>

    <!-- Make Stepper Div with the inputs -->
    <div class="p-8 w-full h-full bg-red-400">
        <div class="p-6 bg-emerald-500">
            <!-- TODO: Make template for forms -->
        </div>
    </div>
</template>