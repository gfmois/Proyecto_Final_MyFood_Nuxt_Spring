import CategoryService from "~/services/categories/CategoryService";

export const useGetCategories = async () => {
    const categories = ref([])
    try {
        categories.value = await CategoryService.getCategories()
    } catch (error) {
        console.log(error);
    }

    return categories
}
