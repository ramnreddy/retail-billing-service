package com.retail.service;

import com.retail.model.bill.Invoice;
import com.retail.model.customer.Affiliate;
import com.retail.model.customer.Employee;
import com.retail.model.customer.User;
import com.retail.rules.AffiliateRule;
import com.retail.rules.EmployeeRule;
import com.retail.rules.Rule;
import com.retail.rules.RuleType;
import com.retail.service.util.RulesHelper;

import java.util.List;
import java.util.logging.Logger;

/**
 * BillCalculationService
 */

public class BillCalculationService {
    private static final Logger log = Logger.getLogger(BillCalculationService.class.getName());

    public Invoice calculateBill(Invoice invoice) {
        try {
            Rule rule = getRule(invoice.getCustomer());
            if (rule != null) {
                invoice = rule.calculateDiscountAmount(invoice);
                log.info("Total amount after applying first set of rule : " + invoice.getTotalAmount());
            }
            invoice = applyAddionalRules(RulesHelper.getAdditionalRules(), invoice);
            log.info("Total amount after applying all discounts : " + invoice.getTotalAmount());
        }
        catch (Exception ex){
            log.info("Error calulating bill {} :"+ ex.getMessage());
        }
        return invoice;
    }

    /**
     * Return the rule based on type of customer
     *
     * @param user : type of teh customer (Employye or Affiliate or normal customer
     * @return type of rule
     */

    private Rule getRule(User user) throws Exception {
        if (user instanceof Employee) {
            return new EmployeeRule(RuleType.PERCENTAGE_RULE);
        } else if (user instanceof Affiliate) {
            return new AffiliateRule(RuleType.PERCENTAGE_RULE);
        }
        return null;
    }

    /**
     * Applies additional common discount based on Loyality & Overal expenses
     *
     * @param additionalRules
     * @param invoice
     * @return
     */
    private Invoice applyAddionalRules(List<Rule> additionalRules, Invoice invoice) {
        additionalRules.forEach(rule -> rule.calculateDiscountAmount(invoice));
        return invoice;
    }
}
