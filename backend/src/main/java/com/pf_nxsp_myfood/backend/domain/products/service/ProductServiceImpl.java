package com.pf_nxsp_myfood.backend.domain.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;
import com.pf_nxsp_myfood.backend.domain.products.entity.ProductEntity;
import com.pf_nxsp_myfood.backend.domain.products.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Component
public class ProductServiceImpl implements ProductService {
    private final ProductRepository pRepository;

    private ProductDto convertEntityToDto(ProductEntity pEntity) {
        return ProductDto.builder()
                .id_product(pEntity.getId_product())
                .name(pEntity.getName())
                .slug(pEntity.getSlug())
                .image(pEntity.getImage())
                .price(pEntity.getPrice())
                .description(pEntity.getDescription())
                .restaurant(pEntity.getRestaurant().getId_restaurant())
                .build();
    }

    private ProductEntity convertDtoToEntity(ProductDto pDto) {
        return ProductEntity.builder()
                .id_product(pDto.getId_product())
                .name(pDto.getName())
                .image(pDto.getImage())
                .price(pDto.getPrice())
                .slug(pDto.getSlug())
                .description(pDto.getDescription())
                .id__restaurant(pDto.getRestaurant())
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
        if (pRepository.save(convertDtoToEntity(pDto)) != null) {
            return new MessageResponse("Product Created Correctly", "201");
        }

        return new MessageResponse("There was an problem creating the product", "401");
    }

    @Override
    public MessageResponse updateProduct(ProductDto pDto) {
        try {
            // Checks if Product exists
            if (!pRepository.existsById(pDto.getId_product())) {
                return new MessageResponse("No Product with that ID", "401");
            }

            // Get the Product from DB to Update it
            ProductEntity pEntity = pRepository.findById(pDto.getId_product()).get();

            // Update the Product
            pEntity.setImage(pDto.getImage());
            pEntity.setName(pDto.getName());
            pEntity.setPrice(pDto.getPrice());
            pEntity.setSlug(pDto.getSlug());
            pEntity.setDescription(pDto.getDescription());

            // Save Product updated
            pRepository.save(pEntity);

            return new MessageResponse("Product Updated Correctly", "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error updating: %s", e.getMessage()), "400");
        }
    }

    @Override
    public MessageResponse deleteProduct(String id) {
        try {
            pRepository.deleteById(id);
            return new MessageResponse(String.format("Product with ID '%s' deleted", id), "200");
        } catch (Exception e) {
            return new MessageResponse(String.format("Error: %s", e.getMessage()), "400");
        }
    }

    @Override
    public List<ProductDto> getProductByRestaurantId(String id_restaurant) {
        return pRepository.findAllProductByRestaurant(id_restaurant)
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductByRestaurantIdOrSlug(String id_restaurant) {
        return pRepository.getProductsByIdRestaurantOrRestaurantSlug(id_restaurant)
            .stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(String product_slug) {
        return convertEntityToDto(pRepository.findById_product(product_slug));
    }
}
