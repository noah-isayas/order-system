package no.nhicrews.ordersystem.repository;

import no.nhicrews.ordersystem.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MachineRepository extends JpaRepository<Machine, Long> {
}
