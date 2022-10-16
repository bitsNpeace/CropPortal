package com.trainingapps.cropdeal.userMicroService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainingapps.cropdeal.userMicroService.models.User;
import com.trainingapps.cropdeal.userMicroService.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*" ,allowedHeaders="*")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	UserService fileService;

	// creating a get mapping that retrieves all the farmers detail from the
	// database
	@GetMapping("/allUser")
	private List<User> getAllUser() {
		return userService.getAllUser();
	}

	// creating a delete mapping that deletes a specified farmer
	@DeleteMapping("/delete/{id}")
	private void deleteUser(@PathVariable("id") int id) {
		userService.deleteUser(id);
	}

	@PutMapping("/inactive/{id}")
	private User makeInactive(@PathVariable("id") int userId) {
		User user = userService.findById(userId);
		user.setActive(false);
		return userService.updateUser(userId, user);
	}

	@GetMapping("/generateReport")
	public ResponseEntity<Resource> getFile() {
		String filename = "usersReport.xlsx";
		InputStreamResource file = new InputStreamResource(fileService.loadExcel());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}
}
