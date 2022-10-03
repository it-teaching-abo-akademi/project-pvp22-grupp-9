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

    @PostMapping
    public void registerNewCard(@RequestBody CreditCard card) {
        cardService.addNewCard(card);
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
