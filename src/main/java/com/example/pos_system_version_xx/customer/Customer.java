package com.example.pos_system_version_xx.customer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Customer {

    @Id
    private int customerNo;
    private String firstName;
    private String lastName;
    private Date birthDate;

    public Customer(){
    }

    public Customer(int customerNo, String firstName, String lastName, Date birthDate) {
        this.customerNo = customerNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public int getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(int customerNo) {
        this.customerNo = customerNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
