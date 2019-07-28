package com.retail.model.bill;

import com.retail.model.customer.User;
import com.retail.rules.RuleType;

import java.util.Hashtable;
import java.util.List;

public class Invoice {
    User customer;
    List<Product> productList;
    Hashtable<RuleType, Integer> rulesExecuted;
    double totalAmount;
    boolean isTotalAmountSet;

    public Invoice() {

    }

    public Invoice(User customer, List<Product> productList, double totalAmount, boolean isTotalAmountSet) {
        this.customer = customer;
        this.productList = productList;
        this.totalAmount = totalAmount;
        this.isTotalAmountSet = isTotalAmountSet;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public User getCustomer() {
        return customer;
    }

    public Hashtable<RuleType, Integer> getRulesExecuted() {
        return this.rulesExecuted;
    }

    public void setRulesExecuted(Hashtable<RuleType, Integer> rulesExecuted) {
        this.rulesExecuted = rulesExecuted;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isTotalAmountSet() {
        return this.isTotalAmountSet;
    }

    public void setTotalAmountSet(boolean isTotalAmountSet) {
        this.isTotalAmountSet = isTotalAmountSet;
    }
}
