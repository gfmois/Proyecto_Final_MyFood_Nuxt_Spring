package com.pf_nxsp_myfood.backend.domain.products.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pf_nxsp_myfood.backend.domain.common.utils.FileUpload;
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

	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();

	@Autowired
	private FileUpload fileUpload;

	// GET Method returns all Products
	@Cacheable(value = "products")
	@GetMapping
	public List<ProductDto> getProducts() {
		return pService.getProducts();
	}

	// POST method to save new product into restaurant and save and image to
	// identify it in the frontend
	@CacheEvict(value = "products", allEntries = true)
	@PostMapping(consumes = { "multipart/form-data" })
	public MessageResponse createProducts(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("slug") String slug,
			@RequestParam("price") String price,
			@RequestParam("id_restaurant") String restaurant) {
		try {
			// Body to ProductDto
			ProductDto pDto = new ProductDto();

			// Saves the image and gets the full path of the file to save it into DB
			Map<String, Object> fileObj = fileUpload.saveFile("products", file);

			// Checks for an error
			if ((Integer) fileObj.get("statusCode") == 400) {
				return new MessageResponse("Error trying to uploading the image",
						String.valueOf((Integer) fileObj.get("status")));
			}

			// Checks if price is greater than 0
			if (Integer.valueOf(price) <= 0) {
				return new MessageResponse("Price myst be grater than 0", "400");
			}

			// Transform FormData to ProductDto
			pDto.setId_product(IdGenerator.generateWithLength(20));
			pDto.setName(name);
			pDto.setSlug(slug);
			pDto.setImage((String) fileObj.get("path").toString());
			pDto.setPrice(price);
			pDto.setRestaurant(restaurant);

			return pService.saveProduct(pDto);
		} catch (Exception e) {
			// Sends error to client and reports the error into the console
			System.out.println(e.getMessage());
			return new MessageResponse("Error trying to create the product", "400");
		}
	}

	@CacheEvict(value = "products", allEntries = true)
	@PutMapping(consumes = { "multipart/form-data" })
	public MessageResponse updateProduct(
			@RequestParam("id_product") String id_product,
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("slug") String slug,
			@RequestParam("price") String price) {
		try {
			// New Instance of ProductDto
			ProductDto pDto = new ProductDto();

			// Saves the new image and gets the full path of the file to update it into DB
			Map<String, Object> filePathObj = fileUpload.saveFile("products", file);

			// Checks for an error
			if ((Integer) filePathObj.get("statusCode") == 400) {
				return new MessageResponse("Error trying to uploading the image",
						String.valueOf((Integer) filePathObj.get("status")));
			}

			// Checks if price is greater than 0
			if (Integer.valueOf(price) <= 0) {
				return new MessageResponse("Price myst be grater than 0", "400");
			}

			// Transform FormData to ProductDto
			pDto.setId_product(id_product);
			pDto.setName(name);
			pDto.setSlug(slug);
			pDto.setImage((String) filePathObj.get("path").toString());
			pDto.setPrice(price);

			// Updates Product
			return pService.updateProduct(pDto);
		} catch (Exception e) {
			// Sends error to client and reports the error into the console
			System.out.println(e.getMessage());
			return new MessageResponse("Error trying to update the product", "400");
		}
	}

	@CacheEvict(value = "products", allEntries = true)
	@DeleteMapping("/{id_product}")
	public MessageResponse deleteProduct(@PathVariable String id_product) {
		return pService.deleteProduct(id_product);
	}
}
