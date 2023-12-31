package org.sid.controllers;

import org.sid.entities.Bill;
import org.sid.entities.Customer;
import org.sid.repo.BillRepo;
import org.sid.repo.ProductItemRepo;
import org.sid.service.CustomerService;
import org.sid.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    @Autowired
    private BillRepo billRepository;
    
    @Autowired
    private ProductItemRepo productItemRepository;
    
    @Autowired
    private CustomerService customerServiceClient;
    
    @Autowired
    private InventoryService inventoryServiceClient;
    
    @GetMapping("/bills/full/{id}")
    public Bill getBill(@PathVariable(name="id") Long id) {
        Bill bill = billRepository.findById(id).orElse(null);
        
        if (bill != null) {
            Long customerId = bill.getCustomerID();
            if (customerId != null) {
                bill.setCustomer(customerServiceClient.findCustomerById(customerId));
            }
            
            if (productItemRepository.findByBillId(id) != null) {
                bill.setProductItems(productItemRepository.findByBillId(id));
                bill.getProductItems().forEach(pi -> {
                    pi.setProduct(inventoryServiceClient.findProductById(pi.getId()));
                });
            }
        }
        
        return bill;
    }
}
