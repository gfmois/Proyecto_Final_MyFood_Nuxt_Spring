import Constants from "../../Constants";

export const cart = {
    namespaces: true,
    state: {
        items: []
    },
    mutations: {
        [Constants.GET_CART]: (state, payload) => {
            state.items = "Adios"
        },
        [Constants.UPDATE_CART]: (state, payload) => {
            let index = state.items.findIndex((product)=> product.id_product === payload.id_product)

            if (index == -1 && payload.quantity > 0) {
                state.items.push(payload)
            } 
            
            if (index >= 0) {
                payload.quantity == 0
                    ? state.items.splice(index,1)
                    : state.items[index] = payload
            }
        }
    },
    actions: {
        [Constants.GET_CART]: (store, payload) => {
            store.commit(Constants.GET_PRODUCTS)
        },
        [Constants.UPDATE_CART]: (store, payload) => {
            store.commit(Constants.UPDATE_CART, payload)
        }

    }
}