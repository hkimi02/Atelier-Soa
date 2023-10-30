package org.sid.repo;


import java.sql.Date;
import java.util.Collection;

import org.sid.entities.Bill;
import org.sid.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

@RepositoryRestResource
public interface BillRepo extends JpaRepository<Bill,Long>{
	
	
}
@Projection(name="fullBill", types = Bill.class)
interface BillProjection{
	public Long getId();
	public Date getBillingDate();
	public Long getCustomerId();
	public Collection<ProductItem> getProductItems();
}
