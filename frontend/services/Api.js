import axios from "axios"

export default (URL) => {
    // Create Axios instance to make Http petitions
    const axiosInstance = axios.create({
        baseURL: URL
    })

    // TODO: Interceptors for Token

    return axiosInstance
}