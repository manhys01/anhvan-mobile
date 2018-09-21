package poly.agile.webapp.service;

import java.util.List;

public interface DTOService<DTO> {
	
	List<DTO> list(int page);
	
}
