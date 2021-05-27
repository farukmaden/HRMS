package javakamp.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javakamp.hrms.entities.concretes.Users;

public interface UsersDao extends JpaRepository<Users, Integer> {

	List<Users> findAllByEmail(String email);
	
	
}
