package com.practice.kafka.orderproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class TransactionalOrderProducer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties prop=new Properties();
		prop.setProperty("bootstrap.servers", "localhost:9092");//broker details (can be a list of brokers)
		prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		prop.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "order-producer-1");
		
		KafkaProducer<String,Integer> producer=new KafkaProducer<>(prop);
		producer.initTransactions();
		ProducerRecord<String,Integer> record=new ProducerRecord<>("OrderTopic","Macbook",10);
		ProducerRecord<String,Integer> record2=new ProducerRecord<>("OrderTopic","Iphone",10);
		
		try {			
			//RecordMetadata recordMetadata = producer.send(record).get(); Sync send
			producer.beginTransaction();
			producer.send(record,new OrderCallback()); //Async end
			producer.send(record2);
			producer.commitTransaction();
		}catch(Exception e) {
			producer.abortTransaction();
			e.printStackTrace();
		}finally {
			producer.close();
		}

	}



}
