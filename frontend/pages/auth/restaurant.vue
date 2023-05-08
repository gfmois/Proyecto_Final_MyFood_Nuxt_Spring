<script setup>
const formSteps = reactive([
    {
        title: "Información del Restaurante",
        name: "information",
        visible: true,
        done: false,
        fields: [
            {
                title: "Nombre",
                objName: "name",
                placeholder: "Restaurante Casa Juan",
                type: "text",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Calle",
                objName: "location", // Transform to Lat & Lng
                placeholder: "Av. Cristóbal Colón",
                type: "text",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Ciudad",
                objName: "city", // Transform to Lat & Lng
                placeholder: "Alicante",
                type: "text",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Tipo",
                objName: "type",
                placeholder: "Italiano",
                options: ['Italiano', 'Chino', 'Japonés', "Español"],
                optionsValue: ["Italiano", "Chino", "Japonés", "Español"],
                type: "select",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Capacidad",
                objName: "capacity", // Transform to Lat & Lng
                placeholder: "30",
                type: "number",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },    
        ]
    },
    {
        title: "Detalles Restaurante    ",
        visible: false,
        done: false,
        fields: [
            {
                title: "Logo",
                objName: "logo",
                type: "file",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            },
            {
                title: "Imágen",
                objName: "file",
                type: "file",
                errorMsg: "Tiene que rellenar todos los campos",
                value: ""
            }
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
    <FormStepper :steps="formSteps" />
</template>