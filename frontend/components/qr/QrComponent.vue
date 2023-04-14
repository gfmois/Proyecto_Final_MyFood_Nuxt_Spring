<template>
    <canvas ref="canvas"></canvas>
</template>
  
<script setup>
import QRCode from 'qrcode'

const { url } = defineProps({
    url: String
})

// TODO: text will be url to download the pdf
const text = ref('https://example.com')
const canvas = ref(null)

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

onMounted(() => {
    QRCode.toCanvas(canvas.value, text.value, options, (error) => {
        if (error) console.error(error)
    })
})
</script>
  