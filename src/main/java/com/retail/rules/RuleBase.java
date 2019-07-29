package com.retail.rules;

import com.retail.model.bill.Invoice;
import com.retail.model.bill.Product;
import com.retail.model.bill.ProductType;
import com.retail.model.customer.User;
import com.retail.service.BillCalculationService;

import java.util.Hashtable;
import java.util.logging.Logger;

/**
 * RuleBase implements the key discounts
 */
public abstract class RuleBase implements Rule {

    RuleType ruleType;

    public abstract double calculateDiscount(double amount, User user);
    private static final Logger log = Logger.getLogger(RuleBase.class.getName());

    /**
     * Calculate the discount amount & apply
     *
     * @param invoice
     * @return
     */
    public Invoice calculateDiscountAmount(Invoice invoice) {
        try {
            if (invoice != null && !invoice.getProductList().isEmpty()) {
                log.info("Discount type being applied based on rule type :"+this.ruleType);
                double totalAmount = invoice.getTotalAmount();
                double totalNonGroceryAmount;
                if (!invoice.isTotalAmountSet()) {
                    totalAmount = invoice.getProductList().stream().mapToDouble(Product::getProductPrice).sum();
                    invoice.setTotalAmount(totalAmount);
                }

                if (this.ruleType == RuleType.PERCENTAGE_RULE) {
                    totalNonGroceryAmount = invoice.getProductList().stream().filter(product -> product.getProductType() == ProductType.NON_GROCERIES)
                            .mapToDouble(Product::getProductPrice).sum();
                } else {
                    totalNonGroceryAmount = totalAmount;
                }

                // check for if discount rules can be applied only once
                if (isDiscountApplicable(invoice)) {
                    double discount = calculateDiscount(totalNonGroceryAmount, invoice.getCustomer());
                    log.info("Discounted Amount  :"+discount);
                    invoice.setTotalAmount(totalAmount - discount);
                    log.info("Amount after discount : "+invoice.getTotalAmount());

                }
                //set count of rules executed of each rule type
                setRuleTypeCounter(invoice);
                invoice.setTotalAmountSet(true);
            }
        } catch (Exception e) {
            log.info("Bill calculation failed due to : "+ e.getMessage());
            throw e;
        }
        return invoice;
    }

    /**
     * Check for if discuount rules can be applied only once
     *
     * @param invoice
     * @return
     */
    private boolean isDiscountApplicable(Invoice invoice) {
        return this.ruleType != RuleType.PERCENTAGE_RULE || invoice.getRulesExecuted() == null || invoice.getRulesExecuted().contains(this.ruleType) || invoice.getRulesExecuted().get(this.ruleType).intValue() <= 0;

    }

    /**
     * Track the type of discounts applied on invoice & avoid percentage based discounts applied more than once
     *
     * @param invoice
     */
    private void setRuleTypeCounter(Invoice invoice) {
        int count = 0;
        if (invoice.getRulesExecuted() != null && invoice.getRulesExecuted().get(this.ruleType) != null)
            count = Integer.valueOf(invoice.getRulesExecuted().get(this.ruleType));
        Hashtable<RuleType, Integer> Ht = new Hashtable<RuleType, Integer>(0);
        if (invoice.getRulesExecuted() != null && invoice.getRulesExecuted().contains(this.ruleType)) {
            Ht.put(this.ruleType, Integer.valueOf(count++));
            invoice.setRulesExecuted(Ht);
        } else {
            Ht.put(this.ruleType, Integer.valueOf(1));
            invoice.setRulesExecuted(Ht);
        }
    }


}
