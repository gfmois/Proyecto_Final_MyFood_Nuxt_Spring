<script setup>
import { useGetClientReserves } from "~/composables/client/useClient"

const keys = ["Reserve No", "Restaurant", "Bill To Name", "Type", "Date", "Diners", "Status"]

const values = ref(await useGetClientReserves())

values.value = values.value.reserves.map((e) => {
    const type = {
        MID_MORNING_SNACK: 'Almuerzo',
        LUNCH: 'Comida',
        DINNER: 'Cena'
    }

    delete e.id_client;
    e.types = type[e.types]

    return e
})
</script>

<template>
    <Header>
        <Title>Mis Reservas</Title>
    </Header>
    <LayoutTable :values="values" :keys="keys"/>
</template>
