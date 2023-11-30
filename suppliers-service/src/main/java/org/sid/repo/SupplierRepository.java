package org.sid.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.sid.entities.Supplier;
@RepositoryRestResource
public interface SupplierRepository extends JpaRepository<Supplier,Long> {}
