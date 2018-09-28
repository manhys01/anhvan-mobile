package poly.agile.webapp.service.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.agile.webapp.model.Specification;
import poly.agile.webapp.repository.SpecificationRepository;

@Service
public class SpecificationServiceImpl implements SpecificationSerivce {

	@Autowired
	private SpecificationRepository specificationRepository;
	
	@Override
	public Specification create(Specification s) {
		return specificationRepository.save(s);
	}

	@Override
	public Specification update(Specification s) {
		return specificationRepository.save(s);
	}

	@Override
	public boolean remove(Specification object) {
		return false;
	}

	@Override
	public Specification findById(Integer id) {
		return specificationRepository.getOne(id);
	}

	@Override
	public List<Specification> findAll() {
		return specificationRepository.findAll();
	}

}
