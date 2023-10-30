package org.sid.entities;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor
public class Bill {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private Date billingDate;
	@Transient
	@OneToMany(mappedBy="bill")
	private Collection<ProductItem> productItems;
	@Transient
	private Customer customer;
	private long customerID;
}
