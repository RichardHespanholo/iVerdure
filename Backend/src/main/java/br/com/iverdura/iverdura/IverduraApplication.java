package br.com.iverdura.iverdura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IverduraApplication {

	public static void main(String[] args) {

		SpringApplication.run(IverduraApplication.class, args);
	}

	//@Bean
	//public BCryptPasswordEncoder bCryptPasswordEncoder() {
	//	return new BCryptPasswordEncoder();
	//}

}
