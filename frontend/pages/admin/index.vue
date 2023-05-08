<template>
    <Header>
        <Title>Dashboard</Title>
    </Header>

    <AdminLayoutAside @clickedOption="$e => pageClicked = $e" :aside-items="asideItems" :title="restaurant.name" :image="restaurant.logo" />

    <div class="w-full h-[10%] px-6 py-3 flex gap-2 justify-between items-center bg-[#1d242d] text-white">
        <div class="flex items-center justify-center w-1/2 flex-col h-full">
            <div>
                <div class="font-medium">Dashboard</div>
                <p class="text-xs font-light">{{ formattedDate }}</p>
            </div>
        </div>

        <div class="flex gap-0 w-2/6 h-full items-center justify-around">
            <div class="p-4 flex-1 w-full h-full flex justify-end items-center">
                <div class="flex items-center justify-center rounded-full w-fit h-fit p-1 border-2 border-gray-200">
                    <Icon name="material-symbols:notifications-rounded" size="1.5rem" class="text-white" />
                </div>
            </div>
            <div class="flex-1 p-2 w-full h-full flex gap-2">
                <img :src="user.avatar" alt="Users Avatar" class="w-12 h-12">
                <div class="flex flex-col gap-0">
                    <h3 class="font-medium">{{ user.name }}</h3>
                    <p class="font-light">{{ user.email }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="px-6 py-4 w-full h-full flex items-center justify-end">
        <div class="bg-[#1f2937] rounded-lg w-5/6 h-full px-6 py-4 text-white">
            <!-- Routing -->
            <ClientOnly placeholder="Loading...">
                <component :is="pages[pageClicked]" />
            </ClientOnly>
        </div>
    </div>
</template>

<script setup>
import { useGetEmployee } from "~/composables/employee/useEmployee"
import { useGetRestaurantByEmployee } from "~~/composables/restaurants/useRestaurants"

definePageMeta({
    layout: "",
})

const asideItems = [
    { title: "Home", icon: "ph:house-simple", redirect: "/admin" },
    { title: "Pedidos", icon: "solar:box-linear", redirect: "/admin/orders" },
    { title: "Reservas", icon: "ic:twotone-table-bar", redirect: "/admin/reserves" },
    { title: "Empleados", icon: "clarity:employee-solid", redirect: "/admin/employees" },
    { title: "Productos", icon: "streamline:food-drum-stick-1-cook-animal-drumsticks-products-chicken-cooking-nutrition-food", redirect: "/admin/employees" },
]

const user = (await useGetEmployee()).value
const restaurant = await ref(computed(async () => (await useGetRestaurantByEmployee()).value)).value
const pageClicked = ref(0)
const pages = ref([
    defineAsyncComponent(() => import('~/components/admin/Dashboard.vue')),
    defineAsyncComponent(() => import('~/components/admin/ListOrders.vue')),
    defineAsyncComponent(() => import('~/components/admin/ListReserves.vue')),
    defineAsyncComponent(() => import('~/components/admin/ListEmployees.vue'))
])

const todaysDate = new Date();
const days = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
const months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

const weekDay = days[todaysDate.getDay()];
const day = todaysDate.getDate();
const month = months[todaysDate.getMonth()];
const year = todaysDate.getFullYear();

const formattedDate = `${weekDay} ${day} de ${month} de ${year}`;

</script>
