package org.sid;

import java.sql.Date;

import org.sid.entities.Bill;
import org.sid.entities.Customer;
import org.sid.entities.ProductItem;
import org.sid.repo.BillRepo;
import org.sid.repo.ProductItemRepo;
import org.sid.service.CustomerService;
import org.sid.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	@Bean	
	public CommandLineRunner start(BillRepo billRepository, ProductItemRepo productItemRepository,
	                               InventoryService inventoryServiceClient,
	                               CustomerService customerServiceClient) {
		
	    return args -> {
	        Customer customer = customerServiceClient.findCustomerById(1L);
	        Bill bill=new Bill();
	        bill.setBillingDate(new java.sql.Date(System.currentTimeMillis()));
	        bill.setCustomerID(customer.getId());
	        billRepository.save(bill);
	        inventoryServiceClient.findAll().getContent().forEach(p -> {
	        	 productItemRepository.save(new ProductItem(
	 	                null, null, p.getId(), p.getPrice(), bill
	 	            ));
	        });
	        System.out.println(bill.toString());
	    };
	}

}
