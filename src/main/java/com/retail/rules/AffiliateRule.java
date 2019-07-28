package com.retail.rules;

import com.retail.model.customer.User;

public class AffiliateRule extends RuleBase {

    public AffiliateRule() {
        super();
    }

    public AffiliateRule(RuleType ruleType) {
        super.ruleType = ruleType;
    }

    public double calculateDiscount(double amount, User user) {
        return amount * 0.1;
    }

    public RuleType getRuleType() {
        return RuleType.PERCENTAGE_RULE;
    }
}
