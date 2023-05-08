<script setup>
import { useToast } from 'vue-toast-notification';
import { useDeleteEmployee, useGetRestaurantEmployees } from '~~/composables/employee/useEmployee';
import { useAuth } from '~~/store';
const { user } = useAuth()

const { employees } = (await useGetRestaurantEmployees(user.value.id_restaurant)).value

employees.map((e) => {
    delete e.id_restaurant;
    delete e.password
})

const deleteUser = async (user) => {
    // FIXME: User devuelve todos los usuarios de la tabla, mirar como envia el padre (este) al hijo la información
    console.log(user);

    const res = await useDeleteEmployee(user.id_employee)
    if (res.value.status == 400) {
        useToast().error(res.value.message)
        return
    }

    useToast().success(res.value.message)
    return
}

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full">
        <ListItems :object="employees" :onlyIsAdmin="true" :hasActionButtons="true" :toSee="['name', 'email', 'phone', 'avatar', 'type']" :actionsKeys="['Editar', 'Eliminar']" @cancel="$e => deleteUser($e)" />
    </div>
</template>
