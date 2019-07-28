package com.retail.service.util;

import com.retail.model.com.reatil.model.rules.ExpenseRule;
import com.retail.model.com.reatil.model.rules.LoyalityRule;
import com.retail.model.com.reatil.model.rules.Rule;
import com.retail.model.com.reatil.model.rules.RuleType;

import java.util.ArrayList;
import java.util.List;

public class RulesHelper {

    public static List<Rule> getAdditionalRules(){
        ArrayList<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(new LoyalityRule(RuleType.PERCENTAGE_RULE));
        ruleList.add(new ExpenseRule(RuleType.NON_PERCENTAGE_RULE));

        return ruleList;
    }
}
