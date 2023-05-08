<script setup>
const { icon, redirect, text } = defineProps({
    icon: String,
    text: String,
    redirect: String
})

</script>

<template>
    <div class="jrs-aside-item gap-2 p-2 hover:bg-[#27333f] flex items-center justify-start text-white rounded-lg cursor-pointer" @click="() => $router.replace(redirect)">
        <Icon size="20px" :name="icon" class="text-white" />
        <p>{{ text }}</p>
    </div>
</template>

<style>
.jrs-aside-item {
    width: 150px;
}
.isActive {
    background: #394552;
}
</style>
