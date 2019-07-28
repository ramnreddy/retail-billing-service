package com.retail.service;

import com.retail.model.bill.Invoice;
import com.retail.model.com.reatil.model.rules.AffiliateRule;
import com.retail.model.com.reatil.model.rules.EmployeeRule;
import com.retail.model.com.reatil.model.rules.Rule;
import com.retail.model.customer.Affiliate;
import com.retail.model.customer.Employee;
import com.retail.model.customer.User;
import com.retail.service.util.RulesHelper;

import java.util.List;

public class RuleEngineService {

    public Invoice calculateBill(Invoice invoice) {

        double amountPayable;

        Rule rule = getRule(invoice.getCustomer());

        if (rule != null){
            invoice = rule.calculateDiscountAmount(invoice);
        }

        invoice = applyAddionalRules(RulesHelper.getAdditionalRules(),invoice);
        return invoice;
    }

    private Rule getRule(User user) {
        if (user instanceof Employee) {
            return new EmployeeRule();
        }
        else if (user instanceof Affiliate) {
            return new AffiliateRule();
        }
        return null;
    }

    private Invoice applyAddionalRules(List<Rule> additionalRules, Invoice invoice) {
        additionalRules.forEach(rule -> rule.calculateDiscountAmount(invoice));
        return invoice;
    }
}
