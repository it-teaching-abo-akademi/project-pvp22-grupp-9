package com.example.pos_system_version_xx.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    public void addNewCard(Card card) {
        Optional<Card> cardOptional = cardRepository.findCardByCardNumber(card.getCardNumber());
        if (cardOptional.isPresent()) {
            throw new IllegalStateException("Card with number " + card.getCardNumber() + " already exists!");
        }
        cardRepository.save(card);
    }

    public void deleteCard(Long cardNumber) {
        boolean exists = cardRepository.existsById(cardNumber);
        if (!exists) {
            throw new IllegalStateException("Card with card number " + cardNumber + " does not exist!");
        }
        cardRepository.deleteById(cardNumber);
    }

    @Transactional
    public void updateCard(Long cardNumber, boolean blocked, boolean expired, String holderName) {
        Card card = cardRepository.findById(cardNumber).orElseThrow(() -> new IllegalStateException("Card with card number " + cardNumber + " does not exist!"));
        card.setBlocked(blocked);
        card.setExpired(expired);
        if (holderName != null && holderName.length() > 0 && !Objects.equals(card.getHolderName(), holderName)) {
            card.setHolderName(holderName);
        }
    }
}
