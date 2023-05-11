<script setup>
import { useToast } from 'vue-toast-notification';
import { useDeleteEmployee, useGetRestaurantEmployees, useUpdateEmployee } from '~~/composables/employee/useEmployee';
import { useAuth } from '~~/store';
const { user } = useAuth()

const { employees } = (await useGetRestaurantEmployees(user.value.id_restaurant)).value

const employeeToUpdate = ref({})
const indexItem = ref()

employees.map((e) => {
    delete e.id_restaurant;
    delete e.password
})

const deleteUser = async (user) => {
    const res = await useDeleteEmployee(user[indexItem.value].id_employee)
    if (res.value.status == 400) {
        useToast().error(res.value.message)
        return
    }

    useToast().success(res.value.message)
    // FIXME: resEmployees doesn't update
    employees = computed(() => employees.filter(
        (e) => e.id_employee !== user[indexItem.value].id_employee
    )).value;
    return
}

watch(employeeToUpdate, async (v, pv) => {
    const objToUpdate = {
        id_employee: v[0],
        name: v[1],
        email: v[2],
        phone: v[3],
        avatar: v[4],
        type: v[5]
    }

    const res = await useUpdateEmployee(objToUpdate)
    console.log(res.value);
    if (res.value.status == 400) {
        useToast().error(res.value.message)
        return
    }

    useToast().success('Employee Updated')
    let index = employees.findIndex((e) => e.id_employee == res.value.employee.id_employee)
    employees[index] = res.value.employee
})

</script>

<template>
    <div class="flex items-center justify-center p-2 w-full h-full flex-col gap-2">
        <ListItems
            :object="employees"
            :onlyIsAdmin="true"
            :hasActionButtons="true"
            :toSee="['name', 'email', 'phone', 'avatar', 'type']"
            :actionsKeys="['Editar', 'Eliminar']"
            @indexCancel="$e => indexItem = $e"
            @cancel="$e => deleteUser($e)"
            @update="$e => employeeToUpdate = $e"
            />
    </div>
</template>
