<script setup>
const { object, keyBanned, keyPreffered } = defineProps({
  object: Array,
  keyBanned: String,
  keyPreffered: String
})

const keys = reactive({})
const values = reactive({})
const isBannedModal = ref(false)
const selectedItems = ref()

console.log(object);

if (Array.isArray(object)) {
  keys.value = Object.keys(object[0])
  values.value = object.map((e) => Object.values(e))
}

const subValues = ref([])

const load = () => {
  const keys = Object.keys(selectedItems.value[0])
  const values = Object.values(selectedItems.value[0])

  values.forEach((item) => {
    if (typeof item == "object") {
      subValues.value.push(item)
    }
  })
}

</script>

<template>
  <table class="min-w-full divide-y divide-gray-200">
    <thead class="bg-gray-50 text-center">
      <tr>
        <th scope="col" class="px-6 py-3 text-xs font-medium text-gray-500 uppercase tracking-wider text-center"
          v-for="key in keys.value">
          {{ key }}
        </th>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
          Acctions
        </th>
      </tr>
    </thead>
    <tbody class="bg-white divide-y divide-gray-200 text-center">
      <tr v-for="value in values.value">
        <td class="px-6 py-4 whitespace-nowrap text-center text-sm font-medium text-gray-900"
          v-for="(item, index) in value">
          <p v-if="keys.value[index] != keyBanned">{{ item }}</p>
          <p class="text-blue-500 cursor-pointer" v-if="keys.value[index] == keyBanned"
            @click="() => { isBannedModal = true; selectedItems = item; load() }">{{ keyPreffered }}</p>
        </td>
        <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-blue-500 uppercase tracking-wider">
          <p class="cursor-pointer w-fit h-fit">Editar</p>
        </th>
      </tr>
    </tbody>
  </table>


  <div v-if="isBannedModal" class="absolute w-full h-full bg-black/40 left-0 top-0 p-4 flex items-center justify-center">
    <div class="bg-crimson-600 p-4 w-2/3 h-2/3 rounded-lg shadow-2xl">
      <div class="flex flex-row justify-between px-4 items-center">
        <div>
          {{ keyPreffered }}
        </div>
        <div class="hover:border cursor-pointer rounded-lg flex items-center justify-center"
          @click="() => isBannedModal = false">
          <Icon name="ep:close-bold" class="cursor-pointer" />
        </div>
      </div>

      <div class="w-full h-full flex items-start justify-center">
        <div class="p-4 w-full h-5/6">
          <ListItems :object="subValues" />
        </div>
      </div>
    </div>
  </div>
</template>
