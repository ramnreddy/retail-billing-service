package com.retail.model.com.reatil.model.rules;

import com.retail.model.customer.User;

public class AffiliateRule extends RuleBase {

    public double calculateDiscount(double amount, User user){
        return amount * (10/100);
    }

    public RuleType getRuleType(){
        return RuleType.PERCENTAGE_RULE;
    }
}
