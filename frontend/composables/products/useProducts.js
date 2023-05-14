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

export const useGetProductsByRestaurant = async (id_restaurant) => {
    const res = ref([])
    try {
        res.value = await ProductService.getProductsByRestaurant(id_restaurant)
    } catch (error) {
        console.log(error);
    }

    return res;
}

export const useUpdateProduct = async (product) => {
    const productUpdated = ref({})
    try {
        productUpdated.value = await ProductService.updateProduct(product)
    } catch (error) {
        console.log(error);
    }

    return productUpdated
}

export const useDeleteProduct = async (id_product) => {
    const result = ref({})
    try {
        result.value  = await ProductService.deleteProduct(id_product)
    } catch (error) {
        console.log(error);
    }

    return result
}
