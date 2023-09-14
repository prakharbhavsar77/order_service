package com.example.Project_Service.externalClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE/product")
public interface Product_Service {

	
	@PutMapping("reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity( @PathVariable("id") long productId,
			@RequestParam("quantity") long quantit);
}
