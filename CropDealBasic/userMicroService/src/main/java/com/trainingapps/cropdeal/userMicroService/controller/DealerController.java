package com.trainingapps.cropdeal.userMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trainingapps.cropdeal.userMicroService.models.Crop;
import com.trainingapps.cropdeal.userMicroService.models.Order;
import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.service.UserService;

@RestController
@RequestMapping("/dealer")
@CrossOrigin(origins="*" ,allowedHeaders="*")
public class DealerController {
	@Autowired
    private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/order/{id}")
	private long placeOrder(@PathVariable("id")int cropId) {
		Crop crop =restTemplate.getForObject("http://localhost:8083/Crop/getCrop/"+cropId, Crop.class);
		User user=userService.getCurrentUser();
		
		Order order=new Order(crop.getCropQuantity(),crop.getCropName(),crop.getPrice(),user.getUserId(),"pending",cropId);
		return restTemplate.postForObject("http://localhost:8084/order/create", order, Long.class);
		
	}
	@CrossOrigin(origins="http://localhost:3000" ,methods = RequestMethod.GET)
	@GetMapping("/getAllCrop")
	private Crop[] allCrops() {
		return restTemplate.getForObject("http://localhost:8083/Crop/getAllCrops/", Crop[].class);
	}
	@PutMapping("/confirmOrder/{id}")
	private String confirmOrder(@PathVariable("id")int orderId)
	{
//		User user=userService.getCurrentUser();
//		int dealer_id=user.getUserId();
//		return restTemplate.getForObject("http://localhost:9090/",String.class );
		Order order=restTemplate.getForObject("http://localhost:8084/order/show/"+orderId, Order.class);
		if(order.getStatus().equals("confirmed"))
		{
			return "payment completed already";
		}
		order.setStatus("confirmed");
		restTemplate.put("http://localhost:8084/order/update/"+orderId, order);
		return "payement success";
	}
	@CrossOrigin(origins="http://localhost:3000" ,methods = RequestMethod.GET)
	@GetMapping("/genReceipt/{id}")
	public Order createReceipt(@PathVariable("id") int cropId) {
		return restTemplate.getForObject("http://localhost:8084/order/receipt/"+cropId, Order.class);
	}
	
	
	@DeleteMapping("/cancelOrder/{id}")
	private String cancelOrder(@PathVariable("id")int orderId)
	{
		User user=userService.getCurrentUser();
		Order order=restTemplate.getForObject("http://localhost:8084/order/show/"+orderId,Order.class);
		if(order.getDealerId()==user.getUserId())
		{
			restTemplate.delete("http://localhost:8084/order/delete/"+orderId);
			return "Deleted";
		}
		else
		{
			return "Forbidden";
		}
		
	}
	@DeleteMapping("/deleteId")
    private void deleteAccount()
    {
		User user=userService.getCurrentUser();
        userService.deleteUser(user.getUserId());
    }
	
}
