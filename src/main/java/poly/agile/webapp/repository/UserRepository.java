package poly.agile.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.agile.webapp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
