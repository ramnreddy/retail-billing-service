package com.retail.service.util;

import com.retail.rules.ExpenseRule;
import com.retail.rules.LoyalityRule;
import com.retail.rules.Rule;
import com.retail.rules.RuleType;

import java.util.ArrayList;
import java.util.List;

public class RulesHelper {

    public static List<Rule> getAdditionalRules() {
        ArrayList<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(new LoyalityRule(RuleType.PERCENTAGE_RULE));
        ruleList.add(new ExpenseRule(RuleType.NON_PERCENTAGE_RULE));

        return ruleList;
    }
}
