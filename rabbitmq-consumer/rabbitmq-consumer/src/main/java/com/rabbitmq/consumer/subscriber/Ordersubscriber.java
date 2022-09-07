package com.rabbitmq.consumer.subscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.basedomains.dtos.Order;

@Service
public class Ordersubscriber {
  
	public static final Logger LOGGER = LoggerFactory.getLogger(Ordersubscriber.class);
	
	
	@RabbitListener( queues =  {"${spring.rabbitmq.queue.name}"})
	public void consumeOrder(Order order)
	{
		LOGGER.info(String.format("%s is from the Consumer-1", order.toString()));
	}
	
	
	
}
