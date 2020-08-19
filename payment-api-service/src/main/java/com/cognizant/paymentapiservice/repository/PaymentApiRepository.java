package com.cognizant.paymentapiservice.repository;

import com.cognizant.paymentapiservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PaymentApiRepository extends JpaRepository<Payment, Integer> {

/*  @Modifying
    @Query("update Payment cust set cust.status = ?1 where cust.id = ?2")
    public int save(String status, int id);*/
}
