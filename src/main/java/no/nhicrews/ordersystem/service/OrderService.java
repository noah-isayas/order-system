package no.nhicrews.ordersystem.service;

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
        return null;
    }

    public Order getOrderById(Long id) {
        return null;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return null;
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return null;
    }

    public void deleteOrder(Long id) {
    }
}
