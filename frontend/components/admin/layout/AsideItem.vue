<script setup>
const { icon, redirect, text } = defineProps({
    icon: String,
    text: String,
    redirect: String
})

</script>

<template>
    <div class="hover:bg-[#27333f] p-2 flex items-center justify-start gap-2 text-white rounded-lg cursor-pointer" @click="() => $router.replace(redirect)">
        <Icon size="1.25rem" :name="icon" class="text-white" /> {{ text }}
    </div>
</template>

<style>
.isActive {
    background: #394552;
}
</style>
