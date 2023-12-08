package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Order;
import no.nhicrews.ordersystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Couldnt find order with ID" + id));
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setOrderDate(updatedOrder.getOrderDate());
            return orderRepository.save(order);
        }).orElseThrow(() -> new EntityNotFoundException("Couldnt find order with ID" + id));
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new EntityNotFoundException("Couldnt find order with ID" + id);
        }
        orderRepository.deleteById(id);
    }
}
