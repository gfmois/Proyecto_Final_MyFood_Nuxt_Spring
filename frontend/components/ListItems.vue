<script setup>
import flatpickr from 'flatpickr'
import { useGetBannedDays } from '~~/composables/reserves/useReserves'
import { useAuth } from '~~/store';

import 'flatpickr/dist/flatpickr.min.css'

const { user } = useAuth()
const { object, keyBanned, keyPreffered, hasActionButtons, idObj, toSee, actionsKeys, onlyIsAdmin, showDescription } = defineProps({
  object: Array,
  keyBanned: String,
  keyPreffered: String,
  hasActionButtons: true,
  idObj: "",
  toSee: [],
  onlyIsAdmin: Boolean,
  showDescription: {
    type: Boolean,
    default: () => false
  },
  actionsKeys: {
    type: Array,
    default: () => ['Ver', 'Cancelar']
  }
});

const keys = reactive({})
const values = reactive({})
const isBannedModal = ref(false)
const openEditModal = ref(false)
const flatpickrInput = ref(null)
const bannedDays = ref(computed(() => []).value)
const selectedItems = ref()
const copy = reactive(computed(() => object)).value
const copySelected = computed(() => values.value[0])

const objCopy = object.map((e) => { delete e.id_restaurant; return e })

if (Array.isArray(objCopy)) {
  keys.value = Object.keys(objCopy[0])
  values.value = objCopy.map((e) => Object.values(e))
}

const subValues = ref([])

const load = () => {
  const keys = Object.keys(selectedItems.value[0])
  const values = Object.values(selectedItems.value[0])

  subValues.value = []

  values.forEach((item) => {
    if (typeof item == "object") {
      subValues.value.push(item)
    }
  })
}

const handleFileInputChange = (index, event) => {
  const file = event.target.files[0];

  selectedItems.value[index] = file;
};

const checkIfVisible = (index) => {
  try {
    if (user.value.type == 'ADMIN') {
      if (!toSee.includes(keys.value[index])) {
        loadDatepicker()
        return true
      }

      return false
    }

    return true
  } catch (error) {
    return false
  }
}

watch(values, async (v, pv) => {
  loadBannedDays()
})


const loadBannedDays = async () => {
  const item = {
    id_restaurant: idObj
  }

  selectedItems.value.forEach((e, index) => {
    if (keys.value[index] == 'diners' || keys.value[index] == 'types') {
      item[keys.value[index]] = e
    }
  })

  const dates = (await useGetBannedDays(item)).value
  bannedDays.value = dates
}

const loadDatepicker = () => {
  nextTick(() => {
    flatpickr(flatpickrInput.value, {
      minDate: "today",
      dateFormat: "Y-m-d",
      disable: bannedDays.value
    })
  })
}
const onlyAdmin = ref(true)

onMounted(() => {
  if (onlyIsAdmin) {
    if (user.value.type === 'ADMIN') {
      onlyAdmin.value = true;
    } else {
      onlyAdmin.value = false;
    }
  } else {
    onlyAdmin.value = true;
  }
});


</script>

