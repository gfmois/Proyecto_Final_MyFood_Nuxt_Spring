package com.pf_nxsp_myfood.backend.domain.products.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {
	@GetMapping
	public String helloWorld() {
		return "Hello World";
	}
}
