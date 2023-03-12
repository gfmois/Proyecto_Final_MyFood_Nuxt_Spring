<template>
    <div>
        <LayoutNavbar/>
        <main>
            <slot/>
        </main>
        <LayoutFooter/>
    </div>
</template>

<script setup>
useHead({
    titleTemplate: "%s - MyFood",
})
</script>
