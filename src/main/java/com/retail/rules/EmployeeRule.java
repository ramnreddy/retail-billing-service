package com.retail.rules;

import com.retail.model.customer.User;

import java.util.logging.Logger;

public class EmployeeRule extends RuleBase {
    private final static double  EMP_DISCOUNT = 0.3;
    private static final Logger log = Logger.getLogger(EmployeeRule.class.getName());

    public EmployeeRule() {
        super();
    }

    public EmployeeRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user) {
        log.info("Discount being applied :"+EMP_DISCOUNT * 100 + "%");
        return amount * EMP_DISCOUNT;
    }

    public RuleType getRuleType() {
        return RuleType.PERCENTAGE_RULE;
    }
}
