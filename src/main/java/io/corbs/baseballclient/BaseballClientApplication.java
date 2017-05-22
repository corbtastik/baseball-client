package io.corbs.baseballclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BaseballClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseballClientApplication.class, args);
	}


}
