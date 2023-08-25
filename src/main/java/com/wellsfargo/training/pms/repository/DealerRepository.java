package com.wellsfargo.training.pms.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wellsfargo.training.pms.model.Dealer;
import com.wellsfargo.training.pms.model.DealerAndAddressProjection;

public interface DealerRepository extends JpaRepository<Dealer, Long> {
	
	//Custom Method to method to fetch record/object based on email field - non primary key field
	public Optional<Dealer> findByEmail(String email);

	public Optional<Dealer> findByPhoneNo(String phone);
	
	/*Some time case arises, where we need a custom query to fulfil one test case like  you use more than 2-3 
	 * query parameters or need to define multiple joins to other entities. 
	* We can use @Query annotation to specify a query within a repository.
	* In these situations, you better use Spring Data JPA’s @Query annotation to 
	* specify a custom JPQL or native SQL query.
		* 
	*  */
		// Custom Queries	
	  
	  @Query("SELECT new com.wellsfargo.training.pms.model.DealerAndAddressProjection"
				+ "(d.id, d.fname,d.lname,d.phoneNo,"
				+ "d.email, a.street, a.city,a.pincode) "
				+ "FROM Dealer d JOIN d.address a")
		List<DealerAndAddressProjection> findSelectedFieldsFromDealerAndAddress();
	  
	  
}
