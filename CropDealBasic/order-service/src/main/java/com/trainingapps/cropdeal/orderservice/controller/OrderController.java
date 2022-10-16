package com.trainingapps.cropdeal.orderservice.controller;

import com.trainingapps.cropdeal.orderservice.model.Order;
import com.trainingapps.cropdeal.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // creating a get mapping that retrieves all the order detail from the database
    @GetMapping("/allOrder")
    private List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }

    //creating post mapping that post the order detail in the database
    @PostMapping("/create")
    private long saveOrder(@RequestBody Order order)
    {
        orderService.saveOrder(order);
        return order.getOrderId();
    }
    @GetMapping("/show/{id}")
    public Order findById(@PathVariable int id) {
        Order response = orderService.findById(id);
        return response;
    }
    
    //Changed today
    
    @GetMapping("/receipt/{id}")
    public Order getByCropId(@PathVariable("id") int cropId) {
    	return orderService.getOrderByCropId(cropId);
    }
    //creating a delete mapping that deletes a specified order
    @DeleteMapping("/delete/{id}")
    private void deleteOrder(@PathVariable("id") int id)
    {
        orderService.deleteOrder(id);
    }
    //creating put mapping that updates the order detail
    @PutMapping("/update/{id}")
    private Order updateOrder(@PathVariable(value="id") int id,@RequestBody Order order) {
        return orderService.updateOrder(id,order);

    }



}