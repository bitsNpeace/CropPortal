package com.trainingapps.cropdeal.orderservice;

import com.trainingapps.cropdeal.orderservice.model.Order;
import com.trainingapps.cropdeal.orderservice.repository.OrderRepository;
import com.trainingapps.cropdeal.orderservice.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private OrderService service;

	@MockBean
	private OrderRepository repository;

	@Test
	public void getAllOrderTest() {
		when(repository.findAll()).thenReturn(Stream.of(new Order("45","Mango",85,1,"pending",2),new Order("25","apple",65,1,"confirmed",1)).collect(Collectors.toList()));
		assertEquals(2,service.getAllOrder().size());

	}
	@Test
	public void saveOrderTest() {
		Order order=new Order("58","wheat",21,1,"pending",1);
		when(repository.save(order)).thenReturn(order);
		assertEquals("wheat",service.saveOrder(order).getCropName());
	}


	@Test
	public void deleteOrderTest() {
		Order order=new Order("54","rice",89,2,"pending",2);
		service.deleteOrder(order.getOrderId());
		verify(repository,times(1)).deleteById(order.getOrderId());

	}
	@Test
	public void testUpdate(){
		Order order=new Order("10","wheat",20,1,"pending",3);
		Order updateOrder=new Order("20","corn",10,1,"pending",4);

		when(repository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		when(repository.save(order)).thenReturn(order);
		assertEquals(updateOrder.getQuantity(),service.updateOrder(order.getOrderId(),updateOrder).getQuantity());
	}
	@Test
	public void testGetById(){
		Order order=new Order("10","wheat",20,1,"confirmed",1);

		when(repository.findById(order.getOrderId())).thenReturn(Optional.of(order));
		assertEquals(order.getQuantity(),service.findById(order.getOrderId()).getQuantity());

	}

}
