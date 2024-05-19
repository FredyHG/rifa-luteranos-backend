package dev.fredyhg.rifaluteranosbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RifaLuteranosBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RifaLuteranosBackendApplication.class, args);
	}

}
