package no.nhicrews.ordersystem.service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Customer;
import no.nhicrews.ordersystem.model.Order;
import no.nhicrews.ordersystem.repository.CustomerRepository;
import no.nhicrews.ordersystem.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository){
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
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
            order.setCustomer(updatedOrder.getCustomer());
            if (updatedOrder.getCustomer() != null){
                Customer fullCustomerDetails = customerRepository.findById(updatedOrder.getCustomer().getId())
                        .orElseThrow(() -> new EntityNotFoundException("cant find customer with id" + updatedOrder.getCustomer().getId()));
                order.setCustomer(fullCustomerDetails);
            }
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
