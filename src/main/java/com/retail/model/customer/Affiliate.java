package com.retail.model.customer;

import java.time.LocalDate;

public class Affiliate extends Customer {

    public Affiliate() {
        super();
    }

    public Affiliate(LocalDate customerRegistrationDate) {
        super(customerRegistrationDate);
    }
}
