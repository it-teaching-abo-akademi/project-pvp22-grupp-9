package com.example.pos_system_version_xx.card;

import java.time.Month;
import java.time.Year;

public class CreditCard extends AbstractCard {
    public CreditCard(Integer cardNumber, Month goodThruMonth, Year goodThruYear, boolean blocked, boolean expired, String holderName) {
        super(cardNumber, goodThruMonth, goodThruYear, blocked, expired, holderName);
    }
}
