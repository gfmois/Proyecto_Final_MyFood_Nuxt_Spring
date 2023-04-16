<script setup>
    const { isModalVisible } = defineProps({
        isModalVisible: Boolean
    })

    const formSteps = reactive([
    {
        title: "Información del Cliente",
        visible: true,
        done: false,
        fields: [
            {
                title: "Nombre",
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
            {
                title: "Turno",
                options: ['Almorzar', 'Comida', 'Cena'],
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
                placeholder: "14/04/2023",
                type: "date",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Número de Comensales",
                placeholder: "2",
                type: "number",
                "min": 2,
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            }
        ]
    },
    {
        title: "Confirmación",
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
                <FormStepper :steps="formSteps" />
            </div>
        </div>
    </div>
</template>