// FIXME: Move this to another folder - Modules
export const useShoppingCart = () => {
  // Global State Array
  const cart: Ref<any[]> = useState("cart", () => []);

  // Actions - Mutations
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

  const removeItem = (item: any) => {
    let index = cart.value.findIndex((e) => e.id_product == item.id_product)
    console.log('Inside');
    if (index != -1) {
        cart.value.splice(index, 1)
    }
  }

  const clearStore = () => (cart.value = []);

  onBeforeRouteLeave(() => {
    cart.value = []; // Borramos el estado del carrito de compras
  });

  return { cart, setItem, clearStore, removeItem };
};
