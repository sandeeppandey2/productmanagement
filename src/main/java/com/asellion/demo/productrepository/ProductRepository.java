/**
 * 
 */
package com.asellion.demo.productrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.asellion.demo.model.Product;

/**
 * @author Sandeep
 *
 */
@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

}
