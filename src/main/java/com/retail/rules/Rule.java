package com.retail.rules;

import com.retail.model.bill.Invoice;
import com.retail.model.customer.User;

public interface Rule {

    double calculateDiscount(double amount, User user);

    Invoice calculateDiscountAmount(Invoice invice);
}
