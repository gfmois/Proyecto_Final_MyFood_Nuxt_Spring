<script>
export default {
    props: {
        stepCollection: Object,
        scale2: Boolean,
        fill: String
    },
    data() {
        return this.stepCollection
    },
    setup() {
        const tagToHtml = (element) => {
            let open_parent = `<${element.tag}>`;
            if (element.children) {
                element.value
                    ? element.children.map((e) => open_parent += `<${e.tag} :value="${e.value}">${e.name}</${e.tag}>`)
                    : element.children.map((e) => open_parent += `<${e.tag} :value="${e.name}">${e.name}</${e.tag}>`)

                return open_parent += `</${element.tag}>`;
            }
            return `<${element.tag}>${element.value}</${element.tag}>`
        }
        return { tagToHtml }
    }
}
</script>

<template>
    <div class="form__group field" v-for="input in stepCollection.children" :key="input.name"
        v-if="stepCollection.children" :class="{ 'scale2': scale2 }">
        <input v-if="!input.tag" :placeholder="input.name" :min="input.min ? input.min : null" :type="input.type"
            class="form__field" :required="input.required" :disabled="input.disabled" v-model="input.value" />
        <label class="form__label">{{ input.name }}</label>

        <select v-model="input.value" v-if="input.tag" v-html="tagToHtml(input)" />

    </div>
    <div v-if="!stepCollection.children" class="form__group_large field">
        <input v-if="!stepCollection.tag" :placeholder="stepCollection.name"
            :min="stepCollection.min ? stepCollection.min : null" :type="stepCollection.type" class="form__field"
            :required="stepCollection.required" :disabled="stepCollection.disabled" v-model="stepCollection.value" />
        <label class="form__label">{{ stepCollection.name }}</label>
    </div>
</template>

<style scoped>
input {
    color: v-bind('fill') !important;
}

.scale2,
.scale2 input,
.scale2 label {
    font-size: 2rem;
}

select {
    background-color: rgba(211, 207, 207, 0.507);
    border: none;
    margin-top: 10px;
    font-size: 1rem;
    height: 40px;
    padding: 5px;
    width: 100%;
}

select:focus {
    outline: none;
}

.form__group {
    position: relative;
    padding: 20px 0 0;
    margin-top: 10px;
    width: 80%;
    margin: 0 auto;
    display: flex;
}

.form__group_large {
    position: relative;
    padding: 20px 0 0;
    margin-top: 10px;
    width: 80%;
}

.form__field {
    font-family: inherit;
    width: 100%;
    border: none;
    border-bottom: 2px solid #9b9b9b;
    outline: 0;
    font-size: 1.2rem;
    color: rgb(0, 0, 0);
    padding: 7px 0;
    background: transparent;
    transition: border-color 0.2s;
    display: flex;
}

.form__field::placeholder {
    color: transparent;
}

.form__field:placeholder-shown~.form__label {
    font-size: 1.2rem;
    cursor: text;
    top: 20px;
}

.form__label {
    position: absolute;
    top: 0;
    display: block;
    transition: 0.2s;
    font-size: 1.2rem;
    color: #9b9b9b;
    pointer-events: none;
}

.form__field:focus {
    padding-bottom: 6px;
    font-weight: 700;
    border-width: 3px;
    border-image: linear-gradient(to right, #116399, #38caef);
    border-image-slice: 1;
}

.form__field:focus~.form__label {
    position: absolute;
    top: 0;
    display: block;
    transition: 0.2s;
    font-size: 1.2rem;
    color: #38caef;
    font-weight: 700;
}

/* reset input */
.form__field:required,
.form__field:invalid {
    box-shadow: none;
}
</style>