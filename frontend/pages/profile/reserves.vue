<script setup>
import { useToast } from 'vue-toast-notification';
import { useGetClientReserves, useCancelReserve } from "~/composables/client/useClient"

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

const cancelReserve = async (item) => {
    const objToUpdate = {
        id_reserve: item["Reserve No"],
        restaurant: item["Restaurant"],
        name: item["Bill To Name"],
        types: item["Type"],
        date_reserve: item["Date"],
        diners: item.Diners,
        status: "CANCELLED"
    }

    const res = await useCancelReserve(objToUpdate)
    if (res.value.status != 200) {
        useToast().error(res.value.message)
        return
    }

    useToast().success("Reserve Cancelled")
    obj.value[obj.value.findIndex(e => e["Reserve No"] == objToUpdate.id_reserve)].Status = res.value.reserve.status
}
</script>


<template>
    <Header>
        <Title>Mis Reservas</Title>
    </Header>
    <LayoutTable :items="obj" @cancel="$e => cancelReserve($e)" :hasCancel="true" :toModal="[]" />
</template>
