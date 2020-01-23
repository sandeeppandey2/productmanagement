/**
 * 
 */
package com.asellion.demo.service;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.asellion.demo.model.Product;
import com.asellion.demo.productrepository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for product operations
 *
 */
@AllArgsConstructor
@Service
@Component
public class ProductService {
	@Autowired
    private final ProductRepository productRespository;

    public List<Product> findAll() {
        return productRespository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRespository.findById(id);
    }

    public Product save(Product product) {
        return productRespository.save(product);
    }
    
    public Product update(Product product) {
        return productRespository.save(product);
    }

    public void deleteById(Long id) {
        productRespository.deleteById(id);
    }
}


