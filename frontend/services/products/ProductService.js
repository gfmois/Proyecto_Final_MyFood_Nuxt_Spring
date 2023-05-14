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

        const response = await fetch(`${s.DEFAULT_URL}/products`, {
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
    },
    async getProductsByRestaurant(id_restaurant) {
      try {
        const result = await fetch(`${s.DEFAULT_URL}/products/restaurant?id_restaurant=${id_restaurant}`, {
          headers: {
            Authorization: `Bearer ${useCookie('token_admin').value}`,
            'Content-Type': "application/json"
          }
        })
        return await result.json()
      } catch (error) {
        console.log(error);
        return { status: 400, message: "Error getting the products" }
      }
    },
    async updateProduct(product) {
      try {
        const body = new FormData()
        Object.keys(product).forEach((e) => {
          if (product[e] instanceof File) {
            body.append(e, product[e], product[e].name);
          } else {
            body.append(e, product[e]);
          }
        });


        const response = await fetch(`${s.DEFAULT_URL}/products`, {
          method: 'PUT',
          headers: {
            Authorization: `Bearer ${useCookie('token_admin').value}`,
          },
          body
        })

        return await response.json()
      } catch (error) {
        console.log(error);
        return {
          status: 400,
          message: "Error Updating the product"
        }
      }
    },
    async deleteProduct(id_product) {
      try {
        const response = await fetch(`${s.DEFAULT_URL}/products/${id_product}`, {
          method: "DELETE",
          headers: {
            Authorization: `Bearer ${useCookie('token_admin').value}`
          }
        })

        return await response.json()
      } catch (error) {
        console.log(error);
        return null
      }
    }
}
