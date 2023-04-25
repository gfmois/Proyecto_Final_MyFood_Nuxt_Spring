<template>
    <div class="fixed w-screen h-screen">
        <AdminLayoutAside :aside-items="asideItems" />
        <main class="absolute w-5/6 h-screen bg-white right-0">
            <slot/>
        </main>
    </div>
</template>

<script setup>
useHead({
    titleTemplate: "%s - MyFood",
})

const asideItems = [
    { title: "Home", icon: "ph:house-simple", redirect: "/admin" },
    { title: "Mis Pedidos", icon: "solar:box-linear", redirect: "/admin/orders" },
    { title: "Mis Reservas", icon: "ic:twotone-table-bar", redirect: "/admin/reserves" },
]
</script>
