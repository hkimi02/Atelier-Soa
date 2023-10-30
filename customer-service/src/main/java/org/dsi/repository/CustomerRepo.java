package org.dsi.repository;

import org.dsi.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {

}
