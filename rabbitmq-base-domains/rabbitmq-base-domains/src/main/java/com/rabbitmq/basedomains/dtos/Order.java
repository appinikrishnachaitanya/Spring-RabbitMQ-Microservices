package com.rabbitmq.basedomains.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
	
	private String id;
	private String orderName;
	private int qty;
	private double price;
	private String category;

}
