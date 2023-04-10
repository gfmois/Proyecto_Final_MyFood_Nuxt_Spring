<script setup>
const { steps } = defineProps({
    steps: Object
})

const errors = ref({})
const actualStep = ref(0)

// TODO: Regex
const checkStep = () => {
    // Check if input value is empty
    steps[actualStep.value].fields.forEach((input) => {
        errors.value[input.title] = input.value == ''
    })

    // Check if can upadte the actualStep or send the request
    if (Object.values(errors.value).every(v => v == false)) {
        if (steps.length > (actualStep.value + 1)) {
            // TODO: If end of steps send request to save the action
            actualStep.value++
            steps[actualStep.value - 1].visible = false
            steps[actualStep.value - 1].done = true
            steps[actualStep.value].visible = true
        }
    }
}

const goBack = () => {
    if (actualStep.value > 0) {
        // Removes visibility of the actual step
        steps[actualStep.value].visible = false

        // Gives visibility to the anterior step
        actualStep.value--
        steps[actualStep.value].visible = true
    }
}

const loadFields = () => {
    steps[actualStep.value].fields.forEach((i) => {
        errors.value[i.title] = false
    })
}

onMounted(() => {
    loadFields()
})

</script>

<template>
    <div class="p-8 mx-auto flex items-center justify-center">
        <ol class="flex items-center justify-center space-x-4 w-full text-sm font-medium text-center text-gray-500 dark:text-gray-400 sm:text-base">
          <FormStepperEntry
            :inputs="step.inputs"
            :done="step.done"
            :title="step.title"
            :isLast="typeof steps[index + 1] == 'undefined'"
            v-for="(step, index) in steps"
            :key="index"
          />
        </ol>
    </div>      

    <div class="p-8 w-full h-full" v-for="step in steps">
        <div v-if="step.visible" class="p-6 flex items-center justify-center">
            <!-- TODO: Add Images & Calendar like MyFood Old -->
            <div class="w-1/2 h-1/2 bg-crimson-500/75 rounded-lg shadow-lg flex items-center justify-center p-6 flex-col">
                <div class="p-4 1/3 w-full flex items-center text-white uppercase font-bold justify-center text-3xl">{{ step.title }}</div>
                <div class="bg-neutral-200/60 rounded-lg w-full h-full flex items-center justify-center p-4 flex-col">
                    <div v-for="input in step.fields">
                        <div v-if="input.type != 'select'">
                            <label class="block text-gray-700 text-sm font-bold mb-2" :for="input.title">
                                {{ input.title }}
                            </label>
                            <input
                                :class="`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline ${errors[input.title] ? 'border-red-500' : ''}`"
                                :id="input.title" v-model="input.value" :type="input.type" :placeholder="input.placeholder">
                            <p class="text-red-500 text-xs italic" v-if="errors[input.title]">{{ input.errorMsg }}</p>
                        </div>
                        <div v-if="input.type == 'select'" class="mt-4">
                            <label class="block text-gray-700 text-sm font-bold mb-2" :for="input.title">
                                {{ input.title }}
                            </label>
                            <select v-model="input.value"
                                :class="`block appearance-none w-full bg-gray-200 border border-gray-200 text-gray-700 py-3 px-4 pr-8 rounded leading-tight focus:outline-none focus:bg-white focus:border-gray-500 ${errors[input.title] ? 'border-red-500' : ''}`">
                                <option disabled value="">Selecciona una opción</option>
                                <option v-for="option in input.options">{{ option }}</option>
                            </select>
                            <p class="text-red-500 text-xs italic mt-1" v-if="errors[input.title]">{{ input.errorMsg }}</p>
                        </div>
                    </div>
                    <div class="mt-5 flex items-center justify-center gap-2">
                        <LayoutButton title="Back" v-if="actualStep > 0" :action="() => goBack()" />
                        <LayoutButton title="Contiune" :action="() => checkStep()" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>