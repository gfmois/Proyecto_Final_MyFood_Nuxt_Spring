<template>
    <div>
      <LayoutLoading v-if="isLoading" />
      <canvas v-if="!isLoading" ref="canvas"></canvas>
    </div>
  </template>
  
  <script setup>
    import QRCode from 'qrcode'
  
    const { url, isLoading } = defineProps({
      url: String,
      isLoading: Boolean
    })
  
    const canvas = ref(null)
  
    const options = ref({
      width: 256,
      height: 256,
      color: {
        dark: '#000000',
        light: '#ffffff'
      },
      errorCorrectionLevel: 'H',
      margin: 1,
      scale: 1,
      version: 6,
      type: 'image/png'
    })
  
    function loadQr(url) {
      QRCode.toCanvas(canvas.value, url, options.value, (error) => {
        if (error) console.error(error)
      })
    }
  
    onMounted(() => {
      console.log(url)
      loadQr(url.url)
    })
  
    watch(url, (v, pv) => {
      loadQr(v.url)
    })
  </script>
  