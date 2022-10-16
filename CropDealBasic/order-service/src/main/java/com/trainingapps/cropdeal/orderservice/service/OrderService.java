package com.trainingapps.cropdeal.orderservice.service;

import com.trainingapps.cropdeal.orderservice.model.Order;
import com.trainingapps.cropdeal.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    // getting all order record by using the method findAll() of CrudRepository
    public List<Order> getAllOrder() {
        List<Order> order = new ArrayList<Order>();
        orderRepository.findAll().forEach(order1 -> order.add(order1));
        return order;
    }

    // getting a specific record by using the method findById() of CrudRepository
    public Order findById(long id) {
        return orderRepository.findById(id).get();
    }

    // saving a specific record by using the method save() of CrudRepository
    public Order saveOrder(Order order) {
        orderRepository.save(order);
        return order;

    }

    // deleting a specific record by using the method deleteById() of CrudRepository
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
    
    //Changed Today
    public Order getOrderByCropId(int cropId) {
    	Order order=orderRepository.findByCropId(cropId).get();
    	return order;
    }
    // updating a record
    public Order updateOrder( long orderId,@Valid @RequestBody Order orderDetails){

        Order order=orderRepository.findById(orderId).get();

        order.setQuantity(orderDetails.getQuantity());
        order.setCropName(orderDetails.getCropName());
        order.setPrice(orderDetails.getPrice());
        order.setDealerId(orderDetails.getDealerId());
        order.setStatus(orderDetails.getStatus());
        orderRepository.save(order);
        return order;
    }
}

