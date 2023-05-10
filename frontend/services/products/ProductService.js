import s from "../secret"

export default {
    async getProducts() {
        try {
            const response = await fetch(`${s.DEFAULT_URL}/products`)
            return await response.json()
          } catch (error) {
            console.log(error)
            return null
          }
    },
    async getRestaurantProducts(id_restaurant) {
        try {
            const response = await fetch(`${s.DEFAULT_URL}/restaurants`)
            return await response.json()
          } catch (error) {
            console.log(error)
            return null
          }
    },
    async createProduct(product) {
      try {
        const formData = new FormData()

        Object.keys(product).forEach((e) => {
          if (product[e] instanceof File) {
            formData.append(e, product[e], product[e].name);
          } else {
            formData.append(e, product[e]);
          }
        });


        console.log(formData);

        const response = await fetch('http://localhost:8080/api/products', {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${useCookie('token_admin').value}`
          },
          body: formData,
        });

        return await response.json()
      } catch (error) {
        console.log(error);
        return { status: 400, message: "Error creating the product" }
      }
    }
}
