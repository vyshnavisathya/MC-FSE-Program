package com.cognizant.cardservice.dao;

import com.cognizant.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CardServiceDao extends JpaRepository<Card, Integer> {

    @Modifying
    @Query("update Card card set card.cardNumber = :cardNumber where card.customerId = :id")
    public int updateCard(String cardNumber, int id);

    public Card findByCustomerId(Integer customerId);

}
