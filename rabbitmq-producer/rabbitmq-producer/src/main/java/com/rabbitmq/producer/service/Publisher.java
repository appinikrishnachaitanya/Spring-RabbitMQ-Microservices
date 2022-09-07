package com.rabbitmq.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.basedomains.dtos.Order;
import com.rabbitmq.producer.controller.Producercontroller;

@Service
public class Publisher {
	
	@Autowired
	private RabbitTemplate rbt;
	
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${spring.rabbitmq.routing.key}")
	private String routingKey;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(Producercontroller.class);
	
	public void SendOrder(Order order)
	{   
		LOGGER.info(String.format("%s is from from PRODUCER", order.toString()));
		rbt.convertAndSend(exchangeName, routingKey, order);
	}

}
