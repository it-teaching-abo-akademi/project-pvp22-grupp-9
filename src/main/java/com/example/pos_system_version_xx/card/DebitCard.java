package com.example.pos_system_version_xx.card;

import javax.persistence.Entity;
import java.time.Month;
import java.time.Year;

@Entity
public class DebitCard extends Card {
    public DebitCard(Long cardNumber, Month goodThruMonth, Year goodThruYear, boolean blocked, boolean expired, String holderName) {
        super(cardNumber, goodThruMonth, goodThruYear, blocked, expired, holderName);
        this.cardType = CardType.DEBIT;
    }

    public DebitCard() {

    }
}
