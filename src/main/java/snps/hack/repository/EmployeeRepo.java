package snps.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snps.hack.model.Employee;

public interface EmployeeRepo extends JpaRepository <Employee, Long>{


}
