<script setup>
import { useAuth } from "~/store"

const router = useRouter()
const { actCheckHasUser, mutCheckIsAdmin, actChangeUser } = useAuth()
const client_token = useCookie('token_client')
const admin_token = useCookie('token_admin')

const { asideItems } = defineProps({
    asideItems: Array,
    title: String,
    image: String
})

const logout = () => {
    client_token.value = null
    admin_token.value = null

    actCheckHasUser()
    mutCheckIsAdmin(false)
    actChangeUser(false)

    router.replace("/")
}

</script>

<template>
    <div class="bg-gray-800 h-screen w-1/6 absolute left-0 p-2 flex flex-col gap-2 jrs-side-panel">
        <div class="jrs-close-button">
            <div class="jrs-button-container">
                <label for="close_asside">X</label>
                <input type="checkbox" id="close_asside"/>
            </div>
        </div>
    
        <!-- Nombre Empresa -->
        <div class="p-2 font-medium border-b text-white flex items-center gap-3">
            <img :src="image" alt="" class="w-16 h-16">
            {{ title }}
        </div>

        <!-- Items -->
        <div class="w-full h-5/6 p-4 flex flex-col gap-1">
            <AdminLayoutAsideItem @click="() => $emit('clickedOption', index)" :icon="item.icon" :text="item.title" v-for="(item, index) in asideItems" />
        </div>
        <div class="w-full h-1/6 p-4 flex flex-col gap-1 justify-end">
            <div class="hover:bg-red-800 p-2 flex items-center justify-start gap-2 text-white rounded-lg cursor-pointer" @click="() => logout()">
                <Icon size="1.25rem" name="ri:logout-circle-line" class="text-white" />
                Cerrar Sesión
            </div>
        </div>
    </div>
</template>

<style scoped>
.jrs-close-button {
    width:100%;
    height: 100vh;
    position: absolute;
    background-color:transparent;
    z-index: 2;
    display: flex;
    align-items: center;
}
.jrs-close-button .jrs-button-container{
    position: absolute;
    right:-5px;
    z-index: 2;
    width: 30px;
    height: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: white;
    background-color: blue;
    border-radius: 50%;
}

.jrs-close-button .jrs-button-container input {
    display: none;
}

.jrs-side-panel {
    min-width: 150px;
    
}

@media (max-width: 960px) {

}
</style>