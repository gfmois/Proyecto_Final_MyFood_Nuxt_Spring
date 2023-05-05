<script setup>
import { useGetClientReserves } from "~/composables/client/useClient"

const keys = ["Reserve No", "Restaurant", "Bill To Name", "Type", "Date", "Diners", "Status"]

const values = ref(await useGetClientReserves())

const obj = reactive({ value: [] })

values.value = values.value.reserves.forEach((e) => {
    const type = {
        MID_MORNING_SNACK: 'Almuerzo',
        LUNCH: 'Comida',
        DINNER: 'Cena'
    }

    delete e.id_client;
    e.types = type[e.types]

    obj.value.push({
        "Reserve No": e.id_reserve,
        "Restaurant": e.restaurant,
        "Bill To Name": e.name,
        "Type": e.types,
        "Date": e.date_reserve,
        "Diners": e.diners,
        "Status": e.status
    })
})

const cancelReserve = (item) => {
    const obj = {
        id_reserve: item["Reserve No"],
        restaurant: item["Restaurant"],
        name: item["Bill To Name"],
        types: item["Type"],
        date_reserve: item["Date"],
        diners: item.Diners,
        status: item.Status
    }

    console.log(obj);
}
</script>


<template>
    <Header>
        <Title>Mis Reservas</Title>
    </Header>
    <LayoutTable :items="obj" @cancel="$e => cancelReserve($e)" />
</template>
