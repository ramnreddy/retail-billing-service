package com.retail.rules;

import com.retail.model.bill.Invoice;
import com.retail.model.bill.Product;
import com.retail.model.bill.ProductType;
import com.retail.model.customer.User;

import java.util.Hashtable;

/**
 * RuleBase implements the key discounts
 */
public abstract class RuleBase implements Rule {

    RuleType ruleType;

    public abstract double calculateDiscount(double amount, User user);

    /**
     * Calculate the discount amount & apply
     *
     * @param invoice
     * @return
     */
    public Invoice calculateDiscountAmount(Invoice invoice) {
        if (invoice != null && !invoice.getProductList().isEmpty()) {
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
                invoice.setTotalAmount(totalAmount - discount);
            }

            //set count of rules executed of each rule type
            setRuleTypeCounter(invoice);
            invoice.setTotalAmountSet(true);

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
