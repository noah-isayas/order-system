package no.nhicrews.ordersystem.controller;

import no.nhicrews.ordersystem.model.Order;
import no.nhicrews.ordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    //using field injections in order to inject OrderService
    private final OrderService orderService;

    @Autowired
    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<Order>> getAllOrders(Pageable pageable) {
        // handles get requests to /orders, and returning a page of objects
        return ResponseEntity.ok(orderService.getAllOrders(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        // handles get requests to /orders/{id}, returning a specific order by its ID
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        // Handles post request, adding an order to /orders, and returning it
        Order newOrder = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        //handles put requests to /orders/{id}, updating an order by its ID
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        //handles delete requests to /orders/{id}, deleting an order with a specific ID
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}
