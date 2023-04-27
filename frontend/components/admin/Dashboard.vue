<script setup>
const labels = ['enero', 'febrero', 'marzo', 'abril', 'mayo', 'junio', 'julio'];
const testData = {
  labels: labels,
  datasets: [{
    label: 'My First Dataset',
    data: [65, 59, 80, 81, 56, 55, 40],
    backgroundColor: [
      'rgba(255, 99, 132, 0.2)',
      'rgba(255, 159, 64, 0.2)',
      'rgba(255, 205, 86, 0.2)',
      'rgba(75, 192, 192, 0.2)',
      'rgba(54, 162, 235, 0.2)',
      'rgba(153, 102, 255, 0.2)',
      'rgba(201, 203, 207, 0.2)'
    ],
    borderColor: [
      'rgb(255, 99, 132)',
      'rgb(255, 159, 64)',
      'rgb(255, 205, 86)',
      'rgb(75, 192, 192)',
      'rgb(54, 162, 235)',
      'rgb(153, 102, 255)',
      'rgb(201, 203, 207)'
    ],
    borderWidth: 1
  }]
};
</script>

<template>
  <div class="max-h-full">
    <div class="container mx-auto p-4 h-full w-full">
      <div class="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-4 w-full h-full">
        <div class="bg-white rounded-lg shadow-lg p-4">
          <h2 class="text-lg font-bold mb-2">Card 1</h2>
          <AdminChart type="bar" :data="testData" />
          <!-- aquí va el gráfico 1 -->
        </div>
        <div class="bg-white rounded-lg shadow-lg p-4">
          <h2 class="text-lg font-bold mb-2">Card 2</h2>
          <AdminChart type="bar" :data="testData" />
          <!-- aquí va el gráfico 2 -->
        </div>
        <div class="bg-white rounded-lg shadow-lg p-4">
          <h2 class="text-lg font-bold mb-2">Card 3</h2>
          <AdminChart type="bar" :data="testData" />
          <!-- aquí va el gráfico 3 -->
        </div>
      </div>
      <div class="w-full h-full flex items-center justify-center">
        <div class="bg-white rounded-lg shadow-lg p-4 w-2/4 h-1/3 col-span-3">
          <h2 class="text-lg font-bold">Large Chart</h2>
          <AdminChart type="bar" :data="testData" />
          <!-- aquí va el gráfico grande -->
        </div>
      </div>
    </div>
  </div>
</template>
