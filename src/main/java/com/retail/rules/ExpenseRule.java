package com.retail.rules;

import com.retail.model.customer.User;

import java.util.logging.Logger;

public class ExpenseRule extends RuleBase {
    private final static double  EXP_DISCOUNT = 5;
    private static final Logger log = Logger.getLogger(ExpenseRule.class.getName());
    public ExpenseRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user) {
        return Math.floor(amount / 100) * EXP_DISCOUNT;
    }

    public RuleType getRuleType() {
        return RuleType.NON_PERCENTAGE_RULE;
    }
}

