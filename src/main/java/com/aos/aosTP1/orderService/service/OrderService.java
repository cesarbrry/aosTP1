package com.aos.aosTP1.orderService.model;

import com.aos.aosTP1.orderService.model.Order;
import com.aos.aosTP1.orderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class OrderService {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String username, String password) {
        
        if (quantity<=0)
        {
            throw new IllegalArgumentException("Quantité nulle ou négative : Commande impossible");
        }

        if (!UserRepository.existsById(userId))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Order order = new Order(userId,product,quantity);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() 
    {
        List<Order> allOrders = orderRepository.getAllOrders();
        
        if (allOrders.isEmpty()) 
        {
            throw new IllegalArgumentException('Order table is empty');
        } 
        
        else 
        {
         return allOrders;   
        }

    }

    public Optional<Order> getOrderById(Long orderId) 
    {
        Optional<Order> OrderById = orderService.getOrderById(orderId);

        if (OrderById.isPresent())
        {
            return OrderById;
        } 
        
        else 
        {
            throw new RuntimeException('No Order with this Id')
        }
        
    }

    public List<Order> getOrdersByUserId(Long userId) 
    {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        if (orders == null || orders.isEmpty()) 
        {
            throw new IllegalArgumentException('This User has no order');
        } 
        
        else 
        {
         return orders;   
        }
        
    }
}