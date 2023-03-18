import Api from "../Api";

export default {
    getRestaurants() {
        // TODO: Secret.js with the URL
        return Api('http://localhost:8080/api').get("/restaurants")
    }
}