package com.retail.model.customer;

import java.time.LocalDate;
import java.time.Period;

public class Customer extends User {

    LocalDate customerRegistrationDate;
    boolean isLoyal;

    public Customer() {
        super();
    }

    public Customer(LocalDate customerRegistrationDate){
        super();
        this.isLoyal = isLoyalCustomer(customerRegistrationDate);
    }

    public boolean isLoyal() {
        return isLoyal;
    }

    public boolean isLoyalCustomer(LocalDate customerRegistrationDate) {
        Period period = Period.between(customerRegistrationDate, LocalDate.now());
        int diff = period.getYears();

        return (period.getYears() >= 2);
    }


}
