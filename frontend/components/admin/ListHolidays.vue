<script setup>
import { useToast } from "vue-toast-notification"
import { useGetRestaurantHolidays, useDeleteHoliday, useUpdateHoliday } from "~/composables/holidays/useHolidays"
import { useAuth } from "~/store"

const { user } = useAuth()
const holidays = await useGetRestaurantHolidays(user.value.id_restaurant)
const indexItem = ref()


if (!holidays.value.message) {
    holidays.value.holidays = holidays.value.holidays.map((e) => {
        delete e.restaurant
        return e
    })
}

const deleteHoliday = async (holiday) => {
    let item = holiday[indexItem.value]

    const res = await useDeleteHoliday(item.id_holiday)
    console.log(res.value);
    if (res.value.status != 400) {
        useToast().success('Vacaciones Eliminadas')
        return
    }

    useToast().error(res.value.message)
}

const updateHoliday = async (holiday) => {
    const obj = {
        id_holiday: holiday[0],
        holiday_date: holiday[1],
        description: holiday[2]
    }

    const res = await useUpdateHoliday(obj)
    if (res.value.status == 200) {
        useToast().success(res.value.message)
        return
    }

    useToast().error(res.value.message)
}
</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full flex-col gap-2">
        <ListItems
            v-if="holidays.holidays"
            :showDescription="true"
            :object="holidays.holidays"
            :onlyIsAdmin="true"
            :hasActionButtons="true"
            :toSee="['holiday_date', 'description']"
            :actionsKeys="['Editar', 'Eliminar']"
            @indexCancel="$e => indexItem = $e"
            @cancel="$e => deleteHoliday($e)"
            @update="$e => updateHoliday($e)"
        />
        <div v-if="!holidays.holidays">Sin Vacaciones Asignadas Todavía</div>
    </div>
</template>
