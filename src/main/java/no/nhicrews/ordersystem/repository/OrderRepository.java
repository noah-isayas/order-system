package no.nhicrews.ordersystem.repository;

import no.nhicrews.ordersystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
