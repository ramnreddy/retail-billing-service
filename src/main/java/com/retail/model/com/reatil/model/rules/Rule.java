package com.retail.model.com.reatil.model.rules;

import com.retail.model.bill.Invoice;
import com.retail.model.customer.User;

public interface Rule {

    public double calculateDiscount(double amount, User user);
    public Invoice calculateDiscountAmount(Invoice invice);
}
