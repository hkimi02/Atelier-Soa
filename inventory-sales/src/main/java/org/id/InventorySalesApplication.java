package org.id;

import org.id.entities.Product;
import org.id.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventorySalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorySalesApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepo productRepository){
	return args->{
			productRepository.save(new Product(null,"Computer Desk TopHP",900.0)); 
			productRepository.save(new Product(null,"PrinterEpson",80.0)); 
			productRepository.save(new Product(null,"MacBook ProLap Top",1800.0)); 
	productRepository.findAll().forEach(System.out::println);};
	
}
}
