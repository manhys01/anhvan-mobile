package poly.agile.webapp.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import poly.agile.webapp.model.User;

public interface UserService extends UserDetailsService {

	User findUserById(Integer id);
	
}
