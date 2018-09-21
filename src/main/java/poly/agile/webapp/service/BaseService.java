package poly.agile.webapp.service;

import java.util.List;

public interface BaseService<T, ID> {

	T create(T object);
	
	T update(T object);
	
	boolean delete(T object);
	
	T findById(ID id);
	
	List<T> findAll();
	
	long totalPages();
	
}
