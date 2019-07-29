package com.retail.rules;

import com.retail.model.customer.User;

import java.util.logging.Logger;

public class LoyalityRule extends RuleBase {
    private final static double  LOYAL_DISCOUNT = 0.05;
    private static final Logger log = Logger.getLogger(EmployeeRule.class.getName());

    public LoyalityRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user) {
        log.info("Loayality Discount being applied :"+LOYAL_DISCOUNT * 100 + "%");
        if (user != null && user.isLoyal())
            return amount * LOYAL_DISCOUNT;
        return 0;
    }

    public RuleType getRuleType() {
        return RuleType.PERCENTAGE_RULE;
    }

}
