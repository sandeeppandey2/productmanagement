package com.asellion.demo.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.asellion.demo.errorhandler.ValidatorApplicationException;
import com.asellion.demo.model.Product;

/**
 * Class responsible for basic validations
 *
 */
@Component
public class Validators {

	/**
	 * Method to validate Product
	 * @param product
	 * @throws ValidatorApplicationException
	 */
	public void validateProduct(Product product) throws ValidatorApplicationException{
		if(product!=null) {
			validateProductName(product.getName());
			validateProductPrice(product.getPrice());
		} else {
			throw new ValidatorApplicationException("Product cannot be null");
		}	
	}

	/**
	 * Method to validate product name
	 * @param name
	 * @throws ValidatorApplicationException
	 */
	public void validateProductName(String name) throws ValidatorApplicationException {
		if(!(name!=null && Pattern.matches("^[A-Za-z]\\S*$", name))) {
			throw new ValidatorApplicationException("Product name cannot be null");
		}
	}

	/**
	 * Method to validate product price
	 * @param productPrice
	 * @throws ValidatorApplicationException
	 */
	public void validateProductPrice(BigDecimal productPrice)  throws  ValidatorApplicationException{
		if(!(productPrice!=null && Pattern.matches("^[\\d]*[\\.]?[\\d]*$", productPrice.toString()))){
			throw new ValidatorApplicationException("Product price is invalid");
		}
	}

}
