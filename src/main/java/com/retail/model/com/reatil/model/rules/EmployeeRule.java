package com.retail.model.com.reatil.model.rules;

import com.retail.model.customer.User;

public class EmployeeRule extends RuleBase {

    public EmployeeRule() {
        super();
    }

    public EmployeeRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user){
        return amount * 0.3 ;
    }

    public RuleType getRuleType(){
        return RuleType.PERCENTAGE_RULE;
    }
}
