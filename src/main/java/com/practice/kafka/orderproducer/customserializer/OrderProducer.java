package com.practice.kafka.orderproducer.customserializer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties prop=new Properties();
		prop.setProperty("bootstrap.servers", "localhost:9092");//broker details (can be a list of brokers)
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.setProperty("value.serializer", OrderSerializer.class.getName());
		prop.setProperty("partitioner.class", VIPPratitioner.class.getName());
		KafkaProducer<String,Order> producer=new KafkaProducer<>(prop);
		Order order=new Order();
		order.setCustomerName("Goutham");
		order.setProduct("iphone");
		order.setQuantity(1);
		ProducerRecord<String,Order> record=new ProducerRecord<>("OrderPartitionedTopic",order.getCustomerName(),order);	
		
		try {			
			//RecordMetadata recordMetadata = producer.send(record).get(); Sync send
			producer.send(record);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}

	}



}
