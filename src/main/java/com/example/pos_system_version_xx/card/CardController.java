package com.example.pos_system_version_xx.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public List<Card> getCards() {
        return cardService.getCards();
    }

    @PostMapping(path = "/register/bonus")
    public void registerNewCard(@RequestBody BonusCard bonusCard) {
        if (bonusCard.getCardType() != CardType.BONUS) {
            throw new IllegalStateException("CARD TYPE ERROR");
        }
        cardService.addNewCard(bonusCard);
    }
    @PostMapping(path = "/register/combinded")
    public void registerNewCard(@RequestBody CombinedCard combinedCard) {
        if (combinedCard.getCardType() != CardType.COMBINED) {
            throw new IllegalStateException("CARD TYPE ERROR");
        }
        cardService.addNewCard(combinedCard);
    }

    @PostMapping(path = "/register/credit")
    public void registerNewCard(@RequestBody CreditCard creditCard) {
        if (creditCard.getCardType() != CardType.CREDIT) {
            throw new IllegalStateException("CARD TYPE ERROR");
        }
        cardService.addNewCard(creditCard);
    }

    @PostMapping(path = "/register/debit")
    public void registerNewCard(@RequestBody DebitCard debitCard) {
        if (debitCard.getCardType() != CardType.DEBIT) {
            throw new IllegalStateException("CARD TYPE ERROR");
        }
        cardService.addNewCard(debitCard);
    }


    @DeleteMapping(path = "{cardNumber}")
    public void deleteCard(@PathVariable("cardNumber") Long cardNumber) {
        cardService.deleteCard(cardNumber);
    }

    @PutMapping(path = "{cardNumber}")
    public void updateCard(@PathVariable("cardNumber") Long cardNumber, @RequestParam(required = false) boolean blocked, @RequestParam(required = false) boolean expired, @RequestParam(required = false) String holderName) {
        cardService.updateCard(cardNumber, blocked, expired, holderName);
    }
}
