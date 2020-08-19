package com.cognizant.customer.repository;

import com.cognizant.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Modifying
    @Query("update Customer cust set cust.name = ?1 where cust.id = ?2")
    public int updateCustomer(String name, int id);


}

