package com.retail.model.bill;

import com.retail.model.com.reatil.model.rules.RuleType;
import com.retail.model.customer.User;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Invoice {
    User customer;
    List<Product> productList;
    Hashtable<RuleType, Integer> rulesExecuted;
    double totalAmount;

    public Invoice(){

    }

    public Invoice(User customer,List<Product> productList,double totalAmount) {
        this.customer = customer;
        this.productList = productList;
        this.totalAmount = totalAmount;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList(){
        return productList;
    }

    public User getCustomer() {
        return customer;
    }

    public void setRulesExecuted(Hashtable<RuleType ,Integer> rulesExecuted) {
        this.rulesExecuted = rulesExecuted;
    }

    public Hashtable<RuleType,Integer> getRulesExecuted() {
        return this.rulesExecuted;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
