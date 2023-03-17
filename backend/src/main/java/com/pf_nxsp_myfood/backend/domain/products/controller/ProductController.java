package com.pf_nxsp_myfood.backend.domain.products.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.pf_nxsp_myfood.backend.domain.payload.request.product.ProductRequest;
import com.pf_nxsp_myfood.backend.domain.payload.response.MessageResponse;
import com.pf_nxsp_myfood.backend.domain.products.dto.ProductDto;
import com.pf_nxsp_myfood.backend.domain.products.service.ProductService;
import com.pf_nxsp_myfood.backend.plugins.IdGenerator;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {
	@Autowired
	private ProductService pService;

	@Autowired
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

	@GetMapping
	public List<ProductDto> getProducts() {
		return pService.getProducts();
	}

	@PostMapping
	public MessageResponse createProducts(@RequestBody @Valid ProductRequest body) {
		ProductDto pDto = new ProductDto();
		JsonObject json = gson.fromJson(gson.toJson(body), JsonObject.class);

		// TODO: Make File Upload

		pDto.setId_product(IdGenerator.generateWithLength(20));
		pDto.setName(json.get("name").getAsString());
		pDto.setSlug(json.get("slug").getAsString());
		pDto.setImage(json.get("image").getAsString()); // FIXME: Image will be the route of the file
		pDto.setPrice(json.get("price").getAsString());

		return pService.saveProduct(pDto);
	}
}
