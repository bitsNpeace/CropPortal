package com.trainingapps.cropdeal.userMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.trainingapps.cropdeal.userMicroService.models.Crop;
import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.service.UserService;
@RestController
@RequestMapping("/farmer")
@CrossOrigin(origins="*" ,allowedHeaders="*")
public class FarmerController {
	@Autowired
    private UserService userService;
	@Autowired
	private RestTemplate restTemplate;
	//add crop by farmer
		@PostMapping("/addCrop")
		private Crop addCrop(@RequestBody Crop crop) {
			User user=userService.getCurrentUser();
			crop.setFarmerId(user.getUserId());
			return restTemplate.postForObject("http://localhost:8083/Crop/addCrop", crop, Crop.class);
		}
		
		//see all crop added by particular farmer
		@CrossOrigin(origins="http://localhost:3000" ,methods = RequestMethod.GET)
		@GetMapping("/allCrop")
		private Crop[] getAllCrop(){
			User user= userService.getCurrentUser();
			int farmerId=user.getUserId();
			return restTemplate.getForObject("http://localhost:8083/Crop/allCrop/"+farmerId,Crop[].class);
		}
		
		
		 //Delete crop by cropID
		@DeleteMapping("/deleteCrop/{id}")
		private String deleteCropById(@PathVariable("id")int cropId)
		{
			User user=userService.getCurrentUser();
			Crop crop=restTemplate.getForObject("http://localhost:8083/Crop/getCrop/"+cropId, Crop.class);
			if(crop.getFarmerId()==user.getUserId())
			{			
				restTemplate.delete("http://localhost:8083/Crop/deleteCrop/"+cropId);
				return "Deleted";
			}
			else
			{
				return "Forbidden";
			}
		}
	    
		@PutMapping("/updateCrop/{id}")
		private Crop updateCropById(@PathVariable("id")int cropId,@RequestBody Crop crop)
		{
			User user=userService.getCurrentUser();
			crop.setFarmerId(user.getUserId());
			
			restTemplate.put("http://localhost:8083/Crop/updateCrop/"+crop.getCropId(), crop);
			return crop;
			
		}
		
		@DeleteMapping("/deleteId")
	    private void deleteAccount()
	    {
			User user=userService.getCurrentUser();
	        userService.deleteUser(user.getUserId());
	    }
}
