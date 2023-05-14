<script setup>
import { jsPDF } from "jspdf"
import { useGetReserveById } from "~/composables/reserves/useReserves"
import secret from "~/services/secret";

const id = useRoute().params.id
const { reserve } = (await useGetReserveById(id)).value
const doc = new jsPDF({
    orientation: "l",
    format: [180, 360],
});

const toDataUrl = (url, cb) => {
    let image = new Image()
    image.setAttribute('crossOrigin', 'annonymous')

    image.onload = function() {
        let canvas = document.createElement('canvas')
        canvas.width = 1920
        canvas.height = 1080

        let ctx = canvas.getContext('2d')
        ctx.fillStyle = "#FFFFFF"
        ctx.fillRect(0, 0, canvas.width, canvas.height)

        ctx.drawImage(this, 0, 0)
        cb(canvas.toDataURL("image/jpeg"))
    }


    image.src = url;
}

const generatePDF = async () => {
    Object.keys(reserve).forEach((e, index) => {
        doc.setTextColor(0, 0, 0)
        doc.text(125, 70 + index * 10, e)
        doc.text(45, 70 + index * 10, reserve[e])
    })

    // TODO: Make the background of pdf, generate it and download it
    // toDataUrl(`${secret.DEFAULT_URL}/`)
}


</script>

<template>
    <Header>
        <Title>Información de la Reserva</Title>
    </Header>

    <div class="flex items-center justify-center w-full h-screen">
        <div class="bg-white rounded-lg shadow-2xl w-1/2 h-1/2 flex flex-col p-4">
            <div class="flex items-center justify-center uppercase font-medium">
                <h1>{{ $t('pdf.info') }}</h1>
            </div>
            <div class="flex flex-col gap-4 p-8 font-thin">
                <p>{{ $t('pdf.part1') }}</p>
                <p>{{ $t('pdf.part2') }}</p>
                <p>{{ $t('pdf.part3') }}</p>
                <p>{{ $t('pdf.part4') }}</p>
                <p>{{ $t('pdf.part5') }}</p>
            </div>
            <div>
                <LayoutButton :title="$t('pdf.download')" />
            </div>
        </div>
    </div>
    <pre>{{ reserve }}</pre>
</template>
