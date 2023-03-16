package com.pf_nxsp_myfood.backend.domain.products.service;

import java.util.List;
import java.util.stream.Collectors;

import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;
import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;
import com.pf_nxsp_myfood.backend.domain.products.repository.ProductRepository;

public class ProductServiceImpl implements ProductService {
    private ProductRepository pRepository;

    private ProductDto convertEntityToDto(ProductEntity pEntity) {
		return ProductDto.builder()
				.id_product(pEntity.getId_product())
				.name(pEntity.getName())
				.slug(pEntity.getSlug())
				.image(pEntity.getImage())
                .price(pEntity.getPrice())
				.build();
	}

    @Override
    public List<ProductDto> getProducts() {
        return pRepository.findAll()
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public MessageResponse saveProduct(ProductDto pDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveProduct'");
    }

    @Override
    public MessageResponse updateProduct(ProductDto pDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProduct'");
    }

    @Override
    public MessageResponse deleteProduct(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

}
