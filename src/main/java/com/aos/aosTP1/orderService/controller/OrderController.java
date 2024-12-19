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
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() 
    {
        List<Order> orders = orderService.getAllOrders();
        
        if (!orders.isEmpty())
        {
            return ResponseEntity.ok(orders); // on a des commandes
        }
        else
        {
            return ResponseEntity.notFound().build(); // table vide
        }
    }

    //recuperer par ID de commande
    @GetMapping
    public ResponseEntity<List<Order>> getOrderById(@PathVariable Long id) 
    {
        Optional<Order> optionalOrder = orderService.getOrderById(id);

        if (optionalOrder.isPresent()) 
        {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(order); // commande trouvee
        } 

        else 
        {
            return ResponseEntity.notFound().build(); // commande pas trouvee
        }
           
    }

    //recuperer par ID d'utilisateur 
    @GetMapping
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) 
    {
        List<Order> orders = orderService.getOrdersByUserId(userId);

        if (orders != null && !orders.isEmpty()) 
        {
            return ResponseEntity.ok(orders); // commande trouvee
        } 
        
        else 
        {
            return ResponseEntity.notFound().build(); // pas de commande dans le tableau ou pas de commande de l'utilisateur
        }
           
    }
}