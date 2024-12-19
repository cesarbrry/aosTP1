package com.aos.aosTP1.orderService.controller;

import com.aos.aosTP1.orderService.model.Order;
import com.aos.aosTP1.orderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //recuperer toutes les commandes
    @GetMapping(name="/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(OrderService.getAllOrders());  
    }

    //recuperer par ID de commande
    @GetMapping(name="/findById/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@RequestBody Long id) {
        return ResponseEntity.ok(OrderService.getOrderById(id));  
    }


    //recuperer par ID d'utilisateur 
    @GetMapping(name="/findByUId/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestBody Long userId) {
        return ResponseEntity.ok(OrderService.getOrdersByUserId(userId));  
    }

}