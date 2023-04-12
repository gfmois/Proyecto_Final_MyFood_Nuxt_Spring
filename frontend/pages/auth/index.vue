<script setup>
import 'vue-toast-notification/dist/theme-sugar.css';

import { useToast } from 'vue-toast-notification';
import { useLogin, useRegister } from '~~/composables/auth/useAuth';
import { useAuth } from "~/store"

const strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})");
const mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");
const emailRegex = new RegExp('[a-z0-9]+@[a-z]+\.[a-z]{2,3}');

const toast = useToast({
	position: 'top-right',
	pauseOnHover: true,
})
const router = useRouter()

const register = ref(false)
const book_open = ref(false)
const login = ref(true)
const isLogin = ref(true)

const loginInputs = {
	name: "login",
	children: [
		{
			name: "Email",
			type: "email",
			required: true,
			value: "daw.moisesguerola@gmail.com"
		},
		{
			name: "Contraseña",
			type: "password",
			required: true,
			value: "#GX_2daw"
		}
	]
}

const registerInputs = {
	name: "register",
	children: [
		{
			name: "Nombre",
			type: "text",
			required: true,
			value: ""
		},
		{
			name: "Email",
			type: "email",
			required: true,
			value: ""
		},
		{
			name: "Teléfono",
			type: "text",
			required: true,
			value: ""
		},
		{
			name: "Contraseña",
			type: "password",
			required: true,
			value: ""
		},
		{
			name: "Repite Contraseña",
			type: "password",
			required: true,
			value: ""
		}
	]
}

function validate_input (input) {
	let validation = { value: true, msg: "Algo ha ido mal al validar"}
	if (!input.value) {
		validation = { value: false, msg: `${input.name} no puede ser un campo vacio`}
	}
	if (validation.value) {
		if(input.type == "password" && !mediumRegex.test(input.value)) {
			validation = { value: false, msg: `${input.name} insegura`}
		}
		if (input.type == "email" && !emailRegex.test(input.value)) {
			validation = { value: false, msg: `${input.name} incorrecto`}
		}
	}
	
	return validation
}

function validate_login () {
	let validation = { value: true, msg: "Has iniciado sessión correctamente"}
	loginInputs.children.map(input => {
		if(validation.value) {
			let v_input = validate_input(input)
			v_input.value ? null : validation = v_input
		}
	})
	return validation;
}


function validate_register () {
	let validation = { value: true, msg: "Te has registrado correctamente"}
	let password = ""
	registerInputs.children.map(input => {
		if(validation.value) {
			let v_input = validate_input(input)
			v_input.value ? null : validation = v_input
			if (!password && input.type == "password") {
				password = input.value
			} 
			if (password && input.type == "password") {
				if (password != input.value ) {
					validation = { value: false, msg: "Las contraseñas no coinciden"}
				}
			} 
		}
	})
	
	return validation;
}

const submit_login = async () => {
	let validation = validate_login()
	if(validation.value) {
		let login_info = {}
		loginInputs.children.map(li => {
			login_info[li.name.toLocaleLowerCase()] = li.value
		})
		let response = await useLogin(login_info)

		response.value.status != 500
			? (toast.success(validation.msg), router.replace('/')) 
			: toast.error("Usuario o contraseña incorrectos")
	} else {
		toast.error(validation.msg)
    }
}

const submit_register = async () => {
	let validation = validate_register()
	if(validation.value) {
		let register_info = {}
		registerInputs.children.map(ri => {
			register_info[ri.name.toLocaleLowerCase()] = ri.value
		})
		let response = await useRegister(register_info)
		response.value != false ? (toast.success(validation.msg), router.push('/')) : toast.error("No se ha podido registrar el usuario")		
	} else {
		toast.error(validation.msg)
	}
}

</script>

