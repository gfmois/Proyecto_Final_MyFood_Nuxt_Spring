<script setup>
import { useGetRestaurantEmployees } from '~~/composables/employee/useEmployee';
import { useAuth } from '~~/store';
const { user } = useAuth()

const { employees } = (await useGetRestaurantEmployees(user.value.id_restaurant)).value

employees.map((e) => {
    delete e.id_restaurant;
    delete e.password
})


</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems
            :object="employees"
            :hasActionButtons="true"
            :actionKeys="['Editar', 'Eliminar']"
        />
    </div>
</template>
