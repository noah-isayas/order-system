package no.nhicrews.ordersystem.repository;

import no.nhicrews.ordersystem.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