<template>
    <Header>
        <Title>{{ isLogin ? $t('auth.login') : $t('auth.register') }}</Title>
    </Header>
    <div>
            <main>
                <div class="book" @mouseover="book_open = true" @mouseleave="book_open = false, login = true, register = false">
                    <div class="book-cover" :class="{'index_up': (register == false && login == true) || book_open == false}">
                        <div class="init">
                            <h1>Inicia <br>Sesión</h1>
                            <div class="separator"></div>
                            <h2>by MyFood</h2>
                        </div>
                    </div>
                    <div class="book-content" :class="{'book-content-hover' : register && book_open, 'index_up': book_open && login == true}">
                        <div class="login">
                            <div class="title">
                                <h1>{{ $t('auth.login') }}</h1>
                                <p class="slogan">¿Ya tienes cuenta? Entra y pide algo bueno.</p>
                            </div>
                            <div class="inputs">
                                <LayoutCustomInput :stepCollection="loginInputs"/>
                                <button @click="submit_login()">{{ $t('auth.login') }}</button>
                            </div>
                            <div class="change-register">
                                <p>¿Todavía no tienes cuenta?</p>
                                <button @click="register = true, login = false, isLogin = true">{{ $t('auth.register') }}</button>
                            </div>
                        </div>
                    </div>
                    <div class="back-cover" :class="{'dpnone' : book_open == false}">
                        <div class="register">
                            <div class="title">
                                <h1>{{ $t('auth.register') }}</h1>
                                <p class="slogan">Registrate hoy mismo y empieza a disfrutar de la buena comida.</p>
                            </div>
                            <div class="inputs p-4 w-fit h-fit">
                                <LayoutCustomInput :stepCollection="registerInputs" :fill="white"/>
                                <button @click="submit_register()">{{ $t('auth.register') }}</button>
                            </div>
                            <!-- <div class="change-login">
                                <p>¿Ya tienes cuenta?</p>
                                <button @click="register = false, opening_login(), isLogin = true">{{ $t('auth.login') }}</button>
                            </div> -->
                        </div>
                    </div>
                </div>
            </main>
    </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Cinzel:wght@400;500&family=Petemoss&display=swap");
