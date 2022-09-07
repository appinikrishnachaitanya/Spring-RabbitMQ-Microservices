package com.rabbitmq.producer.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
   
	@Value("${spring.rabbitmq.queue.name}")
	private String queueName;
	@Value("${spring.rabbitmq.exchange.name}")
	private String exchangeName;
	@Value("${spring.rabbitmq.routing.key}")
	private String routingKey;
	
	
	@Bean
	public Queue queue()
	{
		return new Queue(queueName);
	}
	
	@Bean
	public TopicExchange exchange()
	{
		return new TopicExchange(exchangeName);
	}
	
	@Bean
	public Binding binding()
	{
		return BindingBuilder.bind(queue()).to(exchange()).with(routingKey);
	}
	
	//for the json
	
	@Bean
	public MessageConverter jsonConverter()
	{
		return new  Jackson2JsonMessageConverter();
	}
	
	//AMQP TEMPLATE
	//RABBIT TEMPLATE
	//Connection FACTORY
	
	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonConverter());
		return rabbitTemplate;
		
		
	}
	
	
}
