<template>
    <Header>
        <Title>Dashboard</Title>
    </Header>

    <div class="w-full h-[10%] px-6 py-3 flex gap-2 justify-between items-center">
        <div class="flex items-start flex-col">
            <div class="font-medium">Dashboard</div>
            <p class="text-xs font-light">{{ formattedDate }}</p>
        </div>

        <div class="flex gap-0 w-2/6 h-full items-center justify-around">
            <div class="p-4 flex-1 w-full h-full flex justify-end items-center">
                <div class="flex items-center justify-center rounded-full w-fit h-fit p-1 border-2 border-gray-200">
                    <Icon name="material-symbols:notifications-rounded" size="1.5rem" class="text-[#1f1f22]" />
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
    <div class="px-6 py-4 w-full h-full">
        <div class="bg-[#1f2937] rounded-lg w-full h-5/6 px-6 py-4 text-white">
            A
        </div>
    </div>
</template>

<script setup>
import { useGetEmployee } from "~/composables/employee/useEmployee"

definePageMeta({
    layout: "dashboard"
})

const todaysDate = new Date();
const days = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
const months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

const weekDay = days[todaysDate.getDay()];
const day = todaysDate.getDate();
const month = months[todaysDate.getMonth()];
const year = todaysDate.getFullYear();

const formattedDate = `${weekDay} ${day} de ${month} de ${year}`;

const user = (await useGetEmployee()).value
</script>
