package com.practice.kafka.orderproducer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		
		System.out.println(metadata.topic());
		System.out.println(metadata.offset());
		System.out.println(metadata.partition());
		System.out.println("Record sent successfully");
		if(exception!=null)
			System.out.print(exception.getMessage());

	}

}
