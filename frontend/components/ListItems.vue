<script setup>
const { object } = defineProps({
    object: Object
})

const keys = reactive(computed(() => Object.keys(object)))
const values = reactive(computed(() => Object.values(object)))
</script>

<template>
    <table class="min-w-full divide-y divide-gray-200">
        <thead class="bg-gray-50">
          <tr>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider" v-for="key in keys">
              {{ key }}
            </th>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
              Acctions
            </th>
          </tr>
        </thead>
        <tbody class="bg-white divide-y divide-gray-200">
          <tr>
            <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900" v-for="value in values">
              {{ value }}
            </td>
            <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-blue-500 uppercase tracking-wider">
              <p class="cursor-pointer w-fit h-fit">Editar</p>
            </th>
          </tr>
        </tbody>
      </table>

</template>
