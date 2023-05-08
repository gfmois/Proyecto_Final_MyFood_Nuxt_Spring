<template>
    <div class="fixed w-screen h-screen">

        <main class="absolute w-5/6 h-screen bg-white right-0">
            <slot/>
        </main>
    </div>
</template>

<script setup>
useHead({
    titleTemplate: "%s - MyFood",
})
</script>
