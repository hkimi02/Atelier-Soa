package org.sid;


import org.sid.entities.Supplier;
import org.sid.repo.SupplierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import java.util.stream.Stream;

@SpringBootApplication
public class SuppliersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuppliersServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(SupplierRepository supplierRepository, RepositoryRestConfiguration restConfiguration) {
	    return args -> {
	        restConfiguration.exposeIdsFor(Supplier.class);
	        Stream.of("IBM", "HP", "Samsung").forEach(name -> {
	            supplierRepository.save(new Supplier(null, name, "contact@" + name.toLowerCase() + ".com"));
	        });
	    };
	}

}
