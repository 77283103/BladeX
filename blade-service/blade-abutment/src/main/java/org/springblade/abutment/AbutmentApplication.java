package org.springblade.abutment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AbutmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbutmentApplication.class, args);
		log.info("===================================run success===================================");
	}

}
