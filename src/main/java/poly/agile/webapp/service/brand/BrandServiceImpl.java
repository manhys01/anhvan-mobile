package poly.agile.webapp.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.model.Brand;
import poly.agile.webapp.repository.BrandRespository;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRespository repository;

	@Override
	public Brand create(Brand b) {
		return repository.save(b);
	}

	@Override
	public Brand update(Brand b) {
		return repository.save(b);
	}

	@Override
	public boolean remove(Brand o) {
		try {
			repository.delete(o);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Brand findById(Integer id) {
		return repository.getOne(id);
	}

	@Override
	public List<Brand> findAll() {
		return repository.findAll();
	}

	@Override
	public long totalPages() {
		return (long) Math.ceil(repository.count() / 8.0);
	}

}
