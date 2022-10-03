package com.example.pos_system_version_xx.card;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Month;
import java.time.Year;
import java.util.List;

@Configuration
public class CardConfig {

    @Bean
    CommandLineRunner commandLineRunner(CardRepository repository) {
        return args -> {
            Card bonusCard = new BonusCard(1234567890L, Month.JANUARY, Year.of(2022), false, true, "Tom Fredman");
            Card combinedCard = new CombinedCard(2345678901L, Month.FEBRUARY, Year.of(2023), true, false, "Tom Fredman");
            Card creditCard = new CreditCard(3456789012L, Month.MARCH, Year.of(2024), false, false, "Tom Fredman");
            Card debitCard = new DebitCard(4567890123L, Month.APRIL, Year.of(2025), false, false, "Tom Fredman");
            repository.saveAll(List.of(bonusCard, combinedCard, creditCard, debitCard));
        };
    }
}
