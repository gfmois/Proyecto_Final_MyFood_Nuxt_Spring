<script>
import { jsPDF } from "jspdf"
import { useGetReserveById } from "~/composables/reserves/useReserves"
import secret from "~/services/secret";

export default {
    ssr: false,
    async setup() {
        const id = useRoute().params.id
        const { reserve } = (await useGetReserveById(id)).value
        const doc = new jsPDF({
            orientation: "l",
            format: [180, 360],
        });

        const toDataUrl = (url, cb) => {
            let image = new Image()
            image.setAttribute('crossOrigin', 'anonymous')

            image.onload = function () {
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

        console.log(reserve);

        const generatePDF = () => {
            toDataUrl(`${secret.DEFAULT_URL}/reserves/image/pdf`, (image) => {
                doc.addImage(image, "baseURL", 0, 0)

                // Crd
                doc.setFillColor(255, 255, 255)
                doc.rect(20, 20, 170, 125, "F")

                // Title
                doc.setTextColor(0, 0, 0);
                // doc.setFont("dancing", "italic");
                doc.text(95, 35, "MyFood");


                Object.keys(reserve).map((e, index) => {
                    doc.setTextColor(0, 0, 0);
                    doc.text(100, 70 + index * 10, String(reserve[e]));
                    doc.text(45, 70 + index * 10, e.toUpperCase());
                });

                setTimeout(() => doc.save(`Reserva_${reserve.name}_${reserve.date}`), 500)
            })
        }

        generatePDF()

        return { generatePDF }
    }
}

</script>

<template>
    <div>
        <Header>
            <Title>{{ $t('pdf.info') }}</Title>
        </Header>

        <div class="flex items-center justify-center w-full h-screen">
            <div class="bg-white rounded-lg shadow-2xl xs:w-5/6 lg:w-1/2 lg:h-1/2 flex flex-col p-4">
                <div class="flex items-center justify-center uppercase font-medium">
                    <h1>{{ $t('pdf.info') }}</h1>
                </div>
                <div class="flex flex-col gap-4 p-8 font-thin">
                    <p>{{ $t('pdf.part1') }}</p>
                    <!-- <p>{{ $t('pdf.part2') }}</p>
                    <p>{{ $t('pdf.part3') }}</p>
                    <p>{{ $t('pdf.part4') }}</p>
                    <p>{{ $t('pdf.part5') }}</p> -->
                </div>
                <div>
                    <LayoutButton :action="() => generatePDF()" :title="$t('pdf.download')" />
                </div>
            </div>
        </div>
    </div>
</template>
