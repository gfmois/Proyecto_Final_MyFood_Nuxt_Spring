import ProductService from "~/services/products/ProductService"

export const useCreateProduct = async (product) => {
    const response = ref({})
    try {
        response.value = await ProductService.createProduct(product)
    } catch (error) {
        console.log(error);
    }

    return response
}
