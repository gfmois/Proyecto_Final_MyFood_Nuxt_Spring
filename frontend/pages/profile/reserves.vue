<script setup>
import { useGetClientReserves } from "~/composables/client/useClient"

const keys = [ 'Order No', 'Order Date', 'Bill To Name', 'Restaurant', 'Total', 'Actions' ]
const values = await useGetClientReserves()
</script>

<template>
    <Header>
        <Title>Mis Reservas</Title>
    </Header>
    <LayoutTable :keys="keys"/>
</template>
