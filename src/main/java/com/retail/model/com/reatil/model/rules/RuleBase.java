package com.retail.model.com.reatil.model.rules;

import com.retail.model.bill.Invoice;
import com.retail.model.bill.Product;
import com.retail.model.bill.ProductType;
import com.retail.model.customer.User;

import java.util.Hashtable;


public abstract class RuleBase implements Rule {

    RuleType ruleType;

    public abstract double calculateDiscount(double amount, User user);

    public Invoice calculateDiscountAmount(Invoice invoice){

        double totalAmountBeforeDiscuount = 0;
        double totalNonGroceryAmount;

        if(invoice != null && !invoice.getProductList().isEmpty()) {
            totalAmountBeforeDiscuount = invoice.getProductList().stream().mapToDouble(Product::getProductPrice).sum();
            totalNonGroceryAmount = invoice.getProductList().stream().filter(product -> product.getProductType() == ProductType.NON_GROCERIES)
            .mapToDouble(Product::getProductPrice).sum();

            }
            else {
                totalNonGroceryAmount = totalAmountBeforeDiscuount;
            }

        // check for if discount rules can be applied only once
        if (isDiscountApplicable(invoice)) {
            double discount = calculateDiscount(totalNonGroceryAmount, invoice.getCustomer());
            invoice.setTotalAmount(totalAmountBeforeDiscuount - discount);
        }

        //set count of rules executed of each rule type
        setRuleTypeCounter(invoice);
        return invoice;
    }

    // check for if discuount rules can be applied only once
    private boolean isDiscountApplicable(Invoice invoice) {

        if(this.ruleType == RuleType.PERCENTAGE_RULE && invoice.getRulesExecuted() != null && invoice.getRulesExecuted().contains(this.ruleType) &&
         invoice.getRulesExecuted().get(this.ruleType).intValue() > 0 )
            return false;
        return true;

    }

    private void setRuleTypeCounter(Invoice invoice) {
        int count = 0;
        if (invoice.getRulesExecuted() !=null && invoice.getRulesExecuted().get(this.ruleType) != null )
            count = Integer.valueOf(invoice.getRulesExecuted().get(this.ruleType));
        Hashtable<RuleType,Integer> Ht = new Hashtable<RuleType,Integer>(0);

        if (invoice.getRulesExecuted() != null && invoice.getRulesExecuted().contains(this.ruleType)) {
            Ht.put(this.ruleType,Integer.valueOf(count++));
            invoice.setRulesExecuted(Ht);
        }
        else {
            Ht.put(this.ruleType,Integer.valueOf(1));
            invoice.setRulesExecuted(Ht);
        }
    }


}
