package snps.hack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import snps.hack.model.BU;
import snps.hack.model.BUName;


import java.util.Optional;

@Repository
public interface BURepository extends JpaRepository<BU, Long> {
    Optional<BU> findByName(BUName buName);
}