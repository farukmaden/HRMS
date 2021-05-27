package javakamp.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import javakamp.hrms.entities.concretes.Employers;

public interface EmployersDao extends JpaRepository<Employers, Integer>{

}
