package com.practice.kafka.orderproducer.customserializer;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderSerializer implements Serializer<Order> {

	@Override
	public byte[] serialize(String topic, Order data) {
		
		byte[] order=null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			order=objectMapper.writeValueAsString(data).getBytes();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return order;
	}

}
