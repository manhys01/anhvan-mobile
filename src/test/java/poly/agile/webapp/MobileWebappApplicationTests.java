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
	
	@Ignore
	@Test
	public void testDeleteProduct() {
		Integer id = 1;
		boolean delete = service.delete(service.findById(id));
		assertTrue(delete);
	}
	
	@Test
	public void testDeleteBrand() {
		Brand brand = brandRepo.getOne(1);
		brandRepo.delete(brand);
	}

}
