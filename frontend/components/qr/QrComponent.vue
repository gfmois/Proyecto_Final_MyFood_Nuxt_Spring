<template>
    <LayoutLoading v-if="showLoading"/>
    <canvas v-if="!showLoading" ref="canvas"></canvas>
</template>
  
<script setup>
import QRCode from 'qrcode'

const { url, isLoading } = defineProps({
    url: String,
    isLoading: Boolean
})

// TODO: text will be url to download the pdf
const text = ref(url)
const canvas = ref(null)
const showLoading = ref(isLoading || true)

const options = {
    width: 256,
    height: 256,
    color: {
        dark: '#000000',
        light: '#ffffff'
    },
    errorCorrectionLevel: 'H',
    margin: 1,
    scale: 1,
    version: 3,
    type: 'image/png'
}

function loadQr() {
    QRCode.toCanvas(canvas.value, text.value, options, (error) => {
        if (error) console.error(error)
    })
}
</script>
  