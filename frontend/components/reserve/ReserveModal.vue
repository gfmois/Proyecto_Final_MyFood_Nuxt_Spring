<script setup>
    const emit = defineEmits(["isVisible"])
    const { isModalVisible } = defineProps({
        isModalVisible: Boolean
    })

    const resetFormSteps = () => {
        emit("isVisible", false)
        formSteps.forEach((step) => {
            step.done = false;
            if (step.fields) {
                step.fields.forEach((field) => {
                    field.value = ""
                })
            }
        })
    }

    const formSteps = reactive([
    {
        title: "Información del Cliente",
        name: "information",
        visible: true,
        done: false,
        fields: [
            {
                title: "Número de Comensales",
                objName: "diners",
                placeholder: "2",
                type: "number",
                "min": 2,
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Turno",
                objName: "type",
                options: ['Almorzar', 'Comida', 'Cena'],
                optionsValue: ["MID_MORNING_SNACK", "LUNCH", "DINNER"],
                placeholder: "",
                type: "select",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            }
        ]
    },
    {
        title: "Reserva",
        visible: false,
        done: false,
        fields: [
            {
                title: "Dia de la Reserva",
                objName: "date_reserve",
                placeholder: "14/04/2023",
                type: "date",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Nombre",
                objName: "name",
                placeholder: "John",
                type: "text",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Email",
                placeholder: "johndoe@example.com",
                type: "email",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
        ]
    },
    {
        title: "Confirmación",
        objName: 'confirmation',
        done: false,
        visible: false,
        end: true
    }
])

</script>

<template>
    <div v-if="isModalVisible">
        <div class="fixed top-0 left-0 p-8 w-screen h-screen z-[51] flex items-center justify-center bg-black/40">
            <div class="bg-white w-3/4 h-2/3 rounded-lg shadow-md">
                <div class="bg-neutral-300 flex items-center justify-end p-1">
                    <Icon name="ci:close-big" size="1.5rem" class="cursor-pointer" @click="$emit('isVisible', false)" />
                </div>
                
                <FormStepper :steps="formSteps" @closeModal="$e => resetFormSteps()" />
            </div>
        </div>
    </div>
</template>
