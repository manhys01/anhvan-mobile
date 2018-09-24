package poly.agile.webapp;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import poly.agile.webapp.model.Brand;
import poly.agile.webapp.repository.BrandRespository;
import poly.agile.webapp.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileWebappApplicationTests {
	
	@Autowired
	private ProductService service;
	
	@Autowired
	private BrandRespository brandRepo;

	@Ignore
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testDeleteProduct() {
		boolean delete = service.remove(service.findById(5));
		assertTrue(delete);
	}
	
	@Ignore
	@Test
	public void testDeleteBrand() {
		Brand brand = brandRepo.getOne(1);
		brandRepo.delete(brand);
	}

}
