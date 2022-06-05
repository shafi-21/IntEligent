package snps.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import snps.hack.model.Employee;
import snps.hack.model.Funds;

public interface FundRepository extends JpaRepository <Funds, Long>{


}
