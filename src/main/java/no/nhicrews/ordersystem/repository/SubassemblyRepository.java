package no.nhicrews.ordersystem.repository;

import no.nhicrews.ordersystem.model.Subassembly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubassemblyRepository extends JpaRepository<Subassembly, Long> {
}
