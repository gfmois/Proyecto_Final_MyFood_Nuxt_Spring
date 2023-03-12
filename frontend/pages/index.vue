<template>
    <div
        class="h-[75vh] w-full p-[10px] box-border grid grid-cols-1 grid-rows-1 gap-y-[10px] gap-x-[10px] tracking-[2px] text-[1.5rem] relative">
        <div class="w-full h-full bg-red-400 overflow-hidden filter brightness-50 relative">
            <img src="/images/home.jpg" alt="MyFood Welcome Image" class="h-full w-full object-cover object-center">
        </div>
        <div class="absolute w-1/3 h-1/3 z-50 top-20 left-20 bg-white/80 p-6 text-black rounded-lg shadow-lg font-mono text-center">
            <h1 class="">Bienvenido a MyFood</h1>
            <p class="text-sm">La Aplicación que te servirá para llenar tu apetito</p>
            <LayoutButton title="Comienza tu Experiencia" class="text-center mt-20" />
        </div>
    </div>
</template>