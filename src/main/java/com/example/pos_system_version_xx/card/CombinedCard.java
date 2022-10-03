package com.example.pos_system_version_xx.card;

import javax.persistence.Entity;
import java.time.Month;
import java.time.Year;

@Entity
public class CombinedCard extends Card {
    public CombinedCard(Long cardNumber, Month goodThruMonth, Year goodThruYear, boolean blocked, boolean expired, String holderName) {
        super(cardNumber, goodThruMonth, goodThruYear, blocked, expired, holderName);
        this.cardType = CardType.COMBINED;
    }

    public CombinedCard() {

    }
}
