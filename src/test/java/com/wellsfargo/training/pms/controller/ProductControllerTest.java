/**
 * 
 */
package com.wellsfargo.training.pms.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wellsfargo.training.pms.exception.ResourceNotFoundException;
import com.wellsfargo.training.pms.model.Product;
import com.wellsfargo.training.pms.repository.ProductRepository;
import com.wellsfargo.training.pms.service.ProductService;

/**
 * 
 */

@SpringBootTest
class ProductControllerTest {
	
	@Autowired
	private ProductController productController;
	
	@MockBean
	private ProductService pservice;
	
	Product product;
	

	/**
	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#saveProduct(com.wellsfargo.training.pms.model.Product)}.
	 */
	@BeforeEach
	void setUp() throws Exception{
		product=new Product();
	}
	
	@AfterEach
	void tearDown() throws Exception{
		product=null;
	}

//	@MockBean
//	ProductRepository prepo; // Create Mock object Object of ProductRepository
//	
//	@Autowired
//	private ProductService pservice;
//	
	@Test
	void testSaveProduct() {
//		Product product = new Product();
		product.setName("Optical Mouse");
		product.setBrand("Logitech");
		product.setMadein("Finland");
		product.setPrice(500.00f);
	
		when(pservice.saveProduct(any(Product.class))).thenReturn(product);
		
		ResponseEntity<Product> re=productController.saveProduct(product);
		assertEquals(HttpStatus.CREATED,re.getStatusCode());
		assertEquals("Optical Mouse",re.getBody().getName());
		assertEquals("Logitech",re.getBody().getBrand());
		assertEquals("Finland",re.getBody().getMadein());
		assertEquals(500.00,re.getBody().getPrice());
		
		verify(pservice,times(1)).saveProduct(any(Product.class));


	}

	/**
	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#getAllProducts()}.
	 */
	@Test
	void testGetAllProducts() {
		List<Product> mockProducts=new ArrayList<>();
		mockProducts.add(new Product(1L,"Pen","Reynolds","India",20.0f));
		mockProducts.add(new Product(2L,"HDD","Seagate","India",5000.0f));
		
		when(pservice.listAll()).thenReturn(mockProducts);
		
		List<Product> responseProducts = productController.getAllProducts();
		assertEquals(2,responseProducts.size());
		assertEquals("Pen",responseProducts.get(0).getName());
		assertEquals("HDD",responseProducts.get(1).getName());
		
		verify(pservice,times(1)).listAll();

	}

	/**
	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#getProductById(java.lang.Long)}.
	 */
	@Test
	void testGetProductById() throws ResourceNotFoundException{
		
		Product mockProduct=new Product(2L,"HDD","Seagate","India",5000.0f);
		
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(mockProduct));
		
		ResponseEntity<Product> re = productController.getProductById(2L);
		assertEquals(HttpStatus.OK,re.getStatusCode());
		assertEquals("Seagate",re.getBody().getBrand());
		assertEquals("India",re.getBody().getMadein());
		assertEquals(5000.0,re.getBody().getPrice());
		
		verify(pservice,times(1)).getSingleProduct(2L);
		
		
	}

	/**
	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#updateProduct(java.lang.Long, com.wellsfargo.training.pms.model.Product)}.
	 */
	@Test
	void testUpdateProduct() throws ResourceNotFoundException {

		Product existingProduct = new Product(2L,"HDD","Seagate","India",5000.0f);
		Product updatedProduct = new Product(2L,"HDD","Seagate","USA",6000.0f);
		
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(existingProduct));
		when(pservice.saveProduct(any(Product.class))).thenReturn(updatedProduct);
		
		ResponseEntity<Product> re = productController.updateProduct(2L, updatedProduct);
		
		assertEquals("HDD",re.getBody().getName());
		assertEquals("Seagate",re.getBody().getBrand());
		assertEquals("USA",re.getBody().getMadein());
		assertEquals(6000.0,re.getBody().getPrice());
	
		verify(pservice,times(1)).getSingleProduct(2L);
		verify(pservice,times(1)).saveProduct(any(Product.class));
	}
	
	

	/**
	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#deleteProduct(java.lang.Long)}.
	 */
	@Test
	void testDeleteProduct() throws ResourceNotFoundException{
		
		Product existingProduct = new Product(2L,"HDD","Seagate","India",5000.0f);
		
		when(pservice.getSingleProduct(2L)).thenReturn(Optional.of(existingProduct));
		doNothing().when(pservice).deleteProduct(2L);
		
		Map<String,Boolean> response = productController.deleteProduct(2L);
		
		assertTrue(response.containsKey("Deleted"));
		assertTrue(response.get("Deleted"));
		
		verify(pservice,times(1)).getSingleProduct(2L);
		verify(pservice,times(1)).deleteProduct(2L);
	}

//	/**
//	 * Test method for {@link com.wellsfargo.training.pms.controller.ProductController#helloPrice(float)}.
//	 */
//	@Test
//	void testHelloPrice() {
//		fail("Not yet implemented");
//	}

}