<template>
  <div class="jrs-table-scroller">
    <table class="divide-y divide-gray-200 jrs-table w-full">
      <thead class="bg-gray-50 text-center">
        <tr>
          <th scope="col" class="px-6 py-3 text-xs font-medium text-gray-500 uppercase tracking-wider text-center"
            v-for="key in keys.value">
            <p v-if="key != 'description'">{{ key }}</p>
            <p v-if="showDescription && key == 'description'">{{ key }}</p>
          </th>
          <th scope="col" class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"
            v-if="hasActionButtons">
            Acctions
          </th>
        </tr>
      </thead>
      <tbody class="bg-white divide-y divide-gray-200 text-center">
        <tr v-for="value, indexValue in values.value">
          <td class="px-6 py-4 whitespace-nowrap text-center text-sm font-medium text-gray-900"
            v-for="(item, index) in value">
            <p v-if="keys.value[index] != keyBanned && keys.value[index] != 'description'">{{ item }}</p>
            <p v-if="showDescription && keys.value[index] == 'description'">{{ item }}</p>
            <p class="text-blue-500 cursor-pointer" v-if="keys.value[index] == keyBanned"
              @click="() => { isBannedModal = true; selectedItems = item; load() }">{{ keyPreffered }}</p>
          </td>
          <td scope="col" class="px-6 py-4 whitespace-nowrap text-center text-sm font-medium text-gray-900"
            v-if="hasActionButtons">
            <div class="flex gap-x-3 items-center justify-center w-full h-full">
              <p class="cursor-pointer w-fit h-fit text-blue-500"
                @click="() => { openEditModal = true; selectedItems = value }">{{ actionsKeys[0] }}</p>
              <p v-if="onlyAdmin" class="cursor-pointer w-fit h-fit text-red-500" @click="() => { $emit('indexCancel', indexValue); $emit('cancel', copy) }">{{ actionsKeys[1] }}
              </p>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>

  <div v-if="isBannedModal" class="absolute w-full h-full bg-black/40 left-0 top-0 p-4 flex items-center justify-center">
    <div class="bg-gray-800 p-4 w-2/3 h-2/3 rounded-lg shadow-2xl">
      <div class="flex flex-row justify-between px-4 items-center">
        <div>
          {{ keyPreffered }}
        </div>
        <div class="hover:border cursor-pointer rounded-lg flex items-center justify-center"
          @click="() => isBannedModal = false">
          <Icon name="ep:close-bold" class="cursor-pointer" />
        </div>
      </div>

      <div class="w-full h-full flex items-center justify-center">
        <div class="p-4 w-full h-5/6">
          <ListItems :object="subValues" />
        </div>
      </div>
    </div>
  </div>

  <div v-if="openEditModal" class="absolute w-full h-full bg-black/40 left-0 top-0 p-4 flex items-center justify-center">
    <div class="bg-gray-800 p-4 w-2/3 h-fit rounded-lg shadow-2xl">
      <div class="flex flex-row justify-between px-4 items-center">
        <div>
          Editar
        </div>
        <div class="hover:border cursor-pointer rounded-lg flex items-center justify-center"
          @click="() => openEditModal = false">
          <Icon name="ep:close-bold" class="cursor-pointer" />
        </div>
      </div>
      <div class="p-4">
        <div class="p-4 grid grid-cols-2 gap-4">
          <div class="mb-4" v-for="v, index in selectedItems">
            <div v-if="keys.value[index] != keyBanned">
              <label class="block text-white font-bold mb-2" :for="v">
                {{ keys.value[index] }}
              </label>
              <input v-if="!keys.value[index].includes('date') && !keys.value[index].includes('image')"
                :class="`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline disabled:bg-white/70 ${checkIfVisible(index) ? 'cursor-not-allowed' : 'cursor-text'}`"
                :id="v" type="text" :placeholder="selectedItems[index]" v-model="selectedItems[index]"
                :disabled="checkIfVisible(index)">
              <input v-if="!keys.value[index].includes('date') && keys.value[index].includes('image')"
                @change="handleFileInputChange(index, $event)"
                :class="`shadow text-white border rounded w-full py-2 px-3 bg-gray-800 leading-tight focus:outline-none focus:shadow-outline disabled:bg-white/70 ${checkIfVisible(index) ? 'cursor-not-allowed' : 'cursor-text'}`"
                :id="v" type="file" placeholder="Imagen" :disabled="checkIfVisible(index)">
              <input v-if="keys.value[index].includes('date') && !keys.value[index].includes('image')"
                :class="`shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline disabled:bg-white/70 ${checkIfVisible(index) ? 'cursor-not-allowed' : 'cursor-text'}`"
                :id="v" type="text" :placeholder="selectedItems[index]" v-model="selectedItems[index]"
                :disabled="checkIfVisible(index)" ref="flatpickrInput">
            </div>
          </div>
        </div>
      </div>
      <div class="h-fit py-4 px-8 flex items-center justify-end">
        <LayoutButton title="Actualizar" buttonType="green" v-if="user.type == 'ADMIN'"
          @click="() => { $emit('update', selectedItems); values.value[0] = copySelected; openEditModal = false }" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.jrs-table-scroller {
  width: 100%;
  overflow-x: scroll;
  box-sizing: border-box;
}
</style>
