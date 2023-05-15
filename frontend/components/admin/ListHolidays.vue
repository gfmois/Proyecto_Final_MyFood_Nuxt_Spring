<script setup>
import { useToast } from "vue-toast-notification"
import { useGetRestaurantHolidays, useDeleteHoliday } from "~/composables/holidays/useHolidays"
import { useAuth } from "~/store"

const { user } = useAuth()
const holidays = await useGetRestaurantHolidays(user.value.id_restaurant)
const indexItem = ref()

holidays.value.holidays = holidays.value.holidays.map((e) => {
    delete e.restaurant
    return e
})

const deleteHoliday = async (holiday) => {
    let item = holiday[indexItem.value]

    const res = await useDeleteHoliday(item.id_holiday)
    console.log(res.value);
    if (res.value.status != 400) {
        useToast().success('Holiday Deleted')
        return
    }

    useToast().error(res.value.message)
}

// FIXME: Make Create Holiday in CreateModal && Make Update Function

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full flex-col gap-2">
        <ListItems
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
    </div>
</template>
