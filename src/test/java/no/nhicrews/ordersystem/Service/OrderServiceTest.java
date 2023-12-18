package no.nhicrews.ordersystem.Service;

import jakarta.persistence.EntityNotFoundException;
import no.nhicrews.ordersystem.model.Order;
import no.nhicrews.ordersystem.repository.OrderRepository;
import no.nhicrews.ordersystem.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    //Mocking in order to simulate repository interactions without relying on the actual DB
    @MockBean
    private OrderRepository orderRepository;

    //OrderService is injected into the test class using @Autowired to test its business logic
    @Autowired
    private OrderService orderService;

    @Test
    public void orderShouldBeSavedWhenAdded() {
        // Tests if adding a new order saves the order correctly
        // Mocks an order and verifies the time it was created
        Order order = new Order();
        order.setOrderDate(LocalDate.now());

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.addOrder(order);

        assertNotNull(savedOrder);
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
    }

    @Test
    public void deletingOrderShouldRemoveIt() {
        //This method ensures the service correctly handles deletion.
        // It mocks the existence of an order and verifies the repository's deleteById method is called.
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository).deleteById(orderId);
    }

    @Test
    public void NonExsistingIdShouldThrowException() {
        // Tests the service's response when trying to find an order with a non-existing ID
        // Ensures that an EntityNotFoundException is thrown, indicating the order doesn't exist
        Long orderId = 2L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        //should throw
        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrderById(orderId);
        });

    }
}
