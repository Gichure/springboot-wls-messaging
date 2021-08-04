package com.pgichure.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication(exclude = ActiveMQAutoConfiguration.class)
@EnableJms
public class WlsJmsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WlsJmsDemoApplication.class, args);
	}

}
