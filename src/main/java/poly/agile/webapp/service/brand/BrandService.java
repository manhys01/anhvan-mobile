package poly.agile.webapp.service.brand;

import poly.agile.webapp.model.Brand;
import poly.agile.webapp.service.BaseService;

public interface BrandService extends BaseService<Brand, Integer> {
	
	public Brand findBrandByName(String name);
	
}
