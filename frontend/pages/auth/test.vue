<script setup>
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
                title: "Password",
                placeholder: "**********",
                type: "password",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            }
        ]
    },
    {
        title: "Confirmación",
        done: false,
        visible: false
    }
])
</script>

<template>
    <FormStepper :steps="formSteps" />
</template>