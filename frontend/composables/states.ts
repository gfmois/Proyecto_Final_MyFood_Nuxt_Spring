// FIXME: Move this to another folder - Modules
export const useShoppingCart = () => {
  // Global State Array
  const cart: Ref<any[]> = useState("cart", () => []);

  // Actions - Mutations
  
  // Adds or updates the product/item in the cart store
  const setItem = (item: any) => {
    let index = cart.value.findIndex(
      (e: any) => e.id_product == item.id_product
    );

    if (index == -1 && item.quantity > 0) {
      cart.value.push(item);
    }

    if (index >= 0) {
      item.quantity == 0
        ? cart.value.splice(1, index)
        : (cart.value[index] = item);
    }
  };

  // Removes the item / product from the cart store
  const removeItem = (item: any) => {
    let index = cart.value.findIndex((e) => e.id_product == item.id_product)
    console.log('Inside');
    if (index != -1) {
        cart.value.splice(index, 1)
    }
  }

  // Sets the cart store to empty array
  const clearStore = () => (cart.value = []);

  // Before route changes the cart becomes an empty array
  // This is because you can't make an order of diferents restaurants at the time
  onBeforeRouteLeave(() => {
    cart.value = [];
  });

  return { cart, setItem, clearStore, removeItem };
};
