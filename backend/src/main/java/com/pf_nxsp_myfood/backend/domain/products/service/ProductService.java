package com.pf_nxsp_myfood.backend.domain.products.service;

import java.util.List;

import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;

public interface ProductService {
    List<ProductDto> getProducts();
    ProductDto getProductById(String id_producto);
    MessageResponse saveProduct(ProductDto pDto);
    MessageResponse updateProduct(ProductDto pDto);
    MessageResponse deleteProduct(String id);
    List<ProductDto> getProductByRestaurantId(String id_restaurant);
}
