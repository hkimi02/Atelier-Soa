package org.sid.repo;



import java.util.Collection;
import java.util.List;

import org.sid.entities.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemRepo extends JpaRepository<ProductItem, Long>{

	List<ProductItem> findByBillId(Long billID);
	
}
