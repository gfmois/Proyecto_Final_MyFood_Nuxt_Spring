<template>
    <div class="cart">
        <div class="header p-1 cursor-pointer">
              <Icon name="material-symbols:shopping-cart" size="2rem" class="text-crimson-500" />
        </div>
        <div class="body content">
            <!-- <ProductsList :key="products" :products="cart" :isCart="true"></ProductsList> -->
        </div>
        <div class="footer content">
            <button>{{ $t('open_cart') }}</button>
        </div>
    </div>
</template>

<style scoped>
.cart {
    all: unset;
    box-sizing: border-box;
    background-color: white;
    padding: 10px;
    width: 60px;
    height: 60px;
    border-radius: 40px;
    bottom: 25px;
    right: 25px;
    z-index: 2;
    position: fixed;
    display: flex;
    transition: all .5s ease-in-out;
    flex-direction: column;
    align-items: flex-start;
    overflow: hidden;
    font-weight: 900;
}

.cart .header {
    display: flex;
    align-items: center;
    justify-items: center;
    width: 100%;
}
.cart .body {
    box-sizing: border-box;
    padding-top: 10px;
    height: 80%;
    width: 100%;
}
.cart .footer {
    box-sizing: border-box;
    height: 10%;
    width: 100%;
    display: flex;
}
.cart .footer button {
    all: unset;
    float: right;
    color: white;
    padding: 10px;
    border-radius: 20px;
    background-color: orange;
}
.cart:hover {
    transition: all .5s ease-in-out;
    padding: 20px;
    width: 40vw;
    height: 40vh;
}
.cart .content {
    display: none;
}
.cart:hover .content{
    display: block;
}

@media(max-width: 1000px) {
    .cart:hover {
        width: 90vw;
        height: 60vh;
    }
}
</style>