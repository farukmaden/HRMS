package javakamp.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javakamp.hrms.entities.concretes.Candidates;
import javakamp.hrms.entities.concretes.Users;

@Repository
public interface CandidatesDao extends JpaRepository<Candidates , Integer>{
	
	List<Users> findAllByIdentityNumber(String identity_number);
}