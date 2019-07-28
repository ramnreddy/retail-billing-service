package com.retail.model.com.reatil.model.rules;

import com.retail.model.customer.User;

public class ExpenseRule extends RuleBase{

    public ExpenseRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user){
        return Math.round(amount/100) * 5;
    }

    public RuleType getRuleType(){
        return RuleType.NON_PERCENTAGE_RULE;
    }
}

