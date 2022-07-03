package com.practice.kafka.avro.serializer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.practice.kafka.avro.Order;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

public class OrderProducer {
	
	public static void main(String[] args) {
		
		Properties prop=new Properties();
		prop.setProperty("bootstrap.servers", "localhost:9092");
		prop.setProperty("key.serializer", KafkaAvroSerializer.class.getName());
		prop.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
		prop.setProperty("schema.registry.url", "localhost:8081");
		
		KafkaProducer<String,Order> producer=new KafkaProducer<>(prop);
		Order order=new Order("Goutham","iPhone",3);
		ProducerRecord<String,Order> record=new ProducerRecord<>("OrderAvroTopic",order.getCustomerName().toString(),order);
		
		try{
			producer.send(record);
		}catch(Exception e) {
			e.getStackTrace();
		}finally {
			producer.close();
		}
		
		
	}

}
