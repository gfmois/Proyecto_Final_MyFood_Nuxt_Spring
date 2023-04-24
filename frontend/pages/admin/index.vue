<template>
    <Header>
        <Title>Dashboard</Title>
    </Header>

    <div class="w-full h-[5%] bg-red-400 px-6 py-3 flex gap-2 justify-end items-center">
        <div>A</div>
        <div>A</div>
        <div>A</div>
        <div class="w-8 h-8 cursor-pointer">
            <img :src="user.avatar" class="w-full h-full" alt="Employee Avatar">
        </div>
    </div>

    <div class="px-6 py-4">
        <h1></h1>
    </div>
</template>

<script setup>
import { useGetEmployee } from "~/composables/employee/useEmployee"

definePageMeta({
    layout: "dashboard"
})


const user = (await useGetEmployee()).value
</script>
