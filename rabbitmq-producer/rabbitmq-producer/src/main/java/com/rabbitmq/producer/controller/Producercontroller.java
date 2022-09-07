package com.rabbitmq.producer.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.basedomains.dtos.Order;
import com.rabbitmq.producer.service.Publisher;


@RestController
@RequestMapping("/api/v1")
public class Producercontroller {
	
	@Autowired
	private Publisher publisher;
	
	@PostMapping("/publish")
	public ResponseEntity<String> takeOrder(@RequestBody Order order)
	{   
		order.setId(UUID.randomUUID().toString());
		publisher.SendOrder(order);
		return ResponseEntity.ok("Order is sended to the queue Successfully");
	}
}
