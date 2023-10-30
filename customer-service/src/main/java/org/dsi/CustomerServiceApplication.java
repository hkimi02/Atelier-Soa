package org.dsi;

import org.dsi.entities.Customer;
import org.dsi.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner start(CustomerRepo customerRepository) {
		return args ->{
			customerRepository.save(new Customer(null,"eni","contact@eni.tn"));
			customerRepository.save(new Customer(null,"FST","contact@fst.tn"));
			customerRepository.save(new Customer(null,"ensi","contact@ensi.tn"));
			customerRepository.findAll().forEach(System.out::println);
		};
}
}
