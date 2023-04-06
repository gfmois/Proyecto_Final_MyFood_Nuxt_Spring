<script setup>
const formSteps = reactive([
    {
        title: "Personal Information",
        done: false,
        fields: [
            {
                title: "Nombre",
                placeholder: "John",
                type: "text",
                value: ""
            }
        ]
    }
])
</script>

<template>
    <FormStepper :steps="formSteps" />
</template>