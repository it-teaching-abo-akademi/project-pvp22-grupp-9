package com.example.pos_system_version_xx.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c FROM Card c WHERE c.cardNumber = ?1")
    Optional<Card> findCardByCardNumber(Long cardNumber);
}
