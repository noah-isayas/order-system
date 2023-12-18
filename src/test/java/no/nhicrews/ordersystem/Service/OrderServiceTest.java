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
    @MockBean
    private OrderRepository orderRepository;


    @Autowired
    private OrderService orderService;

    @Test
    public void orderShouldBeSavedWhenAdded() {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.addOrder(order);

        assertNotNull(savedOrder);
        assertEquals(order.getOrderDate(), savedOrder.getOrderDate());
    }
    @Test
    public void deletingOrderShouldRemoveIt() {
        Long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);
        doNothing().when(orderRepository).deleteById(orderId);

        orderService.deleteOrder(orderId);

        verify(orderRepository).deleteById(orderId);
    }

    @Test
    public void NonExsistingIdShouldThrowException() {
        Long orderId = 2L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            orderService.getOrderById(orderId);
        });

    }
}
