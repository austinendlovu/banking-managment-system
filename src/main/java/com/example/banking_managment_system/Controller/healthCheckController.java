package com.example.banking_managment_system.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class healthCheckController {

	@GetMapping
	public ResponseEntity<String> heath(){
		return ResponseEntity.ok("Im alive");
	}
}