.dpnone {
	display: none !important;
}
main {
	display: flex;
	justify-content: center;
	background-color: transparent;
}
.slogan {
	margin-top: 10px;
	text-align: center;
	font-size: 1.2rem;
	font-weight: 600;
}
.book {
	min-width: 60vh;
	--book-height: 70vh;
	--book-ratio: 1.2;
	transition: 2s;
}
.book:hover {
	transform: translateX(50%);
}
.book > div {
	height: var(--book-height);
	width: calc(var(--book-height) / var(--book-ratio));
	overflow: auto;
	background-color: #0a0a0a;
	transform: scale(0.9);
	border-radius: 6px;
	transform-origin: left;
}
.book-cover {
	display: flex;
	justify-content: center;
	align-items: center;
	position: absolute;
	z-index: -1;
	text-align: center;
	background: linear-gradient(135deg, black 25%, transparent 25%) -50px 0,
		linear-gradient(225deg, black 25%, transparent 25%) -50px 0,
		linear-gradient(315deg, black 25%, transparent 25%),
		linear-gradient(45deg, black 25%, transparent 25%);
	background-size: 2em 2em;
	background-color: #232323;
	color: white;
	transition: transform 2s;
}
.book-cover::before {
	content: "";
	position: absolute;
	width: 20px;
	right: 20px;
	top: 0;
	bottom: 0;
	background-color: #b11509;
}
.back-cover {
	display: flex;
	justify-content: center;
	align-items: center;
	top: 0;
	position: absolute;
	z-index: -1;
	text-align: center;
	background: linear-gradient(135deg, black 25%, transparent 25%) -50px 0,
		linear-gradient(225deg, black 25%, transparent 25%) -50px 0,
		linear-gradient(315deg, black 25%, transparent 25%),
		linear-gradient(45deg, black 25%, transparent 25%);
	background-size: 2em 2em;
	background-color: #232323;
	color: white;
	transition: transform 2s;
}
.book .title h1 {
	text-align: center;
	font-size:5rem;
	font-weight: 500;
}
.separator {
	--separator-size: 8px;
	width: var(--separator-size);
	height: var(--separator-size);
	background-color: #dbd75d;
	margin: 50px auto 60px auto;
	border-radius: 50%;
	position: relative;
}
.separator::after,
.separator::before {
	content: "";
	position: absolute;
	width: 12px;
	background-color: white;
	height: 2px;
	top: calc(50% - 1px);
}
.separator::after {
	left: 15px;
}
.separator::before {
	right: 15px;
}
.book-content {
	z-index: 9 !important;
	background-color: white !important;
	transition: transform 1s !important;
}
.book h1 {
	font-family: "Petemoss", cursive;
	font-size: 7rem;
	font-weight: 300;
	color: #dbd75d;
}
.book h2 {
	font-size: 1.2rem;
}
.book h3 {
	padding: 30px;
}
.login {
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	height: 100%;
	width: 100%;
	align-items: center;
	justify-content: center;
	padding: 10px;
	text-align: center;
	font-size: 1rem !important;
}
.register {
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	height: 100%;
	width: 100%;
	align-items: center;
	justify-content: space-between;
	padding: 10px;
	text-align: center;
	font-size: 1rem !important;
}
.login .inputs, .register .inputs {
	box-sizing: border-box;
	height: 50%;
	width: 100%;
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	gap: 20px;
}
.login .inputs .multiple-inputs, .register .inputs .multiple-inputs {
	width: 100%;
}
.login .change-register {
	font-size: 1.5rem;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	gap: 10px;
	justify-content: center;
	align-items: center;
}
.register .change-login {
	font-size: 1.2rem;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	gap: 10px;
	justify-content: center;
	align-items: center;
}
.book-cover > div {
	transition: opacity 0s 0.6s;
}
.book:hover > .book-cover {
	transform: rotateY(180deg) scale(0.9);
}
.book-content-hover {
	transform: rotateY(180deg) scale(0.9) !important;
	z-index: 10;
	position: absolute;
	transition: transform 2s !important;
}
.book-content > * {
	transition-delay: .4s;
	transition-property: visibility;
	visibility: visible;
}
.book-content-hover > * {
	transition-delay: .5s;
	transition-property: visibility;
	visibility: hidden;
}
.book:hover > .book-cover > .init{
	transition-delay: .6s;
	transition-property: visibility;
	visibility: hidden;
	
}
.book > .book-cover > .init {
	transition-delay: .6s;
	transition-property: visibility;
	visibility: visible;
}
.book:hover > .book-cover > .back-cover > div {
	display: flex;
	justify-content: flex-start;
	flex-direction: column;
	width: 100%;
	height: 100%;
}
.book:hover > .book-content {
	transform: scale(0.9) translateY(0px);
}
.book:hover > .book-content h3,
.book:hover > .book-content p {
	opacity: 1;
}
button {
	padding: 10px;
	width: fit-content;
	height: fit-content;
	background: transparent;
	-webkit-animation: jello-horizontal 0.9s both;
	animation: jello-horizontal 0.9s both;
	border: 2px solid #b11509;
	outline: none;
	color: #b11509;
	cursor: pointer;
	font-size: 1.5rem;
	text-transform: capitalize;
}
button:hover {
	background: #b11509;
	color: #ffffff;
	animation: front-page 1s both;
}
   
   @keyframes front-page {
	0% {
	 -webkit-transform: scale3d(1, 1, 1);
	 transform: scale3d(1, 1, 1);
	}
   
	30% {
	 -webkit-transform: scale3d(1.25, 0.75, 1);
	 transform: scale3d(1.25, 0.75, 1);
	}
   
	40% {
	 -webkit-transform: scale3d(0.75, 1.25, 1);
	 transform: scale3d(0.75, 1.25, 1);
	}
   
	50% {
	 -webkit-transform: scale3d(1.15, 0.85, 1);
	 transform: scale3d(1.15, 0.85, 1);
	}
   
	65% {
	 -webkit-transform: scale3d(0.95, 1.05, 1);
	 transform: scale3d(0.95, 1.05, 1);
	}
   
	75% {
	 -webkit-transform: scale3d(1.05, 0.95, 1);
	 transform: scale3d(1.05, 0.95, 1);
	}
   
	100% {
	 -webkit-transform: scale3d(1, 1, 1);
	 transform: scale3d(1, 1, 1);
	}
   }
.index_up {
	z-index: 900 !important;
}
</style>