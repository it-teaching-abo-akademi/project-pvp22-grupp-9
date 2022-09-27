package com.example.pos_system_version_xx.card;

import java.time.Month;
import java.time.Year;

public abstract class AbstractCard {
    private Integer cardNumber;
    private Month goodThruMonth;
    private Year goodThruYear;
    private boolean blocked;
    private boolean expired;
    private String holderName;
    public AbstractCard(Integer cardNumber, Month goodThruMonth, Year goodThruYear, boolean blocked, boolean expired, String holderName) {
        this.cardNumber = cardNumber;
        this.goodThruMonth = goodThruMonth;
        this.goodThruYear = goodThruYear;
        this.blocked = blocked;
        this.expired = expired;
        this.holderName = holderName;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Month getGoodThruMonth() {
        return goodThruMonth;
    }

    public void setGoodThruMonth(Month goodThruMonth) {
        this.goodThruMonth = goodThruMonth;
    }

    public Year getGoodThruYear() {
        return goodThruYear;
    }

    public void setGoodThruYear(Year goodThruYear) {
        this.goodThruYear = goodThruYear;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    @Override
    public String toString() {
        return "AbstractCard{" +
                "cardNumber=" + cardNumber +
                ", goodThruMonth=" + goodThruMonth +
                ", goodThruYear=" + goodThruYear +
                ", blocked=" + blocked +
                ", expired=" + expired +
                ", holderName='" + holderName + '\'' +
                '}';
    }
}
