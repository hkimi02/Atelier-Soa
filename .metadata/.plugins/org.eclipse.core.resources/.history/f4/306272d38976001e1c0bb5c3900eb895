package org.sid.service;

import org.sid.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface InventoryService {
	@GetMapping("/products/{id}?projection=fullProduct") 
	Product findProductById(@PathVariable("id") Long id); 
	@GetMapping("/products?projection=fullProduct") 
	PagedModel<Product> findAll();
}

