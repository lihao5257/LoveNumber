package com.number.lover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoverNumberApplicationConsumer {

	public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LoverNumberApplicationConsumer.class);
        //springApplication.addListeners(new FileWatchStartUp());
        springApplication.run(args);
	}

}
