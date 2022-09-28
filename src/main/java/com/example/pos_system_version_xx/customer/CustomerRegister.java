package com.example.pos_system_version_xx.customer;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
@Repository //@Repository annotation for classes that do database access
public interface CustomerRegister {

    @GetMapping
    public static void findByBonusCard(int cardNumber, Date expiryMonth, Date expiryYear){

    }

    @GetMapping
    public static void findByCustomerNo(@RequestBody int customerNo){

    }

    @PostMapping
    public static void saveCustomer(Customer customer){

    }
}
