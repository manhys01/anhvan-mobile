package poly.agile.webapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import poly.agile.webapp.model.Product;
import poly.agile.webapp.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileWebappApplicationTests {
	
	@Autowired
	private ProductService service;


	@Ignore
	@Test
	public void contextLoads() {
	}
	

	@Ignore
	@Test
	public void testProductServiceNotNull() {
		assertNotNull(service);
	}
	
	@Ignore
	@Test
	public void testFindProductById() {
		Integer id = 1;
		Product product = service.findById(id);
		assertNotNull(product);
	}
	
	@Test
	public void testDeleteProduct() {
		Integer id = 1;
		boolean delete = service.delete(service.findById(id));
		assertEquals(true, delete);
	}

}
