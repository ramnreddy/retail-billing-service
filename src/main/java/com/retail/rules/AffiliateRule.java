package com.retail.rules;

import com.retail.model.customer.User;

import java.util.logging.Logger;

public class AffiliateRule extends RuleBase {
    private final static double  AFF_DISCOUNT = 0.1;
    private static final Logger log = Logger.getLogger(AffiliateRule.class.getName());

    public AffiliateRule() {
        super();
    }

    public AffiliateRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user) {
        log.info("Affliate Discount being applied is :"+AFF_DISCOUNT * 100 + "%");
        return amount * AFF_DISCOUNT;
    }

    public RuleType getRuleType() {
        return RuleType.PERCENTAGE_RULE;
    }
}
