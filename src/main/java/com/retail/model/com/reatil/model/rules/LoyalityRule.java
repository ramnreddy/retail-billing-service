package com.retail.model.com.reatil.model.rules;

import com.retail.model.customer.User;

public class LoyalityRule extends RuleBase {

    public LoyalityRule(RuleType ruleType){
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user){
        if ( user != null && user.isLoyal())
            return amount * 0.1 ;
        return 0;
    }



    public RuleType getRuleType(){
        return RuleType.PERCENTAGE_RULE;
    }

}
