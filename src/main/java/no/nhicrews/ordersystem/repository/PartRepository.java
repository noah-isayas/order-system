package no.nhicrews.ordersystem.repository;

import no.nhicrews.ordersystem.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
