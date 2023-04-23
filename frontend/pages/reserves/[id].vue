<script setup>
const id = useRoute().params.id
</script>

<template>
    <Header>
        <Title>Información de la Reserva</Title>
    </Header>

    <h1> {{ id }} </h1>
</template>
