import Vuex from "vuex"

import { cart } from "./modules/shoppingCart"

// FIXME: Store is not found
export default Vuex.createStore({
    modules: {
        cart
    }
})