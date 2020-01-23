package com.asellion.demo.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asellion.demo.constants.Constants;
import com.asellion.demo.errorhandler.ExceptionFactory;
import com.asellion.demo.errorhandler.ValidatorApplicationException;
import com.asellion.demo.model.Product;
import com.asellion.demo.service.ProductService;
import com.asellion.demo.util.Validators;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * ProductApiController Class responsible for crud operations
 *
 */
@RestController
@RequestMapping("/api/products")
@Slf4j
@RequiredArgsConstructor
public class ProductApiController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private  Validators validators;
	

	/**
	 * This Method is responsible for Get operations
	 * @return List of products
	 */
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok(productService.findAll());
	}
	

	/**
	 * The method is responsibe for post operation
	 * @param product input parameter 
	 * @return Response Entity
	 */
	@PostMapping
	public ResponseEntity<Object> create(@Valid @RequestBody Product product) {
		try {
			validators.validateProduct(product);
		} catch (ValidatorApplicationException  validatorApplicationException) {
			log.error(" Product body cannot be null");
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionFactory.getMessage(Constants.MESSAGE_01, validatorApplicationException.getMessage()));
		}
		return ResponseEntity.ok(productService.save(product));
	}

	
	
	/**
	 * This method is responsible for Get operations with arguments
	 * @param id input parameter
	 * @return list of products
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		Optional<Product> product = productService.findById(id);
		if (!product.isPresent()) {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionFactory.getMessage(Constants.MESSAGE_02, "Product not found"));
		}

		return ResponseEntity.ok(product.get());
	}

	
	/**
	 * This method is responsible for update operation
	 * @param id input parameter
	 * @param product input parameter
	 * 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody Product product) {
		try {
			validators.validateProduct(product);
		} catch (ValidatorApplicationException e) {
			log.error(" Product body cannot be null");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionFactory.getMessage(Constants.MESSAGE_03, "Product body cannot be null"));
		}
		Optional<Product> existingProduct = productService.findById(id);
		if(existingProduct.isPresent()) {
			existingProduct.get().setName(product.getName());
			existingProduct.get().setPrice(product.getPrice());
			existingProduct.get().setLastUpdate(new Date());
			return ResponseEntity.ok(existingProduct.get());
		}else {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionFactory.getMessage(Constants.MESSAGE_02, "Product not found"));
		}


	}

	
	/**
	 * This Method is responsible for Delete operation
	 * @param id input parameter
	 * 
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		if (!productService.findById(id).isPresent()) {
			log.error("Id " + id + " is not existed");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionFactory.getMessage("MESSGAE_01", "Product not found"));
		}

		productService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}