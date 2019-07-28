package com.retail.model.customer;

import java.time.LocalDate;

public class Employee extends Customer {
    public Employee() {
        super();
    }

    public Employee(LocalDate customerRegistrationDate) {
        super(customerRegistrationDate);
    }
}


