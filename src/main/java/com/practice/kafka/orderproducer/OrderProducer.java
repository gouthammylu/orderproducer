package com.practice.kafka.orderproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class OrderProducer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties prop=new Properties();
		prop.setProperty("bootstrap.servers", "localhost:9092");//broker details (can be a list of brokers)
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		
		KafkaProducer<String,Integer> producer=new KafkaProducer<>(prop);
		ProducerRecord<String,Integer> record=new ProducerRecord<>("OrderTopic","Macbook",10);	
		
		try {			
			//RecordMetadata recordMetadata = producer.send(record).get(); Sync send
			producer.send(record,new OrderCallback()); //Async end
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}

	}



}
